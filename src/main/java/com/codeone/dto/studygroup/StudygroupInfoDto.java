package com.codeone.dto.studygroup;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class StudygroupInfoDto implements Serializable {
	private int seq;							// 스터디그룹 정보 번호
	private int managementSeq;					// 이 정보를 사용하는 스터디 그룹 번호
	private String title;						// 모집글 제목
	private String name;						// 스터디그룹 명
	private String contents;					// 모집글 내용
	private int recruitmentType;				// 모집 구분
	private int wayOfProceeding;				// 진행 방식
	private int numberOfRecruits;				// 모집 인원
	private LocalDate startDate;				// 진행 기간(시작 날짜)
	private LocalDate endDate;					// 진행 기간(종료 날짜)
	private LocalDate deadlineForRecruitment;	// 모집 마감 날짜
	private LocalDateTime regdate;				// 이 정보를 등록한 날짜
	
	private int[] recruitmentPart;				// 모집 분야
	private int[] technologyStack;				// 기술 스택
}
