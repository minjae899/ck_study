package spring.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 * @파일명 FileUtil.java
 */
public class FileUtil {
		
	public static void fileWrite(String filepath,String filename,HttpServletResponse resp) throws Exception{
		
		/*파일 다운*/
	    File downFile = new File(filepath); 
		
	    String fileDownName = URLEncoder.encode(filename,"UTF-8");
		resp.setHeader( "Content-Encoding", "gzip" );
	    resp.setHeader( "Content-disposition", "attachment;filename="+fileDownName);
	    
		ServletOutputStream os = resp.getOutputStream();
		GZIPOutputStream zipOut= new GZIPOutputStream(os);
		BufferedInputStream bin = null;
		
		try{ 		
			bin = new BufferedInputStream(new FileInputStream(downFile));
			byte[] buf=new byte[1024];
			int readByte=0;
			while((readByte=bin.read(buf,0,buf.length))!=-1) {
				zipOut.write(buf,0,readByte);
			}
			
		}catch(IOException e){
			//if(!e.getClass().getName().equals("org.apache.catalina.connector.ClientAbortException"))
				//e.printStackTrace();
			throw new IOException();
		}catch(Exception e){
			//if(!e.getClass().getName().equals("org.apache.catalina.connector.ClientAbortException"))
				//e.printStackTrace();
			throw new Exception();	
		}finally{
			zipOut.flush();
			zipOut.close();
			bin.close();
			os.close();
			
			/*if(downFile.exists()){
				downFile.delete();
			}*/
		}
	}

	public static String fileRead(String filePath) throws Exception {
		String html = "";
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));

			int ch;
			while ((ch = reader.read()) != -1) {
				html += (char)ch;
			}
		}
		catch (Exception e) {
			throw new Exception();
		}
		finally {
			try {
				reader.close();
			}
			catch (Exception e) {}
		}

		return html;
	}

	/**
	 * 파일 다운로드
	 * @param resp
	 * @return 
	 * @throws Exception
	 */ 
	public static String fileDown(HttpServletResponse resp , String webDownDir ,String fileName){
		
		String msg = "";
					/*Client로 파일 전송.(다운로드)*/
		try{
			FileUtil.fileWrite(webDownDir + fileName, fileName, resp);
		}catch(Exception e){
			return fileName+" 다운로드 오류";
		}
		return msg;
	}
	
	/**
	 * 파일 다운로드
	 * @param resp
	 * @return 
	 * @throws Exception
	 */ 
	public static String fileDown1(HttpServletResponse resp , String webDownDir ,String filePath, String fileName){
		
		String msg = "";
					/*Client로 파일 전송.(다운로드)*/
		try{
			FileUtil.fileWrite(webDownDir + filePath, fileName, resp);
		}catch(Exception e){
			return fileName+" 다운로드 오류";
		}
		return msg;
	}
}
