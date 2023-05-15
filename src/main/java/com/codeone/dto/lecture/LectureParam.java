package com.codeone.dto.lecture;

import java.io.Serializable;

import lombok.Data;

@Data
public class LectureParam implements Serializable {
	
	private String category;
	private String search;		// 검색어
	private int pageNumber;		// [1][2][3]
	
	private int start;
	private int dataCount;	

}
