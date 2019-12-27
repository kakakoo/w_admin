package com.i4unetworks.weys.notice;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.i4unetworks.weys.common.Paging;

@Service
public class NoticeService {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private NoticeDao noticeDao;
	
	@Value("${SERVER.PATH}")
	private String SERVER_PATH;
	
	// FCML
	@Value("#{props['FCM.SERVER.KEY']}")
	private String FCM_SERVER_KEY; // FCM 서버 키
	@Value("#{props['FCM.SEND.URL']}")
	private String FCM_SEND_URL; // FCM 발송 URL
	
	public List<NoticeDetailVO> selectNoticeList(Paging paging) {

		if (paging.getIsSearch() == 1)
			paging.setSearchPage(0);
		else
			paging.setSearchPage((paging.getPageNo() - 1) * 10);
		
		return noticeDao.selectNoticeList(paging);
	}

	public int selectNoticeListCnt(Paging paging) {
		return noticeDao.selectNoticeListCnt(paging);
	}
	
	public int updateStatus(Map<String, Object> reqMap) {
		
		String st = MapUtils.getString(reqMap, "noticeSt");
		if(st.equals("ON"))
			st = "N";
		else
			st = "Y";
		
		reqMap.put("noticeSt", st);
		
		String tp = MapUtils.getString(reqMap, "tp", "client");
		if(tp.equals("admin")){
			return noticeDao.updateStatusAdmin(reqMap);
		} else {
			return noticeDao.updateStatus(reqMap);
		}
	}

	public int insertNotice(NoticeDetailVO noticeDetailVO, HttpServletRequest request) throws Exception {

		String content = noticeDetailVO.getNoticeContent();
		content = content.replaceAll("<p>", "<p style='font-size:13px;padding-left:15px;padding-right:15px;font-family: 나눔고딕, nanumgothic, se_NanumGothic, AppleSDGothicNeo, sans-serif, simhei;'>");
		if(content.contains("WEYS_IMG") && noticeDetailVO.getImgPath() != null && !noticeDetailVO.getImgPath().equals("")){
			String img = "<img width='100%' src='" + SERVER_PATH + "imgView/" + noticeDetailVO.getImgPath() + "'>";
			content = content.replace("WEYS_IMG", img);
		}
		noticeDetailVO.setNoticeContent(content);
		int res = 0;
		if(noticeDetailVO.getTp().equals("admin")){
			res = noticeDao.insertAdminNotice(noticeDetailVO);
		} else {
			res = noticeDao.insertNotice(noticeDetailVO);
		}
		
//		if(res > 0){
//			/**
//			 * 알람 등록
//			 */
//			Map<String, Object> alarm =  new HashMap<>();
//			alarm.put("armTp", "N");
//			alarm.put("armTitle", noticeDetailVO.getNoticeTitle());
//			alarm.put("armTarget", "notice");
//			alarm.put("armVal", "/api/notive/gVerSion/" + noticeDetailVO.getNoticeId());
//			noticeDao.insertAlarm(alarm);
//			
//			Map<String, Object> reqMap = new HashMap<>();
//			reqMap.put("os", "I");
//			List<String> iosUser = noticeDao.selectNoticePush(reqMap);
//			reqMap.put("os", "A");
//			List<String> aosUser = noticeDao.selectNoticePush(reqMap);
//			
//			/**
//			 * 안드 사용자푸시 보내기
//			 */
//			if(aosUser.size() > 0){
//				
//				int startIndex = 0;
//				int size = aosUser.size();
//				
//				if(aosUser.size() > 1000){
//					while(true){
//						int last = ((startIndex + 1) * 1000) -1;
//						if(size < last){
//							last = size;
//						}
//						List<String> list = aosUser.subList(startIndex * 1000, last);
//						
//						JSONObject dataJson = new JSONObject();
//						dataJson.put("type", "notice");
//						dataJson.put("message", noticeDetailVO.getNoticeTitle());
//						dataJson.put("val", noticeDetailVO.getNoticeId());
//						
//						JSONObject json = new JSONObject();
//						json.put("registration_ids", list);
//						json.put("data", dataJson);
//
//						PushService push = new PushService(json, FCM_SERVER_KEY, GCM_SEND_URL);
//						Thread t = new Thread(push);
//						t.start();
//						
//						startIndex = startIndex + 1;
//						if(last == size){
//							break;
//						}
//					}
//				} else {
//					JSONObject dataJson = new JSONObject();
//					dataJson.put("type", "notice");
//					dataJson.put("message", noticeDetailVO.getNoticeTitle());
//					dataJson.put("val", noticeDetailVO.getNoticeId());
//					
//					JSONObject json = new JSONObject();
//					json.put("registration_ids", aosUser);
//					json.put("data", dataJson);
//
//					PushService push = new PushService(json, FCM_SERVER_KEY, GCM_SEND_URL);
//					Thread t = new Thread(push);
//					t.start();
//				}
//			}
//			
//			/**
//			 * 아이폰 사용자 푸시 보내기
//			 */
//			if(iosUser.size() > 0){
//
//				int startIndex = 0;
//				int size = iosUser.size();
//				
//				if(iosUser.size() > 1000){
//					while(true){
//						int last = ((startIndex + 1) * 1000) -1;
//						if(size < last){
//							last = size;
//						}
//						List<String> list = iosUser.subList(startIndex * 1000, last);
//
//						JSONObject pushObj = new JSONObject();
//						pushObj.put("registration_ids", list);
//						JSONObject dataJson = new JSONObject();
//						dataJson.put("title", noticeDetailVO.getNoticeTitle());
//						dataJson.put("contents","notice");
//						dataJson.put("img", "");
//						dataJson.put("val", noticeDetailVO.getNoticeId());
//						
//						JSONObject notiJson = new JSONObject();
//						notiJson.put("title", "");
//						notiJson.put("body", noticeDetailVO.getNoticeTitle());
//						notiJson.put("icon", "");
//						
//						pushObj.put("content_available", true);
//						pushObj.put("data", dataJson);
//						pushObj.put("notification", notiJson);
//						pushObj.put("priority", "high");
//						
//						PushService push = new PushService(pushObj, FCM_SERVER_KEY, FCM_SEND_URL);
//						Thread t = new Thread(push);
//						t.start();
//
//						startIndex = startIndex + 1;
//						if(last == size){
//							break;
//						}
//					}
//				} else {
//
//					JSONObject pushObj = new JSONObject();
//					pushObj.put("registration_ids", iosUser);
//					JSONObject dataJson = new JSONObject();
//					dataJson.put("title", noticeDetailVO.getNoticeTitle());
//					dataJson.put("contents","notice");
//					dataJson.put("img", "");
//					dataJson.put("val", noticeDetailVO.getNoticeId());
//					
//					JSONObject notiJson = new JSONObject();
//					notiJson.put("title", "");
//					notiJson.put("body", noticeDetailVO.getNoticeTitle());
//					notiJson.put("icon", "");
//					
//					pushObj.put("content_available", true);
//					pushObj.put("data", dataJson);
//					pushObj.put("notification", notiJson);
//					pushObj.put("priority", "high");
//					
//					PushService push = new PushService(pushObj, FCM_SERVER_KEY, FCM_SEND_URL);
//					Thread t = new Thread(push);
//					t.start();
//				}
//			}
//		}
		
		return res;
	}

