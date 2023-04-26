package com.codeone.service.job;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codeone.dao.job.JobComDao;
import com.codeone.dto.job.ComPagingDto;
import com.codeone.dto.job.JobDto;
import com.codeone.dto.job.JobFilterDto;
	
	@Service
	@Transactional
	public class JobComService {
		
		@Autowired
		private JobComDao dao;
		
    
		//기업회원 글목록    
	    public List<JobDto> combbslist(ComPagingDto paging){
	    	return dao.combbslist(paging);
	    }
	    //기업회원 글 총수(페이징 위해)
	    public int getAllComBbs(ComPagingDto paging) {
			return dao.getAllComBbs(paging);
		}	
		//기업회원 글작성
		public boolean writeJob(JobDto job) {
			int n = dao.writeJob(job);
			return n>0?true:false;			
		}
		//기업회원 글수정
		public boolean updateJob(JobDto job) { 
			int n = dao.updateJob(job);
			return n>0?true:false;			
		}
		//기업회원 글삭제
		public boolean deleteJob(int comseq) {
			int n = dao.deleteJob(comseq);
			return n>0?true:false;
		}
		
		
	}




