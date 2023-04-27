package com.codeone.dto.store;

import java.io.Serializable;

import lombok.Data;

@Data
public class StoreCommentParam implements Serializable{
	
	private int itemseq;	
	private int pageNumber;		// 넘어온 페이지 넘버 [1][2][3]
	
	private int start;			// 보여줄 데이터 시작부분. 0번째부터
	private int dataCount;			// 몇개보여줄건지 설정
}
