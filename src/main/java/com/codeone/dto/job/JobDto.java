package com.codeone.dto.job;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class JobDto extends JobFilterDto implements Serializable{
	
	private int seq;
	private String comid;
	private String title;
	private String content;
	private String startline;	
	private String deadline;
	private String wdate;
	private int comdel;
	
// company
	private String comname;
	private String comimage;
	private String comcate;
	private String comjobname;
	private String comcareer;
	private String comlocation;
	private String comskill;
	private String comtag;
	private String comsalary;
	private String comfile;
	private int commapx;
	private int commapy;

	
	public JobDto() {
	}


	public JobDto(int seq, String comid, String title, String content, String startline, String deadline, String wdate,
			int comdel, String comname, String comimage, String comcate, String comjobname, String comcareer,
			String comlocation, String comskill, String comtag, String comsalary, String comfile, int commapx,
			int commapy) {
		super();
		this.seq = seq;
		this.comid = comid;
		this.title = title;
		this.content = content;
		this.startline = startline;
		this.deadline = deadline;
		this.wdate = wdate;
		this.comdel = comdel;
		this.comname = comname;
		this.comimage = comimage;
		this.comcate = comcate;
		this.comjobname = comjobname;
		this.comcareer = comcareer;
		this.comlocation = comlocation;
		this.comskill = comskill;
		this.comtag = comtag;
		this.comsalary = comsalary;
		this.comfile = comfile;
		this.commapx = commapx;
		this.commapy = commapy;
	}


	public int getSeq() {
		return seq;
	}


	public void setSeq(int seq) {
		this.seq = seq;
	}


	public String getComid() {
		return comid;
	}


	public void setComid(String comid) {
		this.comid = comid;
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


	public String getStartline() {
		return startline;
	}


	public void setStartline(String startline) {
		this.startline = startline;
	}


	public String getDeadline() {
		return deadline;
	}


	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}


	public String getWdate() {
		return wdate;
	}


	public void setWdate(String wdate) {
		this.wdate = wdate;
	}


	public int getComdel() {
		return comdel;
	}


	public void setComdel(int comdel) {
		this.comdel = comdel;
	}


	public String getComname() {
		return comname;
	}


	public void setComname(String comname) {
		this.comname = comname;
	}


	public String getComimage() {
		return comimage;
	}


	public void setComimage(String comimage) {
		this.comimage = comimage;
	}


	public String getComcate() {
		return comcate;
	}


	public void setComcate(String comcate) {
		this.comcate = comcate;
	}


	public String getComjobname() {
		return comjobname;
	}


	public void setComjobname(String comjobname) {
		this.comjobname = comjobname;
	}


	public String getComcareer() {
		return comcareer;
	}


	public void setComcareer(String comcareer) {
		this.comcareer = comcareer;
	}


	public String getComlocation() {
		return comlocation;
	}


	public void setComlocation(String comlocation) {
		this.comlocation = comlocation;
	}


	public String getComskill() {
		return comskill;
	}


	public void setComskill(String comskill) {
		this.comskill = comskill;
	}


	public String getComtag() {
		return comtag;
	}


	public void setComtag(String comtag) {
		this.comtag = comtag;
	}


	public String getComsalary() {
		return comsalary;
	}


	public void setComsalary(String comsalary) {
		this.comsalary = comsalary;
	}


	public String getComfile() {
		return comfile;
	}


	public void setComfile(String comfile) {
		this.comfile = comfile;
	}


	public int getCommapx() {
		return commapx;
	}


	public void setCommapx(int commapx) {
		this.commapx = commapx;
	}


	public int getCommapy() {
		return commapy;
	}


	public void setCommapy(int commapy) {
		this.commapy = commapy;
	}


	@Override
	public String toString() {
		return "JobDto [seq=" + seq + ", comid=" + comid + ", title=" + title + ", content=" + content + ", startline="
				+ startline + ", deadline=" + deadline + ", wdate=" + wdate + ", comdel=" + comdel + ", comname="
				+ comname + ", comimage=" + comimage + ", comcate=" + comcate + ", comjobname=" + comjobname
				+ ", comcareer=" + comcareer + ", comlocation=" + comlocation + ", comskill=" + comskill + ", comtag="
				+ comtag + ", comsalary=" + comsalary + ", comfile=" + comfile + ", commapx=" + commapx + ", commapy="
				+ commapy + "]";
	}

	
		
	
}