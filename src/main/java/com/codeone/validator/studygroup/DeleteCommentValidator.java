package com.codeone.validator.studygroup;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.codeone.command.studygroup.StudygroupCommentCommand;

public class DeleteCommentValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return StudygroupCommentCommand.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		StudygroupCommentCommand studygroupCommentCommand = (StudygroupCommentCommand) target;
		
		int seq = studygroupCommentCommand.getSeq();
		
		if(seq <= 0) {
			errors.rejectValue("seq", "Illegal Argument");
		}
	}
}
