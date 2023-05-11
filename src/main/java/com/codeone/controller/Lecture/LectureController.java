package com.codeone.controller.Lecture;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.codeone.dto.lecture.LectureDto;
import com.codeone.dto.lecture.LectureLikeDto;
import com.codeone.dto.lecture.LectureParam;
import com.codeone.dto.store.StoreLikeDto;
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
	public ResponseEntity<String> updateLecture(LectureDto dto,
			@RequestParam(value = "uploadFile", required = false) MultipartFile uploadFile, HttpServletRequest req) {
		System.out.println("LectureController updateLecture() " + new Date());

		System.out.println(dto);
		// id 추후 관리자아이디로 넣어주기

		String id = "sss";
		dto.setId(id);

		// 업로드 파일이 없을경우
		// 업로드 파일이 없을 경우
		if (uploadFile == null || uploadFile.isEmpty()) {

			// db에 원래파일이름, item 넣어주기

			boolean isUpdateLecture = service.updateLecture(dto);

			if (isUpdateLecture == true) {
				return ResponseEntity.ok().build(); // 글수정 성공
			}
			return ResponseEntity.badRequest().build(); // 글수정 실패
		}

		// 파일업로드

		String msg = service.uploadImgFile(dto, uploadFile, req);

		if (msg == "UPLOAD_OK") {

			// 파일이 생성되면 강의글추가
			boolean isUpdateLecture = service.updateLecture(dto);

			if (isUpdateLecture == true) {
				return ResponseEntity.ok().build(); // 글쓰기 성공
			} else {
				return ResponseEntity.badRequest().build(); // 글쓰기 실패
			}
		} else if (msg == "UPLOAD_FAIL") {
			return ResponseEntity.badRequest().body("UPLOAD_FAIL"); // 파일업로드 실패
		} else {
			return ResponseEntity.badRequest().body("NO_IMAGE"); // 이미지 아님
		}

//		boolean isUpdateLecture = service.updateLecture(dto);
//		if (isUpdateLecture) {
//			return ResponseEntity.ok().build(); // 글수정 성공
//		}
//		return ResponseEntity.badRequest().build(); // 글수정 실패
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
	public ResponseEntity<Map<String, Object>> getLectureList(LectureParam param, String id) {
		System.out.println("LectureController getLectureList() " + new Date());

		System.out.println(param);
		// 글의 시작과 끝
		// 0부터 시작하기떄문에 리액트에서 넘겨줄 때 -1해서 넘겨줌
		int pn = param.getPageNumber(); // 0 1 2 3 4

		int start = pn * 10; // 페이지 숫자 넘어온것 10 20 30 40부터 시작
//				int end = ( pn + 1 ) * 10;	// 10 20

		param.setStart(start);
		param.setDataCount(10);
		System.out.println(param);

		// map으로 좋아요리스트 보내주기
		// 좋아요한 seq 리스트
		try {
			// 강의글 리스트
			List<LectureDto> lectureList = service.getLectureList(param);

			// id당 좋아요한 seq 리스트
			List<Integer> likelist = service.getlikeList(id);
			Map<String, Object> map = new HashMap<>();
			map.put("list", lectureList);
			map.put("likelist", likelist);

			return ResponseEntity.ok(map);

		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}

//		try {
//			List<LectureDto> lectureList = service.getLectureList(param);
//			
//			return ResponseEntity.ok(lectureList);
//
//		} catch (Exception e) {
//			return ResponseEntity.badRequest().build();
//		}

	}

	// 서버에 있는 이미지 불러와서 리액트에 반환해주기
	@GetMapping(value = "image/{imagename}", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> searchImage(@PathVariable("imagename") String imagename) throws IOException {
		System.out.println("LectureController searchImage() " + new Date());
		System.out.println("이미지: " + imagename);

		// 이미지 경로 설정
		String imagePath = "src/main/webapp/lectureImage/" + imagename;
		Path imageFilePath = Paths.get(imagePath);

		if (!Files.exists(imageFilePath)) { // 파일이 존재하지 않을 경우 404 에러 반환
			return ResponseEntity.notFound().build();
		}

		// 이미지 파일 읽어오기
		byte[] imageBytes = Files.readAllBytes(imageFilePath);

		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);

	}

	// 강의글 한개
	@GetMapping(value = "detail")
	public ResponseEntity<LectureDto> getLectureOne(int seq) {
		System.out.println("LectureController getLectureOne() " + new Date());
		System.out.println(seq);

		try {
			LectureDto dto = service.getLectureOne(seq);

			if (dto == null) {
				return ResponseEntity.noContent().build(); // 정보없음
			}

			return ResponseEntity.ok(dto); // 성공

		} catch (Exception e) {
			return ResponseEntity.badRequest().build(); // 정보 가져오기 실패
		}

	}

	// 좋아요
	@PostMapping(value = "/like")
	public String likeLecture(@RequestBody LectureLikeDto dto) {
		System.out.println("LectureController likeLecture() " + new Date());

		System.out.println(dto);

		// 좋아요 중인지 확인
		boolean isLiking = service.checkLike(dto);
		System.out.println(isLiking);
		if (isLiking == false) { // 좋아요 중이 아닐때
			// 좋아요
			boolean isLike = service.likeLecture(dto);

			if (isLike == true) {
				// 좋아요하고 좋아요수 올려주기
				service.countLike(dto.getSeq());
				return "LIKE_OK";

			}
			return "LIKE_FAIL";
		}
		// 이미 좋아요한 글일 때
		// return "LIKING";

		// 좋아요 취소
		boolean isCancleLike = service.cancelLike(dto);

		if (isCancleLike == true) {
			// 좋아요수 내려주기
			service.countCancelLike(dto.getSeq());
			return "CANCLE_LIKE";
		}
		return "CANCLE_LIKE_FAIL";

	}

	// 좋아요 중인지 확인
	@GetMapping(value = "/checkLike")
	public String checkLike(LectureLikeDto dto) {
		System.out.println("LectureController checkLike() " + new Date());

		System.out.println(dto.getSeq());
		// 리액트에서 id도 같이 넘겨주기

		System.out.println(dto);
		boolean isLiking = service.checkLike(dto);

		if (isLiking == true) {
			return "LIKING";
		}
		return "NOT_LIKE";
	}

	// 리스트(좋아요순)
	@GetMapping(value = "orderByLike")
	public ResponseEntity<Map<String, Object>> getLectureListOrderByLike(LectureParam param, String id) {
		System.out.println("LectureController getLectureListOrderByLike() " + new Date());

		System.out.println(param);
		// 글의 시작과 끝
		// 0부터 시작하기떄문에 리액트에서 넘겨줄 때 -1해서 넘겨줌
		int pn = param.getPageNumber(); // 0 1 2 3 4

		int start = pn * 10; // 페이지 숫자 넘어온것 10 20 30 40부터 시작
//				int end = ( pn + 1 ) * 10;	// 10 20

		param.setStart(start);
		param.setDataCount(10);
		System.out.println(param);

		try {
			List<LectureDto> orderByLike = service.getLectureListOrderByLike(param);
			// id당 좋아요한 seq 리스트
			List<Integer> likelist = service.getlikeList(id);
			Map<String, Object> map = new HashMap<>();
			map.put("list", orderByLike);
			map.put("likelist", likelist);

			return ResponseEntity.ok(map);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}

	}

}
