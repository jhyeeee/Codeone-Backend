package com.codeone.controller.studygroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeone.dto.studygroup.StudygroupDetailDto;
import com.codeone.exception.DeletedStudygroupException;
import com.codeone.exception.StudygroupNotFoundException;
import com.codeone.service.studygroup.StudygroupDetailService;

// 컨트롤러가 받는 seq는 스터디 그룹 관리 정보(studygroup_management 테이블)의 seq임

@RestController
@RequestMapping("/studygroup/detail")
public class StudygroupDetailController {
	@Autowired
	StudygroupDetailService studygroupDetailService;
	
	@GetMapping("/{seq}")
	public ResponseEntity<StudygroupDetailDto> getStudygroupInfo(@PathVariable("seq") int seq) {
		// seq 검증
		if(seq <= 0) {
			return ResponseEntity.badRequest().build();
		}
		
		try {
			// 스터디 그룹 정보 조회
			StudygroupDetailDto studygroupDetail = studygroupDetailService.getStudygroupInfo(seq);
			
			return ResponseEntity.ok(studygroupDetail);
		} catch(StudygroupNotFoundException |  DeletedStudygroupException e) {
			// StudygroupNotFoundException -> 잘못된 seq를 보내 스터디 그룹을 찾지 못했거나
			// DeletedStudygroupException -> 삭제된 스터디 그룹의 상세 정보를 요청했을 경우
			return ResponseEntity.noContent().build();
		}
	}
}
