package com.i4unetworks.weys.rsv;

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

import com.i4unetworks.weys.common.Constant;
import com.i4unetworks.weys.common.KakaoClient;
import com.i4unetworks.weys.common.Paging;
import com.i4unetworks.weys.common.PushService;
import com.i4unetworks.weys.common.UserVO;
import com.i4unetworks.weys.common.Utils;
import com.i4unetworks.weys.mail.EmailVO;
import com.i4unetworks.weys.mail.Mailer;
import com.i4unetworks.weys.common.Barcode;

@Service
public class RsvService {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private RsvDao rsvDao;

	@Autowired
	private EmailVO emailVO;
	@Autowired
	private Mailer mailer;

	@Value("${UPLOAD.PATH}")
	private String UPLOAD_PATH; 
	@Value("#{props['ENC.KEY']}")
	private String ENC_KEY;
	@Value("#{props['EMAIL.ID']}")
	private String EMAIL_ID;
	@Value("${SERVER.PATH}")
	private String SERVER_PATH;
	@Value("${SERVER.TYPE}")
	private String SERVER_TYPE;

	@Value("#{props['IB.SERVICE.ID']}")
	private String IB_SERVICE_ID;
	@Value("#{props['IB.SERVICE.PWD']}")
	private String IB_SERVICE_PWD;
	// FCML
	@Value("#{props['FCM.SERVER.KEY']}")
	private String FCM_SERVER_KEY; // FCM 서버 키
	@Value("#{props['FCM.SEND.URL']}")
	private String FCM_SEND_URL; // FCM 발송 URL

	//알림톡
	@Value("#{props['IB.FROM.TEL']}")
	private String IB_FROM_TEL;
	@Value("#{props['IB.KAKAO.ID']}")
	private String IB_KAKAO_ID;
	@Value("#{props['IB.KAKAO.PWD']}")
	private String IB_KAKAO_PWD;
	@Value("#{props['IB.KAKAO.SENDER.KEY']}")
	private String IB_KAKAO_SENDER_KEY;

	public List<RsvListVO> getRsvList(Paging paging) {
		
		if (paging.getIsSearch() == 1)
			paging.setSearchPage(0);
		else
			paging.setSearchPage((paging.getPageNo() - 1) * paging.getPageSize());
		
		if(paging.getReservation() == null){
			paging.setStartDt(Utils.getDiffDate(0));
			paging.setEndDt(Utils.getDiffDate(15));
		} else {
			StringTokenizer st = new StringTokenizer(paging.getReservation(), "-");
			paging.setStartDt(st.nextToken().trim());
			paging.setEndDt(st.nextToken().trim());
		}

		if(paging.getRsvStVal() != null){
			String rsvSt = paging.getRsvStVal();
			if(rsvSt.equals("t") || rsvSt.equals("")){
				paging.setRsvStVal(null);
			} else {
				StringTokenizer st = new StringTokenizer(rsvSt, ",");
				List<String> list = new ArrayList<>();
				while(st.hasMoreTokens()){
					list.add(st.nextToken());
				}
				paging.setListData(list);
			}
		}
		if(paging.getRsvFormVal() != null){
			String rsvForm = paging.getRsvFormVal();
			if(rsvForm.equals("t") || rsvForm.equals("")){
				paging.setRsvFormVal(null);
			} else {
				StringTokenizer st = new StringTokenizer(rsvForm, ",");
				List<String> list = new ArrayList<>();
				while(st.hasMoreTokens()){
					list.add(st.nextToken());
				}
				paging.setListData1(list);
			}
		}
		paging.setEncKey(ENC_KEY);
		
		if(paging.getStoreId() == null)
			paging.setStoreId("t");
		
		if(paging.getSearchType() == null)
			paging.setSearchType("t");
		
		if(paging.getOrderTp() == null){
			paging.setOrderTp("R.RSV_ID");
			paging.setPreOrder("regdt");
			paging.setDescTp("DESC");
		} else {
			
			if(paging.getOrderVal() == 1){
				if(paging.getOrderTp().equals(paging.getPreOrder())){
					if(paging.getDescTp().equals("DESC")){
						paging.setDescTp("ASC");
					} else {
						paging.setDescTp("DESC");
					}
				} else {
					paging.setDescTp("DESC");
				}

				if(paging.getOrderTp().equals("store")){
					paging.setOrderTp("R.STORE_ID");
					paging.setPreOrder("store");
				} else if(paging.getOrderTp().equals("regdt")){
					paging.setOrderTp("R.RSV_ID");
					paging.setPreOrder("regdt");
				} else {
					if(paging.getDescTp().equals("DESC")){
						paging.setOrderTp("R.RSV_DT DESC, R.RSV_TM");
					} else {
						paging.setOrderTp("R.RSV_DT ASC, R.RSV_TM");
					}
					paging.setPreOrder("rsvdt");
				}
			}
		}

		return rsvDao.getRsvList(paging);
	}
	
	public int getRsvListCnt(Paging paging) {
		return rsvDao.getRsvListCnt(paging);
	}
	
	public List<RsvListVO> getRsvCancelList(Paging paging) {

		if (paging.getIsSearch() == 1)
			paging.setSearchPage(0);
		else
			paging.setSearchPage((paging.getPageNo() - 1) * 10);
		
		if(paging.getReservation() == null){
			paging.setStartDt(Utils.getDiffDate(-3));
			paging.setEndDt(Utils.getDiffDate(3));
		} else {
			StringTokenizer st = new StringTokenizer(paging.getReservation(), "-");
			paging.setStartDt(st.nextToken().trim());
			paging.setEndDt(st.nextToken().trim());
		}
		
		if(paging.getRsvStVal() != null){
			String rsvSt = paging.getRsvStVal();
			if(rsvSt.equals("t") || rsvSt.equals("")){
				paging.setRsvStVal(null);
				List<String> list = new ArrayList<>();
				list.add("t");
				list.add("CR");
				list.add("CF");
				paging.setListData(list);
			} else {
				StringTokenizer st = new StringTokenizer(rsvSt, ",");
				List<String> list = new ArrayList<>();
				while(st.hasMoreTokens()){
					list.add(st.nextToken());
				}
				paging.setListData(list);
			}
		} else {
			List<String> list = new ArrayList<>();
			list.add("t");
			list.add("CR");
			list.add("CF");
			paging.setListData(list);
			
		}
		paging.setEncKey(ENC_KEY);
		return rsvDao.getRsvCancelList(paging);
	}
	
	public int getRsvListCancelCnt(Paging paging) {
		return rsvDao.getRsvListCancelCnt(paging);
	}
	
