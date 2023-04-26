package com.codeone.dao.lecture;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.codeone.dto.lecture.LectureDto;

@Mapper
@Repository
public interface LectureDao {

	int writeLecture(LectureDto dto);
	
	int updateLecture(LectureDto dto);
	
	int deleteLecture(int seq);
	
	// 강의글 목록
	List<LectureDto> getLectureList();
	
	// 강의글 한개
	LectureDto getLectureOne(int seq);
	
}
