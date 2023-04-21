package com.codeone.validator.studygroup;

import org.springframework.validation.Errors;

import com.codeone.command.studygroup.StudygroupUpdateCommand;

public class UpdateStudygroupValidator extends WriteStudygroupValidator {
	@Override
	public boolean supports(Class<?> clazz) {
		return StudygroupUpdateCommand.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		super.validate(target, errors);
		
		if(!errors.hasErrors()) {
			StudygroupUpdateCommand studygroup = (StudygroupUpdateCommand) target;
			
			int seq = studygroup.getSeq();
			if(seq <= 0) {
				errors.rejectValue("seq", "Illegal Argument");
			}
		}
	}
}
