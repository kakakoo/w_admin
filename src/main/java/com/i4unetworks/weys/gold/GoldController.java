package com.i4unetworks.weys.gold;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
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
import com.i4unetworks.weys.store.StoreListVO;

@Controller
@RequestMapping("/api/gold")
public class GoldController {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	private static final String MOTHER_PAGE = "/api/gold";
	@Autowired
	private GoldService goldService;
	
	/**
	 * 담당자 시제 현황 화면
	 * @param paging
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "")
	public String goldMain(HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		UserVO userVO = (UserVO) request.getSession().getAttribute("login");
		
		List<StoreListVO> storeList = goldService.selectStoreList(userVO.getAdminKey());

		model.addAttribute("storeList", storeList);
		model.addAttribute("adminTp", userVO.getAdminTp());
		model.addAttribute("dt", Utils.getDiffDate(0));
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "gold/gold";
	}

	/**
	 * 담당자 시제관리 화면
	 * @param paging
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/m")
	public String goldManage(@ModelAttribute("reqVO") GoldVO reqVO, HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		logger.info("req ::: " + reqVO.getStoreId());
		
		List<String> dtList = new ArrayList<>();
		for(int i=-1 ; i<2 ; i++){
			String dt = Utils.getDiffDate(i);
			dtList.add(dt);
		}

		UserVO userVO = (UserVO) request.getSession().getAttribute("login");

		model.addAttribute("storeId", reqVO.getStoreId());
		model.addAttribute("adminTp", userVO.getAdminTp());
		model.addAttribute("dtList", dtList);
		model.addAttribute("dt", Utils.getDiffDate(0));
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "gold/m_gold";
	}

	/**
	 * 담당자 시제관리 화면
	 * @param paging
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/m/w")
	public String managerWrite(@ModelAttribute("reqVO") GoldVO reqVO, HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		logger.info("req ::: " + reqVO.toString());
		
		Map<String, Object> reqMap = new HashMap<>();
		reqMap.put("storeId", reqVO.getStoreId());
		List<GoldVO> goldList = goldService.selectStoreGold(reqMap);
		
		String target = "배송 금고";
		if(reqVO.getStoreId() == 5)
			target = "본사 금고";
		
		UserVO userVO = (UserVO) request.getSession().getAttribute("login");

		model.addAttribute("target", target);
		model.addAttribute("adminTp", userVO.getAdminTp());
		model.addAttribute("goldList", goldList);
		model.addAttribute("dt", reqVO.getRsvDt());
		model.addAttribute("storeId", reqVO.getStoreId());
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "gold/m_gold_w";
	}

	/**
	 * 해당 날짜 예약 정보 불러오기
	 * @param req
	 * @param res
	 * @param reqMap
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getStoreGold", method=RequestMethod.POST)
	public Map<String, Object> getStoreGold(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String, Object> reqMap) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		
		UserVO userVO = (UserVO) req.getSession().getAttribute("login");
		
		reqMap.put("adminKey", userVO.getAdminKey());
		List<GoldVO> goldList = goldService.selectStoreGold(reqMap);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("goldList", goldList);
		
		logger.info("===================================== END =====================================");
		return resultMap;
	}

	/**
	 * 해당 날짜 예약 정보 불러오기
	 * @param req
	 * @param res
	 * @param reqMap
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getRsvInfo", method=RequestMethod.POST)
	public Map<String, Object> getRsvInfo(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String, Object> reqMap) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		
		UserVO userVO = (UserVO) req.getSession().getAttribute("login");
		
		reqMap.put("adminKey", userVO.getAdminKey());
		Map<String, Object> resultMap = goldService.selectRsvInfo(reqMap);
		logger.info("===================================== END =====================================");
		return resultMap;
	}
	
	/**
	 * 시제 등록하기
	 * @param req
	 * @param res
	 * @param reqMap
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/m/write", method=RequestMethod.POST)
	public Map<String, Object> updateGold(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String, Object> reqMap) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		logger.info("req ::: " + reqMap.toString());
		
		UserVO userVO = (UserVO) req.getSession().getAttribute("login");
		reqMap.put("adminKey", userVO.getAdminKey());
		
		Map<String, Object> resultMap = new HashMap<>();
		
		int resCnt = goldService.insertGoldLog(reqMap);
		if(resCnt > 0){
			String goldTp = MapUtils.getString(reqMap, "goldTp");
			if(goldTp.equals("M")){
				int storeId = MapUtils.getIntValue(reqMap, "storeId");
				int targetId = 1;
				if(storeId == 1){
					targetId = 5;
				}
				reqMap.put("storeId", targetId);
				reqMap.put("move", -1);
				
				resCnt = goldService.insertGoldLog(reqMap);
			}
			
			
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		logger.info("===================================== END =====================================");
		return resultMap;
	}

	/**
	 * 매일 시제 현황
	 * @param paging
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/krw")
	public String getKrw(@ModelAttribute("paging") Paging paging, HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		UserVO userVO = (UserVO) request.getSession().getAttribute("login");
		
		if(!userVO.getAdminTp().equals("S")){
			return "redirect:/api/gold";
		}
		
		List<Map<String, Object>> cashList = goldService.selectCashList(paging);

		model.addAttribute("cashList", cashList);
		model.addAttribute("adminTp", userVO.getAdminTp());
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "gold/cash";
	}

}
