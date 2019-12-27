package com.i4unetworks.weys.trade;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.i4unetworks.weys.common.Paging;
import com.i4unetworks.weys.common.Utils;

@Service
public class TradeService {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private TradeDao tradeDao;
	
	@Value("#{props['ENC.KEY']}")
	private String ENC_KEY;
	
	public List<TradeDetailVO> getTradeList(Paging paging) {

		if (paging.getIsSearch() == 1)
			paging.setSearchPage(0);
		else
			paging.setSearchPage((paging.getPageNo() - 1) * 10);
		
		if(paging.getReservation() == null){
			paging.setStartDt(Utils.getDiffDate(-14));
			paging.setEndDt(Utils.getTodayDate());
		} else {
			StringTokenizer st = new StringTokenizer(paging.getReservation(), "-");
			paging.setStartDt(st.nextToken().trim());
			paging.setEndDt(st.nextToken().trim());
		}

		if(paging.getTrdTp() != null){
			String trdTp = paging.getTrdTp();
			if(trdTp.equals("t") || trdTp.equals("")){
				paging.setTrdTp(null);
			} else{
				StringTokenizer st = new StringTokenizer(trdTp, ",");
				List<String> tokenList = new ArrayList<String>();
				while(st.hasMoreTokens()){
					tokenList.add(st.nextToken());
				}
				paging.setListData1(tokenList);
			}
		}

		if(paging.getTrdUnit() != null){
			String trdUnit = paging.getTrdUnit();
			if(trdUnit.equals("t") || trdUnit.equals("")){
				paging.setTrdUnit(null);
			} else{
				StringTokenizer st = new StringTokenizer(trdUnit, ",");
				List<String> tokenList = new ArrayList<String>();
				while(st.hasMoreTokens()){
					tokenList.add(st.nextToken());
				}
				paging.setListData(tokenList);
			}
		}
		paging.setEncKey(ENC_KEY);
		
		return tradeDao.getTradeList(paging);
	}
	
	public int getTradeListCnt(Paging paging) {
		return tradeDao.getTradeListCnt(paging);
	}

	public List<TradeDetailVO> selectTradeExcel(Paging paging) {

		if(paging.getReservation() == null){
			paging.setStartDt(Utils.getDiffDate(-14));
			paging.setEndDt(Utils.getTodayDate());
		} else {
			StringTokenizer st = new StringTokenizer(paging.getReservation(), "-");
			paging.setStartDt(st.nextToken().trim());
			paging.setEndDt(st.nextToken().trim());
		}

		if(paging.getTrdTp() != null){
			String trdTp = paging.getTrdTp();
			if(trdTp.equals("t") || trdTp.equals("")){
				paging.setTrdTp(null);
			} else{
				StringTokenizer st = new StringTokenizer(trdTp, ",");
				List<String> tokenList = new ArrayList<String>();
				while(st.hasMoreTokens()){
					tokenList.add(st.nextToken());
				}
				paging.setListData1(tokenList);
			}
		}

		if(paging.getTrdUnit() != null){
			String trdUnit = paging.getTrdUnit();
			if(trdUnit.equals("t") || trdUnit.equals("")){
				paging.setTrdUnit(null);
			} else{
				StringTokenizer st = new StringTokenizer(trdUnit, ",");
				List<String> tokenList = new ArrayList<String>();
				while(st.hasMoreTokens()){
					tokenList.add(st.nextToken());
				}
				paging.setListData(tokenList);
			}
		}
		paging.setEncKey(ENC_KEY);
		
		return tradeDao.selectTradeExcel(paging);
	}
}
