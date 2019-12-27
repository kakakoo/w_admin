package com.i4unetworks.weys.analysis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.i4unetworks.weys.common.Paging;

@Repository
public interface AnalysisDao {

	AnalysisVO selectAnalysis(Map<String, Object> reqMap);

	List<AnalysisVO> selectUsrChart(String date);

	List<Map<String, Object>> selectLoginChart(String date);

	List<Map<String, Object>> selectCmsList();

	List<Map<String, Object>> selectRsvUnit();

	List<Map<String, Object>> selectRsvStore();

	List<Map<String, Object>> selectAnalysisDetail(Paging paging);

	int selectAnalysisDetailCnt(Paging paging);

}
