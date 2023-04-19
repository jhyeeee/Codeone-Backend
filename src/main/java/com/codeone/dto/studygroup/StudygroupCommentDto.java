package com.codeone.dto.studygroup;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class StudygroupCommentDto implements Serializable {
	private int seq;				// 댓글 번호
	@JsonIgnore
	private int studygroupSeq;		// 댓글을 단 스터디 그룹 관리 정보 번호
	@JsonIgnore
	private int memberSeq;			// 댓글을 단 사용자 번호
	@JsonIgnore
	private int commentSeq;			// 댓글에 댓글을 달았을 때 추가되는 것으로 댓글 번호
	private String comment;			// 댓글
	private LocalDateTime regdate;	// 댓글 등록 날짜
}
