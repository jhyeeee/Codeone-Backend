package com.codeone.dto.blog;

import java.io.Serializable;
import java.time.LocalDateTime;

public class BlogDto implements Serializable {
	private int seq;
	private String writer;
	private String title;
	private String content;
	private int category1;
	private int category2;
	private LocalDateTime regdate;	
	private int likes;
	private String tag;
	private int delf;
	private String thumbnail;
	
	
	
	public BlogDto() {
		super();
	}





	public BlogDto(int seq, String writer, String title, String content, int category1, int category2,
			LocalDateTime regdate, int likes, String tag, int delf, String thumbnail) {
		super();
		this.seq = seq;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.category1 = category1;
		this.category2 = category2;
		this.regdate = regdate;
		this.likes = likes;
		this.tag = tag;
		this.delf = delf;
		this.thumbnail = thumbnail;
	}





	public int getSeq() {
		return seq;
	}



	public void setSeq(int seq) {
		this.seq = seq;
	}



	public String getWriter() {
		return writer;
	}



	public void setWriter(String writer) {
		this.writer = writer;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public int getCategory1() {
		return category1;
	}



	public void setCategory1(int category1) {
		this.category1 = category1;
	}



	public int getCategory2() {
		return category2;
	}



	public void setCategory2(int category2) {
		this.category2 = category2;
	}



	public LocalDateTime getRegdate() {
		return regdate;
	}



	public void setRegdate(LocalDateTime regdate) {
		this.regdate = regdate;
	}



	public int getLikes() {
		return likes;
	}



	public void setLikes(int likes) {
		this.likes = likes;
	}



	public String getTag() {
		return tag;
	}



	public void setTag(String tag) {
		this.tag = tag;
	}



	public String getThumbnail() {
		return thumbnail;
	}





	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}





	public int getDelf() {
		return delf;
	}



	public void setDelf(int delf) {
		this.delf = delf;
	}





	@Override
	public String toString() {
		return "BlogDto [seq=" + seq + ", writer=" + writer + ", title=" + title + ", content=" + content
				+ ", category1=" + category1 + ", category2=" + category2 + ", regdate=" + regdate + ", likes=" + likes
				+ ", tag=" + tag + ", delf=" + delf + ", thumbnail=" + thumbnail + "]";
	}



}
