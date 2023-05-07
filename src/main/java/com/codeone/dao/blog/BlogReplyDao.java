package com.codeone.dao.blog;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.codeone.dto.blog.BlogDto;
import com.codeone.dto.blog.BlogReplyDto;

@Mapper
@Repository
public interface BlogReplyDao {
	boolean writeReplyBlog(BlogReplyDto dto);
	
	List getAllBlogReply(int blogseq);
}
