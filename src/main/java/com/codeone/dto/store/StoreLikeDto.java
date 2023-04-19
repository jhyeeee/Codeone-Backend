package com.codeone.dto.store;

import java.io.Serializable;

public class StoreLikeDto implements Serializable{
	
	private int seq;
	private String id;
	private String liketime;
	
	public StoreLikeDto() {
		// TODO Auto-generated constructor stub
	}

	public StoreLikeDto(int seq, String id, String liketime) {
		super();
		this.seq = seq;
		this.id = id;
		this.liketime = liketime;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLiketime() {
		return liketime;
	}

	public void setLiketime(String liketime) {
		this.liketime = liketime;
	}

	@Override
	public String toString() {
		return "StoreLikeDto [seq=" + seq + ", id=" + id + ", liketime=" + liketime + "]";
	}
	
	
}
