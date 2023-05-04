package com.codeone.dao.job;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.codeone.dto.job.JobDto;


@Mapper
public interface JobDao {

	



	//List<JobDto> job_list(JobDto jobdto);
	
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
 

	//update_whoLiked
	
	int update_whoLiked(JobDto job);
	
	int update_Like(Map<String, Object> params) throws Exception;
	
	int insert_Like(Map<String, Object> params) throws Exception;

	int update_UnLike(Map<String, Object> params) throws Exception;
	
	int delete_Like(Map<String, Object> params) throws Exception;
	
	
	
}
