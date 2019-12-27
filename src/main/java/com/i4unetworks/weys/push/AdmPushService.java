package com.i4unetworks.weys.push;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.i4unetworks.weys.common.PushService;
import com.i4unetworks.weys.notice.NoticeDao;

@Service
public class AdmPushService {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private PushDao pushDao;
	@Autowired
	private NoticeDao noticeDao;

	// FCML
	@Value("#{props['FCM.SERVER.KEY']}")
	private String FCM_SERVER_KEY; // FCM 서버 키
	@Value("#{props['FCM.SEND.URL']}")
	private String FCM_SEND_URL; // FCM 발송 URL

	public int updateSendPush(Map<String, Object> reqMap) throws Exception {
		
		List<String> iosUser = new ArrayList<>();
		List<String> aosUser = new ArrayList<>();
		
		String os = MapUtils.getString(reqMap, "os");
		String pushType = MapUtils.getString(reqMap, "pushType");
		String val = MapUtils.getString(reqMap, "value", "");
		
		if(os.contains("A")){
			reqMap.put("os", "A");
			if(pushType.equals("event")){
				aosUser = noticeDao.selectCustomPush(reqMap);
			} else if(pushType.equals("notice")){
				aosUser = noticeDao.selectNoticePush(reqMap);
			} else {
				aosUser = noticeDao.selectAllUsrInfoForPush(reqMap);
			}
		}
		
		if(os.contains("I")){
			reqMap.put("os", "I");
			if(pushType.equals("event")){
				iosUser = noticeDao.selectCustomPush(reqMap);
			} else if(pushType.equals("notice")){
				iosUser = noticeDao.selectNoticePush(reqMap);
			}  else {
				iosUser = noticeDao.selectAllUsrInfoForPush(reqMap);
			}
		}

		String title = MapUtils.getString(reqMap, "title");
		String msg = MapUtils.getString(reqMap, "msg");
		/**
		 * 안드 사용자푸시 보내기
		 */
		if(aosUser.size() > 0){
			
			int startIndex = 0;
			int size = aosUser.size();
			
			if(aosUser.size() > 1000){
				while(true){
					int last = ((startIndex + 1) * 1000) -1;
					if(size < last){
						last = size;
					}
					List<String> list = aosUser.subList(startIndex * 1000, last);
					
					JSONObject dataJson = new JSONObject();
					dataJson.put("type", pushType);
					dataJson.put("title", title);
					dataJson.put("message", msg);
					dataJson.put("val", val);
					dataJson.put("sound", "default");
					
					JSONObject json = new JSONObject();
					json.put("registration_ids", list);
					json.put("data", dataJson);

					PushService push = new PushService(json, FCM_SERVER_KEY, FCM_SEND_URL);
					Thread t = new Thread(push);
					t.start();
					
					startIndex = startIndex + 1;
					if(last == size){
						break;
					}
				}
			} else {
				JSONObject dataJson = new JSONObject();
				dataJson.put("type", pushType);
				dataJson.put("title", title);
				dataJson.put("message", msg);
				dataJson.put("val", val);
				dataJson.put("sound", "default");
				
				JSONObject json = new JSONObject();
				json.put("registration_ids", aosUser);
				json.put("data", dataJson);

				PushService push = new PushService(json, FCM_SERVER_KEY, FCM_SEND_URL);
				Thread t = new Thread(push);
				t.start();
			}
		}
		
		/**
		 * 아이폰 사용자 푸시 보내기
		 */
		if(iosUser.size() > 0){

			int startIndex = 0;
			int size = iosUser.size();
			
			if(iosUser.size() > 1000){
				while(true){
					int last = ((startIndex + 1) * 1000) -1;
					if(size < last){
						last = size;
					}
					List<String> list = iosUser.subList(startIndex * 1000, last);

					JSONObject pushObj = new JSONObject();
					pushObj.put("registration_ids", list);
					JSONObject dataJson = new JSONObject();
					dataJson.put("title", title);
					dataJson.put("contents",pushType);
					dataJson.put("img", "");
					dataJson.put("val", val);
					
					JSONObject notiJson = new JSONObject();
					notiJson.put("title", title);
					notiJson.put("body", msg);
					notiJson.put("icon", "");
					notiJson.put("sound", "activated");
					notiJson.put("badge", "1");
					
					pushObj.put("content_available", true);
					pushObj.put("data", dataJson);
					pushObj.put("notification", notiJson);
					pushObj.put("priority", "high");
					
					PushService push = new PushService(pushObj, FCM_SERVER_KEY, FCM_SEND_URL);
					Thread t = new Thread(push);
					t.start();

					startIndex = startIndex + 1;
					if(last == size){
						break;
					}
				}
			} else {

				JSONObject pushObj = new JSONObject();
				pushObj.put("registration_ids", iosUser);
				JSONObject dataJson = new JSONObject();
				dataJson.put("title", title);
				dataJson.put("contents",pushType);
				dataJson.put("img", "");
				dataJson.put("val", val);
				
				JSONObject notiJson = new JSONObject();
				notiJson.put("title", title);
				notiJson.put("body", msg);
				notiJson.put("icon", "");
				notiJson.put("sound", "activated");
				notiJson.put("badge", "1");
				
				pushObj.put("content_available", true);
				pushObj.put("data", dataJson);
				pushObj.put("notification", notiJson);
				pushObj.put("priority", "high");
				
				PushService push = new PushService(pushObj, FCM_SERVER_KEY, FCM_SEND_URL);
				Thread t = new Thread(push);
				t.start();
			}
		}
		return 1;
	}

