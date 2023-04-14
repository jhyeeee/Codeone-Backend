package com.codeone.etc;

import java.util.ArrayList;
import java.util.List;

public class ToUtils {
	
	public static List<Integer> arrayToList(int[] array) {
		List<Integer> list = new ArrayList<>();
		
		if(array != null && array.length > 0) {
			for(int element : array) {
				list.add(element);
			}
		}
		
		return list;
	}
}
