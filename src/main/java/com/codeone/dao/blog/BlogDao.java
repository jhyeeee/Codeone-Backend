package com.codeone.dao.blog;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.codeone.dto.blog.BlogCategoryDto;
import com.codeone.dto.blog.BlogCategoryParam;
import com.codeone.dto.blog.BlogDto;
import com.codeone.dto.blog.BlogParam;

@Mapper
@Repository
public interface BlogDao {	
	boolean writeBlog(BlogDto dto);
	
	List<BlogDto> getAllBlogs(BlogParam param);
	
	BlogDto getBlog(int seq);
	
	boolean deleteBlog(int seq);
	
	boolean updateBlog(BlogDto dto);
	
	boolean updateBlogLikes(int seq);
	
	boolean deleteBlogLikes(int seq);
	
	List<BlogDto> getSearchBlogs(String searchTerm);
	
	List<BlogCategoryDto> getBlogCategory(BlogCategoryParam param);
	
}
