package com.codeone.dto.studygroup;

import java.io.Serializable;

import lombok.Data;

@Data
public class PositionDto implements Serializable {
	private int seq;		// 모집 분야 번호
	private String name;	// 모집 분야 명
}
