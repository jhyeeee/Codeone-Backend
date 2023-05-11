package com.codeone.controller.blog;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/blog")
public class BlogImageController {

	@PostMapping("/uploadImage")
	public ResponseEntity<Object> uploadImage(HttpServletRequest req,MultipartFile multipartFiles) {
		System.out.println("uploadImage");
		System.out.println(multipartFiles);
		String path = "";
		try {
			path = saveBlogImages(req, multipartFiles);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Object>(null, HttpStatus.CONFLICT);
		}			
		return new ResponseEntity<Object>(path, HttpStatus.OK);
	}
	
	
	// 이미지 불러오기
		@GetMapping("/getBlogImages/{fileId}/{fileType}")
		public ResponseEntity<byte[]> getImageFile(HttpServletRequest req,@PathVariable String fileId, @PathVariable String fileType) {
			System.out.println(" GetImageFile???");
			String path = req.getServletContext().getRealPath("/images/blogImages");
			try {
				FileInputStream fis = new FileInputStream(path + "\\" + fileId + "." + fileType);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				
				byte buffer[] = new byte[1024];
				int length = 0;
				
				while((length = fis.read(buffer)) != -1) {
					baos.write(buffer, 0, length);
				}
				
				return new ResponseEntity<byte[]>(baos.toByteArray(), HttpStatus.OK);
				
			} catch(IOException e) {
				return new ResponseEntity<byte[]>(new byte[] {}, HttpStatus.CONFLICT);
			}
		}
	
	
	
	private String saveBlogImages(HttpServletRequest req, MultipartFile multipartFiles) throws Exception{
		// 이미지 저장
		String path = req.getServletContext().getRealPath("/images/blogImages");
		MultipartFile file = multipartFiles;
		
		String fileId = (new Date().getTime()) + "" + (new Random().ints(1000, 9999).findAny().getAsInt()); // 현재 날짜와 랜덤 정수값으로 새로운 파일명 만들기
		String originName = file.getOriginalFilename(); // ex) 파일.jpg
		String fileExtension = originName.substring(originName.lastIndexOf(".") + 1); // ex) jpg
		originName = originName.substring(0, originName.lastIndexOf(".")); // ex) 파일
		
		File fileSave = new File(path, fileId + "." + fileExtension); // ex) fileId.jpg
//		File fileSave = new File("http://localhost/user/getProfileImage/"+ fileId + "/" + fileExtension); // ex) fileId.jpg
		if(!fileSave.exists()) { // 폴더가 없을 경우 폴더 만들기
			fileSave.mkdirs();
		}        
		file.transferTo(fileSave);
		String filename = "http://localhost/blog/getBlogImages/"+ fileId + "/" + fileExtension;
		
		return filename;
	}
	
	
	
	
}
