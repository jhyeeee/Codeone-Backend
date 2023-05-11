package com.codeone.controller.blog;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
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
import com.codeone.dto.blog.BlogLikeDto;
import com.codeone.dto.user.UserDto;
import com.codeone.service.blog.BlogLikeService;
import com.codeone.service.blog.BlogService;
import com.codeone.service.user.UserService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/bloglike")
public class BlogLikeController {
	
	@Autowired
	private BlogLikeService service;
	
	@PostMapping("/dolike")
	public ResponseEntity<Void> doLike(BlogLikeDto dto) {
		System.out.println(dto.toString());
		
		if(service.blogDoLike(dto)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.noContent().build();			
		}
	}
	
	@PostMapping("/donotlike")
	public ResponseEntity<Void> doNotLike(BlogLikeDto dto) {
		System.out.println(dto.toString() );
		
		if(service.blogDoNotLike(dto)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.noContent().build();			
		}
	}
	
	@GetMapping("/getWhoLikeThisBlog")
	public ResponseEntity<Void> getWhoLikeThisBlog (BlogLikeDto dto) {
		System.out.println(dto.toString());
		
		if(service.getWhoLikeThisBlog(dto)) {
			return ResponseEntity.ok().build();
		} else {
			return  ResponseEntity.noContent().build();			
		}
			
		
	}
	
	
	
	
}
