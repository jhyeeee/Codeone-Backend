package com.codeone.dao.admin;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.codeone.dto.blog.BlogDto;

@Mapper
@Repository
public interface BlogManageDao {	
	boolean writeBlog(BlogDto dto);
	
	List<BlogDto> getAllBlogs();
	
	List<BlogDto> getAllBlogsMng();
	
	BlogDto getBlog(int seq);
	
	boolean deleteBlog(int seq);
	boolean showHideBlog(int seq, int delf);
	
	boolean updateBlog(BlogDto dto);
}
