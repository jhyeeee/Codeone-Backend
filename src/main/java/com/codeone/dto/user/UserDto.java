package com.codeone.dto.user;

import java.io.Serializable;

import com.codeone.dto.studygroup.StudygroupDetailUserDto;

// DB user table
/*create table user (
		seq int auto_increment primary key,
	    email varchar(100) not null unique,
	    emailKey varchar(200) not null unique,
	    emailAuth int not null,
	    name varchar(100) not null,
	    id varchar(100) not null unique,
	    phoneNumber varchar(20),
	    regdate timestamp not null,
	    filename VARCHAR(100) ,
	    newfilename VARCHAR(500) ,
	    delflg int not null,    
	    auth int not null
	);*/

// Serializable 꼭 써주기
public class UserDto implements Serializable {

	int seq;					
	String email;				// 이메일 중복불가
	String emailKey;			// 이메일 인증 랜덤키
	int emailAuth;				// 이메일 인증여부 1이면 인증한 메일
	String name;				// 이름
	String id;					// 아이디 중복불가
	String phoneNumber;			// 전화번호
	String regdate;				// 가입일
	String filename;			// 파일명. 프로필사진
	String newfilename;
	int delflg;					// 기본 0으로 가입, 삭제시 1 
	int auth;					// 0:관리자, 1:일반회원, 2:기업회원
	
	
	public UserDto() {
		
	}


	public UserDto(int seq, String email, String emailKey, int emailAuth, String name, String id, String phoneNumber,
			String regdate, String filename, String newfilename, int delflg, int auth) {
		super();
		this.seq = seq;
		this.email = email;
		this.emailKey = emailKey;
		this.emailAuth = emailAuth;
		this.name = name;
		this.id = id;
		this.phoneNumber = phoneNumber;
		this.regdate = regdate;
		this.filename = filename;
		this.newfilename = newfilename;
		this.delflg = delflg;
		this.auth = auth;
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


	@Override
	public String toString() {
		return "userDto [seq=" + seq + ", email=" + email + ", emailKey=" + emailKey + ", emailAuth=" + emailAuth
				+ ", name=" + name + ", id=" + id + ", phoneNumber=" + phoneNumber + ", regdate=" + regdate
				+ ", filename=" + filename + ", newfilename=" + newfilename + ", delflg=" + delflg + ", auth=" + auth
				+ "]";
	}
	
	public StudygroupDetailUserDto toStudygroupDetailUserDto() {
		StudygroupDetailUserDto studygroupDetailUser = new StudygroupDetailUserDto();
		studygroupDetailUser.setId(id);
		studygroupDetailUser.setFilename(filename);
		
		return studygroupDetailUser;
	}
	
}
