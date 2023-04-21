package com.codeone.dao.store;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.codeone.dto.store.StoreItemDto;

@Mapper
@Repository
public interface StoreDao {
	
	// 중고거래 글쓰기
	
	int writeStore(StoreItemDto item);

}
