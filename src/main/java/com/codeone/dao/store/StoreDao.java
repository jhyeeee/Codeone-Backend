package com.codeone.dao.store;


import java.util.List;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.codeone.dto.store.StoreItemDto;

import com.codeone.dto.store.StoreLikeDto;
import com.codeone.dto.store.StoreParam;


@Mapper
@Repository
public interface StoreDao {
	

	// 중고거래 글쓰기	
	int writeStore(StoreItemDto item);

	// 중고거래 글 목록
	List<StoreItemDto> getStoreList(StoreParam param);
	
	// 중고거래 글의 총수
	int getAllStoreCount(StoreParam param);
	
	// 중고거래 글 1개
	StoreItemDto getStoreItem(int seq);
	
	// 좋아요	
	int likeItem(StoreLikeDto dto);
	
	// 좋아요중인지 확인	
	int checkLike(StoreLikeDto dto);
	
	// 좋아요 취소
	int cancelLike(StoreLikeDto dto);
	
	// 좋아요 카운트
	int countLike(int seq);
	
	// 좋아요 취소카운트
	int countCancelLike(int seq);
	
	// 좋아요한 seq 리스트
	List<Integer> getlikeList(String id);
	
	// 중고거래 글 수정
	int updateStoreWrite(StoreItemDto item);
	
	// 중고거래 글 삭제
	int deleteStoreWrite(int seq);
	
	// 판매여부 변경
	void updateStatus(StoreItemDto item);

}
