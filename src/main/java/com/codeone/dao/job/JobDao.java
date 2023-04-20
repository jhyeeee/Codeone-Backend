package com.codeone.dao.job;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JobDao {

	List<Map<String, Object>> job_list(Map<String, Object> params);
 
	
	
	
	
}
