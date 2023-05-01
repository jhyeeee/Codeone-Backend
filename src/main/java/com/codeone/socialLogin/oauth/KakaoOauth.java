package com.codeone.socialLogin.oauth;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.json.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.codeone.dao.user.UserDao;
import com.codeone.dto.user.UserDto;
import com.codeone.socialLogin.Token.GoogleOAuthToken;
import com.codeone.socialLogin.Token.KakaoOAuthToken;

import com.codeone.socialLogin.Token.OAuthToken;
import com.codeone.socialLogin.dto.GoogleUser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@Component
@RequiredArgsConstructor
@Slf4j
public class KakaoOauth implements SocialOauth {
	private final RestTemplate restTemplate;
	private final ObjectMapper objectMapper;
	private final UserDao dao;
    @Value("${sns.kakao.url}")
    private String KAKAO_SNS_BASE_URL;
    @Value("${sns.kakao.client.id}")
    private String KAKAO_SNS_CLIENT_ID;
    @Value("${sns.kakao.callback.url}")
    private String KAKAO_SNS_CALLBACK_URL;
    @Value("${sns.kakao.token.url}")
    private String KAKAO_SNS_TOKEN_BASE_URL;
    @Value("${sns.kakao.userInfo}")
    private String KAKAO_USERINFO_REQUEST_URL;
    
    
    @Override
    public String getOauthRedirectURL() {
        Map<String, Object> params = new HashMap<>();
        params.put("response_type", "code");
        params.put("client_id", KAKAO_SNS_CLIENT_ID);
        params.put("redirect_uri", KAKAO_SNS_CALLBACK_URL);

        String parameterString = params.entrySet().stream()
                .map(x -> x.getKey() + "=" + x.getValue())
                .collect(Collectors.joining("&"));
        
        System.out.println(KAKAO_SNS_BASE_URL + "?" + parameterString);
        return KAKAO_SNS_BASE_URL + "?" + parameterString;
    }

    
    /*
     * 인증코드를 통해서 accessToken을 받는다.
     * 
     */
    @Override
    public OAuthToken requestAccessTokenAndParsing(String code) throws JsonProcessingException {
    	RestTemplate restTemplate = new RestTemplate();
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    	MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", code);
        params.add("client_id", KAKAO_SNS_CLIENT_ID);
        params.add("redirect_uri", KAKAO_SNS_CALLBACK_URL);
        params.add("grant_type", "authorization_code");
        
        
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity(params, headers);
        ResponseEntity<String> responseEntity =
                restTemplate.postForEntity(KAKAO_SNS_TOKEN_BASE_URL, request, String.class);
        // accessToken 값이 여기 들어 있다.
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
        	System.out.println(responseEntity.getBody() + " accessToken값");
        	KakaoOAuthToken kakaoOAuthToken = objectMapper.readValue(responseEntity.getBody(), KakaoOAuthToken.class);

            return kakaoOAuthToken;
        }
        return null;

    }
  
    @Override
    public ResponseEntity<String> requestUserInfo(OAuthToken oAuthToken) {

        //header에 accessToken을 담는다.
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer "+oAuthToken.getAccess_token());
        log.info("Authorization: " + "Bearer "+oAuthToken.getAccess_token());
         
        //HttpEntity를 하나 생성해 헤더를 담아서 restTemplate으로 구글과 통신하게 된다.
        HttpEntity request = new HttpEntity(headers);

        ResponseEntity<String> userInfoRes = restTemplate.exchange(
        		KAKAO_USERINFO_REQUEST_URL,
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
    	System.out.println(userInfoRes.getBody() + " getUserInfo");
    	JSONObject jObject = new JSONObject(userInfoRes.getBody());

		JSONObject properties = (JSONObject) jObject.get("properties");
		String knickname = (String) properties.get("nickname");
		String kimage = (String) properties.get("thumbnail_image");
		JSONObject kakao_account = (JSONObject) jObject.get("kakao_account");
		String kemail = (String) kakao_account.get("email");
//		String nid = (String) response.get("id");
		
    	int flg = 0; // 0은 기존, 1은 신구
    	UserDto user = checkEmail(kemail);
    	HashMap<Integer,UserDto> map = new HashMap<>();
    	System.out.println(user + " getUserInfo");
    	//DB확인해서 신규가입인경우 내용 세팅 아니면      	
    	if(Objects.isNull(user)) {
    		UserDto newUser = new UserDto();
    		flg = 1;
    		newUser.setEmail(kemail);
    		newUser.setName(knickname);
        	//user.setEmailKey(googleUser.getVerifiedEmail());
    		newUser.setFilename(kimage);
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