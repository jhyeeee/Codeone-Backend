package com.codeone.command.studygroup;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
@Data
public class StudygroupUpdateCommand extends StudygroupInfoCommand {
	private int seq;	// 수정을 요청한 스터디 그룹 관리 번호
}
