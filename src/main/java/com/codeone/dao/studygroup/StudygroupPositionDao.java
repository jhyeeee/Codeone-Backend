package com.codeone.dao.studygroup;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.codeone.dto.studygroup.StudygroupPositionDto;

@Mapper
@Repository
public interface StudygroupPositionDao {
	void insert(List<StudygroupPositionDto> studygroupPositionList);
	void delete(int studygroupSeq);
}
