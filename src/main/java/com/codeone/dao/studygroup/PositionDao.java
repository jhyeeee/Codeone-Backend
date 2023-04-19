package com.codeone.dao.studygroup;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.codeone.dto.studygroup.PositionDto;

@Mapper
@Repository
public interface PositionDao {
	List<PositionDto> getAll();
}
