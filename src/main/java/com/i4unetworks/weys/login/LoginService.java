package com.i4unetworks.weys.login;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i4unetworks.weys.common.AriaUtils;
import com.i4unetworks.weys.common.UserVO;
import com.i4unetworks.weys.common.Utils;

@Service
public class LoginService {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private LoginDao loginDao;
	
	public HashMap<String, Object> insertlogin(Map<String, Object> reqMap, HttpServletRequest req, HttpServletResponse res) {

		String id = MapUtils.getString(reqMap, "id", "");
		String pw = MapUtils.getString(reqMap, "pwd", "");
		String chk = MapUtils.getString(reqMap, "chk", "");
		
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		
		if("".equals(id) || "".equals(pw)){
			returnMap.put("resultCode", "fail");
			returnMap.put("resultMsg", "잘못된 데이터 입니다.");
			return returnMap;
		}

		UserVO userVo = loginDao.login(id);
		
		if(userVo != null){
			String existId = userVo.getAdminId();
			String existPw = userVo.getAdminPw();
			try{
				String inputpPw = AriaUtils.encryptPassword(pw, existId);	

				if(existPw.equals(inputpPw)){
					if(userVo.getAdminTp().equals("A")){
						returnMap.put("resultCode", "success_admin");
					} else if(userVo.getAdminTp().equals("M")){
						returnMap.put("resultCode", "success_manager");
					} else {
						returnMap.put("resultCode", "success");
					}
					returnMap.put("resCode", 200);
					returnMap.put("resultMsg", "로그인 성공");
					req.getSession().setAttribute("login", userVo);

					returnMap.put("resData", userVo);
					
					if (chk.equals("true")) {
						Cookie cookie = new Cookie("WEYSADMIN", id);
						cookie.setMaxAge(60 * 60 * 24 * 30);
						res.addCookie(cookie);
					} else {
						// 체크 없을 경우 쿠키 확인해서 삭제
						Cookie[] cookies = req.getCookies();
						if (cookies != null) {
							for (int i = 0; i < cookies.length; i++) {
								if (cookies[i].getName().equals("WEYSADMIN")) {
									cookies[i].setMaxAge(0);
									res.addCookie(cookies[i]);
								}
							}
						}
					}
					
					/**
					 * 접속로그 등록
					 */
					String ip = req.getHeader("X-FORWARDED-FOR");
			        if (ip == null)
			            ip = req.getRemoteAddr();
			        reqMap.put("clientIp", ip);
			        loginDao.insertAdminLog(reqMap);
			        
					
				} else {
					returnMap.put("resultCode", "fail");
					returnMap.put("resultMsg", "아이디 또는 비밀번호가 일치하지 않습니다.");
				}
			} catch (Exception e) {
				logger.info("error ::: " + e.getMessage());
				returnMap.put("resultCode", "fail");
				returnMap.put("resultMsg", "다시 시도해 주십시오.");
			}
			
		} else {
			returnMap.put("resultCode", "fail");
			returnMap.put("resultMsg", "아이디 또는 비밀번호가 일치하지 않습니다.");
		}
		
		return returnMap;
	}

	public Map<String, Object> getDisplayInfo() {
		
		List<Map<String, Object>> list = loginDao.selectDisplayInfo();
		
		for(Map<String, Object> temp : list){
			String unit = MapUtils.getString(temp, "UNIT");
			double basicRate = MapUtils.getDoubleValue(temp, "BASIC_RATE");
			double cashBuy = MapUtils.getDoubleValue(temp, "CASH_BUY");
			double cashSell = MapUtils.getDoubleValue(temp, "CASH_SELL");
			
			if(unit.equals("CNY")){
				temp.put("BASIC_RATE", String.format("%.2f", (cashBuy + basicRate)/2.0));
				temp.put("CASH_BUY", String.format("%.2f", basicRate + ((cashBuy - basicRate)*0.7)));
				temp.put("CASH_SELL", String.format("%.2f", basicRate - ((basicRate - cashSell)*0.7)));
			} else {
				temp.put("BASIC_RATE", String.format("%.2f", basicRate));
				temp.put("CASH_BUY", String.format("%.2f", (cashBuy + basicRate)/2.0));
				temp.put("CASH_SELL", String.format("%.2f", (cashSell + basicRate)/2.0));
			}
		}
		Map<String, Object> resultmap = new HashMap<>();
		resultmap.put("resultList", list);
		
		return resultmap;
	}

