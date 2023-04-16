package com.codeone.service.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codeone.dao.store.StoreDao;
import com.codeone.dto.store.StoreItemDto;

@Service
@Transactional
public class StoreService {

	@Autowired
	StoreDao dao;
	
	// 중고거래 글쓰기
	public boolean writeStore(StoreItemDto item) {
		int n = dao.writeStore(item);
		return n>0?true:false;
	}
}
