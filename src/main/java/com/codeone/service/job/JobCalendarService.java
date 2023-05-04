package com.codeone.service.job;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codeone.dao.job.JobCalendarDao;
import com.codeone.dto.job.JobCalendarDto;

@Service
@Transactional
public class JobCalendarService {
	@Autowired
	JobCalendarDao dao;
	

	//모든 일정 보기
	public List<JobCalendarDto> getJobCalendarList(Map<String, Object> params) {
	    return dao.getJobCalendarList(params);
	}

	
	//일정 작성
	public boolean writeJobCalendar(JobCalendarDto dto) {
		return dao.writeJobCalendar(dto);
	}
	
	//일정 상세
	public JobCalendarDto detailJobCalendar(int seq) {
		return dao.detailJobCalendar(seq);
	}

	
	//일정 수정
	public boolean updateJobCalendar(JobCalendarDto dto) {
		return dao.updateJobCalendar(dto);
	} 
	
	//일정 삭제
	public boolean deleteJobCalendar(int seq) {
		return dao.deleteJobCalendar(seq);
	}

}
