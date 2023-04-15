package com.codeone.dto.studygroup;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class StudygroupMemberDto implements Serializable {
	private int seq;
	private int studygroupSeq;
	private int memberSeq;
	private LocalDateTime regdate;
}
