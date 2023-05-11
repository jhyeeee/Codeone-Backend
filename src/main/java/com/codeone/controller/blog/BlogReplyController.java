package com.codeone.controller.blog;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeone.dto.blog.BlogReplyDto;
import com.codeone.service.blog.BlogReplyService;

@RestController
@RequestMapping("/blogReply")
public class BlogReplyController {
	
	@Autowired
	BlogReplyService service;
	
	
	@GetMapping("/getAllBlogReply")
	public  ResponseEntity<List<BlogReplyDto>> getAllBlogReply(int blogseq) {
		System.out.println("getAllBlogReply");
		List<BlogReplyDto> blogReplyList  = service.getAllBlogReply(blogseq);
		HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        
        System.out.println(blogReplyList.size());
        
        if(blogReplyList.size() < 1) {
        	System.out.println("hello?");
        	return ResponseEntity.noContent().build();
        }
        
		return ResponseEntity.ok()
				.headers(header)
 				.body(blogReplyList);
	}
	
	
	@PostMapping("/write")
	public ResponseEntity<List<BlogReplyDto>> blogReplyWrite(int blogseq, String writer, String blogReply) {
		System.out.println("blogReply write");
		
		BlogReplyDto dto = new BlogReplyDto();
		dto.setBlogseq(blogseq);
		dto.setWriter(writer);
		dto.setContent(blogReply);
		
		if(service.blogReplyWrite(dto)) {
			HttpHeaders header = new HttpHeaders();
	        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));			
			return getAllBlogReply(blogseq);
		} else {
			return  ResponseEntity.noContent().build();
		}
		
	}
}
