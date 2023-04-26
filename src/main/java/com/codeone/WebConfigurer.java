package com.codeone;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigurer implements WebMvcConfigurer{

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// 접속 크라이언트를 허가
<<<<<<< HEAD
		registry.addMapping("/**").allowedOrigins("*")		
		.allowedMethods("GET", "POST", "PUT", "DELETE"); // PUT 메소드 허용
=======
		registry.addMapping("/**")
		.allowedOrigins("http://localhost:3000")
        .allowedMethods("GET", "POST", "PUT", "PATCH", "OPTIONS")
        .allowCredentials(true)
        .maxAge(3000);
>>>>>>> 28a478e5eab8bbdf80b7923e47285d2fa1a6f0a0
	}
	
	
}
