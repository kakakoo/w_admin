package com.i4unetworks.weys.store;

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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.i4unetworks.weys.common.ExcelConstants;
import com.i4unetworks.weys.common.Paging;
import com.i4unetworks.weys.common.UserVO;
import com.i4unetworks.weys.common.Utils;

@Controller
@RequestMapping("/api/store")
public class StoreController {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	private static final String MOTHER_PAGE = "/api/store";
	@Autowired
	private StoreService storeService;

	/*
	 * 지점 리스트 
	 */
	@RequestMapping(value = "")
	public String main(@ModelAttribute("paging") Paging paging, HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		List<StoreListVO> resultList = storeService.getStoreList(paging);
		int totalCnt = storeService.getStoreListCnt();
		paging.setTotalCount(totalCnt);

		model.addAttribute("resultList", resultList);
		model.addAttribute("paging", paging);
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "store/store";
	}

	/*
	 * 지점 등록 및 수정 화면   
	 */
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String writePage(HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		String storeId = request.getParameter("id");
		
		if(storeId != null){
			StoreDetailVO detail = storeService.selectDetail(storeId);
			List<Map<String, Object>> openList = storeService.selectOpenDay(storeId);
			model.addAttribute("info", detail);
			model.addAttribute("openList", openList);
			model.addAttribute("result", "2");
		} else {
			model.addAttribute("result", "0");
		}
		
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "store/write";
	}

	/*
	 * 지점 등록  
	 */
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(HttpServletRequest request, HttpServletResponse response, @ModelAttribute StoreDetailVO storeDetailVO, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		int res = storeService.insertStore(storeDetailVO);
		
		if(res > 0){
			return "redirect:/api/store";
		}
		model.addAttribute("result", "1");
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "store/write";
	}

	/*
	 * 지점 수정 
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(HttpServletRequest request, HttpServletResponse response, @ModelAttribute StoreDetailVO storeDetailVO, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		logger.info("req ::: " + storeDetailVO.toString());
		int res = storeService.updateStore(storeDetailVO);
		
		if(res > 0){
			return "redirect:/api/store";
		}
		model.addAttribute("result", "1");
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "store/write";
	}

	/*
	 * 지점 삭제 
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(HttpServletRequest request, HttpServletResponse response, @ModelAttribute StoreDetailVO storeDetailVO, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		int res = storeService.deleteStore(storeDetailVO.getStoreId());
		
		if(res > 0){
			return "redirect:/api/store";
		}
		model.addAttribute("result", "1");
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "store/write";
	}

	/*
	 * 지점 상태 변경
	 */
	@ResponseBody
	@RequestMapping(value = "/changeStat", method=RequestMethod.POST)
	public Map<String, Object> changeStatus(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String, Object> reqMap) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		
		int resCnt = storeService.updateStatus(reqMap);
		
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
	 * 지점 담당자 관리 
	 */
	@RequestMapping(value = "/staff")
	public String staff(@ModelAttribute("paging") Paging paging, HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		List<StoreStaffVO> resultList = storeService.selectStoreStaff(paging);
		int totalCnt = storeService.selectStoreStaffCnt();
		paging.setTotalCount(totalCnt);

		model.addAttribute("resultList", resultList);
		model.addAttribute("paging", paging);
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "store/staff";
	}

	/*
	 * 지점 담당자 관리 
	 */
	@RequestMapping(value = "/staff/write", method = RequestMethod.GET)
	public String staffWrite(HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		String adminKey = request.getParameter("id");
		
		if(adminKey != null){
			StoreStaffVO info = storeService.selectAdminInfo(adminKey);
			model.addAttribute("info", info);
			model.addAttribute("result", "2");
		} else {
			model.addAttribute("result", "0");
		}
		List<StoreListVO> storeList = storeService.selectStoreAllList();
		
		model.addAttribute("storeList", storeList);
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "store/s_write";
	}

