package com.codeone.dto.studygroup;

import java.io.Serializable;

import lombok.Data;

@Data
public class StudygroupPositionDto implements Serializable {
	private int managementSeq;	// 스터디 그룹 관리 번호
	private int positionSeq;	// 모집 분야 번호
}
