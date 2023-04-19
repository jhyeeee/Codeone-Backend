package com.codeone.controller.studygroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeone.exception.NotPermissionToModifyException;
import com.codeone.exception.UnmodifiableConditionException;
import com.codeone.service.studygroup.StudygroupMngService;

@RestController
public class StudygroupMngController {
	@Autowired
	StudygroupMngService studygroupMngService;

	@PutMapping("/studygroup/mng/toggleIsClosed/{seq}")
	public ResponseEntity<Void> toggleIsClosed(@PathVariable int seq) {
		if(seq <= 0) {
			return ResponseEntity.badRequest().build();
		}
		
		// 로그인 여부 확인 코드 필요
		int memberSeq = 1;
		
		
		try {
			studygroupMngService.toggleIsClosed(seq, memberSeq);
			return ResponseEntity.ok().build();
		} catch(NotPermissionToModifyException e) {
			return ResponseEntity.badRequest().build();
		} catch(UnmodifiableConditionException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
}
