package com.i4unetworks.weys.user;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.i4unetworks.weys.common.Paging;

@Repository
public interface UserDao {

	List<UserListVO> getUserList(Paging paging);

	int getUserListCnt(Paging paging);

	List<UserListVO> selectUserExcel(Paging paging);

	UserListVO selectUserDetail(Map<String, Object> reqMap);

	List<MemberActiveVO> getMembershipList(Paging paging);

	int getMembershipListCnt(String usrId);

	List<UserPointVO> getChangePointList(Paging paging);

	int getChangePointListCnt(String usrId);

	List<UserPointVO> getUsePointList(Paging paging);

	int getUsePointListCnt(String usrId);

	int updateUsrGrade(Map<String, Object> reqMap);

	List<Map<String, Object>> selectRsvList(int usrId);

	List<Map<String, Object>> selectCostList(int usrId);

	List<Map<String, Object>> selectCouponList(int usrId);

	List<Map<String, Object>> selectSendList(int usrId);

	List<Map<String, Object>> selectLoginList(int usrId);

	List<UserListVO> getUserDeleteList(Paging paging);

	int getUserDeleteListCnt(Paging paging);

	List<Map<String, Object>> selectMemoList(int usrId);

	int insertMemo(Map<String, Object> reqMap);

	Map<String, Object> selectUserTel(Map<String, Object> reqMap);

	void insertUsrSms(Map<String, Object> reqMap);

	List<Map<String, Object>> selectSmsList(int usrId);

	void insertSmsSend(Map<String, Object> reqMap);

}
