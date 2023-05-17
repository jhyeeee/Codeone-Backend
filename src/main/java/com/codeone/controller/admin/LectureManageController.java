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

import com.codeone.dto.lecture.LectureDto;
import com.codeone.service.admin.LectureManageService;

@RestController
@RequestMapping(value="/lecturemanage")
public class LectureManageController {
	
	@Autowired
	LectureManageService service;
	
	@GetMapping("/getMngLectureList")
	public ResponseEntity<Map<String, Object>> getMngLectureList () {
		System.out.println("getMngLectureList");
	
		Map<String, Object> map = new HashMap<>();        
	    try {
	    	List<LectureDto> result = service.getMngLectureList();
	        map.put("list", result);
	        return ResponseEntity.ok(map);                  // 성공
	        
	    } catch (Exception e) {
	        return ResponseEntity.badRequest().build();      // 정보 가져오기 실패
	    }
	}
	
	@PostMapping("/showHideLecture")
	public ResponseEntity<Void> showHideLecture(@RequestParam int seq, @RequestParam int delflg) {
	    System.out.println("showHideLecture");
	    if (service.showHideLecture(seq, delflg)) {
	        return ResponseEntity.ok().build();
	    } else {
	        return ResponseEntity.noContent().build();
	    }
	}
}