	public List<Map<String, Object>> selectEventList() {
		return pushDao.selectEventList();
	}

	public List<Map<String, Object>> selectNoticeList() {
		return pushDao.selectNoticeList();
	}

	public List<Map<String, Object>> selectContList() {
		return pushDao.selectContList();
	}

	public int updateSendPushCont(Map<String, Object> reqMap) throws Exception {

		List<String> iosUser = new ArrayList<>();
		List<String> aosUser = new ArrayList<>();
		
		String os = MapUtils.getString(reqMap, "os");
		String val = MapUtils.getString(reqMap, "value", "");
		String rsvSt = MapUtils.getString(reqMap, "rsvSt", "A");
		
		if(os.contains("A")){
			reqMap.put("os", "A");
			if(rsvSt.equals("F") || rsvSt.equals("R"))
				aosUser = noticeDao.selectUsrUidCont(reqMap);
			else
				aosUser = noticeDao.selectUsrUidContAll(reqMap);
		}
		
		if(os.contains("I")){
			reqMap.put("os", "I");
			if(rsvSt.equals("F") || rsvSt.equals("R"))
				iosUser = noticeDao.selectUsrUidCont(reqMap);
			else
				iosUser = noticeDao.selectUsrUidContAll(reqMap);
		}

		String title = MapUtils.getString(reqMap, "title");
		String msg = MapUtils.getString(reqMap, "msg");
		/**
		 * 안드 사용자푸시 보내기
		 */
		if(aosUser.size() > 0){
			
			int startIndex = 0;
			int size = aosUser.size();
			
			if(aosUser.size() > 1000){
				while(true){
					int last = ((startIndex + 1) * 1000) -1;
					if(size < last){
						last = size;
					}
					List<String> list = aosUser.subList(startIndex * 1000, last);
					
					JSONObject dataJson = new JSONObject();
					dataJson.put("type", "cont");
					dataJson.put("title", title);
					dataJson.put("message", msg);
					dataJson.put("val", val);
					dataJson.put("sound", "default");
					
					JSONObject json = new JSONObject();
					json.put("registration_ids", list);
					json.put("data", dataJson);

					PushService push = new PushService(json, FCM_SERVER_KEY, FCM_SEND_URL);
					Thread t = new Thread(push);
					t.start();
					
					startIndex = startIndex + 1;
					if(last == size){
						break;
					}
				}
			} else {
				JSONObject dataJson = new JSONObject();
				dataJson.put("type", "cont");
				dataJson.put("title", title);
				dataJson.put("message", msg);
				dataJson.put("val", val);
				dataJson.put("sound", "default");
				
				JSONObject json = new JSONObject();
				json.put("registration_ids", aosUser);
				json.put("data", dataJson);

				PushService push = new PushService(json, FCM_SERVER_KEY, FCM_SEND_URL);
				Thread t = new Thread(push);
				t.start();
			}
		}
		
		/**
		 * 아이폰 사용자 푸시 보내기
		 */
		if(iosUser.size() > 0){

			int startIndex = 0;
			int size = iosUser.size();
			
			if(iosUser.size() > 1000){
				while(true){
					int last = ((startIndex + 1) * 1000) -1;
					if(size < last){
						last = size;
					}
					List<String> list = iosUser.subList(startIndex * 1000, last);

					JSONObject pushObj = new JSONObject();
					pushObj.put("registration_ids", list);
					JSONObject dataJson = new JSONObject();
					dataJson.put("title", title);
					dataJson.put("contents","cont");
					dataJson.put("img", "");
					dataJson.put("val", val);
					
					JSONObject notiJson = new JSONObject();
					notiJson.put("title", title);
					notiJson.put("body", msg);
					notiJson.put("icon", "");
					notiJson.put("sound", "activated");
					notiJson.put("badge", "1");
					
					pushObj.put("content_available", true);
					pushObj.put("data", dataJson);
					pushObj.put("notification", notiJson);
					pushObj.put("priority", "high");
					
					PushService push = new PushService(pushObj, FCM_SERVER_KEY, FCM_SEND_URL);
					Thread t = new Thread(push);
					t.start();

					startIndex = startIndex + 1;
					if(last == size){
						break;
					}
				}
			} else {

				JSONObject pushObj = new JSONObject();
				pushObj.put("registration_ids", iosUser);
				JSONObject dataJson = new JSONObject();
				dataJson.put("title", title);
				dataJson.put("contents","cont");
				dataJson.put("img", "");
				dataJson.put("val", val);
				
				JSONObject notiJson = new JSONObject();
				notiJson.put("title", title);
				notiJson.put("body", msg);
				notiJson.put("icon", "");
				notiJson.put("sound", "activated");
				notiJson.put("badge", "1");
				
				pushObj.put("content_available", true);
				pushObj.put("data", dataJson);
				pushObj.put("notification", notiJson);
				pushObj.put("priority", "high");
				
				PushService push = new PushService(pushObj, FCM_SERVER_KEY, FCM_SEND_URL);
				Thread t = new Thread(push);
				t.start();
			}
		}
		return 1;
	}

