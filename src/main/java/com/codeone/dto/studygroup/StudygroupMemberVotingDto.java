package com.codeone.dto.studygroup;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class StudygroupMemberVotingDto implements Serializable {
	private int studygroupSeq;		// 투표를 진행한 스터디그룹 번호
	private int votingSeq;		// 투표 유형 번호
	private int memberSeq;			// 투표한 스터디그룹원 번호
	private boolean isAgreement;	// 찬성 여부
	private LocalDateTime regdate;	// 투표한 날짜
	
	private int agreementMemberAmount;		// 투표에 찬성한 그룹원의 수
	private int disAgreementMemberAmount; 	// 투표에 반대한 그룹원의 수
}
