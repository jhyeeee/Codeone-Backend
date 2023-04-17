package com.codeone.controller.studygroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeone.command.studygroup.StudygroupCommentCommand;
import com.codeone.dto.studygroup.StudygroupCommentDto;
import com.codeone.exception.DeletedStudygroupException;
import com.codeone.exception.NotFoundCommentException;
import com.codeone.exception.NotPermissionToModifyException;
import com.codeone.exception.StudygroupNotFoundException;
import com.codeone.service.studygroup.StudygroupCommentService;
import com.codeone.validator.studygroup.UpdateCommentValidator;
import com.codeone.validator.studygroup.WriteCommentValidator;

@RestController
@RequestMapping("/studygroup/comment")
public class StudygroupCommentController {

	@Autowired
	StudygroupCommentService studygroupCommentService;
	
	@PostMapping()
	public ResponseEntity<Void> writeComment(StudygroupCommentCommand studygroupCommentCommand, BindingResult errors) {
		Validator validator = new WriteCommentValidator();
		validator.validate(studygroupCommentCommand, errors);
		
		if(errors.hasErrors()) {
			// 클라이언트가 잘못된 값을 전달했을 경우
			return ResponseEntity.badRequest().build();
		}
		
		// 로그인 여부 확인 코드 필요
		int memberSeq = 1;
		
		
		StudygroupCommentDto studygroupComment = studygroupCommentCommand.toDto();
		studygroupComment.setMemberSeq(memberSeq);
		
		try {
			studygroupCommentService.writeComment(studygroupComment);
			return ResponseEntity.ok().build();
		} catch(StudygroupNotFoundException | IllegalArgumentException e) {
			// 존재 하지 않는 스터디 그룹에 댓글을 달려 했거나
			// 본인이 쓴 모집 글에 댓글을 달려 했다면
			return ResponseEntity.badRequest().build();
		} catch(DeletedStudygroupException e) {
			// 삭제된 스터디 그룹에 댓글을 달려 했다면
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	@PutMapping()
	public ResponseEntity<Void> updateComment(StudygroupCommentCommand studygroupCommentCommand, BindingResult errors) {
		Validator validator = new UpdateCommentValidator();
		validator.validate(studygroupCommentCommand, errors);
		
		if(errors.hasErrors()) {
			// 클라이언트가 잘못된 값을 전달했을 경우
			return ResponseEntity.badRequest().build();
		}
		
		// 로그인 여부 확인 코드 필요
		int memberSeq = 1;
		
		
		StudygroupCommentDto studygroupComment = studygroupCommentCommand.toDto();
		studygroupComment.setMemberSeq(memberSeq);
		
		try {
			studygroupCommentService.updateComment(studygroupComment);
			return ResponseEntity.ok().build();
		} catch(NotFoundCommentException | NotPermissionToModifyException e) {
			// 존재 하지 않는 댓글을 수정하려는 경우 또는
			// 다른 사람이 작성한 댓글을 수정하려는 경우
			return ResponseEntity.badRequest().build();
		}
	}
}
