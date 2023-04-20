package com.codeone.controller.store;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.codeone.dto.store.StoreCommentDto;
import com.codeone.service.store.StoreCommentService;

@RestController
public class StoreCommentController {
	
	@Autowired
	StoreCommentService service;
	
	// 중고거래 댓글 작성
	@PostMapping(value = "/storeComment")
	public ResponseEntity<Void> writeCommentStore(@RequestBody StoreCommentDto dto){
		System.out.println("StoreCommentController writeCommentStore()" + new Date());
		
		String id = "mi538";
		dto.setId(id);
		
		boolean isStoreComment = service.writeCommentStore(dto);
		
		if(isStoreComment) {
			return ResponseEntity.ok().build();		// 댓글작성 성공
		}
		return ResponseEntity.badRequest().build(); // 댓글작성 실패
	}
	
	// 중고거래 댓글 목록
	@GetMapping(value = "/storeComment")
	public ResponseEntity<List<StoreCommentDto>> getStoreCommentList(int itemseq){
		System.out.println("StoreCommentController getStoreCommentList()" + new Date());
		
		List<StoreCommentDto> commentList = service.getStoreCommentList(itemseq);
		
		if(commentList.size() == 0) {
			return ResponseEntity.noContent().build();			
		}
		return ResponseEntity.ok(commentList);
	}
	
	// 댓글 수정
	@PutMapping(value = "/storeComment")
	public ResponseEntity<Void> updateStoreComment(StoreCommentDto dto){
		System.out.println("StoreCommentController storeComment()" + new Date());
		System.out.println(dto);
		
		try {
			service.updateStoreComment(dto);
			return ResponseEntity.ok().build();		// 댓글수정 성공
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().build(); // 댓글작성 실패
		}
	}
	
	// 댓글 삭제
	@DeleteMapping(value = "/storeComment")
	public ResponseEntity<Void> deleteStoreComment(int seq){
		System.out.println("StoreCommentController deleteStoreComment()" + new Date());
		
		try {
			service.deleteStoreComment(seq);
			return ResponseEntity.ok().build();		// 댓글삭제 성공
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().build(); // 댓글삭제 실패
		}
	}
	

}
