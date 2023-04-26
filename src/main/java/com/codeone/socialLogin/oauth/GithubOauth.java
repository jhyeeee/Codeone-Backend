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
import com.codeone.socialLogin.Token.GitHubOAuthToken;
import com.codeone.socialLogin.Token.OAuthToken;
import com.codeone.socialLogin.dto.GitUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@Component
@RequiredArgsConstructor
@Slf4j
public class GithubOauth implements SocialOauth {
	private final RestTemplate restTemplate;
	private final ObjectMapper objectMapper;
	private final UserDao dao;
    @Value("${sns.github.url}")
    private String GITHUB_SNS_BASE_URL;
    @Value("${sns.github.client.id}")
    private String GITHUB_SNS_CLIENT_ID;
    @Value("${sns.github.callback.url}")
    private String GITHUB_SNS_CALLBACK_URL;
    @Value("${sns.github.client.secret}")
    private String GITHUB_SNS_CLIENT_SECRET;
    @Value("${sns.github.token.url}")
    private String GITHUB_SNS_TOKEN_BASE_URL;
    @Value("${sns.github.scope.setting}")
    private String GITHUB_SCOPE_SETTING;    
    @Value("${sns.github.userInfo}")
    private String GITHUB_USERINFO_REQUEST_URL;

    
    @Override
    public String getOauthRedirectURL() {
        Map<String, Object> params = new HashMap<>();
//        params.put("scope", GITHUB_SCOPE_SETTING);
//        params.put("response_type", "code");
        params.put("client_id", GITHUB_SNS_CLIENT_ID);
        params.put("redirect_uri", GITHUB_SNS_CALLBACK_URL);
//        params.put("scope", GITHUB_SCOPE_SETTING);

        String parameterString = params.entrySet().stream()
                .map(x -> x.getKey() + "=" + x.getValue())
                .collect(Collectors.joining("&"));

        return GITHUB_SNS_BASE_URL + "?" + parameterString;
    }

    
    /*
     * 인증코드를 통해서 accessToken을 받는다.
     * 
     */
    @Override
    public GitHubOAuthToken requestAccessTokenAndParsing(String code) throws JsonProcessingException {
    	RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> params = new HashMap<>();
        params.put("code", code);
        params.put("client_id", GITHUB_SNS_CLIENT_ID);
        params.put("client_secret", GITHUB_SNS_CLIENT_SECRET);
        params.put("redirect_uri", GITHUB_SNS_CALLBACK_URL);
//        params.put("grant_type", "authorization_code");

        ResponseEntity<String> responseEntity =
                restTemplate.postForEntity(GITHUB_SNS_TOKEN_BASE_URL, params, String.class);
        // accessToken 값이 여기 들어 있다.
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
        	System.out.println(responseEntity.getBody() + " accessToken값");
//        	GitHubOAuthToken githubOAuthToken = objectMapper.readValue(responseEntity.getBody(), GitHubOAuthToken.class);
//        	System.out.println(githubOAuthToken.getAccess_token());
        	GitHubOAuthToken githubOAuthToken = new GitHubOAuthToken();
        	githubOAuthToken.setAccess_token(getAccessCode(responseEntity.getBody()));
            return githubOAuthToken;
        }
        return null;

    }

    
    @Override
    public ResponseEntity<String> requestUserInfo(OAuthToken oAuthToken) {
    	System.out.println("githubOauth RequestUserInfo");
        //header에 accessToken을 담는다.
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer "+oAuthToken.getAccess_token());
        log.info("Authorization: " + "Bearer "+oAuthToken.getAccess_token());

        //HttpEntity를 하나 생성해 헤더를 담아서 restTemplate으로 구글과 통신하게 된다.
        HttpEntity request = new HttpEntity(headers);

        ResponseEntity<String> userInfoRes = restTemplate.exchange(
        		GITHUB_USERINFO_REQUEST_URL,
                HttpMethod.GET,
                request,
                String.class
        );

        return userInfoRes;
    }
    

    
    
    /*
     * 토큰을 가지고 받능 정보를 통해 DB에서 확인해보기
     */
    @Override
    public HashMap<Integer, UserDto> getUserInfo(ResponseEntity<String> userInfoRes) throws JsonProcessingException {
    	GitUser gitUser = objectMapper.readValue(userInfoRes.getBody(), GitUser.class);
    	
    	int flg = 0; // 0은 기존, 1은 신구
    	UserDto user = checkEmail(gitUser.getEmail());
    	HashMap<Integer,UserDto> map = new HashMap<>();
    	System.out.println(user + " getUserInfo");
    	//DB확인해서 신규가입인경우 내용 세팅 아니면      	
    	if(Objects.isNull(user)) {
    		UserDto newUser = new UserDto();
    		flg = 1;
    		newUser.setEmail(gitUser.getEmail());
    		newUser.setName(gitUser.getName());
    		newUser.setFilename(gitUser.getAvatar_url());     	
        	System.out.println(user.getEmail());
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

    
    private String getAccessCode(String body) {
    	String access_code = body.split("&")[0].split("=")[1];
    	return access_code;
    }

}