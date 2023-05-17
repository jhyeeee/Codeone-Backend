package com.codeone.dao.admin;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.codeone.dto.blog.BlogDto;
import com.codeone.dto.studygroup.StudygroupInfoDto;

@Mapper
@Repository
public interface StudyManageDao {	

	List<StudygroupInfoDto> allStudyManage();

}
