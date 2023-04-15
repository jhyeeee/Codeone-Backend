package com.codeone.dao.studygroup;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.codeone.dto.studygroup.StudygroupMemberVotingDto;

@Mapper
@Repository
public interface StudygroupMemberVotingDao {
	int getAgreementMemberAmount(int studygroupSeq);
	boolean isExceededMajority(StudygroupMemberVotingDto studygroupMemberVoting);
}
