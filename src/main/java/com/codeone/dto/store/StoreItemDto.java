package com.codeone.dto.store;

import java.io.Serializable;

// store item db
/*
CREATE TABLE item (
		seq INT AUTO_INCREMENT PRIMARY KEY, 	
		id VARCHAR(50) NOT NULL,  		
		title VARCHAR(200) NOT NULL,  		
		content VARCHAR(4000) NOT NULL,  	
		price int NOT NULL,  		
		location VARCHAR(400) NOT NULL, 			
	    status int NOT NULL,
		wdate DATETIME NOT NULL,
	 	likecount int,
	    filename VARCHAR(100) ,
	    newfilename VARCHAR(500) ,
		delflg int not null				
	);
*/
public class StoreItemDto implements Serializable {

	// 중고인지 새거인지 여부 추가 itemcondition 추가함
	private int seq;
	private String id;				// 작성자 id
	private String title;			// 제목
	private String content;			// 내용
	private int price;				// 가격
	private String location;		// 판매위치
	private String itemcondition;	// 중고 "old", 새제품 "new"
	private String status;			// 판매여부
	private String wdate;			// 작성
	private int likecount;			// 좋아요 수
	private String filename;		// 사진
	private String newfilename;	
	private int delflg;				// 삭제여부 삭제된글:1
	
	public StoreItemDto() {
		// TODO Auto-generated constructor stub
	}

	public StoreItemDto(int seq, String id, String title, String content, int price, String location,
			String itemcondition, String status, String wdate, int likecount, String filename, String newfilename,
			int delflg) {
		super();
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.content = content;
		this.price = price;
		this.location = location;
		this.itemcondition = itemcondition;
		this.status = status;
		this.wdate = wdate;
		this.likecount = likecount;
		this.filename = filename;
		this.newfilename = newfilename;
		this.delflg = delflg;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getItemcondition() {
		return itemcondition;
	}

	public void setItemcondition(String itemcondition) {
		this.itemcondition = itemcondition;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getWdate() {
		return wdate;
	}

	public void setWdate(String wdate) {
		this.wdate = wdate;
	}

	public int getLikecount() {
		return likecount;
	}

	public void setLikecount(int likecount) {
		this.likecount = likecount;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getNewfilename() {
		return newfilename;
	}

	public void setNewfilename(String newfilename) {
		this.newfilename = newfilename;
	}

	public int getDelflg() {
		return delflg;
	}

	public void setDelflg(int delflg) {
		this.delflg = delflg;
	}

	@Override
	public String toString() {
		return "StoreItemDto [seq=" + seq + ", id=" + id + ", title=" + title + ", content=" + content + ", price="
				+ price + ", location=" + location + ", itemcondition=" + itemcondition + ", status=" + status
				+ ", wdate=" + wdate + ", likecount=" + likecount + ", filename=" + filename + ", newfilename="
				+ newfilename + ", delflg=" + delflg + "]";
	}

	
	
	
	
	
}


