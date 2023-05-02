package com.codeone.service.studygroup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeone.dao.studygroup.StudygroupInfoDao;
import com.codeone.dao.studygroup.StudygroupManagementDao;
import com.codeone.dto.studygroup.StudygroupInfoDto;

@Service
public class StudygroupScheduleService {
	@Autowired
	StudygroupInfoDao studygroupInfoDao;
	@Autowired
	StudygroupManagementDao studygroupManagementDao;
	
	public void closeStudygroupRecruitment() {
		List<StudygroupInfoDto> list = studygroupInfoDao.selectAllClosed();
		
		if(list.size() > 0) {
			studygroupManagementDao.updateAllClose(list);
		}
	}
}
