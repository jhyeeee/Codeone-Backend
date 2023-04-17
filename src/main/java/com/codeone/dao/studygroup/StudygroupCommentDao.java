package com.codeone.dao.studygroup;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.codeone.dto.studygroup.StudygroupCommentDto;

@Mapper
@Repository
public interface StudygroupCommentDao {
	void writeComment(StudygroupCommentDto studygroupComment);
}
