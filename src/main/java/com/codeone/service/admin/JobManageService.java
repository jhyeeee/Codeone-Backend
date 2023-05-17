package com.codeone.service.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeone.dao.admin.JobManageDao;
import com.codeone.dao.job.JobDao;
import com.codeone.dto.blog.BlogDto;
import com.codeone.dto.job.JobDto;
import com.codeone.dto.store.StoreItemDto;
import com.codeone.dto.store.StoreParam;

@Service
public class JobManageService {
	@Autowired
	private JobManageDao dao;
	
	public List<JobDto> getAllJobsMng() {
		return dao.getAllJobsMng();
	}
	
	public boolean showHideJob (int seq, int comdel) {
		return dao.showHideJob(seq, comdel);
	}
	
}
