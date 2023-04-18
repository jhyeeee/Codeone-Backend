package com.codeone.dto.job;

import java.io.Serializable;

public class ComPagingDto implements Serializable{

	private int pageNo;		// 페이지번호 (controller에서 처리)
	private int viewCount;	// 보여줄갯수
	private int limit;  	//시작 limit index
	

	public ComPagingDto() {
	}

	public ComPagingDto(int pageNo, int viewCount, int limit) {
		super();
		this.pageNo = pageNo;
		this.viewCount = viewCount;
		this.limit = limit;
	}


	public int getPageNo() {
		return pageNo;
	}


	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}


	public int getViewCount() {
		return viewCount;
	}


	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}


	public int getLimit() {
		return limit;
	}


	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	
	
}