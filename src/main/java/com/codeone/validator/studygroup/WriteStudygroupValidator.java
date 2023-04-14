package com.codeone.validator.studygroup;

import java.time.LocalDate;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.codeone.command.studygroup.StudygroupCommand;
import com.codeone.etc.SearchUtils;
import com.codeone.etc.StaticVariable;

public class WriteStudygroupValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return StudygroupCommand.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// 진행 기간(시작 날짜, 종료 날짜), 마감 날짜를 검증하기 위한 현재 날짜
		LocalDate now = LocalDate.now();
		
		StudygroupCommand studygroup = (StudygroupCommand) target;
		
		// 검증할 값들을 꺼냄
		String title = studygroup.getTitle();
		String contents = studygroup.getContents();
		int recruitmentType = studygroup.getRecruitmentType();
		int wayOfProceeding = studygroup.getWayOfProceeding();
		int numberOfRecruits = studygroup.getNumberOfRecruits();
		LocalDate startDate = studygroup.getStartDate();
		LocalDate endDate = studygroup.getEndDate();
		int[] recruitmentPart = studygroup.getRecruitmentPart();
		int[] technologyStack = studygroup.getTechnologyStack();
		LocalDate deadlineForRecruitment = studygroup.getDeadlineForRecruitment();
		
		// 값 검증
		if(isWrongTitle(title)) {
			errors.rejectValue("title", "Illegal Argument");
		} else if(isWrongContents(contents)) {
			errors.rejectValue("contents", "Illegal Argument");
		} else if(isWrongRecruitmentType(recruitmentType)) {
			errors.rejectValue("recruitmentType", "Illegal Argument");
		} else if(isWrongWayOfProceeding(wayOfProceeding)) {
			errors.rejectValue("wayOfProceeding", "Illegal Argument");
		} else if(isWrongNumberOfRecruits(numberOfRecruits)) {
			errors.rejectValue("numberOfRecruits", "Illegal Argument");
		} else if(isWrongDeadlineForRecruitment(deadlineForRecruitment, now)) {
			errors.rejectValue("deadlineForRecruitment", "Illegal Argument");
		} else if(isWrongStartDate(startDate, deadlineForRecruitment)) {
			errors.rejectValue("startDate", "Illegal Argument");
		} else if(isWrongEndDate(endDate, startDate)) {
			errors.rejectValue("endDate", "Illegal Argument");
		} else if(isWrongRecruitmentPart(recruitmentPart)) {
			errors.rejectValue("recruitmentPart", "Illegal Argument");
		} else if(isWrongTechnologyStack(technologyStack)) {
			errors.rejectValue("technologyStack", "Illegal Argument");
		}
	}
	
	private boolean isWrongTitle(String title) {
		// 모집글 제목을 전달 받지 못했거나 너무 길 경우
		return title == null || title.trim().length() == 0 || title.length() > 50;
	}
	
	private boolean isWrongContents(String contents) {
		// 모집글 내용을 전달 받지 못했거나 너무 길 경우
		return contents == null || contents.trim().length() == 0 || contents.length() >= 3000;
	}

	private boolean isWrongRecruitmentType(int recruitmentType) {
		// 모집 구분을 잘못 전달했을 경우
		return recruitmentType != 1 && recruitmentType != 2;
	}
	
	private boolean isWrongWayOfProceeding(int wayOfProceeding) {
		// 진행 방식을 잘못 전달했을 경우
		return wayOfProceeding != 1 && wayOfProceeding != 2;
	}
	
	private boolean isWrongNumberOfRecruits(int numberOfRecruits) {
		// 모집 인원을 잘못 전달했을 경우
		return numberOfRecruits < 1 || numberOfRecruits > 11;
	}
	
	private boolean isWrongDeadlineForRecruitment(LocalDate deadlineForRecruitment, LocalDate now) {
		// 모집 날짜를 전달 받지 못했을 경우 또는  마감 날짜가 잘못됐을 경우(오늘 이전)
		return deadlineForRecruitment == null || deadlineForRecruitment.isBefore(now);
	}
	
	private boolean isWrongStartDate(LocalDate startDate, LocalDate deadlineForRecruitment) {
		// 시작 날짜가 비어있을 경우 또는 시작 날짜가 잘못됐을 경우(모집 마감 날짜 이전)
		return startDate == null || startDate.isBefore(deadlineForRecruitment);
	}
	
	private boolean isWrongEndDate(LocalDate endDate, LocalDate startDate) {
		// 종료 날짜가 비어있을 경우 또는 종료 날짜가 잘못됐을 경우(시작 날짜 이전)
		return endDate == null || endDate.isBefore(startDate);
	}
	
	private boolean isWrongRecruitmentPart(int[] recruitmentPart) {
		if(recruitmentPart == null) {
			return true;
		}
		
		for(int i=0; i<recruitmentPart.length; i++) {
			// 모집 분야 값이 최소, 최대를 벗어났다면
			int find = recruitmentPart[i];
			if(find < StaticVariable.POSITION_MIN || find > StaticVariable.POSITION_MAX) {
				return true;
			}
			
			// 똑같은 모집 분야가 들어있다면
			boolean isExist = SearchUtils.linearSearch(recruitmentPart, find, i+1);
			if(isExist) {
				return true;
			}
		}
		
		return false;
	}
	
	private boolean isWrongTechnologyStack(int[] technologyStack) {
		if(technologyStack == null) {
			return true;
		}
		
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
		
		return false;
	}

}
