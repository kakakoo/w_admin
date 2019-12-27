package com.i4unetworks.weys.coupon;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.i4unetworks.weys.common.Barcode;
import com.i4unetworks.weys.common.ExcelRead;
import com.i4unetworks.weys.common.Paging;
import com.i4unetworks.weys.common.PushService;
import com.i4unetworks.weys.common.Utils;

@Service
public class CouponService {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private CouponDao couponDao;

	@Value("${UPLOAD.PATH}")
	private String UPLOAD_ROOT_PATH;
	
	// FCML
	@Value("#{props['FCM.SERVER.KEY']}")
	private String FCM_SERVER_KEY; // FCM 서버 키
	@Value("#{props['FCM.SEND.URL']}")
	private String FCM_SEND_URL; // FCM 발송 URL
	
	public List<CouponVO> selectCouponList(Paging paging) {
		
		if (paging.getIsSearch() == 1)
			paging.setSearchPage(0);
		else
			paging.setSearchPage((paging.getPageNo() - 1) * 10);
		
		return couponDao.selectCouponList(paging);
	}
	
	public int selectCouponListCnt() {
		return couponDao.selectCouponListCnt();
	}

	public CouponVO selectCouponInfo(String couponId) {
		return couponDao.selectCouponInfo(couponId);
	}

	public int selectTargetAllCnt() {
		return couponDao.selectTargetAllCnt();
	}

	public int selectTargetMemberCnt() {
		return couponDao.selectTargetMemberCnt();
	}

	public int insertCoupon(CouponVO couponVO) throws Exception{

		StringTokenizer st = new StringTokenizer(couponVO.getReservation(), "-");
		couponVO.setStartDt(st.nextToken());
		couponVO.setEndDt(st.nextToken());
		
		String type = couponVO.getCouponTarget();
		int res = 0;
		res = couponDao.insertCouponInfo(couponVO);
		
		String sendTp = couponVO.getSendTp();
		
		/**
		 * 즉시 적용 쿠폰 대상자에 대해서는 바로 적용
		 * 한도지급일 경우 바로 지급 
		 */
		if(sendTp.equals("R")){
			String dueDt = "";
			if(couponVO.getPeriodTp().equals("P")){
				dueDt = Utils.getDiffMonth(couponVO.getMemberPeriod());
			} else {
				dueDt = couponVO.getEndDt();
			}
			couponVO.setDueDt(dueDt);
			
			if(type.equals("S")){
				String excelPath = couponVO.getExcelFilePath();
				List<String> userList = ExcelRead.userIdList(UPLOAD_ROOT_PATH + excelPath);
				couponVO.setUserList(userList);
				
				String couponTp = couponVO.getCouponTp();
				
				if(couponTp.equals("D") || couponTp.equals("M")){
					/**
					 * COUPON_HIST 발급
					 */
					res = couponDao.insertCouponByExcel(couponVO);
					if(couponTp.equals("M") && res > 0){
						/**
						 * MEMBER_ACTIVE 발급
						 * MEMBER.COST 업데이트
						 */
						couponDao.insertMACouponByExcel(couponVO);
						couponDao.updateMemCostByExcel(couponVO);
					}
				} else if(couponTp.equals("B")){
					/**
					 * 음료 쿠폰 발급
					 */
					for(String usrId : userList){
						couponVO.setUsrId(usrId);
						
						boolean couponQr = true;
						String qr = "";
						while(couponQr){
							qr = Barcode.CreateQrCode(2);
							int checkCnt = couponDao.selectCouponQr(qr);
							if(checkCnt == 0){
								couponQr = false;
							}
						}
						// QR CODE 이미지 생성
						String qrUrl = Barcode.CreateQRCodePng(qr, UPLOAD_ROOT_PATH, "qrcp");
						if(qrUrl == null){
							throw new Exception("QR CODE 생성 실패");
						}
						couponVO.setQrCode(qr);
						couponVO.setQrImg(qrUrl);
						couponDao.insertCouponBarbageExcel(couponVO);
					}
				}
				
			} else if(type.equals("T")){
				String couponTp = couponVO.getCouponTp();
				
				if(couponTp.equals("D") || couponTp.equals("M")){
					/**
					 * COUPON_HIST 발급
					 */
					res = couponDao.insertCouponByAll(couponVO);
					if(couponTp.equals("M") && res > 0){
						/**
						 * MEMBER_ACTIVE 발급
						 * MEMBER.COST 업데이트
						 */
						couponDao.insertMACouponByAll(couponVO);
						couponDao.updateMemCostByAll(couponVO);
					}
				} else if(couponTp.equals("B")){
					/**
					 * 음료 쿠폰 발급
					 */
					List<String> userList = couponDao.selectAllUser();
					for(String usrId : userList){
						couponVO.setUsrId(usrId);
						
						boolean couponQr = true;
						String qr = "";
						while(couponQr){
							qr = Barcode.CreateQrCode(2);
							int checkCnt = couponDao.selectCouponQr(qr);
							if(checkCnt == 0){
								couponQr = false;
							}
						}
						// QR CODE 이미지 생성
						String qrUrl = Barcode.CreateQRCodePng(qr, UPLOAD_ROOT_PATH, "qrcp");
						if(qrUrl == null){
							throw new Exception("QR CODE 생성 실패");
						}
						couponVO.setQrCode(qr);
						couponVO.setQrImg(qrUrl);
						couponDao.insertCouponBarbageExcel(couponVO);
					}
				}
			}
		}
		
		return res;
	}

