package com.codeone.controller.blog;

import java.nio.charset.Charset;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codeone.dto.blog.BlogDto;
import com.codeone.service.blog.BlogService;

@Controller
@RequestMapping("/blog")
public class BlogController {

	@Autowired
	private BlogService service;
	
	@PostMapping("/write")
	public ResponseEntity<Void> blogWrite(BlogDto dto) {
		System.out.println(dto.toString());
		System.out.println("blogWrite");
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
	
}