	public int updateRsvCancel(String adminKey, int rsvId) throws Exception {
		/**
		 * 예약 환불 완료
		 * 사용자에게 취소 환급내용 푸쉬 및 메일 전송
		 */
		Map<String, Object> reqMap = new HashMap<>();
		reqMap.put("adminKey", adminKey);
		reqMap.put("rsvId", rsvId);
		reqMap.put("asIs", Constant.RSV_CANCEL_READY);
		reqMap.put("toBe", Constant.RSV_CANCEL_FINISH);
		
		int res = rsvDao.updateRsvCancel(reqMap);
		if(res > 0){
			res = rsvDao.insertRsvLog(reqMap);
			reqMap.put("encKey", ENC_KEY);
			Map<String, Object> infoMap = rsvDao.selectCancelUsrInfo(reqMap);

			
			/**
			 * 알림톡 발송
			 * 
			 	[웨이즈]

				조은용 고객님의 USD 1,200 예약취소 및 환불이 완료되었습니다.
				
				■환불내역
				- 환불금액: 1,302,000원
				- 환불계좌: 신한은행(111-554-777976)
				- 예금주: 조은용
			 */
			String msg = "[웨이즈]\n\n";
			msg += MapUtils.getString(infoMap, "RSV_NM") + " 고객님의 " + MapUtils.getString(infoMap, "UNIT") + " " + Utils.setStringFormatInteger(MapUtils.getString(infoMap, "RSV_AMNT")) + " 예약취소 및 환불이 완료되었습니다.\n\n";

			msg += "■환불내역\n";
			msg += "- 환불금액: " + Utils.setStringFormatInteger(MapUtils.getString(infoMap, "CANCEL_AMNT")) + "원\n";
			msg += "- 환불계좌: " + MapUtils.getString(infoMap, "RET_BANK_NM") + "(" + MapUtils.getString(infoMap, "RET_BANK_CD") + ")\n";
			msg += "- 예금주: " + MapUtils.getString(infoMap, "RSV_NM");
			
			String nation = MapUtils.getString(infoMap, "NATION");
			String usrTel = MapUtils.getString(infoMap, "RSV_TEL");
			if(nation.equals("82") && usrTel.startsWith("010"))
				usrTel = usrTel.substring(1);
			String tel = nation + usrTel;
			
			KakaoClient kakao = new KakaoClient(Constant.KAKAO_TALK_MSG, IB_KAKAO_ID, IB_KAKAO_PWD, IB_KAKAO_SENDER_KEY, IB_FROM_TEL);
			kakao.sendMsg(tel, msg, "refund_complete_v6");
			
			int usrId = MapUtils.getIntValue(infoMap, "USR_ID", 0);

			if(usrId > 0){
				Map<String, Object> talk =  new HashMap<>();
				talk.put("usrId",usrId);
				talk.put("msg", msg);
				talk.put("templete", "refund_complete_v6");
				rsvDao.insertKakaoLog(talk);
				

				/**
				 * 예약 취소 알림 등록
				 */
				String armTitle = "고객님의 계좌로 " + Utils.setStringFormatInteger(MapUtils.getString(infoMap, "CANCEL_AMNT"));
				armTitle += "원 환불이 완료되었습니다.";
				
				Map<String, Object> alarm =  new HashMap<>();
				alarm.put("armTp", "I");
				alarm.put("armTitle", armTitle);
				alarm.put("armTarget", "rsv");
				alarm.put("armVal", "/api/user/gVerSion/rsv/" + rsvId);
				alarm.put("rsvId", rsvId);
				rsvDao.insertAlarm(alarm);
				
				/**
				 * 푸쉬 보내기
				 */
				String uuid = MapUtils.getString(infoMap, "UUID", "");
				String os = MapUtils.getString(infoMap, "OS", "");
				String st = MapUtils.getString(infoMap, "PUSH_ST", "");
				
				if(st.equals("Y") && !uuid.equals("")){
					
					sendPush(os, uuid, "reserve", "return", "환불이 완료되었습니다.", armTitle, rsvId + "");
				}
			}
			if(SERVER_TYPE.equals("USER")){
				Map<String, Object> mailMap = new HashMap<>();
				mailMap.put("usrNm", MapUtils.getString(infoMap, "RSV_NM"));
				mailMap.put("rsvNo", MapUtils.getString(infoMap, "RSV_NO"));
				mailMap.put("regDttm", Utils.getTodayDate("yyyy년 MM월 dd일 a hh시 mm분"));
				
				mailMap.put("cancelAmnt", Utils.setStringFormatInteger(MapUtils.getString(infoMap, "CANCEL_AMNT")));
				mailMap.put("bankNm", MapUtils.getString(infoMap, "RET_BANK_NM"));
				mailMap.put("bankCd", MapUtils.getString(infoMap, "RET_BANK_CD"));
				
				emailVO.setEmailMap(mailMap);
				emailVO.setSubject(MapUtils.getString(infoMap, "RSV_NM") + " 고객님, 환불이 완료되었습니다.");
				emailVO.setVeloTmp("cancel.vm");
				emailVO.setFrom(EMAIL_ID);
				emailVO.setReceiver(MapUtils.getString(infoMap, "RET_CONTACT"));
				
				try{
					mailer.sendEmail(emailVO);
				} catch (Exception e) {
					logger.info("mail error : " + e.getMessage());
				}
			}

		}
		
		return res;
	}

	public List<Map<String, Object>> selectStoreList() {
		return rsvDao.selectStoreList();
	}

	public List<RsvUnitVO> getRsvUnitList() {
		return rsvDao.getRsvUnitList();
	}

	public int updateRsvUnit(Map<String, Object> reqMap, UserVO userVO) {
		return rsvDao.updateRsvUnit(reqMap);
	}

	public List<RsvListVO> getRsvListMng(Paging paging) {

		if (paging.getIsSearch() == 1)
			paging.setSearchPage(0);
		else
			paging.setSearchPage((paging.getPageNo() - 1) * 10);
		
		if(paging.getReservation() == null){
			paging.setStartDt(Utils.getDiffDate(0));
			paging.setEndDt(Utils.getDiffDate(3));
		} else {
			StringTokenizer st = new StringTokenizer(paging.getReservation(), "-");
			paging.setStartDt(st.nextToken().trim());
			paging.setEndDt(st.nextToken().trim());
		}
		paging.setEncKey(ENC_KEY);
		return rsvDao.getRsvList(paging);
	}

	public List<RsvExcelVO> selectRsvExcel(Paging paging) {
		Map<String, Object> reqMap = new HashMap<>();
		reqMap.put("startDt", paging.getReservation());
		reqMap.put("endDt", Utils.getNextDt(paging.getReservation(), 1));
		reqMap.put("rsvTm", "04:55");
		reqMap.put("tp", paging.getExcelType());
		reqMap.put("encKey", ENC_KEY);
		return rsvDao.selectRsvExcel(reqMap);
	}

	public Map<String, Object> getStoreInfo(Map<String, Object> reqMap) {
		
		List<Map<String, Object>> resultList = rsvDao.selectStoreMng(MapUtils.getIntValue(reqMap, "storeId"));
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("list", resultList);
		return resultMap;
	}

