package com.codeone.service.job;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeone.dao.job.JobDao;
import com.codeone.dto.job.JobDto;
import com.codeone.dto.job.JobParam;
import com.codeone.dto.job.JobResponse;

@Service
public class JobService {
	@Autowired
	private JobDao dao;

	
	// 채용 글목록 페이징후
	public List<JobDto> joblist(JobParam param) {
		return dao.job_list(param);
	}
	
	//채용일정 목록
	public Map<String, Object> jobcalendar(Map<String, Object>params) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", dao.jobcalendar_list(params));
		return map;
	}
 
	//채용 글의 총수
		public int getAllJobCount(JobParam param) {
			return dao.getAllJobCount(param);
		}
		
	
	//채용 update페이지 상세보기
//	public JobResponse job(int id) {
//		JobDto job = dao.job(id).orElse(new JobDto());	
//		System.out.println("codeName : " + job.getComjobname());
//		List<Map<String, Object>> codeNameList = dao.code_list(Map.of("codeName", job.getComjobname()));
//				
//		JobResponse response = JobResponse.of(job);
//		
//		if (!codeNameList.isEmpty()) {
//			Map<String, Object> codeName = codeNameList.get(0); 
//						
//			response.setComjobname(codeName);			
//			response.getComjobname().values().stream().forEach(it -> System.out.println("value: " + it));
//		}
//		
//		return response;
//	}
//	
		//채용 update페이지 상세보기
	public JobResponse job(int id) {
		JobDto job = dao.job(id).orElse(new JobDto());	
		System.out.println("codeName : " + job.getComjobname());
		List<Map<String, Object>> codeNameList = dao.code_list(Map.of("codeName", job.getComjobname()));
		List<Map<String, Object>> careerList = dao.code_list(Map.of("career", job.getComcareer()));
		List<Map<String, Object>> locationList = dao.code_list(Map.of("comLocation", job.getComlocation()));
		List<Map<String, Object>> skillList = dao.code_list(Map.of("comSkill", job.getComskill()));
		List<Map<String, Object>> tagList = dao.code_list(Map.of("comTag", job.getComtag()));
		
			
		JobResponse response = JobResponse.of(job);
		
		// codeNameList가 비어있지 않다면, response에 값을 설정
		if (!codeNameList.isEmpty()) {
			Map<String, Object> codeName = codeNameList.get(0); 			
			response.setComjobname(codeName);	
			
			// Debugging용 코드
			response.getComjobname().values().stream().forEach(it -> System.out.println("value: " + it));
		}
		
		// careerList가 비어있지 않다면, response에 값을 설정
	    if (!careerList.isEmpty()) {
	        Map<String, Object> career = careerList.get(0);          
	        response.setComcareer(career);            

	        // Debugging용 코드
	        response.getComcareer().values().stream().forEach(it -> System.out.println("career value: " + it));
	    }
	    
	    // locationList가 비어있지 않다면, response에 값을 설정
	    if (!locationList.isEmpty()) {
	        Map<String, Object> comLocation = locationList.get(0);           
	        response.setComlocation(comLocation);            

	        // Debugging용 코드
	        response.getComlocation().values().stream().forEach(it -> System.out.println("comLocation value: " + it));
	    }

	    if (!skillList.isEmpty()) {
	        Map<String, Object> comSkill = skillList.get(0);           
	        response.setComskill(comSkill);            

	        // Debugging용 코드
	        response.getComskill().values().stream().forEach(it -> System.out.println("comLocation value: " + it));
	    }
	    

	    if (!tagList.isEmpty()) {
	        Map<String, Object> comTag = tagList.get(0);           
	        response.setComtag(comTag);            

	        // Debugging용 코드
	        response.getComtag().values().stream().forEach(it -> System.out.println("comLocation value: " + it));
	    }


		return response;
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
	
	//whoLiked 수정
	public boolean whoLiked(JobDto job) { 
		int n = dao.update_whoLiked(job);
		return n>0?true:false;			
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
	
	// 좋아요한 채용공고 일정관리에 등록
	public List<JobDto> getCalendarjobList(String id){
		return dao.getCalendarjobList(id);
	}
	
}
