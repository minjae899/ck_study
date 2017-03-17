package spring.common.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.StringTokenizer;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * FTP 서버 연동을 위해 작성된 클래스이다.<br>
 * 
 * @version 1.0
 */

public class FTPUtil 
{
	/** FTP client */
	private FTPClient client = null;
	
	
	/**
	 * @param serverIp
	 * @param loginId
	 * @param loginPwd
	 * @throws SocketException
	 * @throws IOException
	 */
	public FTPUtil(String serverIp, String loginId, String loginPwd) throws SocketException, IOException
	{
		
		try{
			FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
			//FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_NT);
			client = new FTPClient();	
		    client.configure(conf);		
			client.setControlEncoding("euc-kr"); //한글 파일때문에 기본 encoding euc-kr로 한다.
			client.connect(serverIp);
			int reply = client.getReplyCode();
			if(!FTPReply.isPositiveCompletion(reply)){
				try{	
					client.disconnect();	
				}catch(Exception e) {
					client = null;
				}
			}
			client.login(loginId, loginPwd);
			client.setFileType(FTPClient.BINARY_FILE_TYPE);
			
		}catch(Exception e){
			e.printStackTrace();
			client = null;
		}
	}
	/**
	 * FTP 연결을 종료한다.
	 */
	public boolean isConnection(){
		if(client == null) return false;
		else return true;
	}
	
	/**
	 * FTP 연결을 종료한다.
	 */
	public  void closeConnection()
	{
		if (client != null){
			try{	
				client.logout();		
			}catch (Exception e){
				//e.printStackTrace();
				//Logger.info( e.toString() + "\r\n" + e.getMessage());
			}
			if (client.isConnected()){				
				try{	
					client.disconnect();	
				}
				catch (Exception e){
					//Logger.info(e.toString() + "\r\n" + e.getMessage());
				}
			}
			//Logger.info("FTP server disconnected...");
			client = null;
		}
	}
	/**
	 * FTP 서버에 있는 파일을 클라이언트로 내려 받는다.
	 * 
	 * @param serverFilePath 서버 파일 경로
	 * @return  연결되면 FTP 서버 파일의 Stream, 그렇지 않으면 null
	 */	
	public InputStream retrieveFileStream(String serverFilePath)
	{
		InputStream is = null;
		try{
			is = client.retrieveFileStream(serverFilePath);
		}catch (IOException ioe){
			is = null;
		}
		return is;
	}
	/**
	 * FTP 서버에 있는 파일을 클라이언트로 내려 받는다.
	 * 
	 * @param serverFilePath 서버 파일 경로
	 * @param localFilePath 클라이언트 파일 경로
	 * @return 파일을 내려받으면 true, 그렇지 않으면 false
	 */
	public boolean retrieveFile(String serverFilePath, String localFilePath)
	{
		
		//System.out.println("#######serverFilePath#########" + serverFilePath);
		//System.out.println("#######localFilePath#########" + localFilePath);
		boolean bSuccess = false;
		
		FileOutputStream fos = null;
		try{
			fos = new FileOutputStream(localFilePath);
			client.setFileType(FTPClient.BINARY_FILE_TYPE);
			if (client.retrieveFile(serverFilePath, fos)) bSuccess = true;
		}catch (FileNotFoundException fne){
			fne.printStackTrace();
			bSuccess = false;
			//Logger.info(fne.toString() + System.getProperty("line.separator") + fne.getMessage());			
		}catch (IOException ioe){
			ioe.printStackTrace();
			bSuccess = false;
			//Logger.info(ioe.toString() + System.getProperty("line.separator") + ioe.getMessage());
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			if (fos != null){
				try	{	fos.flush(); } catch(Exception e){}
				try	{	fos.close(); } catch(Exception e){}
				fos = null;
			}
		}
		return bSuccess;
	}
	/**
	 * 클라이언트에 있는 파일을 FTP 서버로 올린다.
	 * 
	 * @param serverFilePath 서버 파일 경로
	 * @param localFilePath 클라이언트 파일 경로
	 * @return 파일을 올리면 true, 그렇지 않으면 false
	 */	
	public boolean storeFile(MultipartHttpServletRequest req ,String serverFilePath, String localFilePath)
	{
		boolean bSuccess = false;
		FileInputStream fio = null;
		//System.out.println("serverFilePath ######## "+serverFilePath);
		//System.out.println("localFilePath ######## "+localFilePath);
		
		//MultipartFile file1 = req.getFile("file_nm");
		
		try{
			fio = new FileInputStream(localFilePath);
			System.out.println("fio:::"+fio);
			client.setFileType(FTPClient.BINARY_FILE_TYPE);
			if(client.storeFile(serverFilePath, fio)) bSuccess = true;
		}catch (FileNotFoundException fne){
			bSuccess = false;
			//Logger.info(fne.toString() + System.getProperty("line.separator") + fne.getMessage());			
		}catch (IOException ioe){
			ioe.printStackTrace();
			bSuccess = false;
			//Logger.info(ioe.toString() + System.getProperty("line.separator") + ioe.getMessage());
		}finally{
			if (fio != null){
				try	{	fio.close(); } catch(Exception e){}
				fio = null;
			}
		}
		return bSuccess;
	}
	/**
	 * FTP 서버의 파일을 삭제한다.
	 * 
	 * @param serverFilePath 삭제할 파일
	 * @return 삭제에 성공하면 true, 그렇지 않으면 false
	 */
	public boolean deleteFile(String serverFilePath)
	{
		boolean bSuccess = false;
		try{
			if (client.deleteFile(serverFilePath))
				bSuccess = true;
		}catch (IOException ioe){
			bSuccess = false;
			//Logger.info(ioe.toString() + System.getProperty("line.separator") + ioe.getMessage());
		}
		return bSuccess;
	}
	/**
	 * FTP 서버의 파일명을 바꾼다.
	 * 
	 * @param serverFilePath 기존 파일명
	 * @param newServerFilepath 바꿀 파일명
	 * @return 파일명을 바꾸면 true, 그렇지 않으면 false
	 */
	public boolean rename(String serverFilePath, String newServerFilepath)
	{
		boolean bSuccess = false;
		try{
			if (client.rename(serverFilePath, newServerFilepath))
				bSuccess = true;
		}catch (IOException ioe){
			bSuccess = false;
			//Logger.info(ioe.toString() + System.getProperty("line.separator") + ioe.getMessage());
		}
		return bSuccess;
	}
	
