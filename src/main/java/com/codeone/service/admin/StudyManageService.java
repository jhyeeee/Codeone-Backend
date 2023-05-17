package com.codeone.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codeone.dao.admin.BlogManageDao;
import com.codeone.dao.admin.StudyManageDao;
import com.codeone.dao.blog.BlogDao;
import com.codeone.dao.user.UserDao;
import com.codeone.dto.blog.BlogDto;
import com.codeone.dto.studygroup.StudygroupInfoDto;
@Service
@Transactional
public class StudyManageService {
	
	@Autowired	
	private StudyManageDao dao;
	
	public List<StudygroupInfoDto> allStudyManage() {
		return dao.allStudyManage();
	}

}
