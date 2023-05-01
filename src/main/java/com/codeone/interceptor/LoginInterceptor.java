package com.codeone.interceptor;

import java.util.Objects;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.codeone.dto.user.UserDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
	
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		System.out.println("==================preHandle 시작===============");
		String token = req.getHeader("jwt");
		
		System.out.println(token + " preHandle");
		
		
		return true;
	}
	
	
	public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler, ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle시작");
		HttpSession session = req.getSession(false);
		if(Objects.isNull(session)) {
			System.out.println("세션이 왜 없지? post");
		}
	}
	
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			 Exception ex) throws Exception {
	}
}