	@SuppressWarnings("unchecked")
	public int updateRsvMng(Map<String, Object> reqMap) throws Exception {
		
		/**
		 * 상태 업데이트
		 * 담당자 배정된 사용자라면 업데이트만,
		 * 새로 배정된 사용자라면 알림
		 */
		
		String adminKey = MapUtils.getString(reqMap, "adminKey");
		List<String> rsvList = (List<String>) reqMap.get("rsvList");
		
		int res = 0;
		for(String rsvId : rsvList){
			/**
			 * 예약 qr 생성
			 */
			boolean checkBarcode = true;
			String qr = "";
			while (checkBarcode) {
				qr = Barcode.CreateQrCode(2);
				int checkCnt = rsvDao.selectRsvQrCnt(qr);
				if (checkCnt == 0) {
					checkBarcode = false;
				}
			}
			String qrCodeUrl = Barcode.CreateQRCodePng(qr, UPLOAD_PATH, "rsv");

			Map<String, Object> updateMap = new HashMap<>();
			updateMap.put("adminKey", adminKey);
			updateMap.put("rsvId", rsvId);
			updateMap.put("qr", qr);
			updateMap.put("qrCodeUrl", qrCodeUrl);
			int suc = rsvDao.updateRsvMng(updateMap);
			
			res += suc;
			if(suc > 0){
				int st = rsvDao.updateRsvSt(updateMap);
				if(st > 0){
					/**
					 * 예약 로그 업데이트
					 */
					updateMap.put("asIs", Constant.RSV_INCOME);
					updateMap.put("toBe", Constant.RSV_READY);
					res = rsvDao.insertRsvLogMap(updateMap);
					
					updateMap.put("act", Constant.ADM_ACT_RSV_READY);
					res = rsvDao.insertAdmActLog(updateMap);
					
					/**
					 * 고객에게 준비가 되었다고 푸시 보내기 
					 */
					Map<String, Object> pushMap = rsvDao.selectPushInfo(updateMap);
					if(pushMap == null)
						return res;
					
					String uuid = MapUtils.getString(pushMap, "UUID", "");
					String os = MapUtils.getString(pushMap, "OS", "");
					String storeNm = MapUtils.getString(pushMap, "STORE_NM", "");
					
					if(os.equals("A")){
						JSONObject dataJson = new JSONObject();
						dataJson.put("type", "reserve");
						dataJson.put("st", "ready");
						dataJson.put("message", Constant.PUSH_MSG_READY + storeNm + " 에서 만나요!");
						dataJson.put("sound", "default");
						
						JSONObject json = new JSONObject();
						json.put("to", uuid);
						json.put("data", dataJson);
						
						PushService push = new PushService(json, FCM_SERVER_KEY, FCM_SEND_URL);
						Thread t = new Thread(push);
						t.start();

					} else if(os.equals("I")){
						JSONObject pushObj = new JSONObject();
						pushObj.put("to", uuid);
						JSONObject dataJson = new JSONObject();
						dataJson.put("title", Constant.PUSH_MSG_READY + storeNm + " 에서 만나요!");
						dataJson.put("contents","reserve");
						dataJson.put("st", "ready");
						dataJson.put("img", "");
						
						JSONObject notiJson = new JSONObject();
						notiJson.put("title", "");
						notiJson.put("body", Constant.PUSH_MSG_READY + storeNm + " 에서 만나요!");
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
			}
		}
		return res; 
	}

	public Map<String, Object> checkVb(Map<String, Object> reqMap, UserVO userVO) {
		
		reqMap.put("encKey", ENC_KEY);
		return rsvDao.selectVbInfo(reqMap);
	}
	
	public int updateMiss(Map<String, Object> reqMap) throws Exception {
		int rsvId = MapUtils.getIntValue(reqMap, "rsvId");
		
		String st = rsvDao.checkRsvSt(rsvId);
		
		if(!st.equals("S")){
			return 0;
		}
		
		int rsvAmntWeys = rsvDao.selectCheckUseCost(rsvId);
		
		if(rsvAmntWeys > 0){
			/**
			 * 멤버십 사용된 내역이면 사용된 멤버십 복구
			 * MEMBER COST 복구
			 * MEMBER_ACTIVE USE_COST 복구
			 * MEMBER_ACTIVE 예약내역 삭제
			 */
			rsvDao.updateReturnMemCost(rsvId);
			rsvDao.updateReturnUseCost(rsvId);
			rsvDao.insertReturnRA(rsvId);
		}

		/**
		 * 사용한 쿠폰이 있으면 반납
		 */
		Map<String, Object> bonusMap = rsvDao.selectRsvMissIncomeCp(rsvId);
		int couponId = MapUtils.getIntValue(bonusMap, "COUPON_ID", 0);
		if(couponId > 0)
			rsvDao.updateReturnCoupon(couponId);

		int bonusId = MapUtils.getIntValue(bonusMap, "BONUS_ID", 0);
		if(bonusId > 0)
			rsvDao.updateReturnCoupon(bonusId);
		
		int res = rsvDao.updateRsvMiss(rsvId);
		
		if(res > 0){

			reqMap.put("encKey", ENC_KEY);
			
			/**
			 * 사용자에게 시간초과 처리 푸시 보내기
			 */
			Map<String, Object> uuidMap = rsvDao.selectMissUuid(reqMap);
			
			/**
			 * 알림톡 발송
			 * 
			 	[웨이즈]

				#{name} 고객님의 #{r_currency} #{r_amount} 예약이 시간 초과로 자동 취소되었습니다.
			 */
			String msg = "[웨이즈]\n\n";
			msg += MapUtils.getString(uuidMap, "RSV_NM") + " 고객님의 " + MapUtils.getString(uuidMap, "UNIT") + " " + Utils.setStringFormatInteger(MapUtils.getString(uuidMap, "RSV_AMNT")) + " 예약이 시간 초과로 자동 취소되었습니다.\n\n";

			String nation = MapUtils.getString(uuidMap, "NATION");
			String usrTel = MapUtils.getString(uuidMap, "RSV_TEL");
			if(nation.equals("82") && usrTel.startsWith("010"))
				usrTel = usrTel.substring(1);
			String tel = nation + usrTel;
			
			KakaoClient kakao = new KakaoClient(Constant.KAKAO_TALK_MSG, IB_KAKAO_ID, IB_KAKAO_PWD, IB_KAKAO_SENDER_KEY, IB_FROM_TEL);
			kakao.sendMsg(tel, msg, "reservation_timeout");
			
			if(uuidMap != null){
				String pushMsg = "입금 제한 시간이 초과 되었습니다. 다시 예약 후 진행해 주세요.";
				String uuid = MapUtils.getString(uuidMap, "UUID", "");
				String os = MapUtils.getString(uuidMap, "OS", "");
				
				sendPush(os, uuid, "reserve", "income", "예약이 취소되었습니다.", pushMsg, "");

				Map<String, Object> alarm =  new HashMap<>();
				alarm.put("usrId", MapUtils.getInteger(uuidMap, "USR_ID"));
				alarm.put("armTp", "I");
				alarm.put("armTitle", pushMsg);
				alarm.put("armTarget", "rsv");
				alarm.put("armVal", "/api/user/gVerSion/rsv/" + rsvId);
				rsvDao.insertAlarm(alarm);
			}

		}
		
		return res;
	}

	public int updateIncome(Map<String, Object> reqMap) throws Exception {
		
		int rsvId = MapUtils.getIntValue(reqMap, "rsvId");
		
		/**
		 * 1. 멤버십 결제인지, 예약 결제인지 확인
		 */
//		int managerKey = rsvDao.selectManagerKey(rsvId); 
//		reqMap.put("adminKey", managerKey);
		reqMap.put("asIs", Constant.RSV_START);
		reqMap.put("toBe", Constant.RSV_READY);
		
		int res = rsvDao.updateRsvIncome(reqMap);
		

		if(res > 0){
			rsvDao.insertRsvLogVO(reqMap);
			
			/**
			 * 예약 qr 생성
			 */
			boolean checkBarcode = true;
			String qr = "";
			while (checkBarcode) {
				qr = Barcode.CreateQrCode(2);
				int checkCnt = rsvDao.selectRsvQrCnt(qr);
				if (checkCnt == 0) {
					checkBarcode = false;
				}
			}
			String qrCodeUrl = Barcode.CreateQRCodePng(qr, UPLOAD_PATH, "rsv");
			reqMap.put("qr", qr);
			reqMap.put("qrCodeUrl", qrCodeUrl);
			res = rsvDao.updateQrCode(reqMap);
			
			Map<String, Object> rsvMap = rsvDao.selectRsvForm(rsvId);
			
			String type = MapUtils.getString(rsvMap, "RSV_FORM");
			
			/**
			 * 가상계좌 입금완료
			 * 카카오 알림 보내기
			 */
			String msg = "";
			String mngMsg = "";
			String templeteCode = "";
			String tel = "";
			int usrId = 0;
			reqMap.put("encKey", ENC_KEY);
			
			Map<String, Object> kakaoMap = rsvDao.selectResPayInfo(reqMap);
			usrId = MapUtils.getIntValue(kakaoMap, "USR_ID");
			String pushMsg = Utils.setStringFormatInteger(MapUtils.getString(kakaoMap, "GET_AMNT")) + "원 입금이 확인되었습니다.";
			
			String addr = "";
			String rsvDt = "";
			
			if(type.equals("R")){
				String center = MapUtils.getString(rsvMap, "STORE_CENTER", "N");
				
				if(center.equals("Y")){
					templeteCode = "reservation_complete_center_v9";
				} else {
					templeteCode = "reservation_complete_branch_v8";
				}
				
				
				/**
				 	#{name} 고객님의 환전대금 입금이 확인되었습니다.

					■입금내역
					- 신청외화: #{r_currency} #{r_amount}
					- 결제금액: #{r_total_amount}원
					
					■수령안내
					- 수령장소: #{r_delivery_location}
					- 수령예정일시: #{r_delivery_date}
					- 배송담당자: #{r_delivery_staff}
					
					※ 고객님의 환전 예약이 완료되었으며, 지정하신 수령예정 시간과 장소에서 외화를 수령하시면 됩니다. 수령 시간 변경 등이 필요한 경우 고객센터로 문의하시기 바랍니다.
					
					버튼명: 수령장소 안내
					버튼url: https://weys.exchange/info/#{url_location}
					

				 	#{name} 고객님의 환전대금 입금이 확인되었습니다.

					■입금내역
					- 신청외화: #{r_currency} #{r_amount}
					- 결제금액: #{r_total_amount}원
					
					■수령안내
					- 수령장소: #{r_delivery_location}
					- 수령예정일시: #{r_delivery_date}
					- 교환증코드: #{r_delivery_code}
					
					※ 고객님의 환전 예약이 완료되었으며, 지정하신 수령예정 시간과 장소에서 외화를 수령하시면 됩니다. 수령 시간 변경 등이 필요한 경우 고객센터로 문의하시기 바랍니다.
					※ [수령장소 안내] 버튼을 눌러 자세한 수령장소 위치를 확인하세요.
					
					버튼명: 수령장소 안내
					버튼링크: https://weys.exchange/info/#{url_location}
				 */
				msg = MapUtils.getString(kakaoMap, "RSV_NM") + " 고객님의 환전대금 입금이 확인되었습니다.\n\n";
				msg += "■입금내역\n";
				msg += "- 신청외화: " + MapUtils.getString(kakaoMap, "UNIT") + " " + Utils.setStringFormatInteger(MapUtils.getString(kakaoMap, "RSV_AMNT")) +"\n";
				msg += "- 결제금액: " + Utils.setStringFormatInteger(MapUtils.getString(kakaoMap, "GET_AMNT")) + "원\n\n";
				msg += "■수령안내\n";
				msg += "- 수령장소: " + MapUtils.getString(kakaoMap, "STORE_NM") + "\n";
				msg += "- 수령예정일시: " + MapUtils.getString(kakaoMap, "RSV_DT") + " " + MapUtils.getString(kakaoMap, "RSV_TM") + "\n";
				

				if(center.equals("Y")){
					msg += "- 교환증코드: " + MapUtils.getString(kakaoMap, "RSV_QR") + "\n\n";
					msg += "※ 고객님의 환전 예약이 완료되었으며, 지정하신 수령예정 시간과 장소에서 외화를 수령하시면 됩니다. 수령 시간 변경 등이 필요한 경우 고객센터로 문의하시기 바랍니다.\n";
					msg += "※ [수령장소 안내] 버튼을 눌러 자세한 수령장소 위치를 확인하세요.";
					
				} else {
					msg += "- 배송담당자: " + MapUtils.getString(kakaoMap, "ADMIN_TEL") + "\n\n";
					msg += "※ 고객님의 환전 예약이 완료되었으며, 지정하신 수령예정 시간과 장소에서 외화를 수령하시면 됩니다. 수령 시간 변경 등이 필요한 경우 고객센터로 문의하시기 바랍니다.";
				}

				pushMsg += MapUtils.getString(kakaoMap, "RSV_DT") + " " + MapUtils.getString(kakaoMap, "RSV_TM") + " ";
				pushMsg += MapUtils.getString(kakaoMap, "STORE_NM") + "에서 ";
				pushMsg += MapUtils.getString(kakaoMap, "UNIT") + " " + Utils.setStringFormatInteger(MapUtils.getString(kakaoMap, "RSV_AMNT")) + "을 수령하시기 바랍니다.";
				
				mngMsg = "[당일 예약] 시간 : " + MapUtils.getString(kakaoMap, "RSV_TM");
				mngMsg += " 외화: " + MapUtils.getString(kakaoMap, "UNIT") + " " + Utils.setStringFormatInteger(MapUtils.getString(kakaoMap, "RSV_AMNT"));
				mngMsg += " 장소 : " + MapUtils.getString(kakaoMap, "STORE_NM");
				
				addr = MapUtils.getString(kakaoMap, "STORE_NM");
				rsvDt = MapUtils.getString(kakaoMap, "RSV_DT") + " " + MapUtils.getString(kakaoMap, "RSV_TM");
			} else {
				
				templeteCode = "reservation_complete_address";
				/**
				 	[웨이즈]

					조은용 고객님의 환전대금 입금이 확인되었습니다.
					
					■입금내역
					- 신청외화: USD 1,200
					- 결제금액: 1,302,000원
					
					■배송안내
					- 배송장소: 서울 강남구 테헤란로 337 포스코빌딩 14층
					- 배송예정일시: 2018/03/17 10:00~12:00
					
					※ 신청하신 환전이 완료되었으며, 선택하신 장소와 시간에 외화가 배송될 예정입니다. 배송일시 변경 등이 필요한 경우 고객센터로 문의하시기 바랍니다.
				 */
				String tm = MapUtils.getString(kakaoMap, "RSV_TM");
				int deliverTm = MapUtils.getIntValue(kakaoMap, "DELIVER_TIME");
				StringTokenizer st = new StringTokenizer(tm, ":");
				String hour = st.nextToken();
				String min = st.nextToken();
				
				int iHour = Integer.parseInt(hour) + deliverTm;
				hour = iHour + ":" + min;
				
				msg = "[웨이즈]\n\n";
				msg += MapUtils.getString(kakaoMap, "RSV_NM") + " 고객님의 환전대금 입금이 확인되었습니다.\n\n";
				msg += "■입금내역\n";
				msg += "- 신청외화: " + MapUtils.getString(kakaoMap, "UNIT") + " " + Utils.setStringFormatInteger(MapUtils.getString(kakaoMap, "RSV_AMNT")) +"\n";
				msg += "- 결제금액: " + Utils.setStringFormatInteger(MapUtils.getString(kakaoMap, "GET_AMNT")) + "원\n\n";
				msg += "■배송안내\n";
				msg += "- 배송장소: " + MapUtils.getString(kakaoMap, "ADDR") + "\n";
				msg += "- 배송예정일시: " + MapUtils.getString(kakaoMap, "RSV_DT") + " " + MapUtils.getString(kakaoMap, "RSV_TM") + " ~ " + hour + "\n\n";
				msg += "※ 신청하신 환전이 완료되었으며, 선택하신 장소와 시간에 외화가 배송될 예정입니다. 배송일시 변경 등이 필요한 경우 고객센터로 문의하시기 바랍니다.";
				
				pushMsg += MapUtils.getString(kakaoMap, "RSV_DT") + " " + MapUtils.getString(kakaoMap, "RSV_TM") + " ~ " + hour + " 경 ";
				pushMsg += MapUtils.getString(kakaoMap, "UNIT") + " " + Utils.setStringFormatInteger(MapUtils.getString(kakaoMap, "RSV_AMNT")) + " 배송 예정입니다.";

				mngMsg = "[당일 배송] 시간 : " + MapUtils.getString(kakaoMap, "RSV_TM") + " ~ " + hour + " 경 ";
				mngMsg += " 외화: " + MapUtils.getString(kakaoMap, "UNIT") + " " + Utils.setStringFormatInteger(MapUtils.getString(kakaoMap, "RSV_AMNT"));
				mngMsg += " 장소 : " + MapUtils.getString(kakaoMap, "ADDR");
				
				addr = MapUtils.getString(kakaoMap, "ADDR");
				rsvDt = MapUtils.getString(kakaoMap, "RSV_DT") + " " + MapUtils.getString(kakaoMap, "RSV_TM") + " ~ " + hour + " 경 ";
			}
			
			if(SERVER_TYPE.equals("USER")){
				Map<String, Object> mailMap = new HashMap<>();
				mailMap.put("usrNm", MapUtils.getString(kakaoMap, "RSV_NM"));
				mailMap.put("rsvNo", MapUtils.getString(kakaoMap, "RSV_NO"));
				mailMap.put("regDttm", Utils.getTodayDate("yyyy년 MM월 dd일 a hh시 mm분"));
				mailMap.put("unit", MapUtils.getString(kakaoMap, "UNIT_NM") + "(" + MapUtils.getString(kakaoMap, "UNIT") + ")");
				mailMap.put("rsvAmnt", Utils.setStringFormatInteger(MapUtils.getString(kakaoMap, "RSV_AMNT")));
				mailMap.put("getAmnt", Utils.setStringFormatInteger(MapUtils.getString(kakaoMap, "GET_AMNT")));
				
				mailMap.put("addr", addr);
				mailMap.put("rsvDt", rsvDt);
				mailMap.put("rsvQr", qr);

				emailVO.setEmailMap(mailMap);
				emailVO.setSubject(MapUtils.getString(kakaoMap, "RSV_NM") + " 고객님, 입금 확인이 완료되었습니다.");
				emailVO.setVeloTmp("ready.vm");
				emailVO.setFrom(EMAIL_ID);
				emailVO.setReceiver(MapUtils.getString(kakaoMap, "USR_EMAIL"));
				
				try{
					mailer.sendEmail(emailVO);
				} catch (Exception e) {
					logger.info("mail error : " + e.getMessage());
				}
			}

			String nation = MapUtils.getString(kakaoMap, "NATION");
			String usrTel = MapUtils.getString(kakaoMap, "USR_TEL");
			if(nation.equals("82")){
				if(usrTel.startsWith("010")){
					usrTel = usrTel.substring(1);
				}
			}
			tel = nation + usrTel;
			
			KakaoClient kakao = new KakaoClient(Constant.KAKAO_TALK_MSG, IB_KAKAO_ID, IB_KAKAO_PWD, IB_KAKAO_SENDER_KEY, IB_FROM_TEL);
			if(type.equals("R")){
				String msgBtn = "수령장소 안내";
				String msgBtnUrl = "https://weys.exchange/info/" + MapUtils.getString(rsvMap, "STORE_TAG");
				kakao.sendMsg(tel, msg, templeteCode, msgBtn, msgBtnUrl);
			} else {
				kakao.sendMsg(tel, msg, templeteCode);
			}

			if(usrId > 0){
				Map<String, Object> talk =  new HashMap<>();
				talk.put("usrId", usrId);
				talk.put("msg", msg);
				talk.put("templete", templeteCode);
				rsvDao.insertKakaoLog(talk);
				
				/**
				 * 사용자에게 입금 처리 완료 푸쉬 발송
				 */
				Map<String, Object> uuidMap = rsvDao.selectVbFinUuid(reqMap);
				if(uuidMap != null){
					String uuid = MapUtils.getString(uuidMap, "UUID");
					String os = MapUtils.getString(uuidMap, "OS");
					
					sendPush(os, uuid, "reserve", "income", "입금이 확인되었습니다.", pushMsg, rsvId + "");
				}

				Map<String, Object> alarm =  new HashMap<>();
				alarm.put("usrId", MapUtils.getInteger(kakaoMap, "USR_ID"));
				alarm.put("armTp", "I");
				alarm.put("armTitle", pushMsg);
				alarm.put("armTarget", "rsv");
				alarm.put("armVal", "/api/user/gVerSion/rsv/" + MapUtils.getInteger(kakaoMap, "RSV_ID"));
				rsvDao.insertAlarm(alarm);
			}
			
			/**
			 * 당일 예약 및 배송이면 모든담당자에게 알림
			 */
			rsvDt = MapUtils.getString(kakaoMap, "RSV_DT");
			String todayDt = Utils.getDiffDate(0);
			if(rsvDt.equals(todayDt)){
				List<String> adminUuid = rsvDao.selectAdminUuid();
				
				for(String uuid : adminUuid){
					JSONObject dataJson = new JSONObject();
					dataJson.put("type", "reserve");
					dataJson.put("st", "income");
					dataJson.put("val", rsvId);
					dataJson.put("message", mngMsg);
					
					JSONObject json = new JSONObject();
					json.put("to", uuid);
					json.put("data", dataJson);
					
					PushService push = new PushService(json, FCM_SERVER_KEY, FCM_SEND_URL);
					Thread t = new Thread(push);
					t.start();
				}
			}
		}
		
		return res;
	}

	public List<RsvListVO> selectRsvDataExcel(Paging paging) {

		if(paging.getReservation() == null){
			paging.setStartDt(Utils.getDiffDate(0));
			paging.setEndDt(Utils.getDiffDate(3));
		} else {
			StringTokenizer st = new StringTokenizer(paging.getReservation(), "-");
			paging.setStartDt(st.nextToken().trim());
			paging.setEndDt(st.nextToken().trim());
		}

		if(paging.getRsvStVal() != null){
			String rsvSt = paging.getRsvStVal();
			if(rsvSt.equals("t") || rsvSt.equals("")){
				paging.setRsvStVal(null);
			} else {
				StringTokenizer st = new StringTokenizer(rsvSt, ",");
				List<String> list = new ArrayList<>();
				while(st.hasMoreTokens()){
					list.add(st.nextToken());
				}
				paging.setListData(list);
			}
		}
		if(paging.getRsvFormVal() != null){
			String rsvForm = paging.getRsvFormVal();
			if(rsvForm.equals("t") || rsvForm.equals("")){
				paging.setRsvFormVal(null);
			} else {
				StringTokenizer st = new StringTokenizer(rsvForm, ",");
				List<String> list = new ArrayList<>();
				while(st.hasMoreTokens()){
					list.add(st.nextToken());
				}
				paging.setListData1(list);
			}
		}
		paging.setEncKey(ENC_KEY);
		
		if(paging.getSearchType() == null)
			paging.setSearchType("t");
		return rsvDao.getRsvExcelList(paging);
	}

	public int updateRsvDt(Map<String, Object> reqMap) {
		
		Map<String, Object> oriMap = rsvDao.selectRsvInfo(reqMap);
		
		int res = rsvDao.updateRsvDt(reqMap);
		
		/**
		 * 아이트립 
		 */
		String oriDt = MapUtils.getString(oriMap, "RSV_DT");
		String adminSt = MapUtils.getString(oriMap, "ADMIN_ST");
		
		if(res > 0){
			/**
			 * 센터 예약일 경우 인수완료된 예약이라면 인수를 풀고 
			 * 그룹 예약 상태를 변경으로 수정한다
			 */
			if(adminSt.equals("Y") && !(oriDt.equals(MapUtils.getString(reqMap, "rsvDt")))){
				reqMap.put("st", "M");
				int ch = rsvDao.insertRsvRetLog(reqMap);
//				int ch = rsvDao.updateGrpLog(reqMap);
				if(ch > 0){
					rsvDao.updateRollBackRsv(reqMap);
				}
			}
		}
		
		return res;
	}

	public int updateReady(Map<String, Object> reqMap) {
		
		String rsvIds = MapUtils.getString(reqMap, "rsvId");
		
		if(rsvIds == null)
			return 0;
		
		if(rsvIds.contains(",")){
			StringTokenizer st = new StringTokenizer(rsvIds, ",");
			
			while(st.hasMoreTokens()){
				String id = st.nextToken();
				rsvDao.updateRsvReady(id);
			}
			
		} else {
			rsvDao.updateRsvReady(rsvIds);
		}

		int usd = MapUtils.getIntValue(reqMap, "usd", 0);
		int jpy = MapUtils.getIntValue(reqMap, "jpy", 0);
		int hkd = MapUtils.getIntValue(reqMap, "hkd", 0);
		int twd = MapUtils.getIntValue(reqMap, "twd", 0);
		
		String amnt = "USD : " + usd + ", JPY : " + jpy + " , HKD : " + hkd + " , twd : " + twd;
		String log = "";
		if(usd > 0){
			log += "USD 100 : " +  MapUtils.getIntValue(reqMap, "usd_100", 0) + "\n";
			log += "USD 50 : " +  MapUtils.getIntValue(reqMap, "usd_50", 0) + "\n";
			log += "USD 20 : " +  MapUtils.getIntValue(reqMap, "usd_20", 0) + "\n";
			log += "USD 10 : " +  MapUtils.getIntValue(reqMap, "usd_10", 0) + "\n";
		}
		if(jpy > 0){
			log += "JPY 10000 : " +  MapUtils.getIntValue(reqMap, "jpy_10000", 0) + "\n";
			log += "JPY 5000 : " +  MapUtils.getIntValue(reqMap, "jpy_5000", 0) + "\n";
			log += "JPY 1000 : " +  MapUtils.getIntValue(reqMap, "jpy_1000", 0) + "\n";
		}
		if(hkd > 0){
			log += "HKD 500 : " +  MapUtils.getIntValue(reqMap, "hkd_500", 0) + "\n";
			log += "HKD 100 : " +  MapUtils.getIntValue(reqMap, "hkd_100", 0) + "\n";
		}
		if(twd > 0){
			log += "TWD 1000 : " +  MapUtils.getIntValue(reqMap, "twd_1000", 0) + "\n";
			log += "TWD 500 : " +  MapUtils.getIntValue(reqMap, "twd_500", 0) + "\n";
			log += "TWD 100 : " +  MapUtils.getIntValue(reqMap, "twd_100", 0) + "\n";
		}

		reqMap.put("amnt", amnt);
		reqMap.put("log", log);
		
		return rsvDao.insertReadyRsv(reqMap);
	}

	public List<RsvListVO> selectRsvCancelExcel(Paging paging) {
		
		paging.setEncKey(ENC_KEY);

		if(paging.getReservation() == null){
			paging.setStartDt(Utils.getDiffDate(-3));
			paging.setEndDt(Utils.getDiffDate(3));
		} else {
			StringTokenizer st = new StringTokenizer(paging.getReservation(), "-");
			paging.setStartDt(st.nextToken().trim());
			paging.setEndDt(st.nextToken().trim());
		}
		
		return rsvDao.selectRsvCancelExcel(paging);
	}

	public Map<String, Object> getRsvStoreInfo(Map<String, Object> reqMap) {
		
		reqMap.put("encKey", ENC_KEY);
		Map<String, Object> resultMap = rsvDao.selectRsvStoreInfo(reqMap);
		
		String unit = MapUtils.getString(resultMap, "UNIT");
		List<Map<String, Object>> storeList = rsvDao.selectRsvStoreList(unit);
		resultMap.put("store", storeList);
		return resultMap;
	}

	public int updateRsvStore(Map<String, Object> reqMap, UserVO userVO) {
		return rsvDao.updateRsvStore(reqMap);
	}

	public int updateRsvBack(Map<String, Object> reqMap) {
		
		int rsvId = MapUtils.getIntValue(reqMap, "rsvId", 0);
		if(rsvId == 0){
			return 0;
		}

		String rsvSt = MapUtils.getString(reqMap, "rsvSt");
		
		int res = 0;
		if(rsvSt.equals("M") || rsvSt.equals("CB")){
			/**
			 * 보너스 쿠폰 사용 여부 확인
			 */
			int bonusId = rsvDao.selectRsvBonusId(rsvId);
			if(bonusId > 0){
				res = rsvDao.updateCouponBack(bonusId);
				if(res == 0){
					return 0;
				}
			}
			res = rsvDao.updateRsvBack(rsvId);
		} else {
			/**
			 * 환불신청 복구
			 */
			res = rsvDao.updateRsvBackCancel(rsvId);
			
			/**
			 * 지점 예약된 배송건이라면 다시 복구
			 * 센터지점 && 그룹 완료 상태면
			 */
			if(res > 0){
				rsvDao.updateRsvRetLogBack(rsvId);
//				rsvDao.updateCenterBack(rsvId);
			}
		}
		
		return res;
	}

	public List<Map<String, Object>> selectRsvUnitList() {
		return rsvDao.selectRsvUnitList();
	}

	public int updateSendMissMatch(Map<String, Object> reqMap) {

		reqMap.put("encKey", ENC_KEY);
		reqMap.put("templete", "reservation_mismatch");
		
		int checkCnt = rsvDao.selectMissMatchKakao(reqMap);
		
		int res = 0;
		if(checkCnt == 0){
			Map<String, Object> uuidMap = rsvDao.selectMissUuid(reqMap);
			
			/**
			 * 알림톡 발송
			 * 
			 	[웨이즈]

				#{name} 고객님의 예약 건 입금 정보 오류(예상)로 인하여 입금 확인 지연 중입니다. 고객센터로 문의하시기 바랍니다.
				
				(입금 정보 오류란 예약자명과 입금자명이 일치하지 않거나 결제금액이 정확하게 입금되지 않은 경우를 뜻합니다.)
			 */
			String msg = "[웨이즈]\n\n";
			msg += MapUtils.getString(uuidMap, "RSV_NM") + " 고객님의 예약 건 입금 정보 오류(예상)로 인하여 입금 확인 지연 중입니다. 고객센터로 문의하시기 바랍니다.\n\n";
			msg += "(입금 정보 오류란 예약자명과 입금자명이 일치하지 않거나 결제금액이 정확하게 입금되지 않은 경우를 뜻합니다.)";

			String nation = MapUtils.getString(uuidMap, "NATION");
			String usrTel = MapUtils.getString(uuidMap, "RSV_TEL");
			if(nation.equals("82") && usrTel.startsWith("010"))
				usrTel = usrTel.substring(1);
			String tel = nation + usrTel;
			
			KakaoClient kakao = new KakaoClient(Constant.KAKAO_TALK_MSG, IB_KAKAO_ID, IB_KAKAO_PWD, IB_KAKAO_SENDER_KEY, IB_FROM_TEL);
			kakao.sendMsg(tel, msg, "reservation_mismatch");

			reqMap.put("msg", msg);
			res = rsvDao.insertMissmatchKakao(reqMap);
		} else {
			return -1;
		}
		
		return res;
	}

	public List<Map<String, Object>> selectRsvMemo(Map<String, Object> reqMap) {
		
		return rsvDao.selectRsvMemo(reqMap);
	}

	public int insertRsvMemo(Map<String, Object> reqMap) {

		return rsvDao.insertRsvMemo(reqMap);
	}
	
	public int selectGrpCnt(Map<String, Object> reqMap) {
		return rsvDao.selectGrpCnt(reqMap);
	}

	public int updateRsvGroup(GroupVO groupVO) throws Exception {
		
		List<Integer> centerList = rsvDao.selectCenterList();

		int hanjin1T = 11;
		if(SERVER_TYPE.equals("TEST")){
			hanjin1T = 13;
		}
		
		String rsvDt = groupVO.getRsvDt();
		String endDt = Utils.getNextDt(rsvDt, 1);
		String rsvTm = "04:55";
		
		for(int storeId : centerList){
			groupVO.setStoreId(storeId);

			Map<String, Object> reqMap = new HashMap<>();
			reqMap.put("storeId", storeId);
			reqMap.put("startDt", rsvDt);
			reqMap.put("endDt", endDt);
			reqMap.put("rsvTm", rsvTm);
			
			/**
			 * 그룹 생성
			 * 리스트 겟수 없으면 QR생성 안함
			 */
			int rsvList = rsvDao.selectRsvGrpADCnt(reqMap);
			if(rsvList > 0){
				boolean checkBarcode = true;
				String qr = "";
				while (checkBarcode) {
					qr = Barcode.CreateQrCode(2);
					int checkCnt = rsvDao.selectGrpQrCnt(qr);
					if (checkCnt == 0) {
						checkBarcode = false;
					}
				}
				String qrCodeUrl = Barcode.CreateQRCodePng(qr, UPLOAD_PATH, "grp");
				groupVO.setBarcode(qr);
				groupVO.setBarcodeUrl(qrCodeUrl);
			}
			groupVO.setStoreId(storeId);
			groupVO.setGroupTp("D");
			int res = rsvDao.insertRsvGroup(groupVO);
			
			if(res > 0){
				reqMap.put("groupId", groupVO.getGroupId());
				res = rsvDao.insertRsvGrpLogAD(reqMap);
				rsvDao.updateRsvGrpAD(reqMap);
			}
			
			/**
			 * 회수 생성
			 * 한진 1T -> 2일전 미회수 건
			 * 그외 -> 1일전 미회수 건
			 */
			
			String r1 = Utils.getNextDt(groupVO.getRsvDt(), -1);
			if(storeId == hanjin1T){
				/**
				 * 24시간 
				 */
				r1 = Utils.getNextDt(groupVO.getRsvDt(), -2);
			}
			
			reqMap = new HashMap<>();
			reqMap.put("storeId", storeId);
			reqMap.put("twoday", r1);
			List<Map<String, Object>> retList1 = rsvDao.selectRsvRetLog(reqMap);
			List<Map<String, Object>> retList2 = rsvDao.selectRsvNotGet(reqMap);

			groupVO.setBarcode(null);
			groupVO.setBarcodeUrl(null);
			
			int retCnt = retList1.size() + retList2.size();
			if(retCnt > 0){
				boolean checkBarcode = true;
				String qr = "";
				while (checkBarcode) {
					qr = Barcode.CreateQrCode(2);
					int checkCnt = rsvDao.selectGrpQrCnt(qr);
					if (checkCnt == 0) {
						checkBarcode = false;
					}
				}
				String qrCodeUrl = Barcode.CreateQRCodePng(qr, UPLOAD_PATH, "grp");
				groupVO.setBarcode(qr);
				groupVO.setBarcodeUrl(qrCodeUrl);
			}
			
			groupVO.setGroupTp("R");
			res = rsvDao.insertRsvGroup(groupVO);
			
			if(res > 0 && retCnt > 0){
				List<Map<String, Object>> retList = retList1;
				retList.addAll(retList.size(), retList2);

				reqMap.put("groupId", groupVO.getGroupId());
				reqMap.put("retList", retList);
				res = rsvDao.insertRsvRetGrpLogAD(reqMap);
				
				if(retList1.size() > 0){
					rsvDao.updateRsvRetLog(retList1);
				}
			}
		}
		
		
		return 1;
	}

	public List<GroupVO> getGrpList(Paging paging) {

		if (paging.getIsSearch() == 1)
			paging.setSearchPage(0);
		else
			paging.setSearchPage((paging.getPageNo() - 1) * paging.getPageSize());
		
		if(paging.getReservation() == null){
			paging.setStartDt(Utils.getDiffDate(0));
			paging.setEndDt(Utils.getDiffDate(0));
		} else {
			StringTokenizer st = new StringTokenizer(paging.getReservation(), "-");
			paging.setStartDt(st.nextToken().trim());
			paging.setEndDt(st.nextToken().trim());
		}
		
		return rsvDao.getGrpList(paging);
	}

	public int getGrpListCnt(Paging paging) {
		return rsvDao.getGrpListCnt(paging);
	}

	public Map<String, Object> selectGrpPdfView(GroupVO groupVO) throws Exception {
		
		groupVO.setEncKey(ENC_KEY);
		List<RsvListVO> rsvList = rsvDao.selectRsvGroupView(groupVO);
		
		if(rsvList == null){
			return null;
		} 
		
		/**
		 * 그룹 인수건 있을때
		 */
		List<Map<String, Object>> unitList = rsvDao.selectGroupCntView(groupVO);
		String unitText = "";
		int total = 0;
		
		for(Map<String, Object> unit : unitList){
			int cnt = MapUtils.getIntValue(unit, "CNT");
			String u = MapUtils.getString(unit, "UNIT");
			total = total + cnt;
			if(unitText.equals("")){
				unitText = u + ":" + cnt + "건";
			} else {
				unitText = unitText + " / " +  u + ":" + cnt + "건";
			}
		}
		
		Map<String, Object> grpInfo = rsvDao.selectGrpInfo(groupVO.getGroupId());
		
		String dt = MapUtils.getString(grpInfo, "RSV_DT");
		int rsvDay = Utils.getTdayDay(dt);
		String [] dayString = new String []{"일", "월", "화", "수", "목", "금", "토"};
		
		String rsvDt = "";
		
		StringTokenizer st = new StringTokenizer(dt, ".");
		
		String [] dateString = new String [] {"년", "월", "일"};
		int index = 0;
		while(st.hasMoreTokens()){
			rsvDt = rsvDt + st.nextToken() + dateString[index] + " ";
			index = index + 1;
		}
		rsvDt = rsvDt + dayString[rsvDay];
		
		String title = "입/출고 확인서";
		String tp = MapUtils.getString(grpInfo, "GROUP_TP");
		if(tp.equals("R")){
			title = "회수 확인서";
		}
		
		grpInfo.put("rsvList", rsvList);
		grpInfo.put("unitText", unitText);
		grpInfo.put("total", total);
		grpInfo.put("rsvDt", rsvDt);
		grpInfo.put("title", title);
		grpInfo.put("tp", tp);
		grpInfo.put("qr", SERVER_PATH + "imgView/" + MapUtils.getString(grpInfo, "BARCODE_URL"));
		
		return grpInfo;
	}

	public List<Map<String, Object>> getVbankList(Paging paging) {

		paging.setEncKey(ENC_KEY);
		if (paging.getIsSearch() == 1)
			paging.setSearchPage(0);
		else
			paging.setSearchPage((paging.getPageNo() - 1) * paging.getPageSize());
		
		if(paging.getReservation() == null){
			paging.setStartDt(Utils.getDiffDate(-3));
			paging.setEndDt(Utils.getDiffDate(0));
		} else {
			StringTokenizer st = new StringTokenizer(paging.getReservation(), "-");
			paging.setStartDt(st.nextToken().trim());
			paging.setEndDt(st.nextToken().trim());
		}
		
		return rsvDao.getVbankList(paging);
	}

	public int getVbankListCnt(Paging paging) {
		return rsvDao.getVbankListCnt(paging);
	}

	public int updateVbankChk(Map<String, Object> reqMap) {
	
		reqMap.put("chkSt", "H");
		return rsvDao.updateVbankChk(reqMap);
	}

	public int updateReturn(Map<String, Object> reqMap) throws Exception {
		
		reqMap.put("encKey", ENC_KEY);
		
		Map<String, Object> cancelInfo = rsvDao.selectCancelSt(reqMap);
		
		int res = 0;
		if(cancelInfo == null){
			return -1;
		} else {
			reqMap.put("rsvSt", Constant.RSV_CANCEL_READY);
			res = rsvDao.updateCancelRsv(reqMap);
		}
		
		/**
		 * 예약 로그 등록
		 */
		if(res > 0){
			String rsvSt = MapUtils.getString(cancelInfo, "RSV_ST");
			int rsvId = MapUtils.getIntValue(reqMap, "rsvId");
			
			reqMap.put("asIs", rsvSt);
			reqMap.put("toBe", Constant.RSV_CANCEL_READY);
			rsvDao.insertRsvLog(reqMap);
			
			/**
			 * 취소 내용 사용자 이메일로 발송
			 * 외화, 취소시점 날짜, 환급받을 계좌 정보
			 * 
			 * 취소 수수료에 대한 변경으로 수정사항
			 */
			Map<String, Object> emailInfo = rsvDao.selectCancelEmail(reqMap);
			String rsvDt = MapUtils.getString(emailInfo, "RSV_DT");
			
			/**
			 * 예약일 당일 전 쿠폰을 사용했더라면 
			 */
			int couponId = MapUtils.getIntValue(emailInfo, "COUPON_ID", 0);
			if(couponId > 0){
				rsvDao.updateCouponReturn(couponId);
			}

			/**
			 * 보너스 쿠폰 사용 여부
			 */
			int bonusId = MapUtils.getIntValue(emailInfo, "BONUS_ID", 0);
			if(bonusId > 0){
				rsvDao.updateCouponReturn(bonusId);
			}
			
			/**
			 * 아이트립
			 * 당일 취소시
			 * 센터 - 취소로 상태 변경. 회수가 필요함
			 */
			String storeCenter = MapUtils.getString(cancelInfo, "STORE_CENTER"); 
			if(storeCenter.equals("Y") && Utils.diffTwoDate(rsvDt, Utils.getDiffDate(0)) <= 0){
				reqMap.put("st", "C");
				rsvDao.insertRsvRetLog(reqMap);
//				rsvDao.updateRsvGrpCancelLog(rsvId);
			}

			/**
			 * 예약 취소 알림 등록
			 * 입금전 취소는 푸쉬만, 알림은 등록 안함
			 */
			String armTitle = MapUtils.getString(emailInfo, "UNIT") + Utils.setStringFormatInteger(MapUtils.getString(emailInfo, "RSV_AMNT"));
			int usrId = MapUtils.getIntValue(cancelInfo, "USR_ID", 0);
			if(usrId > 0){
				armTitle += " 예약취소 및 " + Utils.setStringFormatInteger(MapUtils.getString(emailInfo, "CANCEL_AMNT"));
				armTitle += " 원 환불요청이 접수되었습니다.(영업일 기준 2일 소요)";

				Map<String, Object> alarm =  new HashMap<>();
				alarm.put("usrId", usrId);
				alarm.put("armTp", "I");
				alarm.put("armTitle", armTitle);
				alarm.put("armTarget", "rsv");
				alarm.put("armVal", "/api/user/gVerSion/rsv/" + MapUtils.getIntValue(reqMap, "rsvId"));
				rsvDao.insertAlarm(alarm);
			}
			
			/**
			 * 메일 보내기
			 */
			String rsvForm = MapUtils.getString(emailInfo, "RSV_FORM");
			String addr = "";
			String emailDt = "";
			if(rsvForm.equals("R")){
				addr = MapUtils.getString(emailInfo, "STORE_NM");
				emailDt = MapUtils.getString(emailInfo, "RSV_DT") + " " + MapUtils.getString(emailInfo, "RSV_TM");
			} else {
				addr = MapUtils.getString(emailInfo, "RSV_ADDR") + " " + MapUtils.getString(emailInfo, "RSV_ADDR_DETAIL");
				
				String tm = MapUtils.getString(emailInfo, "RSV_TM");
				int deliverTm = MapUtils.getIntValue(emailInfo, "DELIVER_TIME");
				StringTokenizer st = new StringTokenizer(tm, ":");
				String hour = st.nextToken();
				String min = st.nextToken();
				
				int iHour = Integer.parseInt(hour) + deliverTm;
				hour = iHour + ":" + min;
				emailDt = MapUtils.getString(emailInfo, "RSV_DT") + " " + MapUtils.getString(emailInfo, "RSV_TM") + " ~ " + hour + " 경 ";
			}

			String template = "";
			String subject = "";
			Map<String, Object> mailMap = new HashMap<>();
			mailMap.put("usrNm", MapUtils.getString(emailInfo, "RSV_NM"));
			mailMap.put("rsvNo", MapUtils.getString(emailInfo, "RSV_NO"));
			mailMap.put("regDttm", Utils.getTodayDate("yyyy년 MM월 dd일 a hh시 mm분"));
			
			if(rsvSt.equals("S")){
				mailMap.put("unit", MapUtils.getString(emailInfo, "UNIT_NM") + "(" + MapUtils.getString(emailInfo, "UNIT") + ")");
				mailMap.put("rsvAmnt", Utils.setStringFormatInteger(MapUtils.getString(emailInfo, "RSV_AMNT")));
				mailMap.put("addr", addr);
				mailMap.put("rsvDt", emailDt);
				template = "befCancel.vm";
				subject = MapUtils.getString(emailInfo, "RSV_NM") + " 고객님, 환전 예약이 취소되었습니다.";
			} else {
				mailMap.put("getAmnt", Utils.setStringFormatInteger(MapUtils.getString(emailInfo, "GET_AMNT")));
				mailMap.put("cancelCms", "-" + Utils.setStringFormatInteger(MapUtils.getString(emailInfo, "CANCEL_CMS")));
				mailMap.put("cancelAmnt", Utils.setStringFormatInteger(MapUtils.getString(emailInfo, "CANCEL_AMNT")));
				mailMap.put("bankNm", MapUtils.getString(reqMap, "retBankNm"));
				mailMap.put("bankCd", MapUtils.getString(reqMap, "retBankCd"));
				template = "cancel.vm";
				subject = MapUtils.getString(emailInfo, "RSV_NM") + " 고객님, 환불이 접수되었습니다.";
			}
			
			emailVO.setEmailMap(mailMap);
			emailVO.setSubject(subject);
			emailVO.setVeloTmp(template);
			emailVO.setFrom(EMAIL_ID);
			emailVO.setReceiver(MapUtils.getString(emailInfo, "RSV_EMAIL"));
			
			try{
				mailer.sendEmail(emailVO);
			} catch (Exception e) {
				logger.info("mail error : " + e.getMessage());
			}

			
			/**
			 * 알림톡 메세지 보내기
			 	[웨이즈]

				조은용 고객님의 USD 1,200 예약이 취소되었습니다.
				
				[웨이즈]
				
				조은용 고객님의 USD 1,200 예약취소 및 환불요청이 접수되었습니다.
				
				■환불 요청내역
				- 결제하신 금액: 1,300,000원
				- 취소수수료: -8,240원
				- 환불받으실 금액: 1,296,500원
				
				(영업일 기준 2일 소요)
			 */
			String templete = "";
			String msg = "[웨이즈]\n\n";
			
			int cancelCms = MapUtils.getIntValue(emailInfo, "CANCEL_CMS");

			if(rsvSt.equals("S")){
				msg += MapUtils.getString(emailInfo, "RSV_NM") + " 고객님의 " + MapUtils.getString(emailInfo, "UNIT") + " " + Utils.setStringFormatInteger(MapUtils.getString(emailInfo, "RSV_AMNT"));
				msg += " 예약이 취소되었습니다.";
				templete = "reservation_cancel_before_v6";
			} else {
				msg += MapUtils.getString(emailInfo, "RSV_NM") + " 고객님의 " + MapUtils.getString(emailInfo, "UNIT") + " " + Utils.setStringFormatInteger(MapUtils.getString(emailInfo, "RSV_AMNT"));
				msg += " 예약취소 및 환불요청이 접수되었습니다.\n\n";
				msg += "■환불 요청내역\n";
				msg += "- 결제하신 금액: " + Utils.setStringFormatInteger(MapUtils.getString(emailInfo, "GET_AMNT")) + "원\n";
				msg += "- 취소수수료: -" + Utils.setStringFormatInteger(cancelCms + "") + "원\n";
				msg += "- 환불받으실 금액: " + Utils.setStringFormatInteger(MapUtils.getString(emailInfo, "CANCEL_AMNT")) + "원\n\n";
				msg += "(영업일 기준 2일 소요)";
				templete = "reservation_cancel_after_v6";
			}
			
			String nation = MapUtils.getString(emailInfo, "NATION");
			String usrTel = MapUtils.getString(emailInfo, "USR_TEL");
			if(nation.equals("82")){
				if(usrTel.startsWith("010")){
					usrTel = usrTel.substring(1);
				}
			}
			String tel = nation + usrTel;
			
			KakaoClient kakao = new KakaoClient(Constant.KAKAO_TALK_MSG, IB_KAKAO_ID, IB_KAKAO_PWD, IB_KAKAO_SENDER_KEY, IB_FROM_TEL);
			kakao.sendMsg(tel, msg, templete);

			if(usrId > 0){
				Map<String, Object> talk =  new HashMap<>();
				talk.put("usrId", usrId);
				talk.put("msg", msg);
				talk.put("templete", templete);
				rsvDao.insertKakaoLog(talk);
				
				Map<String, Object> usrPush = rsvDao.selectUsrUuid(usrId);
				if(usrPush != null){
					String uuid = MapUtils.getString(usrPush, "UUID");
					String os = MapUtils.getString(usrPush, "OS");
					String val = "";
					if(!rsvSt.equals("S")){
						val = rsvId + "";
					}
					sendPush(os, uuid, "reserve", "cancel", armTitle, msg, val);
				}
			}
			
			
			
		}
		
		return res;
	}

	public Map<String, Object> sendEmail() {
		
		Map<String, Object> reqMap = new HashMap<>();
		reqMap.put("usrId", 68350);
		reqMap.put("encKey", ENC_KEY);
		
		List<Map<String, Object>> usrList = rsvDao.selectTestInfo(reqMap);
		
		for(Map<String, Object> kakaoMap : usrList){
			Map<String, Object> mailMap = new HashMap<>();
			mailMap.put("usrNm", MapUtils.getString(kakaoMap, "RSV_NM"));
			mailMap.put("rsvNo", MapUtils.getString(kakaoMap, "RSV_NO"));
			mailMap.put("regDttm", MapUtils.getString(kakaoMap, "ICM_DTTM"));
			mailMap.put("unit", MapUtils.getString(kakaoMap, "UNIT_NM") + "(" + MapUtils.getString(kakaoMap, "UNIT") + ")");
			mailMap.put("rsvAmnt", Utils.setStringFormatInteger(MapUtils.getString(kakaoMap, "RSV_AMNT")));
			mailMap.put("getAmnt", Utils.setStringFormatInteger(MapUtils.getString(kakaoMap, "GET_AMNT")));
			
			mailMap.put("addr", MapUtils.getString(kakaoMap, "STORE_NM"));
			mailMap.put("rsvDt", MapUtils.getString(kakaoMap, "RSV_DT") + " " + MapUtils.getString(kakaoMap, "RSV_TM"));
			mailMap.put("rsvQr", MapUtils.getString(kakaoMap, "RSV_QR"));

			emailVO.setEmailMap(mailMap);
			emailVO.setSubject(MapUtils.getString(kakaoMap, "RSV_NM") + " 고객님, 입금 확인이 완료되었습니다.");
			emailVO.setVeloTmp("ready.vm");
			emailVO.setFrom(EMAIL_ID);
			emailVO.setReceiver(MapUtils.getString(kakaoMap, "USR_EMAIL"));
			
			try{
				mailer.sendEmail(emailVO);
			} catch (Exception e) {
				logger.info("mail error : " + e.getMessage());
			}
		}
		
		
		
		return null;
	}


	private void sendPush(String os, String uuid, String type, String st, String title, String msg, String val) throws Exception{
		
		if(os.equals("A")){
			JSONObject dataJson = new JSONObject();
			dataJson.put("type", type);
			dataJson.put("st", st);
			dataJson.put("message", title);
			dataJson.put("val", val);
			dataJson.put("sound", "default");
			
			JSONObject json = new JSONObject();
			json.put("to", uuid);
			json.put("data", dataJson);
			
			PushService push = new PushService(json, FCM_SERVER_KEY, FCM_SEND_URL);
			Thread t = new Thread(push);
			t.start();

		} else if(os.equals("I")){
			JSONObject pushObj = new JSONObject();
			pushObj.put("to", uuid);
			JSONObject dataJson = new JSONObject();
			dataJson.put("title", title);
			dataJson.put("contents", type);
			dataJson.put("st", st);
			dataJson.put("val", val);
			dataJson.put("img", "");
			
			JSONObject notiJson = new JSONObject();
			
			notiJson.put("title", title);
			notiJson.put("body", msg);
			notiJson.put("icon", "");
			notiJson.put("sound", "activated");
			notiJson.put("badge", "1");
			
			pushObj.put("content_available", true);
			pushObj.put("data", dataJson);
			pushObj.put("notification", notiJson);
			
			PushService push = new PushService(pushObj, FCM_SERVER_KEY, FCM_SEND_URL);
			Thread t = new Thread(push);
			t.start();
		}
	}

}
