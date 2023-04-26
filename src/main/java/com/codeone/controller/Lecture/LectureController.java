package com.codeone.controller.Lecture;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.codeone.dto.lecture.LectureDto;
import com.codeone.service.Lecture.LectureService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/lecture")
public class LectureController {

	@Autowired
	LectureService service;

	// 강의글 추가
	@PostMapping()
	public ResponseEntity<String> writeLecture(LectureDto dto,
			@RequestParam(value = "uploadFile", required = false) MultipartFile uploadFile, HttpServletRequest req) {
		System.out.println("LectureController writeLecture() " + new Date());

		// id 추후 관리자아이디로 넣어주기
		
		String id = "sss";
		dto.setId(id);
		
		// 파일추가

		String msg = service.uploadImgFile(dto, uploadFile, req);

		if (msg == "UPLOAD_OK") {

			// 파일이 생성되면 강의글추가
			boolean isWriteLecture = service.writeLecture(dto);

			if (isWriteLecture == true) {
				return ResponseEntity.ok().build(); // 글쓰기 성공
			} else {
				return ResponseEntity.badRequest().build(); // 글쓰기 실패
			}
		} else if (msg == "UPLOAD_FAIL") {
			return ResponseEntity.badRequest().body("UPLOAD_FAIL"); // 파일업로드 실패
		} else {
			return ResponseEntity.badRequest().body("NO_IMAGE"); // 이미지 아님
		}

//		boolean isWriteLecture = service.writeLecture(dto);
//		if(isWriteLecture) {					// 강의글 추가 성공
//			return ResponseEntity.ok().build();		
//		}
//		return ResponseEntity.badRequest().build();		// 강의글 추가 실패
	}

	// 강의글 수정
	@PutMapping()
	public ResponseEntity<Void> updateLecture(LectureDto dto) {
		System.out.println("LectureController updateLecture() " + new Date());
		
		// 파일업로드

		boolean isUpdateLecture = service.updateLecture(dto);
		if (isUpdateLecture) {
			return ResponseEntity.ok().build(); // 글수정 성공
		}
		return ResponseEntity.badRequest().build(); // 글수정 실패
	}

	// 강의글 삭제
	@DeleteMapping()
	public ResponseEntity<Void> deleteLecture(int seq) {
		System.out.println("LectureController deleteLecture() " + new Date());
		System.out.println(seq);
		
		boolean isDeleteLecture = service.deleteLecture(seq);
		if (isDeleteLecture) {
			return ResponseEntity.ok().build(); // 글삭제 성공
		}
		return ResponseEntity.badRequest().build(); // 글삭제 실패

	}

	// 강의글 목록
	@GetMapping()
	public ResponseEntity<List<LectureDto>> getLectureList() {
		System.out.println("LectureController getLectureList() " + new Date());

		try {
			List<LectureDto> lectureList = service.getLectureList();
			return ResponseEntity.ok(lectureList);

		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}

	}

	// 서버에 있는 이미지 불러와서 리액트에 반환해주기
	@GetMapping(value = "image/{imagename}", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> searchImage(@PathVariable("imagename") String imagename) throws IOException {
		System.out.println("LectureController searchImage() " + new Date());
		System.out.println("이미지: " + imagename);

		// 이미지 경로 설정
		String imagePath = "src/main/webapp/lectureImage/" + imagename;
		Path imageFilePath = Paths.get(imagePath);

		// 이미지 파일 읽어오기
		byte[] imageBytes = Files.readAllBytes(imageFilePath);

		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);

	}
	
	// 강의글 한개
	@GetMapping(value = "detail")
	public ResponseEntity<LectureDto> getLectureOne(int seq){
		System.out.println("LectureController getLectureOne() " + new Date());	
		System.out.println(seq);
		
		try {
			LectureDto dto = service.getLectureOne(seq);
			
			if(dto == null) {
				return ResponseEntity.noContent().build();		// 정보없음
			}
			
			return ResponseEntity.ok(dto);					// 성공
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();		// 정보 가져오기 실패
		}				
		
	}

}




