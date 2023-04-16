package com.codeone.service.studygroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codeone.dao.studygroup.StudygroupLikeDao;
import com.codeone.dao.studygroup.StudygroupPositionDao;
import com.codeone.dao.studygroup.StudygroupStackDao;
import com.codeone.dao.user.UserDao;
import com.codeone.dto.studygroup.StudygroupDetailDto;
import com.codeone.dto.studygroup.StudygroupDetailUserDto;
import com.codeone.dto.studygroup.StudygroupInfoDto;
import com.codeone.dto.studygroup.StudygroupManagementDto;
import com.codeone.dto.user.UserDto;
import com.codeone.enumVariable.VotingType;
import com.codeone.etc.ToUtils;
import com.codeone.exception.DeletedStudygroupException;
import com.codeone.exception.StudygroupNotFoundException;

@Service
@Transactional
public class StudygroupDetailService extends StudygroupService {
	@Autowired
	UserDao userDao;
	@Autowired
	StudygroupPositionDao studygroupPositionDao;
	@Autowired
	StudygroupStackDao studygroupStackDao;
	@Autowired
	StudygroupLikeDao studygroupLikeDao;
	
	public boolean checkIsModifiable(int seq, int memberSeq, VotingType votingType) {
		// 수정을 요청한 사용자가 해당 스터디 그룹을 수정할 수 있는 권한이 있는지 확인
		StudygroupManagementDto studygroupManagement = checkPermissionToModify(seq, memberSeq);
		if(studygroupManagement == null) {
			// 존재 하지 않는 seq 번호를 전달했거나 수정할 스터디 그룹의 팀장이 아닐 경우
			return false;
		}
		
		return isModifiableStudygroup(studygroupManagement.getInfoSeq(), votingType);
	}

	public StudygroupDetailDto getStudygroupInfo(int seq) throws StudygroupNotFoundException, DeletedStudygroupException {
		// 스터디 그룹 관리 정보 조회
		StudygroupManagementDto studygroupManagement = studygroupManagementDao.selectOne(seq);
		
		if(studygroupManagement == null) {
			throw new StudygroupNotFoundException();
		} else if(studygroupManagement.getIsDeleted()) {
			throw new DeletedStudygroupException();
		}
		
		// 스터디 그룹 팀장 정보 조회
		UserDto teamReaderInfo = userDao.selectOneBySeq(studygroupManagement.getMemberSeq());
		StudygroupDetailUserDto studygroupDetailUser = teamReaderInfo.toStudygroupDetailUserDto();
		
		// 스터디 그룹 정보 조회
		StudygroupInfoDto studygroupInfo = studygroupInfoDao.selectOneBySeq(studygroupManagement.getInfoSeq());
		
		// 모집 분야 조회
		int[] recruitmentPart = ToUtils.ListToArray(studygroupPositionDao.selectAll(seq));
		
		// 기술 스택 조회
		int[] technologyStack = ToUtils.ListToArray(studygroupStackDao.selectAll(seq));
		
		// 관심 그룹 수 조회
		int likeAmount = studygroupLikeDao.getAmountByStudygroupSeq(seq);
		
		// 스터디 그룹 상세 정보 생성
		StudygroupDetailDto studygroupDetail = new StudygroupDetailDto();
		studygroupDetail.setTitle(studygroupInfo.getTitle());									// 제목
		studygroupDetail.setStudygroupDetailUser(studygroupDetailUser);							// 스터디 그룹 팀장 정보
		studygroupDetail.setRegdate(studygroupManagement.getRegdate());							// 스터디 그룹 생성 날짜
		
		studygroupDetail.setRecruitmentType(studygroupInfo.getRecruitmentType());				// 모집 구분
		studygroupDetail.setWayOfProceeding(studygroupInfo.getWayOfProceeding());				// 진행 방식
		studygroupDetail.setNumberOfRecruits(studygroupInfo.getNumberOfRecruits());				// 모집 인원
		studygroupDetail.setStartDate(studygroupInfo.getStartDate());							// 진행 기간(시작 날짜)
		studygroupDetail.setEndDate(studygroupInfo.getEndDate());								// 진행 기간(종료 날짜)
		studygroupDetail.setDeadlineForRecruitment(studygroupInfo.getDeadlineForRecruitment());	// 모집 마감 날짜
		
		studygroupDetail.setRecruitmentPart(recruitmentPart);									// 모집 분야
		studygroupDetail.setTechnologyStack(technologyStack);									// 기술 스택
		
		studygroupDetail.setContents(studygroupInfo.getContents());								// 내용
		
		studygroupDetail.setLikeAmount(likeAmount);												// 관심 그룹 수
		
		return studygroupDetail;
	}
}
