package com.codeone.controller.store;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.codeone.dto.store.StoreItemDto;
import com.codeone.service.store.StoreService;

@RestController
public class StoreController {

	@Autowired
	StoreService service;
	
	// 중고거래 글쓰기
	@PostMapping(value = "/storewrite")
	public String storewrite(StoreItemDto item) {		
		System.out.println("storeController storewrite() " + new Date());			
		
		boolean isWriteStore = service.writeStore(item);
		if(isWriteStore == true) {
			return "WRITE_OK";		// 글쓰기 성공
		}
		return "WRITE_FAIL";		// 글쓰기 실패
	}
}
