package com.codeone.service.job;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeone.dao.job.TestDao;

@Service
public class TestService {
	//어플리케이션 : back의 로직 -> 서비스      트랜잭션처리
	@Autowired
	private TestDao dao;

	public Map<String, Object> insert(Map<String, Object> params) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String errCode = "100";
		String msg = "데이터 등록중 오류가 발생하였습니다.";
		//성공시 1 아니면 0 
		if(dao.insert(params) > 0) {
			errCode = "200";
			msg = "데이터 등록이 성공적으로 완료되었습니다.";
		}
		
		map.put("list", dao.list(params));
		map.put("msg", msg);
		map.put("status", errCode);
		return map;
	}
	
	public Map<String, Object> insert2(Map<String, Object> params2) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String errCode = "100";
		String msg = "데이터 등록중 오류가 발생하였습니다.";		
		if(dao.insert2(params2) > 0) {
			errCode = "200";
			msg = "데이터 등록이 성공적으로 완료되었습니다.";
		}
		map.put("testlist", dao.testList(params2));
		map.put("msg", msg);
		map.put("status", errCode);
		return map;
} 
	
	
	public Map<String, Object> testList(Map<String, Object> params) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("list", dao.testList(params));
		return map;
	}
}
