package com.i4unetworks.weys.user;

import java.net.URLEncoder;
import java.util.ArrayList;
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
public class UserService {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private UserDao userDao;

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
	
	public List<UserListVO> getUserList(Paging paging) {
		
		if (paging.getIsSearch() == 1)
			paging.setSearchPage(0);
		else
			paging.setSearchPage((paging.getPageNo() - 1) * 10);
		
		if(paging.getReservation() == null){
			paging.setStartDt(Utils.getDiffDate(-1));
			paging.setEndDt(Utils.getTodayDate());
		} else {
			StringTokenizer st = new StringTokenizer(paging.getReservation(), "-");
			paging.setStartDt(st.nextToken().trim());
			paging.setEndDt(st.nextToken().trim());
		}
		
		if(paging.getGrade() != null){
			String grade = paging.getGrade();
			if(grade.equals("T") || grade.equals(""))
				paging.setGrade(null);
			else{
				StringTokenizer st = new StringTokenizer(grade, ",");
				List<String> tokenList = new ArrayList<String>();
				while(st.hasMoreTokens()){
					tokenList.add(st.nextToken());
				}
				paging.setListData(tokenList);
			}
		}

		if(paging.getSearchText() == null || paging.getSearchText().equals("")){
			paging.setSearchType(null);
		}
		
		if(paging.getDateType() == null)
			paging.setDateType("J");
		
		paging.setEncKey(ENC_KEY);
		return userDao.getUserList(paging);
	}
	
	public int getUserListCnt(Paging paging) {
		return userDao.getUserListCnt(paging);
	}
	
	public List<UserListVO> selectUserExcel(Paging paging) {

		if(paging.getReservation() == null){
			paging.setStartDt(Utils.getDiffDate(-14));
			paging.setEndDt(Utils.getTodayDate());
		} else {
			StringTokenizer st = new StringTokenizer(paging.getReservation(), "-");
			paging.setStartDt(st.nextToken().trim());
			paging.setEndDt(st.nextToken().trim());
		}
		
		if(paging.getGrade() != null){
			String grade = paging.getGrade();
			if(grade.equals("T") || grade.equals(""))
				paging.setGrade(null);
			else{
				StringTokenizer st = new StringTokenizer(grade, ",");
				List<String> tokenList = new ArrayList<String>();
				while(st.hasMoreTokens()){
					tokenList.add(st.nextToken());
				}
				paging.setListData(tokenList);
			}
		}
		
		if(paging.getDateType() == null)
			paging.setDateType("J");
		paging.setEncKey(ENC_KEY);
		return userDao.selectUserExcel(paging);
	}

	public UserListVO selectUserDetail(String usrId) {
		Map<String, Object> reqMap = new HashMap<>();
		reqMap.put("usrId", usrId);
		reqMap.put("encKey", ENC_KEY);
		return userDao.selectUserDetail(reqMap);
	}

	public List<MemberActiveVO> getMembershipList(Paging paging) {

		if (paging.getIsSearch() == 1)
			paging.setSearchPage(0);
		else
			paging.setSearchPage((paging.getPageNo() - 1) * 10);
		
		return userDao.getMembershipList(paging);
	}

	public int getMembershipListCnt(String usrId) {
		return userDao.getMembershipListCnt(usrId);
	}

	public List<UserPointVO> getChangePointList(Paging paging) {

		if (paging.getIsSearch() == 1)
			paging.setSearchPage(0);
		else
			paging.setSearchPage((paging.getPageNo() - 1) * 10);
		
		return userDao.getChangePointList(paging);
	}

	public int getChangePointListCnt(String usrId) {
		return userDao.getChangePointListCnt(usrId);
	}

	public List<UserPointVO> getUsePointList(Paging paging) {
		if (paging.getIsSearch() == 1)
			paging.setSearchPage(0);
		else
			paging.setSearchPage((paging.getPageNo() - 1) * 10);
		
		return userDao.getUsePointList(paging);
	}

	public int getUsePointListCnt(String usrId) {
		return userDao.getUsePointListCnt(usrId);
	}

	public int updateUsrGrade(Map<String, Object> reqMap) {
		return userDao.updateUsrGrade(reqMap);
	}

