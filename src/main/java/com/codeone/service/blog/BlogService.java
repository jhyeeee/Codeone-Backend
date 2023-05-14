package com.codeone.service.blog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codeone.dao.blog.BlogDao;
import com.codeone.dao.user.UserDao;
import com.codeone.dto.blog.BlogCategoryDto;
import com.codeone.dto.blog.BlogCategoryParam;
import com.codeone.dto.blog.BlogDto;
@Service
@Transactional
public class BlogService {
	
	@Autowired	
	private BlogDao dao;
	
	public boolean blogWrite(BlogDto dto) {
		return dao.writeBlog(dto);
	}
	
	public List<BlogDto> getAllBlogs() {
		return dao.getAllBlogs();
	}
	
	public BlogDto getBlog(int seq) {
		return dao.getBlog(seq);
	}
	
	public boolean deleteBlog (int seq) {
		return dao.deleteBlog(seq);
	}
	
	public boolean updateBlog (BlogDto dto) {
		return dao.updateBlog(dto);
	}
	
	public List<BlogDto> getSerchBlogs(String searchTerm) {
		return dao.getSearchBlogs(searchTerm);
	}
	
	public List<BlogCategoryDto> getBlogCategory(BlogCategoryParam param) {
		return dao.getBlogCategory(param);
	}
	
}