	public int updateCouponDone(String couponId) throws Exception {
		
		int res = couponDao.updateCouponStatusDone(couponId);
		if(res > 0){
			String title = couponDao.selectCouponPush(couponId);
			
			if(title != null){
				/**
				 * 푸시 발송 상태라면 부시 발송 대상자에게 푸시 발송 
				 */

				Map<String, Object> pushMap = new HashMap<>();
				pushMap.put("couponId", couponId);
				pushMap.put("os", "I");
				List<String> iosList = couponDao.selectPushUsrList(pushMap);
				pushMap.put("os", "A");
				List<String> aosList = couponDao.selectPushUsrList(pushMap);
				
				/**
				 * 안드 사용자 푸시 전송
				 */
				JSONObject dataJson = new JSONObject();
				dataJson.put("type", "coupon");
				dataJson.put("message", title);
				
				JSONObject json = new JSONObject();
				json.put("registration_ids", aosList);
				json.put("data", dataJson);

				PushService push = new PushService(json, FCM_SERVER_KEY, FCM_SEND_URL);
				Thread t = new Thread(push);
				t.start();
				
				/**
				 * 아이폰 사용자 푸시 전송
				 */
				JSONObject pushObj = new JSONObject();
				pushObj.put("registration_ids", iosList);
				dataJson = new JSONObject();
				dataJson.put("title", title);
				dataJson.put("contents","coupon");
				dataJson.put("img", "");
				
				JSONObject notiJson = new JSONObject();
				notiJson.put("title", "");
				notiJson.put("body", title);
				notiJson.put("icon", "");

				pushObj.put("content_available", true);
				pushObj.put("data", dataJson);
				pushObj.put("notification", notiJson);
				pushObj.put("priority", "high");

				push = new PushService(pushObj, FCM_SERVER_KEY, FCM_SEND_URL);
				t = new Thread(push);
				t.start();
			}
			
		}
		return res;
	}

	public int deleteCoupon(String couponId) {
		int res = couponDao.deleteUserCoupon(couponId);
		if(res > 0){
			res = couponDao.deleteCouponInfo(couponId);
		}
		return res;
	}

	public int updateCoupon(CouponVO couponVO) throws Exception {
//		StringTokenizer st = new StringTokenizer(couponVO.getReservation(), "-");
//		couponVO.setStartDt(st.nextToken());
//		couponVO.setEndDt(st.nextToken());
//		
//		int res = 0;
//		res = couponDao.updateCouponInfo(couponVO);
//		res = couponDao.deleteUserCoupon(couponVO.getCouponId());
//		String type = couponVO.getCouponTarget();
//		if(type.equals("S")){
//			String excelPath = couponVO.getExcelFilePath();
//			List<String> userList = ExcelRead.userIdList(UPLOAD_ROOT_PATH + excelPath);
//			couponVO.setUserList(userList);
//			
//			res = couponDao.insertCouponByExcel(couponVO);
//		} else if(type.equals("T")){
//			res = couponDao.insertCouponByTotal(couponVO);
//		} else {
//			res = couponDao.insertCouponByMember(couponVO);
//		}
//		
//		return res;
		return 0;
	}

}
