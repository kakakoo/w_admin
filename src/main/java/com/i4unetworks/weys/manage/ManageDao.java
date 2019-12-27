package com.i4unetworks.weys.manage;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.i4unetworks.weys.common.Paging;
import com.i4unetworks.weys.unit.UnitVO;

@Repository
public interface ManageDao {

	List<MemberListVO> getManageInfoMembership(Paging paging);

	List<MemberListVO> getManageInfoPoint(Paging paging);

	List<ActiveListVO> selectActiveList(Paging paging);

	int selectActiveListCnt(Paging paging);

	ClientInfoVO selectClientInfo(Map<String, Object> reqMap);

	List<UnitVO> selectStoreUnit(String storeId);

	List<String> selectUnitBasicRate(String unitCd);

	String selectStoreUnitAmnt(Map<String, Object> reqMap);

	List<Map<String, Object>> selectPayCoinList(Map<String, Object> reqMap);

	List<Map<String, Object>> selectGetCoinList(Map<String, Object> reqMap);

	int insertMemberActive(Map<String, Object> reqMap);

	Map<String, Object> selectMemberIdFromBarcode(String barcode);

	int insertPaperHist(Map<String, Object> reqMap);

	int selectAdminKey(String adminId);

	int selectUsrIdFromBarcode(String barcode);

	int insertPointActive(Map<String, Object> reqMap);

	int updateUsrPoint(Map<String, Object> reqMap);

	List<Map<String, Object>> selectStoreCoinList(Map<String, Object> reqMap);

	int updateStoreCoin(Map<String, Object> temp);

	Map<String, Object> selectMemberActive(String paper);

	Map<String, Object> selectPaperHist(String paper);

	Map<String, Object> selectPointActive(String paper);

	int updateReturnPoint(Map<String, Object> pointMap);

	int deleteMemberActive(String paper);

	int deletePointActive(String paper);

	void insertMemberUsrNm(Map<String, Object> reqMap);

	void insertPointUsrNm(Map<String, Object> reqMap);

	void updateMemberPoint(Map<String, Object> reqMap);

	double selectUsdRate();

	void returnMemberCost(Map<String, Object> reqMap);

	int checkPaper(String paper);

	int checkCost(Map<String, Object> reqMap);

	double selectUnitCommis(String unitCd);

	int insertNoMemberActive(Map<String, Object> reqMap);

	List<Map<String, Object>> selectStoreCoin(String storeId);

	int updateCoin(Map<String, Object> reqMap);

	int updateSafeCoin(Map<String, Object> reqMap);

	List<ActiveListVO> selectActiveListExcel(Paging paging);

	int selectStoreKor(String storeId);

	Map<String, Object> chkBarcode(Map<String, Object> reqMap);

	Map<String, Object> selectSellUnitInfo(Map<String, Object> reqMap);

	int insertUnitNomem(Map<String, Object> reqMap);

	int insertSellUnitMem(Map<String, Object> reqMap);

	int updateStoreMoneySellKor(Map<String, Object> reqMap);
	
	int updateStoreMoneySell(Map<String, Object> reqMap);

	Map<String, Object> selectBuyUnitInfo(Map<String, Object> reqMap);

	int selectStoreMoney(Map<String, Object> reqMap);

	int insertBuyUnitMem(Map<String, Object> reqMap);

	int updateMemberPointBuy(Map<String, Object> reqMap);

	int insertMemberActiveBuy(Map<String, Object> reqMap);

	List<Map<String, Object>> selectUsrMemActList(String barcode);

	void updateMemActUse(Map<String, Object> usingCost);

	int updateStoreMoneyBuyKor(Map<String, Object> reqMap);

	int updateStoreMoneyBuy(Map<String, Object> reqMap);

	List<Map<String, Object>> selectStoreMoneyList(String storeId);

	int updateMoney(Map<String, Object> reqMap);

	int updateUsrNmInfo(Map<String, Object> reqMap);

	void insertStoreMoneyLog(Map<String, Object> reqMap);

	void insertStoreMoneyLogForce(Map<String, Object> reqMap);

	List<LogVO> selectLogList(Paging paging);

	int selectLogListCnt(Paging paging);

	void insertStoreMoneyLogKor(Map<String, Object> reqMap);

	List<Map<String, Object>> selectExchangeRate();

	List<UnitVO> selectRsvUnit();

}
