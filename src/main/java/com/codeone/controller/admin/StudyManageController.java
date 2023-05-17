package com.codeone.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeone.dto.blog.BlogDto;
import com.codeone.dto.studygroup.StudygroupInfoDto;
import com.codeone.service.admin.StudyManageService;


@RestController
@RequestMapping("/studymanage")
public class StudyManageController {
	@Autowired
	StudyManageService service;
	
	@GetMapping("/allStudyManage")
	public ResponseEntity<Map<String, Object>> allStudyMange () {
		System.out.println("allStudyMange");
	
		Map<String, Object> map = new HashMap<>();        
	    try {
	    	List<StudygroupInfoDto> result = service.allStudyManage();
	        map.put("list", result);
	        return ResponseEntity.ok(map);                  // 성공
	        
	    } catch (Exception e) {
	        return ResponseEntity.badRequest().build();      // 정보 가져오기 실패
	    }
	}

}
