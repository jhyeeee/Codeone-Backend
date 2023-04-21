package com.codeone.dto.studygroup;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class StudygroupDetailDto implements Serializable {
	private boolean isVisible;					// 공개, 비공개
	private boolean isClosed;					// 모집 마감
	
	private StudygroupDetailUserDto studygroupDetailUser;	// 스터디 그룹 팀장 정보
	
	private String title;						// 모집글 제목
	private String contents;					// 모집글 내용
	private int recruitmentType;				// 모집 구분
	private int wayOfProceeding;				// 진행 방식
	private int numberOfRecruits;				// 모집 인원
	private LocalDate startDate;				// 진행 기간(시작 날짜)
	private LocalDate endDate;					// 진행 기간(종료 날짜)
	private LocalDate deadlineForRecruitment;	// 모집 마감 날짜
	private LocalDateTime regdate;				// 스터디 그룹 생성 날짜
	
	private int[] recruitmentPart;				// 모집 분야
	private int[] technologyStack;				// 기술 스택
	
	private int likeAmount;						// 관심 그룹으로 체크한 수
	private int readAmount;						// 이 글을 읽은 회원의 수
}