	public int sendPushTestAll(Map<String, Object> reqMap) throws Exception {

		List<String> iosUsr = pushDao.selectTestPushAll("I");
		List<String> aosUsr = pushDao.selectTestPushAll("A");
		
		String val = "https://m.weys.exchange/weys/html/hanjin.html";
		String title = "(공지) 인천공항 수령 장소 개편 소식!";
		String msg = "1터미널 24시간 수령 가능(10월 1일부터~), 2터미널 신규 오픈(10월 2일부터~), 기존 배송 업무 종료(9월 30일까지 정상 운영)";
		
		if(aosUsr.size() > 0){

			int startIndex = 0;
			int size = aosUsr.size();
			
			while(true){
				int last = ((startIndex + 1) * 1000) -1;
				if(size < last){
					last = size;
				}
				List<String> list = aosUsr.subList(startIndex * 1000, last);
				
				JSONObject dataJson = new JSONObject();
				dataJson.put("type", "cont");
				dataJson.put("title", title);
				dataJson.put("message", msg);
				dataJson.put("val", val);
				dataJson.put("sound", "default");
				
				JSONObject json = new JSONObject();
				json.put("registration_ids", list);
				json.put("data", dataJson);

				PushService push = new PushService(json, FCM_SERVER_KEY, FCM_SEND_URL);
				Thread t = new Thread(push);
				t.start();
				
				startIndex = startIndex + 1;
				if(last == size){
					break;
				}
			}
		}
		
		if(iosUsr.size() > 0){

			int startIndex = 0;
			int size = iosUsr.size();
			
			while(true){
				int last = ((startIndex + 1) * 1000) -1;
				if(size < last){
					last = size;
				}
				List<String> list = iosUsr.subList(startIndex * 1000, last);

				JSONObject pushObj = new JSONObject();
				pushObj.put("registration_ids", list);
				JSONObject dataJson = new JSONObject();
				dataJson.put("title", title);
				dataJson.put("contents","cont");
				dataJson.put("img", "");
				dataJson.put("val", val);
				
				JSONObject notiJson = new JSONObject();
				notiJson.put("title", title);
				notiJson.put("body", msg);
				notiJson.put("icon", "");
				notiJson.put("sound", "activated");
				notiJson.put("badge", "1");
				
				pushObj.put("content_available", true);
				pushObj.put("data", dataJson);
				pushObj.put("notification", notiJson);
				pushObj.put("priority", "high");
				
				PushService push = new PushService(pushObj, FCM_SERVER_KEY, FCM_SEND_URL);
				Thread t = new Thread(push);
				t.start();

				startIndex = startIndex + 1;
				if(last == size){
					break;
				}
			}
		}
		
		return 1;
	}

