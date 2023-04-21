package com.codeone.service.studygroup;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeone.dao.studygroup.PositionDao;
import com.codeone.dao.studygroup.TechnologyStackDao;
import com.codeone.dto.studygroup.PositionDto;
import com.codeone.dto.studygroup.RecruitmentTypeDto;
import com.codeone.dto.studygroup.TechnologyStackDto;

@Service
public class StudygroupFilterService {
	@Autowired
	PositionDao positionDao;
	@Autowired
	TechnologyStackDao technologyStackDao;
	
	public List<PositionDto> getPositionList() {
		return positionDao.getAll();
	}
	
	public List<TechnologyStackDto> getTechnologyStackList() {
		return technologyStackDao.getAll();
	}
	
	// DTO 참고
	public List<RecruitmentTypeDto> getRecruitmentTypeList() {
		RecruitmentTypeDto rt1 = new RecruitmentTypeDto();
		RecruitmentTypeDto rt2 = new RecruitmentTypeDto();
		
		rt1.setSeq(1);
		rt1.setName("프로젝트");
		
		rt2.setSeq(2);
		rt2.setName("스터디");
		
		List<RecruitmentTypeDto> recruitmentTypeList = new ArrayList<>();
		recruitmentTypeList.add(rt1);
		recruitmentTypeList.add(rt2);
		
		return recruitmentTypeList;
	}
}
