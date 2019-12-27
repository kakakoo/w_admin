package com.i4unetworks.weys.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.collections.MapUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.i4unetworks.weys.common.Paging;
import com.i4unetworks.weys.common.PushService;
import com.i4unetworks.weys.common.Utils;
import com.i4unetworks.weys.coupon.CouponVO;

@Service
public class EventService {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private EventDao eventDao;
	
	// FCML
	@Value("#{props['FCM.SERVER.KEY']}")
	private String FCM_SERVER_KEY; // FCM 서버 키
	@Value("#{props['FCM.SEND.URL']}")
	private String FCM_SEND_URL; // FCM 발송 URL

	@Value("#{props['ENC.KEY']}")
	private String ENC_KEY;
	
	public List<EventVO> getEventList(Paging paging) {

		if (paging.getIsSearch() == 1)
			paging.setSearchPage(0);
		else
			paging.setSearchPage((paging.getPageNo() - 1) * 10);
		
		if(paging.getReservation() == null){
			paging.setStartDt(Utils.getDiffDate(-14));
			paging.setEndDt(Utils.getTodayDate());
		} else {
			StringTokenizer st = new StringTokenizer(paging.getReservation(), "-");
			paging.setStartDt(st.nextToken().trim());
			paging.setEndDt(st.nextToken().trim());
		}

		return eventDao.getEventList(paging);
	}
	
	public int getEventListCnt(Paging paging) {
		return eventDao.getEventListCnt(paging);
	}
	
	public EventVO selectEventInfo(String eventId) {
		
		EventVO info = eventDao.selectEventInfo(eventId);

		List<String> imgList = new ArrayList<String>();
		if(info.getEventImg() == null || info.getEventImg().equals("")){
			info.setImgList(null);
		} else if (info.getEventImg().contains(",")){
			StringTokenizer st = new StringTokenizer(info.getEventImg(), ",");
			
			while(st.hasMoreTokens()){
				imgList.add(st.nextToken());
			}
			info.setImgList(imgList);
		} else {
			imgList.add(info.getEventImg());
			info.setImgList(imgList);
		}
		
		return info;
	}

	public int insertEvent(EventVO eventVO) throws Exception{

		StringTokenizer st = new StringTokenizer(eventVO.getReservation(), "-");
		eventVO.setStartDt(st.nextToken().trim());
		eventVO.setEndDt(st.nextToken().trim());
		return eventDao.insertEvent(eventVO);
	}

	public void updateEvent(EventVO eventVO) {
		StringTokenizer st = new StringTokenizer(eventVO.getReservation(), "-");
		eventVO.setStartDt(st.nextToken().trim());
		eventVO.setEndDt(st.nextToken().trim());
		eventDao.updateEvent(eventVO);
	}

	public List<CouponVO> selectEventCoupList() {
		return eventDao.selectEventCoupList();
	}

