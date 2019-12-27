package com.i4unetworks.weys.login;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.i4unetworks.weys.common.UserVO;

@Repository
public interface LoginDao {

	UserVO login(String string);

	List<Map<String, Object>> selectDisplayInfo();

	int selectExchangeRateCnt(String dt);

	void insertAdminLog(Map<String, Object> reqMap);

	int updateAdmPwd(UserVO userVO);

	int insertPwdLog(UserVO userVO);

	List<Map<String, Object>> selectExchangeRate();

	Map<String, Object> selectExchangeRateCny();

	List<Map<String, Object>> selectMoneyList(String unit);

	List<Map<String, Object>> selectBuyList(String unit);

	void updateMoneyMng(Map<String, Object> reqMap);

	List<String> selectUnitList();

}
