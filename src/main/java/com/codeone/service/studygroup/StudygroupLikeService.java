package com.codeone.service.studygroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codeone.dao.studygroup.StudygroupLikeDao;
import com.codeone.dao.studygroup.StudygroupManagementDao;
import com.codeone.dto.studygroup.StudygroupLikeDto;
import com.codeone.dto.studygroup.StudygroupManagementDto;
import com.codeone.exception.DeletedStudygroupException;

@Service
@Transactional
public class StudygroupLikeService {
	@Autowired
	StudygroupLikeDao studygroupLikeDao;
	@Autowired
	StudygroupManagementDao studygroupManagementDao;
	
	public void toggleLike(StudygroupLikeDto studygroupLike) throws DeletedStudygroupException {
		// 기존에 좋아요 한 스터디 그룹인지 확인
		StudygroupLikeDto oldStudygroupLike = studygroupLikeDao.selectOne(studygroupLike);
		
		StudygroupManagementDto studygroupManagement = studygroupManagementDao.selectOne(studygroupLike.getStudygroupSeq());
		if(studygroupManagement.getIsDeleted()) {
			// 삭제된 스터디 그룹일 경우
			throw new DeletedStudygroupException();
		}
		
		if(oldStudygroupLike == null) {
			// 좋아요 하지 않은 스터디 그룹일 경우
			studygroupManagementDao.increaseLike(studygroupLike.getStudygroupSeq());
			studygroupLikeDao.insert(studygroupLike);
		} else {
			// 좋아요 한 스터디 그룹일 경우
			studygroupManagementDao.decreaseLike(studygroupLike.getStudygroupSeq());
			studygroupLikeDao.delete(studygroupLike);
		}
	}
}
