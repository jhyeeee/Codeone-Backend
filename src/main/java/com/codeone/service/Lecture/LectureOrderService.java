package com.codeone.service.Lecture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codeone.dao.lecture.LectureOrderDao;
import com.codeone.dto.lecture.LectureDto;
import com.codeone.dto.lecture.LectureOrderDto;

@Service
@Transactional
public class LectureOrderService {
	
	@Autowired
	LectureOrderDao dao;
	
	public boolean orderLecture(LectureOrderDto dto) {
		int n = dao.orderLecture(dto);
		return n>0?true:false;
	}
	
	// 주문한사람 id 주문한 강의 seq 넣어주고 강의정보 불러오기
	public LectureDto getOrderLecture(LectureOrderDto dto) {
		return dao.getOrderLecture(dto);
	}
	
	public boolean checkOrder(LectureOrderDto dto) {
		int n = dao.checkOrder(dto);
		return n>0?true:false;
	}
	
	public int checkPaidCount(int seq) {
		return dao.checkPaidCount(seq);
	}

}
