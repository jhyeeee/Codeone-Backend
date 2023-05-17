package com.codeone.controller.blog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.codeone.command.studygroup.StudygroupListCommand;
import com.codeone.dto.blog.BlogDto;
import com.codeone.dto.blog.BlogParam;
import com.codeone.dto.studygroup.StudygroupListDto;
import com.codeone.dto.user.UserDto;
import com.codeone.etc.LoginUtil;
import com.codeone.service.blog.BlogGroupMngService;
import com.codeone.service.studygroup.StudygroupMngService;

import jakarta.servlet.http.HttpSession;

@RestController
public class BlogMngController {

	@Autowired
	BlogGroupMngService blogGroupMngService;
	@GetMapping("/bloggroup/mng/amount")
	public ResponseEntity<Integer> getMyStudygroupAmount(HttpSession session) {
		// 로그인 여부 확인 및 로그인한 사용자 번호 꺼내기
		if(!LoginUtil.isLogin(session)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		
		UserDto loginUserInfo = LoginUtil.getLoginUserInfo(session);
		String userEmail = loginUserInfo.getEmail();
		// 로그인 여부 확인 및 로그인한 사용자 번호 꺼내기
		
		int amount = blogGroupMngService.getMyBloggroupAmount(userEmail);
		
		System.out.println( amount + " blog ammount");
		if(amount == 0) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(amount);
		}
	}
	
	@GetMapping("/bloggroup/mng/likeamount")
	public ResponseEntity<Integer> getMyStudygroupLikeAmount(HttpSession session) {
		// 로그인 여부 확인 및 로그인한 사용자 번호 꺼내기
		if(!LoginUtil.isLogin(session)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		
		UserDto loginUserInfo = LoginUtil.getLoginUserInfo(session);
		String userEmail = loginUserInfo.getEmail();
		// 로그인 여부 확인 및 로그인한 사용자 번호 꺼내기
		
		int amount = blogGroupMngService.getMyLikeBloggroupAmount(userEmail);
		System.out.println( amount + " like ammount");
		if(amount == 0) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(amount);
		}
	}
	
	@GetMapping("/bloggroup/mng/list/{pageNumber}")
	public ResponseEntity<List<BlogDto>> getMyBloggroupList(@PathVariable("pageNumber") int pageNumber, HttpSession session) {
		if(pageNumber < 1) {
			return ResponseEntity.badRequest().build();
		}
		
		// 로그인 여부 확인 및 로그인한 사용자 번호 꺼내기
		if(!LoginUtil.isLogin(session)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		
		UserDto loginUserInfo = LoginUtil.getLoginUserInfo(session);
		String memberEmail = loginUserInfo.getEmail();
		// 로그인 여부 확인 및 로그인한 사용자 번호 꺼내기
	
		// 글의 시작과 끝
		// 0부터 시작하기떄문에 리액트에서 넘겨줄 때 -1해서 넘겨줌
		BlogParam param = new BlogParam();
		System.out.println(pageNumber + " blog PageNumber");
		int pn = pageNumber-1; // 0 1 2 3 4

		int start = pn * 5; // 페이지 숫자 넘어온것 10 20 30 40부터 시작
		// int end = ( pn + 1 ) * 5;	// 10 20

		param.setStart(start);
		param.setDataCount(5);
		
		param.setUserEmail(memberEmail);
		System.out.println(param);
		
		
		
		List<BlogDto> bloggroupList = blogGroupMngService.getMyBloggroupList(param);
		if(bloggroupList.size() == 0) {
			return ResponseEntity.badRequest().build();
		} else {
			return ResponseEntity.ok(bloggroupList);
		}
	}
	
	
	@GetMapping("/bloggroup/mng/likelist/{pageNumber}")
	public ResponseEntity<List<BlogDto>> getMyLikeBloggroupList(@PathVariable("pageNumber") int pageNumber, HttpSession session) {
		if(pageNumber < 1) {
			return ResponseEntity.badRequest().build();
		}
		
		// 로그인 여부 확인 및 로그인한 사용자 번호 꺼내기
		if(!LoginUtil.isLogin(session)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		
		UserDto loginUserInfo = LoginUtil.getLoginUserInfo(session);
		System.out.println(loginUserInfo.toString());
		String memberEmail = loginUserInfo.getEmail();
		// 로그인 여부 확인 및 로그인한 사용자 번호 꺼내기
	
		// 글의 시작과 끝
		// 0부터 시작하기떄문에 리액트에서 넘겨줄 때 -1해서 넘겨줌
		BlogParam param = new BlogParam();
		int pn = pageNumber-1 ; // 0 1 2 3 4
		System.out.println(pageNumber + " like PageNumber");

		int start = pn * 5; // 페이지 숫자 넘어온것 10 20 30 40부터 시작
//				int end = ( pn + 1 ) * 10;	// 10 20

		param.setStart(start);
		param.setDataCount(5);
		param.setUserEmail(memberEmail);
		System.out.println(param);
		
		
		
		List<BlogDto> bloggroupList = blogGroupMngService.getMyLikeBloggroupList(param);
		System.out.println(bloggroupList.size() + " size()");
		if(bloggroupList.size() == 0) {
			return ResponseEntity.badRequest().build();
		} else {
			return ResponseEntity.ok(bloggroupList);
		}
	}
	
}
