package com.codeone.dto.blog;

import java.io.Serializable;

import lombok.Data;

@Data
public class BlogParam implements Serializable {

	
//	private String category;
//	private String search;		// 검색어
	private String userEmail;
	private int pageNumber;		// [1][2][3]
	
	private int start;
	private int dataCount;	
}
