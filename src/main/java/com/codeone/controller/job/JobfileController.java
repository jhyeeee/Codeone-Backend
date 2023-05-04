package com.codeone.controller.job;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;

// 채용 메인페이지 썸네일 이미지 업로드 컨트롤러

@RestController
public class JobfileController {

	
	// 파일 업로드
	// 이곳에서는 DB에 저장 안함. 리액트 upload폴더에 업로드만 함.
	@PostMapping("/uploadFile")
	public String uploadImageTest(MultipartFile multipartFiles,
			HttpServletRequest req) {
		//String UPLOAD_PATH = req.getSession().getServletContext().getRealPath("/upload");
		String UPLOAD_PATH = "C:\\finalfront\\codeonereact\\public\\jobfile";	//파일이 올라가는 경로
		
		System.out.println("UPLOAD_PATH : " + UPLOAD_PATH);
		//String UPLOAD_PATH = "C:\\Upload"; // 업로드 할 위치
		try {
			System.out.println("fileController uploadImageTest " + new Date());
//			System.out.println("1>> " + multipartFiles[0]);
			MultipartFile file = multipartFiles;
            
			String fileId = "file_" + new SimpleDateFormat("MMddHHmmss").format(new Date()) + "_" + new Random().nextInt(10000);

//			String fileId = (new Date().getTime()) + "" + (new Random().ints(1000, 9999).findAny().getAsInt()); // 현재 날짜와 랜덤 정수값으로 새로운 파일명 만들기
			String originName = file.getOriginalFilename(); // ex) 파일.jpg
			String fileExtension = originName.substring(originName.lastIndexOf(".") + 1); // ex) jpg
			originName = originName.substring(0, originName.lastIndexOf(".")); // ex) 파일
			System.out.println("fileId : " + fileId);
			System.out.println("originName : " + originName);
			System.out.println("fileExtension : " + fileExtension);

			
			String path= UPLOAD_PATH+"\\"+fileId + "." + fileExtension;
			File file2 = new File(path);
			System.out.println("path : " + path);
			
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file2));
			bos.write(file.getBytes());			
			bos.close();
				
		
			//uplwoadimage 경로변경
			//return new ResponseEntity<Object>("http://localhost:80/getImage/" + fileId + "/" + fileExtension, HttpStatus.OK);
			
			return "jobfile"+"/"+fileId + "." + fileExtension;
		} catch(IOException e) {
			//return new ResponseEntity<Object>(null, HttpStatus.CONFLICT);
			return "FAIL";
		}
	}
	
	//파일다운로드
	@GetMapping("/downloadFile")
	public ResponseEntity<Resource> downloadFile(@RequestParam String fileName) {
	    // 파일 다운로드를 위한 경로 설정
	    String filePath = "C:\\finalfront\\codeonereact\\public\\jobfile";
	    Path path = Paths.get(filePath, fileName);
	    Resource resource = null;

	    try {
	        resource = new UrlResource(path.toUri());
	    } catch (MalformedURLException e) {
	        e.printStackTrace();
	    }

	    HttpHeaders headers = new HttpHeaders();
	    headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"");

	    return ResponseEntity.ok().headers(headers).body(resource);
	}

	
}
