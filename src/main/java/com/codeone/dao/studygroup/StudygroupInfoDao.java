package com.codeone.dao.studygroup;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.codeone.dto.studygroup.StudygroupInfoDto;

@Mapper
@Repository
public interface StudygroupInfoDao {
	int writeStudygroupRecruitment(StudygroupInfoDto studygroup);
	StudygroupInfoDto selectOneBySeq(int seq);
	int updateStudygroupRecruitment(StudygroupInfoDto studygroup);
	List<StudygroupInfoDto> selectAllClosed();
	
	// 좋아요한 모집글 캘린더에 불러오기
	public List<StudygroupInfoDto> getLikedInfo(int seq);
}
