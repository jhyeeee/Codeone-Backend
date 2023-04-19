package com.codeone.dao.user;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.codeone.dto.user.UserDto;

@Mapper
@Repository
public interface UserDao {
	
	// 회원가입 전 이메일 중복체크
	int checkEmail(String email);
	
	// 이메일 중복체크
	int checkId(String id);
	
	int addUser(UserDto dto);
	
	int updateEmailKey(UserDto dto);
	

	int updateEmailAuth(UserDto dto);
	
	UserDto getMember(String email);
	
	
	UserDto selectOneBySeq(int seq);

}
