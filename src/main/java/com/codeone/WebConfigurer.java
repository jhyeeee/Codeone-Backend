package com.codeone;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.codeone.interceptor.LoginInterceptor;

@Configuration
public class WebConfigurer implements WebMvcConfigurer{

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// 접속 크라이언트를 허가

		registry.addMapping("/**")
		.allowedOrigins("http://localhost:3000")
        .allowedMethods("GET", "POST", "PUT", "PATCH", "OPTIONS", "DELETE")
        .allowCredentials(true)
        .maxAge(3000);

	}
	
	@Override
	public void addInterceptors(InterceptorRegistry regi) {
		regi.addInterceptor(new LoginInterceptor())
		.excludePathPatterns("/", "/auth/*", "/user/logout", "/user/login", "/user/getSessionUser", "/user/loginAf");
	}
	
	
}
