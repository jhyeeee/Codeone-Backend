package com.codeone.socialLogin.oauth;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.codeone.dao.user.UserDao;
import com.codeone.dto.user.UserDto;
import com.codeone.socialLogin.Token.GoogleOAuthToken;
import com.codeone.socialLogin.Token.OAuthToken;
import com.codeone.socialLogin.dto.GoogleUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@Component
@RequiredArgsConstructor
@Slf4j
public class GoogleOauth implements SocialOauth {
	private final RestTemplate restTemplate;
	private final ObjectMapper objectMapper;
	private final UserDao dao;
    @Value("${sns.google.url}")
    private String GOOGLE_SNS_BASE_URL;
    @Value("${sns.google.client.id}")
    private String GOOGLE_SNS_CLIENT_ID;
    @Value("${sns.google.callback.url}")
    private String GOOGLE_SNS_CALLBACK_URL;
    @Value("${sns.google.client.secret}")
    private String GOOGLE_SNS_CLIENT_SECRET;
    @Value("${sns.google.token.url}")
    private String GOOGLE_SNS_TOKEN_BASE_URL;
    @Value("${sns.google.scope.setting}")
    private String GOOGLE_SCOPE_SETTING;
    @Value("${sns.google.userInfo}")
    private String GOOGLE_USERINFO_REQUEST_URL;
    
    @Override
    public String getOauthRedirectURL() {
        Map<String, Object> params = new HashMap<>();
        params.put("scope", GOOGLE_SCOPE_SETTING);
        params.put("response_type", "code");
        params.put("client_id", GOOGLE_SNS_CLIENT_ID);
        params.put("redirect_uri", GOOGLE_SNS_CALLBACK_URL);

        String parameterString = params.entrySet().stream()
                .map(x -> x.getKey() + "=" + x.getValue())
                .collect(Collectors.joining("&"));

        return GOOGLE_SNS_BASE_URL + "?" + parameterString;
    }

    
    /*
     * 인증코드를 통해서 accessToken을 받는다.
     * 
     */
    @Override
    public GoogleOAuthToken requestAccessTokenAndParsing(String code) throws JsonProcessingException {
    	RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> params = new HashMap<>();
        params.put("code", code);
        params.put("client_id", GOOGLE_SNS_CLIENT_ID);
        params.put("client_secret", GOOGLE_SNS_CLIENT_SECRET);
        params.put("redirect_uri", GOOGLE_SNS_CALLBACK_URL);
        params.put("grant_type", "authorization_code");

        ResponseEntity<String> responseEntity =
                restTemplate.postForEntity(GOOGLE_SNS_TOKEN_BASE_URL, params, String.class);
        // accessToken 값이 여기 들어 있다.
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
        	System.out.println(responseEntity.getBody() + " accessToken값");
        	GoogleOAuthToken googleOAuthToken = objectMapper.readValue(responseEntity.getBody(), GoogleOAuthToken.class);
            return googleOAuthToken;
        }
        return null;

    }

    
    /*
     * accessToken 값을 가지고 유저의 정보를 얻음
     */
    @Override
    public ResponseEntity<String> requestUserInfo(OAuthToken oAuthToken) {
    	System.out.println("GoogleOauth RequestUserInfo");
        //header에 accessToken을 담는다.
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer "+oAuthToken.getAccess_token());
        log.info("Authorization: " + "Bearer "+oAuthToken.getAccess_token());

        //HttpEntity를 하나 생성해 헤더를 담아서 restTemplate으로 구글과 통신하게 된다.
        HttpEntity request = new HttpEntity(headers);

        ResponseEntity<String> userInfoRes = restTemplate.exchange(
                GOOGLE_USERINFO_REQUEST_URL,
                HttpMethod.GET,
                request,
                String.class
        );

        log.info("response.getBody() = " + userInfoRes.getBody());
        log.info("response = " + userInfoRes);
        return userInfoRes;
    }
    

    
    
    /*
     * 토큰을 가지고 받능 정보를 통해 DB에서 확인해보기
     */
    @Override
    public HashMap<Integer, UserDto> getUserInfo(ResponseEntity<String> userInfoRes) throws JsonProcessingException {
    	GoogleUser googleUser = objectMapper.readValue(userInfoRes.getBody(), GoogleUser.class);
    	int flg = 0; // 0은 기존, 1은 신구
    	UserDto user = checkEmail(googleUser.getEmail());
    	HashMap<Integer,UserDto> map = new HashMap<>();
    	System.out.println(user + " getUserInfo");
    	//DB확인해서 신규가입인경우 내용 세팅 아니면      	
    	if(Objects.isNull(user)) {
    		UserDto newUser = new UserDto();
    		flg = 1;
    		newUser.setEmail(googleUser.getEmail());
    		newUser.setName(googleUser.getName());
        	//user.setEmailKey(googleUser.getVerifiedEmail());
    		newUser.setFilename(googleUser.getPicture());
    		newUser.setId(googleUser.getId());        	
        	System.out.println(newUser.getEmail());
        	map.put(flg, newUser);
    	}else {
    		map.put(flg, user);
    	}
    	return map;
    }
    
    /*
     * DB체크
     * 
     */
    @Override
    public UserDto checkEmail(String email) {    	
    	return dao.checkEmail(email);
    }


}