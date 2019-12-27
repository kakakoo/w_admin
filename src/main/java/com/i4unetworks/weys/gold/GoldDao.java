package com.i4unetworks.weys.gold;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.i4unetworks.weys.common.Paging;
import com.i4unetworks.weys.store.StoreListVO;

@Repository
public interface GoldDao {

	List<GoldVO> selectGoldList(Map<String, Object> reqMap);

	List<Map<String, Object>> selectRsvInfo(Map<String, Object> reqMap);

	int insertGoldHist(Map<String, Object> reqMap);

	int updateGoldInfo(Map<String, Object> reqMap);

	int insertGoldLog(Map<String, Object> reqMap);

	List<Map<String, Object>> selectGoldInfo(Map<String, Object> reqMap);

	List<Map<String, Object>> selectRsvChange(Map<String, Object> reqMap);

	List<Map<String, Object>> selectLogList(Map<String, Object> tmp);

	List<Map<String, Object>> selectRsvCancel(Map<String, Object> reqMap);

	List<StoreListVO> selectStoreList(String adminKey);

	void insertMoneyMng(Map<String, Object> reqMap);

	List<Map<String, Object>> selectCashList(Paging paging);

	List<Map<String, Object>> selectMoneyList(String tmpUnit);

	void updateMoneyMng(Map<String, Object> mMng);

	Map<String, Object> selectDataInfo(String unit);

	double selectRateInfo(String unit);

	void insertDumData(Map<String, Object> reqMap);

}