	public NoticeDetailVO selectNotice(String noticeId, String tp) {
		
		if(tp.equals("admin")){
			return noticeDao.selectAdminNotice(noticeId);
		} else {
			return noticeDao.selectNotice(noticeId);
		}
	}

	public int updateNotice(NoticeDetailVO noticeDetailVO) {
		String content = noticeDetailVO.getNoticeContent();
		content = content.replaceAll("<p>", "<p style='font-size:13px;padding-left:15px;padding-right:15px;font-family: 나눔고딕, nanumgothic, se_NanumGothic, AppleSDGothicNeo, sans-serif, simhei;'>");
		if(content.contains("WEYS_IMG") && noticeDetailVO.getImgPath() != null && !noticeDetailVO.getImgPath().equals("")){
			String img = "<img width='100%' src='" + SERVER_PATH + "imgView/" + noticeDetailVO.getImgPath() + "'>";
			content = content.replace("WEYS_IMG", img);
		}
		noticeDetailVO.setNoticeContent(content);
		
		int res = 0;
		
		if(noticeDetailVO.getTp().equals("admin")){
			res = noticeDao.updateAdminNotice(noticeDetailVO);
		} else {
			res = noticeDao.updateNotice(noticeDetailVO);
		}
		return res;
	}

	public List<NoticeDetailVO> selectAdminList(Paging paging) {

		if (paging.getIsSearch() == 1)
			paging.setSearchPage(0);
		else
			paging.setSearchPage((paging.getPageNo() - 1) * 10);
		
		return noticeDao.selectAdminList(paging);
	}

	public int selectAdminListCnt(Paging paging) {
		return noticeDao.selectAdminListCnt(paging);
	}

}
