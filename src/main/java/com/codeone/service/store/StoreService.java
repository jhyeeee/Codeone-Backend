package com.codeone.service.store;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.codeone.dao.store.StoreDao;
import com.codeone.dto.store.StoreItemDto;

import com.codeone.dto.store.StoreLikeDto;
import com.codeone.dto.store.StoreParam;
import com.codeone.etc.ItemUtil;

import jakarta.servlet.http.HttpServletRequest;


@Service
@Transactional
public class StoreService {

	@Autowired
	StoreDao dao;
	
	// 중고거래 글쓰기
	public boolean writeStore(StoreItemDto item) {
		int n = dao.writeStore(item);
		return n>0?true:false;
	}

	
	public List<StoreItemDto> getStoreList(StoreParam param) {
		return dao.getStoreList(param);
	}
	
	// 중고거래 글 총수
	public int getAllStoreCount(StoreParam param) {
		return dao.getAllStoreCount(param);
	}
	
	public StoreItemDto getStoreItem(int seq) {
		return dao.getStoreItem(seq);
	}
	
	// 좋아요
	public boolean likeItem(StoreLikeDto dto) {
		int n = dao.likeItem(dto);
		return n>0?true:false;		
	}
	
	// 좋아요중인지 확인
	public boolean checkLike(StoreLikeDto dto) {
		int n = dao.checkLike(dto);
		return n>0?true:false;
	}
	
	// 좋아요취소
	public boolean cancelLike(StoreLikeDto dto) {
		int n = dao.cancelLike(dto);
		return n>0?true:false;		
	}
	
	// 좋아요카운트
	public int countLike(int seq) {
		return dao.countLike(seq);
	}
	
	// 좋아요취소 카운트
	public int countCancelLike(int seq) {
		return dao.countCancelLike(seq);
	}
	
	// 중고거래 글 수정
	public boolean updateStoreWrite(StoreItemDto item) {
		int n = dao.updateStoreWrite(item);
		return n>0?true:false;
	}
	
	// 중고거래 글 삭제
	public boolean deleteStoreWrite(int seq) {
		int n = dao.deleteStoreWrite(seq);
		return n>0?true:false; 
	}
	
	// 판매여부 변경
	public void updateStatus(StoreItemDto item) {
		dao.updateStatus(item);		
	}
	
	
	// 파일생성 service
	public boolean uploadImgFile(StoreItemDto item, MultipartFile uploadFile, HttpServletRequest req) {
        // 경로
        String path = req.getServletContext().getRealPath("/storeImage");

        // filename 취득
        String filename = uploadFile.getOriginalFilename(); // 원본 파일명

        // 확장자 제한
        String filecheck = filename.substring(filename.lastIndexOf('.'));

        // img 파일일때 파일생성
        if (filecheck.equals(".png") || filecheck.equals(".jpg") || filecheck.equals(".jpeg")) {
            item.setFilename(filename);

            // 파일명을 충돌되지 않는 명칭(Date)으로 변경
            String newfilename = ItemUtil.getNewFileName(filename);
            item.setNewfilename(newfilename); // 변경된 파일명 db에 넣어줌

            // 파일 생성
            File file = new File(path + "/" + newfilename);
            System.out.println(file); // 파일경로
            try {
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
                bos.write(uploadFile.getBytes());
                bos.close();
                return true;

            } catch (Exception e) {
                return false;
            }
        }
        return false; // 이미지가 아님
    }
}






