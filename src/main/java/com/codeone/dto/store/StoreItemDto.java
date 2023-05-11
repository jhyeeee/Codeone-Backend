package com.codeone.dto.store;

import java.io.Serializable;

import lombok.Data;

// store item db
/*
CREATE TABLE item (
		seq INT AUTO_INCREMENT PRIMARY KEY, 	
		id VARCHAR(50) NOT NULL,  		
		title VARCHAR(200) NOT NULL,  		
		content VARCHAR(4000) NOT NULL,  	
		price int NOT NULL,  		
		location VARCHAR(400) NOT NULL, 			
	    status int NOT NULL,
		wdate DATETIME NOT NULL,
	 	likecount int,
	    filename VARCHAR(100) ,
	    newfilename VARCHAR(500) ,
		delflg int not null,
		readcount int				
	);
*/

@Data
public class StoreItemDto implements Serializable {

	// 중고인지 새거인지 여부 추가 itemcondition 추가함
	private int seq;
	private String id;				// 작성자 id
	private String title;			// 제목
	private String content;			// 내용
	private int price;				// 가격
	private String location;		// 판매위치
	private String itemcondition;	// 중고 "old", 새제품 "new"
	private String status;			// 판매여부
	private String wdate;			// 작성
	private int likecount;			// 좋아요 수
	private String filename;		// 사진
	private String newfilename;	
	private int delflg;				// 삭제여부 삭제된글:1
	private int readcount;			// 조회수 새로추가함
	
	
	
	
}


