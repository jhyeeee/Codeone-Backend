package com.codeone.service.studygroup;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codeone.command.studygroup.StudygroupInfoCommand;
import com.codeone.command.studygroup.StudygroupDeleteCommand;
import com.codeone.command.studygroup.StudygroupUpdateCommand;
import com.codeone.dao.studygroup.StudygroupInfoDao;
import com.codeone.dao.studygroup.StudygroupManagementDao;
import com.codeone.dao.studygroup.StudygroupMemberDao;
import com.codeone.dao.studygroup.StudygroupMemberVotingDao;
import com.codeone.dao.studygroup.StudygroupPositionDao;
import com.codeone.dao.studygroup.StudygroupStackDao;
import com.codeone.dto.studygroup.StudygroupInfoDto;
import com.codeone.dto.studygroup.StudygroupManagementDto;
import com.codeone.dto.studygroup.StudygroupMemberVotingDto;
import com.codeone.dto.studygroup.StudygroupPositionDto;
import com.codeone.dto.studygroup.StudygroupStackDto;
import com.codeone.enumVariable.VotingType;
import com.codeone.exception.NotPermissionToModifyException;

@Service
@Transactional
public class StudygroupService {
	@Autowired
	StudygroupManagementDao studygroupManagementDao;
	@Autowired
	StudygroupInfoDao studygroupInfoDao;
	@Autowired
	StudygroupPositionDao studygroupPositionDao;
	@Autowired
	StudygroupStackDao studygroupStackDao;
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
	private StudygroupManagementDto checkPermissionToModify(int seq, int requestMemberSeq) {
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
	private boolean isModifiableStudygroup(StudygroupInfoDto studygroup, VotingType votingType) {
		// 수정을 요청한 날짜
		LocalDate now = LocalDate.now();
		// 수정을 요청한 스터디그룹 번호
		int requestStudygroupSeq = studygroup.getSeq();
		// 스터디 그룹 시작 날짜
		LocalDate startDate = studygroup.getStartDate();
		
		// 수정 가능 여부
		boolean modifiable = false;
		
		// 스터디그룹 시작 여부 확인
		if(startDate.isBefore(now)) {
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
	
	/**
	 * 모집 분야 초기화
	 * 
	 * @param studygroup 모집 분야를 초기화할 스터디 그룹 정보
	 */
	private void resetStudygroupPosition(StudygroupInfoDto studygroup, boolean isDelete) {
		// 모집 분야를 초기화할 스터디 그룹의 번호
		int managementSeq = studygroup.getManagementSeq();
		
		if(isDelete) {
			// 모집 분야 삭제
			studygroupPositionDao.delete(managementSeq);
		}
		
		// -- 모집 분야 등록 --
		int[] recruitementPart = studygroup.getRecruitmentPart();
		
		List<StudygroupPositionDto> studygroupPositionList = new ArrayList<>(recruitementPart.length);
		for(int rp : recruitementPart) {
			StudygroupPositionDto sgpd = new StudygroupPositionDto();
			sgpd.setManagementSeq(managementSeq);
			sgpd.setPositionSeq(rp);
			
			studygroupPositionList.add(sgpd);
		}
		
		studygroupPositionDao.insert(studygroupPositionList);
		// -- 모집 분야 등록 --
	}
	
	/**
	 * 기술 스택 초기화
	 * 
	 * @param studygroup 기술 스택을 초기화할 스터디 그룹 정보
	 */
	private void resetStudygroupStack(StudygroupInfoDto studygroup, boolean isDelete) {
		// 모집 분야를 초기화할 스터디 그룹의 번호
		int managementSeq = studygroup.getManagementSeq();
		
		if(isDelete) {
			// 모집 분야 삭제
			studygroupStackDao.delete(managementSeq);
		}
		
		// -- 기술 스택 등록 --
		int[] technologyStack = studygroup.getTechnologyStack();
		
		List<StudygroupStackDto> StudygroupStackList = new ArrayList<>(technologyStack.length);
		for(int ts : technologyStack) {
			StudygroupStackDto ssd = new StudygroupStackDto();
			ssd.setManagementSeq(managementSeq);
			ssd.setStackSeq(ts);
			
			StudygroupStackList.add(ssd);
		}
		
		studygroupStackDao.insert(StudygroupStackList);
		// -- 기술 스택 등록 --
	}
	
	/**
	 * 스터디 그룹 삭제
	 * 
	 * @param studygroup
	 * @return
	 * @throws NotPermissionToModifyException
	 */
	public boolean deleteStudygroupRecruitment(StudygroupDeleteCommand studygroup) throws NotPermissionToModifyException {
		// 삭제를 요청한 스터디 그룹 관리 번호
		int seq = studygroup.getSeq();
		// 삭제를 요청한 사용자의 번호
		int requestMemberSeq = studygroup.getMemberSeq();
		
		// 삭제를 요청한 사용자가 해당 스터디 그룹을 삭제할 수 있는 권한이 있는지 확인
		StudygroupManagementDto oldStudygroup = checkPermissionToModify(seq, requestMemberSeq);
		if(oldStudygroup == null) {
			// 존재 하지 않는 seq 번호를 전달했거나 삭제할 모집글의 작성자가 아닐 경우
			throw new NotPermissionToModifyException();
		} else if(oldStudygroup.isDeleted()) {
			// 이미 삭제 처리된 모집 글에 다시 삭제 요청을 했을 경우
			return true;
		}
		
		// 스터디 그룹 정보 조회
		StudygroupInfoDto oldStudygroupInfo = studygroupInfoDao.selectOneBySeq(oldStudygroup.getInfoSeq());
		// 삭제를 요청한 스터디 그룹을 삭제할 수 있는지 여부 확인
		if(isModifiableStudygroup(oldStudygroupInfo, VotingType.DELETE)) {
			// 삭제를 요청한 스터디 그룹을 삭제할 수 있다면
			studygroupManagementDao.deleteStudygroupRecruitment(studygroup.getSeq());
			
			return true;
		} else {
			// 삭제를 요청한 스터디 그룹을 삭제할 수 없다면
			return false;
		}
	}
	
	/**
	 * 스터디 그룹 수정
	 * 
	 * @param studygroup
	 * @return
	 * @throws NotPermissionToModifyException
	 */
	public boolean updateStudygroupRecruitment(StudygroupUpdateCommand studygroup) throws NotPermissionToModifyException {
		// 수정을 요청한 스터디 그룹 관리 번호
		int seq = studygroup.getSeq();
		// 수정을 요청한 사용자의 번호
		int requestMemberSeq = studygroup.getMemberSeq();
		
		// 수정을 요청한 사용자가 해당 스터디 그룹을 수정할 수 있는 권한이 있는지 확인
		StudygroupManagementDto oldStudygroup = checkPermissionToModify(seq, requestMemberSeq);
		if(oldStudygroup == null) {
			// 존재 하지 않는 seq 번호를 전달했거나 수정할 스터디 그룹의 팀장이 아닐 경우
			throw new NotPermissionToModifyException();
		}
		
		// 스터디 그룹 정보 조회
		StudygroupInfoDto oldStudygroupInfo = studygroupInfoDao.selectOneBySeq(oldStudygroup.getInfoSeq());
		// 수정을 요청한 스터디 그룹을 수정할 수 있는지 여부 확인
		if(isModifiableStudygroup(oldStudygroupInfo, VotingType.UPDATE)) {
			StudygroupInfoDto newStudygroupInfo = studygroup.toStudygroupInfoDto();
			
			// 수정을 요청한 스터디 그룹을 수정할 수 있다면
			studygroupInfoDao.updateStudygroupRecruitment(newStudygroupInfo);
			
			// -- 스터디 그룹의 모집 분야 초기화 --
			resetStudygroupPosition(newStudygroupInfo, true);
			
			// -- 스터디 그룹의 기술 스택 초기화 --
			resetStudygroupStack(newStudygroupInfo, true);
			
			return true;
		} else {
			// 수정을 요청한 스터디 그룹을 수정할 수 없다면
			return false;
		}
	}
	
	/**
	 * 스터디 그룹 생성
	 * 
	 * @param studygroupCommand
	 */
	public void writeStudygroupRecruitment(StudygroupInfoCommand studygroupCommand) {
		// -- 스터디 그룹 관리 정보 생성 --
		StudygroupManagementDto newStudygroupManagement = studygroupCommand.toStudygroupManagementDto();
		studygroupManagementDao.insert(newStudygroupManagement);
		
		// -- 스터디 그룹 정보 생성 --
		StudygroupInfoDto newStudygroupInfo = studygroupCommand.toStudygroupInfoDto();
		newStudygroupInfo.setManagementSeq(newStudygroupManagement.getSeq());
		
		studygroupInfoDao.writeStudygroupRecruitment(newStudygroupInfo);
		
		// -- 스터디 그룹 관리 정보와 스터디 그룹 정보 연결 --
		newStudygroupManagement.setInfoSeq(newStudygroupInfo.getSeq());
		studygroupManagementDao.updateInfoSeq(newStudygroupManagement);
		
		// -- 모집글의 모집 분야 등록 --
		resetStudygroupPosition(newStudygroupInfo, false);
		
		// -- 모집글의 기술 스택 등록 --
		resetStudygroupStack(newStudygroupInfo, false);
	}
}
