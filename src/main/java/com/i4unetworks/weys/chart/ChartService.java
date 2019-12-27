package com.i4unetworks.weys.chart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i4unetworks.weys.common.Utils;

@Service
public class ChartService {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private ChartDao chartDao;

	public List<ChartBSVO> selectTradeList() {
		return chartDao.selectTradeList();
	}

	public Map<String, Object> selectTradeChart(List<ChartBSVO> tradeList) {

		/**
		 * TOP 4 국가를 제외한 나머지 국가는 기타에 포함해서 표시
		 */

		List<String> label = new ArrayList<>();
		List<Integer> cnt = new ArrayList<>();
		
		if(tradeList.size() < 4){
			return null;
		}

		/**
		 * USD, JPY, EUR, CNY, OTHER 순서대로 정렬되도록 배치하기
		 */
		int index = 0;
		while (label.size() < 4) {
			for (ChartBSVO temp : tradeList) {
				if (index == 0 && temp.getUnit().equals("USD") || index == 1 && temp.getUnit().equals("JPY")
						|| index == 2 && temp.getUnit().equals("EUR") || index == 3 && temp.getUnit().equals("CNY")) {
					label.add("'" + temp.getUnit() + "'");
					cnt.add(temp.getTotalCnt());
					index++;
					break;
				}
			}
		}

		int total = 0;
		if (tradeList.size() > 4) {
			for (int i = 4; i < tradeList.size(); i++) {
				total = total + tradeList.get(i).getTotalCnt();
			}
		}
		label.add("'OTHER'");
		cnt.add(total);

		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("label", label);
		resultMap.put("cnt", cnt);

		return resultMap;
	}

	public Map<String, Object> selectTradeMonthlyData() {

		/**
		 * 1년전 데이터 부터 12개월간의 직거래 월별 등록현황 USD, JPY, EUR, CNY 4국가 및 나머지 기타로
		 */
		String date = Utils.getLastYear();

		List<ChartUnitVO> dtList = chartDao.selectTradeMonthlyData(date);

		List<String> label = new ArrayList<>();
		List<Integer> usd = new ArrayList<>();
		List<Integer> jpy = new ArrayList<>();
		List<Integer> eur = new ArrayList<>();
		List<Integer> cny = new ArrayList<>();
		List<Integer> other = new ArrayList<>();

		for (ChartUnitVO temp : dtList) {
			label.add(0, temp.getDt());
			usd.add(0, temp.getUsdCnt());
			jpy.add(0, temp.getJpyCnt());
			eur.add(0, temp.getEurCnt());
			cny.add(0, temp.getCnyCnt());
			other.add(0, temp.getOtherCnt());
		}

		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("label", label);
		resultMap.put("usd", usd);
		resultMap.put("jpy", jpy);
		resultMap.put("eur", eur);
		resultMap.put("cny", cny);
		resultMap.put("other", other);

		return resultMap;
	}

	public Map<String, Object> selectTradeBarChart(List<ChartBSVO> tradeList) {

		/**
		 * TOP 4 국가를 제외한 나머지 국가는 기타에 포함해서 표시
		 */

		List<String> label = new ArrayList<>();
		List<Long> buyKor = new ArrayList<>();
		List<Long> sellKor = new ArrayList<>();

		int index = 0;
		for (ChartBSVO temp : tradeList) {
			if (index == 4)
				break;

			label.add("'" + temp.getUnit() + "'");
			buyKor.add(temp.getBuyKor());
			sellKor.add(temp.getSellKor());
			index++;
		}

		long buySum = 0;
		long sellSum = 0;
		if (tradeList.size() > 4) {
			for (int i = 4; i < tradeList.size(); i++) {
				buySum = buySum + tradeList.get(i).getBuyKor();
				sellSum = sellSum + tradeList.get(i).getSellKor();
			}
		}
		label.add("'OTHER'");
		buyKor.add(buySum);
		sellKor.add(sellSum);

		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("label", label);
		resultMap.put("buyKor", buyKor);
		resultMap.put("sellKor", sellKor);

		return resultMap;
	}

