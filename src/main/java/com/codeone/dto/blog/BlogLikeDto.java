package com.codeone.dto.blog;

import java.io.Serializable;

public class BlogLikeDto  implements Serializable {
	
	private int seq;
	private int blogseq;
	private String useremail;
	public BlogLikeDto() {
		super();
	}
	public BlogLikeDto(int seq, int blogseq, String useremail) {
		super();
		this.seq = seq;
		this.blogseq = blogseq;
		this.useremail = useremail;
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
	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	@Override
	public String toString() {
		return "BlogLikeDto [seq=" + seq + ", blogseq=" + blogseq + ", useremail=" + useremail + "]";
	}	
}
