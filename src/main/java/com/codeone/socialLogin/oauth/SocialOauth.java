package com.codeone.socialLogin.oauth;

import org.springframework.http.ResponseEntity;
import org.springframework.http.*;

import com.codeone.dto.user.UserDto;
import com.codeone.socialLogin.SocialLoginType;
import com.codeone.socialLogin.Token.GoogleOAuthToken;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface SocialOauth {
	/**
     * 각 Social Login 페이지로 Redirect 처리할 URL Build
     * 사용자로부터 로그인 요청을 받아 Social Login Server 인증용 code 요
     */
	String getOauthRedirectURL();
	   /**
     * API Server로부터 받은 code를 활용하여 사용자 인증 정보 요청
     * @param code API Server 에서 받아온 code
     * @return 임시!!! GoogleOAuthToken
	 * @throws JsonProcessingException 
     */
    GoogleOAuthToken requestAccessTokenAndParsing(String code) throws JsonProcessingException;
    
    /**
     * 
     * 받은 유저 정보를 DB에서체크
     */
    UserDto getUserInfo(ResponseEntity<String> userInfoRes) throws JsonProcessingException;
   
    
    default SocialLoginType type() {
    	if (this instanceof GoogleOauth) {
            return SocialLoginType.GOOGLE;
        } else {
            return null;
        }
    }
}
