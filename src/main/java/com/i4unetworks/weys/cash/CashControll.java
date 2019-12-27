package com.i4unetworks.weys.cash;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.i4unetworks.weys.common.Paging;
import com.i4unetworks.weys.common.Utils;

@Controller
@RequestMapping("/api/cash")
public class CashControll {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	private static final String MOTHER_PAGE = "/api/cash";
	@Autowired
	private CashService cashService;

	/*
	 * 현금 영수증 리스트
	 */
	@RequestMapping(value = "")
	public String list(@ModelAttribute("paging") Paging paging, HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		List<CashVO> resultList = cashService.selectCashList(paging);
		int totalCnt = cashService.selectCashListCnt(paging);
		paging.setTotalCount(totalCnt);

		model.addAttribute("resultList", resultList);
		model.addAttribute("paging", paging);
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "cash/cash";
	}
	
	/*
	 * 현금영수증 등록
	 */
	@ResponseBody
	@RequestMapping(value = "/registerIssue", method=RequestMethod.POST)
	public Map<String, Object> register(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String, Object> reqMap) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		logger.info("req ::: " + reqMap.toString());
		
		String msg = cashService.updateCashRegisterIssue(reqMap);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", msg);
		logger.info("===================================== END =====================================");
		return resultMap;
	}
	
}
