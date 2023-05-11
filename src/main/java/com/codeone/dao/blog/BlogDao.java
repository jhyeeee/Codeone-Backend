package com.codeone.dao.blog;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.codeone.dto.blog.BlogDto;

@Mapper
@Repository
public interface BlogDao {	
	boolean writeBlog(BlogDto dto);
	
	List<BlogDto> getAllBlogs();
	
	BlogDto getBlog(int seq);
	
	boolean deleteBlog(int seq);
	
	boolean updateBlog(BlogDto dto);
}
