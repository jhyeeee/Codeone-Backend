package com.codeone.controller.job;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeone.dto.job.ComPagingDto;
import com.codeone.dto.job.JobDto;
import com.codeone.service.job.JobComService;

//기업회원 전용 컨트롤러
 
@RestController
public class JobComController {

 
      @Autowired
      private JobComService service;
    
      
      @GetMapping("/combbslist")
      public Map<String, Object> bbslist(ComPagingDto param){
  		System.out.println("ComBbsController Combbslist() " + new Date());
  		System.out.println(param.getPageNumber());
      	// 페이징 - 글의 시작과 끝
  		int pn = param.getPageNumber();  
  		int start = 1 + (pn * 10);	
  		int end = (pn + 1) * 10;		
  		param.setStart(start);
  		param.setEnd(end);
  		
  		
  		List<JobDto>combbslist = service.combbslist(param); // 1.ComBbsDto
  		int totalCount = service.getAllComBbs(param); 		 // 2.전체 게시글 수
  		
  		System.out.println("totalcount : " + totalCount + "개");	//현재 페이지 개수는 잘 나옴.(15개)
  		
  		Map<String, Object> map = new HashMap<>();
  		map.put("combbslist", combbslist);
  		map.put("totalCount", totalCount);

  		return map;
  	}

 
    //기업회원 글목록(페이징)
//    @GetMapping("/combbslist")
//    public Map<String, Object> combbslist(@RequestParam(name = "page", defaultValue = "1") int page) {
//        // 페이징 관련 데이터 생성
//        ComPagingDto pagingDto = new ComPagingDto();
//        pagingDto.setPageNo(page);
//        pagingDto.setViewCount(10);
//        pagingDto.setLimit((page - 1) * pagingDto.getViewCount());
//
//        // 페이지 데이터 조회
//        List<JobDto> list = service.combbslist(pagingDto);
//
//        // 페이징 관련 데이터 조회
//        int totalCount = service.getAllComBbs(pagingDto);
//        int totalPages = (int) Math.ceil((double) totalCount / pagingDto.getViewCount());
//
//        // 데이터 저장
//        Map<String, Object> map = new HashMap<>();
//        map.put("combbslist", list);
//        map.put("currentPage", page);
//        map.put("totalPages", totalPages);
//        map.put("totalCount", totalCount);
//
//        return map;
//    }

    //기업회원 글작성
  	@PostMapping("/writeJob")
  	public String writeJob(JobDto job) {
  		System.out.println("JobController writeJob() " + new Date());
  		System.out.println(job.toString());
  		
  		boolean b = service.writeJob(job);
  		if(b) {
  			return "YES";
  		} else {
  			return "NO";
  		}
  	} 
  //기업회원 글수정
  	@PostMapping(value = "/updateJob")
  	public String updateJob(JobDto job) {
  		System.out.println("JobController updateJob " + new Date());
  			    
  		boolean b = service.updateJob(job);
  		if(b == false) {
  			return "NO";
  		}
  		return "YES";		
  	}
  	
  //기업회원 글삭제
  	@PostMapping(value = "/deleteJob")
  	public String deleteJob(int seq) {
  		System.out.println("JobController deleteJob " + new Date());
  		System.out.println("삭제된 글번호 " + new Date() + " seq: " + seq);
  		
  		boolean b = service.deleteJob(seq);
  		if(b == false) {
  			return "NO";
  		}
  		return "YES"; 
  	}
  	
  	
  	
  	


  
  	

  	
}