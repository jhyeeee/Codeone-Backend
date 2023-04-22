package com.codeone.controller.user;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeone.dto.user.UserDto;
import com.codeone.etc.TempKey;
import com.codeone.service.user.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping(value="/user")
public class UserController {

	// userService 연결
	@Autowired
	UserService service;

	// 회원가입 이메일 중복체크, 이메일 전송
//	@PostMapping(value = "/checkEmail")
//	public ResponseEntity<String> checkEmail(String email) throws Exception {
//		System.out.println("userController checkEmail() " + new Date());
//
//		boolean isEmailCheck = service.checkEmail(email);
//		if (isEmailCheck) { // 중복된 이메일이 있음
//			return ResponseEntity.status(HttpStatusCode.valueOf(204)).build();
//		}
//
//		// 중복된 이메일 없음
//		// 없는 이메일이면 메일발송
//		String emailKey = new TempKey().getKey(10, false); // 랜덤키 길이 설정
//		System.out.println(emailKey);
//
//		service.sendEmail(email, emailKey);
//		return ResponseEntity.ok().build();
//
//	}
	
	// 회원가입 이메일 중복체크
	@PostMapping(value = "/checkingEmail")
	public ResponseEntity<String> checkingEmail(String email) throws Exception {

		boolean isEmailCheck = service.checkEmail(email);
		if (isEmailCheck) { // 중복된 아이디가 있음
			return ResponseEntity.status(HttpStatusCode.valueOf(204)).build();
		}

		// 중복된 아이디 없음
		return ResponseEntity.ok().build();

	}
	
	// Id 체크 로직
	@PostMapping(value = "/checkingId")
	public ResponseEntity<String> checkingId(String id) throws Exception {
		boolean isEmailCheck = service.checkEmail(id);
		if (isEmailCheck) { // 중복된 이메일이 있음
			return ResponseEntity.status(HttpStatusCode.valueOf(204)).build();
		}

		// 중복된 이메일 없음
		// 없는 이메일이면 메일발송
		return ResponseEntity.ok().build();

	}
	
	
	
//	@PostMapping(value = "/checkId")
//	public String checkId(String id) {
//		System.out.println("userController checkId() " + new Date());
//		
//		// 아이디 유효성 검사
//		
//		// 아이디 중복 검사
//		boolean isIdCheck = service.checkId(id);
//		if (isIdCheck) {	// 중복된 아이디 있음
//			return "DUPLICATED_ID";
//		}
//		return "SUCCESS";
//	}

	// 회원가입 페이지 이동 react 프론트에서

	// 이메일 인증 넣어주기
	@PostMapping(value = "/sendSignUpEmail")
	public ResponseEntity<String> sendSignUpEmail(UserDto dto) throws Exception {
		System.out.println("userController sendSignUpEmail() " + new Date());
		
		// 넘어온 값 확인 

		
	
		// 이메일 중복체크
		boolean isEmailCheck = service.checkEmail(dto.getEmail());
		if (isEmailCheck) { // 중복된 이메일이 있음
			HttpHeaders header = new HttpHeaders();
	        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
			return ResponseEntity.status(HttpStatusCode.valueOf(204)).build();
		}
		
		// 중복 이메일 없을 때
		
		// 없는 이메일이면 메일발송
		service.sendSignUpEmail(dto.getEmail(), dto.getId(), dto.getName());
		return ResponseEntity.ok().build(); 
		
		// 이메일인증여부 여기서 업데이트 해주기
//		String emailKey = new TempKey().getKey(10, false); // 랜덤키 길이 설정
//		boolean isSignupSuccess = service.addUser(dto);
//		
//		if (isSignupSuccess == false) { // 회원가입 실패
//			return ResponseEntity.status(HttpStatusCode.valueOf(500)).build();
//		}
		
		
		
		// 회원가입 성공
	}

	@PostMapping(value = "/login")
	public String login(String email) throws Exception {
		System.out.println("userController login() " + new Date());
		System.out.println(email);
		// 이메일넣고 회원정보 불러오기
		UserDto dto = service.getMember(email);
		
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
	// signUpAf 만들어주기
	@GetMapping(value = "/signUpemail")
	public void signUpemail(String email, String id, String name, HttpServletRequest request, HttpServletResponse response) throws Exception {

		// DB에 회원을
		UserDto user = new UserDto();
		user.setEmail(email);
		user.setId(id);
		user.setName(name);
		String emailKey = new TempKey().getKey(10, false); // 랜덤키 길이 설정
		System.out.println(emailKey);		
		user.setEmailKey(emailKey);
		if(service.addUser(user)) {
			HttpSession httpSession = request.getSession();
	        httpSession.setAttribute("user", user);
			
			response.sendRedirect("http://localhost:3000");
		}else {
			response.sendRedirect("http://localhost:3000/error");
		}
		
		
	}

	// loginAf 만들어주기
	@GetMapping(value = "/codeone/regi")
	public void loginAf(String email, String emailKey, HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(email);
		System.out.println(emailKey);
		UserDto user = service.checkEmailKey(emailKey);
		HttpSession httpSession = request.getSession();
        httpSession.setAttribute("user", user);
		
		response.sendRedirect("http://localhost:3000");
		
	}
	

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
	
	
    
    @GetMapping(value="/getSessionUser")
    public ResponseEntity<UserDto> getSessionUser(HttpServletRequest request, HttpServletResponse response) {
    	HttpSession httpSession = request.getSession();
    	UserDto user = (UserDto)httpSession.getAttribute("user");
    	if(Objects.isNull(user)) {
    		return ResponseEntity.noContent().build();    				
    	}
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
    	return ResponseEntity.ok()
    				.headers(header)
    				.body(user);    	
    }
}
