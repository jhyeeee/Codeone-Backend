
package com.codeone.dto.job;

public class JobFilterDto {
	
	//직군, 직무
	private String comcate;
	private String comjobname;
	
	//경력
	private String comcareer;
	
	//지역
	private String comlocation;
	
	//기술
	private String comskill;
	
	//태그
	private String comtag;

    public JobFilterDto() {}

	public JobFilterDto(String comcate, String comjobname, String comcareer, String comlocation, String comskill,
			String comtag) {
		super();
		this.comcate = comcate;
		this.comjobname = comjobname;
		this.comcareer = comcareer;
		this.comlocation = comlocation;
		this.comskill = comskill;
		this.comtag = comtag;
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

    

    
	
    }

   
	