	public Map<String, Object> updateAdmPwd(HttpServletRequest req, Map<String, Object> reqMap, UserVO userVO) {

		String oldPwd = MapUtils.getString(reqMap, "oldPwd");
		String newPwd = MapUtils.getString(reqMap, "pwd");
		
		Map<String, Object> result = new HashMap<>();
		
		try{
			String inputpPw = AriaUtils.encryptPassword(oldPwd, userVO.getAdminId());
			String pwd = userVO.getAdminPw();
			
			if(inputpPw.equals(pwd)){
				String makeNew = AriaUtils.encryptPassword(newPwd, userVO.getAdminId());
				userVO.setAdminPw(makeNew);
				userVO.setPwdDue(Utils.getDiffDate(30));
				int res = loginDao.updateAdmPwd(userVO);
				
				if(res > 0){
					req.getSession().setAttribute("login", userVO);
					res = loginDao.insertPwdLog(userVO);
					result.put("result", "true");
				} else {
					result.put("result", "false");
					result.put("msg", "서버에러. 다시시도해주세요.");
				}

			} else {
				result.put("result", "false");
				result.put("msg", "비밀번호가 일치하지 않습니다.");
			}
			
		} catch (Exception e) {
			result.put("result", "false");
			result.put("msg", "서버에러. 다시시도해주세요.");
		}
		
		
		return result;
	}

	public List<Map<String, Object>> selectExchangeRate() {
		
		List<Map<String, Object>> list = loginDao.selectExchangeRate();
		list.add(loginDao.selectExchangeRateCny());
		return list;
	}

	public void insertMoneyLog() {
		
		List<String> unitList = loginDao.selectUnitList();
		
		for(String unit : unitList){

			List<Map<String, Object>> moneyList = loginDao.selectMoneyList(unit);

			List<Map<String, Object>> buyList = loginDao.selectBuyList(unit);
			
			int sumAmnt = 0;
			int sumKor = 0;
			int mmId = 0;
			int index = 0;
			int i = 0;
			for(Map<String, Object> mMng : moneyList){
				
				mmId = MapUtils.getIntValue(mMng, "MM_ID");
				int buyAmnt = MapUtils.getIntValue(mMng, "BUY_AMNT");
				
				for(i = index ; i<buyList.size() ; i++){
					
					Map<String, Object> buy = buyList.get(i);
					int rsvAmnt = MapUtils.getIntValue(buy, "RSV_AMNT");
					double basicRate = MapUtils.getDoubleValue(buy, "BASIC_RATE");
					
					sumAmnt = sumAmnt + rsvAmnt;
					
					if(buyAmnt < sumAmnt){
						int next = sumAmnt - buyAmnt;
						int rest = rsvAmnt - next;
						if(unit.equals("JPY")){
							sumKor = (int) (sumKor + (rest * basicRate / 100));
						} else {
							sumKor = (int) (sumKor + (rest * basicRate));
						}
						
						/**
						 * 
						 */
						Map<String, Object> reqMap = new HashMap<>();
						reqMap.put("mmId", mmId);
						reqMap.put("sumAmnt", buyAmnt);
						reqMap.put("sumKor", sumKor);
						loginDao.updateMoneyMng(reqMap);
						
						if(unit.equals("JPY")){
							sumKor = (int) (next * basicRate / 100);
						} else {
							sumKor = (int) (next * basicRate);
						}
						
						sumAmnt = next;
						index = i + 1;
						break;
						
					} else {
						if(unit.equals("JPY")){
							sumKor = (int) (sumKor + (rsvAmnt * basicRate / 100));
						} else {
							sumKor = (int) (sumKor + (rsvAmnt * basicRate));
						}
					}
					
				}
				
				if(i == buyList.size()){

					Map<String, Object> reqMap = new HashMap<>();
					reqMap.put("mmId", mmId);
					reqMap.put("sumAmnt", sumAmnt);
					reqMap.put("sumKor", sumKor);
					loginDao.updateMoneyMng(reqMap);
					break;
				}

			}
		}

		
	}
}
