package com.codeone.dao.studygroup;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.codeone.command.studygroup.StudygroupListCommand;
import com.codeone.dto.studygroup.StudygroupListDto;
import com.codeone.dto.studygroup.StudygroupManagementDto;

@Mapper
@Repository	
public interface StudygroupManagementDao {
	void insert(StudygroupManagementDto newStudygroupManagement);
	void updateInfoSeq(StudygroupManagementDto newStudygroupManagement);
	StudygroupManagementDto selectOne(int seq);
	int deleteStudygroupRecruitment(int seq);
	List<StudygroupListDto> selectAllStudygroupList(StudygroupListCommand studygroupListCommand);
	void toggleIsClosed(int seq);
	void increaseLikeAmount(int seq);
	void decreaseLikeAmount(int seq);
	void increaseCommentAmount(int seq);
}
