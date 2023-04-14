package com.codeone.etc;

import java.util.ArrayList;
import java.util.List;

import com.codeone.dto.studygroup.StudygroupPositionDto;

public class ToUtils {
	
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
