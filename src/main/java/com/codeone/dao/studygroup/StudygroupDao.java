package com.codeone.dao.studygroup;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.codeone.command.studygroup.StudygroupCommand;
import com.codeone.dto.studygroup.StudygroupDto;

@Mapper
@Repository
public interface StudygroupDao {
	int writeStudygroupRecruitment(StudygroupDto studygroup);
	StudygroupDto selectOneBySeq(int seq);
	int deleteStudygroupRecruitment(int seq);
	int updateStudygroupRecruitment(StudygroupDto studygroup);
}
