package com.i4unetworks.weys.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i4unetworks.weys.common.Paging;
import com.i4unetworks.weys.common.Utils;

@Service
public class AnalysisService {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private AnalysisDao analysisDao;
	
	public List<AnalysisVO> selectAnalysis() throws Exception {
		
		List<AnalysisVO> resultList = new ArrayList<>();
		Map<String, Object> reqMap = new HashMap<>();
		
		/**
		 * 저번 달 통계
		 */
		reqMap = Utils.getLastMonthMaxDay();
		AnalysisVO lastMonth = analysisDao.selectAnalysis(reqMap);
		lastMonth.setDt(MapUtils.getString(reqMap, "startDt").substring(0, 7));
		resultList.add(lastMonth);

		/**
		 * 저저번주 날짜를 구해서 통계 가져오기
		 */
		int num = Utils.getTdayDay(null);
		String ddt = Utils.getDiffDate((num + 14) * -1);
		String dde = Utils.getDiffDate((num + 8) * -1);
		reqMap.put("startDt", ddt);
		reqMap.put("endDt", dde + " 23:59:59");
		
		AnalysisVO twoWeekAgo = analysisDao.selectAnalysis(reqMap);
		twoWeekAgo.setDt(ddt + " ~ " + dde);
		resultList.add(twoWeekAgo);
		
		/**
		 * 저번주 날짜를 구해서 통계 가져오기
		 */
		String dt = Utils.getDiffDate((num + 7) * -1);
		String de = Utils.getDiffDate((num + 1) * -1);
		reqMap.put("startDt", dt);
		reqMap.put("endDt", de + " 23:59:59");
		
		AnalysisVO weekAgo = analysisDao.selectAnalysis(reqMap);
		weekAgo.setDt(dt + " ~ " + de);
		resultList.add(weekAgo);
		
		/**
		 * 이번 날짜를 구해서 통계 가져오기
		 */
		String t = Utils.getDiffDate((num) * -1);
		String e = Utils.getDiffDate(6 -num);
		reqMap.put("startDt", t);
		reqMap.put("endDt", e + " 23:59:59");
		
		AnalysisVO thisweek = analysisDao.selectAnalysis(reqMap);
		thisweek.setDt("이번주");
		resultList.add(thisweek);
		
		/**
		 * 토탈 통계
		 */
		reqMap = new HashMap<>();
		AnalysisVO totalList = analysisDao.selectAnalysis(reqMap);
		totalList.setDt("총");
		resultList.add(totalList);
		
		
		return resultList;
	}

	public Map<String, Object> selectUsrChart(String date) {

		List<AnalysisVO> list = analysisDao.selectUsrChart(date);
		
		List<String> label = new ArrayList<>();
		List<Integer> total = new ArrayList<>();
		List<Integer> web = new ArrayList<>();
		List<Integer> andUsr = new ArrayList<>();
		List<Integer> iosUsr = new ArrayList<>();
		
		for(AnalysisVO temp : list){
			label.add(temp.getDt());
			total.add(temp.getTotalUsr());
			andUsr.add(temp.getAndUsr());
			iosUsr.add(temp.getIosUsr());
			web.add(temp.getWebUsr());
		}

		Map<String, Object> resMap = new HashMap<>();
		resMap.put("label", label);
		resMap.put("total", total);
		resMap.put("andUsr", andUsr);
		resMap.put("iosUsr", iosUsr);
		resMap.put("webUsr", web);
		
		return resMap;
	}

	public Map<String, Object> selectLonginChart(String date) {
		
		List<Map<String, Object>> list = analysisDao.selectLoginChart(date);
		
		List<String> label = new ArrayList<>();
		List<Integer> total = new ArrayList<>();
		
		for(Map<String, Object> temp : list){
			label.add(MapUtils.getString(temp, "DT"));
			total.add(MapUtils.getInteger(temp, "CNT"));
		}

		Map<String, Object> resMap = new HashMap<>();
		resMap.put("label", label);
		resMap.put("total", total);
		
		return resMap;
	}

	public List<Map<String, Object>> selectCmsList() {
		return analysisDao.selectCmsList();
	}

	public List<Map<String, Object>> selectRsvStore() {
		return analysisDao.selectRsvStore();
	}

	public List<Map<String, Object>> selectRsvUnit() {
		return analysisDao.selectRsvUnit();
	}

	public List<Map<String, Object>> selectAnalysisDetail(Paging paging) {

		if (paging.getIsSearch() == 1)
			paging.setSearchPage(0);
		else
			paging.setSearchPage((paging.getPageNo() - 1) * paging.getPageSize());

		if(paging.getTrdTp() == null){
			paging.setTrdTp("%Y.%m");
		}

		if(paging.getTrdUnit() == null){
			paging.setTrdUnit("ALL");
		}

		if(paging.getStoreId() == null){
			paging.setStoreId("0");
		}
		
		return analysisDao.selectAnalysisDetail(paging);
	}

	public int selectAnalysisDetailCnt(Paging paging) {
		return analysisDao.selectAnalysisDetailCnt(paging);
	}
	
}
