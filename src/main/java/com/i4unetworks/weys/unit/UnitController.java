package com.i4unetworks.weys.unit;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.i4unetworks.weys.common.Utils;

@Controller
@RequestMapping("/api/unit")
public class UnitController {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	private static final String MOTHER_PAGE = "/api/unit";
	@Autowired
	private UnitService unitService;

	/*
	 * 멤버십 통화 관리
	 */
	@RequestMapping(value = "")
	public String main(HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		List<UnitListVO> resultList = unitService.getStoreUnitList();

		model.addAttribute("resultList", resultList);
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "unit/unit";
	}
	
	/*
	 * 외화값 업데이트
	 */
	@ResponseBody
	@RequestMapping(value = "/update", method=RequestMethod.POST)
	public Map<String, Object> updateStoreUnit(HttpServletRequest req, HttpServletResponse res, @RequestBody UnitListVO reqVO) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		logger.info("req ::: " + reqVO.toString());
		
		int resCnt = unitService.updateStoreUnit(reqVO);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(resCnt > 0){
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}

		logger.info("===================================== END =====================================");
		return resultMap;
	}

	/*
	 * 멤버십 통화 관리
	 */
	@RequestMapping(value = "/rsv")
	public String rsv(HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		List<Map<String, Object>> resultList = unitService.selectRsvStore();

		model.addAttribute("resultList", resultList);
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "unit/rsv";
	}

	/*
	 * 지점 예약 현황
	 */
	@ResponseBody
	@RequestMapping(value = "/updateRsvUnit", method=RequestMethod.POST)
	public Map<String, Object> updateRsvUnit(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String, Object> reqMap) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		logger.info("req ::: " + reqMap.toString());
		
		int resCnt = unitService.updateStoreRsvUnit(reqMap);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(resCnt > 0){
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}

		logger.info("===================================== END =====================================");
		return resultMap;
	}
	
}
