package com.codeone.controller.job;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class fileController {
	private String UPLOAD_PATH = "src/main/webapp/upload"; // 업로드 할 위치
	//String UPLOAD_PATH = "C:\\Upload"; // 업로드 할 위치
	//String UPLOAD_PATH = "./Upload"; 오류남
	//FileInputStream fis = new FileInputStream(UPLOAD_PATH + "\\" + fileId + "." + fileType);
	
	private Logger log = LoggerFactory.getLogger(fileController.class);
	
	// 이미지 불러오기
	@GetMapping("/getImage/{imagename}/{fileType}")
	public ResponseEntity<byte[]> getImageFile(@PathVariable String imagename, @PathVariable String fileType
			) {
		log.info("getImagefile call. {}.{}", imagename, fileType);
		
		try {
			FileInputStream fis = new FileInputStream(UPLOAD_PATH + "\\" + imagename + "." + fileType);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			byte buffer[] = new byte[1024];
			int length = 0;
			
			while((length = fis.read(buffer)) != -1) {
				baos.write(buffer, 0, length);
			}
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_JPEG);	// Http:요청하면 응답. 크롬->스프링에 이미지 요청, 이미지파일로 읽도록 MediaType.IMAGE_JPEG라고 정해놓음.(jpeg=jpg)
			
			return new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.OK);
			
		} catch(IOException e) {
			log.info("error", e);
					
			return new ResponseEntity<byte[]>(new byte[] {}, HttpStatus.CONFLICT);
		}
	}
	
	// 이미지 업로드
	@PostMapping("/uploadImage")
	public ResponseEntity<Object> uploadImage(MultipartFile multipartFiles[],
			HttpServletRequest req) {
		String UPLOAD_PATH = req.getSession().getServletContext().getRealPath("/upload");
		System.out.println("UPLOAD_PATH : " + UPLOAD_PATH);
		//String UPLOAD_PATH = "C:\\Upload"; // 업로드 할 위치
		try {
			System.out.println("fileController uploadImage " + new Date());
//			System.out.println("1>> " + multipartFiles[0]);
			MultipartFile file = multipartFiles[0];
            
			String fileId = (new Date().getTime()) + "" + (new Random().ints(1000, 9999).findAny().getAsInt()); // 현재 날짜와 랜덤 정수값으로 새로운 파일명 만들기
			String originName = file.getOriginalFilename(); // ex) 파일.jpg
			String fileExtension = originName.substring(originName.lastIndexOf(".") + 1); // ex) jpg
			originName = originName.substring(0, originName.lastIndexOf(".")); // ex) 파일
			System.out.println("fileId : " + fileId);
			System.out.println("originName : " + originName);
			System.out.println("fileExtension : " + fileExtension);

//			long fileSize = file.getSize(); // 파일 사이즈
//			System.out.println("fileSize :  " + fileSize);
			
//			File fileSave = new File(UPLOAD_PATH, fileId + "." + fileExtension); // ex) fileId.jpg
//			if(!fileSave.exists()) { // 폴더가 없을 경우 폴더 만들기
//				fileSave.mkdirs();
//			}
//            
//			file.transferTo(fileSave); // fileSave의 형태로 파일 저장
			
			String path= UPLOAD_PATH+"\\"+fileId + "." + fileExtension;
			File file2 = new File(path);
			System.out.println("path : " + path);
			
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file2));
			bos.write(file.getBytes());			
			bos.close();
				
//			System.out.println("fileId= " + fileId);
//			System.out.println("originName= " + originName);
//			System.out.println("fileExtension= " + fileExtension);
//			System.out.println("fileSize= " + fileSize);
			
			//uploadimage 경로변경
			return new ResponseEntity<Object>("http://localhost:80/getImage/" + fileId + "/" + fileExtension, HttpStatus.OK);
		} catch(IOException e) {
			return new ResponseEntity<Object>(null, HttpStatus.CONFLICT);
		}
	}
}