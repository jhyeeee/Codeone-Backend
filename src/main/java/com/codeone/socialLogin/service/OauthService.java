package com.codeone.socialLogin.service;



import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;

import com.codeone.socialLogin.SocialLoginType;
import com.codeone.socialLogin.Token.GoogleOAuthToken;
import com.codeone.socialLogin.dao.SocialDao;
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
     * 어떤 소셜 로그인인지 확인하고 해당 소셜의 로그인 페이지로 이동 CORS처리 문제가 있음
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

    public GoogleOAuthToken requestAccessTokenAndParsing(SocialLoginType socialLoginType, String code) throws JsonProcessingException {
        SocialOauth socialOauth = this.findSocialOauthByType(socialLoginType);
        return socialOauth.requestAccessTokenAndParsing(code);
    }
    
    private SocialOauth findSocialOauthByType(SocialLoginType socialLoginType) {
        return socialOauthList.stream()
                .filter(x -> x.type() == socialLoginType)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("알 수 없는 SocialLoginType 입니다."));
    }
   
    

}