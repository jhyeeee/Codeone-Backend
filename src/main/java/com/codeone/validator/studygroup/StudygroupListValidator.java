package com.codeone.validator.studygroup;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.codeone.command.studygroup.StudygroupListCommand;
import com.codeone.etc.SearchUtils;
import com.codeone.etc.StaticVariable;

// 스터디 그룹 메인 -> 필터 Validator
// 필터링 하지 않을 수도 있으므로 스터디 그룹 생성 Validator와는 로직이 살짝 다름

public class StudygroupListValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return StudygroupListCommand.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		StudygroupListCommand studygroupListCommand = (StudygroupListCommand) target;
		
		int[] technologyStack = studygroupListCommand.getTechnologyStack();
		int recruitmentType = studygroupListCommand.getRecruitmentType();
		String term = studygroupListCommand.getTerm();
		
		int depth = studygroupListCommand.getDepth();
		
		if(isWrongTechnologyStack(technologyStack)) {
			errors.rejectValue("technologyStack", "Illegal Argument");
		} else if(isWrongRecruitmentType(recruitmentType)) {
			errors.rejectValue("recruitmentType", "Illegal Argument");
		} else if(isWrongTerm(term)) {
			errors.rejectValue("term", "Illegal Argument");
		} else if(isWrongDepth(depth)) {
			errors.rejectValue("depth", "Illegal Argument");
		}
	}
	
	private boolean isWrongTechnologyStack(int[] technologyStack) {
		if(technologyStack != null) {
			for(int i=0; i<technologyStack.length; i++) {
				// 기술 스택 값이 최소, 최대를 벗어났다면
				int find = technologyStack[i];
				if(find < StaticVariable.TECHNOLOGY_STACK_MIN || find > StaticVariable.TECHNOLOGY_STACK_MAX) {
					return true;
				}
				
				// 똑같은 기술 스택이 들어있다면
				boolean isExist = SearchUtils.linearSearch(technologyStack, find, i+1);
				if(isExist) {
					return true;
				}
			}
		}

		
		return false;
	}
	
	private boolean isWrongRecruitmentType(int recruitmentType) {
		// 모집 구분을 잘못 전달했을 경우
		return recruitmentType != 0 && recruitmentType != 1 && recruitmentType != 2;
	}

	private boolean isWrongTerm(String term) {
		// 검색어가 잘못 전달됐을 경우
		return term != null && term.length() > 100;
	}

	private boolean isWrongDepth(int depth) {
		// 페이지 번호가 잘못 전달됐을 경우
		return depth < 0;
	}
}
