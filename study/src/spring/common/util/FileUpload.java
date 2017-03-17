package spring.common.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/** 
 * @identity FileUpload.java
 * @author 김준호
 * @since 2012. 07.12
 * 
 * Copyright (C) 2013 by Libeka Co. Ltd. All right reserved.
 */

public class FileUpload 
{
	public FileUpload(){
		
	}
	
	//파일 업로드
	public static Map<String,Object> fileUpload(MultipartHttpServletRequest mutireq , String web_dir_temp , String attach_name ,String defaultDir) throws Exception{
	
		MultipartFile file = mutireq.getFile(attach_name);
		
		String file_path = "";
		String file_nm = "";
		
		if( "photo_file".equals(attach_name) ){
			file_path = mutireq.getParameter("pict_path") != null && !"null".equals(mutireq.getParameter("pict_path")) ? mutireq.getParameter("pict_path") : "";
			file_nm = mutireq.getParameter("pict_nm") != null && !"null".equals(mutireq.getParameter("pict_nm")) ? mutireq.getParameter("pict_nm") : "";
		}else if( "excel_file1".equals(attach_name) || "excel_file2".equals(attach_name) || "excel_file3".equals(attach_name) || "excel_file4".equals(attach_name)){
			file_path = mutireq.getParameter("file_path") != null && !"null".equals(mutireq.getParameter("file_path")) ? mutireq.getParameter("file_path") : "";
			file_nm = mutireq.getParameter("file_name") != null && !"null".equals(mutireq.getParameter("file_name")) ? mutireq.getParameter("file_name") : "";
		}else if( "per_video_file".equals(attach_name) ){
			file_path = mutireq.getParameter("per_video_ph") != null && !"null".equals(mutireq.getParameter("per_video_ph")) ? mutireq.getParameter("per_video_ph") : "";
			file_nm = mutireq.getParameter("per_video_nm") != null && !"null".equals(mutireq.getParameter("per_video_nm")) ? mutireq.getParameter("per_video_nm") : "";
		}else if( "book_list_file".equals(attach_name) ){
			file_path = mutireq.getParameter("book_list_ph") != null && !"null".equals(mutireq.getParameter("book_list_ph")) ? mutireq.getParameter("book_list_ph") : "";
			file_nm = mutireq.getParameter("book_list_nm") != null && !"null".equals(mutireq.getParameter("book_list_nm")) ? mutireq.getParameter("book_list_nm") : "";
		}
		
		
		System.out.println("file_path::::"+file_path);
		System.out.println("file_nm::::"+file_nm);
		
		String fp = file_path;
		
		Map<String,Object> local = new HashMap<String,Object>();
		
		try{
			
			if(!"".equals(file.getOriginalFilename())){
				//String local_filePath = web_dir_temp;
				
				if( "photo_file".equals(attach_name) ){
					
					String local_filePath = "";
					
					if(!"".equals(file_nm)){
						//기존 파일이 있을경우
						local_filePath = web_dir_temp + file_path;
					}else{
						local_filePath = createDirectory(web_dir_temp+defaultDir);
					}
					
					String local_fileName = file.getOriginalFilename();
					String local_fileType = file.getContentType();
					local_fileType = getExtension(local_fileName); //파일명 변경해서 저장
					//local_fileName = StringUtil.createFileName()+"."+local_fileType;
					
					String save_fileName = "";
					if(!"".equals(file_nm)){
						//기존 파일이 있을경우 - 삭제
						save_fileName = file_nm;
						String save_file = local_filePath+file_nm;
						File d_file = new File(save_file);
						if(d_file.isFile()){
							d_file.delete();
						}
					}
					save_fileName = createFileName()+"."+local_fileType;
					
					local.put("local_filePath", local_filePath.replace(web_dir_temp, ""));
					local.put("local_fileName", save_fileName);
					local.put("local_fileType", local_fileType);
					local.put("fileGubun", attach_name);
					
					//file.transferTo(new File(local_filePath + local_fileName));
					//기존 디렉토리에 저장
					file.transferTo(new File(local_filePath + save_fileName)); //저장
					
				// 엑셀반입	
				}else if( "excel_file1".equals(attach_name) || "excel_file2".equals(attach_name) || "excel_file3".equals(attach_name)  || "excel_file4".equals(attach_name)){
					String local_filePath = "";
					if(!"".equals(file_nm)){
						//기존 파일이 있을경우
						local_filePath = web_dir_temp + file_path;
					}else{
						local_filePath = createDirectory(web_dir_temp+defaultDir);
					}
					
					String local_fileName = file.getOriginalFilename();
					String local_fileType = file.getContentType();
					local_fileType = getExtension(local_fileName); //파일명 변경해서 저장
					//local_fileName = StringUtil.createFileName()+"."+local_fileType;
					
//					String save_fileName = "";
					if(!"".equals(file_nm)){
						//기존 파일이 있을경우 - 삭제
//						save_fileName = file_nm;
						String save_file = local_filePath+local_fileName;
						File d_file = new File(save_file);
						if(d_file.isFile()){
							d_file.delete();
						}
					}
//					save_fileName = createFileName()+"."+local_fileType;
					
					local.put("local_filePath", local_filePath);
					local.put("local_fileName", local_fileName);
					local.put("local_fileType", local_fileType);
					local.put("filePath", fp);
					
					//file.transferTo(new File(local_filePath + local_fileName));
					//기존 디렉토리에 저장
					file.transferTo(new File(local_filePath + local_fileName)); //저장
				}else if( "per_video_file".equals(attach_name) ){
					
					String local_filePath = "";
					if(!"".equals(file_nm)){
						//기존 파일이 있을경우
						local_filePath = web_dir_temp +file_path;
					}else{
						local_filePath = createDirectory(web_dir_temp+defaultDir);
					}
					
					String local_fileName = file.getOriginalFilename();
					String local_fileType = file.getContentType();
					local_fileType = getExtension(local_fileName); //파일명 변경해서 저장
					//local_fileName = StringUtil.createFileName()+"."+local_fileType;
					
					String save_fileName = "";
					if(!"".equals(file_nm)){
						//기존 파일이 있을경우 - 삭제
						save_fileName = file_nm;
						String save_file = local_filePath+file_nm;
						File d_file = new File(save_file);
						if(d_file.isFile()){
							d_file.delete();
						}
					}
					save_fileName = createFileName()+"."+local_fileType;
					
					local.put("local_filePath", local_filePath.replace(web_dir_temp, ""));
					local.put("local_fileName", save_fileName);
					local.put("local_fileType", local_fileType);
					local.put("fileGubun", attach_name);
					
					//file.transferTo(new File(local_filePath + local_fileName));
					//기존 디렉토리에 저장
					file.transferTo(new File(local_filePath + save_fileName)); //저장
				}else if( "book_list_file".equals(attach_name) ){
					
					String local_filePath = "";
					if(!"".equals(file_nm)){
						//기존 파일이 있을경우
						local_filePath = web_dir_temp +file_path;
					}else{
						local_filePath = createDirectory(web_dir_temp+defaultDir);
					}
					
					String local_fileName = file.getOriginalFilename();
					String local_fileType = file.getContentType();
					local_fileType = getExtension(local_fileName); //파일명 변경해서 저장
					//local_fileName = StringUtil.createFileName()+"."+local_fileType;
					
					String save_fileName = "";
					if(!"".equals(file_nm)){
						//기존 파일이 있을경우 - 삭제
						save_fileName = file_nm;
						String save_file = local_filePath+file_nm;
						File d_file = new File(save_file);
						if(d_file.isFile()){
							d_file.delete();
						}
					}
					save_fileName = createFileName()+"."+local_fileType;
					
					local.put("local_filePath", local_filePath.replace(web_dir_temp, ""));
					local.put("local_fileName", save_fileName);
					local.put("local_fileType", local_fileType);
					local.put("fileGubun", attach_name);
					
					//file.transferTo(new File(local_filePath + local_fileName));
					//기존 디렉토리에 저장
					file.transferTo(new File(local_filePath + save_fileName)); //저장
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return local;
	}
	
	public static Map<String,Object> mailFileUpload(MultipartHttpServletRequest req , String web_dir_temp , String attach_name ) throws Exception{
		
		MultipartFile file = req.getFile(attach_name);
		String local_fileName = "";
		String local_fileType = "";
		
		Map<String,Object> local = new HashMap<String,Object>();
		
		if( !"".equals(file.getOriginalFilename()) ){
			try {
				local_fileName = file.getOriginalFilename();
				local_fileType = file.getContentType();
				//local_fileType = getExtension(local_fileName); //파일명 변경해서 저장
				//local_fileName = StringUtil.createFileName()+"."+local_fileType;
				
				//기존 디렉토리에 저장
				file.transferTo(new File(web_dir_temp + local_fileName)); //저장
				
				local.put("local_filePath", web_dir_temp + local_fileName);
				local.put("local_fileName", local_fileName);
				local.put("local_fileType", local_fileType);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return local;
	}	
	
	//파일삭제
	public static boolean fileDelete(Map<String,Object> params , String tempDir, String gubun)throws Exception{

		boolean flag = false;
		try{
			
			String path = "";
			String nm = "";
			
			if("photo".equals(gubun)){
				path = StringUtil.toString(params.get("pict_path"));
				nm = StringUtil.toString(params.get("pict_nm"));
			}else if( "per_video".equals(gubun) ){
				path = StringUtil.toString(params.get("per_video_ph"));
				nm = StringUtil.toString(params.get("per_video_nm"));
			}else if( "book_list".equals(gubun) ){
				path = StringUtil.toString(params.get("book_list_ph"));
				nm = StringUtil.toString(params.get("book_list_nm"));
			}
			
//			String save_fileName = "";
			if(!"".equals(nm)){
				//기존 파일이 있을경우 - 삭제
//				save_fileName = nm;
				String save_file = tempDir + path+nm;
				File d_file = new File(save_file);
				if(d_file.isFile()){
					d_file.delete();
					flag = true;
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
	//파일 확장자
	private static String getExtension(String str) throws Exception{

		String ext = "";
		int dotIdx = 0;
		int lastIdx = str.length();
		if (str.indexOf(".") != -1) {

			dotIdx = str.lastIndexOf(".") + 1;
			ext = str.substring(dotIdx, lastIdx);
		}
		return ext;
	}
	
	/*
	 * 설명 : 날짜별 디렉토리 생성 함수
	 * param : 상위 디렉토리 경로 
	 * return : 상위 디렉토리 + /yyyy/mm/dd/
	 * 작성자 : 김준호 
	 * 작성일 : 2010-06-20 
	 * 수정이력 : 
	 * 수정자: 
	 * DESC:
	 */
	public static String createDirectory(String dir) {

		String saveDir = "";

//		Date curDate = new Date();
		SimpleDateFormat formatter = null;
		Calendar cal = Calendar.getInstance();

		String today;
		String yyyy;
		String mm;
		String dd;

		String format = "yyyyMMdd";

		try {

			formatter = new SimpleDateFormat(format);
			cal.add(Calendar.DATE, 0);
			today = formatter.format(cal.getTime());

			yyyy = today.substring(0, 4);
			mm = today.substring(4, 6);
			dd = today.substring(6);
			
			if (mm.length() < 2) {
				mm = "0" + mm;
			}
			if (dd.length() < 2) {
				dd = "0" + dd;
			}
			File f = new File(dir  + yyyy + "/" + mm + "/" + dd + "/");
			//File f = new File(dir  + yyyy + "/");

			if (!f.mkdirs()) {
				System.err.println("Using the directory.....");
			}

			saveDir = dir + yyyy + "/" + mm + "/" + dd + "/";
			//saveDir = dir + yyyy + "/" ;

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return saveDir;
	}
	
	/*
	 * 설명 : 날짜 형식으로 파일 이름 생성
	 * param : 
	 * return : 파일명
	 * 작성자 : 김준호 
	 * 작성일 : 2010-06-20 
	 * 수정이력 : 
	 * 수정자: 
	 * DESC:
	 */
	public static String createFileName() {

		Date date = new Date();
		String nowTime = null;

		try {

			String nameFormat = "yyyyMMddHHmmssSSS";
			SimpleDateFormat sdf = new SimpleDateFormat(nameFormat);
			nowTime = sdf.format(date);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return nowTime;
	}
}
