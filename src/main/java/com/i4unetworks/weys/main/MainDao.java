package com.i4unetworks.weys.main;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.i4unetworks.weys.common.Paging;

@Repository
public interface MainDao {

	int selectTotalUser(String date);

	int selectTotalTrade(String date);

	long selectTotalCost(String date);

	int selectMemberShip(String date);

	long selectMemberShipPay(String date);

	long selectPointChangeUser(String date);

	long selectPointChange(Map<String, Object> reqMap);

	int selectTotalRsv();

	int selectTodayRsv(String date);

	int selectTodayRsvDone(String date);

	List<Map<String, Object>> selectReadyUnit(Map<String, Object> reqMap);

	int selectTodayRsvAll(String date);

	List<Map<String, Object>> selectRsvUnit(String date);

	List<Map<String, Object>> selectRsvList(Map<String, Object> tmp);

	List<String> selectRsvDt(String date);

	List<Map<String, Object>> selectSurveyHist(Paging paging);

	int selectSurveyHistCnt(Paging paging);

	List<Map<String, Object>> selectStoreList(String today);

	Map<String, Object> selectReadyStore(Map<String, Object> storeMap);

	List<Map<String, Object>> selectRateList();

	int selectRemainRsv(String todayDate);

	List<String> selectDateList(String today);

}
