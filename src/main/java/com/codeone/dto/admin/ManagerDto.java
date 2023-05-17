package com.codeone.dto.admin;

import java.io.Serializable;

public class ManagerDto implements Serializable {
	
	private int seq;					
	private String email;				// 이메일 중복불가
	private String emailKey;			// 이메일 인증 랜덤키
	private int emailAuth;				// 이메일 인증여부 1이면 인증한 메일
	private String name;				// 이름
	private String id;					// 아이디 중복불가
	private String phoneNumber;			// 전화번호
	private String regdate;				// 가입일
	private String filename;			// 파일명. 프로필사진
	private String newfilename;
	private int delflg;					// 기본 0으로 가입, 삭제시 1 
	private int auth;					// 0:관리자, 1:일반회원, 2:기업회원
	private String pwd;
	
	public ManagerDto() {
		
	}


	public int getSeq() {
		return seq;
	}


	public void setSeq(int seq) {
		this.seq = seq;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getEmailKey() {
		return emailKey;
	}


	public void setEmailKey(String emailKey) {
		this.emailKey = emailKey;
	}


	public int getEmailAuth() {
		return emailAuth;
	}


	public void setEmailAuth(int emailAuth) {
		this.emailAuth = emailAuth;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getRegdate() {
		return regdate;
	}


	public void setRegdate(String regdate) {
		this.regdate = regdate;
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


	public int getAuth() {
		return auth;
	}


	public void setAuth(int auth) {
		this.auth = auth;
	}
	

	public String getPwd() {
		return pwd;
	}


	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


	@Override
	public String toString() {
		return "ManagerDto [seq=" + seq + ", email=" + email + ", emailKey=" + emailKey + ", emailAuth=" + emailAuth
				+ ", name=" + name + ", id=" + id + ", phoneNumber=" + phoneNumber + ", regdate=" + regdate
				+ ", filename=" + filename + ", newfilename=" + newfilename + ", delflg=" + delflg + ", auth=" + auth
				+ "]";
	}
	
	
}
