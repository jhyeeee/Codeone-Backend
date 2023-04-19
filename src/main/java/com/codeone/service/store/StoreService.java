package com.codeone.service.store;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codeone.dao.store.StoreDao;
import com.codeone.dto.store.StoreItemDto;

import com.codeone.dto.store.StoreLikeDto;
import com.codeone.dto.store.StoreParam;


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

	
	public List<StoreItemDto> getStoreList(StoreParam param) {
		return dao.getStoreList(param);
	}
	
	public StoreItemDto getStoreItem(int seq) {
		return dao.getStoreItem(seq);
	}
	
	// 좋아요
	public boolean likeItem(StoreLikeDto dto) {
		int n = dao.likeItem(dto);
		return n>0?true:false;		
	}
	
	// 좋아요중인지 확인
	public boolean checkLike(StoreLikeDto dto) {
		int n = dao.checkLike(dto);
		return n>0?true:false;
	}
	
	// 좋아요취소
	public boolean cancelLike(StoreLikeDto dto) {
		int n = dao.cancelLike(dto);
		return n>0?true:false;		
	}
	
	// 좋아요카운트
	public int countLike(int seq) {
		return dao.countLike(seq);
	}
	
	// 좋아요취소 카운트
	public int countCancelLike(int seq) {
		return dao.countCancelLike(seq);
	}
	
	// 중고거래 글 수정
	public boolean updateStoreWrite(StoreItemDto item) {
		int n = dao.updateStoreWrite(item);
		return n>0?true:false;
	}
}






