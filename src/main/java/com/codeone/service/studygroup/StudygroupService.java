package com.codeone.service.studygroup;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codeone.dao.studygroup.StudygroupDao;
import com.codeone.dao.studygroup.StudygroupMemberDao;
import com.codeone.dao.studygroup.StudygroupMemberVotingDao;
import com.codeone.dao.studygroup.StudygroupPositionDao;
import com.codeone.dao.studygroup.StudygroupStackDao;
import com.codeone.dto.studygroup.StudygroupDto;
import com.codeone.dto.studygroup.StudygroupMemberVotingDto;
import com.codeone.dto.studygroup.StudygroupPositionDto;
import com.codeone.dto.studygroup.StudygroupStackDto;
import com.codeone.enumVariable.VotingType;
import com.codeone.exception.AlreadyStudygroupStartedException;
import com.codeone.exception.NotPermissionToModifyException;

@Service
@Transactional
public class StudygroupService {
	@Autowired
	StudygroupDao studygroupDao;
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
	private boolean checkStudygroupWithPermissionToModify(int seq, int requestMemberSeq) {
		StudygroupDto studygroup = studygroupDao.selectOneBySeq(seq);
		
		return studygroup != null && studygroup.getMemberSeq() == requestMemberSeq;
	}
	
	/**
	 * 스터디그룹의 정보를 수정할 수 있는지 여부 확인
	 * 
	 * @param studygroup 수정할 스터디그룹 정보
	 * @param votingType 수정 유형
	 * @return 수정 가능하다면 true, 불가능하다면 false
	 */
	private boolean isModifiableStudygroup(StudygroupDto studygroup, VotingType votingType) {
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
	private void resetStudygroupPosition(StudygroupDto studygroup) {
		// 모집 분야를 초기화할 스터디 그룹의 번호
		int requestStudygroupSeq = studygroup.getSeq();
		
		// 모집 분야 삭제
		studygroupPositionDao.delete(requestStudygroupSeq);
		
		// -- 모집 분야 등록 --
		int[] recruitementPart = studygroup.getRecruitmentPart();
		
		List<StudygroupPositionDto> studygroupPositionList = new ArrayList<>(recruitementPart.length);
		for(int rp : recruitementPart) {
			StudygroupPositionDto sgpd = new StudygroupPositionDto();
			sgpd.setStudygroupSeq(requestStudygroupSeq);
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
	private void resetStudygroupStack(StudygroupDto studygroup) {
		// 모집 분야를 초기화할 스터디 그룹의 번호
		int requestStudygroupSeq = studygroup.getSeq();
		
		// 모집 분야 삭제
		studygroupStackDao.delete(requestStudygroupSeq);
		
		// -- 기술 스택 등록 --
		int[] technologyStack = studygroup.getTechnologyStack();
		
		List<StudygroupStackDto> StudygroupStackList = new ArrayList<>(technologyStack.length);
		for(int ts : technologyStack) {
			StudygroupStackDto ssd = new StudygroupStackDto();
			ssd.setStudygroupSeq(requestStudygroupSeq);
			ssd.setStackSeq(ts);
			
			StudygroupStackList.add(ssd);
		}
		
		studygroupStackDao.insert(StudygroupStackList);
		// -- 기술 스택 등록 --
	}
	
	public boolean deleteStudygroupRecruitment(StudygroupDto studygroup) throws AlreadyStudygroupStartedException {
		// 삭제를 요청한 스터디그룹 모집 글 번호
		int seq = studygroup.getSeq();
		// 삭제를 요청한 사용자의 번호
		int requestMemberSeq = studygroup.getMemberSeq();
		// 삭제를 요청한 날짜
		LocalDate now = LocalDate.now();
		
		// 삭제할 모집글 조회
		StudygroupDto oldStudygroup = studygroupDao.selectOneBySeq(seq);
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
	
	/**
	 * 스터디 그룹 정보 수정<br>
	 * 스터디 그룹 정보를 수정했다면 true<br>
	 * 스터디 그룹 정보를 수정하지 못했다면 false<br>
	 * 스터디 그룹이 시작되면 스터디 그룹은 내 소유가 아닌 우리의 소유이기 때문에 수정 시 과반수 이상의 동의가 필요함<br>
	 * 
	 * @param studygroup 수정할 스터디 그룹 정보
	 * @return 스터디 그룹 정보를 수정했다면 true
	 * @throws NotPermissionToModifyException 스터디 그룹을 수정할 권한이 없을 경우
	 */
	public boolean updateStudygroupRecruitment(StudygroupDto studygroup) throws NotPermissionToModifyException {
		// 수정을 요청한 스터디 그룹 모집 글 번호
		int requestStudygroupSeq = studygroup.getSeq();
		// 수정을 요청한 사용자의 번호
		int requestMemberSeq = studygroup.getMemberSeq();
		
		// 수정을 요청한 사용자가 해당 스터디 그룹을 수정할 수 있는 권한이 있는지 확인
		boolean isPermission = checkStudygroupWithPermissionToModify(requestStudygroupSeq, requestMemberSeq);
		if(!isPermission) {
			// 수정할 수 있는 권한이 없다면
			throw new NotPermissionToModifyException();
		}
		
		// 수정을 요청한 스터디 그룹을 수정할 수 있는지 여부 확인
		if(isModifiableStudygroup(studygroup, VotingType.UPDATE)) {
			// 수정을 요청한 스터디 그룹을 수정할 수 있다면
			studygroupDao.updateStudygroupRecruitment(studygroup);
			
			// -- 스터디 그룹의 모집 분야 초기화 --
			resetStudygroupPosition(studygroup);
			
			// -- 스터디 그룹의 기술 스택 초기화 --
			resetStudygroupStack(studygroup);
			
			return true;
		} else {
			// 수정을 요청한 스터디 그룹을 수정할 수 없다면
			return false;
		}
	}
	
	public void writeStudygroupRecruitment(StudygroupDto studygroup) {
		// 모집글 작성
		studygroupDao.writeStudygroupRecruitment(studygroup);
		
		// 작성한 모집글 번호
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
