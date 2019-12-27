package com.i4unetworks.weys.paygate;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.i4unetworks.weys.common.Utils;

@Controller
public class PaygateController {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private PaygateService paygateService;
	
	/**
	 * 입금완료
	 * @param req
	 * @param res
	 * @param reqMap
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/pg/income/get")
	public String incomeGet(HttpServletRequest req, HttpServletResponse res, Map<String, Object> reqMap) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		
		int resCnt = paygateService.updateRsvIncome(req);

		String sResult = "FAIL";
		if(resCnt > 0){
			sResult = "SUCCESS";
		}
		logger.info("===================================== END =====================================");
		return sResult;
	}
}
