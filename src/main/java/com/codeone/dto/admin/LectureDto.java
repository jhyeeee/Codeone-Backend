package com.codeone.dto.admin;

import java.io.Serializable;

import lombok.Data;

// lecture db
//CREATE TABLE lecture (
//		seq INT AUTO_INCREMENT PRIMARY KEY, 	
//		id VARCHAR(100) NOT NULL,  		
//		category int NOT NULL, 
//	    title varchar(1000) NOT NULL,
//		content VARCHAR(4000) NOT NULL,  	
//		price int NOT NULL,  		
//		wdate DATETIME NOT NULL,
//	 	likecount int,
//	    zoomUrl varchar(1000) NOT NULL,
//	    zoomPwd varchar(100) NOT NULL,
//	    filename VARCHAR(100) ,
//	    newfilename VARCHAR(500) ,
//		delflg int not null	
//	);

@Data
public class LectureDto implements Serializable {

	private int seq;				// 강의번호
	private String id;				// 관리자만 쓸수있음. 관리자 id
	private String category;		// 강의 기술카테고리
	private String title;			// 강의글 제목
	private String content;			// 강의소개
	private int price;				// 가격
	private String wdate;			// 작성일
	private int likecount;			// 좋아요 갯수
	private String zoomurl;			// 수업 url
	private String zoompwd;			// 수업 pwd
	private String filename;		// 강의 대표이미지 original 파일명
	private String newfilename;		// 서버에 저장할 파일명
	private int delflg;				// 삭제여부
}
