package com.codeone.dto.job;

import java.time.LocalDateTime;

public class JobLikeDto {
	private int seq;				// joblist의 seq
	
	private int likeseq;			// 좋아요 번호
	private int joblistseq;			// 채용글 번호
	private int memberseq;			// 로그인 번호
	private LocalDateTime regdate;	// 좋아요 등록시간

	
    public JobLikeDto() {}


	public JobLikeDto(int seq, int likeseq, int joblistseq, int memberseq, LocalDateTime regdate) {
		super();
		this.seq = seq;
		this.likeseq = likeseq;
		this.joblistseq = joblistseq;
		this.memberseq = memberseq;
		this.regdate = regdate;
	}


	public int getSeq() {
		return seq;
	}


	public void setSeq(int seq) {
		this.seq = seq;
	}


	public int getLikeseq() {
		return likeseq;
	}


	public void setLikeseq(int likeseq) {
		this.likeseq = likeseq;
	}


	public int getJoblistseq() {
		return joblistseq;
	}


	public void setJoblistseq(int joblistseq) {
		this.joblistseq = joblistseq;
	}


	public int getMemberseq() {
		return memberseq;
	}


	public void setMemberseq(int memberseq) {
		this.memberseq = memberseq;
	}


	public LocalDateTime getRegdate() {
		return regdate;
	}


	public void setRegdate(LocalDateTime regdate) {
		this.regdate = regdate;
	}

	

}