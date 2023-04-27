package com.codeone.etc;

import java.util.Date;

public class ItemUtil {

	// 파일명 -> 변경(time)
	
	// mydata 확장자명이 없으면 -1
	
	// myfile.txt -> 235453252.txt
	
	public static String getNewFileName(String filename) {
		String newfilename  = "";
		String fpost = "";	// 포지션 위치 파일명위치
		
		// 위치를 알려주는 함수
		if(filename.indexOf('.') >= 0) { // 확장자명이 있음	
			fpost = filename.substring(filename.indexOf('.')); // 시작위치 .txt 가져옴. 확장자명
			newfilename = new Date().getTime() + fpost;		// 235453252 + .txt
		}else {		// 확장자명이 없음
			newfilename = new Date().getTime() + ".back";	// 235453252 + .back 확장자명이 없을때 붙여줌
		}
		
		return newfilename;
	}
}
