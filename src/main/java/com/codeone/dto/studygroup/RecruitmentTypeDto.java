package com.codeone.dto.studygroup;

import lombok.Data;

// 모집 구분 DTO
// 모집 구분은 스터디, 프로젝트 두 개밖에 없으므로 DTO와 매치되는 테이블이 존재하진 않음
// 추후 확장성을 위해 DTO를 만든 것

@Data
public class RecruitmentTypeDto {
	private int seq;		// 모집 구분 번호
	private String name;	// 모집 구분 명
}
