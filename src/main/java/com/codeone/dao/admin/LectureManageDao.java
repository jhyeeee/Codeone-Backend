package com.codeone.dao.admin;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.codeone.dto.lecture.LectureDto;
import com.codeone.dto.lecture.LectureLikeDto;
import com.codeone.dto.lecture.LectureParam;

@Mapper
@Repository
public interface LectureManageDao {

	int writeLecture(LectureDto dto);

	int updateLecture(LectureDto dto);

	int deleteLecture(int seq);

	// 강의글 목록
	List<LectureDto> getLectureList(LectureParam param);
	List<LectureDto> getMngLectureList();

	// 강의글 한개
	LectureDto getLectureOne(int seq);

	// 좋아요
	int likeLecture(LectureLikeDto dto);

	// 좋아요중인지 확인
	int checkLike(LectureLikeDto dto);

	// 좋아요 취소
	int cancelLike(LectureLikeDto dto);

	// 좋아요 카운트
	int countLike(int seq);

	// 좋아요 취소카운트
	int countCancelLike(int seq);
	
	// 좋아요한 seq 리스트
	List<Integer> getlikeList(String id);
	
	// 관리자 강의목록 숨김/노출
	boolean showHideLecture(int seq, int delflg);

}
