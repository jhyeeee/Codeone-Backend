package com.codeone.dto.blog;

import java.io.Serializable;
import java.time.LocalDateTime;

public class BlogReplyDto  implements Serializable {
	private int seq;
	private int blogseq;
	private String content;
	private String writer;
	private LocalDateTime regdate;	
	private LocalDateTime renewdate;
	private String profileUrl;
	
	public BlogReplyDto() {
		super();
	}

	public BlogReplyDto(int seq, int blogseq, String content, String writer, LocalDateTime regdate,
			LocalDateTime renewdate, String profileUrl) {
		super();
		this.seq = seq;
		this.blogseq = blogseq;
		this.content = content;
		this.writer = writer;
		this.regdate = regdate;
		this.renewdate = renewdate;
		this.profileUrl = profileUrl;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getBlogseq() {
		return blogseq;
	}

	public void setBlogseq(int blogseq) {
		this.blogseq = blogseq;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public LocalDateTime getRegdate() {
		return regdate;
	}

	public void setRegdate(LocalDateTime regdate) {
		this.regdate = regdate;
	}

	public LocalDateTime getRenewdate() {
		return renewdate;
	}

	public void setRenewdate(LocalDateTime renewdate) {
		this.renewdate = renewdate;
	}

	public String getProfileUrl() {
		return profileUrl;
	}

	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}

	@Override
	public String toString() {
		return "BlogReplyDto [seq=" + seq + ", blogseq=" + blogseq + ", content=" + content + ", writer=" + writer
				+ ", regdate=" + regdate + ", renewdate=" + renewdate + ", profileUrl=" + profileUrl + "]";
	}
	
}
