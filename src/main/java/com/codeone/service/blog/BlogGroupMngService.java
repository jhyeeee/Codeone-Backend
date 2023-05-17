package com.codeone.service.blog;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codeone.command.studygroup.StudygroupListCommand;
import com.codeone.dao.blog.BloggroupManagementDao;
import com.codeone.dto.blog.BlogDto;
import com.codeone.dto.blog.BlogParam;
import com.codeone.dto.studygroup.StudygroupInfoDto;
import com.codeone.dto.studygroup.StudygroupListDto;
import com.codeone.dto.studygroup.StudygroupManagementDto;
import com.codeone.exception.NotPermissionToModifyException;
import com.codeone.exception.UnmodifiableConditionException;

@Service
@Transactional
public class BlogGroupMngService  {
	
	@Autowired
	BloggroupManagementDao dao;
	
	// 내가 쓴 블로그 글 갯수
	public int getMyBloggroupAmount(String memberEmail) {
		return dao.getAmountByMemberEmail(memberEmail);
	}
	
	// 내가 쓴 블로그 가져오기
	public List<BlogDto> getMyBloggroupList(BlogParam param) {
		return dao.selectAllMyBlogroupList(param);
	}
	
	
	// 내가 좋아요한 블로그 글 갯수
	public int getMyLikeBloggroupAmount(String memberEmail) {
		return dao.getMyLikeBloggroupAmount(memberEmail);
	}
	
	
	
	
	// 내가 좋아요한 블로그 가져오기
	public List<BlogDto> getMyLikeBloggroupList(BlogParam param) {
		return dao.getMyLikeBloggroupList(param);
	}
//	
//	public void toggleIsClosed(int seq, int memberSeq) throws NotPermissionToModifyException, UnmodifiableConditionException {
//		// 요청한 스터디 그룹의 마감 여부를 전환할 수 있는 권한이 있는지 확인
//		StudygroupManagementDto studygroupManagement = checkPermissionToModify(seq, memberSeq);
//		if(studygroupManagement == null) {
//			// 마감 여부를 전환할 권한이 없다면
//			throw new NotPermissionToModifyException();
//		}
//		
//		LocalDate now = LocalDate.now();
//		
//		StudygroupInfoDto studygroupInfo = studygroupInfoDao.selectOneBySeq(studygroupManagement.getInfoSeq());
//		
//		LocalDate deadlineForRecruitment = studygroupInfo.getDeadlineForRecruitment();
//		if(deadlineForRecruitment.isBefore(now)) {
//			// 모집 마감 날짜가 이미 지났을 경우
//			throw new UnmodifiableConditionException();
//		}
//		
//		// 마감 여부 전환
//		studygroupManagementDao.toggleIsClosed(seq);
//	}
//
//	public void increaseViewAmount(int seq) {
//		studygroupManagementDao.increaseViewAmount(seq);
//	}
}
