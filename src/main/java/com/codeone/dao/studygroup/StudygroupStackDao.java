package com.codeone.dao.studygroup;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.codeone.dto.studygroup.StudygroupStackDto;

@Mapper
@Repository
public interface StudygroupStackDao {
	void insert(List<StudygroupStackDto> studygroupStackDtoList);
	void delete(int studygroupManagementSeq);
	List<Integer> selectAll(int studygroupManagementSeq);
}
