package com.codeone.command.studygroup;

import com.codeone.dto.studygroup.StudygroupCommentDto;

import lombok.Data;

@Data
public class StudygroupCommentCommand {
	private int studygroupSeq;	// 댓글을 단 스터디 그룹 관리 정보 번호
	private String comment;		// 댓글
	
	public StudygroupCommentDto toDto() {
		StudygroupCommentDto dto = new StudygroupCommentDto();
		dto.setStudygroupSeq(studygroupSeq);
		dto.setComment(comment);
		
		return dto;
	}
}
