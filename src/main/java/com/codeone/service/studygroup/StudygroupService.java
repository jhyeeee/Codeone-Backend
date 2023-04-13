package com.codeone.service.studygroup;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codeone.dao.studygroup.StudygroupDao;
import com.codeone.dao.studygroup.StudygroupPositionDao;
import com.codeone.dao.studygroup.StudygroupStackDao;
import com.codeone.dto.studygroup.StudygroupDto;
import com.codeone.dto.studygroup.StudygroupPositionDto;
import com.codeone.dto.studygroup.StudygroupStackDto;

@Service
@Transactional
public class StudygroupService {
	@Autowired
	StudygroupDao studygroupDao;
	@Autowired
	StudygroupPositionDao studygroupPositionDao;
	@Autowired
	StudygroupStackDao studygroupStackDao;
	
	public void writeStudyGroupRecruitment(StudygroupDto studygroup) {
		// 모집글 작성
		studygroupDao.writeStudyGroupRecruitment(studygroup);
		
		// 모집글 번호
		int seq = studygroup.getSeq();
		
		// -- 모집글의 모집 분야 등록 --
		int[] recruitementPart = studygroup.getRecruitmentPart();
		
		List<StudygroupPositionDto> studygroupPositionList = new ArrayList<>(recruitementPart.length);
		for(int rp : recruitementPart) {
			StudygroupPositionDto sgpd = new StudygroupPositionDto();
			sgpd.setStudygroupSeq(seq);
			sgpd.setPositionSeq(rp);
			
			studygroupPositionList.add(sgpd);
		}
		
		studygroupPositionDao.insert(studygroupPositionList);
		// -- 모집글의 모집 분야 등록 --
		
		// -- 모집글의 기술 스택 등록 --
		int[] technologyStack = studygroup.getTechnologyStack();
		
		List<StudygroupStackDto> StudygroupStackList = new ArrayList<>(technologyStack.length);
		for(int ts : technologyStack) {
			StudygroupStackDto ssd = new StudygroupStackDto();
			ssd.setStudygroupSeq(seq);
			ssd.setStackSeq(ts);
			
			StudygroupStackList.add(ssd);
		}
		
		studygroupStackDao.insert(StudygroupStackList);
		// -- 모집글의 기술 스택 등록 --
	}
	
	public List<StudygroupDto> getAll() {
		return studygroupDao.getAll();
	}
}
