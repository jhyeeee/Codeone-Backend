package com.codeone.service.studygroup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codeone.command.studygroup.StudygroupCommentCommand;
import com.codeone.dao.studygroup.StudygroupCommentDao;
import com.codeone.dao.studygroup.StudygroupManagementDao;
import com.codeone.dto.studygroup.StudygroupCommentDto;
import com.codeone.dto.studygroup.StudygroupCommentListDto;
import com.codeone.dto.studygroup.StudygroupManagementDto;
import com.codeone.exception.DeletedStudygroupException;
import com.codeone.exception.NotFoundCommentException;
import com.codeone.exception.NotPermissionToModifyException;
import com.codeone.exception.StudygroupNotFoundException;

@Service
@Transactional
public class StudygroupCommentService {
	@Autowired
	StudygroupManagementDao studygroupManagementDao;
	@Autowired
	StudygroupCommentDao studygroupCommentDao;
	
	public void writeComment(StudygroupCommentDto studygroupComment) throws StudygroupNotFoundException, DeletedStudygroupException {
		StudygroupManagementDto studygroupManagement = studygroupManagementDao.selectOne(studygroupComment.getStudygroupSeq());
		if(studygroupManagement == null) {
			// 존재 하지 않는 스터디 그룹에 댓글을 달려 했다면
			throw new StudygroupNotFoundException();
		} else if(studygroupManagement.getIsDeleted()) {
			// 삭제된 스터디 그룹에 댓글을 달려 했다면
			throw new DeletedStudygroupException();
		} else if(studygroupManagement.getMemberSeq() == studygroupComment.getMemberSeq()) {
			// 본인이 쓴 모집 글에 댓글을 달려 했다면
			throw new IllegalArgumentException();
		}
		
		// 댓글 작성
		studygroupCommentDao.writeComment(studygroupComment);
		
		// 댓글수 증가
		studygroupManagementDao.increaseCommentAmount(studygroupComment.getStudygroupSeq());
	}

	public void updateComment(StudygroupCommentDto studygroupComment) throws NotFoundCommentException, NotPermissionToModifyException {
		StudygroupCommentDto oldStudygroupCommentDto = studygroupCommentDao.selectOneBySeq(studygroupComment.getSeq());
		
		if(oldStudygroupCommentDto == null) {
			// 존재 하지 않는 댓글을 수정하려는 경우
			throw new NotFoundCommentException();
		} else if(oldStudygroupCommentDto.getMemberSeq() != studygroupComment.getMemberSeq()) {
			// 다른 사람이 작성한 댓글을 수정하려는 경우
			throw new NotPermissionToModifyException();
		}
		
		// 댓글 수정
		studygroupCommentDao.updateComment(studygroupComment);
	}
	
	public void deleteComment(StudygroupCommentDto studygroupComment) throws NotFoundCommentException, NotPermissionToModifyException {
		StudygroupCommentDto oldStudygroupCommentDto = studygroupCommentDao.selectOneBySeq(studygroupComment.getSeq());
		
		if(oldStudygroupCommentDto == null) {
			// 존재 하지 않는 댓글을 삭제하려는 경우
			throw new NotFoundCommentException();
		} else if(oldStudygroupCommentDto.getMemberSeq() != studygroupComment.getMemberSeq()) {
			// 다른 사람이 작성한 댓글을 삭제하려는 경우
			throw new NotPermissionToModifyException();
		}
		
		// 댓글 삭제
		studygroupCommentDao.deleteComment(studygroupComment.getSeq());
		
		// 댓글수 감소
		studygroupManagementDao.decreaseCommentAmount(oldStudygroupCommentDto.getStudygroupSeq());
	}

	public List<StudygroupCommentListDto> getList(StudygroupCommentCommand studygroupCommentCommand) {
		return studygroupCommentDao.getList(studygroupCommentCommand);
	}
}
