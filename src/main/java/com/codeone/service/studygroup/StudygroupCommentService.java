package com.codeone.service.studygroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codeone.dao.studygroup.StudygroupCommentDao;
import com.codeone.dao.studygroup.StudygroupManagementDao;
import com.codeone.dto.studygroup.StudygroupCommentDto;
import com.codeone.dto.studygroup.StudygroupManagementDto;
import com.codeone.exception.DeletedStudygroupException;
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
}
