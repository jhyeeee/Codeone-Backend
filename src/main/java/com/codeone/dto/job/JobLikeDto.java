package com.codeone.dto.job;

public class JobLikeDto {
	private int like_id;
	private int job_board_id;
	private int user_id;

	
    public JobLikeDto() {}


	public JobLikeDto(int like_id, int job_board_id, int user_id) {
		super();
		this.like_id = like_id;
		this.job_board_id = job_board_id;
		this.user_id = user_id;
	}


	public int getLike_id() {
		return like_id;
	}


	public void setLike_id(int like_id) {
		this.like_id = like_id;
	}


	public int getJob_board_id() {
		return job_board_id;
	}


	public void setJob_board_id(int job_board_id) {
		this.job_board_id = job_board_id;
	}


	public int getUser_id() {
		return user_id;
	}


	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

    

}