package com.codeone.dao.calendar;
 
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.codeone.dto.calendar.CalendarDto;




@Mapper
@Repository
public interface CalendarDao {
	
	//일정 달력에 불러오기
	public List<CalendarDto> getCalendarList(String id);
	
	//일정추가
	boolean writeCalendar(CalendarDto dto);
	
	//일정조회
	public CalendarDto detailCalendar (int seq);
	
	//일정수정
	boolean updateCalendar(CalendarDto dto);
	
	//일정삭제
	public boolean deleteCalendar(int seq);
}
