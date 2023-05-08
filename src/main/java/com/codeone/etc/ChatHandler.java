package com.codeone.etc;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Log4j2
public class ChatHandler extends TextWebSocketHandler {

	private static List<WebSocketSession> list = new ArrayList<>();

	private static Set<String> sessionIds = new HashSet<>();

	// 메세지 내용이 들어오는 부분
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String payload = message.getPayload();
		log.info("payload : " + payload);
		log.info("session : " + session);
		
		for (WebSocketSession sess : list) {
			sess.sendMessage(message);
		}
	}

	/* Client가 접속 시 호출되는 메서드 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {

//		String sessionId = session.getId();
//		if (sessionIds.contains(sessionId)) {
//			session.close(CloseStatus.NORMAL.withReason("Duplicated session"));
//			return;
//		}
//		sessionIds.add(sessionId);

		list.add(session);

		log.info(session + " 클라이언트 접속");
	}

	/* Client가 접속 해제 시 호출되는 메서드드 */

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

		String sessionId = session.getId();
		sessionIds.remove(sessionId);

		log.info(session + " 클라이언트 접속 해제");
		list.remove(session);
	}

	// 예외처리
//	@Override
//	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
//		// 예외 처리 로직
//		System.out.println("Exception occurred: " + exception.getMessage());
//		session.close();
//
//	}
}
