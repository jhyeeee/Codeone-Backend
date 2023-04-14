package com.codeone.dao.studygroup;

import com.codeone.dto.studygroup.StudygroupMemberVotingDto;

public interface StudygroupMemberVotingDao {
	int getAgreementMemberAmount(int studygroupSeq);
	boolean isExceededMajority(StudygroupMemberVotingDto studygroupMemberVoting);
}
