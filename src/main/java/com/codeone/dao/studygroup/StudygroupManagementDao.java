package com.codeone.dao.studygroup;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.codeone.dto.studygroup.StudygroupManagementDto;

@Mapper
@Repository	
public interface StudygroupManagementDao {
	void insert(StudygroupManagementDto newStudygroupManagement);
	void updateInfoSeq(StudygroupManagementDto newStudygroupManagement);
	StudygroupManagementDto selectOne(int seq);
	int deleteStudygroupRecruitment(int seq);
}
