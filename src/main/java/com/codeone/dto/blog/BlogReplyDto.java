package com.codeone.dto.blog;

import java.io.Serializable;

public class BlogReplyDto  implements Serializable {
	private int seq;
	private int blogseq;
	private String content;
	private String writer;
	
	public BlogReplyDto() {
		super();
	}

	public BlogReplyDto(int seq, int blogseq, String content, String writer) {
		super();
		this.seq = seq;
		this.blogseq = blogseq;
		this.content = content;
		this.writer = writer;
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

	@Override
	public String toString() {
		return "BlogReplyDto [seq=" + seq + ", blogseq=" + blogseq + ", content=" + content + ", writer=" + writer
				+ "]";
	}
	
}
