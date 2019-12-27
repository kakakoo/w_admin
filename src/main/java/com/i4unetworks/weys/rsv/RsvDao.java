package com.i4unetworks.weys.rsv;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.i4unetworks.weys.common.Paging;

@Repository
public interface RsvDao {

	List<RsvListVO> getRsvList(Paging paging);

	int getRsvListCnt(Paging paging);

	List<RsvListVO> getRsvCancelList(Paging paging);

	int getRsvListCancelCnt(Paging paging);

	int updateRsvCancel(Map<String, Object> reqMap);

	int insertRsvLog(Map<String, Object> reqMap);

	Map<String, Object> selectCancelUsrInfo(Map<String, Object> reqMap);

	List<Map<String, Object>> selectStoreList();

	List<RsvUnitVO> getRsvUnitList();

	int updateRsvUnit(Map<String, Object> reqMap);

	List<RsvExcelVO> selectRsvExcel(Map<String, Object> reqMap);

	List<Map<String, Object>> selectStoreMng(int storeId);

	int updateRsvMng(Map<String, Object> reqMap);

	int updateRsvSt(Map<String, Object> updateMap);

	int insertRsvLogMap(Map<String, Object> updateMap);

	int insertAdmActLog(Map<String, Object> updateMap);

	Map<String, Object> selectPushInfo(Map<String, Object> updateMap);

	String selectUsrNation(String rsvId);

	void insertKakaoLog(Map<String, Object> talk);

	Integer insertMARsvCancel(String rsvId);

	void updateMemberCost(String rsvId);

	Map<String, Object> selectVbInfo(Map<String, Object> reqMap);

	int selectRsvQrCnt(String qr);

	void insertAlarm(Map<String, Object> alarm);

	List<RsvListVO> getRsvExcelList(Paging paging);

	int selectManagerKey(int rsvId);

	int updateRsvIncome(Map<String, Object> reqMap);

	void insertRsvLogVO(Map<String, Object> reqMap);

	int updateQrCode(Map<String, Object> reqMap);

	Map<String, Object> selectRsvForm(int rsvId);

	Map<String, Object> selectResPayInfo(Map<String, Object> reqMap);

	List<String> selectMngList(String eNC_KEY);

	Map<String, Object> selectVbFinUuid(Map<String, Object> reqMap);

	int selectCheckUseCost(int rsvId);

	void updateReturnMemCost(int rsvId);

	void updateReturnUseCost(int rsvId);

	void insertReturnRA(int rsvId);

	void insertReturnMoneyLog(Map<String, Object> moneyMap);

	void updateReturnMoney(Map<String, Object> moneyMap);

	Map<String, Object> selectRsvMissIncomeCp(int rsvId);

	void updateReturnCoupon(int couponId);

	int updateRsvMiss(int rsvId);

	String checkRsvSt(int rsvId);

	int updateRsvDt(Map<String, Object> reqMap);

	void updateRsvReady(String id);

	int insertReadyRsv(Map<String, Object> reqMap);

	List<RsvListVO> selectRsvCancelExcel(Paging paging);

	Map<String, Object> selectRsvStoreInfo(Map<String, Object> reqMap);

	List<Map<String, Object>> selectRsvStoreList(String unit);

	int updateRsvStore(Map<String, Object> reqMap);

	List<String> selectAdminUuid();

	Map<String, Object> selectMissUuid(Map<String, Object> reqMap);

	int selectRsvBonusId(int rsvId);

	int updateCouponBack(int bonusId);

	int updateRsvBack(int rsvId);

	List<Map<String, Object>> selectRsvUnitList();

	int updateRsvBackCancel(int rsvId);

	List<Map<String, Object>> selectRsvMemo(Map<String, Object> reqMap);

	int insertRsvMemo(Map<String, Object> reqMap);

	int selectGrpCnt(Map<String, Object> reqMap);

	List<RsvListVO> selectRsvGroup(GroupVO groupVO);

	List<Map<String, Object>> selectGroupCnt(GroupVO groupVO);

	void updateRsvGrp(GroupVO groupVO);

	int selectGrpQrCnt(String qr);

	int insertRsvGroup(GroupVO groupVO);

	int insertRsvGrpLog(GroupVO groupVO);

	Map<String, Object> selectStoreNm(int storeId);

	List<GroupVO> getGrpList(Paging paging);

	int getGrpListCnt(Paging paging);

	List<RsvListVO> selectRsvGroupView(GroupVO groupVO);

	List<Map<String, Object>> selectGroupCntView(GroupVO groupVO);

	Map<String, Object> selectGrpInfo(int groupId);

	Map<String, Object> selectRsvInfo(Map<String, Object> reqMap);

	int updateGrpLog(Map<String, Object> reqMap);

	void updateRollBackRsv(Map<String, Object> reqMap);

	List<Integer> selectCenterList();

	int selectExistGrp(GroupVO groupVO);

	int insertRsvRetGrpLog(GroupVO groupVO);

	int selectRsvGrpCnt(GroupVO groupVO);

	List<Map<String, Object>> selectRsvRet(GroupVO groupVO);

	int selectMissMatchKakao(Map<String, Object> reqMap);

	int insertMissmatchKakao(Map<String, Object> reqMap);

	List<Map<String, Object>> getVbankList(Paging paging);

	int getVbankListCnt(Paging paging);

	int updateVbankChk(Map<String, Object> reqMap);

	Map<String, Object> selectCancelSt(Map<String, Object> reqMap);

	int updateCancelRsv(Map<String, Object> reqMap);

	Map<String, Object> selectCancelEmail(Map<String, Object> reqMap);

	void updateCouponReturn(int couponId);

	void updateRsvGrpCancelLog(int intValue);

	Map<String, Object> selectUsrUuid(int usrId);

	List<Map<String, Object>> selectTestInfo(Map<String, Object> reqMap);

	void updateCenterBack(int rsvId);

	int selectRsvGrpADCnt(Map<String, Object> reqMap);

	int insertRsvGrpLogAD(Map<String, Object> reqMap);

	void updateRsvGrpAD(Map<String, Object> reqMap);

	List<Map<String, Object>> selectRsvCancelGroup(Map<String, Object> reqMap);

	List<Map<String, Object>> selectRsvNotGet(Map<String, Object> reqMap);

	int insertRsvRetGrpLogAD(Map<String, Object> reqMap);

	int insertRsvRetLog(Map<String, Object> reqMap);

	List<Map<String, Object>> selectRsvRetLog(Map<String, Object> reqMap);

	void updateRsvRetLog(List<Map<String, Object>> retList1);

	void updateRsvRetLogBack(int rsvId);

}
