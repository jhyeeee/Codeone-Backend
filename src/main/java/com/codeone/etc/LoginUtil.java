package com.codeone.etc;

import com.codeone.dto.user.UserDto;
import com.codeone.exception.NotLoggedInException;

import jakarta.servlet.http.HttpSession;

public class LoginUtil {
	public static boolean isLogin(HttpSession session) {
		return session.getAttribute("user") != null;
	}
	
	public static UserDto getLoginUserInfo(HttpSession session) throws NotLoggedInException {
		if(!isLogin(session)) {
			throw new NotLoggedInException();
		}
		
		return (UserDto) session.getAttribute("user");
	}
}
