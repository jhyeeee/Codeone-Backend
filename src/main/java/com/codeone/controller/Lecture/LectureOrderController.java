package com.codeone.controller.Lecture;

import java.util.Date;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeone.dto.lecture.LectureDto;
import com.codeone.dto.lecture.LectureOrderDto;
import com.codeone.service.Lecture.LectureOrderService;



@RestController
@RequestMapping(value = "/lecture")
public class LectureOrderController {

	@Autowired
	LectureOrderService service;
	
	// db에 결제정보 넣어주기
	@PostMapping(value = "/order")
	public ResponseEntity<Void> orderLecture(@RequestBody LectureOrderDto dto) {

		System.out.println("LectureOrderController orderLecture() " + new Date());
		System.out.println(dto);

		// 주문정보 받아와서 orderdate 날짜로 변경
		long epochTimeLong = Long.parseLong(dto.getOrderdate());
		Instant instant = Instant.ofEpochSecond(epochTimeLong);
		ZoneId zoneId = ZoneId.of("Asia/Seoul");
		ZonedDateTime zonedDateTime = instant.atZone(zoneId);
		LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();

		System.out.println(localDateTime);

		// dto.setOrderdate(localDateTime.toString());
		dto.setOrderdate(localDateTime.format(DateTimeFormatter.ISO_DATE_TIME));

		// 주문정보받아서 db에 넘겨주기
		// ordernum, id, lectureseq, orderdate, orderstatus

		boolean isOrderLecture = service.orderLecture(dto);
		if (isOrderLecture) {
			return ResponseEntity.ok().build(); // 주문정보 넣기 성공
		}
		return ResponseEntity.badRequest().build(); // 주문정보 넣기 실패
	}
	
	@GetMapping(value = "/paidLecture")
	public ResponseEntity<LectureDto> getOrderLecture(LectureOrderDto dto){
		System.out.println("LectureOrderController paidLecture() " + new Date());
		System.out.println(dto);
		
		// 결제가격, status 정보 검증. 
		
		
		try {
			LectureDto lecture = service.getOrderLecture(dto);
			System.out.println(lecture);
			
			if(lecture == null) {
				return ResponseEntity.noContent().build();
			}
			
			return ResponseEntity.ok(lecture);				// 강의정보 보내주기
			
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}		
		
	}
	
	@GetMapping(value = "/order")
	public ResponseEntity<String> checkOrder(LectureOrderDto dto){
		System.out.println("LectureOrderController checkOrder() " + new Date());
		System.out.println(dto);
		
		boolean isCheckPaid = service.checkOrder(dto);
		if(isCheckPaid) {		// 이미 결제함
			return ResponseEntity.ok("PAID");
		}
		return ResponseEntity.ok("NO_PAID");		// 결제 안함
	}
	
	@GetMapping(value = "/ordercount")
	public ResponseEntity<Integer> checkPaidCount(int seq){
		System.out.println("LectureOrderController checkPaidCount() " + new Date());
		System.out.println(seq);
		
		try {
			int count = service.checkPaidCount(seq);
			return ResponseEntity.ok(count);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
		
	}
   

}
	
	








