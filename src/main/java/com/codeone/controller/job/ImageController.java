package com.codeone.controller.job;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.codeone.dto.job.JobDto;

//리액트 퀼 안의 content 이미지 업로드 컨트롤러

//@CrossOrigin("*")
@RestController
@RequestMapping(value="/job")
public class ImageController {

	String UPLOAD_PATH = "C:\\image\\imagefile";// 업로드 할 위치
	
	// 이미지 불러오기
	@GetMapping("/getImage/{fileId}/{fileType}")
	public ResponseEntity<byte[]> getImageFile(@PathVariable String fileId, @PathVariable String fileType) {
		System.out.println(" GetImageFile???");
		try {
			FileInputStream fis = new FileInputStream(UPLOAD_PATH + "\\" + fileId + "." + fileType);
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
	
	// 이미지 업로드 -> 이미지를 리액트퀼에 삽입하는순간 C:\\image\\imagefile 폴더 안에 업로도됨. 
	// 발표자 드라이브에 C:\\image\\imagefile 폴더가 있어야함.
	@PostMapping("/uploadImage")
	public ResponseEntity<Object> uploadImage(MultipartFile multipartFiles) {
		System.out.println("uploadImage");
		System.out.println(multipartFiles);
		try {
			MultipartFile file = multipartFiles;
            
			String fileId = (new Date().getTime()) + "" + (new Random().ints(1000, 9999).findAny().getAsInt()); // 현재 날짜와 랜덤 정수값으로 새로운 파일명 만들기
			String originName = file.getOriginalFilename(); // ex) 파일.jpg
			String fileExtension = originName.substring(originName.lastIndexOf(".") + 1); // ex) jpg
			originName = originName.substring(0, originName.lastIndexOf(".")); // ex) 파일
			long fileSize = file.getSize(); // 파일 사이즈
			
			File fileSave = new File(UPLOAD_PATH, fileId + "." + fileExtension); // ex) fileId.jpg
			if(!fileSave.exists()) { // 폴더가 없을 경우 폴더 만들기
				fileSave.mkdirs();
			}
            
			file.transferTo(fileSave); // fileSave의 형태로 파일 저장
			
			System.out.println("fileId= " + fileId);
			System.out.println("originName= " + originName);
			System.out.println("fileExtension= " + fileExtension);
			System.out.println("fileSize= " + fileSize);
			
			return new ResponseEntity<Object>("http://localhost/job/getImage/" + fileId + "/" + fileExtension, HttpStatus.OK);
		} catch(IOException e) {
			return new ResponseEntity<Object>(null, HttpStatus.CONFLICT);
		}
	}
}
