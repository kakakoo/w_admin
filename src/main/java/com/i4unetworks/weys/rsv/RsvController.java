package com.i4unetworks.weys.rsv;

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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.i4unetworks.weys.common.ExcelConstants;
import com.i4unetworks.weys.common.Paging;
import com.i4unetworks.weys.common.UserVO;
import com.i4unetworks.weys.common.Utils;

@Controller
@RequestMapping("/api/rsv")
public class RsvController {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	private static final String MOTHER_PAGE = "/api/rsv";
	@Autowired
	private RsvService rsvService;

	private List<Map<String, Object>> storeList;
	private List<Map<String, Object>> unitList;

	@RequestMapping(value = "/list")
	public String main(@ModelAttribute("paging") Paging paging, HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());

		paging.setPageSize(20);
		List<RsvListVO> resultList = rsvService.getRsvList(paging);
		int totalCnt = rsvService.getRsvListCnt(paging);
		paging.setTotalCount(totalCnt);
		
		if(storeList == null)
			storeList = rsvService.selectStoreList();
		
		if(unitList == null)
			unitList = rsvService.selectRsvUnitList();

		UserVO userVO = (UserVO) request.getSession().getAttribute("login");
		
		model.addAttribute("adminTp", userVO.getAdminTp());
		model.addAttribute("resultList", resultList);
		model.addAttribute("paging", paging);
		model.addAttribute("excelDt", Utils.getTodayDate("yyyy.MM.dd"));
		model.addAttribute("storeList", storeList);
		model.addAttribute("unitList", unitList);
		model.addAttribute("motherPage", MOTHER_PAGE + "/list");
		logger.info("===================================== END =====================================");
		return "rsv/rsv";
	}

	@RequestMapping(value = "/list/m")
	public String mobileList(@ModelAttribute("paging") Paging paging, HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		paging.setRsvStVal("Y");
		paging.setRsvStVal("S,R,F");
		paging.setPageSize(40);
		List<RsvListVO> resultList = rsvService.getRsvList(paging);
		int totalCnt = rsvService.getRsvListCnt(paging);
		paging.setTotalCount(totalCnt);
		
		if(storeList == null)
			storeList = rsvService.selectStoreList();

		UserVO userVO = (UserVO) request.getSession().getAttribute("login");

		model.addAttribute("adminTp", userVO.getAdminTp());
		model.addAttribute("resultList", resultList);
		model.addAttribute("paging", paging);
		model.addAttribute("excelDt", Utils.getTodayDate("yyyy.MM.dd"));
		model.addAttribute("storeList", storeList);
		model.addAttribute("motherPage", MOTHER_PAGE + "/list");
		logger.info("===================================== END =====================================");
		return "rsv/rsv_m";
	}

	@RequestMapping(value = "/list/pt")
	public String managerList(@ModelAttribute("paging") Paging paging, HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		paging.setPageSize(40);
		List<RsvListVO> resultList = rsvService.getRsvList(paging);
		int totalCnt = rsvService.getRsvListCnt(paging);
		paging.setTotalCount(totalCnt);

		if(storeList == null)
			storeList = rsvService.selectStoreList();


		UserVO userVO = (UserVO) request.getSession().getAttribute("login");

		model.addAttribute("adminTp", userVO.getAdminTp());
		model.addAttribute("resultList", resultList);
		model.addAttribute("paging", paging);
		model.addAttribute("excelDt", Utils.getTodayDate("yyyy.MM.dd"));
		model.addAttribute("storeList", storeList);
		model.addAttribute("motherPage", MOTHER_PAGE + "/list/pt");
		logger.info("===================================== END =====================================");
		return "pt/rsv";
	}

	@RequestMapping(value = "/cancel")
	public String cancel(@ModelAttribute("paging") Paging paging, HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		List<RsvListVO> resultList = rsvService.getRsvCancelList(paging);
		int totalCnt = rsvService.getRsvListCancelCnt(paging);
		paging.setTotalCount(totalCnt);
		
		UserVO userVO = (UserVO) request.getSession().getAttribute("login");

		model.addAttribute("adminTp", userVO.getAdminTp());
		model.addAttribute("resultList", resultList);
		model.addAttribute("paging", paging);
		model.addAttribute("motherPage", MOTHER_PAGE + "/cancel");
		logger.info("===================================== END =====================================");
		return "rsv/rsv_cancel";
	}

	/**
	 * 예약 통화 변경 수정
	 * @param req
	 * @param res
	 * @param reqMap
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/cancelUpdate", method=RequestMethod.POST)
	public Map<String, Object> cancelUpdate(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String, Object> reqMap) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		logger.info("req ::: " + reqMap.toString());
		
		UserVO userVO = (UserVO) req.getSession().getAttribute("login");
		int rsvId = MapUtils.getIntValue(reqMap, "rsvId");
		
		int resCnt = rsvService.updateRsvCancel(userVO.getAdminKey(), rsvId);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(resCnt > 0){
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		logger.info("===================================== END =====================================");
		return resultMap;
	}

	@RequestMapping(value = "/unit")
	public String unit(HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		List<RsvUnitVO> resultList = rsvService.getRsvUnitList();
		
		model.addAttribute("resultList", resultList);
		model.addAttribute("motherPage", "/api/unit");
		logger.info("===================================== END =====================================");
		return "rsv/unit";
	}

	/**
	 * 예약 통화 변경 수정
	 * @param req
	 * @param res
	 * @param reqMap
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/updateRsvUnit", method=RequestMethod.POST)
	public Map<String, Object> updateRsvUnit(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String, Object> reqMap) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		logger.info("req ::: " + reqMap.toString());
		
		UserVO userVO = (UserVO) req.getSession().getAttribute("login");
		Map<String, Object> resultMap = new HashMap<String, Object>();

		int resCnt = rsvService.updateRsvUnit(reqMap, userVO);
		if(resCnt > 0){
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		logger.info("===================================== END =====================================");
		return resultMap;
	}

	/**
	 * 지점 변경 정보
	 * @param req
	 * @param res
	 * @param reqMap
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/updateRsvStore", method=RequestMethod.POST)
	public Map<String, Object> updateRsvStore(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String, Object> reqMap) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		logger.info("req ::: " + reqMap.toString());
		
		UserVO userVO = (UserVO) req.getSession().getAttribute("login");
		Map<String, Object> resultMap = new HashMap<String, Object>();

		int resCnt = rsvService.updateRsvStore(reqMap, userVO);
		if(resCnt > 0){
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		logger.info("===================================== END =====================================");
		return resultMap;
	}
	
	/**
	 * 지점 변경 정보
	 * @param req
	 * @param res
	 * @param reqMap
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getRsvStoreInfo", method=RequestMethod.POST)
	public Map<String, Object> getRsvStoreInfo(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String, Object> reqMap) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		logger.info("req ::: " + reqMap.toString());
		
		Map<String, Object> resultMap = rsvService.getRsvStoreInfo(reqMap);

		logger.info("===================================== END =====================================");
		return resultMap;
	}


	@RequestMapping(value = "/mng")
	public String mng(@ModelAttribute("paging") Paging paging, HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());

		List<String> list = new ArrayList<>();
		list.add("I");
		list.add("R");
		paging.setListData(list);
		paging.setRsvStVal("");
		
		List<RsvListVO> resultList = rsvService.getRsvListMng(paging);
		int totalCnt = rsvService.getRsvListCnt(paging);
		paging.setTotalCount(totalCnt);
		
		if(storeList == null)
			storeList = rsvService.selectStoreList();

		model.addAttribute("resultList", resultList);
		model.addAttribute("paging", paging);
		model.addAttribute("storeList", storeList);
		model.addAttribute("motherPage", MOTHER_PAGE + "/list");
		logger.info("===================================== END =====================================");
		return "rsv/mng";
	}
	

	/**
	 * 예약 통화 변경 수정
	 * @param req
	 * @param res
	 * @param reqMap
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getStoreInfo", method=RequestMethod.POST)
	public Map<String, Object> getStoreInfo(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String, Object> reqMap) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		logger.info("req ::: " + reqMap.toString());
		
		Map<String, Object> resultMap = rsvService.getStoreInfo(reqMap);

		logger.info("result ::: " + resultMap.toString());
		logger.info("===================================== END =====================================");
		return resultMap;
	}

	/**
	 * 예약 통화 변경 수정
	 * @param req
	 * @param res
	 * @param reqMap
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/setMng", method=RequestMethod.POST)
	public Map<String, Object> setMng(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String, Object> reqMap) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		logger.info("req ::: " + reqMap.toString());
		
		int resCnt = rsvService.updateRsvMng(reqMap);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(resCnt > 0){
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		logger.info("result ::: " + resultMap.toString());
		logger.info("===================================== END =====================================");
		return resultMap;
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
	@RequestMapping(value = "/checkVb", method=RequestMethod.POST)
	public Map<String, Object> checkVb(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String, Object> reqMap) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		
		UserVO userVO = (UserVO) req.getSession().getAttribute("login");
		Map<String, Object> resultMap = rsvService.checkVb(reqMap, userVO);

		logger.info("===================================== END =====================================");
		return resultMap;
	}

	/**
	 * 가상계좌 입금처리
	 * @param req
	 * @param res
	 * @param reqMap
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/updateIncome", method=RequestMethod.POST)
	public Map<String, Object> updateIncome(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String, Object> reqMap) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		
		Map<String, Object> resultMap = new HashMap<>();
		int resCnt = rsvService.updateIncome(reqMap);
		
		if(resCnt > 0){
			resultMap.put("res", "suc");
		} else {
			resultMap.put("res", "fail");
		}

		logger.info("===================================== END =====================================");
		return resultMap;
	}

	/**
	 * 시간초과 예약 복구
	 * @param req
	 * @param res
	 * @param reqMap
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/backRsv", method=RequestMethod.POST)
	public Map<String, Object> backRsv(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String, Object> reqMap) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		
		Map<String, Object> resultMap = new HashMap<>();
		int resCnt = rsvService.updateRsvBack(reqMap);
		
		if(resCnt > 0){
			resultMap.put("res", "suc");
		} else {
			resultMap.put("res", "fail");
		}

		logger.info("===================================== END =====================================");
		return resultMap;
	}

	/**
	 * 예약 준비 완료 처리
	 * @param req
	 * @param res
	 * @param reqMap
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/updateReady", method=RequestMethod.POST)
	public Map<String, Object> updateReady(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String, Object> reqMap) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		logger.info("req ::: " + reqMap.toString());
		
		Map<String, Object> resultMap = new HashMap<>();
		int resCnt = rsvService.updateReady(reqMap);
		
		if(resCnt > 0){
			resultMap.put("res", "suc");
		} else {
			resultMap.put("res", "fail");
		}

		logger.info("===================================== END =====================================");
		return resultMap;
	}

	/**
	 * 입금시간 초과 처리
	 * @param req
	 * @param res
	 * @param reqMap
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/updateMiss", method=RequestMethod.POST)
	public Map<String, Object> updateMiss(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String, Object> reqMap) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		
		Map<String, Object> resultMap = new HashMap<>();
		int resCnt = rsvService.updateMiss(reqMap);
		
		if(resCnt > 0){
			resultMap.put("res", "suc");
		} else {
			resultMap.put("res", "fail");
		}

		logger.info("===================================== END =====================================");
		return resultMap;
	}
	
	/**
	 * 예약정보 불일치 알림톡 전송
	 * @param req
	 * @param res
	 * @param reqMap
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/missMatch", method=RequestMethod.POST)
	public Map<String, Object> missMatch(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String, Object> reqMap) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		
		Map<String, Object> resultMap = new HashMap<>();

		UserVO userVO = (UserVO) req.getSession().getAttribute("login");
		
		reqMap.put("adminKey", userVO.getAdminKey());
		int resCnt = rsvService.updateSendMissMatch(reqMap);
		
		if(resCnt > 0){
			resultMap.put("res", "suc");
		} else if(resCnt == -1){
			resultMap.put("res", "sent");
		} else {
		
			resultMap.put("res", "fail");
		}

		logger.info("===================================== END =====================================");
		return resultMap;
	}

	/**
	 * 예약일 변경
	 * @param req
	 * @param res
	 * @param reqMap
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/updateRsvDt", method=RequestMethod.POST)
	public Map<String, Object> updateRsvDt(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String, Object> reqMap) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		
		Map<String, Object> resultMap = new HashMap<>();
		int resCnt = rsvService.updateRsvDt(reqMap);
		
		if(resCnt > 0){
			resultMap.put("res", "suc");
		} else {
			resultMap.put("res", "fail");
		}

		logger.info("===================================== END =====================================");
		return resultMap;
	}
	
	/**
	 * 예약건별 메모 리스트
	 * @param req
	 * @param res
	 * @param reqMap
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/selectRsvMemo", method=RequestMethod.POST)
	public Map<String, Object> selectRsvMemo(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String, Object> reqMap) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		
		Map<String, Object> resultMap = new HashMap<>();
		List<Map<String, Object>> resultList = rsvService.selectRsvMemo(reqMap);
		
		resultMap.put("resultList", resultList);

		logger.info("===================================== END =====================================");
		return resultMap;
	}
	
	/**
	 * 예약건별 메모 등록
	 * @param req
	 * @param res
	 * @param reqMap
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/insertRsvMemo", method=RequestMethod.POST)
	public Map<String, Object> insertRsvMemo(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String, Object> reqMap) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		
		UserVO userVO = (UserVO) req.getSession().getAttribute("login");
		
		Map<String, Object> resultMap = new HashMap<>();
		reqMap.put("admin", userVO.getAdminName());
		int resCnt = rsvService.insertRsvMemo(reqMap);
		
		if(resCnt > 0){
			resultMap.put("res", "suc");
		} else {
			resultMap.put("res", "fail");
		}

		logger.info("===================================== END =====================================");
		return resultMap;
	}

	/**
	 * 예약 그룹 수 확인
	 * @param req
	 * @param res
	 * @param reqMap
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/selectGrpCnt", method=RequestMethod.POST)
	public Map<String, Object> selectGrpCnt(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String, Object> reqMap) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		
		int resCnt = rsvService.selectGrpCnt(reqMap);
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("resCnt", resCnt);

		logger.info("===================================== END =====================================");
		return resultMap;
	}

	/**
	 * 지점 그룹 생성하기
	 * @param req
	 * @param res
	 * @param reqMap
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/makeGroup", method=RequestMethod.POST)
	public Map<String, Object> grpPdf(HttpServletRequest req, HttpServletResponse res, @RequestBody GroupVO groupVO) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		logger.info("req ::: " + groupVO.toString());
		
		UserVO userVO = (UserVO) req.getSession().getAttribute("login");
		groupVO.setAdminKey(Integer.parseInt(userVO.getAdminKey()));
		
		int resCnt = rsvService.updateRsvGroup(groupVO);
		
		Map<String, Object> resultMap = new HashMap<>();
		if(resCnt > 0){
			resultMap.put("res", "suc");
		} else {
			resultMap.put("res", "fail");
		}

		logger.info("ret ::: " + resultMap.toString());
		logger.info("===================================== END =====================================");
		return resultMap;
	}

	@RequestMapping(value = "/dataExcel")
	public String dataExcel(@ModelAttribute("paging") Paging paging, HttpServletRequest request, HttpServletResponse response, ModelMap model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		List<RsvListVO> info = rsvService.selectRsvDataExcel(paging);

		model.put("info", info);
		model.put("type", ExcelConstants.RSV_DATA_LIST);

		logger.info("===================================== END =====================================");
		return "excelView";
	}

	@RequestMapping(value = "/excel")
	public String rsvExcel(@ModelAttribute("paging") Paging paging, HttpServletRequest request
			, HttpServletResponse response, ModelMap model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		logger.info("req ::: " + paging.getExcelType());
		logger.info("req ::: " + paging.getReservation());
		
		List<RsvExcelVO> info = rsvService.selectRsvExcel(paging);

		model.put("info", info);
		model.put("dt", paging.getReservation().replaceAll(".", ""));
		model.put("type", ExcelConstants.RSV_LIST);

		logger.info("===================================== END =====================================");
		return "excelView";
	}
	
	@RequestMapping(value = "/cancelExcel")
	public String cancelExcel(@ModelAttribute("paging") Paging paging, HttpServletRequest request
			, HttpServletResponse response, ModelMap model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		List<RsvListVO> info = rsvService.selectRsvCancelExcel(paging);

		model.put("info", info);
		model.put("dt", paging.getReservation().replaceAll(".", ""));
		model.put("type", ExcelConstants.RSV_CANCEL_LIST);

		logger.info("===================================== END =====================================");
		return "excelView";
	}

	@RequestMapping(value = "/grp")
	public String grpList(@ModelAttribute("paging") Paging paging, HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());

		paging.setPageSize(20);
		List<GroupVO> resultList = rsvService.getGrpList(paging);
		int totalCnt = rsvService.getGrpListCnt(paging);
		paging.setTotalCount(totalCnt);
		
		if(storeList == null)
			storeList = rsvService.selectStoreList();

		UserVO userVO = (UserVO) request.getSession().getAttribute("login");
		
		model.addAttribute("adminTp", userVO.getAdminTp());
		model.addAttribute("resultList", resultList);
		model.addAttribute("paging", paging);
		model.addAttribute("excelDt", Utils.getTodayDate("yyyy.MM.dd"));
		model.addAttribute("storeList", storeList);
		model.addAttribute("motherPage", MOTHER_PAGE + "/grp");
		logger.info("===================================== END =====================================");
		return "rsv/grp";
	}

	/**
	 * pdf 보기
	 * @param req
	 * @param res
	 * @param reqMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/viewPdf")
	public String viewPdf(@ModelAttribute("paging") GroupVO groupVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		UserVO userVO = (UserVO) request.getSession().getAttribute("login");
		groupVO.setAdminKey(Integer.parseInt(userVO.getAdminKey()));
		Map<String, Object> rsvInfo = rsvService.selectGrpPdfView(groupVO);
		
		if(rsvInfo == null){
			return "redirect:/api/rsv/list";
		}

		model.put("fileNm", groupVO.getRsvDt());
		model.put("rsvInfo", rsvInfo);

		logger.info("===================================== END =====================================");
		return "grpPdfView";
	}

	/**
	 * 가상계좌 확인
	 * @param paging
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/vbank")
	public String vbank(@ModelAttribute("paging") Paging paging, HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());

		paging.setPageSize(20);
		List<Map<String, Object>> resultList = rsvService.getVbankList(paging);
		int totalCnt = rsvService.getVbankListCnt(paging);
		paging.setTotalCount(totalCnt);
		
		UserVO userVO = (UserVO) request.getSession().getAttribute("login");
		
		model.addAttribute("adminTp", userVO.getAdminTp());
		model.addAttribute("resultList", resultList);
		model.addAttribute("paging", paging);
		model.addAttribute("excelDt", Utils.getTodayDate("yyyy.MM.dd"));
		model.addAttribute("motherPage", MOTHER_PAGE + "/vbank");
		logger.info("===================================== END =====================================");
		return "rsv/vbank";
	}

	/**
	 * 입금확인처리
	 * @param req
	 * @param res
	 * @param reqMap
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/vbank/chk", method=RequestMethod.POST)
	public Map<String, Object> grpPdf(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String, Object> reqMap) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		logger.info("req ::: " + reqMap.toString());
		
		UserVO userVO = (UserVO) req.getSession().getAttribute("login");
		reqMap.put("adminKey", userVO.getAdminKey());

		int resCnt = rsvService.updateVbankChk(reqMap);
		
		Map<String, Object> resultMap = new HashMap<>();
		if(resCnt > 0){
			resultMap.put("res", "suc");
		} else {
			resultMap.put("res", "fail");
		}

		logger.info("ret ::: " + resultMap.toString());
		logger.info("===================================== END =====================================");
		return resultMap;
	}

	/**
	 * 환불 등록
	 * @param req
	 * @param res
	 * @param reqMap
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/updateReturn", method=RequestMethod.POST)
	public Map<String, Object> updateReturn(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String, Object> reqMap) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		logger.info("req ::: " + reqMap.toString());
		
		UserVO userVO = (UserVO) req.getSession().getAttribute("login");
		reqMap.put("adminKey", userVO.getAdminKey());

		int resCnt = rsvService.updateReturn(reqMap);
		
		Map<String, Object> resultMap = new HashMap<>();
		if(resCnt > 0){
			resultMap.put("res", "suc");
		} else {
			resultMap.put("res", "fail");
		}

		logger.info("ret ::: " + resultMap.toString());
		logger.info("===================================== END =====================================");
		return resultMap;
	}
	
}
