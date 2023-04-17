package com.codeone.dto.studygroup;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class StudygroupLikeDto implements Serializable {
	private int studygroupSeq;		// 관심 스터디 그룹으로 등록한 스터디 그룹 관리 정보 번호
	private int memberSeq;			// 관심 스터디 그룹으로 등록한 사용자 번호
	private LocalDateTime regdate;	// 등록 날짜
}
