package com.codeone.command.studygroup;

import com.codeone.dto.studygroup.StudygroupInfoDto;
import com.codeone.dto.studygroup.StudygroupManagementDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
@Data
public class StudygroupUpdateCommand extends StudygroupInfoCommand {
	private int seq;	// 수정을 요청한 스터디 그룹 관리 번호
	
	@Override
	public StudygroupManagementDto toStudygroupManagementDto() {
		StudygroupManagementDto dto = super.toStudygroupManagementDto();
		dto.setSeq(seq);
		
		return dto;
	}
	
	@Override
	public StudygroupInfoDto toStudygroupInfoDto() {
		StudygroupInfoDto dto = super.toStudygroupInfoDto();
		dto.setManagementSeq(seq);
		
		return dto;
	}
}
