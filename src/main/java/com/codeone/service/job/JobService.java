package com.codeone.service.job;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeone.dao.job.JobDao;

@Service
public class JobService {
	@Autowired
	private JobDao dao;
	//채용 글목록
	public Map<String, Object> joblist(Map<String, Object>params) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", dao.job_list(params));
		return map;
	}
	//채용 상세보기
	public Map<String, Object> view(Map<String, Object> params) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		
		map.put("view", dao.view(params));
		return map;
	}

//	public Map<String, Object> insert(Map<String, Object> params) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		String err = "100";
//		String msg = "데이터 등록중 오류가 발생하였습니다.";
//		
//		if(dao.insert(params) > 0) {
//			err = "500";
//			 msg = "데이터 등록이 완료되였습니다.";
//		}
//		
//		map.put("err", err);
//		map.put("msg", msg);
//		return map;
//	}
//
//	public Map<String, Object> update(Map<String, Object> params) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		String err = "100";
//		String msg = "데이터 수정중 오류가 발생하였습니다.";
//		
//		if(dao.update(params) > 0) {
//			err = "500";	
//			 msg = "데이터 수정이 완료되였습니다.";
//		}
//		
//		map.put("err", err);
//		map.put("msg", msg);
//		return map;
//	}
//
//	public Map<String, Object> delete(Map<String, Object> params) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		String err = "100";
//		String msg = "데이터 삭제중 오류가 발생하였습니다.";
//		
//		if(dao.delete(params) > 0) {
//			err = "500";
//			 msg = "데이터 삭제이 완료되였습니다.";
//		}
//		
//		map.put("err", err);
//		map.put("msg", msg);
//		
//		return map;
//	}

	//필터
	public Map<String, Object> code_list(Map<String, Object> params) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("list", dao.code_list(params));
		return map;
	}
	
	
	//좋아요
	public Map<String,Object> update_Like(Map<String, Object> params) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String err = "100";
		String msg = "데이터 추가 오류가 발생하였습니다.";
		
		if(dao.update_Like(params) > 0) {
			if(dao.insert_Like(params) > 0) {
				err = "500";	
				msg = "데이터 수정이 완료되였습니다.";
			}
		}
		
		map.put("err", err);
		map.put("msg", msg);
		return map;
	}

	
	
	public Map<String,Object> update_UnLike(Map<String, Object> params) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String err = "100";
		String msg = "데이터 추가 오류가 발생하였습니다.";
		
		if(dao.update_UnLike(params) > 0) {
			if(dao.delete_Like(params) > 0) {
				err = "500";	
				msg = "데이터 수정이 완료되였습니다.";
			}
		}
		
		map.put("err", err);
		map.put("msg", msg);
		return map;
	}

	
}
