package com.i4unetworks.weys.analysis;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.i4unetworks.weys.common.Paging;
import com.i4unetworks.weys.common.Utils;

@Controller
@RequestMapping("/api/analysis")
public class AnalysisController {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	private static final String MOTHER_PAGE = "/api/analysis";
	@Autowired
	private AnalysisService analysisService;

	private List<Map<String, Object>> storeList;
	private List<Map<String, Object>> unitList;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String analysis(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		List<AnalysisVO> resultList = analysisService.selectAnalysis();

		String date = Utils.getLastYear();

		Map<String, Object> usrChart = analysisService.selectUsrChart(date);
		Map<String, Object> loginChart = analysisService.selectLonginChart(date);
		
		List<Map<String, Object>> rsvList = analysisService.selectCmsList();

		model.addAttribute("usrChart", usrChart);
		model.addAttribute("loginChart", loginChart);
		model.addAttribute("rsvList", rsvList);
		model.addAttribute("resultList", resultList);
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "analysis/analysis";
	}

	@RequestMapping(value = "/detail")
	public String detail(@ModelAttribute("paging") Paging paging, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		if(storeList == null)
			storeList = analysisService.selectRsvStore();
		
		if(unitList == null)
			unitList = analysisService.selectRsvUnit();
		
		paging.setPageSize(40);
		List<Map<String, Object>> rsvList = analysisService.selectAnalysisDetail(paging);
		int totalCnt = analysisService.selectAnalysisDetailCnt(paging);
		paging.setTotalCount(totalCnt);

		model.addAttribute("rsvList", rsvList);
		model.addAttribute("paging", paging);
		model.addAttribute("unitList", unitList);
		model.addAttribute("storeList", storeList);
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "analysis/detail";
	}

}
