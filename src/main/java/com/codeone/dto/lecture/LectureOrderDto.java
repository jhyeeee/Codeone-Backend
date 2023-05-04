package com.codeone.dto.lecture;

import java.io.Serializable;

import lombok.Data;

// 수강신청 db
//	CREATE TABLE lectureOrder (
//	 ordernum varchar(100) PRIMARY KEY, 	
//	 id VARCHAR(50) NOT NULL,  			
//	 seq int,   						
//	 orderdate DATETIME NOT NULL,		
// 	 orderstatus int,					
//	 delflg int not null	
// );

@Data
public class LectureOrderDto implements Serializable {
	
	private String ordernum;			// 주문번호
	private String id;					// userId
	private int seq;					// lectureSeq
	private String orderdate;			// 결제시간 아임포트에서 받은정보 넣어주기
	private String orderstatus;			// 아임포트에서 받은정보 넘겨주기 "paid"
	private int delflg;					
}
