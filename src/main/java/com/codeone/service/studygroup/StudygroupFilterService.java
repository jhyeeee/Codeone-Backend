package com.codeone.service.studygroup;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeone.dao.studygroup.PositionDao;
import com.codeone.dao.studygroup.TechnologyStackDao;
import com.codeone.dto.studygroup.NumberOfRecruitsDto;
import com.codeone.dto.studygroup.PositionDto;
import com.codeone.dto.studygroup.RecruitmentTypeDto;
import com.codeone.dto.studygroup.TechnologyStackDto;
import com.codeone.dto.studygroup.WayOfProceedingDto;

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
		rt1.setName("스터디");
		
		rt2.setSeq(2);
		rt2.setName("프로젝트");
		
		List<RecruitmentTypeDto> recruitmentTypeList = new ArrayList<>();
		recruitmentTypeList.add(rt1);
		recruitmentTypeList.add(rt2);
		
		return recruitmentTypeList;
	}

	// DTO 참고
	public List<WayOfProceedingDto> getWayOfProceedingList() {
		WayOfProceedingDto wop1 = new WayOfProceedingDto();
		WayOfProceedingDto wop2 = new WayOfProceedingDto();
		
		wop1.setSeq(1);
		wop1.setName("온라인");
		
		wop2.setSeq(2);
		wop2.setName("오프라인");
		
		List<WayOfProceedingDto> wayOfProceedingList = new ArrayList<>();
		wayOfProceedingList.add(wop1);
		wayOfProceedingList.add(wop2);
		
		return wayOfProceedingList;
	}

	public List<NumberOfRecruitsDto> getNumberOfRecruitsList() {
		NumberOfRecruitsDto nor1 = new NumberOfRecruitsDto();
		NumberOfRecruitsDto nor2 = new NumberOfRecruitsDto();
		NumberOfRecruitsDto nor3 = new NumberOfRecruitsDto();
		NumberOfRecruitsDto nor4 = new NumberOfRecruitsDto();
		NumberOfRecruitsDto nor5 = new NumberOfRecruitsDto();
		NumberOfRecruitsDto nor6 = new NumberOfRecruitsDto();
		NumberOfRecruitsDto nor7 = new NumberOfRecruitsDto();
		NumberOfRecruitsDto nor8 = new NumberOfRecruitsDto();
		NumberOfRecruitsDto nor9 = new NumberOfRecruitsDto();
		NumberOfRecruitsDto nor10 = new NumberOfRecruitsDto();
		NumberOfRecruitsDto nor11 = new NumberOfRecruitsDto();
		
		nor1.setSeq(1);
		nor1.setName("1명");
		
		nor2.setSeq(2);
		nor2.setName("2명");
		
		nor3.setSeq(3);
		nor3.setName("3명");
		
		nor4.setSeq(4);
		nor4.setName("4명");
		
		nor5.setSeq(5);
		nor5.setName("5명");
		
		nor6.setSeq(6);
		nor6.setName("6명");
		
		nor7.setSeq(7);
		nor7.setName("7명");
		
		nor8.setSeq(8);
		nor8.setName("8명");
		
		nor9.setSeq(9);
		nor9.setName("9명");
		
		nor10.setSeq(10);
		nor10.setName("10명");
		
		nor11.setSeq(11);
		nor11.setName("인원 미정");
		
		List<NumberOfRecruitsDto> numberOfRecruitsList = new ArrayList<>();
		numberOfRecruitsList.add(nor1);
		numberOfRecruitsList.add(nor2);
		numberOfRecruitsList.add(nor3);
		numberOfRecruitsList.add(nor4);
		numberOfRecruitsList.add(nor5);
		numberOfRecruitsList.add(nor6);
		numberOfRecruitsList.add(nor7);
		numberOfRecruitsList.add(nor8);
		numberOfRecruitsList.add(nor9);
		numberOfRecruitsList.add(nor10);
		numberOfRecruitsList.add(nor11);
		
		return numberOfRecruitsList;
	}
}
