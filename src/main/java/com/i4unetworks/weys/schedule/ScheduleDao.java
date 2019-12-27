package com.i4unetworks.weys.schedule;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleDao {

	String checkDate();

	void insertExchange(Map<String, Object> req);

	void deleteChat();

	void deleteYesterdayExchange(String diffDate);

	void updateMemberCostZero(String diffDate);

	List<Integer> selectNoMemberList();

	int selectMemberBarcodeCnt(String barcode);

	int insertMemberInfo(Map<String, Object> insertMap);

	int insertMemberActive(Map<String, Object> insertMap);

	Map<String, Object> selectVbFinUuid(Integer usrid);

	int selectCheckMem(Integer usrid);

	void updateMemberCost();

	List<Map<String, Object>> selectExcList();

	List<String> selectAlrmUsrList(Map<String, Object> rateMap);

	void updateAlrmSt(Map<String, Object> rateMap);

	void insertAlarm(Map<String, Object> alarm);

	List<String> selectTomorwRsv(Map<String, Object> reqMap);

	int insertCashInfo(Map<String, Object> reqMap);

	int insertCashLog(Map<String, Object> reqMap);

	void updateCenterLogout();

	void updateCenterReturn(Map<String, Object> reqMap);

}
