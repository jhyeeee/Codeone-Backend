package com.codeone.dto.studygroup;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class StudygroupManagementDto {
	private int seq;				// 스터디 그룹 번호
	private int memberSeq;			// 스터디 그룹 팀장 번호
	private int infoSeq;			// 스터디 그룹 정보 번호
	private boolean isVisible;		// 공개, 비공개
	private boolean isClosed;		// 모집 마감
	private boolean isDeleted;		// 삭제 여부
	private LocalDateTime regdate;	// 스터디 그룹 생성 날짜
	
	public void setIsVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
	
	public boolean getIsVisible() {
		return isVisible;
	}
	
	
}
