package com.codeone.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codeone.dto.job.JobDto;
import com.codeone.service.admin.JobManageService;


@RestController
@RequestMapping(value="/jobmanage")
public class JobManageController {
	
	@Autowired
	JobManageService service;
	
	@GetMapping("/getAllJobsMng")
	public ResponseEntity<Map<String, Object>> getAllJobsMng () {
		System.out.println("getAllJobsMng가나다");
		Map<String, Object> map = new HashMap<>();   
		
	    try {
	    	List<JobDto> list = service.getAllJobsMng();
	        map.put("list", list);
	        return ResponseEntity.ok(map);                  // 성공
	        
	    } catch (Exception e) {
	        return ResponseEntity.badRequest().build();      // 정보 가져오기 실패
	    }
	}
	
	@PostMapping("/showHideJob")
	public ResponseEntity<Void> showHideJob(@RequestParam int seq, @RequestParam int comdel) {
	    System.out.println("showHideJob");
	    if (service.showHideJob(seq, comdel)) {
	        return ResponseEntity.ok().build();
	    } else {
	        return ResponseEntity.noContent().build();
	    }
	}
	
}