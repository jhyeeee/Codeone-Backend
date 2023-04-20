package com.codeone.socialLogin.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SocialDao {
	
	// 소셜 로그인 한 사람이 이미 로그인한 사람인지 확인하기
	int checkUser(String email);

}
