package com.codeone.dao.job;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.codeone.dto.job.JobLikeDto;

@Mapper
public interface JobDao {

	List<Map<String, Object>> job_list(Map<String, Object> params);

	Map<String, Object> view(Map<String, Object> params);

	/*
	 * int insert(Map<String, Object> params);
	 * 
	 * int update(Map<String, Object> params);
	 * 
	 * int delete(Map<String, Object> params);
	 */

	List<Map<String, Object>> code_list(Map<String, Object> params);
 
	int update_Like(JobLikeDto params) throws Exception;
	
	int insert_Like(JobLikeDto params) throws Exception;
	
	
}
