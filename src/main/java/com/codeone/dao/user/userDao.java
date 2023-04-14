package com.codeone.dao.user;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.codeone.dto.user.userDto;

@Mapper
@Repository
public interface userDao {
	
	// 회원가입 전 이메일 중복체크
	int checkEmail(String email);
	
	// 이메일 중복체크
	int checkId(String email);
	
	int addUser(userDto dto);
	
	int updateEmailKey(userDto dto);
	
	userDto getMember(String email);
}