	public int sendPushTest(Map<String, Object> reqMap) throws Exception {

		List<Map<String, Object>> resultMap = pushDao.selectTestPush();
		
		String val = "https://m.weys.exchange/weys/api/event/27/join/";
		String title = "(이벤트) 만다리나덕 캐리어 응모하기";
		String msg = "SNS에 인증샷 올리면 모든 통화 100% 우대! 아이스크림, 만다리나덕 캐리어 당첨 행운까지! (수신거부: 설정 > 마케팅 수신동의 해제)";
		
		for(Map<String, Object> info : resultMap){

			String os = MapUtils.getString(info, "OS");
			String uuid = MapUtils.getString(info, "UUID");
			String ak = MapUtils.getString(info, "USR_AK");
			
			if(os.equals("A")){
				JSONObject dataJson = new JSONObject();
				dataJson.put("type", "cont");
				dataJson.put("title", title);
				dataJson.put("message", msg);
				dataJson.put("val", val + ak);
				dataJson.put("sound", "default");
				
				JSONObject json = new JSONObject();
				json.put("to", uuid);
				json.put("data", dataJson);

				PushService push = new PushService(json, FCM_SERVER_KEY, FCM_SEND_URL);
				Thread t = new Thread(push);
				t.start();
			} else {
				JSONObject pushObj = new JSONObject();
				pushObj.put("to", uuid);
				JSONObject dataJson = new JSONObject();
				dataJson.put("title", title);
				dataJson.put("contents","cont");
				dataJson.put("img", "");
				dataJson.put("val", val + ak);
				
				JSONObject notiJson = new JSONObject();
				notiJson.put("title", title);
				notiJson.put("body", msg);
				notiJson.put("icon", "");
				notiJson.put("sound", "activated");
				notiJson.put("badge", "1");
				
				pushObj.put("content_available", true);
				pushObj.put("data", dataJson);
				pushObj.put("notification", notiJson);
				pushObj.put("priority", "high");
				
				PushService push = new PushService(pushObj, FCM_SERVER_KEY, FCM_SEND_URL);
				Thread t = new Thread(push);
				t.start();
			}
		}
		
		return 1;
	}
	
}
