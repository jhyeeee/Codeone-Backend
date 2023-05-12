package com.codeone.service.Lecture;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codeone.dao.lecture.LectureCommentDao;
import com.codeone.dto.lecture.LectureCommentDto;
import com.codeone.dto.lecture.LectureCommentParam;
import com.codeone.dto.lecture.LectureCommentUserDto;
import com.codeone.dto.store.StoreCommentDto;
import com.codeone.dto.store.StoreCommentParam;
import com.codeone.dto.user.UserDto;

@Service
@Transactional
public class LectureCommentService {

	@Autowired
	LectureCommentDao dao;

	public boolean writeCommentLecture(LectureCommentDto dto) {
		int n = dao.writeCommentLecture(dto);
		return n > 0 ? true : false;
	}

	// 댓글목록
//	public List<LectureCommentDto> getLectureCommentList(LectureCommentParam param) {
//		return dao.getLectureCommentList(param);
//	}
	
	// 댓글목록 + 유저프로필사진
	public List<LectureCommentUserDto> getLectureCommentList(LectureCommentParam param) {
		return dao.getLectureCommentList(param);
	}
	

	// 댓글의 총수
	public int getLectureCommentCount(int lectureseq) {
		return dao.getLectureCommentCount(lectureseq);
	}

	// 댓글 수정
	public void updateLectureComment(LectureCommentDto dto) {
		dao.updateLectureComment(dto);
	}

	// 댓글 삭제
	public void deleteLectureComment(int seq) {
		dao.deleteLectureComment(seq);
	}
	
	// 댓글단 유저정보
	public List<UserDto> getCommentUser(LectureCommentDto dto) {
		return dao.getCommentUser(dto);
	}

}
