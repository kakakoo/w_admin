package com.i4unetworks.weys.money;

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
import com.i4unetworks.weys.common.UserVO;
import com.i4unetworks.weys.common.Utils;

@Controller
@RequestMapping("/api/money")
public class MoneyController {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	private static final String MOTHER_PAGE = "/api/gold";
	@Autowired
	private MoneyService moneyService;

	@RequestMapping(value = "")
	public String main(@ModelAttribute("paging") Paging paging, HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());

		paging.setPageSize(20);
		List<MoneyListVO> resultList = moneyService.selectMoneyList(paging);
		int totalCnt = moneyService.selectMoneyListCnt(paging);
		paging.setTotalCount(totalCnt);
		
		model.addAttribute("resultList", resultList);
		model.addAttribute("paging", paging);
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "money/money";
	}

	/**
	 * 가상계좌 정보 확인
	 * @param req
	 * @param res
	 * @param reqMap
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/addUnit", method=RequestMethod.POST)
	public Map<String, Object> addUnit(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String, Object> reqMap) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		logger.info("req ::: " + reqMap.toString());
		
		UserVO userVO = (UserVO) req.getSession().getAttribute("login");
		
		int resCnt= moneyService.insertMoneyMng(reqMap, userVO);

		Map<String, Object> resultMap = new HashMap<>();
		if(resCnt > 0){
			resultMap.put("res", "suc");
		} else {
			resultMap.put("res", "fail");
		}

		logger.info("===================================== END =====================================");
		return resultMap;
	}

}
