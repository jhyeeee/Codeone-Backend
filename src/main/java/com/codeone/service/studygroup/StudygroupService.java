package com.codeone.service.studygroup;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codeone.command.studygroup.StudygroupCommand;
import com.codeone.dao.studygroup.StudygroupDao;
import com.codeone.dao.studygroup.StudygroupManagementDao;
import com.codeone.dao.studygroup.StudygroupPositionDao;
import com.codeone.dao.studygroup.StudygroupStackDao;
import com.codeone.dto.studygroup.StudygroupDto;
import com.codeone.dto.studygroup.StudygroupManagementDto;
import com.codeone.dto.studygroup.StudygroupPositionDto;
import com.codeone.dto.studygroup.StudygroupStackDto;

@Service
@Transactional
public class StudygroupService {
	@Autowired
	StudygroupManagementDao studygroupManagementDao;
	@Autowired
	StudygroupDao studygroupDao;
	@Autowired
	StudygroupPositionDao studygroupPositionDao;
	@Autowired
	StudygroupStackDao studygroupStackDao;
	
	public void writeStudyGroupRecruitment(StudygroupCommand studygroupCommand) {
		// -- 스터디 그룹 관리 정보 생성 --
		StudygroupManagementDto newStudygroupManagement = studygroupCommand.toStudygroupManagementDto();
		studygroupManagementDao.insert(newStudygroupManagement);
		
		// -- 스터디 그룹 정보 생성 --
		StudygroupDto newStudygroup = studygroupCommand.toDto();
		newStudygroup.setManagementSeq(newStudygroupManagement.getSeq());
		
		studygroupDao.writeStudyGroupRecruitment(newStudygroup);
		
		// -- 스터디 그룹 관리 정보와 스터디 그룹 정보 연결 --
		newStudygroupManagement.setInfoSeq(newStudygroup.getSeq());
		studygroupManagementDao.updateInfoSeq(newStudygroupManagement);
		
		
		// -- 모집글의 모집 분야 등록 --
		int[] recruitementPart = newStudygroup.getRecruitmentPart();
		
		List<StudygroupPositionDto> studygroupPositionList = new ArrayList<>(recruitementPart.length);
		for(int rp : recruitementPart) {
			StudygroupPositionDto sgpd = new StudygroupPositionDto();
			sgpd.setManagementSeq(newStudygroupManagement.getSeq());
			sgpd.setPositionSeq(rp);
			
			studygroupPositionList.add(sgpd);
		}
		
		studygroupPositionDao.insert(studygroupPositionList);
		// -- 모집글의 모집 분야 등록 --
		
		// -- 모집글의 기술 스택 등록 --
		int[] technologyStack = newStudygroup.getTechnologyStack();
		
		List<StudygroupStackDto> StudygroupStackList = new ArrayList<>(technologyStack.length);
		for(int ts : technologyStack) {
			StudygroupStackDto ssd = new StudygroupStackDto();
			ssd.setStudygroupSeq(newStudygroupManagement.getSeq());
			ssd.setStackSeq(ts);
			
			StudygroupStackList.add(ssd);
		}
		
		studygroupStackDao.insert(StudygroupStackList);
		// -- 모집글의 기술 스택 등록 --
	}
}