	public boolean removeDirectory(String path){
		boolean blnReturn  = false;
		try{
			blnReturn =  client.removeDirectory(path);
		}catch (IOException e) {
			e.printStackTrace();
		}
		return blnReturn;
	}
		
	/**
	 * FTP 서버의 파일 경로상에 파일이 없으면 directory를 삭제한다.
	 *  
	 * @param path 삭제할 directory
	 * @param defaultPath 삭제하지 말아야할 directory
	 * @return cd 명령에 성공하면 true, 그렇지 않으면 false
	 */
	public boolean changeDirectorForRemoveDirectory(String path, String defaultPath)
	{
		boolean isSuccess = false;
		boolean bContinue = true;
		FTPFile[] file = null;
		String ftp_path = path;
		String del_dir = null;
		
		try{		
			if (ftp_path == null || "".equals(ftp_path.trim())){
				return false;
			}
			if (!client.changeWorkingDirectory(ftp_path)){
				return false;
			}
			while(bContinue && !ftp_path.equals(defaultPath)){
				file = client.listFiles();
				if (file.length == 0){
					del_dir = ftp_path.substring(ftp_path.lastIndexOf("/") + "/".getBytes().length);					
					ftp_path = ftp_path.substring(0, ftp_path.lastIndexOf("/"));
					if (!client.changeWorkingDirectory(ftp_path)){
						return false;
					}
					
					if (!client.removeDirectory(del_dir)){
						return false;
					}
				}else{
					bContinue = false;
				}
			}
			isSuccess = true;
		}catch (IOException ioe){
			//Logger.info(ioe.toString() + System.getProperty("line.separator") + ioe.getMessage());
			isSuccess = false;
		}catch (Exception ex){
			ex.printStackTrace();
			//Logger.error(ex.getMessage(), ex);
			isSuccess = false;
		}
		return isSuccess;
	}
	
