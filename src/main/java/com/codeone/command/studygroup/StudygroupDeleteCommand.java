package com.codeone.command.studygroup;

import lombok.Data;

@Data
public class StudygroupDeleteCommand {
	private int seq;		// 삭제할 스터디 그룹 관리 번호
	private int memberSeq;	// 삭제할 스터디 그룹 정보 번호
}
