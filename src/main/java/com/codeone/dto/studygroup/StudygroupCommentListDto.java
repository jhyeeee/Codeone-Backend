package com.codeone.dto.studygroup;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class StudygroupCommentListDto extends StudygroupCommentDto implements Serializable {
	private int amount;								// 댓글이 달린 개수
	private List<StudygroupCommentDetailDto> list;	// 댓글 목록
}
