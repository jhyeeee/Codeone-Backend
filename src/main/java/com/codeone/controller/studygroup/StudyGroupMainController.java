package com.codeone.controller.studygroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeone.command.studygroup.StudygroupDeleteCommand;
import com.codeone.command.studygroup.StudygroupInfoCommand;
import com.codeone.command.studygroup.StudygroupUpdateCommand;
import com.codeone.exception.NotPermissionToModifyException;
import com.codeone.service.studygroup.StudygroupService;
import com.codeone.validator.studygroup.DeleteStudygroupValidator;
import com.codeone.validator.studygroup.UpdateStudygroupValidator;
import com.codeone.validator.studygroup.WriteStudygroupValidator;

@RestController
public class StudyGroupMainController {
	@Autowired
	StudygroupService studygroupService;
	
	@PostMapping("/studygroup")
	public ResponseEntity<Void> writeStudygroupRecruitment(StudygroupInfoCommand studygroupCommand, BindingResult errors) {
		// 로그인 여부 확인 코드 필요
		int memberSeq = 1;
		
		
		// 커맨드 객체 검증
		Validator validator = new WriteStudygroupValidator();
		validator.validate(studygroupCommand, errors);
		
		if(errors.hasErrors()) {
			// 클라이언트가 잘못된 값을 보냈다면
			return ResponseEntity.badRequest().build();
		}
		
		studygroupCommand.setMemberSeq(memberSeq);
		
		// 모집글 작성
		studygroupService.writeStudygroupRecruitment(studygroupCommand);
		
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/studygroup")
	public ResponseEntity<Void> deleteStudygroupRecruitment(StudygroupDeleteCommand studygroupDeleteCommand, BindingResult errors) {
		// 로그인 여부 확인 코드 필요
		int memberSeq = 1;
		
		// 커맨드 객체 검증
		Validator validator = new DeleteStudygroupValidator();
		validator.validate(studygroupDeleteCommand, errors);
		
		if(errors.hasErrors()) {
			// 클라이언트가 잘못된 값을 보냈다면
			return ResponseEntity.badRequest().build();
		}
		
		// 삭제하기 위해 삭제를 요청한 사용자(로그인한 사용자)의 번호를 저장
		StudygroupDeleteCommand studygroup = new StudygroupDeleteCommand();
		studygroup.setMemberSeq(memberSeq);
		
		try {
			// 모집글 삭제
			boolean result = studygroupService.deleteStudygroupRecruitment(studygroup);
			
			if(result) {
				// 모집글을 삭제했을 경우
				return ResponseEntity.ok().build();
			} else {
				// 모집글을 삭제하지 못했을 경우
				return ResponseEntity.badRequest().build();
			}
		} catch(NotPermissionToModifyException e) {
			// 이미 스터디가 시작된 경우
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
	}
	
	@PutMapping("/studygroup")
	public ResponseEntity<Void> updateStudygroupRecruitment(StudygroupUpdateCommand studygroupUpdateCommand, BindingResult errors) {
		// 로그인 여부 확인 코드 필요
		int memberSeq = 1;
		
		
		// 커맨드 객체 검증
		Validator validator = new UpdateStudygroupValidator();
		validator.validate(studygroupUpdateCommand, errors);
		
		if(errors.hasErrors()) {
			// 클라이언트가 잘못된 값을 보냈다면
			return ResponseEntity.badRequest().build();
		}
		
		// 커맨드 객체를 DTO로 변환
		studygroupUpdateCommand.setMemberSeq(memberSeq);
		
		try {
			// 모집글 수정
			boolean result = studygroupService.updateStudygroupRecruitment(studygroupUpdateCommand);
			
			if(result) {
				// 모집글 수정 완료
				return ResponseEntity.ok().build();
			} else {
				// 모집글 수정 실패
				return ResponseEntity.badRequest().build();
			}
		} catch(NotPermissionToModifyException e) {
			// 모집글 수정 권한 없음
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
	}
}
