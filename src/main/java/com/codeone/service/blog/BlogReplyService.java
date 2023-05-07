package com.codeone.service.blog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeone.dao.blog.BlogReplyDao;
import com.codeone.dto.blog.BlogReplyDto;

@Service
public class BlogReplyService {
	
	@Autowired
	BlogReplyDao dao;

	public boolean blogReplyWrite(BlogReplyDto dto) {
		return dao.writeReplyBlog(dto);
	}
	
	public List<BlogReplyDto> getAllBlogReply(int blogseq) {
		return dao.getAllBlogReply(blogseq);
	}
}
