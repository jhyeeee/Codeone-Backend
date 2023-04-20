package com.codeone.dao.store;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.codeone.dto.store.StoreCommentDto;

@Mapper
@Repository
public interface StoreCommentDao {
	
	int writeCommentStore(StoreCommentDto dto);
	
	List<StoreCommentDto> getStoreCommentList(int itemseq);
	
	void updateStoreComment(StoreCommentDto dto);
	
	void deleteStoreComment(int seq);

}
