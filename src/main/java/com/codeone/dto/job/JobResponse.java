package com.codeone.dto.job;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

//수정시 Select에 값 넣기 위한 Dto
public class JobResponse implements Serializable{
	
	private int seq;
	private String id;
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
	private Map<String, Object> comjobname;
	private Map<String, Object> comcareer;
	private Map<String, Object> comlocation;
	private Map<String, Object> comskill;
	private Map<String, Object> comtag;
	private String comsalary;
	private String comfile;
	private String commapx;
	private String commapy;
	private String whoLiked;
	private String comEmail;
	
	public JobResponse() {
	}

 

	public JobResponse(int seq, String id, String title, String content, String startline, String deadline,
			String wdate, int comdel, String comname, String comimage, String comcate, Map<String, Object> comjobname,
			Map<String, Object> comcareer, Map<String, Object> comlocation, Map<String, Object> comskill, Map<String, Object> comtag,
			String comsalary, String comfile, String commapx, String commapy, String whoLiked, String comEmail) {
		super();
		this.seq = seq;
		this.id = id;
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
		this.whoLiked = whoLiked;
		this.comEmail = comEmail;
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





	public Map<String, Object> getComjobname() {
		return comjobname;
	}





	public void setComjobname(Map<String, Object> comjobname) {
		this.comjobname = comjobname;
	}





	public Map<String, Object> getComcareer() {
		return comcareer;
	}





	public void setComcareer(Map<String, Object> comcareer) {
		this.comcareer = comcareer;
	}





	public Map<String, Object> getComlocation() {
		return comlocation;
	}





	public void setComlocation(Map<String, Object> comlocation) {
		this.comlocation = comlocation;
	}





	public Map<String, Object> getComskill() {
		return comskill;
	}





	public void setComskill(Map<String, Object> comskill) {
		this.comskill = comskill;
	}





	public Map<String, Object> getComtag() {
		return comtag;
	}




 
	public void setComtag(Map<String, Object> comtag) {
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





	public String getCommapx() {
		return commapx;
	}





	public void setCommapx(String commapx) {
		this.commapx = commapx;
	}





	public String getCommapy() {
		return commapy;
	}





	public void setCommapy(String commapy) {
		this.commapy = commapy;
	}





	public String getWhoLiked() {
		return whoLiked;
	}





	public void setWhoLiked(String whoLiked) {
		this.whoLiked = whoLiked;
	}





	public String getComEmail() {
		return comEmail;
	}





	public void setComEmail(String comEmail) {
		this.comEmail = comEmail;
	}



	
	
	
	@Override
	public String toString() {
		return "JobResponse [seq=" + seq + ", id=" + id + ", title=" + title + ", content=" + content + ", startline="
				+ startline + ", deadline=" + deadline + ", wdate=" + wdate + ", comdel=" + comdel + ", comname="
				+ comname + ", comimage=" + comimage + ", comcate=" + comcate + ", comjobname=" + comjobname
				+ ", comcareer=" + comcareer + ", comlocation=" + comlocation + ", comskill=" + comskill + ", comtag="
				+ comtag + ", comsalary=" + comsalary + ", comfile=" + comfile + ", commapx=" + commapx + ", commapy="
				+ commapy + ", whoLiked=" + whoLiked + ", comEmail=" + comEmail + "]";
	}

  

	static public JobResponse of(JobDto dto) {
		JobResponse response = new JobResponse(
				dto.getSeq(), dto.getId(), dto.getTitle(), dto.getContent(), dto.getStartline(), dto.getDeadline(), 
				dto.getWdate(), dto.getComdel(), dto.getComname(), dto.getComimage(), dto.getComcate(), new HashMap<>(), 
				new HashMap<>(), new HashMap<>(),  new HashMap<>(),  new HashMap<>(), dto.getComsalary(), 
				dto.getComfile(), dto.getCommapx(), dto.getCommapy(), dto.getWhoLiked(), dto.getComEmail()
				);		
		
		return response;
	}
	

}