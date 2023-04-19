package com.codeone.controller.user;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeone.dto.user.UserDto;
import com.codeone.etc.TempKey;
import com.codeone.service.user.UserService;

@RestController
public class UserController {

	// userService 연결
	@Autowired
	UserService service;

	// 회원가입 이메일 중복체크, 이메일 전송
	@PostMapping(value = "/checkEmail")
	public String checkEmail(String email) throws Exception {
		System.out.println("userController checkEmail() " + new Date());

		if (email == null || !email.matches("\\w+@(\\w+\\.)+\\w+")) { // 이메일 형식 체크
			return "INVALID_EMAIL";
		}

		boolean isEmailCheck = service.checkEmail(email);
		if (isEmailCheck) { // 중복된 이메일이 있음
			return "DUPLICATED_EMAIL";
		}

		// 중복된 이메일 없음
		// 없는 이메일이면 메일발송
		String emailKey = new TempKey().getKey(10, false); // 랜덤키 길이 설정
		System.out.println(emailKey);

		service.sendEmail(email, emailKey);

		return "SUCCESS";

	}
	
	@PostMapping(value = "/checkId")
	public String checkId(String id) {
		System.out.println("userController checkId() " + new Date());
		
		// 아이디 유효성 검사
		
		// 아이디 중복 검사
		boolean isIdCheck = service.checkId(id);
		if (isIdCheck == true) {	// 중복된 아이디 있음
			return "DUPLICATED_ID";
		}
		return "SUCCESS";
	}

	// 회원가입 페이지 이동 react 프론트에서

	// 이메일 인증 넣어주기
	// 회원가입
	@PostMapping(value = "/addUser")
	public String addUser(UserDto dto) {
		System.out.println("userController addUser() " + new Date());
		
		// 넘어온 값 확인 
		System.out.println(dto);
		
		// 이메일 중복체크
		boolean isEmailCheck = service.checkEmail(dto.getEmail());
		if (isEmailCheck) { // 중복된 이메일이 있음
			return "DUPLICATED_EMAIL";
		}
		
		// 중복 이메일 없을 때	
		
		// 회원가입
		boolean isSignupSuccess = service.addUser(dto);
		
		// 이메일인증여부 업데이트
		service.updateEmailAuth(dto);
		
		if (isSignupSuccess == false) { // 회원가입 실패
			return "NO";
		}	
		
		return "YES"; // 회원가입 성공
	}

	@PostMapping(value = "/login")
	public String login(String email) throws Exception {
		System.out.println("userController login() " + new Date());
		
		// 이메일넣고 회원정보 불러오기
		UserDto dto = service.getMember(email);
		System.out.println(dto);
		
		if(dto == null) {		// 가입정보가 없을 때
			return "NO_USER";	
		}		
		
		// 가입된 이메일이 있을 때
		// 인증키 생성
		String emailKey = new TempKey().getKey(10, false); // 랜덤키 길이 설정
		System.out.println(emailKey);
		
		// 메일키 회원정보에 업데이트 시켜주기	
		dto.setEmailKey(emailKey);
		service.updateEmailKey(dto);
		
		// 이메일로 로그인 메일 전송
		service.sendLoginEmail(email, emailKey);
		
		// 이메일로 링크 전송후 프론트에서 메일키 맞는지 확인하고 로그인 시키고 메일주소에 정보 지워줌.

		return "SUCCESS";
	}
	
	// loginAf 만들어주기
	

//  중복이메일 체크하면서 한번에 메일 보내주기	
//	@GetMapping(value = "/sendEmail")
//	public String email(String email) throws Exception {
//		
//		// 이메일 랜덤키 생성
//		String mail_key = new TempKey().getKey(10, false); // 랜덤키 길이 설정
//		System.out.println(mail_key);		
//		
//		service.sendEmail(email, mail_key);
//		
//		return "OK";
//	}
}
