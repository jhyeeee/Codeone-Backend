package com.codeone.dto.lecture;

import java.io.Serializable;

import lombok.Data;


// 수강평 db
//CREATE TABLE lectureComment (
//		seq INT AUTO_INCREMENT PRIMARY KEY,
//		lectureseq int NOT NULL,  		
//		id VARCHAR(100) NOT NULL,   		
//		content varchar(1000) NOT NULL,
//		starrate int 
//	 	wdate DATETIME NOT NULL,
//	    updatewdate DATETIME NOT NULL,
//		delflg int not null	
//	);

@Data
public class LectureCommentDto implements Serializable {
	private int seq;					// 댓글 seq
	private int lectureseq;				// 강의 seq
	private String id;					// userId
	private String content;				// 댓글내용
	private int starrate;				// 별점 0 - 5점
	private String wdate;				// 등록일
	private String updatewdate;			// 수정일
	private int delflg; 				// 삭제여부 0이 기본
}