	/**
	 * FTP cd 명령을 실행한다. <br>
	 * path가 존재하지 않으면 mkdir 명령을 실행한다.<br> 
	 * 
	 * @param path 최종적으로 이동할 (절대/상대)경로
	 * @return path 이동에 성공하면 true, 그렇지 않으면 false 
	 */
	public boolean changeDirectoryForMakeDirectory(String path)
	{
		boolean isSuccess = false;
		StringTokenizer st =  null;
		String pathImsi = null;

		try{
			if (path == null || "".equals(path.trim())){
				return false;
			}
			st = new StringTokenizer(path, "/");
			if (path.indexOf("/") == 0){ // root로 부터 시작된 path일 경우
				if (!client.changeWorkingDirectory("/")){  // root 로 이동
					return false;
				}
			} // end if (path.indexOf("/") == 0)
			while(st.hasMoreTokens()){
				pathImsi = st.nextToken();
				if (!client.changeWorkingDirectory(pathImsi)){ // path로 이동하지 못할 경우
					if (!client.makeDirectory(pathImsi)){ // path를 만들지 못할 경우
						return false;
					}else{
						if (!client.changeWorkingDirectory(pathImsi)){ // 만든 path로 이동하지 못할 경우
							return false;
						}
					}
				}				
			} // end while(st.hasMoreTokens())
			isSuccess = true;
		}catch (IOException ioe){
			//Logger.info(ioe.toString() + System.getProperty("line.separator") + ioe.getMessage());
			isSuccess = false;
		}
		return isSuccess;
	}
	
	
	/**
	 * 디렉토리 생성. 존재하는 폴더이면 생성하지 않는다.
	 * 
	 * @param rootPath
	 * @param filePath
	 * @throws IOException 
	 */
	public boolean changeDirectoryForMakeDirectory(String rootPath, String filePath){
		
		String dirName = null;
		boolean blnSucc = true;
		try{
			if (filePath.indexOf(rootPath) <= 0){
				StringTokenizer st = new StringTokenizer(filePath.replaceAll(rootPath, ""), "/");
				client.changeWorkingDirectory(rootPath);
				while(st.hasMoreTokens()){
					dirName = st.nextToken();
					if (!client.changeWorkingDirectory(dirName)){ // path로 이동하지 못할 경우
						if (!client.makeDirectory(dirName)){ // path를 만들지 못할 경우
							return false;
						}else{
							if (!client.changeWorkingDirectory(dirName)){ // 만든 path로 이동하지 못할 경우
								blnSucc = false;
								return false;
							}
						}
					}
				} // end while(st.hasMoreTokens())
			}else{
				blnSucc = false;
			}
		}catch(IOException ioe){
			//Logger.info(ioe.toString() + System.getProperty("line.separator") + ioe.getMessage());
			blnSucc = false;
		}
		return blnSucc;
	}
	
	public void retrieveFile1(String serverFilePath, String localFilePath)
	{
		
		System.out.println("#######serverFilePath#########" + serverFilePath);
		System.out.println("#######localFilePath#########" + localFilePath);
//		boolean bSuccess = false;
		//System.out.println("serverFilePath:::::::"+serverFilePath);
		//System.out.println("localFilePath::::::"+localFilePath);
		FileOutputStream fos = null;
		try{
			client.setControlEncoding("euc-kr");
			fos = new FileOutputStream(localFilePath);
			System.out.println("fos:::::::"+fos);
			client.setFileType(FTPClient.BINARY_FILE_TYPE);
			client.retrieveFile(serverFilePath, fos);
		}catch (FileNotFoundException fne){
			fne.printStackTrace();
			//Logger.info(fne.toString() + System.getProperty("line.separator") + fne.getMessage());			
		}catch (IOException ioe){
			ioe.printStackTrace();
			//Logger.info(ioe.toString() + System.getProperty("line.separator") + ioe.getMessage());
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			if (fos != null){
				try	{	fos.flush(); } catch(Exception e){}
				try	{	fos.close(); } catch(Exception e){}
				fos = null;
			}
		}
	}
}
