package com.i4unetworks.weys.paygate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.MapUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.i4unetworks.weys.common.Barcode;
import com.i4unetworks.weys.common.Constant;
import com.i4unetworks.weys.common.KakaoClient;
import com.i4unetworks.weys.common.PushService;
import com.i4unetworks.weys.common.Utils;
import com.i4unetworks.weys.mail.EmailVO;
import com.i4unetworks.weys.mail.Mailer;
import com.i4unetworks.weys.rsv.RsvDao;

@Service
public class PaygateService {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private PaygateDao paygateDao;
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

	
	public int updateRsvIncome(HttpServletRequest req) throws Exception {

		String tid = req.getParameter("tid");
		String refId = req.getParameter("refId");
		String trnsctnSt = req.getParameter("trnsctnSt");
		
		String log = "tid=" + tid + "&refId=" + refId + "&trnsctnSt=" + trnsctnSt;
		Map<String, Object> reqMap = new HashMap<>();
		reqMap.put("tid", tid);
		reqMap.put("refId", refId);
		reqMap.put("log", log);
		
		paygateDao.insertPaygateLog(reqMap);
		
		int res = 0;
		if(trnsctnSt.equals("SFRT_TRNSFR_VACNT_FINISHED")){
			int resCnt = paygateDao.selectRsvCheck(reqMap);
			
			if(resCnt > 0){
				int rsvId = paygateDao.selectRsvId(reqMap);;
				reqMap.put("rsvId", rsvId);
				
				/**
				 * 1. 멤버십 결제인지, 예약 결제인지 확인
				 */
//				int managerKey = rsvDao.selectManagerKey(rsvId); 
//				reqMap.put("adminKey", managerKey);
				reqMap.put("asIs", Constant.RSV_START);
				reqMap.put("toBe", Constant.RSV_READY);
				
				res = rsvDao.updateRsvIncome(reqMap);

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
							
							if(os.equals("A")){
								JSONObject dataJson = new JSONObject();
								dataJson.put("type", "reserve");
								dataJson.put("st", "income");
								dataJson.put("val", rsvId);
								dataJson.put("message", pushMsg);
								
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
								dataJson.put("title", pushMsg);
								dataJson.put("contents","reserve");
								dataJson.put("st", "income");
								dataJson.put("val", rsvId);
								dataJson.put("img", "");
								
								JSONObject notiJson = new JSONObject();
								notiJson.put("title", "입금이 확인되었습니다.");
								notiJson.put("body", pushMsg);
								notiJson.put("icon", "");
								
								pushObj.put("content_available", true);
								pushObj.put("data", dataJson);
								pushObj.put("priority", "high");
								pushObj.put("notification", notiJson);
								
								PushService push = new PushService(pushObj, FCM_SERVER_KEY, FCM_SEND_URL);
								Thread t = new Thread(push);
								t.start();
							}
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
				
			}
		}
		
		return res;
	}
	
}
