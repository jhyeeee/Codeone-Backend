package com.codeone.dao.studygroup;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.codeone.dto.studygroup.StudygroupMemberDto;

@Mapper
@Repository
public interface StudygroupMemberDao {
	int getStudygroupMemberAmountByStudygroupSeq(int studygroupSeq);
	void joinMember(StudygroupMemberDto studygroupMember);
}
