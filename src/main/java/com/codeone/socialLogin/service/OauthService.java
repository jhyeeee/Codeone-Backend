package com.codeone.socialLogin.service;



import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;

import com.codeone.dto.user.UserDto;
import com.codeone.socialLogin.SocialLoginType;
import com.codeone.socialLogin.Token.GoogleOAuthToken;
import com.codeone.socialLogin.Token.OAuthToken;
import com.codeone.socialLogin.oauth.SocialOauth;
import com.fasterxml.jackson.core.JsonProcessingException;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Service

@RequiredArgsConstructor
@Slf4j
public class OauthService {
    private final List<SocialOauth> socialOauthList;
    private final HttpServletResponse response;
    
    /**
     * 어떤 소셜 로그인인지 확인하고 해당 소셜의 로그인 페이지로 이동 
     * @param socialLoginType (GOOGLE, NAVER, KAKAO)
     */
    public void request(SocialLoginType socialLoginType) {
        SocialOauth socialOauth = this.findSocialOauthByType(socialLoginType);
        String redirectURL = socialOauth.getOauthRedirectURL();

        try {
            response.sendRedirect(redirectURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    /*
     *  각 소셜서비스에서 받은 코드를 가지고 user의 정보를 얻음
     * 
     */
    public HashMap<Integer, UserDto> requestUserInfo(SocialLoginType socialLoginType, String code) {
    	SocialOauth socialOauth = this.findSocialOauthByType(socialLoginType);
    	HashMap<Integer, UserDto> result = null;
    	try {
    		OAuthToken token =  socialOauth.requestAccessTokenAndParsing(code);
    		ResponseEntity<String> userInfores = socialOauth.requestUserInfo(token);
    		result = socialOauth.getUserInfo(userInfores);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return result;
    }
    
    
    
    /*
     * 소셜에서 받은 코드로 억세스 코드를 요청
     * 
     */
    public OAuthToken requestAccessTokenAndParsing(SocialLoginType socialLoginType, String code) throws JsonProcessingException {
        SocialOauth socialOauth = this.findSocialOauthByType(socialLoginType);
        return socialOauth.requestAccessTokenAndParsing(code);
    }
    
    

    /**
     * 소셜 로그인 타입을 설정함
     * 
     * @param socialLoginType
     * @return
     */
    private SocialOauth findSocialOauthByType(SocialLoginType socialLoginType) {
        return socialOauthList.stream()
                .filter(x -> x.type() == socialLoginType)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("알 수 없는 SocialLoginType 입니다."));
    }
   
    

}