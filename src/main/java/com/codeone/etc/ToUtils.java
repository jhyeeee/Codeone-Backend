package com.codeone.etc;

import java.util.ArrayList;
import java.util.List;

import com.codeone.dto.studygroup.StudygroupPositionDto;

public class ToUtils {
	public static int[] ListToArray(List<Integer> list) {
		if(list.size() == 0) {
			return new int[0];
		}
		
		int[] array = new int[list.size()];
		for(int i=0; i<list.size(); i++) {
			array[i] = list.get(i);
		}
		
		return array;
	}
	
	public static List<StudygroupPositionDto> arrayToStudygroupPositionList(int[] array) {
		List<StudygroupPositionDto> studygroupPositionList = new ArrayList<>();
		
		if(array != null && array.length > 0) {
			for(int element : array) {
				StudygroupPositionDto studygroupPosition = new StudygroupPositionDto();
				studygroupPosition.setPositionSeq(element);
				
				studygroupPositionList.add(studygroupPosition);
			}
		}
		
		return studygroupPositionList;
	}
	
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
