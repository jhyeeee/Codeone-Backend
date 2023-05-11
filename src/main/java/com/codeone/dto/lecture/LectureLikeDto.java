package com.codeone.dto.lecture;

import java.io.Serializable;

import lombok.Data;

@Data
public class LectureLikeDto implements Serializable{
	private int seq;
	private String id;
	private String liketime;
}
