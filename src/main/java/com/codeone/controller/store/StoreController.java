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
import java.util.List;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileSystemUtils;
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

@RestController
public class StoreController {

	@Autowired
	StoreService service;

	// 중고거래 글쓰기
	@PostMapping(value = "/storewrite")
	public String storewrite(StoreItemDto item, 
							@RequestParam("uploadFile")MultipartFile uploadFile, 
							HttpServletRequest req) {		
		System.out.println("StoreController storewrite() " + new Date());			
		
		// System.out.println(item);
		
		// 파일 만들어주기
		
		// 파일 확장자, 사이즈 제한 추가하기
		
		// 경로
		String path = req.getServletContext().getRealPath("/storeImage");		
		
		// filename 취득		
		String filename = uploadFile.getOriginalFilename();	// 원본 파일명
//		String filepath = path + "/" + filename;	// 실제경로 + 파일네임
		item.setFilename(filename);
//		System.out.println(filepath);
		
		// 파일명을 충돌되지 않는 명칭(Date)으로 변경
		String newfilename = ItemUtil.getNewFileName(filename);
		item.setNewfilename(newfilename);	// 변경된 파일명 db에 넣어줌		
		
		// 파일 생성
		File file = new File(path + "/" + newfilename);		
		System.out.println(file);		// 파일경로
//		System.out.println(item.toString());
		try {
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
			bos.write(uploadFile.getBytes());
			bos.close();	
			
			// db에 파일, item 넣어주기
			boolean isWriteStore = service.writeStore(item);
			
			if(isWriteStore == true) {
				return "WRITE_OK";		// 글쓰기 성공
			}
			return "WRITE_FAIL";		// 글쓰기 실패
			
		} catch (Exception e) {
			return "file upload fail";
		} 
}		
		
	// 중고거래 리스트
	// 좋아요 여부 같이보내주기
	@GetMapping(value = "/getStoreList")
	public List<StoreItemDto> getStoreList(StoreParam param) {
		System.out.println("StoreController getStoreList() " + new Date());	
		
		// search, choice 넣어주고 리스트 불러오기
		List<StoreItemDto> list = service.getStoreList(param);
		
		// 로그인 id받아서 그사람이 좋아요중인지 여부 확인

	    
		return list;
	}
	
	// 서버에 있는 이미지 불러와서 리액트에 반환해주기	
	@GetMapping(value = "image/{imagename}", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> searchImage(@PathVariable("imagename") String imagename) throws IOException {
		System.out.println("StoreController searchImage() " + new Date());	
		//System.out.println("이미지: " + imagename);
		
		// 이미지 경로 설정
        String imagePath = "src/main/webapp/storeImage/" + imagename;
        Path imageFilePath = Paths.get(imagePath);

        // 이미지 파일 읽어오기
        byte[] imageBytes = Files.readAllBytes(imageFilePath);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(imageBytes);
		
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
		if(isLiking == false) {			// 좋아요 중이 아닐때
			// 좋아요
			boolean isLike = service.likeItem(dto);	
			
			if(isLike == true) {
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
		
		// 좋아요수 내려주기
		service.countCancelLike(dto.getSeq());
		return "CANCLE_LIKE";
		
	}
	
	// 좋아요 중인지 확인
	@GetMapping(value = "/checkLike")
	public String checkLike(StoreLikeDto dto) {
	    System.out.println("StoreController checkLike() " + new Date());
	    
	    System.out.println(dto.getSeq());
	    // 리액트에서 id도 같이 넘겨주기
	    String id = "sss";	    
	    dto.setId(id);
	    
		System.out.println(dto);
	    boolean isLiking = service.checkLike(dto);
	    
	    if(isLiking == true) {
	    	return "LIKING";
		}
		return "NOT_LIKE";
	} 
	
	
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
	
	// 중고거래 글 수정 파일 만드는 부분 추가하기
	@PutMapping(value = "/writeStore")
	public ResponseEntity<Void> updateStoreWrite(StoreItemDto item,
											@RequestParam(value = "uploadFile", required = false)MultipartFile uploadFile, 
											HttpServletRequest req){
		System.out.println("StoreController writeStore() " + new Date());
		System.out.println(item);
		// 업로드 파일이 없을 경우
		if(uploadFile == null || uploadFile.isEmpty()) {
			
			// db에 파일, item 넣어주기
	        boolean isUpdateWrite = service.updateStoreWrite(item);
	        
	        if(isUpdateWrite == true) {
	            return ResponseEntity.ok().build();        // 글수정 성공
	        }
	        return ResponseEntity.badRequest().build();    // 글수정 실패
	    }
		// 업로드 파일이 있을 경우
		
		// 경로
		String path = req.getServletContext().getRealPath("/storeImage");		
		
		// filename 취득		
		String filename = uploadFile.getOriginalFilename();	// 원본 파일명
		item.setFilename(filename);
//				System.out.println(filepath);
		
		// 파일명을 충돌되지 않는 명칭(Date)으로 변경
		String newfilename = ItemUtil.getNewFileName(filename);
		item.setNewfilename(newfilename);	// 변경된 파일명 db에 넣어줌		
		
		// 파일 생성
		File file = new File(path + "/" + newfilename);		
		System.out.println(file);		// 파일경로
//				System.out.println(item.toString());
		try {
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
			bos.write(uploadFile.getBytes());
			bos.close();	
			
			// db에 파일, item 넣어주기
			boolean isUpdateWrite = service.updateStoreWrite(item);
			
			if(isUpdateWrite == true) {
				return ResponseEntity.ok().build();		// 글쓰기 성공
			}
			return ResponseEntity.badRequest().build();		// 글쓰기 실패
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		} 
		
		
//		// id 추후 로그인에서 받아오기
//		String id = "sss";
//		item.setId(id);		
//		
//		boolean isUpdateWrite = service.updateStoreWrite(item);
//		if(isUpdateWrite) {		// 글수정 성공
//			return ResponseEntity.ok().build();
//		}
//		// 글 수정 실패
//		return ResponseEntity.badRequest().build();
	}

}












