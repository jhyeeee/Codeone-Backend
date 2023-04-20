package com.codeone.service.job;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeone.dao.job.JobDao;

@Service
public class JobService {
	@Autowired
	private JobDao dao;
	
	public Map<String, Object> joblist(Map<String, Object>params) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", dao.job_list(params));
		return map;
	}
	

	
}
