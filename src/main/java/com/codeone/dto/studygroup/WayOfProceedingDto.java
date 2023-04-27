package com.codeone.dto.studygroup;

import lombok.Data;

// 모집 방법 DTO
// 모집 방법은 온라인, 오프라인 두 개밖에 없으므로 DTO와 매치되는 테이블이 존재하진 않음
// 추후 확장성을 위해 DTO를 만든 것

@Data
public class WayOfProceedingDto {
	private int seq;		// 모집 방법 번호
	private String name;	// 모집 방법 명
}
