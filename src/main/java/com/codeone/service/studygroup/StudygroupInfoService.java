package com.codeone.service.studygroup;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codeone.command.studygroup.StudygroupDeleteCommand;
import com.codeone.command.studygroup.StudygroupInfoCommand;
import com.codeone.command.studygroup.StudygroupListCommand;
import com.codeone.command.studygroup.StudygroupUpdateCommand;
import com.codeone.dao.studygroup.StudygroupPositionDao;
import com.codeone.dao.studygroup.StudygroupStackDao;
import com.codeone.dto.studygroup.StudygroupInfoDto;
import com.codeone.dto.studygroup.StudygroupListDto;
import com.codeone.dto.studygroup.StudygroupManagementDto;
import com.codeone.dto.studygroup.StudygroupPositionDto;
import com.codeone.dto.studygroup.StudygroupStackDto;
import com.codeone.enumVariable.VotingType;
import com.codeone.exception.NotPermissionToModifyException;

@Service
@Transactional
public class StudygroupInfoService extends StudygroupService {
	@Autowired
	StudygroupPositionDao studygroupPositionDao;
	@Autowired
	StudygroupStackDao studygroupStackDao;
	
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
		StudygroupManagementDto studygroupManagement = checkPermissionToModify(seq, requestMemberSeq);
		if(studygroupManagement == null) {
			// 존재 하지 않는 seq 번호를 전달했거나 삭제할 모집글의 작성자가 아닐 경우
			throw new NotPermissionToModifyException();
		} else if(studygroupManagement.getIsClosed()) {
			// 이미 삭제 처리된 모집 글에 다시 삭제 요청을 했을 경우
			return true;
		}
		
		// 삭제를 요청한 스터디 그룹을 삭제할 수 있는지 여부 확인
		if(isModifiableStudygroup(studygroupManagement.getInfoSeq(), VotingType.DELETE)) {
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
		StudygroupManagementDto studygroupManagement = checkPermissionToModify(seq, requestMemberSeq);
		if(studygroupManagement == null) {
			// 존재 하지 않는 seq 번호를 전달했거나 수정할 스터디 그룹의 팀장이 아닐 경우
			throw new NotPermissionToModifyException();
		}
		
		// 수정을 요청한 스터디 그룹을 수정할 수 있는지 여부 확인
		if(isModifiableStudygroup(studygroupManagement.getInfoSeq(), VotingType.UPDATE)) {
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

	public List<StudygroupListDto> getStudygroupList(StudygroupListCommand studygroupListCommand) {
		return studygroupManagementDao.selectAllStudygroupList(studygroupListCommand);
	}
}
