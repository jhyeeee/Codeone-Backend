package com.codeone.dto.job;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class JobDto implements Serializable {
	
	private int seq;
	private String comid;
	private String title;
	private String content;
	private Date startline;	
	private Date deadline;
	private Timestamp comcreated;
	private Timestamp comupdated;
	private int comdel;
	
// company
	private String comsalary;
	private String comname;
	private String comcate;
	private String comjobname;
	private String comcareer;
	private String comlocation;
	private String comskill;
	private String comtag;
	private String comimage;
	private String comfile;
	private int commapx;
	private int commapy;

	
	public JobDto() {
	}


	public JobDto(int seq, String comid, String title, String content, Date startline, Date deadline,
			Timestamp comcreated, Timestamp comupdated, int comdel, String comsalary, String comname, String comcate,
			String comjobname, String comcareer, String comlocation, String comskill, String comtag, String comimage,
			String comfile, int commapx, int commapy) {
		super();
		this.seq = seq;
		this.comid = comid;
		this.title = title;
		this.content = content;
		this.startline = startline;
		this.deadline = deadline;
		this.comcreated = comcreated;
		this.comupdated = comupdated;
		this.comdel = comdel;
		this.comsalary = comsalary;
		this.comname = comname;
		this.comcate = comcate;
		this.comjobname = comjobname;
		this.comcareer = comcareer;
		this.comlocation = comlocation;
		this.comskill = comskill;
		this.comtag = comtag;
		this.comimage = comimage;
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


	public Date getStartline() {
		return startline;
	}


	public void setStartline(Date startline) {
		this.startline = startline;
	}


	public Date getDeadline() {
		return deadline;
	}


	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}


	public Timestamp getComcreated() {
		return comcreated;
	}


	public void setComcreated(Timestamp comcreated) {
		this.comcreated = comcreated;
	}


	public Timestamp getComupdated() {
		return comupdated;
	}


	public void setComupdated(Timestamp comupdated) {
		this.comupdated = comupdated;
	}


	public int getComdel() {
		return comdel;
	}


	public void setComdel(int comdel) {
		this.comdel = comdel;
	}


	public String getComsalary() {
		return comsalary;
	}


	public void setComsalary(String comsalary) {
		this.comsalary = comsalary;
	}


	public String getComname() {
		return comname;
	}


	public void setComname(String comname) {
		this.comname = comname;
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


	public String getComimage() {
		return comimage;
	}


	public void setComimage(String comimage) {
		this.comimage = comimage;
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
	
	
	
	
	
}