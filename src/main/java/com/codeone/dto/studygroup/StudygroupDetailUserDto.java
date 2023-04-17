package com.codeone.dto.studygroup;

import java.io.Serializable;

import lombok.Data;

@Data
public class StudygroupDetailUserDto implements Serializable {
	private String filename;	// 스터디 그룹 팀장 프로필 이미지
	private String id;			// 스터디 그룹 팀장 닉네임
}
