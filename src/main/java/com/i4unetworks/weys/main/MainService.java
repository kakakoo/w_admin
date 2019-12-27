package com.i4unetworks.weys.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i4unetworks.weys.common.Paging;
import com.i4unetworks.weys.common.Utils;

@Service
public class MainService {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private MainDao mainDao;
	
	public int selectTotalUser(String date) {
		return mainDao.selectTotalUser(date);
	}

	public int selectTotalTrade(String date) {
		return mainDao.selectTotalTrade(date);
	}

	public long selectTotalCost(String date) {
		return mainDao.selectTotalCost(date);
	}

	public int selectMemberShip(String date) {
		return mainDao.selectMemberShip(date);
	}

	public long selectMemberShipPay(String date) {
		return mainDao.selectMemberShipPay(date);
	}

	public long selectPointChangeUser(String date) {
		return mainDao.selectPointChangeUser(date);
	}

	public long selectPointChange(Map<String, Object> reqMap) {
		return mainDao.selectPointChange(reqMap);
	}

	public int selectTotalRsv() {
		return mainDao.selectTotalRsv();
	}

	public int selectTodayRsv(String date) {
		return mainDao.selectTodayRsv(date);
	}

	public int selectTodayRsvDone(String date) {
		return mainDao.selectTodayRsvDone(date);
	}

	public List<Map<String, Object>> selectReadyUnit(String date) {
		
		List<Map<String, Object>> unitList = mainDao.selectRsvUnit(date);
		
		List<Map<String, Object>> resultList = new ArrayList<>();
		
		for(Map<String, Object> tmp : unitList){
			tmp.put("dt", date);
			List<Map<String, Object>> rsvList = mainDao.selectRsvList(tmp);
			tmp.put("rsvList", rsvList);
			resultList.add(tmp);
		}
		
		return resultList;
	}

	public int selectTodayRsvAll(String date) {
		return mainDao.selectTodayRsvAll(date);
	}

	public int selectYesRsv(String date) {
		return mainDao.selectTodayRsvAll(date);
	}

	public List<String> selectRsvDt(String date) {
		return mainDao.selectRsvDt(date);
	}

	public List<Map<String, Object>> selectSurveyHist(Paging paging) {
	
		paging.setPageSize(40);
		if (paging.getIsSearch() == 1)
			paging.setSearchPage(0);
		else
			paging.setSearchPage((paging.getPageNo() - 1) * 40);
		
		return mainDao.selectSurveyHist(paging);
	}

	public int selectSurveyHistCnt(Paging paging) {
		return mainDao.selectSurveyHistCnt(paging);
	}

	public List<Map<String, Object>> selectReadyStore() {

		String today = Utils.getDiffDate(0);
		List<Map<String, Object>> storeList = mainDao.selectStoreList(today);
		List<String> dtList = mainDao.selectDateList(today);
		
		List<Map<String, Object>> resultList = new ArrayList<>();
		for(Map<String, Object> storeMap : storeList){
			
			List<Map<String, Object>> rsvList = new ArrayList<>();
			for(String dt : dtList){
				storeMap.put("startDt", dt);
				storeMap.put("endDt", Utils.getNextDt(dt,  1));
				storeMap.put("rsvTm", "04:55");
				Map<String, Object> rsv = mainDao.selectReadyStore(storeMap);
				rsvList.add(rsv);
			}
			
//			List<Map<String, Object>> rsvList = mainDao.selectReadyStore(storeMap);
			storeMap.put("rsvList", rsvList);
			resultList.add(storeMap);
		}
		
		return resultList;
				
	}

	public List<Map<String, Object>> selectRateList() {
		return mainDao.selectRateList();
	}

	public int selectRemainRsv() {
		String todayDate = Utils.checkTimeRsv();
		return mainDao.selectRemainRsv(todayDate);
	}
}
