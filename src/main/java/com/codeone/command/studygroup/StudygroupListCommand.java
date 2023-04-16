package com.codeone.command.studygroup;

import lombok.Data;

@Data
public class StudygroupListCommand {
	private int[] technologyStack;	// 필터 : 기술 스택
	private int recruitmentType;	// 필터 : 모집 구분
	private String term;			// 필터 : 검색어
	private boolean isOpened;		// 필터 : 모집 중만 보기(true=>모집중인 스터디 그룹, false=>모든 스터디 그룹)
	
	private int depth;				// 페이지 번호
	private int start;				// 검색 범위 (start부터)
	private int end;				// 검색 범위 (end전까지)
	
	public void setIsOpened(boolean isOpened) {
		this.isOpened = isOpened;
	}
	
	public boolean getIsOpened() {
		return isOpened;
	}
}
