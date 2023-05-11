package com.codeone.dto.job;

import java.io.Serializable;

public class ComPagingDto implements Serializable{

	private String choice;	// 제목/내용/작성자
	private String search;	// 검색어
	private int pageNumber; // [1][2][3]
	
	private int start;
	private int end;
	

	public ComPagingDto() {
	}

	public ComPagingDto(String choice, String search, int pageNumber, int start, int end) {
		super();
		this.choice = choice;
		this.search = search;
		this.pageNumber = pageNumber;
		this.start = start;
		this.end = end;
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	@Override
	public String toString() {
		return "BbsParam [choice=" + choice + ", search=" + search + ", pageNumber=" + pageNumber + ", start=" + start
				+ ", end=" + end + "]";
	}
	
}


//package com.codeone.dto.job;
//
//import java.io.Serializable;
//
//public class ComPagingDto implements Serializable{
//
//	private int pageNo;		// 페이지번호 (controller에서 처리)
//	private int viewCount;	// 보여줄갯수
//	private int limit;  	//시작 limit index
//	
//
//	public ComPagingDto() {
//	}
//
//	public ComPagingDto(int pageNo, int viewCount, int limit) {
//		super();
//		this.pageNo = pageNo;
//		this.viewCount = viewCount;
//		this.limit = limit;
//	}
//
//
//	public int getPageNo() {
//		return pageNo;
//	}
//
//
//	public void setPageNo(int pageNo) {
//		this.pageNo = pageNo;
//	}
//
//
//	public int getViewCount() {
//		return viewCount;
//	}
//
//
//	public void setViewCount(int viewCount) {
//		this.viewCount = viewCount;
//	}
//
//
//	public int getLimit() {
//		return limit;
//	}
//
//
//	public void setLimit(int limit) {
//		this.limit = limit;
//	}
//	
//	
//	
//}