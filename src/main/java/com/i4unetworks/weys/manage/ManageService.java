package com.i4unetworks.weys.manage;

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

import com.i4unetworks.weys.common.IdentifyUtils;
import com.i4unetworks.weys.common.Paging;
import com.i4unetworks.weys.common.Utils;
import com.i4unetworks.weys.unit.UnitVO;

@Service
public class ManageService {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private ManageDao manageDao;
	
	@Value("#{props['ENC.KEY']}")
	private String ENC_KEY;
	// 나이스 평가모듈 
	@Value("#{props['NAME.CHECK.CODE']}")
	private String NAME_CHECK_CODE;
	@Value("#{props['NAME.CHECK.PWD']}")
	private String NAME_CHECK_PWD;

	
	public List<MemberListVO> getManageInfoMembership(Paging paging) {

		if(paging.getReservation() == null){
			paging.setStartDt(Utils.getDiffDate(-14));
			paging.setEndDt(Utils.getTodayDate());
		} else {
			StringTokenizer st = new StringTokenizer(paging.getReservation(), "-");
			paging.setStartDt(st.nextToken().trim());
			paging.setEndDt(st.nextToken().trim());
		}
		
		return manageDao.getManageInfoMembership(paging);
	}

	public List<MemberListVO> getManageInfoPoint(Paging paging) {
		return manageDao.getManageInfoPoint(paging);
	}

	public List<ActiveListVO> selectActiveList(Paging paging) {

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
		paging.setEncKey(ENC_KEY);
		return manageDao.selectActiveList(paging);
	}

	public int selectActiveListCnt(Paging paging) {
		return manageDao.selectActiveListCnt(paging);
	}

	public ClientInfoVO selectClientInfo(String barcode) throws Exception {
		
		if(barcode.length() == 8){
			barcode = barcode.substring(0, 4) + " " + barcode.substring(4, 8);
		}
		
		String type = "member";
//		if(Integer.parseInt(first) < 5000){
//			type = "point";
//		} else {
//			type = "member";
//		}
		Map<String, Object> reqMap = new HashMap<String, Object>();
		reqMap.put("barcode", barcode);
		reqMap.put("type", type);
		reqMap.put("encKey", ENC_KEY);
		ClientInfoVO info = manageDao.selectClientInfo(reqMap);
		info.setType(type);
		return info;
	}

	public List<UnitVO> selectStoreUnit(String storeId) {
		return manageDao.selectStoreUnit(storeId);
	}

	public Map<String, Object> selectUnitBasicRate(String unitCd) {
		
		List<String> basicRateList = manageDao.selectUnitBasicRate(unitCd); 
		String result = "";
		double nowRate = 0;
		
		if(basicRateList.size() == 0){
			result = "";
		} else {
			result = basicRateList.get(0);
			nowRate = Double.parseDouble(basicRateList.get(0));
			if (basicRateList.size() > 1){
				double beforeRate = Double.parseDouble(basicRateList.get(1));
				
				if(nowRate > beforeRate){
					double diff = nowRate - beforeRate;
					double diffPercent = diff / beforeRate * 100;
					result = "매매기준율 : " + Utils.setStringFormatInteger(result) + " ▲ " + String.format("%.2f", diff) + " (+" + String.format("%.2f", diffPercent) + "%)";
				} else if(nowRate < beforeRate){
					double diff = beforeRate - nowRate;
					double diffPercent = diff / beforeRate * 100;
					result = "매매기준율 : " + Utils.setStringFormatInteger(result) + " ▼ " + String.format("%.2f", diff) + " (-" + String.format("%.2f", diffPercent) + "%)";
				}
			}
		}
		
		
		Map<String, Object> reqMap = new HashMap<String, Object>();
		reqMap.put("basicRate", nowRate);
		reqMap.put("basicRateText", result);
		
		return reqMap;
	}
	