	public List<ChartBSVO> selectExcList() {
		return chartDao.selectExcList();
	}

	public Map<String, Object> selectExcChart(List<ChartBSVO> excList) {

		/**
		 * 환전소에서 거래된 양으로 파이 그래프 표현
		 */
		List<String> label = new ArrayList<>();
		List<Integer> cnt = new ArrayList<>();

		for (ChartBSVO temp : excList) {
			label.add("'" + temp.getUnit() + "'");
			cnt.add(temp.getTotalCnt());
		}

		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("label", label);
		resultMap.put("cnt", cnt);

		return resultMap;
	}

	public Map<String, Object> selectExcMonthlyData() {

		/**
		 * 1년전 데이터 부터 12개월간의 직거래 월별 등록현황 USD, JPY, EUR, CNY 4국가
		 */
		String date = Utils.getLastYear();

		List<ChartUnitVO> dtList = chartDao.selectExcMonthlyData(date);

		List<String> label = new ArrayList<>();
		List<Integer> usd = new ArrayList<>();
		List<Integer> jpy = new ArrayList<>();
		List<Integer> eur = new ArrayList<>();
		List<Integer> cny = new ArrayList<>();

		for (ChartUnitVO temp : dtList) {
			label.add(0, temp.getDt());
			usd.add(0, temp.getUsdCnt());
			jpy.add(0, temp.getJpyCnt());
			eur.add(0, temp.getEurCnt());
			cny.add(0, temp.getCnyCnt());
		}

		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("label", label);
		resultMap.put("usd", usd);
		resultMap.put("jpy", jpy);
		resultMap.put("eur", eur);
		resultMap.put("cny", cny);

		return resultMap;
	}

	public Map<String, Object> selectExcBarChart() {

		/**
		 * TOP 4 국가를 제외한 나머지 국가는 기타에 포함해서 표시
		 */
		
		List<Map<String, Object>> excList = chartDao.selectExcBarChart();

		List<String> label = new ArrayList<>();
		List<Long> buyKorMem = new ArrayList<>();
		List<Long> buyKorNoMem = new ArrayList<>();
		List<Long> sellKorMem = new ArrayList<>();
		List<Long> sellKorNoMem = new ArrayList<>();

		for (Map<String, Object> temp : excList) {
			label.add("'" + MapUtils.getString(temp, "UNIT") + "'");
			buyKorMem.add(MapUtils.getLong(temp, "BUY_KOR_MEM"));
			buyKorNoMem.add(MapUtils.getLong(temp, "BUY_KOR_NOMEM"));
			sellKorMem.add(MapUtils.getLong(temp, "SELL_KOR_MEM"));
			sellKorNoMem.add(MapUtils.getLong(temp, "SELL_KOR_NOMEM"));
		}

		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("label", label);
		resultMap.put("buyKorMem", buyKorMem);
		resultMap.put("buyKorNoMem", buyKorNoMem);
		resultMap.put("sellKorMem", sellKorMem);
		resultMap.put("sellKorNoMem", sellKorNoMem);

		return resultMap;
	}

