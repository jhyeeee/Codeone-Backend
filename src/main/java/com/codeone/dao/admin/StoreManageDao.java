package com.codeone.dao.admin;


import java.util.List;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.codeone.dto.store.StoreItemDto;

import com.codeone.dto.store.StoreLikeDto;
import com.codeone.dto.store.StoreParam;


@Mapper
@Repository
public interface StoreManageDao {
	
	List<StoreItemDto> getAllStoreMng();
	boolean showHideStore(int seq, int delflg);

}