	public int updateSendPush(String eventId) throws Exception {
		
		String title = eventDao.selectEventTitle(eventId);
		/**
		 * 알람 등록
		 */
		Map<String, Object> alarm =  new HashMap<>();
		alarm.put("armTp", "N");
		alarm.put("armTitle", title);
		alarm.put("armTarget", "event");
		alarm.put("armVal", "/api/event/gVerSion/" + eventId);
		alarm.put("eventId", eventId);
		eventDao.insertNotJoinEvent(alarm);
		
		Map<String, Object> reqMap =  new HashMap<>();
		reqMap.put("eventId", eventId);
		reqMap.put("os", "I");
		List<String> iosUser = eventDao.selectNotJoinEvent(reqMap);
		reqMap.put("os", "A");
		List<String> aosUser = eventDao.selectNotJoinEvent(reqMap);
		

		/**
		 * 안드 사용자푸시 보내기
		 */
		if(aosUser.size() > 0){
			JSONObject dataJson = new JSONObject();
			dataJson.put("type", "event");
			dataJson.put("message", title);
			dataJson.put("val", eventId);
			
			JSONObject json = new JSONObject();
			json.put("registration_ids", aosUser);
			json.put("data", dataJson);

			PushService push = new PushService(json, FCM_SERVER_KEY, FCM_SEND_URL);
			Thread t = new Thread(push);
			t.start();
		}
		
		/**
		 * 아이폰 사용자 푸시 보내기
		 */
		if(iosUser.size() > 0){
			JSONObject pushObj = new JSONObject();
			pushObj.put("registration_ids", iosUser);
			JSONObject dataJson = new JSONObject();
			dataJson.put("title", title);
			dataJson.put("contents","event");
			dataJson.put("img", "");
			dataJson.put("val", eventId);
			
			JSONObject notiJson = new JSONObject();
			notiJson.put("title", "");
			notiJson.put("body", title);
			notiJson.put("icon", "");
			
			pushObj.put("content_available", true);
			pushObj.put("data", dataJson);
			pushObj.put("notification", notiJson);
			pushObj.put("priority", "high");
			
			PushService push = new PushService(pushObj, FCM_SERVER_KEY, FCM_SEND_URL);
			Thread t = new Thread(push);
			t.start();
		}
		
		
		return 1;
	}

	public List<Map<String, Object>> getEventJoinList(Paging paging) {
		
		paging.setPageSize(40);
		if (paging.getIsSearch() == 1)
			paging.setSearchPage(0);
		else
			paging.setSearchPage((paging.getPageNo() - 1) * 40);
		
		if(paging.getReservation() == null){
			paging.setStartDt(Utils.getDiffDate(-14));
			paging.setEndDt(Utils.getTodayDate());
		} else {
			StringTokenizer st = new StringTokenizer(paging.getReservation(), "-");
			paging.setStartDt(st.nextToken().trim());
			paging.setEndDt(st.nextToken().trim());
		}
		paging.setEncKey(ENC_KEY);
		return eventDao.getEventJoinList(paging);
	}

	public int getEventJoinListCnt(Paging paging) {
		return eventDao.getEventJoinListCnt(paging);
	}

	public int updateSendCoupon(Map<String, Object> reqMap) throws Exception {
		
		/**
		 * 이벤트로 지급될 쿠폰 발급
		 * 쿠폰 발급후 푸시 메세지 보내기
		 */
		String due = Utils.getDiffDate(180);
		reqMap.put("due", due);
		int resCnt = eventDao.insertEventJoinCoupon(reqMap);
		
		if(resCnt > 0){
			resCnt = eventDao.updateEventJoined(reqMap);
			
			Map<String, Object> pushInfo = eventDao.selectUsrPushInfo(reqMap);
			if(pushInfo != null){
				String os = MapUtils.getString(pushInfo, "OS");
				String uuid = MapUtils.getString(pushInfo, "UUID");

				String title = "무료 환전 쿠폰팩 도착!";
				String msg = "다음 여행 무료 환전 가능! 인증샷 이벤트에 참여해주셔서 감사합니다.(~" + due + "까지 사용가능)";
				
				if(os.equals("A")){
					JSONObject dataJson = new JSONObject();
					dataJson.put("type", "coupon");
					dataJson.put("title", title);
					dataJson.put("message", msg);
					dataJson.put("val", "");
					
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
					dataJson.put("contents","coupon");
					dataJson.put("img", "");
					dataJson.put("val", "");
					
					JSONObject notiJson = new JSONObject();
					notiJson.put("title", title);
					notiJson.put("body", msg);
					notiJson.put("icon", "");
					
					pushObj.put("content_available", true);
					pushObj.put("data", dataJson);
					pushObj.put("notification", notiJson);
					pushObj.put("priority", "high");
					
					PushService push = new PushService(pushObj, FCM_SERVER_KEY, FCM_SEND_URL);
					Thread t = new Thread(push);
					t.start();
				}
				
			}
		}
		
		return resCnt;
	}
	
}
