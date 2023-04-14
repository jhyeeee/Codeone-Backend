package com.codeone.validator.studygroup;

import org.springframework.validation.Errors;

import com.codeone.command.studygroup.StudygroupCommand;

public class UpdateStudygroupValidator extends WriteStudygroupValidator {
	@Override
	public void validate(Object target, Errors errors) {
		super.validate(target, errors);
		
		if(!errors.hasErrors()) {
			StudygroupCommand studygroup = (StudygroupCommand) target;
			
			int seq = studygroup.getSeq();
			if(seq <= 0) {
				errors.rejectValue("seq", "Illegal Argument");
			}
		}
	}
}
