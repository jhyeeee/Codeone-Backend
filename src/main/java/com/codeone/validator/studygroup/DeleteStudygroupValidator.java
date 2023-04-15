package com.codeone.validator.studygroup;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.codeone.command.studygroup.StudygroupDeleteCommand;

public class DeleteStudygroupValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return StudygroupDeleteCommand.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		StudygroupDeleteCommand studygroup = (StudygroupDeleteCommand) target;
		
		int seq = studygroup.getSeq();
		if(seq <= 0) {
			// seq를 전달 받지 못했거나 잘못된 값을 전달 했을 경우
			errors.rejectValue("seq", "Illegal Argument");
		}
	}
}
