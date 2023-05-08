package com.codeone.controller.store;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class ChatController {

	@GetMapping("/chat/{roomid}")
	public String chatGET(@PathVariable String roomid) {

		// log.info("@ChatController, chat GET()");
		// roomid를 사용하여 채팅방 정보를 가져온 후 반환
		log.info("@ChatController, chat GET(), roomid: " + roomid);

		return "chat";
	}
}