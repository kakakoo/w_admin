package com.i4unetworks.weys.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
import com.i4unetworks.weys.common.Utils;

@Service
public class ScheduleService {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private ScheduleDao scheduleDao;

	@Value("${UPLOAD.PATH}")
	private String UPLOAD_PATH;
	// FCML
	@Value("#{props['FCM.SERVER.KEY']}")
	private String FCM_SERVER_KEY; // FCM 서버 키
	@Value("#{props['FCM.SEND.URL']}")
	private String FCM_SEND_URL; // FCM 발송 URL
	
	public int insertExchange(Map<String, Object> reqMap) throws Exception {
		
		try{
			String date = MapUtils.getString(reqMap, "날짜").replace("년 ", ".").replace("월 ", ".").replace("일", "");
			String time = scheduleDao.checkDate();

			// 같은 시간대 환율이 있다면 패스 
			if(date.equals(time))
				return 0;

			String today = Utils.getTodayDate("yyyy.MM.dd HH:mm");
			SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm");
			
			Date exDate = format.parse(date);
			Date toDate = format.parse(today);

			int compare = exDate.compareTo(toDate);

			// 오늘보다 지난 시간의 환율(잘못된 환율정보) 라면 패스 
			if(compare > 0)
				return 0;
			
			List<Map<String, Object>> exchangeList = (List<Map<String, Object>>) reqMap.get("리스트");

			for(Map<String, Object> exchange : exchangeList){
				String unitRange = MapUtils.getString(exchange, "통화명");
				
				String unit = checkUnit(unitRange);
				if(unit == null)
					continue;
				
				Map<String, Object> req = new HashMap<String, Object>();
				req.put("unit", unit);
				req.put("basicRate", MapUtils.getString(exchange, "매매기준율"));
				req.put("cashBuy", MapUtils.getString(exchange, "현찰사실때"));
				req.put("cashSELL", MapUtils.getString(exchange, "현찰파실때"));
				req.put("dttm", date);
				scheduleDao.insertExchange(req);
			}
		} catch (Exception e) {
			logger.info("error ::: " + e.getMessage());
			return 0;
		}
		return 1;
	}

	public void deleteChat() throws Exception {
		scheduleDao.deleteChat();
	}

	public void deleteYesterdayExchange(String diffDate) {
		scheduleDao.deleteYesterdayExchange(diffDate);
	}

	private String checkUnit(String unitRange) {
		
		if(unitRange.contains("USD"))
			return "USD";
		else if(unitRange.contains("JPY"))
			return "JPY";
		else if(unitRange.contains("EUR"))
			return "EUR";
		else if(unitRange.contains("CNY"))
			return "CNY";
		else if(unitRange.contains("NZD"))
			return "NZD";
		else if(unitRange.contains("TWD"))
			return "TWD";
		else if(unitRange.contains("KRW"))
			return "KRW";
		else if(unitRange.contains("RUB"))
			return "RUB";
		else if(unitRange.contains("MYR"))
			return "MYR";
		else if(unitRange.contains("MXN"))
			return "MXN";
		else if(unitRange.contains("BRL"))
			return "BRL";
		else if(unitRange.contains("CHF"))
			return "CHF";
		else if(unitRange.contains("SGD"))
			return "SGD";
		else if(unitRange.contains("AED"))
			return "AED";
		else if(unitRange.contains("GBP"))
			return "GBP";
		else if(unitRange.contains("INR"))
			return "INR";
		else if(unitRange.contains("CAD"))
			return "CAD";
		else if(unitRange.contains("THB"))
			return "THB";
		else if(unitRange.contains("TRY"))
			return "TRY";
		else if(unitRange.contains("PHP"))
			return "PHP";
		else if(unitRange.contains("AUD"))
			return "AUD";
		else if(unitRange.contains("HKD"))
			return "HKD";
		
		return null;
	}

	public void updateMemberCostZero(String diffDate) {
		scheduleDao.updateMemberCostZero(diffDate);
		scheduleDao.updateMemberCost();
	}

	public void insertNotiExc() throws Exception {
		
		/**
		 * 최신환율 호출
		 */
		List<Map<String, Object>> excList = scheduleDao.selectExcList();
		
		for(Map<String, Object> rateMap : excList){

			/**
			 * 설정한 환율값에 도달한 사용자 기기값 가져오기
			 */
			rateMap.put("os", "A");
			List<String> aosUsr = scheduleDao.selectAlrmUsrList(rateMap);
			rateMap.put("os", "I");
			List<String> iosUsr = scheduleDao.selectAlrmUsrList(rateMap);

			String unit = MapUtils.getString(rateMap, "UNIT");
			double basicRate = MapUtils.getDoubleValue(rateMap, "BASIC_RATE");
			
			String title = unit + " 외화가 목표에 도달하였습니다.";
			String msg = unit + " 외화의 환율이 현재 " + basicRate + " 되었습니다. 확인해주세요.";
			
			if(aosUsr.size() > 0 || iosUsr.size() > 0){
				rateMap.put("armTp", "I");
				rateMap.put("armTitle", title);
				rateMap.put("armTarget", "exc");
				rateMap.put("armVal", "");
				scheduleDao.insertAlarm(rateMap);

				scheduleDao.updateAlrmSt(rateMap);
			}
			
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
					dataJson.put("type", "exc");
					dataJson.put("title", title);
					dataJson.put("message", msg);
					dataJson.put("val", "");
					
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
					dataJson.put("contents","exc");
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

					startIndex = startIndex + 1;
					if(last == size){
						break;
					}
				}
			}
		}
		
	}

	public void sendRsvInfo() throws Exception {

		String title = "(광고) 설마 여행자보험 없이 떠나시나요?";
		String msg = "공항보다 싸게 5분이면 가입 끝! 동행자와 함께 가입 가능! (수신거부: 더보기 > 마케팅정보수신동의 해제)";
		String value = "https://m.weys.exchange/weys/html/inbyu_insure.html";
		
		Map<String, Object> reqMap = new HashMap<>();
		reqMap.put("dt", Utils.getDiffDate(1));

		reqMap.put("os", "A");
		List<String> aosUsr = scheduleDao.selectTomorwRsv(reqMap);
		reqMap.put("os", "I");
		List<String> iosUsr = scheduleDao.selectTomorwRsv(reqMap);

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
				dataJson.put("val", value);
				
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
				dataJson.put("val", value);
				
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

				startIndex = startIndex + 1;
				if(last == size){
					break;
				}
			}
		}
	}

	public void updateCash() {
		Map<String, Object> reqMap = new HashMap<>();
		reqMap.put("dt", Utils.getDiffDate(0));
		
		int res = scheduleDao.insertCashInfo(reqMap);
		if(res > 0){
			res = scheduleDao.insertCashLog(reqMap);
		}
	}

	public void updateCenterLogout() {
		/**
		 * 센터 미수령건 회수 처리
		 */
		Map<String, Object> reqMap = new HashMap<>();
		reqMap.put("dt", Utils.getDiffDate(-1));
		scheduleDao.updateCenterReturn(reqMap);

		/**
		 * 센터 로그아웃
		 */
		scheduleDao.updateCenterLogout();
		
	}
	
}
