package com.codeone.dao.store;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.codeone.dto.store.storeItemDto;

@Mapper
@Repository
public interface storeDao {
	
	// 중고거래 글쓰기
	
	int writeStore(storeItemDto item);

}
