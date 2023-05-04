package com.codeone.controller.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codeone.dto.job.JobDto;
import com.codeone.service.job.JobService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import com.fasterxml.jackson.core.type.TypeReference;

//회원 전용 컨트롤러

@RequestMapping("job/")
@RestController
public class JobController {
	@Autowired
	private JobService service;
	
	//디버깅용도 LoggerFactory 추가
	private Logger log = LoggerFactory.getLogger(JobController.class);
 
	// 원래 버전	
		//채용 글목록
		@GetMapping("list")
		public Map<String, Object> list(@RequestParam Map<String, Object> params) throws Exception {
			Map<String, Object> res = new HashMap<String, Object>();
			
			System.out.println("params title == > " + params.get("title"));
//			params.forEach((key, value) -> {
//				log.info("{}: {}", key, value);
//			});
			
			res.put("list", service.joblist(params)); 
			return res;		
		}	  

	
	//채용 상세보기
	@GetMapping("view")
	public Map<String, Object> view(@RequestParam Map<String, Object> params) throws Exception{
		Map<String, Object> res = new HashMap<String, Object>();
		
		res.put("view", service.view(params));
		return res;
	}
	
	
	//채용 글목록 필터링
	@GetMapping("code/list")
	public Map<String, Object> code_list(@RequestParam Map<String, Object> params) throws Exception{
		log.info("code_list calld");
		params.forEach((key, value) -> log.info("{} : {}", key, value));
		
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("list", service.code_list(params));
	
//		Map codes = (Map) res.get("list");
//		codes.forEach((key, value) -> log.info("res {} : {}", key, value));
		return res;
	}
	

	// 좋아요한 사람의 id 추가
	@PostMapping(value = "whoLiked")
	public String whoLiked(JobDto job) {
		System.out.println("JobController updateJob " + new Date());
		System.out.println(job.toString());
		System.out.println("test1");
		System.out.println(job.getWhoLiked()); //디코딩
		System.out.println("test2");
  		boolean b = service.whoLiked(job);
  		if(b == false) {
  			return "NO";
  		}
  		return "YES";		
  	}
	
	
	//좋아요
//	@PostMapping("doLike")
//	public Map<String, Object> doLike(@RequestBody Map<String, Object> params) throws Exception{
//		Map<String, Object> res = new HashMap<String, Object>();
//		res.put("doLike", service.update_Like(params));
//		return res;
//	}
//	
//	@PostMapping("doUnLike")
//	public Map<String, Object> insertLike(@RequestBody Map<String, Object> params) throws Exception{
//		Map<String, Object> res = new HashMap<String, Object>();
//		res.put("insertLike", service.update_UnLike(params));
//		return res;
//	}
//	
	
	
}

    
