package com.codeone.dto.store;

import java.io.Serializable;

import lombok.Data;

@Data
public class StoreParam implements Serializable {
	
	private String choice;		// 제목/내용/작성자 카테고리
	private String search;		// 검색어
	private int pageNumber;		// [1][2][3]
	
	private int start;
	private int dataCount;	
	
	
	
}
