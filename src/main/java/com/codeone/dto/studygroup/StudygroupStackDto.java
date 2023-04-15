package com.codeone.dto.studygroup;

import java.io.Serializable;

import lombok.Data;

@Data
public class StudygroupStackDto implements Serializable {
	private int managementSeq;	// 스터디 그룹 관리 번호
	private int stackSeq;		// 기술 스택 번호
}
