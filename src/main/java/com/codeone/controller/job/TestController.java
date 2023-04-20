package com.codeone.controller.job;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codeone.service.job.TestService;

@RestController
public class TestController {
	//웹 : 화면과 back 컨트롤 -> controller

	@Autowired
	private TestService service;
	//localhost:/test?group_name=직무 
	
	@RequestMapping(value = "/test", method = {RequestMethod.GET})
	// 접근제어자 / 반환 변수형 / 함수명 (인자값) 
	public ResponseEntity<Map<String, Object>> test(@RequestParam Map<String, Object> params) throws Exception{
		Map<String, Object> res = new HashMap<String, Object>();
		System.out.println("params === > " + params);
		System.out.println("params === > " + params.get("group_name"));
		res.put("test", service.insert(params));
		
		return new ResponseEntity<Map<String,Object>>(res, HttpStatus.OK);
	} 
	
	 @GetMapping("/test2")
	 public ResponseEntity<Map<String, Object>> test2(@RequestParam Map<String, Object> params2) throws Exception{
		Map<String, Object> res = new HashMap<String, Object>();
		System.out.println("params === > " + params2);
		System.out.println("params === > " + params2.get("CODE_NAME"));
		res.put("test2", service.insert2(params2));
			
		return new ResponseEntity<Map<String,Object>>(res, HttpStatus.OK);
		
	 }
	 
	 @GetMapping("/testList")
	 public ResponseEntity<Map<String, Object>> testList(@RequestParam Map<String, Object> params) throws Exception{ 
		 Map<String, Object> res = new HashMap<String, Object>();
		 System.out.println("params === > " + params);
		 System.out.println("params === > " + params.get("CODE_NAME"));
		 
		 res.put("testList", service.testList(params));
		 return new ResponseEntity<Map<String,Object>>(res, HttpStatus.OK);
	 
	 }
}
	
	
	
	




