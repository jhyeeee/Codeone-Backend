package com.codeone.dto.studygroup;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class StudygroupMemberDto {
	private int seq;
	private int studygroupSeq;
	private int memberSeq;
	private LocalDateTime regdate;
}
