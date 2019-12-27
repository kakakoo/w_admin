package com.i4unetworks.weys.money;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.i4unetworks.weys.common.Paging;

@Repository
public interface MoneyDao {

	List<MoneyListVO> selectMoneyList(Paging paging);

	int selectMoneyListCnt(Paging paging);

	int insertMoneyMng(Map<String, Object> reqMap);

}
