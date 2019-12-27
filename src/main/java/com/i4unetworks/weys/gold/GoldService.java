package com.i4unetworks.weys.gold;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.i4unetworks.weys.common.Paging;
import com.i4unetworks.weys.common.Utils;
import com.i4unetworks.weys.store.StoreListVO;

@Service
public class GoldService {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private GoldDao goldDao;

	@Value("#{props['ENC.KEY']}")
	private String ENC_KEY;
	
	public Map<String, Object> selectRsvInfo(Map<String, Object> reqMap) {
		
		String rsvDt = MapUtils.getString(reqMap, "rsvDt");
		reqMap.put("rsvTm", "04:55");
		reqMap.put("endDt", Utils.getNextDt(rsvDt, 1));

		List<Map<String, Object>> resultList = goldDao.selectRsvInfo(reqMap);
		List<Map<String, Object>> goldList = goldDao.selectGoldInfo(reqMap);
		
		for(Map<String, Object> tmp : goldList){
			List<Map<String, Object>> logList = goldDao.selectLogList(tmp);
			
			String unit = "";
			String smry = "";
			int row = 0;
			int sum = 0;
			
			List<Map<String, Object>> smryList = new ArrayList<>();
			
			for(Map<String, Object> log : logList){
				String logUnit = MapUtils.getString(log, "UNIT");

				int paper = MapUtils.getIntValue(log, "PAPER");
				int amnt = MapUtils.getIntValue(log, "AMNT");
				
				if(unit.equals(logUnit)){
					smry = smry + " " + Utils.setStringFormatInteger(paper + "") + "(" + amnt + ") ";
					sum = sum + (paper * amnt);
				} else {
					if(!unit.equals("")){
						Map<String, Object> logMap = new HashMap<>();
						logMap.put("unit", unit);
						logMap.put("smry", smry);
						logMap.put("sum", sum);
						smryList.add(logMap);
					}
					unit = logUnit;
					smry = Utils.setStringFormatInteger(paper + "") + "(" + amnt + ") ";
					sum = (paper * amnt);
					row = row + 1;
				}
			}

			Map<String, Object> logMap = new HashMap<>();
			logMap.put("unit", unit);
			logMap.put("smry", smry);
			logMap.put("sum", sum);
			smryList.add(logMap);

			tmp.put("row", row);
			tmp.put("smryList", smryList);
			
		}

		reqMap.put("encKey", ENC_KEY);
		List<Map<String, Object>> changeList = goldDao.selectRsvChange(reqMap);
		List<Map<String, Object>> cancelList = goldDao.selectRsvCancel(reqMap);
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("goldList", goldList);
		resultMap.put("resultList", resultList);
		resultMap.put("changeList", changeList);
		resultMap.put("cancelList", cancelList);
		return resultMap;
	}

	public int insertGoldLog(Map<String, Object> reqMap) {

		int res = goldDao.insertGoldHist(reqMap);
		
		if(res > 0){
			List<Map<String, Object>> unitList = (List<Map<String, Object>>) reqMap.get("goldAry");
			int ghId = MapUtils.getIntValue(reqMap, "ghId");
			
			int move = MapUtils.getIntValue(reqMap, "move", 1);
			String goldTp = MapUtils.getString(reqMap, "goldTp");
			String unit = "";
			int sumAmnt = 0;
			double basicRate = 0.0;
			
			for(Map<String, Object> tmp : unitList){
				tmp.put("move", move);
				if(move == -1){
					int storeId = MapUtils.getIntValue(reqMap, "storeId");
					tmp.put("storeId", storeId);
				}
				res = goldDao.updateGoldInfo(tmp);
				if(res > 0){
					tmp.put("ghId", ghId);
					res = goldDao.insertGoldLog(tmp);
				}
				
				if(goldTp.equals("B") || goldTp.equals("P")){
					/**
					 * 매입 환율 등록
					 */
					String tmpUnit = MapUtils.getString(tmp, "unit");
					int amnt = MapUtils.getIntValue(tmp, "paper") * MapUtils.getIntValue(tmp, "cnt");
					double rate = MapUtils.getDoubleValue(tmp, "rate");
					
					if(unit.equals(tmpUnit)){
						sumAmnt = sumAmnt + amnt;
					} else {
						if(unit.equals("")){
							sumAmnt = amnt;
							unit = tmpUnit;
							basicRate = rate;
						} else {
							uploadMoneyMng(unit, sumAmnt, basicRate);

							sumAmnt = amnt;
							unit = tmpUnit;
							basicRate = rate;
						}
					}
				} else if(goldTp.equals("S")){
					/**
					 * 오프라인 판매 환율 등록
					 */
					String tmpUnit = MapUtils.getString(tmp, "unit");
					int amnt = MapUtils.getIntValue(tmp, "paper") * MapUtils.getIntValue(tmp, "cnt") * -1;
					double rate = MapUtils.getDoubleValue(tmp, "rate");
					
					List<Map<String, Object>> moneyList = goldDao.selectMoneyList(tmpUnit);
					for(Map<String, Object> mMng : moneyList){
						
						int buyAmnt = MapUtils.getIntValue(mMng, "BUY_AMNT");
						if(amnt < buyAmnt){
							int sellKor = (int) (amnt * rate);
							if(tmpUnit.equals("JPY"))
								sellKor = sellKor / 100;
							
							mMng.put("sellAmnt", amnt);
							mMng.put("sellKor", sellKor);
							
							goldDao.updateMoneyMng(mMng);
							break;
						} else {
							amnt = amnt - buyAmnt;
							
							int sellAmnt = buyAmnt;
							int sellKor = (int) (sellAmnt * rate);
							if(tmpUnit.equals("JPY"))
								sellKor = sellKor / 100;
							
							mMng.put("sellAmnt", sellAmnt);
							mMng.put("sellKor", sellKor);
							
							goldDao.updateMoneyMng(mMng);
						}
					}
				}
			}

			if(goldTp.equals("B") || goldTp.equals("P")){
				uploadMoneyMng(unit, sumAmnt, basicRate);
				
				if(goldTp.equals("P")){
					/**
					 * 한중 매입일 경우 분리해서 등록
					 * 
					 * 해당 통화 마지막에 산 날짜 다음날 부터 오늘까지.
					 * 
					 */
					insertDummyData(unit, sumAmnt, basicRate);
				}
			}
		}

		return res;
	}
	
