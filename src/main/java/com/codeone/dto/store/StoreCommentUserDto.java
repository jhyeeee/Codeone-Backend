package com.codeone.dto.store;

import java.io.Serializable;

import lombok.Data;

// 프로필사진 같이 내보내 줄때 사용할 dto
@Data
public class StoreCommentUserDto implements Serializable{
	
	private int seq;				// 댓글 seq
	private int itemseq;			// 중고거래 글의 seq
	private String id;				// 작성자
	private String content;			// 댓글 내용
	private String wdate;			// 댓글 작성일
	private int delflg;				// 삭제여부
	private String filename;
	private String newfilename;

}
