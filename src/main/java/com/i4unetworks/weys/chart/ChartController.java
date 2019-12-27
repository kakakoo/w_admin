package com.i4unetworks.weys.chart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.i4unetworks.weys.common.Utils;

@Controller
@RequestMapping("/api/chart")
public class ChartController {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	private static final String MOTHER_PAGE = "/api/chart";
	@Autowired
	private ChartService chartService;

	@RequestMapping(value = "/trade", method = RequestMethod.GET)
	public String trade(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		List<ChartBSVO> tradeList = chartService.selectTradeList();
		Map<String, Object> tradeChart = chartService.selectTradeChart(tradeList);
		Map<String, Object> tradeMonthlyData = chartService.selectTradeMonthlyData();
		Map<String, Object> tradeWeeklyData = chartService.selectTradeWeeklyData();
		Map<String, Object> tradeBarChart = chartService.selectTradeBarChart(tradeList);
		
		List<Map<String, Object>> colorList = tradeChart == null ? null : getColorlist((List<String>)tradeChart.get("label"));

		List<String> list = new ArrayList<>();
		list.add("'#FF0000'");
		list.add("'#C72FB3'");
		list.add("'#FFE400'");
		list.add("'#1DDB16'");
		list.add("'#0054FF'");

		model.addAttribute("color", list);
		model.addAttribute("colorList", colorList);
		model.addAttribute("tradeList", tradeList);
		model.addAttribute("tradeChart", tradeChart);
		model.addAttribute("tradeMonthlyData", tradeMonthlyData);
		model.addAttribute("tradeWeeklyData", tradeWeeklyData);
		model.addAttribute("tradeBarChart", tradeBarChart);
		model.addAttribute("motherPage", MOTHER_PAGE + "/trade");
		logger.info("===================================== END =====================================");
		return "chart/trade";
	}

	@RequestMapping(value = "/exc", method = RequestMethod.GET)
	public String exc(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		List<ChartBSVO> excList = chartService.selectExcList();
		Map<String, Object> excChart = chartService.selectExcChart(excList);
		Map<String, Object> excMonthlyData = chartService.selectExcMonthlyData();
		Map<String, Object> excWeeklyData = chartService.selectExcWeeklyData();
		Map<String, Object> excBarChart = chartService.selectExcBarChart();

		model.addAttribute("excList", excList);
		model.addAttribute("excChart", excChart);
		model.addAttribute("excMonthlyData", excMonthlyData);
		model.addAttribute("excWeeklyData", excWeeklyData);
		model.addAttribute("excBarChart", excBarChart);
		model.addAttribute("motherPage", MOTHER_PAGE + "/exc");
		logger.info("===================================== END =====================================");
		return "chart/exc";
	}

	private List<Map<String, Object>> getColorlist(List<String> label) {
		
		List<String> list = new ArrayList<>();
		list.add("#FF0000");
		list.add("#C72FB3");
		list.add("#FFE400");
		list.add("#1DDB16");
		list.add("#0054FF");
		
		List<Map<String, Object>> resultList = new ArrayList<>();
		for(int i=0 ; i<5 ; i++){
			Map<String, Object> map = new HashMap<>();
			map.put("unit", label.get(i).replaceAll("'", ""));
			map.put("color", list.get(i));
			resultList.add(map);
		}
		
		return resultList;
	}

}