	/*
	 * 지점 담당자 추가 
	 */
	@RequestMapping(value = "/staff/write", method = RequestMethod.POST)
	public String insertStaff(@ModelAttribute StoreStaffVO staffVO, HttpServletRequest request, HttpServletResponse response
			, Model model) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());

		UserVO userVO = (UserVO) request.getSession().getAttribute("login");
		int res = storeService.insertStaff(staffVO, userVO);
		if(res > 0){
			logger.info("===================================== END =====================================");
			return "redirect:/api/store/staff";
		}
		logger.info("===================================== END =====================================");
		return "store/staff";
	}

	/*
	 * 지점 담당자 삭제 
	 */
	@RequestMapping(value = "/staff/delete", method=RequestMethod.POST)
	public String deleteStaff(HttpServletRequest req, HttpServletResponse res, @ModelAttribute StoreStaffVO staffVO) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		
		int resCnt = storeService.deleteStaff(staffVO.getAdminKey());
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(resCnt > 0){
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}

		logger.info("===================================== END =====================================");
		return "redirect:/api/store/staff";
	}

	/*
	 * 지점 담당자 정보 업데이트 
	 */
	@RequestMapping(value = "/staff/update", method=RequestMethod.POST)
	public String updateStaff(HttpServletRequest req, HttpServletResponse res, @ModelAttribute StoreStaffVO staffVO) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		logger.info("req ::: " + staffVO.toString());

		UserVO userVO = (UserVO) req.getSession().getAttribute("login");
		int resCnt = storeService.updateStaff(staffVO, userVO);
		if(resCnt > 0){
			logger.info("===================================== END =====================================");
			return "redirect:/api/store/staff";
		}
		logger.info("===================================== END =====================================");
		return "store/staff";
	}
	

	/*
	 * 지점 화폐 관리 
	 */
	@RequestMapping(value = "/{storeId}/coin", method = RequestMethod.GET)
	public String coin(@PathVariable String storeId, HttpServletRequest request
			, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		String storeNm = storeService.selectStoreNm(storeId);
		List<Map<String, Object>> resultList = storeService.selectStoreCoin(storeId);

		model.addAttribute("storeId", storeId);
		model.addAttribute("storeNm", storeNm);
		model.addAttribute("resultList", resultList);
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "store/coin";
	}

	/*
	 * 지점 멤버십 상세 리스트 
	 */
	@RequestMapping(value = "/{storeId}/member")
	public String member(@ModelAttribute("paging") Paging paging, @PathVariable String storeId, HttpServletRequest request
			, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		paging.setStoreId(storeId);
		String storeNm = storeService.selectStoreNm(storeId);
		List<StoreChangeVO> resultList = storeService.selectStoreMember(paging);
		int totalCnt = storeService.selectStoreMemberCnt(paging);
		paging.setTotalCount(totalCnt);

		model.addAttribute("paging", paging);
		model.addAttribute("storeId", storeId);
		model.addAttribute("storeNm", storeNm);
		model.addAttribute("resultList", resultList);
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "store/member";
	}

	/*
	 * 지점 포인트 상세 리스트 
	 */
	@RequestMapping(value = "/{storeId}/point")
	public String point(@ModelAttribute("paging") Paging paging, @PathVariable String storeId, HttpServletRequest request
			, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		paging.setStoreId(storeId);
		String storeNm = storeService.selectStoreNm(storeId);
		List<StoreChangeVO> resultList = storeService.selectStorePoint(paging);
		int totalCnt = storeService.selectStorePointCnt(paging);
		paging.setTotalCount(totalCnt);

		model.addAttribute("paging", paging);
		model.addAttribute("storeId", storeId);
		model.addAttribute("storeNm", storeNm);
		model.addAttribute("resultList", resultList);
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "store/point";
	}

	/*
	 * 거래내역 상세 엑셀
	 */
	@RequestMapping(value = "/{storeId}/excel")
	public String detailExcel(@ModelAttribute("paging") Paging paging, @PathVariable String storeId, HttpServletRequest request
			, HttpServletResponse response, ModelMap model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		paging.setStoreId(storeId);
		paging.setIsExcel("Y");
		List<StoreChangeVO> memberList = storeService.selectStoreMember(paging);
		List<StoreChangeVO> pointList = storeService.selectStorePoint(paging);

		model.put("pointList", pointList);
		model.put("memberList", memberList);
		model.put("type", ExcelConstants.STORE_DETAIL);

		logger.info("===================================== END =====================================");
		// WEB_INF/mvc-config.xml에 BeanNameViewResolver를 등록했기 때문에 kr.co.medisolution.common.ExelView가 열림.
		return "excelView";
	}
	

	/*
	 * 지점 리스트 가져오기
	 */
	@ResponseBody
	@RequestMapping(value = "/storeList", method=RequestMethod.POST)
	public Map<String, Object> storeList(HttpServletRequest req, HttpServletResponse res) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		
		List<Map<String, Object>> resultList = storeService.selectStoreList();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("list", resultList);

		logger.info("===================================== END =====================================");
		return resultMap;
	}
}
