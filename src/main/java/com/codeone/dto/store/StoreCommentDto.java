package com.codeone.dto.store;

import java.io.Serializable;

import lombok.Data;

//	중고거래 댓글 db
//	CREATE TABLE itemcomment (
//	seq INT AUTO_INCREMENT PRIMARY KEY,
//	itemseq int NOT NULL,  		
//	id VARCHAR(100) NOT NULL,   		
//	content varchar(1000) NOT NULL,
//	wdate DATETIME NOT NULL,
//	delflg int not null	
//	);

@Data
public class StoreCommentDto implements Serializable {
	
	private int seq;				// 댓글 seq
	private int itemseq;			// 중고거래 글의 seq
	private String id;				// 작성자	
	private String content;			// 댓글 내용
	private String wdate;			// 댓글 작성일
	private String updatewdate;		// 댓글 수정일
	private int delflg;				// 삭제여부
}
