package com.codeone.service.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codeone.dao.store.storeDao;
import com.codeone.dto.store.storeItemDto;

@Service
@Transactional
public class storeService {

	@Autowired
	storeDao dao;
	
	// 중고거래 글쓰기
	public boolean writeStore(storeItemDto item) {
		int n = dao.writeStore(item);
		return n>0?true:false;
	}
}
