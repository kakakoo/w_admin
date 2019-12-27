package com.i4unetworks.weys.money;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i4unetworks.weys.common.Paging;
import com.i4unetworks.weys.common.UserVO;

@Service
public class MoneyService {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private MoneyDao moneyDao;
	
	public List<MoneyListVO> selectMoneyList(Paging paging) {

		if (paging.getIsSearch() == 1)
			paging.setSearchPage(0);
		else
			paging.setSearchPage((paging.getPageNo() - 1) * paging.getPageSize());
		
		List<MoneyListVO> resultList = moneyDao.selectMoneyList(paging);
		
		for(MoneyListVO tmp : resultList){
			if(tmp.getSellAmnt() > 0){
				double avgRate = (double) tmp.getSellKor() / tmp.getSellAmnt();
				if(tmp.getUnit().equals("JPY")){
					avgRate = avgRate * 100;
				}
				tmp.setAvgRate(avgRate);
				tmp.setDiffRate(avgRate - tmp.getBuyRate());
				
				if(tmp.getBuyAmnt() > tmp.getSellAmnt()){
					int leftAmnt = tmp.getBuyAmnt() - tmp.getSellAmnt();
					int leftKor = tmp.getBuyKor() - tmp.getSellKor();
					
					double leftRate = (double) leftKor / leftAmnt;
					if(tmp.getUnit().equals("JPY")){
						leftRate = leftRate * 100;
					}
					tmp.setLeftRate(leftRate);
				} else if(tmp.getBuyAmnt() == tmp.getSellAmnt()){
					int leftKor = tmp.getSellKor() - tmp.getBuyKor();
				}
					
			}
				
		}
		
		return resultList;
	}
	
	public int selectMoneyListCnt(Paging paging) {
		return moneyDao.selectMoneyListCnt(paging);
	}
	
	public int insertMoneyMng(Map<String, Object> reqMap, UserVO userVO) {
		
		String unit = MapUtils.getString(reqMap, "unit");
		double basicRate = MapUtils.getDoubleValue(reqMap, "basicRate");
		int amnt = MapUtils.getIntValue(reqMap, "amnt");
		
		int buyKor = (int) (basicRate * amnt);
		if(unit.equals("JPY")){
			buyKor = buyKor / 100;
		}
		reqMap.put("buyKor", buyKor);
		
		return moneyDao.insertMoneyMng(reqMap);
	}
}
