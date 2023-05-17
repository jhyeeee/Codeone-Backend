package com.codeone.dao.admin;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.codeone.dto.job.JobDto;


@Mapper
public interface JobManageDao {

	List<JobDto> getAllJobsMng();
	boolean showHideJob(int seq, int comdel);	
	
}
