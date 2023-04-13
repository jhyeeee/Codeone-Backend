package com.codeone.service.studygroup;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codeone.command.studygroup.StudygroupCommand;
import com.codeone.dao.studygroup.StudygroupDao;
import com.codeone.dao.studygroup.StudygroupPositionDao;
import com.codeone.dao.studygroup.StudygroupStackDao;
import com.codeone.dto.studygroup.StudygroupDto;
import com.codeone.dto.studygroup.StudygroupPositionDto;
import com.codeone.dto.studygroup.StudygroupStackDto;
import com.codeone.exception.AlreadyStudygroupStartedException;

@Service
@Transactional
public class StudygroupService {
	@Autowired
	StudygroupDao studygroupDao;
	@Autowired
	StudygroupPositionDao studygroupPositionDao;
	@Autowired
	StudygroupStackDao studygroupStackDao;
	
	public boolean deleteStudygroupRecruitment(StudygroupCommand studygroup) throws AlreadyStudygroupStartedException {
		// 삭제를 요청한 사용자의 번호
		int requestMemberSeq = studygroup.getMemberSeq();
		// 삭제를 요청한 날짜
		LocalDate now = LocalDate.now();
		
		// 삭제할 모집글 조회
		StudygroupDto oldStudygroup = studygroupDao.selectOneBySeq(studygroup);
		if(oldStudygroup == null || oldStudygroup.getMemberSeq() != requestMemberSeq) {
			// 존재 하지 않는 seq 번호를 전달했거나 삭제할 모집글의 작성자가 아닐 경우
			return false;
		} else if(oldStudygroup.isDeleted()) {
			// 이미 삭제 처리된 모집 글에 다시 삭제 요청을 했을 경우
			return true;
		} else if(oldStudygroup.getStartDate().isBefore(now)) {
			// 이미 시작된 스터디그룹일 경우
			throw new AlreadyStudygroupStartedException();
		}
		
		// 삭제할 모집글의 작성자일 경우
		// 모집글 삭제 처리
		studygroupDao.deleteStudygroupRecruitment(studygroup.getSeq());
		
		return true;
	}
	
	public void writeStudygroupRecruitment(StudygroupDto studygroup) {
		// 모집글 작성
		studygroupDao.writeStudygroupRecruitment(studygroup);
		
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
}
