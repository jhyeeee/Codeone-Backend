package com.codeone.controller.store;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeone.dto.store.storeItemDto;
import com.codeone.service.store.storeService;

@RestController
public class storeController {

	@Autowired
	storeService service;
	
	// 중고거래 글쓰기
	@PostMapping(value = "/storewrite")
	public String storewrite(storeItemDto item) {
		System.out.println("storeController storewrite() " + new Date());
		
		boolean isWriteStore = service.writeStore(item);
		if(isWriteStore == true) {
			return "WRITE_OK";		// 글쓰기 성공
		}
		return "WRITE_FAIL";		// 글쓰기 실패
	}
}
