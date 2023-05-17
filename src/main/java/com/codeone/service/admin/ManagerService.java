package com.codeone.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codeone.dao.admin.ManagerDao;
import com.codeone.dto.admin.ManagerDto;



@Service
@Transactional
public class ManagerService {
	
	@Autowired
	ManagerDao dao;
	
	// 아이디 중복여부 체크
	public boolean checkId(String id) {
		int n = dao.checkId(id);
		return n>0?true:false;
	}
	
	// 관리자 회원가입
	public boolean addManager(ManagerDto dto) {
		int n = dao.addManager(dto);
		return n>0?true:false;
	}
	
	// 관리자 로그인
	public ManagerDto adminLogin(ManagerDto dto) {
		return dao.adminLogin(dto);
	}
	
	public List<ManagerDto> allUser(){
		return dao.allUser();
	}
	
	public List<ManagerDto> allManager(){
		return dao.allManager();
	}
	
	public List<ManagerDto> allBiz(){
		return dao.allBiz();
	}
	
	public List<ManagerDto> allBan(){
		return dao.allBan();
	}
	
	public boolean modifyName(ManagerDto dto) {
		int count = dao.modifyName(dto);
		return count>0?true:false;
	}

	public boolean modifyContact(ManagerDto dto) {
		int count = dao.modifyPhone(dto);
		return count>0?true:false;
	}

	public boolean modifyEmail(ManagerDto dto) {
		int count = dao.modifyEmail(dto);
		return count>0?true:false;
	}

	public boolean modifyAuth(ManagerDto dto) {
		int count = dao.modifyAuth(dto);
		return count>0?true:false;
	}

}
