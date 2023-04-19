package com.codeone.service.calendar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codeone.dao.calendar.CalendarDao;
import com.codeone.dto.calendar.CalendarDto;


@Service
@Transactional
public class CalendarService {

	@Autowired
	CalendarDao dao;
	
	//일정 달력에 불러오기
	public List<CalendarDto> getCalendarList(String id) {
		return dao.getCalendarList(id);
	}
	
	//일정추가
	public boolean writeCalendar(CalendarDto dto) {
		return dao.writeCalendar(dto);
	}
	
	//일정조회
	public CalendarDto detailCalendar(int seq) {
		return dao.detailCalendar(seq);
	}
	
	//일정수정
	public boolean updateCalendar(CalendarDto dto) {
		return dao.updateCalendar(dto);
	}
}
