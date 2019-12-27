package com.i4unetworks.weys.websocket;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SocketHanlder extends TextWebSocketHandler implements InitializingBean {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	private Set<Map<String, Object>> sessionSet = new HashSet<Map<String, Object>>();

	public SocketHanlder() {
		super();
		this.logger.info("Open SocketHandler");
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		super.afterConnectionClosed(session, status);

		/**
		 * 종료할 세션과 일치하면 삭제 
		 */
		for(Map<String, Object> temp : this.sessionSet){
			WebSocketSession tempSession = (WebSocketSession) temp.get("session");
			if(tempSession.getId().equals(session.getId())){
				sessionSet.remove(temp);
				break;
			}
		}
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//		super.afterConnectionEstablished(session);
//		this.logger.info("add info ::: " +session.getId());
//
//		sessionSet.add(session);
//		this.logger.info("Add Session!");
	}

	public void sendMessage(String barcode, String storeId, WebSocketSession nowSession) {
		for (Map<String, Object> temp : this.sessionSet) {
			
			WebSocketSession session = (WebSocketSession) temp.get("session");
			String tempStoreId = MapUtils.getString(temp, "storeId");
			
			/**
			 * 본인 세션과, 상점아이디가 다른 세션에는 바코드 전송하지 않는다. 
			 */
//			if(nowSession.getId().equals(session.getId()))
//				continue;
			if(!storeId.equals(tempStoreId))
				continue;
			
			if (session.isOpen()) {
				try {
					this.logger.info("send store ::: " + storeId);
					this.logger.info("send barcode ::: " + barcode);
					session.sendMessage(new TextMessage(barcode));
				} catch (Exception ignored) {
					this.logger.error("fail to send message!", ignored);
				}
			}
		}
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		super.handleMessage(session, message);

		String msg = message.getPayload().toString();
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> newInfo = mapper.readValue(msg, new TypeReference<Map<String, Object>>() {});
		
		String barcode = MapUtils.getString(newInfo, "barcode");
		String storeId = MapUtils.getString(newInfo, "storeId");
		
		if(barcode == null || barcode.trim().equals("")
				|| storeId == null || storeId.trim().equals("")){
			return ;
		}

		/**
		 * 세션과 스토어 아이디를 저장한다.
		 * 세션과 스토어 아이디를 통해서 바코드를 전달하는 방식.
		 * 관지라는 바코드를 "NONE" 으로 보내서 세션을 오픈해둔다.
		 */
		Map<String, Object> newSession = new HashMap<String, Object>();
		newSession.put("session", session);
		newSession.put("storeId", storeId);
		sessionSet.add(newSession);
		this.logger.info("add info ::: " +session.getId() + ", storeId ::: " + storeId);
		
		if(!"NONE".equals(barcode)){
			sendMessage(barcode, storeId, session);
		}
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		this.logger.error("web socket error!", exception);
	}

	@Override
	public boolean supportsPartialMessages() {
		return super.supportsPartialMessages();
	}

	@Override
	public void afterPropertiesSet() throws Exception {

		this.logger.info("afterPropertiesSet");
//		Thread thread = new Thread() {
//			int i = 0;
//
//			public void run() {
//				while (true) {
//					try {
//						sendMessage("Send Message index " + i++);
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//						break;
//					}
//				}
//			};
//		};
//		thread.start();
	}

}
