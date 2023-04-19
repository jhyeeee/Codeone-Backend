package com.codeone.socialLogin.controller;

import java.nio.charset.Charset;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codeone.dto.user.UserDto;
import com.codeone.socialLogin.SocialLoginType;
import com.codeone.socialLogin.oauth.GoogleOauth;
import com.codeone.socialLogin.service.OauthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins="http://localhost:3000/")
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
@Slf4j
public class OauthController {
    private final OauthService oauthService;
    //임시
    private final GoogleOauth googleOauth;

    /**
     * 사용자가 소셜로그인 요청을 보냈을 때 처리하는 로직
     * @param socialLoginType (GOOGLE, NAVER, KAKAO)
     */
    @GetMapping(value = "/{socialLoginType}")
    public void socialLoginType(
            @PathVariable(name = "socialLoginType") SocialLoginType socialLoginType) {
		log.info(">> 사용자로부터 SNS 로그인 요청을 받음 :: {} Social Login", socialLoginType);
        oauthService.request(socialLoginType);
    }
    
	/**
     * Social Login API Server 요청에 의한 callback 을 처리
     * @param socialLoginType (GOOGLE, NAVER, KAKAO)
     * @param code API Server 로부터 넘어오는 code
     * @return ResponseEntity를 신규냐 기존회원인가에 따라 다르게 정보를 준다
	 * @throws Exception 
     */
    @GetMapping(value = "/{socialLoginType}/callback")
    public void  callback(
            @PathVariable(name = "socialLoginType") SocialLoginType socialLoginType,
            @RequestParam(name = "code") String code,
            HttpServletResponse response,
            HttpServletRequest request) throws Exception {
        log.info(">> 소셜 로그인 API 서버로부터 받은 code :: {}", code);

        // User정보 생성 ()
        UserDto user =  googleOauth.getUserInfo( googleOauth.requestUserInfo(oauthService.requestAccessTokenAndParsing(socialLoginType, code)));
        HttpSession httpSession = request.getSession();
        	httpSession.setAttribute("user", user);
//        if(httpSession.isNew()) {
//        	String url =  response.encodeURL("http://localhost:3000/signUp");
//        	System.out.println("isNew");
//        	response.sendRedirect(url);
//        }
//        
        response.sendRedirect("http://localhost:3000/signUp");
    }
    
    
    @GetMapping(value="/getSessionUser")
    public ResponseEntity<UserDto> getSessionUser(HttpServletRequest request, HttpServletResponse response) {
    	HttpSession httpSession = request.getSession();
    	UserDto user = (UserDto)httpSession.getAttribute("user");
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
    	return ResponseEntity.ok()
    				.headers(header)
    				.body(user);
    	
    }
    
}