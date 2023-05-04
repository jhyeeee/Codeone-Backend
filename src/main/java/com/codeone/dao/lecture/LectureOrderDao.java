package com.codeone.dao.lecture;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.codeone.dto.lecture.LectureDto;
import com.codeone.dto.lecture.LectureOrderDto;

@Mapper
@Repository
public interface LectureOrderDao {
	
	int orderLecture(LectureOrderDto dto);
	
	LectureDto getOrderLecture(LectureOrderDto dto);
	
	// 결제한 상품인지 확인
	int checkOrder(LectureOrderDto dto);

}
