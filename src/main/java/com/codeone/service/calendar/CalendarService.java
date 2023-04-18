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
	
	public List<CalendarDto> getCalendarList(String id) {
		return dao.getCalendarList(id);
	}
	
	public boolean writeCalendar(CalendarDto dto) {
		return dao.writeCalendar(dto);
	}
}
