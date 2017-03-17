package spring.common.util;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartHttpServletRequest;

/** 2012-09-20 김정수
 * ftp파일전송
 * @identity FtpFile.java
 * @author 김정수
 * 
 * Copyright (C) 2012 by Libeka Co. Ltd. All right reserved.
 */

public class FtpFile {
	
	private String server_ip;
	private String server_id;
	private String server_password;
	
	private FTPUtil ftp = null;
	
	public FtpFile(String server_ip , String server_id, String server_pw ){
		
		this.server_ip = server_ip;
		this.server_id = server_id;
		this.server_password = server_pw;
	}
	
	public FtpFile(){
		
	}
	
	/**
	 * 서버 ftp 접속 후 클라이언트로 파일 전송
	 * @param resp
	 * @return 
	 * @throws Exception
	 */ 
	public String ftpFileDown(HttpServletResponse resp , String webDownDir ,String filePath , String fileName){
		
		
		String webUpDir = webDownDir;
		String msg = "";
		try{
			
			ftp =  new FTPUtil(this.server_ip, this.server_id, this.server_password);
			
			if(ftp.isConnection()){ //접속 되었을 경우
				/*FTP 다운*/
				System.out.println("filePath + fileName::::::"+filePath + fileName);
				if (!ftp.retrieveFile(filePath + fileName, webUpDir+fileName)){
					msg = fileName+" 파일을 찾을 수 없습니다.";
				}else{
					/*Client로 파일 전송.(다운로드)*/
					try{
						//gov.nanet.watermarc.util.FileUtil.fileWriteDown(fileName, fileName, resp);
						ftp.retrieveFile1(filePath + fileName, webUpDir+fileName);
					}catch(Exception e){
						return fileName+" 다운로드 오류";
					}
				}
			}else{
				msg = "서버에 접속할 수 없습니다.";
			}
		}catch(Exception e){
			return fileName+" 다운로드 오류";
		}finally
		{
			 if (ftp != null) ftp.closeConnection();
		}
		return msg;
	}
	
	/**
	 * 서버 ftp 접속 후 클라이언트로 파일 전송
	 * @param resp
	 * @return 
	 * @throws Exception
	 */ 
	public String ftpFileUp(MultipartHttpServletRequest req , String webDownDir ,String filePath , String fileName){
		
		
		String webUpDir = webDownDir;
		String msg = "";
		
		try{
			
			ftp =  new FTPUtil(this.server_ip, this.server_id, this.server_password);
			
			if(ftp.isConnection()){ //접속 되었을 경우
				/*FTP 다운*/
				System.out.println("filePath + fileName::::::"+filePath + fileName);
				if (!ftp.storeFile(req, filePath + fileName, webUpDir+fileName)){
					msg = fileName+" 파일을 찾을 수 없습니다.";
				}else{
					/*Client로 파일 전송.(다운로드)*/
					try{
						//gov.nanet.watermarc.util.FileUtil.fileWriteDown(fileName, fileName, resp);
						System.out.println("업로드 성공");
						ftp.storeFile(req, filePath + fileName, webUpDir+fileName);
						System.out.println("업로드 성공");
					}catch(Exception e){
						return fileName+" 다운로드 오류";
					}
				}
			}else{
				msg = "서버에 접속할 수 없습니다.";
			}
		}catch(Exception e){
			return fileName+" 다운로드 오류";
		}finally
		{
			 if (ftp != null) ftp.closeConnection();
		}
		return msg;
	}
}
