package com.i4unetworks.weys.trade;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.i4unetworks.weys.common.Paging;

@Repository
public interface TradeDao {

	List<TradeDetailVO> getTradeList(Paging paging);

	int getTradeListCnt(Paging paging);

	List<TradeDetailVO> selectTradeExcel(Paging paging);

}