	public List<Map<String, Object>> selectDetailList(Map<String, Object> reqMap) {
		/**
		 * tab1 = 거래내역
		 * tab2 = 한도내역
		 * tab3 = 쿠폰내역
		 * tab4 = 발송내역
		 * tab5 = 접속내역
		 * tab6 = 상담내역
		 * tab7 = SMS내역
		 */
		int usrId = MapUtils.getIntValue(reqMap, "usrId");
		String tab = MapUtils.getString(reqMap, "tab");
		List<Map<String, Object>> resultList = new ArrayList<>();
		
		if(tab.equals("tab1")){
			resultList = selectRsvList(usrId);
		} else if(tab.equals("tab2")){
			resultList = selectCostList(usrId);
		}  else if(tab.equals("tab3")){
			resultList = selectCouponList(usrId);
		}  else if(tab.equals("tab4")){
			resultList = selectSendList(usrId);
		}  else if(tab.equals("tab5")){
			resultList = selectLoginList(usrId);
		}   else if(tab.equals("tab6")){
			resultList = selectMemoList(usrId);
		}   else if(tab.equals("tab7")){
			resultList = selectSmsList(usrId);
		} 
		
		return resultList;
	}

	private List<Map<String, Object>> selectSmsList(int usrId) {
		return userDao.selectSmsList(usrId);
	}

	private List<Map<String, Object>> selectMemoList(int usrId) {
		return userDao.selectMemoList(usrId);
	}

	private List<Map<String, Object>> selectRsvList(int usrId) {
		return userDao.selectRsvList(usrId);
	}
	private List<Map<String, Object>> selectCostList(int usrId) {
		return userDao.selectCostList(usrId);
	}

	private List<Map<String, Object>> selectCouponList(int usrId) {
		return userDao.selectCouponList(usrId);
	}

	private List<Map<String, Object>> selectSendList(int usrId) {
		return userDao.selectSendList(usrId);
	}

	private List<Map<String, Object>> selectLoginList(int usrId) {
		return userDao.selectLoginList(usrId);
	}

	public List<UserListVO> getUserDeleteList(Paging paging) {

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
		
		if(paging.getGrade() != null){
			String grade = paging.getGrade();
			if(grade.equals("T") || grade.equals(""))
				paging.setGrade(null);
			else{
				StringTokenizer st = new StringTokenizer(grade, ",");
				List<String> tokenList = new ArrayList<String>();
				while(st.hasMoreTokens()){
					tokenList.add(st.nextToken());
				}
				paging.setListData(tokenList);
			}
		}

		if(paging.getSearchText() == null || paging.getSearchText().equals("")){
			paging.setSearchType(null);
		}
		
		if(paging.getDateType() == null)
			paging.setDateType("J");
		
		paging.setEncKey(ENC_KEY);
		return userDao.getUserDeleteList(paging);
	}

	public int getUserDeleteListCnt(Paging paging) {
		return userDao.getUserDeleteListCnt(paging);
	}

	public int insertMemo(Map<String, Object> reqMap) {
		return userDao.insertMemo(reqMap);
	}

	public int insertSMS(Map<String, Object> reqMap) throws Exception {
	
		reqMap.put("encKey", ENC_KEY);
		Map<String, Object> infoMap = userDao.selectUserTel(reqMap);
		/**
		 * 고객한테 SMS 발송
		 */
		String url = SERVER_TYPE.equals("USER") ? Constant.INFO_BANK_URL_REAL : Constant.INFO_BANK_URL_TEST;

		String nation = MapUtils.getString(infoMap, "NATION", "82");
		String tel = MapUtils.getString(infoMap, "USR_TEL");
		String msg = MapUtils.getString(reqMap, "memo");
		
		if(nation.equals("82")){
			if(tel.startsWith("010")){
				tel = tel.substring(1);
			}
		}
		
		url = url + "?id=" + IB_SERVICE_ID
				+ "&pwd=" + IB_SERVICE_PWD
				+ "&message=" + URLEncoder.encode(msg, "UTF-8")
				+ "&from=" + IB_FROM_TEL
				+ "&to_country=" + nation
				+ "&to=" + tel
				+ "&report_req=1";
		
		String res = SMSUtil.sendSms(url);
		int resCnt = 0;

		reqMap.put("nation", nation);
		reqMap.put("tel", tel);
		reqMap.put("txt", msg);
		
		if(res.contains("R000")){
			resCnt = 1;
			reqMap.put("res", "R000");
			userDao.insertUsrSms(reqMap);
		} else {
			reqMap.put("res", res);
		}
		userDao.insertSmsSend(reqMap);
		
		return resCnt;
	}

}
