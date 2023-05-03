package com.codeone.controller.store;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.crypto.Data;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.codeone.dto.store.StoreItemDto;
import com.codeone.dto.store.StoreLikeDto;
import com.codeone.dto.store.StoreParam;
import com.codeone.etc.ItemUtil;
import com.codeone.service.store.StoreService;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.codeone.dto.store.StoreItemDto;
import com.codeone.service.store.StoreService;

@RestController
public class StoreController {

	@Autowired
	StoreService service;

	// 중고거래 글쓰기
	@PostMapping(value = "/storewrite")
	public String storewrite(StoreItemDto item,
			@RequestParam(value = "uploadFile", required = false) MultipartFile uploadFile, HttpServletRequest req) {
		System.out.println("StoreController storewrite() " + new Date());

		// System.out.println(item);

		// 프론트에서 어떻게 처리할지 고민. 장소부분 비어있을때
//		if (item.getLocation() == null || item.getLocation().equals("")) {
//			item.setLocation("전국");
//		}
//		if(item.getLocation().equals("지역설정안함")) {
//			item.setLocation("전국");
//		}
		
		// 업로드파일이 없을경우
		if (uploadFile == null || uploadFile.isEmpty()) {

			// db에 원래파일이름, item 넣어주기
			boolean isWriteStore = service.writeStore(item);

			if (isWriteStore == true) {
				return "WRITE_OK"; // 글쓰기 성공
			}
			return "WRITE_FAIL"; // 글쓰기 실패
		}

		// 업로드 파일이 있을 경우 파일 생성
		String msg = service.uploadImgFile(item, uploadFile, req);
		// boolean isUploadImg = service.uploadImgFile(item, uploadFile, req);

		if (msg == "UPLOAD_OK") {	// 파일 업로드 완료

			// 파일이 생성되면 글작성
			boolean isWriteStore = service.writeStore(item);

			if (isWriteStore == true) {
				return "WRITE_OK"; // 글쓰기 성공
			} else {
				return "WRITE_FAIL"; // 글쓰기 실패
			}
		}
		
		else if(msg == "NO_IMAGE"){
			return "NO_IMAGE";
		}
		
		return "UPLOAD_FAIL";

	}
	// 파일 만들어주기

	// 파일 확장자, 사이즈 제한 추가하기

//		// 경로
//		String path = req.getServletContext().getRealPath("/storeImage");
//
//		// filename 취득
//		String filename = uploadFile.getOriginalFilename(); // 원본 파일명
//
//		// 확장자 제한
//		String filecheck = filename.substring(filename.lastIndexOf('.'));
//
//		// img 파일일때 파일생성
//		if (filecheck.equals(".png") || filecheck.equals(".jpg") || filecheck.equals(".jpeg")) {
//			item.setFilename(filename);
//
//			// 파일명을 충돌되지 않는 명칭(Date)으로 변경
//			String newfilename = ItemUtil.getNewFileName(filename);
//			item.setNewfilename(newfilename); // 변경된 파일명 db에 넣어줌
//
//			// 파일 생성
//			File file = new File(path + "/" + newfilename);
//			System.out.println(file); // 파일경로
////			System.out.println(item.toString());
//			try {
//				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
//				bos.write(uploadFile.getBytes());
//				bos.close();
//
//				// db에 파일, item 넣어주기
//				boolean isWriteStore = service.writeStore(item);
//
//				if (isWriteStore == true) {
//					return "WRITE_OK"; // 글쓰기 성공
//				}
//				return "WRITE_FAIL"; // 글쓰기 실패
//
//			} catch (Exception e) {
//				return "UPLOAD_FAIL";
//			}
//		}
//		return "NO_IMAGE";

	// 중고거래 리스트
	// 좋아요 여부 같이보내주기
	@GetMapping(value = "/getStoreList")
	public Map<String, Object> getStoreList(StoreParam param, String id) {
		System.out.println("StoreController getStoreList() " + new Date());

		// 글의 시작과 끝
		// 0부터 시작하기떄문에 리액트에서 넘겨줄 때 -1해서 넘겨줌
		int pn = param.getPageNumber(); // 0 1 2 3 4

		int start = pn * 10; // 페이지 숫자 넘어온것 10 20 30 40부터 시작
//		int end = ( pn + 1 ) * 10;	// 10 20

		param.setStart(start);
		param.setDataCount(10); // 데이터 10개씩 보여주기 추후 25개로 바꾸기

		System.out.println(param);

		// search, choice 넣어주고 리스트 불러오기
		List<StoreItemDto> list = service.getStoreList(param);
		
		// 댓글의 총갯수
		int totalCount = service.getAllStoreCount(param); // search, choice 들어오는값은 없음.
		
		// 좋아요한 seq 리스트
		List<Integer> likelist = service.getlikeList(id);

		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		// map.put("pageBbs", pageBbs);
		map.put("cnt", totalCount); // 리액트 글의 총수 보내주기
		
		map.put("likelist", likelist);
		
		
		System.out.println(totalCount);
		return map;
		
		// 로그인 id받아서 그사람이 좋아요중인지 여부 확인

	}

