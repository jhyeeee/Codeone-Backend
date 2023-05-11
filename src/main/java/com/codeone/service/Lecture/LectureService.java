package com.codeone.service.Lecture;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.codeone.dao.lecture.LectureDao;
import com.codeone.dto.lecture.LectureDto;
import com.codeone.dto.lecture.LectureLikeDto;
import com.codeone.dto.lecture.LectureParam;
import com.codeone.dto.store.StoreItemDto;
import com.codeone.dto.store.StoreLikeDto;
import com.codeone.etc.ItemUtil;

import jakarta.servlet.http.HttpServletRequest;

@Service
@Transactional
public class LectureService {

	@Autowired
	LectureDao dao;

	public boolean writeLecture(LectureDto dto) {
		int n = dao.writeLecture(dto);
		return n > 0 ? true : false;
	}

	// 파일생성
	// 파일생성 service
	public String uploadImgFile(LectureDto dto, MultipartFile uploadFile, HttpServletRequest req) {
		// 경로
		String path = req.getServletContext().getRealPath("/lectureImage");

		// filename 취득
		String filename = uploadFile.getOriginalFilename(); // 원본 파일명

		// 확장자 제한
		String filecheck = filename.substring(filename.lastIndexOf('.'));

		// img 파일일때 파일생성
		if (filecheck.equals(".png") || filecheck.equals(".jpg") || filecheck.equals(".jpeg")
				|| filecheck.equals(".PNG") || filecheck.equals(".JPG") || filecheck.equals(".JPEG")) {
			dto.setFilename(filename);

			// 파일명을 충돌되지 않는 명칭(Date)으로 변경
			String newfilename = ItemUtil.getNewFileName(filename);
			dto.setNewfilename(newfilename); // 변경된 파일명 db에 넣어줌

			// 파일 생성
			File file = new File(path + "/" + newfilename);
			System.out.println(file); // 파일경로
			try {
				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
				bos.write(uploadFile.getBytes());
				bos.close();
				return "UPLOAD_OK";

			} catch (Exception e) {
				return "UPLOAD_FAIL";
			}
		}
		return "NO_IMAGE"; // 이미지가 아님
	}

	// 강의글 수정
	public boolean updateLecture(LectureDto dto) {
		int n = dao.updateLecture(dto);
		return n > 0 ? true : false;
	}

	// 강의글 삭제
	public boolean deleteLecture(int seq) {
		int n = dao.deleteLecture(seq);
		return n > 0 ? true : false;
	}

	// 강의 글 목록
	public List<LectureDto> getLectureList(LectureParam param) {
		return dao.getLectureList(param);
	}

	// 강의 글 한개
	public LectureDto getLectureOne(int seq) {
		return dao.getLectureOne(seq);
	}

	// 좋아요
	public boolean likeLecture(LectureLikeDto dto) {
		int n = dao.likeLecture(dto);
		return n > 0 ? true : false;
	}

	// 좋아요중인지 확인
	public boolean checkLike(LectureLikeDto dto) {
		int n = dao.checkLike(dto);
		return n > 0 ? true : false;
	}

	// 좋아요취소
	public boolean cancelLike(LectureLikeDto dto) {
		int n = dao.cancelLike(dto);
		return n > 0 ? true : false;
	}

	// 좋아요카운트
	public int countLike(int seq) {
		return dao.countLike(seq);
	}

	// 좋아요취소 카운트
	public int countCancelLike(int seq) {
		return dao.countCancelLike(seq);
	}

	// 좋아요 seq 리스트
	public List<Integer> getlikeList(String id) {
		return dao.getlikeList(id);
	}

	// 리스트(좋아요순)
	public List<LectureDto> getLectureListOrderByLike (LectureParam param) {
		return dao.getLectureListOrderByLike(param);		
	}
}
