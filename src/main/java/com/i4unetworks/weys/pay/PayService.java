package com.i4unetworks.weys.pay;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.i4unetworks.weys.common.Constant;
import com.i4unetworks.weys.common.Paging;
import com.i4unetworks.weys.common.SMSUtil;
import com.i4unetworks.weys.common.Utils;

@Service
public class PayService {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private PayDao payDao;
	@Value("#{props['ENC.KEY']}")
	private String ENC_KEY;
	@Value("${SERVER.TYPE}")
	private String SERVER_TYPE;
	@Value("#{props['IB.SERVICE.ID']}")
	private String IB_SERVICE_ID;
	@Value("#{props['IB.SERVICE.PWD']}")
	private String IB_SERVICE_PWD;
	@Value("#{props['IB.FROM.TEL']}")
	private String IB_FROM_TEL;
	
	public List<PayListVO> selectPayList(Paging paging) {

		if (paging.getIsSearch() == 1)
			paging.setSearchPage(0);
		else
			paging.setSearchPage((paging.getPageNo() - 1) * 10);

		if(paging.getReservation() == null){
			paging.setStartDt(Utils.getDiffDate(-7));
			paging.setEndDt(Utils.getTodayDate());
		} else {
			StringTokenizer st = new StringTokenizer(paging.getReservation(), "-");
			paging.setStartDt(st.nextToken().trim());
			paging.setEndDt(st.nextToken().trim());
		}

		if(paging.getSearchText() == null || paging.getSearchText().equals("")){
			paging.setSearchType(null);
		}
		
		paging.setEncKey(ENC_KEY);
		return payDao.selectPayList(paging);
	}
	
	public int selectPayListCnt(Paging paging) {
		return payDao.selectPayListCnt(paging);
	}
	
	public Map<String, Object> cancelCheck(Map<String, Object> reqMap) {
		
		int id = MapUtils.getIntValue(reqMap, "id");
		String flag = payDao.cancelCheck(id);
		Map<String, Object> result = new HashMap<>();
		result.put("flag", flag);
		return result;
	}

	public Map<String, Object> insertMemberCancel(Map<String, Object> reqMap) throws Exception {
		
		reqMap.put("encKey", ENC_KEY);
		int res = payDao.updateMemberCancel(reqMap);
		Map<String, Object> resMap = new HashMap<>();
		if(res > 0){
			res = payDao.updateMemberActiveCancel(reqMap);

			Map<String, Object> smsMap = payDao.selectSmsInfo(reqMap);
			String payAmnt = MapUtils.getString(smsMap, "PAY_AMNT");
			String nation = MapUtils.getString(smsMap, "NATION");
			String tel = MapUtils.getString(smsMap, "USR_TEL");
			
			if(nation.equals("82")){
				if(tel.startsWith("010")){
					tel = tel.substring(1);
				}
			}
			
			/**
			 * 취소 관련 SMS 발송
			 */
			String url = SERVER_TYPE.equals("USER") ? Constant.INFO_BANK_URL_REAL : Constant.INFO_BANK_URL_TEST;
			String msg = "[웨이즈 예약] \n 환불이 정상적으로 처리 완료되었습니다.\n 환불금액 : " + Utils.setStringFormatInteger(payAmnt);
			
			url = url + "?id=" + IB_SERVICE_ID
					+ "&pwd=" + IB_SERVICE_PWD
					+ "&message=" + URLEncoder.encode(msg, "UTF-8")
					+ "&from=" + IB_FROM_TEL
					+ "&to_country=" + nation
					+ "&to=" + tel
					+ "&report_req=1";
			
			SMSUtil.sendSms(url);
			
			resMap.put("result", "success");
		} else {
			resMap.put("result", "fail");
		}
		return resMap;
	}

	public Map<String, Object> updateReturnComplete(Map<String, Object> reqMap) {
		int res = payDao.updateReturnComplete(reqMap);
		Map<String, Object> resMap = new HashMap<>();
		if(res > 0){
			resMap.put("result", "success");
		} else {
			resMap.put("result", "fail");
		}
		return resMap;
	}
}
