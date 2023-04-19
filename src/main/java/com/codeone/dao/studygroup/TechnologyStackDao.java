package com.codeone.dao.studygroup;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.codeone.dto.studygroup.TechnologyStackDto;

@Mapper
@Repository
public interface TechnologyStackDao {
	List<TechnologyStackDto> getAll();
}
