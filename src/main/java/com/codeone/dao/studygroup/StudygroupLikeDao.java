package com.codeone.dao.studygroup;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.codeone.dto.studygroup.StudygroupLikeDto;

@Mapper
@Repository
public interface StudygroupLikeDao {
	int getAmountByStudygroupSeq(int studygroupSeq);
	StudygroupLikeDto selectOne(StudygroupLikeDto studygroupLike);
	void insert(StudygroupLikeDto studygroupLike);
	void delete(StudygroupLikeDto studygroupLike);
}
