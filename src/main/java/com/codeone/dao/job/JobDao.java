package com.codeone.dao.job;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.codeone.dto.job.JobDto;
import com.codeone.dto.job.JobParam;
import com.codeone.dto.store.StoreParam;


@Mapper
public interface JobDao {

	
	//List<JobDto> job_list(JobDto jobdto);
	
	//채용 글목록 페이징후
	List<JobDto> job_list(JobParam param);
	
	//채용 글목록 페이징전
//	List<Map<String, Object>> job_list(Map<String, Object> params);

	//채용 글의 총수
	int getAllJobCount(JobParam param);
	
	//채용 update페이지 상세보기->optional : null일 경우 처리
	Optional<JobDto> job(int seq);
	
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
