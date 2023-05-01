package com.codeone.socialLogin.oauth;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.json.JSONObject;
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
import com.codeone.socialLogin.Token.NaverOAuthToken;
import com.codeone.socialLogin.Token.OAuthToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@Component
@RequiredArgsConstructor
@Slf4j
public class NaverOauth implements SocialOauth {
	private final RestTemplate restTemplate;
	private final ObjectMapper objectMapper;
	private final UserDao dao;
    @Value("${sns.naver.url}")
    private String NAVER_SNS_BASE_URL;
    @Value("${sns.naver.client.id}")
    private String NAVER_SNS_CLIENT_ID;
    @Value("${sns.naver.callback.url}")
    private String NAVER_SNS_CALLBACK_URL;
    @Value("${sns.naver.client.secret}")
    private String NAVER_SNS_CLIENT_SECRET;
    @Value("${sns.naver.state}")
    private String NAVER_SNS_STATE;
    @Value("${sns.naver.token.url}")
    private String NAVER_SNS_TOKEN_BASE_URL;
    @Value("${sns.naver.userInfo}")
    private String NAVER_USERINFO_REQUEST_URL;
    
    
    @Override
    public String getOauthRedirectURL() {
        Map<String, Object> params = new HashMap<>();
//        params.put("scope", GOOGLE_SCOPE_SETTING);
        params.put("response_type", "code");
        params.put("client_id", NAVER_SNS_CLIENT_ID);
        params.put("redirect_uri", NAVER_SNS_CALLBACK_URL);
        params.put("state", NAVER_SNS_STATE);

        String parameterString = params.entrySet().stream()
                .map(x -> x.getKey() + "=" + x.getValue())
                .collect(Collectors.joining("&"));
        
        System.out.println(NAVER_SNS_BASE_URL + "?" + parameterString);
        return NAVER_SNS_BASE_URL + "?" + parameterString;
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
        params.add("client_id", NAVER_SNS_CLIENT_ID);
        params.add("client_secret", NAVER_SNS_CLIENT_SECRET);
        params.add("redirect_uri", NAVER_SNS_CALLBACK_URL);
        params.add("grant_type", "authorization_code");
        params.add("state", NAVER_SNS_STATE);
        
        
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity(params, headers);
        ResponseEntity<String> responseEntity =
                restTemplate.postForEntity(NAVER_SNS_TOKEN_BASE_URL, request, String.class);
        System.out.println(responseEntity.getBody());
        // accessToken 값이 여기 들어 있다.
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
        	System.out.println(responseEntity.getBody() + " accessToken값");
        	NaverOAuthToken naverOAuthToken = objectMapper.readValue(responseEntity.getBody(), NaverOAuthToken.class);

            return naverOAuthToken;
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
        		NAVER_USERINFO_REQUEST_URL,
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

		JSONObject response = (JSONObject) jObject.get("response");
		String nname = (String) response.get("name");
		String namail = (String) response.get("email");
		System.out.println(namail + " namail");
//		String nid = (String) response.get("id");
		String nmobile = (String) response.get("mobile");
		String image = (String) response.get("profile_image");
		
    	int flg = 0; // 0은 기존, 1은 신구
    	UserDto user = checkEmail(namail);
    	HashMap<Integer,UserDto> map = new HashMap<>();
    	System.out.println(user + " getUserInfo");
    	//DB확인해서 신규가입인경우 내용 세팅 아니면      	
    	if(Objects.isNull(user)) {
    		UserDto newUser = new UserDto();
    		flg = 1;
    		newUser.setEmail(namail);
    		newUser.setName(nname);
        	//user.setEmailKey(googleUser.getVerifiedEmail());
    		newUser.setFilename(image);
    		newUser.setPhoneNumber(nmobile);
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