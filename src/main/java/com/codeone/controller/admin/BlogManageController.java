package com.codeone.controller.admin;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codeone.dto.blog.BlogDto;
import com.codeone.dto.calendar.CalendarDto;
import com.codeone.service.admin.BlogManageService;

@RestController
@RequestMapping(value="/blogmanage")
public class BlogManageController {
	
	@Autowired
	BlogManageService service;
	
	@GetMapping("/getAllBlogsMng")
	public ResponseEntity<Map<String, Object>> getAllBlogsMng () {
		System.out.println("getAllBlogsMng");
	
		Map<String, Object> map = new HashMap<>();        
	    try {
	    	List<BlogDto> result = service.getAllBlogsMng();
	        map.put("list", result);
	        return ResponseEntity.ok(map);                  // 성공
	        
	    } catch (Exception e) {
	        return ResponseEntity.badRequest().build();      // 정보 가져오기 실패
	    }
	}
	
	@PostMapping("/showHideBlog")
	public ResponseEntity<Void> showHideBlog(@RequestParam int seq, @RequestParam int delf) {
	    System.out.println("showHideBlog");
	    if (service.showHideBlog(seq, delf)) {
	        return ResponseEntity.ok().build();
	    } else {
	        return ResponseEntity.noContent().build();
	    }
	}
}