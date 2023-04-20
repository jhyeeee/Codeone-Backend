package com.codeone.controller.calendar;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.codeone.dto.calendar.CalendarDto;
import com.codeone.service.calendar.CalendarService;



@RestController
public class CalendarController {

	@Autowired
	CalendarService service;
	
	@GetMapping(value = "/getCalendarList")
	public Map<String, Object> getCalendarList() {
	    System.out.println("CalendarController getCalendarList() " + new Date());
	    
	    String id = "aaa";
	    Map<String, Object> map = new HashMap<>();
	    List<CalendarDto> list = service.getCalendarList(id);
	    map.put("list", list);
	    
	    
	    System.out.println("////////확인 " + list);
	    return map;
	}
	
	
	//	일정추가
	@PostMapping(value="/writeCalendar")
    public String writeCalendar(CalendarDto dto) {
		System.out.println("CalendarController writeCalendar() " + new Date());
		System.out.println("/////////////" + dto);
		boolean result = service.writeCalendar(dto);
        if(result == true) {
        	return "YES";        
        }
        return "NO";
        
    }
	
	// 일정조회
	@GetMapping(value = "/detailCalendar")
	public CalendarDto detailCalendar(int seq) {
		System.out.println("CalendarController detailCalendar() " + new Date());
		
		return service.detailCalendar(seq);
	}
	
	// 일정수정
	@PostMapping(value="/updateCalendar")
	public String updateCalendar(CalendarDto dto) {
		System.out.println("CalendarController updateCalendar() " + new Date());
		System.out.println("/////////////" + dto);
		
		boolean result = service.updateCalendar(dto);
		if(result == true) {
			return "YES";
		} 
		return "NO";		
	}
	
	// 일정삭제
	@GetMapping(value = "/deleteCalendar")
	public String deleteCalendar(int seq) {
		System.out.println("CalendarController deleteCalendar() " + new Date());
	    //System.out.println("////////확인 " + seq);  -> seq가 잘 넘어오는지 확인
	    
	    boolean result = service.deleteCalendar(seq);
	    if(result == true) {
	    	return "YES";
	    }
	    return "NO";
	}
	
//	@DeleteMapping(value = "/deleteCalendar")
//	public ResponseEntity<String> deleteCalendar(@RequestParam int seq) {
//	    System.out.println("CalendarController deleteCalendar() " + new Date());
//	    System.out.println("////////확인" + seq);
//	    boolean result = service.deleteCalendar(seq);
//	    
//	    if(result == true) {
//			return ResponseEntity.ok("YES");
//		}
//		return ResponseEntity.ok("NO");
//	}
	
	
}
