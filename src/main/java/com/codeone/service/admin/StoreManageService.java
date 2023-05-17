package com.codeone.service.admin;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codeone.dao.admin.StoreManageDao;
import com.codeone.dto.store.StoreItemDto;

 

@Service
@Transactional
public class StoreManageService {

	@Autowired
	StoreManageDao dao;
	
	

	// 중고거래 글목록
	public List<StoreItemDto> getAllStoreMng() {
		return dao.getAllStoreMng();
	}
	
	public boolean showHideStore (int seq, int delflg) {
		return dao.showHideStore(seq,delflg);
	}
	
}






