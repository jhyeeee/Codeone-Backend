package com.codeone.controller.job;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codeone.dto.job.ComPagingDto;
import com.codeone.dto.job.JobDto;
import com.codeone.dto.job.JobFilterDto;
import com.codeone.service.job.JobService;

@RestController
public class JobController {

 
      @Autowired
      private JobService service;
        
      //채용 글목록 - 수정 필요
   @GetMapping(value = "/joblist")
   public List<JobDto> getJobList( @RequestParam JobFilterDto dto) {
    		  //Map으로 받기 -> json형태로 map에 담김. map.get 으로 하면 key값으로 
    		  //컨트롤러에서 filterdto받기
    		  //바로 dto뽑기
    	 //필요 없어짐 JobFilterDto dto = new JobFilterDto();
    	  return service.joblist(dto);
    }
    //채용 seq조회
    @GetMapping(value = "/getJob")
  	public JobDto getJob(int comseq) {
  		System.out.println("JobController getJob " + new Date());
  		
  		return service.getJob(comseq);		
  	}
  	
    //기업회원 글목록(페이징)
    @GetMapping("/combbslist")
    public Map<String, Object> combbslist(@RequestParam(name = "page", defaultValue = "1") int page) {
        // 페이징 관련 데이터 생성
        ComPagingDto pagingDto = new ComPagingDto();
        pagingDto.setPageNo(page);
        pagingDto.setViewCount(10);
        pagingDto.setLimit((page - 1) * pagingDto.getViewCount());

        // 페이지 데이터 조회
        List<JobDto> list = service.combbslist(pagingDto);

        // 페이징 관련 데이터 조회
        int totalCount = service.getAllComBbs(pagingDto);
        int totalPages = (int) Math.ceil((double) totalCount / pagingDto.getViewCount());

        // 데이터 저장
        Map<String, Object> map = new HashMap<>();
        map.put("combbslist", list);
        map.put("currentPage", page);
        map.put("totalPages", totalPages);
        map.put("totalCount", totalCount);

        return map;
    }

    //기업회원 글작성
  	@PostMapping(value = "writeJob")
  	public String writeJob(JobDto job) {
  		System.out.println("JobController writeJob() " + new Date());
  		
  		boolean b = service.writeJob(job);
  		if(b) {
  			return "YES";
  		} else {
  			return "NO";
  		}
  	} 
  //기업회원 글수정
  	@PostMapping(value = "updateJob")
  	public String updateJob(JobDto job) {
  		System.out.println("JobController updateJob " + new Date());
  		
  		boolean b = service.updateJob(job);
  		if(b == false) {
  			return "NO";
  		}
  		return "YES";		
  	}
  //기업회원 글삭제
  	@PostMapping(value = "deleteJob")
  	public String deleteJob(int comseq) {
  		System.out.println("JobController deleteJob " + new Date());
  		
  		boolean b = service.deleteJob(comseq);
  		if(b == false) {
  			return "NO";
  		}
  		return "YES";
  	}
  
  	
  	//필터링 기능 DB삭제로 삭제 예정
//  	@GetMapping("/getGroupList")
//  	public ResponseEntity<List<JobGroupDto>> getGroupList(){
//  		List<JobGroupDto> result = service.getGroupList();
//  		return new ResponseEntity<>(result,HttpStatus.OK); // result 반환해줄 데이터 , HttpStatus : 상태코드, OK: 200
//  
//  	}
//  	
//	@GetMapping("/getCareerList")
//  	public ResponseEntity<List<JobFilterDto>> getCareerList(){
//  		List<JobFilterDto> result = service.getCareerList();
//  		return new ResponseEntity<>(result,HttpStatus.OK); // result 반환해줄 데이터 , HttpStatus : 상태코드, OK: 200
//  
//  	}
//	
//	@GetMapping("/getLocationList")
//  	public ResponseEntity<List<JobFilterDto>> getLocationList(){
//  		List<JobFilterDto> result = service.getLocationList();
//  		return new ResponseEntity<>(result,HttpStatus.OK); // result 반환해줄 데이터 , HttpStatus : 상태코드, OK: 200
//  
//  	}
//	
//	@GetMapping("/getSkillList")
//  	public ResponseEntity<List<JobFilterDto>> getSkillList(){
//  		List<JobFilterDto> result = service.getSkillList();
//  		return new ResponseEntity<>(result,HttpStatus.OK); // result 반환해줄 데이터 , HttpStatus : 상태코드, OK: 200
//  
//  	}
//	
//	@GetMapping("/getTagList")
//  	public ResponseEntity<List<JobFilterDto>> getTagList(){
//  		List<JobFilterDto> result = service.getTagList();
//  		return new ResponseEntity<>(result,HttpStatus.OK); // result 반환해줄 데이터 , HttpStatus : 상태코드, OK: 200
//  
//  	}
	
     
}