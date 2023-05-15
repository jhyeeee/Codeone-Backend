package com.codeone.dto.store;

import java.io.Serializable;

import lombok.Data;

@Data
public class storeChatDto implements Serializable {
	
	private String roomId;
	private String id;
	private String message;
	private String date;
	

}
