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

import com.codeone.dto.job.JobCalendarDto;
import com.codeone.service.job.JobCalendarService;

// 채용 일정 컨트롤러

@RestController
public class JobCalendarController {
	@Autowired
	JobCalendarService service;
	
	@GetMapping(value = "/getJobCalendarList")	

	//모든 일정 보기
	public Map<String, Object> getJobCalendarList(@RequestParam Map<String, Object> params) {
	    System.out.println("JobCalendarController getJobCalendarList() " + new Date());
	    
	    Map<String, Object> map = new HashMap<>();
	    List<JobCalendarDto> list = service.getJobCalendarList(params);
	    map.put("list", list);
	    
	    
	    System.out.println("모든 일정 불러오기 " + list);
	    return map;
	}
	
	
	//	일정 작성
	@PostMapping(value="/writeJobCalendar")
    public String writeJobCalendar(JobCalendarDto dto) {
		System.out.println("JobCalendarController writeJobCalendar() " + new Date());
		boolean result = service.writeJobCalendar(dto);
        if(result == true) {
        	return "YES";        
        }
        return "NO";
        
    }

	// 일정 상세
	@GetMapping(value = "/detailJobCalendar")
	public JobCalendarDto detailJobCalendar(int seq) {
		System.out.println("JobCalendarController detailJobCalendar() " + new Date());
		
		return service.detailJobCalendar(seq);
	}
	
	// 일정 수정
	@PostMapping(value="/updateJobCalendar")
	public String updateJobCalendar(JobCalendarDto dto) {
		System.out.println("JobCalendarController updateJobCalendar() " + new Date());
		System.out.println("수정값" + dto);
		
		boolean result = service.updateJobCalendar(dto);
		if(result == true) {
			return "YES";
		} 
		return "NO";		
	}
	
	// 일정 삭제
	@GetMapping(value = "/deleteJobCalendar")
	public String deleteJobCalendar(int seq) {
		System.out.println("JobCalendarController deleteJobCalendar() " + new Date());
	    System.out.println("삭제 seq값 " + seq);  
	    
	    boolean result = service.deleteJobCalendar(seq);
	    if(result == true) {
	    	return "YES";
	    }
	    return "NO";
	}
	
	

}
