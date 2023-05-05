package com.codeone.controller.blog;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.codeone.dto.blog.BlogDto;
import com.codeone.service.blog.BlogService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/blog")
public class BlogController {
	final String defaultImage = "http://localhost/user/blogThumbnailImages/imageDefault/png";
	@Autowired
	private BlogService service;
	
	@PostMapping("/write")
	public ResponseEntity<Void> blogWrite(String writer, String title, String content, MultipartFile multipartFiles, HttpServletRequest req) throws Exception {
		System.out.println("blogWrite");
		BlogDto dto = new BlogDto();
		dto.setWriter(writer);
		dto.setTitle(title);
		dto.setContent(content);		
		System.out.println(dto.toString());
		String thumbnail = defaultImage;
		System.out.println(multipartFiles);
		boolean noPicture = Objects.isNull(multipartFiles);
		
    	// 썸네일을 지정한 경우
		if(!noPicture) {
			thumbnail = saveThumbnailPicture(req, multipartFiles);
    	} 
		System.out.println(thumbnail + " this is thumbnail");
		dto.setThumbnail(thumbnail);
		
		
		if(service.blogWrite(dto)) {
			return ResponseEntity.ok().build();			
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	
	@GetMapping("/getAllBlogs")
	public ResponseEntity<List<BlogDto>> getAllBlogs () {
		System.out.println("getAllBlogs");

		List<BlogDto> result = service.getAllBlogs();
		
		HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		System.out.println(result);
     	return ResponseEntity.ok()
 				.headers(header)
 				.body(result);
	}
	
	@GetMapping("/getBlog")
	public ResponseEntity<BlogDto> getBlog (int seq) {
		System.out.println("getBlog");
		System.out.println(seq);
		BlogDto result = service.getBlog(seq);
		
		HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		System.out.println(result);
     	return ResponseEntity.ok()
 				.headers(header)
 				.body(result);
	}
	
	@GetMapping("/deleteBlog")
	public ResponseEntity<Void> deleteBlog(int seq) {
		System.out.println("deleteBlog");
		if(service.deleteBlog(seq)) {
			return ResponseEntity.ok().build();			
		}else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@PostMapping("/updateBlog")
	public ResponseEntity<Void> updateBlog(BlogDto dto) {
		System.out.println("updateBlog");
		if(service.updateBlog(dto)) {
			return ResponseEntity.ok().build();			
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	// 이미지 불러오기
	@GetMapping("/getBlogThumbnailImages/{fileId}/{fileType}")
	public ResponseEntity<byte[]> getImageFile(HttpServletRequest req,@PathVariable String fileId, @PathVariable String fileType) {
		System.out.println(" GetBlogThumbnailFile???");
		String path = req.getServletContext().getRealPath("/images/blogThumbnailImages");
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
	
	
	
	private String saveThumbnailPicture(HttpServletRequest req, MultipartFile multipartFiles) throws Exception{
		// 이미지 저장
		String path = req.getServletContext().getRealPath("/images/blogThumbnailImages");
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
		String filename = "http://localhost/blog/getBlogThumbnailImages/"+ fileId + "/" + fileExtension;
		
		return filename;
	}
	
}
