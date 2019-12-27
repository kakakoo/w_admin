package com.i4unetworks.weys.store;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.i4unetworks.weys.common.Paging;

@Repository
public interface StoreDao {

	List<StoreListVO> getStoreList(Paging paging);

	int getStoreListCnt();

	int insertStore(StoreDetailVO storeDetailVO);

	StoreDetailVO selectDetail(String storeId);

	int updateStore(StoreDetailVO storeDetailVO);

	int deleteStore(String storeId);

	int updateStatus(Map<String, Object> reqMap);

	List<StoreStaffVO> selectStoreStaff(Paging paging);

	int selectStoreStaffCnt();

	List<Map<String, Object>> selectStoreList();

	int insertStaff(StoreStaffVO staffVO);

	int updateStaffPw(StoreStaffVO staffVO);

	int deleteStaff(String adminKey);

	int updateStaffInfo(StoreStaffVO staffVO);

	List<Map<String, Object>> selectStoreCoin(String storeId);

	String selectStoreNm(String storeId);

	List<StoreChangeVO> selectStoreMember(Paging paging);

	int selectStoreMemberCnt(Paging paging);

	List<StoreChangeVO> selectStorePoint(Paging paging);

	int selectStorePointCnt(Paging paging);

	void insertRsvStore(Map<String, Object> reqMap);

	List<Map<String, Object>> selectOpenDay(String storeId);

	void updateRsvStore(Map<String, Object> updateMap);

	void deleteRsvClose(String storeId);

	void insertRsvClose(Map<String, Object> updateMap);

	void insertPwdLog(Map<String, Object> reqMap);

	void deleteStaffToken(String adminKey);

	List<StoreListVO> selectStoreAllList();

	StoreStaffVO selectAdminInfo(Map<String, Object> reqMap);

	List<Integer> selectAdminStore(String adminKey);

	void insertAdminStore(StoreStaffVO staffVO);

	void deleteStaffStore(String adminKey);

}
