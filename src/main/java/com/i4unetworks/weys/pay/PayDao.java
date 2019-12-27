package com.i4unetworks.weys.pay;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.i4unetworks.weys.common.Paging;

@Repository
public interface PayDao {

	List<PayListVO> selectPayList(Paging paging);

	int selectPayListCnt(Paging paging);

	String cancelCheck(int id);

	int updateMemberCancel(Map<String, Object> reqMap);

	int updateReturnComplete(Map<String, Object> reqMap);

	int updateMemberActiveCancel(Map<String, Object> reqMap);

	Map<String, Object> selectSmsInfo(Map<String, Object> reqMap);

}
