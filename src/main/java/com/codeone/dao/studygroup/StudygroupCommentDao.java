package com.codeone.dao.studygroup;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.codeone.command.studygroup.StudygroupCommentCommand;
import com.codeone.dto.studygroup.StudygroupCommentDetailDto;
import com.codeone.dto.studygroup.StudygroupCommentDto;

@Mapper
@Repository
public interface StudygroupCommentDao {
	void writeComment(StudygroupCommentDto studygroupComment);
	void updateComment(StudygroupCommentDto studygroupComment);
	StudygroupCommentDto selectOneBySeq(int seq);
	void deleteComment(int seq);
	List<StudygroupCommentDetailDto> getList(StudygroupCommentCommand studygroupCommentCommand);
	int getAmount(int studygroupSeq);
}
