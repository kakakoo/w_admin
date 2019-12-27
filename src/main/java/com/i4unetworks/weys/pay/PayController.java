package com.i4unetworks.weys.pay;

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
@RequestMapping("/api/pay")
public class PayController {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	private static final String MOTHER_PAGE = "/api/pay";
	@Autowired
	private PayService payService;
	
	/*
	 * 사용자 멤버십 결제 내역
	 */
	@RequestMapping(value = "")
	public String main(@ModelAttribute("paging") Paging paging, HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		List<PayListVO> resultList = payService.selectPayList(paging);
		int totalCnt = payService.selectPayListCnt(paging);
		paging.setTotalCount(totalCnt);

		model.addAttribute("resultList", resultList);
		model.addAttribute("paging", paging);
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "pay/pay";
	}

	/**
	 * 멤버십 결제 취소 확인
	 */
	@ResponseBody
	@RequestMapping(value = "/cancelCheck", method = RequestMethod.POST)
	public Map<String, Object> cancelCheck(HttpServletRequest req, HttpServletResponse res
			, @RequestBody Map<String, Object> reqMap) throws Exception {

		logger.info("===================================== START ===================================");
		logger.info("url ::: " + req.getRequestURL());
		logger.info("req ::: " + reqMap.toString());
		
		Map<String, Object> resultMap = payService.cancelCheck(reqMap);
		
		logger.info("result ::: " + resultMap.toString());
		logger.info("===================================== END =====================================");
		return resultMap;
	}

	/**
	 * 멤버십 결제 취소 정보 등록
	 */
	@ResponseBody
	@RequestMapping(value = "/cancelMember", method = RequestMethod.POST)
	public Map<String, Object> cancelMember(HttpServletRequest req, HttpServletResponse res
			, @RequestBody Map<String, Object> reqMap) throws Exception {

		logger.info("===================================== START ===================================");
		logger.info("url ::: " + req.getRequestURL());
		logger.info("req ::: " + reqMap.toString());
		
		Map<String, Object> resultMap = payService.insertMemberCancel(reqMap);
		
		logger.info("result ::: " + resultMap.toString());
		logger.info("===================================== END =====================================");
		return resultMap;
	}

	/**
	 * 멤버십 환불 완료
	 */
	@ResponseBody
	@RequestMapping(value = "/returnComplete", method = RequestMethod.POST)
	public Map<String, Object> returnComplete(HttpServletRequest req, HttpServletResponse res
			, @RequestBody Map<String, Object> reqMap) throws Exception {

		logger.info("===================================== START ===================================");
		logger.info("url ::: " + req.getRequestURL());
		logger.info("req ::: " + reqMap.toString());
		
		Map<String, Object> resultMap = payService.updateReturnComplete(reqMap);
		
		logger.info("result ::: " + resultMap.toString());
		logger.info("===================================== END =====================================");
		return resultMap;
	}

}
