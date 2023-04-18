package com.codeone.dto.job;

public class JobEnum {

enum Job {
	

 //수정 예정
    BACK_END("서버/백엔드", 1),
    FRONT_END("front_end", 2);

    final String job_name;
    final int job_id;

    Job(String job_name, int job_id) {
        this.job_name = job_name;
        this.job_id = job_id;
    }
}
}