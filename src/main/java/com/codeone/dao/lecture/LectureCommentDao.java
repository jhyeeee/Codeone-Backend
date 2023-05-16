package com.codeone.dao.lecture;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.codeone.dto.lecture.LectureCommentDto;
import com.codeone.dto.lecture.LectureCommentParam;
import com.codeone.dto.lecture.LectureCommentUserDto;
import com.codeone.dto.store.StoreCommentDto;
import com.codeone.dto.store.StoreCommentParam;
import com.codeone.dto.user.UserDto;

@Mapper
@Repository
public interface LectureCommentDao {
	
	int writeCommentLecture(LectureCommentDto dto); 
	
	// 댓글목록 param넣어줌
	List<LectureCommentUserDto> getLectureCommentList(LectureCommentParam param);

	void updateLectureComment(LectureCommentDto dto);

	void deleteLectureComment(int seq);

	// 강의글에 잇는 댓글갯수
	int getLectureCommentCount(int lectureseq);
	
	List<UserDto> getCommentUser(LectureCommentDto dto);

}
