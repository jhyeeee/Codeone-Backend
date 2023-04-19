package com.codeone.validator.studygroup;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.codeone.command.studygroup.StudygroupCommentCommand;

public class WriteCommentValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return StudygroupCommentCommand.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		StudygroupCommentCommand studygroupCommentCommand = (StudygroupCommentCommand) target;
		
		int studygroupSeq = studygroupCommentCommand.getStudygroupSeq();
		String comment = studygroupCommentCommand.getComment();
		
		if(studygroupSeq <= 0) {
			errors.rejectValue("studygroupSeq", "Illegal Argument");
		} else if(comment == null || comment.trim().length() == 0 || comment.length() > 300) {
			errors.rejectValue("comment", "Illegal Argument");
		}
	}
}
