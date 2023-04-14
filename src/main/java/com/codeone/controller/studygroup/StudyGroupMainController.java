package com.codeone.controller.studygroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeone.command.studygroup.StudygroupCommand;
import com.codeone.dto.studygroup.StudygroupDto;
import com.codeone.service.studygroup.StudygroupService;
import com.codeone.validator.StudygroupValidator;

@RestController
public class StudyGroupMainController {
	@Autowired
	StudygroupService studygroupService;
	
	@PostMapping("/studygroup")
	public ResponseEntity<Void> writeStudyGroupRecruitment(StudygroupCommand studygroupCommand, BindingResult errors) {
		// 로그인 여부 확인 코드 필요
		int memberSeq = 1;
		
		
		// 커맨드 객체 검증
		Validator validator = new StudygroupValidator();
		validator.validate(studygroupCommand, errors);
		
		if(errors.hasErrors()) {
			// 클라이언트가 잘못된 값을 보냈다면
			return ResponseEntity.badRequest().build();
		}
		
		studygroupCommand.setMemberSeq(memberSeq);
		
		// 모집글 작성
		studygroupService.writeStudyGroupRecruitment(studygroupCommand);
		
		return ResponseEntity.ok().build();
	}
}
