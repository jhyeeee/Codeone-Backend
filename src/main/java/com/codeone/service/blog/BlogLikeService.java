package com.codeone.service.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codeone.dao.blog.BlogDao;
import com.codeone.dao.blog.BlogLikeDao;
import com.codeone.dao.blog.BlogReplyDao;
import com.codeone.dto.blog.BlogLikeDto;

@Service
@Transactional
public class BlogLikeService {
	
	@Autowired
	BlogLikeDao dao;
	
	@Autowired
	BlogDao bdao;
	
	public boolean blogDoLike(BlogLikeDto dto) {
		boolean result = false;
		if(dao.blogDoLike(dto)) {
			result = bdao.updateBlogLikes(dto.getBlogseq());
		}
		return result ;
	}

	
	public boolean blogDoNotLike(BlogLikeDto dto) {
		boolean result = false;
		if(dao.blogDoNotLike(dto) > 0) {
			result = bdao.deleteBlogLikes(dto.getBlogseq());
		}
		
		return result ;
	}

	
	
	public boolean getWhoLikeThisBlog(BlogLikeDto dto) {
		
		int result = dao.getWhoLikeThisBlog(dto);
		
		if(result >0) {
			return true;
		} else {
			return false;
		}
	}
	
	
}
