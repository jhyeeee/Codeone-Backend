package com.codeone.validator.studygroup;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.codeone.command.studygroup.StudygroupCommentCommand;

public class CommentListValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return StudygroupCommentCommand.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		StudygroupCommentCommand studygroupCommentCommand = (StudygroupCommentCommand) target;
		
		int studygroupSeq = studygroupCommentCommand.getStudygroupSeq();
		int pageNumber = studygroupCommentCommand.getPageNumber();
		
		if(studygroupSeq <= 0) {
			errors.rejectValue("studygroupSeq", "Illegal Argument");
		} else if(pageNumber <= 0) {
			errors.rejectValue("pageNumber", "Illegal Argument");
		}
	}

}