	public Map<String, Object> selectTradeWeeklyData() throws Exception {
		/**
		 * 최근 4주간의 데이터를 표시 
		 * USD, JPY, EUR, CNY 4국가 및 나머지 기타로
		 */
		int num = Utils.getTdayDay(null);

		List<String> label = new ArrayList<>();
		List<Integer> usd = new ArrayList<>();
		List<Integer> jpy = new ArrayList<>();
		List<Integer> eur = new ArrayList<>();
		List<Integer> cny = new ArrayList<>();
		List<Integer> other = new ArrayList<>();
		List<Integer> total = new ArrayList<>();
		
		List<Long> usdSum = new ArrayList<>();
		List<Long> jpySum = new ArrayList<>();
		List<Long> eurSum = new ArrayList<>();
		List<Long> cnySum = new ArrayList<>();
		List<Long> otherSum = new ArrayList<>();
		
		for(int i=3 ; i>=0 ; i--){
			String startDt = Utils.getDiffDate((num + (7*i)) * -1);
			String endDt = Utils.getDiffDate((num + (7*(i-1)) + 1) * -1);
			
			Map<String, Object> reqMap = new HashMap<>();
			reqMap.put("startDt", startDt);
			reqMap.put("endDt", endDt);
			
			ChartUnitVO temp = chartDao.selectTradeWeeklyData(reqMap);
			
			label.add("'" + startDt.substring(2) + "~" + endDt.substring(2) + "'");
			usd.add(temp.getUsdCnt());
			jpy.add(temp.getJpyCnt());
			eur.add(temp.getEurCnt());
			cny.add(temp.getCnyCnt());
			other.add(temp.getOtherCnt());
			total.add(temp.getTotalCnt());

			usdSum.add(temp.getUsdSum());
			jpySum.add(temp.getJpySum());
			eurSum.add(temp.getEurSum());
			cnySum.add(temp.getCnySum());
			otherSum.add(temp.getOtherSum());
		}

		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("label", label);
		resultMap.put("usd", usd);
		resultMap.put("jpy", jpy);
		resultMap.put("eur", eur);
		resultMap.put("cny", cny);
		resultMap.put("other", other);
		resultMap.put("total", total);
		
		resultMap.put("usdSum", usdSum);
		resultMap.put("jpySum", jpySum);
		resultMap.put("eurSum", eurSum);
		resultMap.put("cnySum", cnySum);
		resultMap.put("otherSum", otherSum);

		return resultMap;
	}

	public Map<String, Object> selectExcWeeklyData() throws Exception {

		/**
		 * 최근 4주간의 데이터를 표시 
		 * USD, JPY, EUR, CNY 4국가 및 나머지 기타로
		 */
		int num = Utils.getTdayDay(null);

		List<String> label = new ArrayList<>();
		List<Integer> usd = new ArrayList<>();
		List<Integer> jpy = new ArrayList<>();
		List<Integer> eur = new ArrayList<>();
		List<Integer> cny = new ArrayList<>();
		
		List<Long> usdSum = new ArrayList<>();
		List<Long> jpySum = new ArrayList<>();
		List<Long> eurSum = new ArrayList<>();
		List<Long> cnySum = new ArrayList<>();
		
		for(int i=3 ; i>=0 ; i--){
			String startDt = Utils.getDiffDate((num + (7*i)) * -1);
			String endDt = Utils.getDiffDate((num + (7*(i-1)) + 1) * -1);
			
			Map<String, Object> reqMap = new HashMap<>();
			reqMap.put("startDt", startDt);
			reqMap.put("endDt", endDt);
			
			ChartUnitVO temp = chartDao.selectExcWeeklyData(reqMap);
			
			label.add("'" + startDt.substring(2) + "~" + endDt.substring(2) + "'");
			usd.add(temp.getUsdCnt());
			jpy.add(temp.getJpyCnt());
			eur.add(temp.getEurCnt());
			cny.add(temp.getCnyCnt());

			usdSum.add(temp.getUsdSum());
			jpySum.add(temp.getJpySum());
			eurSum.add(temp.getEurSum());
			cnySum.add(temp.getCnySum());
		}
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("label", label);
		resultMap.put("usd", usd);
		resultMap.put("jpy", jpy);
		resultMap.put("eur", eur);
		resultMap.put("cny", cny);

		resultMap.put("usdSum", usdSum);
		resultMap.put("jpySum", jpySum);
		resultMap.put("eurSum", eurSum);
		resultMap.put("cnySum", cnySum);
		
		return resultMap;
	}
}
