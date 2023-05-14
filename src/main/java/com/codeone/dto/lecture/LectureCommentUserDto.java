package com.codeone.dto.lecture;

import java.io.Serializable;

import lombok.Data;

// 댓글과 유저정보를 같이 불러오기위한 dto
@Data
public class LectureCommentUserDto implements Serializable{
	
	private int seq;					// 댓글 seq
	private int lectureseq;				// 강의 seq
	private String id;					// userId
	private String content;				// 댓글내용
	private int starrate;				// 별점추가
	private String wdate;				// 등록일
	private String updatewdate;			// 수정일
	private int delflg; 				// 삭제여부 0이 기본
	private String filename;
	private String newfilename;
	
}
