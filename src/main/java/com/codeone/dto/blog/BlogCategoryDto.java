package com.codeone.dto.blog;

public class BlogCategoryDto {
	
	private int seq;
	private int cate1seq;
	private String name;
	public BlogCategoryDto() {
		super();
	}
	public BlogCategoryDto(int seq, int cate1seq, String name) {
		super();
		this.seq = seq;
		this.cate1seq = cate1seq;
		this.name = name;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getCate1seq() {
		return cate1seq;
	}
	public void setCate1seq(int cate1seq) {
		this.cate1seq = cate1seq;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "BlogCategory2Dto [seq=" + seq + ", cate1seq=" + cate1seq + ", name=" + name + "]";
	}
	
	
	
}
