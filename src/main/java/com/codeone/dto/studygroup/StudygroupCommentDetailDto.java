package com.codeone.dto.studygroup;

import java.io.Serializable;

import com.codeone.dto.user.UserDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class StudygroupCommentDetailDto extends StudygroupCommentDto implements Serializable {
	private StudygroupDetailUserDto user;	// 댓글을 단 사용자 정보
	private boolean haveReply;				// 댓글에 댓글이 달린 여부
	private boolean isMine;					// 내가 단 댓글 여부
	
	public StudygroupCommentDetailDto() {
		user = new StudygroupDetailUserDto();
	}
	
	public void setIsMine(boolean isMine) {
		this.isMine = isMine;
	}
	
	public boolean getIsMine() {
		return isMine;
	}
}
