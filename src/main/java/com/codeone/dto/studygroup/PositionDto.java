package com.codeone.dto.studygroup;

import java.io.Serializable;

import lombok.Data;

@Data
public class PositionDto implements Serializable {
	private int seq;	// 모집 분야 번호
	private int name;	// 모집 분야 명
	
	public PositionDto(int seq, int name) {
		super();
		this.seq = seq;
		this.name = name;
	}
}
