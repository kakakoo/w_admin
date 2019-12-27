package com.i4unetworks.weys.trade;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.i4unetworks.weys.common.ExcelConstants;
import com.i4unetworks.weys.common.Paging;
import com.i4unetworks.weys.common.Utils;

@Controller
@RequestMapping("/api")
public class TradeController {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	private static final String MOTHER_PAGE = "/api/trade";
	@Autowired
	private TradeService tradeService;
	
	@RequestMapping(value = "/trade")
	public String main(@ModelAttribute("paging") Paging paging, HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		List<TradeDetailVO> resultList = tradeService.getTradeList(paging);
		int totalCnt = tradeService.getTradeListCnt(paging);
		paging.setTotalCount(totalCnt);

		model.addAttribute("resultList", resultList);
		model.addAttribute("paging", paging);
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "trade/trade";
	}
	

	@RequestMapping(value = "/trade/excel")
	public String excel(@ModelAttribute("paging") Paging paging, HttpServletRequest request
			, HttpServletResponse response, ModelMap model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		List<TradeDetailVO> excelList = tradeService.selectTradeExcel(paging);
		
		model.put("excelList", excelList);
		model.put("type", ExcelConstants.TRADE);

		logger.info("===================================== END =====================================");
		// WEB_INF/mvc-config.xml에 BeanNameViewResolver를 등록했기 때문에 kr.co.medisolution.common.ExelView가 열림.
		return "excelView";
	}
}