	// 서버에 있는 이미지 불러와서 리액트에 반환해주기
	@GetMapping(value = "image/{imagename}", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> searchImage(@PathVariable("imagename") String imagename) throws IOException {
		//System.out.println("StoreController searchImage() " + new Date());
		// System.out.println("이미지: " + imagename);

		// 이미지 경로 설정
		String imagePath = "src/main/webapp/storeImage/" + imagename;
		Path imageFilePath = Paths.get(imagePath);

		// 이미지 파일 읽어오기
		byte[] imageBytes = Files.readAllBytes(imageFilePath);

		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);

//	    Resource resource = new FileSystemResource("src/main/webapp/storeImage/" + imagename);	// 이미지경로 + 이미지이름
//	    System.out.println(resource);
//	    return ResponseEntity.ok()
//	            .contentType(MediaType.IMAGE_JPEG)
//	            .body(resource);
	}

	// 거래글 상세보기
	@GetMapping(value = "/storedetail")
	public StoreItemDto getStoreItem(int seq) {
		System.out.println("StoreController getStoreItem() " + new Date());

		StoreItemDto item = service.getStoreItem(seq);
		
		// readcount 늘려주기
		service.itemReadCount(seq);
		return item;

	}

	// 좋아요
	@GetMapping(value = "/likeItem")
	public String likeItem(StoreLikeDto dto) {
		System.out.println("StoreController likeItem() " + new Date());

		System.out.println(dto);

		// 좋아요 중인지 확인
		boolean isLiking = service.checkLike(dto);
		System.out.println(isLiking);
		if (isLiking == false) { // 좋아요 중이 아닐때
			// 좋아요
			boolean isLike = service.likeItem(dto);

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
	public String checkLike(StoreLikeDto dto) {
		System.out.println("StoreController checkLike() " + new Date());

		System.out.println(dto.getSeq());
		// 리액트에서 id도 같이 넘겨주기		

		System.out.println(dto);
		boolean isLiking = service.checkLike(dto);

		if (isLiking == true) {
			return "LIKING";
		}
		return "NOT_LIKE";
	}
	
	// 목록에서 좋아요중인지 체크
//	@PostMapping(value = "/checkLikeList")
//	public String checkLikeList(@RequestBody String data) {
//		System.out.println("StoreController checkLikeList() " + new Date());
//
//		System.out.println(data);
//		StoreLikeDto dto = new StoreLikeDto();
//		
//		
//		
//		
//		
//		// 리액트에서 id도 같이 넘겨주기		
//
//		System.out.println(dto);
//		boolean isLiking = service.checkLike(dto);
//
//		if (isLiking == true) {
//			return "LIKING";
//		}
//		return "NOT_LIKE";
//	}

	// 글 목록에서 좋아요중인지 확인 추가하기

//	    System.out.println(seqArray);
//	    String id = "sss";
//	    boolean isLiking = false;
//	    
//	    for (Integer seq : seqArray) {
//	        StoreLikeDto dto = new StoreLikeDto();
//	        dto.setId(id);
//	        dto.setSeq(seq);
//	        
//	        isLiking = service.checkLike(dto);
//	        if (isLiking) {
//	            break; // 한 번이라도 좋아요 중이면 루프를 종료
//	        }
//	    }
//	    
//	    if (isLiking) {
//	        return "LIKING";
//	    }
//	    
//	    return "NOT_LIKE";

//		String id = "sss";
//		
//		StoreLikeDto dto = new StoreLikeDto();
//		 dto.setId(id);
//		    for (Integer seqValue : seq) {
//		        dto.setSeq(seqValue); // seq 값을 StoreLikeDto 객체의 seq 필드에 추가
//		    }
//		    
//		boolean isLiking = service.checkLike(dto);
//		if(isLiking == true) {
//			return "LIKING";
//		}
//		return "NOT_LIKE";

	// 중고거래 글 수정 파일생성은 service로 뺌
	@PutMapping(value = "/writeStore")
	public ResponseEntity<String> updateStoreWrite(StoreItemDto item,
			@RequestParam(value = "uploadFile", required = false) MultipartFile uploadFile, HttpServletRequest req) {
		System.out.println("StoreController writeStore() " + new Date());
		System.out.println(item);
		
//		if(item.getLocation().equals("지역설정안함")) {
//			item.setLocation("전국");
//		}

		// 업로드 파일이 없을 경우
		if (uploadFile == null || uploadFile.isEmpty()) {

			// db에 원래파일이름, item 넣어주기
			boolean isUpdateWrite = service.updateStoreWrite(item);

			if (isUpdateWrite == true) {
				return ResponseEntity.ok().build(); // 글수정 성공
			}
			return ResponseEntity.badRequest().build(); // 글수정 실패
		}

		// 업로드 파일이 있을 경우 파일 생성
		String msg = service.uploadImgFile(item, uploadFile, req);

		if (msg == "UPLOAD_OK") {

			// 파일이 생성되면 글수정
			boolean isUpdateWrite = service.updateStoreWrite(item);

			if (isUpdateWrite == true) {
				return ResponseEntity.ok().build(); // 글쓰기 성공
			} else {
				return ResponseEntity.badRequest().build(); // 글쓰기 실패
			}
		} else if (msg == "UPLOAD_FAIL") {
			return ResponseEntity.badRequest().body("UPLOAD_FAIL"); // 파일업로드 실패
		} else {
			return ResponseEntity.badRequest().body("NO_IMAGE"); // 이미지 아님
		}
	}

//	@PutMapping(value = "/writeStore")
//	public ResponseEntity<String> updateStoreWrite(StoreItemDto item,
//			@RequestParam(value = "uploadFile", required = false) MultipartFile uploadFile, HttpServletRequest req) {
//		System.out.println("StoreController writeStore() " + new Date());
//		System.out.println(item);
//		// 업로드 파일이 없을 경우
//		if (uploadFile == null || uploadFile.isEmpty()) {
//
//			// db에 원래파일이름, item 넣어주기
//			boolean isUpdateWrite = service.updateStoreWrite(item);
//
//			if (isUpdateWrite == true) {
//				return ResponseEntity.ok().build(); // 글수정 성공
//			}
//			return ResponseEntity.badRequest().build(); // 글수정 실패
//		}
//
//		// 업로드 파일이 있을 경우 파일 생성
//
//		// 경로
//		String path = req.getServletContext().getRealPath("/storeImage");
//
//		// filename 취득
//		String filename = uploadFile.getOriginalFilename(); // 원본 파일명
//
//		// 확장자 제한
//		String filecheck = filename.substring(filename.lastIndexOf('.'));
//
//		// img 파일일때 파일생성
//		if (filecheck.equals(".png") || filecheck.equals(".jpg") || filecheck.equals(".jpeg")) {
//			item.setFilename(filename);
////			System.out.println(filepath);
//
//			// 파일명을 충돌되지 않는 명칭(Date)으로 변경
//			String newfilename = ItemUtil.getNewFileName(filename);
//			item.setNewfilename(newfilename); // 변경된 파일명 db에 넣어줌
//
//			// 파일 생성
//			File file = new File(path + "/" + newfilename);
//			System.out.println(file); // 파일경로
////			System.out.println(item.toString());
//			try {
//				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
//				bos.write(uploadFile.getBytes());
//				bos.close();
//
//				// db에 파일, item 넣어주기
//				boolean isUpdateWrite = service.updateStoreWrite(item);
//
//				if (isUpdateWrite == true) {
//					return ResponseEntity.ok().build(); // 글쓰기 성공
//				}else {
//					return ResponseEntity.badRequest().build(); // 글쓰기 실패
//				}	
//			} catch (Exception e) {
//				return ResponseEntity.badRequest().build();
//			}
//		}
//		return ResponseEntity.badRequest().body("NO_IMAGE");		// 이미지가 아님
//
//	}

	// 중고거래 글 삭제
	@DeleteMapping("/storeitem")
	public ResponseEntity<Void> deleteStoreWrite(int seq) {
		System.out.println("StoreController deleteStoreWrite() " + new Date());

		System.out.println(seq);
		boolean isDeleteItem = service.deleteStoreWrite(seq);
		if (isDeleteItem) {
			return ResponseEntity.ok().build(); // 글삭제 성공
		}
		return ResponseEntity.badRequest().build(); // 글삭제 실패
	}

	// 중고거래 판매여부 변경
	@PutMapping("/status")
	public ResponseEntity<Void> updateStatus(@RequestBody StoreItemDto item) {
		System.out.println("StoreController updateStatus() " + new Date());

		System.out.println(item);

		try {
			service.updateStatus(item);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

}
