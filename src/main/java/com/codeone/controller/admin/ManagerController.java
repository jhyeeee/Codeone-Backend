package com.codeone.controller.admin;

import java.io.BufferedOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.crypto.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.codeone.dto.admin.ManagerDto;
import com.codeone.dto.calendar.CalendarDto;
import com.codeone.service.admin.ManagerService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value="/admin")
public class ManagerController {
	
	@Autowired
	ManagerService service;
	
	// 아이디 중복여부 체크
	@PostMapping(value = "/checkId")
	public ResponseEntity<String> checkId(String id) throws Exception {
		System.out.println("ManagerController checkingId " + new Date());
		boolean isCheckId = service.checkId(id);
		
		System.out.println("///////id확인"+id);
		if (isCheckId) { // 중복된 아이디가 있음
			return ResponseEntity.status(HttpStatusCode.valueOf(204)).build();
		}
		// 아이디가 없으면
		return ResponseEntity.ok().build();
	}
	
	@PostMapping(value = "/addManager")
	public  ResponseEntity<String> addManager(ManagerDto dto) {
		System.out.println("ManagerController addManager " + new Date());	
		boolean isAddManager = service.addManager(dto);
		if(isAddManager) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatusCode.valueOf(204)).build();
	}
	
	@PostMapping(value="/adminLogin")
	public ManagerDto adminLogin(HttpServletRequest req, ManagerDto dto) {
		System.out.println("ManagerController adminLogin " + new Date());
		
		ManagerDto man = service.adminLogin(dto);
		System.out.println("///////admin login 확인 "+man);
		if (man != null) {
			req.getSession().setAttribute("login", man);
			req.getSession().setMaxInactiveInterval(7200);//세션 기본 30분, 개발위해 7200초 임시설정
			System.out.println("Login Success");
		}else {
			System.out.println("Login Fail");
		}
		return man;
	}
	
	@GetMapping("/allUser")
	public Map<String, Object> allUser() {
		System.out.println("ManagerController allUser() " + new Date());
	   
	    // Map으로 dto 전달
	    Map<String, Object> map = new HashMap<>();        
	    List<ManagerDto> list = service.allUser();
	    map.put("list", list);
	    System.out.println(map);
	    return map;                  // 성공
	}
	
	@GetMapping("/allManager")
	public Map<String, Object> allManager() {
		System.out.println("ManagerController allManager() " + new Date());
	   
	    // Map으로 dto 전달
	    Map<String, Object> map = new HashMap<>();        
	    List<ManagerDto> list = service.allManager();
	    map.put("list", list);
	    System.out.println(map);
	    return map;                  // 성공
	}

	@GetMapping("/allBan")
	public Map<String, Object> allBan() {
		System.out.println("ManagerController allBan() " + new Date());
	   
	    // Map으로 dto 전달
	    Map<String, Object> map = new HashMap<>();        
	    List<ManagerDto> list = service.allBan();
	    map.put("list", list);
	    System.out.println(map);
	    return map;                  // 성공
	}
	
	@GetMapping("/allBiz")
	public Map<String, Object> allBiz() {
		System.out.println("ManagerController allBiz() " + new Date());
	   
	    // Map으로 dto 전달
	    Map<String, Object> map = new HashMap<>();        
	    List<ManagerDto> list = service.allBiz();
	    map.put("list", list);
	    System.out.println(map);
	    return map;                  // 성공
	}

	
	@PostMapping(value = "/modifyName")
	public ResponseEntity<String> modifyName(ManagerDto dto){
		System.out.println("ManagerController modifyName() " + new Date());
		boolean isModifyName = service.modifyName(dto);
		if(isModifyName) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatusCode.valueOf(204)).build();

	}
	
	@PostMapping(value = "/modifyEmail")
	public ResponseEntity<String> modifyEmail(ManagerDto dto){
		System.out.println("ManagerController modifyEmail() " + new Date());
		boolean ismodifyEmail = service.modifyEmail(dto);
		if(ismodifyEmail) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatusCode.valueOf(204)).build();

	}
	
	@PostMapping(value = "/modifyContact")
	public ResponseEntity<String> modifyContact(ManagerDto dto){
		System.out.println("ManagerController modifyContact() " + new Date());
		boolean ismodifyContact = service.modifyContact(dto);
		if(ismodifyContact) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatusCode.valueOf(204)).build();

	}
	
	@PostMapping(value = "/modifyAuth")
	public ResponseEntity<String> modifyAuth(ManagerDto dto){
		System.out.println("ManagerController modifyAuth() " + new Date());
		boolean ismodifyAuth = service.modifyAuth(dto);
		if(ismodifyAuth) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatusCode.valueOf(204)).build();

	}
	
}
