package com.codeone.controller.job;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
// 채용 메인페이지 썸네일 이미지 업로드 컨트롤러

import jakarta.servlet.http.HttpServletRequest;

//@CrossOrigin("*")
@RestController
public class thumbController {
//	private String UPLOAD_PATH = "src/main/webapp/upload"; // 업로드 할 위치
	//String UPLOAD_PATH = "C:\\Upload"; // 업로드 할 위치
	//String UPLOAD_PATH = "./Upload"; 오류남
	//FileInputStream fis = new FileInputStream(UPLOAD_PATH + "\\" + fileId + "." + fileType);
	
//	private Logger log = LoggerFactory.getLogger(thumbController.class);
	
	// 이미지 불러오기 
//	@GetMapping("/getImage/{imagename}")
//	public ResponseEntity<byte[]> getImageFile(@PathVariable String imagename
//			) {
//		String UPLOAD_PATH = "C:\\finalfront\\codeonereact\\public";
//		log.info("getImagefile call.{}", imagename);
//		
//		try {
//			FileInputStream fis = new FileInputStream(UPLOAD_PATH + "\\" + imagename);
//			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			
//			byte buffer[] = new byte[1024];
//			int length = 0;
//			
//			while((length = fis.read(buffer)) != -1) {
//				baos.write(buffer, 0, length);
//			}
//			
//			HttpHeaders headers = new HttpHeaders();
//			headers.setContentType(MediaType.IMAGE_JPEG);	// Http:요청하면 응답. 크롬->스프링에 이미지 요청, 이미지파일로 읽도록 MediaType.IMAGE_JPEG라고 정해놓음.(jpeg=jpg)
//			
//			return new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.OK);
//			
//		} catch(IOException e) {
//			log.info("error", e);
//					
//			return new ResponseEntity<byte[]>(new byte[] {}, HttpStatus.CONFLICT);
//		}
//	}
	
  
	// 이미지 파일 업로드
	// 이곳에서는 DB에 저장 안함. 리액트 upload폴더에 업로드만 함.
	@PostMapping("/uploadImageTest")
	public String uploadImageTest(MultipartFile multipartFiles,
			HttpServletRequest req) {
		System.out.println("uploadImageTest 이미지 업로드 시작~~");
		String UPLOAD_PATH = req.getSession().getServletContext().getRealPath("/jobImage");
//		String UPLOAD_PATH = ".codeonereact\\public\\upload";
//		String UPLOAD_PATH = "C:\\finalfront\\codeonereact\\public\\upload";	//파일이 올라가는 경로
		//상대경로로 변경하기 
		
		System.out.println("UPLOAD_PATH : " + UPLOAD_PATH);
		//String UPLOAD_PATH = "C:\\Upload"; // 업로드 할 위치
		try {
			System.out.println("fileController uploadImageTest " + new Date());
//			System.out.println("1>> " + multipartFiles[0]);
			MultipartFile file = multipartFiles;
            
			String fileId = (new Date().getTime()) + "" + (new Random().ints(1000, 9999).findAny().getAsInt()); // 현재 날짜와 랜덤 정수값으로 새로운 파일명 만들기
			String originName = file.getOriginalFilename(); // ex) 파일.jpg
			String fileExtension = originName.substring(originName.lastIndexOf(".") + 1); // ex) jpg
			originName = originName.substring(0, originName.lastIndexOf(".")); // ex) 파일
			System.out.println("fileId : " + fileId);
			System.out.println("originName : " + originName);
			System.out.println("fileExtension : " + fileExtension);

			
			String path= UPLOAD_PATH+"\\"+fileId + "." + fileExtension;
			System.out.println("uploadImageTest 이미지 path : " + path);
			File file2 = new File(path);
			System.out.println("path : " + path);
			
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file2));
			bos.write(file.getBytes());			
			bos.close();
				
		
			//uploadimage 경로변경
			//return new ResponseEntity<Object>("http://localhost:80/getImage/" + fileId + "/" + fileExtension, HttpStatus.OK);
			System.out.println("uploadImageTest 이미지 업로드 끝~~");
			
			return path;
		} catch(IOException e) {
			//return new ResponseEntity<Object>(null, HttpStatus.CONFLICT);
			System.out.println("uploadImageTest 이미지 업로드 error");
			return "FAIL";
		}
	}
	
}


	
//private String UPLOAD_PATH = "src/main/webapp/upload"; // 업로드 할 위치
////String UPLOAD_PATH = "C:\\Upload"; // 업로드 할 위치
////String UPLOAD_PATH = "./Upload"; 오류남
////FileInputStream fis = new FileInputStream(UPLOAD_PATH + "\\" + fileId + "." + fileType);
//
//private Logger log = LoggerFactory.getLogger(thumbController.class);
//
// 이미지 불러오기
