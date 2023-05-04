package com.codeone.service.studygroup;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codeone.dao.studygroup.StudygroupInfoDao;
import com.codeone.dao.studygroup.StudygroupManagementDao;
import com.codeone.dao.studygroup.StudygroupMemberDao;
import com.codeone.dao.studygroup.StudygroupMemberVotingDao;
import com.codeone.dto.studygroup.StudygroupInfoDto;
import com.codeone.dto.studygroup.StudygroupManagementDto;
import com.codeone.dto.studygroup.StudygroupMemberVotingDto;
import com.codeone.enumVariable.VotingType;

@Service
@Transactional
public class StudygroupService {
	@Autowired
	StudygroupManagementDao studygroupManagementDao;
	@Autowired
	StudygroupInfoDao studygroupInfoDao;
	@Autowired
	StudygroupMemberDao studygroupMemberDao;
	@Autowired
	StudygroupMemberVotingDao studygroupMemberVotingDao;
	
	/**
	 * 수정할 권한이 있는 스터디 그룹인지 확인
	 * 
	 * @param seq 권한을 확인할 스터디그룹 번호
	 * @param requestMemberSeq 수정을 요청한 사용자의 번호
	 * @return 수정할 권한이 있다면 true, 없다면 false
	 */
	protected StudygroupManagementDto checkPermissionToModify(int seq, int requestMemberSeq) {
		StudygroupManagementDto studygroupManagement = studygroupManagementDao.selectOne(seq);
		
		if(studygroupManagement == null || studygroupManagement.getMemberSeq() != requestMemberSeq) {
			return null;
		} else {
			return studygroupManagement;
		}
	}
	
	/**
	 * 스터디그룹의 정보를 수정할 수 있는지 여부 확인
	 * 
	 * @param studygroup 수정할 스터디그룹 정보
	 * @param votingType 수정 유형
	 * @return 수정 가능하다면 true, 불가능하다면 false
	 */
	protected boolean isModifiableStudygroup(int infoSeq, VotingType votingType) {
		// 스터디 그룹 정보 조회
		StudygroupInfoDto studygroupInfo = studygroupInfoDao.selectOneBySeq(infoSeq);
		
		// 수정을 요청한 날짜
		LocalDate now = LocalDate.now();
		// 수정을 요청한 스터디그룹 번호
		int requestStudygroupSeq = studygroupInfo.getSeq();
		// 스터디 그룹 시작 날짜
		LocalDate startDate = studygroupInfo.getStartDate();
		
		// 수정 가능 여부
		boolean modifiable = false;
		
		// 스터디그룹 시작 여부 확인
		if(!startDate.isBefore(now)) {
			// 시작 전이라면 수정 가능
			modifiable = true;
		} else {
			// 이미 시작된 스터디 그룹이라면
			int studygroupAmount = studygroupMemberDao.getStudygroupMemberAmountByStudygroupSeq(requestStudygroupSeq);
			if(studygroupAmount == 1) {
				// 그룹원 수가 1명이라면 곧 바로 수정
				modifiable = true;
			} else {
				// 그룹원 수가 2명 이상이라면
				StudygroupMemberVotingDto studygroupMemberVoting = new StudygroupMemberVotingDto();
				studygroupMemberVoting.setStudygroupSeq(requestStudygroupSeq);
				studygroupMemberVoting.setVotingSeq(votingType.ordinal());
				
				// 과반수 이상이 수정에 동의했는지 여부 확인
				boolean isExceededMajority = studygroupMemberVotingDao.isExceededMajority(studygroupMemberVoting);
				// 과반수 이상이 수정에 동의했다면 수정 가능, 과반수 미만이라면 수정 불가능
				modifiable = isExceededMajority;
			}
		}
		
		return modifiable;
	}
}
