package com.codeone.dao.blog;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.codeone.command.studygroup.StudygroupListCommand;
import com.codeone.dto.blog.BlogDto;
import com.codeone.dto.blog.BlogParam;
import com.codeone.dto.studygroup.StudygroupInfoDto;
import com.codeone.dto.studygroup.StudygroupListDto;
import com.codeone.dto.studygroup.StudygroupManagementDto;

@Mapper
@Repository	
public interface BloggroupManagementDao {
//	void insert(StudygroupManagementDto newStudygroupManagement);
//	void updateInfoSeq(StudygroupManagementDto newStudygroupManagement);
//	StudygroupManagementDto selectOne(int seq);
//	int deleteStudygroupRecruitment(int seq);
//	List<StudygroupListDto> selectAllStudygroupList(StudygroupListCommand studygroupListCommand);
//	void toggleIsClosed(int seq);
//	void increaseLikeAmount(int seq);
//	void decreaseLikeAmount(int seq);
//	void increaseCommentAmount(int seq);
//	void decreaseCommentAmount(int seq);
//	void updateIsVisible(StudygroupManagementDto studygroupManagement);
//	void updateAllClose(List<StudygroupInfoDto> list);
//	void increaseViewAmount(int seq);
	int getAmountByMemberEmail(String memberEmail);
	int getMyLikeBloggroupAmount(String memberEmail);
	List<BlogDto> selectAllMyBlogroupList(BlogParam param);
	List<BlogDto> getMyLikeBloggroupList(BlogParam param);
}
