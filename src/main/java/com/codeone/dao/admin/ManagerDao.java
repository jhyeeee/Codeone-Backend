package com.codeone.dao.admin;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.codeone.dto.admin.ManagerDto;


@Mapper
public interface ManagerDao {

	// 아이디 중복여부 체크
	int checkId(String id);
	
	// 관리자 회원가입
	int addManager(ManagerDto dto);
	
	// 관리자 로그인
	ManagerDto adminLogin (ManagerDto dto);
	
	List<ManagerDto> allUser();
	List<ManagerDto> allManager();
	List<ManagerDto> allBan();
	List<ManagerDto> allBiz();
	
	int modifyName(ManagerDto dto);
	int modifyEmail(ManagerDto dto);
	int modifyPhone(ManagerDto dto);
	int modifyAuth(ManagerDto dto);
}