	public Map<String, Object> selectUnitBasicRateSell(String unitCd, String isApp) {
		
		Map<String, Object> reqMap = new HashMap<>();
		reqMap.put("unit", unitCd);
		Map<String, Object> todayBasicInfo = manageDao.selectSellUnitInfo(reqMap); 
		reqMap.put("dt", Utils.getDiffDate(0));
		Map<String, Object> yesBasicInfo = manageDao.selectSellUnitInfo(reqMap); 
		
		double rate = 0.5;
		
		if(isApp.equals("true") && (unitCd.equals("USD") || unitCd.equals("JPY"))){
			rate = 0.8;
		} else if(unitCd.equals("CNY")){
			rate = 0.3;
		}

		double tBasic = MapUtils.getDoubleValue(todayBasicInfo, "BASIC_RATE");
		double tSell = MapUtils.getDoubleValue(todayBasicInfo, "CASH_SELL");
		
		tBasic = tBasic - ((tBasic - tSell) * (1 - rate));

		double yBasic = MapUtils.getDoubleValue(yesBasicInfo, "BASIC_RATE");
		double ySell = MapUtils.getDoubleValue(yesBasicInfo, "CASH_SELL");
		
		yBasic = yBasic - ((yBasic - ySell) * (1 - rate));
		
		String result = "";
		
		if(tBasic > yBasic){
			double diff = tBasic - yBasic;
			double diffPercent = diff / yBasic * 100;
			result = "웨이즈 기준율 : " + Utils.setStringFormatInteger(String.format("%.2f", tBasic)) + " ▲ " + String.format("%.2f", diff) + " (+" + String.format("%.2f", diffPercent) + "%)";
		} else if(tBasic < yBasic){
			double diff = yBasic - tBasic;
			double diffPercent = diff / yBasic * 100;
			result = "웨이즈 기준율 : " + Utils.setStringFormatInteger(String.format("%.2f", tBasic)) + " ▼ " + String.format("%.2f", diff) + " (-" + String.format("%.2f", diffPercent) + "%)";
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("basicRate", String.format("%.2f", MapUtils.getDoubleValue(todayBasicInfo, "BASIC_RATE")));
		resultMap.put("basicRateWeys", String.format("%.2f", tBasic));
		resultMap.put("bankSell", String.format("%.2f", tSell));
		resultMap.put("basicRateText", result);
		resultMap.put("result", "success");
		
		return resultMap;
	}
	
	public Map<String, Object> selectUnitBasicRateBuy(String unitCd, String isApp, String storeId) {
		
		Map<String, Object> reqMap = new HashMap<>();
		reqMap.put("unit", unitCd);
		Map<String, Object> todayBasicInfo = manageDao.selectBuyUnitInfo(reqMap); 
		reqMap.put("dt", Utils.getDiffDate(0));
		Map<String, Object> yesBasicInfo = manageDao.selectBuyUnitInfo(reqMap); 
		reqMap.put("storeId", storeId);
		
		int totAmnt = manageDao.selectStoreMoney(reqMap);
		
		double rate = 0.5;
		if(unitCd.equals("CNY")){
			rate = 0.3;
		}
		double tBasic = MapUtils.getDoubleValue(todayBasicInfo, "BASIC_RATE");
		double tBuy = MapUtils.getDoubleValue(todayBasicInfo, "CASH_BUY");
		
		tBasic = tBasic + ((tBuy - tBasic) * (1 - rate));

		double yBasic = MapUtils.getDoubleValue(yesBasicInfo, "BASIC_RATE");
		double yBuy = MapUtils.getDoubleValue(yesBasicInfo, "CASH_BUY");
		
		yBasic = yBasic + ((yBuy - yBasic) * (1 - rate));

		String result = "";
		
		if(tBasic > yBasic){
			double diff = tBasic - yBasic;
			double diffPercent = diff / yBasic * 100;
			result = "매매기준율 : " + Utils.setStringFormatInteger(String.format("%.2f", tBasic)) + " ▲ " + String.format("%.2f", diff) + " (+" + String.format("%.2f", diffPercent) + "%)";
		} else if(tBasic < yBasic){
			double diff = yBasic - tBasic;
			double diffPercent = diff / yBasic * 100;
			result = "매매기준율 : " + Utils.setStringFormatInteger(String.format("%.2f", tBasic)) + " ▼ " + String.format("%.2f", diff) + " (-" + String.format("%.2f", diffPercent) + "%)";
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("basicRateWeys", MapUtils.getDoubleValue(todayBasicInfo, "BASIC_RATE"));
		resultMap.put("basicRateUser", String.format("%.2f", tBasic));
		resultMap.put("bankBuy", String.format("%.2f", tBuy));
		resultMap.put("basicRateText", result);
		resultMap.put("totAmnt", totAmnt);
		resultMap.put("result", "success");
		
		return resultMap;
	}
	
	public int selectMaxValue(String unitCd, double buy_price) {
		
		int maxValue = 2000;
		
		if(!unitCd.equals("USD")){
			double usdRate = manageDao.selectUsdRate();
			
			int val = (int) (maxValue * usdRate);
			double dVal = val / buy_price;
			if(unitCd.equals("JPY")){
				dVal = dVal * 100;
			}
			maxValue = (int) ((dVal / 10) * 10);
		}
		
		return maxValue;
	}

	
	public String selectStoreUnitAmnt(String unitCd, String storeId, String tradeType) {
		
		Map<String, Object> reqMap = new HashMap<String, Object>();
		reqMap.put("storeId", storeId);
		if(tradeType.equals("S")){
			unitCd = "KOR";
		}
		reqMap.put("unitCd", unitCd);
		
		String totalAmnt = manageDao.selectStoreUnitAmnt(reqMap);
			
		return totalAmnt + " " + unitCd;
	}

	public List<Map<String, Object>> selectPayCoinList(String storeId, String unitCd, String tradeType, String writeType) {
		
		String unit = "";
		if(tradeType.equals("B")){
			unit = unitCd;
		} else {
			unit = "KOR";
		}
		Map<String, Object> reqMap = new HashMap<String, Object>();
		reqMap.put("storeId", storeId);
		reqMap.put("unitCd", unit);
		
		return manageDao.selectPayCoinList(reqMap);
	}

	public List<Map<String, Object>> selectGetCoinList(String storeId, String unitCd, String tradeType,
			String writeType) {
		
		String unit = "";
		if(tradeType.equals("S") || writeType.equals("point")){
			unit = unitCd;
		} else {
			unit = "KOR";
		}
		Map<String, Object> reqMap = new HashMap<String, Object>();
		reqMap.put("storeId", storeId);
		reqMap.put("unitCd", unit);
		if(writeType.equals("point"))
			reqMap.put("coinTp", "C");
		
		return manageDao.selectGetCoinList(reqMap);
	}

	public int insertTradeInfo(Map<String, Object> reqMap, String adminId, String barcode) {
		
		String type = MapUtils.getString(reqMap, "writeType");
		int adminKey = manageDao.selectAdminKey(adminId);
		reqMap.put("adminId", adminKey);
		
		if(barcode.equals("0000")){
			type = "nomem";
		} else if (barcode.length() == 8){
			barcode = barcode.substring(0, 4) + " " + barcode.substring(4, 8);
		}

		boolean suc = false;
		int res = 0;
		try{
			if(type.equals("member")){
				Map<String, Object> memberMap = manageDao.selectMemberIdFromBarcode(barcode);
				int memberId = MapUtils.getIntValue(memberMap, "MEMBER_ID");
				int cost = MapUtils.getIntValue(memberMap, "COST");
				reqMap.put("memberId", memberId);
				
				/**
				 * 구매시 원화(KOR)는 + COIN_CNT, 원하는 구매 화폐는 - COIN_CNT
				 * 판매시 원화(KOR)는 - COIN_CNT, 원하는 판매 화폐는 + COIN_CNT 
				 */
				String tradeType = MapUtils.getString(reqMap, "tradeType");
				String storeId = MapUtils.getString(reqMap, "storeId");
				
				if(tradeType.equals("B")){
					suc = checkCoinCnt(storeId, "KOR", reqMap, true, "GET", true);
					if(!suc)
						return -1;
					suc = checkCoinCnt(storeId, MapUtils.getString(reqMap, "storeUnit"), reqMap, false, "PAY", true);
					if(!suc)
						return -1;

					suc = updateStoreCoin(storeId, "KOR", reqMap, true, "GET", true);
					if(suc){
						suc = updateStoreCoin(storeId, MapUtils.getString(reqMap, "storeUnit"), reqMap, false, "PAY", true);
						if(cost < MapUtils.getIntValue(reqMap, "getAmnt"))
							throw new Exception("에러!");
//						manageDao.insertMemberUsrNm(reqMap);
						res = manageDao.insertMemberActive(reqMap);
						
						reqMap.put("val", MapUtils.getIntValue(reqMap, "getAmnt"));
						manageDao.updateMemberPoint(reqMap);
					}
					
				} else if(tradeType.equals("S")) {
					suc = checkCoinCnt(storeId, MapUtils.getString(reqMap, "storeUnit"), reqMap, true, "GET", true);
					if(!suc)
						return -1;
					suc = checkCoinCnt(storeId, "KOR", reqMap, false, "PAY", true);
					if(!suc)
						return -1;
					
					suc = updateStoreCoin(storeId, MapUtils.getString(reqMap, "storeUnit"), reqMap, true, "GET", true);
					if(suc){
						suc = updateStoreCoin(storeId, "KOR", reqMap, false, "PAY", true);
						if(cost < MapUtils.getIntValue(reqMap, "payAmnt"))
							throw new Exception("에러!");
//						manageDao.insertMemberUsrNm(reqMap);
						res = manageDao.insertMemberActive(reqMap);
						
						reqMap.put("val", MapUtils.getIntValue(reqMap, "payAmnt"));
						manageDao.updateMemberPoint(reqMap);
					}
				}
				if(!suc){
					throw new Exception("에러!");
				}
				if(suc){
					res = manageDao.insertPaperHist(reqMap);
				}
			} else if(type.equals("nomem")){
				
				/**
				 * 구매시 원화(KOR)는 + COIN_CNT, 원하는 구매 화폐는 - COIN_CNT
				 * 판매시 원화(KOR)는 - COIN_CNT, 원하는 판매 화폐는 + COIN_CNT 
				 */
				String tradeType = MapUtils.getString(reqMap, "tradeType");
				String storeId = MapUtils.getString(reqMap, "storeId");
				
				if(tradeType.equals("B")){
					suc = checkCoinCnt(storeId, "KOR", reqMap, true, "GET", true);
					if(!suc)
						return -1;
					suc = checkCoinCnt(storeId, MapUtils.getString(reqMap, "storeUnit"), reqMap, false, "PAY", true);
					if(!suc)
						return -1;
					
					suc = updateStoreCoin(storeId, "KOR", reqMap, true, "GET", true);
					if(suc){
						res = manageDao.insertNoMemberActive(reqMap);
						suc = updateStoreCoin(storeId, MapUtils.getString(reqMap, "storeUnit"), reqMap, false, "PAY", true);
					}
					
				} else if(tradeType.equals("S")) {
					suc = checkCoinCnt(storeId, MapUtils.getString(reqMap, "storeUnit"), reqMap, true, "GET", true);
					if(!suc)
						return -1;
					suc = checkCoinCnt(storeId, "KOR", reqMap, false, "PAY", true);
					if(!suc)
						return -1;
					
					suc = updateStoreCoin(storeId, MapUtils.getString(reqMap, "storeUnit"), reqMap, true, "GET", true);
					if(suc){
						res = manageDao.insertNoMemberActive(reqMap);
						suc = updateStoreCoin(storeId, "KOR", reqMap, false, "PAY", true);
					}
				}
				if(!suc){
					throw new Exception("에러!");
				}
				if(suc){
					res = manageDao.insertPaperHist(reqMap);
				}
			} else if(type.equals("point")){
				int usrId = manageDao.selectUsrIdFromBarcode(barcode);
				reqMap.put("usrId", usrId);
				suc = updateStoreCoin(MapUtils.getString(reqMap, "storeId"), MapUtils.getString(reqMap, "storeUnit"), reqMap, true, "GET", false);
				
				if(suc){
					manageDao.insertPointUsrNm(reqMap);
					res = manageDao.insertPointActive(reqMap);
					res = manageDao.insertPaperHist(reqMap);
					res = manageDao.updateUsrPoint(reqMap);
				} else {
					throw new Exception("에러에러");
				}
			}
		} catch (Exception e) {
			logger.info("error ::: " + e.getMessage());
			return 0;
		}

		if(!suc){
			return 0;
		}
		return res;
	}

	public int deleteTrade(String paper, String tradeType, String storeId) throws Exception {
		/**
		 * 멤버십일 경우, 받은돈, 줬던 돈 다시 복구 
		 * 포인트일 경우,  포인트 차감, 받은돈 복구 
		 */
		Map<String, Object> paperHistMap = manageDao.selectPaperHist(paper);
		
		boolean suc = false;
		int res = 0;
		if(tradeType.equals("멤버십")){
			Map<String, Object> memberActiveMap = manageDao.selectMemberActive(paper);
			String tp = MapUtils.getString(memberActiveMap, "TP");
			String unit = MapUtils.getString(memberActiveMap, "UNIT");
			
			if(tp.equals("B")){
				suc = updateStoreCoin(storeId, "KOR", paperHistMap, false, "GET", true);
				if(suc){
					suc = updateStoreCoin(storeId, unit, paperHistMap, true, "PAY", true);
					// GET_AMNT 
					Map<String, Object> reqMap = new HashMap<>();
					reqMap.put("memberId", memberActiveMap.get("MEMBER_ID"));
					reqMap.put("val", memberActiveMap.get("GET_AMNT"));
					manageDao.returnMemberCost(reqMap);
				}
			} else if(tp.equals("S")){
				suc = updateStoreCoin(storeId, unit, paperHistMap, false, "GET", true);
				if(suc){
					suc = updateStoreCoin(storeId, "KOR", paperHistMap, true, "PAY", true);
					// PAY_AMNT
					Map<String, Object> reqMap = new HashMap<>();
					reqMap.put("memberId", memberActiveMap.get("MEMBER_ID"));
					reqMap.put("val", memberActiveMap.get("PAY_AMNT"));
					manageDao.returnMemberCost(reqMap);
				}
			}
			if(suc){
				res = manageDao.deleteMemberActive(paper);
			}
			
		} else if(tradeType.equals("비멤버십")){
			Map<String, Object> memberActiveMap = manageDao.selectMemberActive(paper);
			String tp = MapUtils.getString(memberActiveMap, "TP");
			String unit = MapUtils.getString(memberActiveMap, "UNIT");
			
			if(tp.equals("B")){
				suc = updateStoreCoin(storeId, "KOR", paperHistMap, false, "GET", true);
				if(suc){
					suc = updateStoreCoin(storeId, unit, paperHistMap, true, "PAY", true);
				}
			} else if(tp.equals("S")){
				suc = updateStoreCoin(storeId, unit, paperHistMap, false, "GET", true);
				if(suc){
					suc = updateStoreCoin(storeId, "KOR", paperHistMap, true, "PAY", true);
				}
			}
			if(suc){
				res = manageDao.deleteMemberActive(paper);
			}
			
		} else {
			Map<String, Object> pointMap = manageDao.selectPointActive(paper);
			suc = updateStoreCoin(storeId, MapUtils.getString(pointMap, "UNIT"), paperHistMap, false, "GET", false);
			if(suc){
				res = manageDao.updateReturnPoint(pointMap);
				res = manageDao.deletePointActive(paper);
			}
		}
		
		return res;
	}
	
	public boolean updateStoreCoin(String storeId, String unit, Map<String, Object> targetMap, boolean isAdd, String getPay, boolean isMember) throws Exception{
	
		Map<String, Object> req = new HashMap<String, Object>();
		req.put("storeId", storeId);
		req.put("storeUnit", unit);
		if(!isMember)
			req.put("coinTp", "C");
		
		List<Map<String, Object>> storeCoinList = manageDao.selectStoreCoinList(req);
		int index = 1;
		for(Map<String, Object> temp : storeCoinList){
			int coinCnt = MapUtils.getIntValue(temp, "COIN_CNT", 0);
			int getAmnt = MapUtils.getIntValue(targetMap, getPay + "_AMNT" + index, 0);
			if(isAdd){
				coinCnt = coinCnt + getAmnt;
			} else {
				coinCnt = coinCnt - getAmnt;
			}
			
			if(coinCnt < 0)
				throw new Exception("에러에러");
			
			temp.put("COIN_CNT", coinCnt);
			manageDao.updateStoreCoin(temp);
			index = index + 1;
		}
		
		return true;
	}
	
	public boolean checkCoinCnt(String storeId, String unit, Map<String, Object> targetMap, boolean isAdd, String getPay, boolean isMember) throws Exception{
		
		Map<String, Object> req = new HashMap<String, Object>();
		req.put("storeId", storeId);
		req.put("storeUnit", unit);
		if(!isMember)
			req.put("coinTp", "C");
		
		List<Map<String, Object>> storeCoinList = manageDao.selectStoreCoinList(req);
		int index = 1;
		for(Map<String, Object> temp : storeCoinList){
			int coinCnt = MapUtils.getIntValue(temp, "COIN_CNT", 0);
			int getAmnt = MapUtils.getIntValue(targetMap, getPay + "_AMNT" + index, 0);
			if(isAdd){
				coinCnt = coinCnt + getAmnt;
			} else {
				coinCnt = coinCnt - getAmnt;
			}
			
			if(coinCnt < 0)
				return false;
			index = index + 1;
		}
		
		return true;
	}

	public int checkPaper(String paper) {
		return manageDao.checkPaper(paper);
	}

	public int checkCost(Map<String, Object> reqMap) {
		
		String barcode = MapUtils.getString(reqMap, "barcode");
		if(barcode.length() == 8){
			barcode = barcode.substring(0, 4) + " " + barcode.substring(4, 8);
			reqMap.put("barcode", barcode);
		}
		int cost = manageDao.checkCost(reqMap);
		
		int diff = MapUtils.getIntValue(reqMap, "cost");
		
		if(cost >= diff)
			return 0;
		else
			return 1;
	}

	public double selectUnitCommis(String unitCd) {
		return manageDao.selectUnitCommis(unitCd);
	}

	public List<Map<String, Object>> selectStoreCoin(String storeId) {
		return manageDao.selectStoreCoin(storeId);
	}

	public int updateCoin(Map<String, Object> reqMap) {
		
//		manageDao.updateCoin(reqMap);
		return manageDao.updateSafeCoin(reqMap);
	}

	public List<ActiveListVO> selectActiveListExcel(Paging paging) {

		if(paging.getReservation() == null){
			paging.setStartDt(Utils.getDiffDate(-14));
			paging.setEndDt(Utils.getTodayDate());
		} else {
			StringTokenizer st = new StringTokenizer(paging.getReservation(), "-");
			paging.setStartDt(st.nextToken().trim());
			paging.setEndDt(st.nextToken().trim());
		}
		
		return manageDao.selectActiveListExcel(paging);
	}

	public int selectStoreKor(String storeId) {
		return manageDao.selectStoreKor(storeId);
	}

	public Map<String, Object> chkBarcode(String barcode) {
		Map<String, Object> reqMap = new HashMap<>();
		reqMap.put("barcode", barcode);
		reqMap.put("encKey", ENC_KEY);
		return manageDao.chkBarcode(reqMap);
	}

	public int insertSellUnit(Map<String, Object> reqMap) {

		/**
		 * 외화 구입 관련 정보 등록
		 */
		String barcode = MapUtils.getString(reqMap, "barcode", "");
		
		int res = 0;
		if(barcode.equals("")){
			reqMap.put("encKey", ENC_KEY);
			reqMap.put("tp", "S");
			res = manageDao.insertUnitNomem(reqMap);
		} else {
			String certify = MapUtils.getString(reqMap, "certify", "");
			String idChk = MapUtils.getString(reqMap, "idChk", "");
			if(certify.equals("")){
				/**
				 * 실명인증
				 */
				if(idChk.equals("true")){
					String usrNm = MapUtils.getString(reqMap, "usrNm");
					String usrNmId = MapUtils.getString(reqMap, "usrNmId");
					IdentifyUtils idu = new IdentifyUtils(NAME_CHECK_CODE, NAME_CHECK_PWD);
					boolean chk = idu.checkIdentify(usrNm, usrNmId.replaceAll("-", ""));
					if(!chk){
						return -11;
					}
				}
				
				manageDao.updateUsrNmInfo(reqMap);
			}
			res = manageDao.insertSellUnitMem(reqMap);
		}
		
		if(res > 0){
			reqMap.put("type", "S");
			reqMap.put("st", "S");
			manageDao.insertStoreMoneyLog(reqMap);
			manageDao.insertStoreMoneyLogKor(reqMap);
			res = manageDao.updateStoreMoneySellKor(reqMap);
			res = manageDao.updateStoreMoneySell(reqMap);
		}
		
		return res;
	}

	public int insertBuyUnit(Map<String, Object> reqMap) {
		/**
		 * 외화 구입 관련 정보 등록
		 */
		String barcode = MapUtils.getString(reqMap, "barcode", "");
		String unit = MapUtils.getString(reqMap, "unit", "");
		
		int res = 0;

		reqMap.put("encKey", ENC_KEY);
		if(barcode.equals("")){
			reqMap.put("tp", "B");
			res = manageDao.insertUnitNomem(reqMap);
		} else {

			String certify = MapUtils.getString(reqMap, "certify", "");
			String idChk = MapUtils.getString(reqMap, "idChk", "");
			
			if(certify.equals("")){
				/**
				 * 실명인증
				 */
				if(idChk.equals("true")){
					String usrNm = MapUtils.getString(reqMap, "usrNm");
					String usrNmId = MapUtils.getString(reqMap, "usrNmId");
					IdentifyUtils idu = new IdentifyUtils(NAME_CHECK_CODE, NAME_CHECK_PWD);
					boolean chk = idu.checkIdentify(usrNm, usrNmId.replaceAll("-", ""));
					if(!chk){
						return -11;
					}
				}
				manageDao.updateUsrNmInfo(reqMap);
			}
			
			if(unit.equals("EUR") || unit.equals("CNY")){
				reqMap.put("bonus", 0);
				reqMap.put("basicRateWeys", reqMap.get("basicRate"));
				res = manageDao.insertBuyUnitMem(reqMap);
			} else {
				double basicRateWeys = MapUtils.getDoubleValue(reqMap, "basicRateWeys");
				int payAmntWeys = MapUtils.getIntValue(reqMap, "payAmntWeys");
				int bonus = (int) (payAmntWeys * basicRateWeys);
				
				if(unit.equals("JPY")){
					bonus = bonus / 100;
				}
				reqMap.put("bonus", bonus);
				
				res = manageDao.insertBuyUnitMem(reqMap);
				if(res > 0){
					List<Map<String, Object>> rsvActiveList = new ArrayList<>();
					res = manageDao.updateMemberPointBuy(reqMap);
					
					List<Map<String, Object>> usrMemActiveList = manageDao.selectUsrMemActList(barcode);
					
					for(int i=0 ; i<usrMemActiveList.size() ; i++){
						int cost = MapUtils.getIntValue(usrMemActiveList.get(i), "USE_COST");
						int activeId = MapUtils.getIntValue(usrMemActiveList.get(i), "ACTIVE_ID");
						Map<String, Object> usingCost = new HashMap<>();
						usingCost.put("activeId", activeId);
						
						if(cost > bonus){
							usingCost.put("cost", bonus);
							bonus = 0;
							rsvActiveList.add(usingCost);
							break;
						} else {
							usingCost.put("cost", cost);
							bonus = bonus - cost;
							rsvActiveList.add(usingCost);
						}
					}
					/**
					 * 예약 멤버십 사용 내역 정리
					 */
					for(Map<String, Object> usingCost : rsvActiveList){
						manageDao.updateMemActUse(usingCost);
					}
//					Map<String, Object> rsvActMap = new HashMap<>();
//					rsvActMap.put("rsvId", rsvId);
//					rsvActMap.put("list", rsvActiveList);
//					manageDao.insertRsvAct(rsvActMap);
				}
			}
		}

		if(res > 0){
			reqMap.put("type", "S");
			reqMap.put("st", "B");
			manageDao.insertStoreMoneyLog(reqMap);
			manageDao.insertStoreMoneyLogKor(reqMap);
			res = manageDao.updateStoreMoneyBuyKor(reqMap);
			res = manageDao.updateStoreMoneyBuy(reqMap);
		}
		
		
		return res;
	}

	public List<Map<String, Object>> selectStoreMoney(String storeId) {
		return manageDao.selectStoreMoneyList(storeId);
	}

	public int updateMoney(Map<String, Object> reqMap) {
		reqMap.put("st", "F");
		manageDao.insertStoreMoneyLogForce(reqMap);
		return manageDao.updateMoney(reqMap);
	}

	public List<LogVO> selectLogList(Paging paging) {

		if (paging.getIsSearch() == 1)
			paging.setSearchPage(0);
		else
			paging.setSearchPage((paging.getPageNo() - 1) * 10);
		
		return manageDao.selectLogList(paging);
	}

	public int selectLogListCnt(Paging paging) {
		return manageDao.selectLogListCnt(paging);
	}

	public List<Map<String, Object>> selectExchangeRate() {
		return manageDao.selectExchangeRate();
	}

	public List<UnitVO> selectRsvUnit() {
		return manageDao.selectRsvUnit();
	}

}
