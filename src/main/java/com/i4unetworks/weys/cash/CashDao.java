package com.i4unetworks.weys.cash;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.i4unetworks.weys.common.Paging;

@Repository
public interface CashDao {

	List<CashVO> selectCashList(Paging paging);

	int selectCashListCnt(Paging paging);

	CashVO selectCashInfo(Map<String, Object> reqMap);

	int updateCashInfo(Map<String, Object> reqMap);

}
