package com.codeone.dao.studygroup;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.codeone.command.studygroup.StudygroupInfoCommand;
import com.codeone.dto.studygroup.StudygroupInfoDto;

@Mapper
@Repository
public interface StudygroupInfoDao {
	int writeStudygroupRecruitment(StudygroupInfoDto studygroup);
	StudygroupInfoDto selectOneBySeq(int seq);
	int updateStudygroupRecruitment(StudygroupInfoDto studygroup);
}
