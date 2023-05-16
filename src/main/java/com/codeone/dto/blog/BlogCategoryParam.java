package com.codeone.dto.blog;

public class BlogCategoryParam {	
	private int catetype;
	private int cate1seq;
	
	
	
	public BlogCategoryParam() {
		super();
	}



	public BlogCategoryParam(int catetype, int cate1seq) {
		super();
		this.catetype = catetype;
		this.cate1seq = cate1seq;
	}



	public int getCatetype() {
		return catetype;
	}



	public void setCatetype(int catetype) {
		this.catetype = catetype;
	}



	public int getCate1seq() {
		return cate1seq;
	}



	public void setCate1seq(int cate1seq) {
		this.cate1seq = cate1seq;
	}



	@Override
	public String toString() {
		return "BlogCategoryParam [catetype=" + catetype + ", cate1seq=" + cate1seq + "]";
	}
	
	
	
	
}
