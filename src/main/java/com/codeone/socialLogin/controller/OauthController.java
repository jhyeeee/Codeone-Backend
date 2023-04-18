package com.codeone.socialLogin.controller;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
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

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
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
     * @param code API Server 로부터 넘어노는 code
     * @return SNS Login 요청 결과로 받은 Json 형태의 String 문자열 (access_token, refresh_token 등)
	 * @throws Exception 
     */
    @GetMapping(value = "/{socialLoginType}/callback")
    public void callback(
            @PathVariable(name = "socialLoginType") SocialLoginType socialLoginType,
            @RequestParam(name = "code") String code,
            HttpServletResponse response) throws Exception {
        log.info(">> 소셜 로그인 API 서버로부터 받은 code :: {}", code);

        // User정보 생성 ()
        UserDto user =  googleOauth.getUserInfo( googleOauth.requestUserInfo(oauthService.requestAccessTokenAndParsing(socialLoginType, code)));
        System.out.println(user.toString());
        
        response.sendRedirect("http://localhost:3000/");
    }
}