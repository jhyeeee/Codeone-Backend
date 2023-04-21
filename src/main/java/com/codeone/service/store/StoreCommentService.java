package com.codeone.service.store;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codeone.dao.store.StoreCommentDao;
import com.codeone.dto.store.StoreCommentDto;
import com.codeone.dto.store.StoreCommentParam;
import com.codeone.dto.store.StoreParam;

@Service
@Transactional
public class StoreCommentService {
	
	@Autowired
	StoreCommentDao dao;
	
	public boolean writeCommentStore(StoreCommentDto dto) {
		int n = dao.writeCommentStore(dto);
		return n>0?true:false;
	}
	
	// 댓글목록
	public List<StoreCommentDto> getStoreCommentList(StoreCommentParam param) {
		return dao.getStoreCommentList(param);
	}
	
	// 댓글의 총수
	public int getStoreCommentCount(int itemseq) {
		return dao.getStoreCommentCount(itemseq);
	}
	
	// 댓글 수정
	public void updateStoreComment(StoreCommentDto dto) {
		dao.updateStoreComment(dto);		
	}
	
	// 댓글 삭제
	public void deleteStoreComment(int seq) {
		dao.deleteStoreComment(seq);
	}

}
