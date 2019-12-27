package com.i4unetworks.weys.chart;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface ChartDao {

	List<ChartBSVO> selectTradeList();

	List<ChartUnitVO> selectTradeMonthlyData(String date);

	List<ChartBSVO> selectExcList();

	List<ChartUnitVO> selectExcMonthlyData(String date);

	ChartUnitVO selectTradeWeeklyData(Map<String, Object> reqMap);

	ChartUnitVO selectExcWeeklyData(Map<String, Object> reqMap);

	List<Map<String, Object>> selectExcBarChart();

}
