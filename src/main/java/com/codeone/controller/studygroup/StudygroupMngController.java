package com.codeone.controller.studygroup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeone.command.studygroup.StudygroupListCommand;
import com.codeone.dto.studygroup.StudygroupListDto;
import com.codeone.dto.user.UserDto;
import com.codeone.etc.LoginUtil;
import com.codeone.exception.NotPermissionToModifyException;
import com.codeone.exception.UnmodifiableConditionException;
import com.codeone.service.studygroup.StudygroupMngService;

import jakarta.servlet.http.HttpSession;

@RestController
public class StudygroupMngController {
	@Autowired
	StudygroupMngService studygroupMngService;
	
	@GetMapping("/studygroup/mng/amount")
	public ResponseEntity<Integer> getMyStudygroupAmount(HttpSession session) {
		// 로그인 여부 확인 및 로그인한 사용자 번호 꺼내기
		if(!LoginUtil.isLogin(session)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		
		UserDto loginUserInfo = LoginUtil.getLoginUserInfo(session);
		int memberSeq = loginUserInfo.getSeq();
		// 로그인 여부 확인 및 로그인한 사용자 번호 꺼내기
		
		int amount = studygroupMngService.getMyStudygroupAmount(memberSeq);
		if(amount == 0) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(amount);
		}
	}
	
	@GetMapping("/studygroup/mng/list/{pageNumber}")
	public ResponseEntity<List<StudygroupListDto>> getMyStudygroupList(@PathVariable("pageNumber") int pageNumber, HttpSession session) {
		if(pageNumber < 1) {
			return ResponseEntity.badRequest().build();
		}
		
		// 로그인 여부 확인 및 로그인한 사용자 번호 꺼내기
		if(!LoginUtil.isLogin(session)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		
		UserDto loginUserInfo = LoginUtil.getLoginUserInfo(session);
		int memberSeq = loginUserInfo.getSeq();
		// 로그인 여부 확인 및 로그인한 사용자 번호 꺼내기
		
		StudygroupListCommand studygroupListCommand = new StudygroupListCommand();
		studygroupListCommand.setMemberSeq(memberSeq);
		studygroupListCommand.setStart((pageNumber-1) * 10);
		studygroupListCommand.setEnd(pageNumber * 10);
		
		List<StudygroupListDto> studygroupList = studygroupMngService.getMyStudygroupList(studygroupListCommand);
		if(studygroupList.size() == 0) {
			return ResponseEntity.badRequest().build();
		} else {
			return ResponseEntity.ok(studygroupList);
		}
	}

	@PutMapping("/studygroup/mng/toggleIsClosed/{seq}")
	public ResponseEntity<Void> toggleIsClosed(@PathVariable int seq, HttpSession session) {
		if(seq <= 0) {
			return ResponseEntity.badRequest().build();
		}
		
		// 로그인 여부 확인 및 로그인한 사용자 번호 꺼내기
		if(!LoginUtil.isLogin(session)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		
		UserDto loginUserInfo = LoginUtil.getLoginUserInfo(session);
		int memberSeq = loginUserInfo.getSeq();
		// 로그인 여부 확인 및 로그인한 사용자 번호 꺼내기
		
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
