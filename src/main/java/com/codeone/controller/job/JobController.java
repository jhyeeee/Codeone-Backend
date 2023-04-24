package com.codeone.controller.job;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codeone.dto.job.JobLikeDto;
import com.codeone.service.job.JobService;

@RequestMapping("job/")
@RestController
public class JobController {
	@Autowired
	private JobService service;
	
	//채용 글목록
	@GetMapping("list")
	public Map<String, Object> list(@RequestParam Map<String, Object> params) throws Exception {
		Map<String, Object> res = new HashMap<String, Object>();
		
		System.out.println("params title == > " + params.get("title"));
		
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
	
//	@GetMapping("write")
//	public Map<String, Object> write(@RequestParam Map<String, Object> params) throws Exception{
//		Map<String, Object> res = new HashMap<String, Object>();
//		
//		res.put("result", service.insert(params));
//		return res;
//	}
//	
//
//	@GetMapping("update")
//	public Map<String, Object> update(@RequestParam Map<String, Object> params) throws Exception{
//		Map<String, Object> res = new HashMap<String, Object>();
//		
//		res.put("result", service.update(params));
//		return res;
//	}
//	
//	@GetMapping("delete")
//	public Map<String, Object> delete(@RequestParam Map<String, Object> params) throws Exception{
//		Map<String, Object> res = new HashMap<String, Object>();
//		res.put("result", service.delete(params));
//		return res;
//	}
//	
	//채용 글목록 필터링
	@GetMapping("code/list")
	public Map<String, Object> code_list(@RequestParam Map<String, Object> params) throws Exception{
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("list", service.code_list(params));
		return res;
	}
	
//
	//좋아요
	@PostMapping("doLike")
	public Map<String, Object> doLike(@RequestBody JobLikeDto params) throws Exception{
		Map<String, Object> res = new HashMap<String, Object>();
		System.out.println("params do like ==> " + params);
		res.put("doLike", service.update_Like(params));
		return res;
	}
	
	@PostMapping("insertLike")
	public Map<String, Object> insertLike(@RequestBody JobLikeDto params) throws Exception{
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("insertLike", service.insert_Like(params));
		return res;
	}
	
	
}
