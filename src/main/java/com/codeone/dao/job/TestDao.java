package com.codeone.dao.job;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestDao {

	int insert(Map<String, Object> params) throws Exception;

	List<Map<String, Object>> list(Map<String, Object> params) throws Exception;
	
	int insert2(Map<String, Object> parmas2) throws Exception;

	List<Map<String,Object>> testList(Map<String, Object> params) throws Exception;
	
}
