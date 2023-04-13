package com.codeone.etc;

public class SearchUtils {
	// 선형 검색
	public static boolean linearSearch(int[] target, int find, int start) {
		if(target.length > start) {
			for(int i=start; i<target.length; i++) {
				if(target[i] == find) {
					return true;
				}
			}
		}
		
		return false;
	}
}
