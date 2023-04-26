package com.codeone.socialLogin.Token;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

//네이버에 일회성 코드를 다시 보내 받아올 액세스 토큰을 포함한 JSON 문자열을 담을 클래스
@AllArgsConstructor
@Getter
@Setter
public class NaverOAuthToken implements OAuthToken{
    private String access_token;
    private int expires_in;
    private String scope;
    private String token_type;
    private String id_token;
}