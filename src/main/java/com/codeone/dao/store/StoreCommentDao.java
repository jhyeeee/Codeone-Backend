package com.codeone.dao.store;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.codeone.dto.store.StoreCommentDto;
import com.codeone.dto.store.StoreCommentParam;
import com.codeone.dto.store.StoreParam;

@Mapper
@Repository
public interface StoreCommentDao {
	
	int writeCommentStore(StoreCommentDto dto);
	
	// 댓글목록 param넣어줌
	List<StoreCommentDto> getStoreCommentList(StoreCommentParam param);
	
	void updateStoreComment(StoreCommentDto dto);
	
	void deleteStoreComment(int seq);
	
	int getStoreCommentCount(int itemseq);

}
