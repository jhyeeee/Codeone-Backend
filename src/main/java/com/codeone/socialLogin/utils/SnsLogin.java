package com.codeone.socialLogin.utils;
import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;

import com.codeone.dto.user.UserDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
public class SnsLogin {
	
	private OAuth20Service oauthService;
	private SnsValue sns;
	
	public SnsLogin(SnsValue sns) {
		this.oauthService = new ServiceBuilder(sns.getClientId())
				.apiSecret(sns.getClientSecret())
				.callback(sns.getRedirectUrl())
				.scope("profile")
				.build(sns.getApi20Instance());
		
		this.sns = sns;
	}
	
	public UserDto getUserProfile(String code) throws Exception {
		OAuth2AccessToken accessToken = oauthService.getAccessToken(code);
		
		OAuthRequest request = new OAuthRequest(Verb.GET, this.sns.getProfileUrl());
		oauthService.signRequest(accessToken, request);
		
		Response response = oauthService.execute(request);
		System.out.println(response.getBody());
		return parseJson(response.getBody());
	}
	
	
	private UserDto parseJson(String body) throws Exception {
		System.out.println("============================\n" + body + "\n==================");
		UserDto user = new UserDto();
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.readTree(body);
		
		if (this.sns.isGoogle()) {
			String id = rootNode.get("id").asText();
//			if (sns.isGoogle())
//				user.setId(id);
			user.setId(rootNode.get("displayName").asText());
			JsonNode nameNode = rootNode.path("name");
			String uname = nameNode.get("familyName").asText() + nameNode.get("givenName").asText();
			user.setName(uname);

			Iterator<JsonNode> iterEmails = rootNode.path("emails").elements();
			while(iterEmails.hasNext()) {
				JsonNode emailNode = iterEmails.next();
				String type = emailNode.get("type").asText();
				if (StringUtils.equals(type, "account")) {
					user.setEmail(emailNode.get("value").asText());
					break;
				}
			}
			
		} 
//		else if (this.sns.isNaver()) {
//			JsonNode resNode = rootNode.get("response");
//			user.setNaverid(resNode.get("id").asText());
//			user.setNickname(resNode.get("nickname").asText());
//			user.setEmail(resNode.get("email").asText());
//		}
		
		return user;
	}
}
