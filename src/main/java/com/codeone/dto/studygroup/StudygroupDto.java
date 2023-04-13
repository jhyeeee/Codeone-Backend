package com.codeone.dto.studygroup;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class StudygroupDto {
	private int seq;							// 스터디그룹 식별값
	private int memberSeq;						// 모집글 작성 회원 번호(스터디장)
	private String title;						// 모집글 제목
	private String name;						// 스터디그룹 명
	private String contents;					// 모집글 내용
	private int recruitmentType;				// 모집 구분
	private int wayOfProceeding;				// 진행 방식
	private int numberOfRecruits;				// 모집 인원
	private LocalDate startDate;				// 진행 기간(시작 날짜)
	private LocalDate endDate;					// 진행 기간(종료 날짜)
	private LocalDate deadlineForRecruitment;	// 모집 마감 날짜
	private boolean isVisible;					// 공개, 비공개
	private boolean isClosed;					// 모집 마감
	private boolean isDeleted;					// 삭제 여부
	private LocalDateTime regdate;				// 모집글 등록일
	
	private int[] recruitmentPart;				// 모집 분야
	private int[] technologyStack;				// 기술 스택
}
