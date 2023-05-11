package com.codeone.controller.job;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codeone.dto.job.JobDto;
import com.codeone.dto.job.JobParam;
import com.codeone.dto.job.JobResponse;
import com.codeone.service.job.JobService;
//채용 메인페이지 글목록, 상세보기, 필터링, 좋아요 컨트롤러

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin
@RequestMapping("job/")
@RestController
public class JobController {
	@Autowired
	private JobService service;
	
	//디버깅용도 LoggerFactory 추가
	private Logger log = LoggerFactory.getLogger(JobController.class);
 
	//채용 글목록 페이징 후
	@GetMapping(value = "list")
	public Map<String, Object> joblist(JobParam param) {
		System.out.println("JobController joblist() " + new Date());

		// 글의 시작과 끝
		// 0부터 시작하기떄문에 리액트에서 넘겨줄 때 -1해서 넘겨줌
		int pn = param.getPageNumber(); // 0 1 2 3 4

		int start = pn * 10; // 페이지 숫자 넘어온것 10 20 30 40부터 시작


		param.setStart(start);
		param.setDataCount(10); // 데이터 10개씩 보여주기 추후 25개로 바꾸기

	 	System.out.println(param);

		// search, choice 넣어주고 리스트 불러오기
		List<JobDto> list = service.joblist(param);
		
		// 글의 총갯수
		int totalCount = service.getAllJobCount(param); // search, choice 들어오는값은 없음.

		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("cnt", totalCount); // 리액트 글의 총수 보내주기
		
		
		System.out.println(totalCount);
		return map;

	}

	
		//채용 글목록 페이징전
//		@GetMapping("list")
//		public Map<String, Object> list(@RequestParam Map<String, Object> params) throws Exception {
//			Map<String, Object> res = new HashMap<String, Object>();
//			
//			System.out.println("params title == > " + params.get("title"));
//
//			res.put("list", service.joblist(params)); 
//			return res;		
//		}	  

		
		
	//채용 update페이지 상세보기
	@GetMapping("{id}")
	public JobResponse job(@PathVariable("id") int id) {
		return service.job(id);
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
	// 좋아요한 채용공고 일정관리에 등록
	@GetMapping(value = "/getCalendarjobList")
	public ResponseEntity<Map<String, Object>> getCalendarjobList(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
	    System.out.println("JobController getCalendarjobList() " + new Date());
	    
	    // Map으로 dto 전달
	    Map<String, Object> map = new HashMap<>();        
	    try {
	        List<JobDto> list = service.getCalendarjobList(id);
	        map.put("list", list);
	        return ResponseEntity.ok(map);                  // 성공
	        
	    } catch (Exception e) {
	        return ResponseEntity.badRequest().build();      // 정보 가져오기 실패
	    }
	    
	    
	}

}

    
