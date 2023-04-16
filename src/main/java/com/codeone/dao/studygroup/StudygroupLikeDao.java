package com.codeone.dao.studygroup;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface StudygroupLikeDao {
	int getAmountByStudygroupSeq(int studygroupSeq);
}