	private void insertDummyData(String unit, int sumAmnt, double basicRate) {

		Map<String, Object> infoMap = goldDao.selectDataInfo(unit);
		
		String lastChange = MapUtils.getString(infoMap, "dt");		// 마지막 매입 날짜
		int rsvSize = MapUtils.getIntValue(infoMap, "UNIT_SIZE");	// 예약단위

		double unitRate = goldDao.selectRateInfo(unit);
		double usdRate = goldDao.selectRateInfo("USD");
		
		int max = 0;		// 최대금액치
		int USD_MAX = 2000;
		
		if(unit.equals("USD")){
			max = USD_MAX;
		} else {
			int maxKor = (int) (USD_MAX * usdRate);
			int unitMax = (int) (maxKor / unitRate);
			if(unit.equals("JPY")){
				unitMax = unitMax * 100;
			}
			max = unitMax / rsvSize * rsvSize;
		}
		
		int amnt = sumAmnt;			// 매입금액
		int min = rsvSize * 5;		// 예약단위 * 5
		
		List<Integer> list = new ArrayList<>();
		
		if(amnt < min){
			list.add(amnt);
		} else {
			while(amnt > 0){
				if(amnt < (max / 2)){
					list.add(amnt);
					amnt = 0;
				} else {
					int val = (int) (Math.random() * max) / rsvSize * rsvSize;
					val = val < min ? min : val;
					
					if(val > amnt){
						list.add(amnt);
						amnt = 0;
					} else {
						list.add(val);
						amnt = amnt - val;
					}
				}
			}
		}
		
		
		/**
		 * 
		 */
		int listSize = list.size();
		
		String td = Utils.getTodayDate();
		long deff = Utils.diffTwoDate(td, lastChange);
		
		int dayColumn = (int) (listSize / deff);
		int leftColumn = (int) (listSize % deff);

		int index = 1;
		if(deff == 0)
			index = 0;
		String startDt = Utils.getNextDt(lastChange, index);

		int startIndex = 0;
		int endIndex = dayColumn;
		
		while(deff >= 0){

			if(index <= leftColumn){
				endIndex = endIndex + 1;
			}
			
			Map<String, Object> reqMap = new HashMap<>();
			reqMap.put("dt", startDt);
			List<Integer> res = list.subList(startIndex, endIndex);
			reqMap.put("dataSet", res);
			reqMap.put("basicRate", basicRate);
			reqMap.put("unit", unit);
			goldDao.insertDumData(reqMap);
			
			startIndex = endIndex;
			endIndex = endIndex + dayColumn;
			
			if(listSize <= endIndex)
				break;

			index = index + 1;
			startDt = Utils.getNextDt(lastChange, index);
			deff = Utils.diffTwoDate(td, startDt);
		}
	}

	private void uploadMoneyMng(String unit, int sumAmnt, double basicRate) {
		
		Map<String, Object> reqMap = new HashMap<>();
		reqMap.put("unit", unit);
		reqMap.put("buyAmnt", sumAmnt);
		reqMap.put("buyRate", basicRate);
		
		int buyKor = (int) (unit.equals("JPY") ?  sumAmnt * basicRate / 100 : sumAmnt * basicRate);
		reqMap.put("buyKor", buyKor);
		
		goldDao.insertMoneyMng(reqMap);
	}

	public List<StoreListVO> selectStoreList(String adminKey) {
		return goldDao.selectStoreList(adminKey);
	}

	public List<GoldVO> selectStoreGold(Map<String, Object> reqMap) {

		return goldDao.selectGoldList(reqMap);
	}

	public List<Map<String, Object>> selectCashList(Paging paging) {
		
		if(paging.getStartDt() == null){
			paging.setStartDt(Utils.getDiffDate(-1));
		}
		
		List<Map<String, Object>> cashList = goldDao.selectCashList(paging);
		
		int cageKor = 0;
		int rsvKor = 0;
		int totalKor = 0;
		
		for(Map<String, Object> tmp : cashList){
			
			String unit = MapUtils.getString(tmp, "UNIT");
			
			if(unit.equals("KRW")){
				cageKor = cageKor + MapUtils.getIntValue(tmp, "A_CAGE", 0);
				cageKor = cageKor + MapUtils.getIntValue(tmp, "B_CAGE", 0);
			} else {
				cageKor = cageKor + MapUtils.getIntValue(tmp, "CAGE_KOR", 0);
				rsvKor = rsvKor + MapUtils.getIntValue(tmp, "RSV_KOR", 0);
			}
		}
		totalKor = cageKor - rsvKor;
		
		Map<String, Object> krwMap = new HashMap<>();
		krwMap.put("UNIT", "TOTAL");
		krwMap.put("CAGE_KOR", cageKor);
		krwMap.put("RSV_KOR", rsvKor);
		krwMap.put("TOTAL", totalKor);
		
		cashList.add(krwMap);
		
		return cashList;
	}
	
}
