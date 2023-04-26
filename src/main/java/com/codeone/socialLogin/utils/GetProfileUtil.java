package com.codeone.socialLogin.utils;

import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.codeone.dto.user.UserDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

@Component
public class GetProfileUtil {
	private OAuth20Service oauthService;
	
	// 인가 코드?
	public UserDto getUserProfile(String code) throws Exception {
		OAuth2AccessToken accessToken = oauthService.getAccessToken(code);
		
		OAuthRequest request = new OAuthRequest(Verb.GET, "https://www.googleapis.com/auth/userinfo.profile");
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

//		else if (this.sns.isNaver()) {
//			JsonNode resNode = rootNode.get("response");
//			user.setNaverid(resNode.get("id").asText());
//			user.setNickname(resNode.get("nickname").asText());
//			user.setEmail(resNode.get("email").asText());
//		}
		
		return user;
	}
}
