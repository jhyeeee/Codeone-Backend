package com.codeone.dto.studygroup;

import java.time.LocalDate;

import lombok.Data;

@Data
public class StudygroupListDto {
	private int recruitmentType;								// 모집 분야
	
	private LocalDate deadlineForRecruitment;					// 모집 마감 날짜
	private String title;										// 제목
	
	private int[] recruitmentPart;								// 모집 분야
	private int[] technologyStack;								// 기술 스택
	
	private StudygroupDetailUserDto studygroupDetailUserDto;	// 스터디 그룹 팀장 정보
	
	private boolean isClosed;									// 모집 마감 여부
	private int viewAmount;										// 조회수
	private int commentAmount;									// 댓글수
	
	public StudygroupListDto() {
		studygroupDetailUserDto = new StudygroupDetailUserDto();
	}
	
	public void setRecruitmentPart(String recruitmentPart) {
		String[] recruitmentPart_stringArray = recruitmentPart.split(",");
		this.recruitmentPart = new int[recruitmentPart_stringArray.length];
		
		for(int i=0; i<recruitmentPart_stringArray.length; i++) {
			this.recruitmentPart[i] = Integer.parseInt(recruitmentPart_stringArray[i]);
		}
	}
	
	public void setTechnologyStack(String technologyStack) {
		String[] technologyStack_stringArray = technologyStack.split(",");
		this.technologyStack = new int[technologyStack_stringArray.length];
		
		for(int i=0; i<technologyStack_stringArray.length; i++) {
			this.technologyStack[i] = Integer.parseInt(technologyStack_stringArray[i]);
		}
	}
	
	public void setId(String id) {
		studygroupDetailUserDto.setId(id);
	}
	
	public void setFilename(String filemane) {
		studygroupDetailUserDto.setFilename(filemane);
	}
	
	public void setIsClosed(boolean isClosed) {
		this.isClosed = isClosed;
	}
	
	public boolean getIsClosed() {
		return isClosed;
	}
}
