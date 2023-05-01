package com.codeone.controller.calendar;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codeone.dto.calendar.CalendarDto;
import com.codeone.dto.user.UserDto;
import com.codeone.service.calendar.CalendarService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



@RestController
public class CalendarController {

	@Autowired
	CalendarService service;
	
	// 달력에 일정 불러오기
	@GetMapping(value = "/getCalendarList")
	public ResponseEntity<Map<String, Object>> getCalendarList(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
	    System.out.println("CalendarController getCalendarList() " + new Date());
	    System.out.println("//////// id확인 "+id);
	   
	    
	    // Map으로 dto 전달
	    Map<String, Object> map = new HashMap<>();        
	    try {
	        List<CalendarDto> list = service.getCalendarList(id);
	        map.put("list", list);
	        return ResponseEntity.ok(map);                  // 성공
	        
	    } catch (Exception e) {
	        return ResponseEntity.badRequest().build();      // 정보 가져오기 실패
	    }
	    
	    
	}

	
	//	일정추가
	@PostMapping(value = "/writeCalendar")
    public ResponseEntity<String> writeCalendar(CalendarDto dto) {
        System.out.println("CalendarController writeCalendar() " + new Date());
        //System.out.println("/////////////확인" + dto); ->일정이 잘들어가는지 확인
        
        boolean result = service.writeCalendar(dto);
        if (result == true) {
        	return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
	

	// 일정조회
    @GetMapping(value = "/detailCalendar")
    public ResponseEntity<CalendarDto> detailCalendar(@RequestParam int seq) {
        System.out.println("CalendarController detailCalendar() " + new Date());
        return new ResponseEntity<>(service.detailCalendar(seq), HttpStatus.OK);
    }
    
	
	// 일정수정
	@PostMapping(value = "/updateCalendar")
    public ResponseEntity<String> updateCalendar(CalendarDto dto) {
        System.out.println("CalendarController updateCalendar() " + new Date());
        //System.out.println("/////////////확인" + dto); ->일정이 잘들어가는지 확인
        
        boolean result = service.updateCalendar(dto);
        if (result == true) {
        	return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
	
	// 일정삭제
    @DeleteMapping(value = "/deleteCalendar")
    public ResponseEntity<String> deleteCalendar(@RequestParam int seq) {
        System.out.println("CalendarController deleteCalendar() " + new Date());
        boolean result = service.deleteCalendar(seq);
        if (result == true) {
        	return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
	
	
	

}
