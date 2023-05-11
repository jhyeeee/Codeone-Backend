package com.codeone.dao.blog;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.codeone.dto.blog.BlogLikeDto;

@Mapper
@Repository
public interface BlogLikeDao {
	
	boolean blogDoLike(BlogLikeDto dto);
	int blogDoNotLike(BlogLikeDto dto);
	
	int getWhoLikeThisBlog(BlogLikeDto dto);
}
