package com.codeone.service.studygroup;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codeone.command.studygroup.StudygroupListCommand;
import com.codeone.dto.studygroup.StudygroupInfoDto;
import com.codeone.dto.studygroup.StudygroupListDto;
import com.codeone.dto.studygroup.StudygroupManagementDto;
import com.codeone.exception.NotPermissionToModifyException;
import com.codeone.exception.UnmodifiableConditionException;

@Service
@Transactional
public class StudygroupMngService extends StudygroupService {
	public int getMyStudygroupAmount(int memberSeq) {
		return studygroupManagementDao.getAmountByMemberSeq(memberSeq);
	}
	
	public List<StudygroupListDto> getMyStudygroupList(StudygroupListCommand command) {
		return studygroupManagementDao.selectAllMyStudygroupList(command);
	}
	
	public void toggleIsClosed(int seq, int memberSeq) throws NotPermissionToModifyException, UnmodifiableConditionException {
		// 요청한 스터디 그룹의 마감 여부를 전환할 수 있는 권한이 있는지 확인
		StudygroupManagementDto studygroupManagement = checkPermissionToModify(seq, memberSeq);
		if(studygroupManagement == null) {
			// 마감 여부를 전환할 권한이 없다면
			throw new NotPermissionToModifyException();
		}
		
		LocalDate now = LocalDate.now();
		
		StudygroupInfoDto studygroupInfo = studygroupInfoDao.selectOneBySeq(studygroupManagement.getInfoSeq());
		
		LocalDate deadlineForRecruitment = studygroupInfo.getDeadlineForRecruitment();
		if(deadlineForRecruitment.isBefore(now)) {
			// 모집 마감 날짜가 이미 지났을 경우
			throw new UnmodifiableConditionException();
		}
		
		// 마감 여부 전환
		studygroupManagementDao.toggleIsClosed(seq);
	}

	public void increaseViewAmount(int seq) {
		studygroupManagementDao.increaseViewAmount(seq);
	}
}
