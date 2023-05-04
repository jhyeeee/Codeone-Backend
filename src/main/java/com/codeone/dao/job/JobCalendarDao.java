package com.codeone.dao.job;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.codeone.dto.job.JobCalendarDto;


@Mapper
@Repository
public interface JobCalendarDao {
	
	//모든 일정 보기
	public List<JobCalendarDto> getJobCalendarList(Map<String, Object> params);
	 
	//일정 작성 
	boolean writeJobCalendar(JobCalendarDto dto);
	
	//일정 상세
	public JobCalendarDto detailJobCalendar(int seq);
	
	//일정 수정
	boolean updateJobCalendar(JobCalendarDto dto);

	//일정 삭제
	public boolean deleteJobCalendar(int seq);

}
