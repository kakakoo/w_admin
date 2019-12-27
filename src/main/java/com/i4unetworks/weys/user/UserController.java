package com.i4unetworks.weys.user;

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
@RequestMapping("/api/user")
public class UserController {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	private static final String MOTHER_PAGE = "/api/user";
	@Autowired
	private UserService userService;

	@RequestMapping(value = "")
	public String main(@ModelAttribute("paging") Paging paging, HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		List<UserListVO> resultList = userService.getUserList(paging);
		int totalCnt = userService.getUserListCnt(paging);
		paging.setTotalCount(totalCnt);

		UserVO userVO = (UserVO) request.getSession().getAttribute("login");
		model.addAttribute("adminTp", userVO.getAdminTp());
		model.addAttribute("resultList", resultList);
		model.addAttribute("paging", paging);
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "user/user";
	}

	@RequestMapping(value = "/delete")
	public String deleteList(@ModelAttribute("paging") Paging paging, HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		List<UserListVO> resultList = userService.getUserDeleteList(paging);
		int totalCnt = userService.getUserDeleteListCnt(paging);
		paging.setTotalCount(totalCnt);

		UserVO userVO = (UserVO) request.getSession().getAttribute("login");
		model.addAttribute("adminTp", userVO.getAdminTp());
		model.addAttribute("resultList", resultList);
		model.addAttribute("paging", paging);
		model.addAttribute("motherPage", MOTHER_PAGE + "/delete");
		logger.info("===================================== END =====================================");
		return "user/delete";
	}

	@RequestMapping(value = "/{usrId}/detail")
	public String membershipDetail(@ModelAttribute("paging") Paging paging, HttpServletRequest request, HttpServletResponse response
			, @PathVariable String usrId, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		UserListVO info = userService.selectUserDetail(usrId);
		paging.setUsrId(usrId);
		
//		List<MemberActiveVO> resultList = userService.getMembershipList(paging);
		int totalCnt = userService.getMembershipListCnt(usrId);
		paging.setTotalCount(totalCnt);

		UserVO userVO = (UserVO) request.getSession().getAttribute("login");
		model.addAttribute("adminTp", userVO.getAdminTp());
		model.addAttribute("info", info);
		model.addAttribute("paging", paging);
		model.addAttribute("resultList", null);
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "user/detail";
	}

	/*
	 * 회원 등급 변경 
	 */
	@ResponseBody
	@RequestMapping(value = "/change/usrGrade", method=RequestMethod.POST)
	public Map<String, Object> changeGrade(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String, Object> reqMap) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		logger.info("req ::: " + reqMap.toString());
		
		int resCnt = userService.updateUsrGrade(reqMap);
		
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
	 * 상담내용 등록 
	 */
	@ResponseBody
	@RequestMapping(value = "/insertMemo", method=RequestMethod.POST)
	public Map<String, Object> insertMemo(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String, Object> reqMap) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		logger.info("req ::: " + reqMap.toString());
		
		int resCnt = userService.insertMemo(reqMap);
		
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
	 * sms 전송
	 */
	@ResponseBody
	@RequestMapping(value = "/insertSMS", method=RequestMethod.POST)
	public Map<String, Object> insertSMS(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String, Object> reqMap) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		logger.info("req ::: " + reqMap.toString());
		
		UserVO userVO = (UserVO) req.getSession().getAttribute("login");
		reqMap.put("adminKey", userVO.getAdminKey());

		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		if(!userVO.getAdminTp().equals("S")){
			resultMap.put("result", "no Permission");
		} else {
			int resCnt = userService.insertSMS(reqMap);
			if(resCnt > 0){
				resultMap.put("result", "success");
			} else {
				resultMap.put("result", "fail");
			}
		}

		logger.info("===================================== END =====================================");
		return resultMap;
	}

	/*
	 * 상세 내역 리스트
	 */
	@ResponseBody
	@RequestMapping(value = "/detail/list", method=RequestMethod.POST)
	public Map<String, Object> selectList(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String, Object> reqMap) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		logger.info("req ::: " + reqMap.toString());
		
		List<Map<String, Object>> resultList = userService.selectDetailList(reqMap);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(resultList != null){
			resultMap.put("result", "success");
			resultMap.put("list", resultList);
		} else {
			resultMap.put("result", "fail");
		}

		logger.info("===================================== END =====================================");
		return resultMap;
	}

	/**
	 * 삭제 각 
	 * @param paging
	 * @param request
	 * @param response
	 * @param usrId
	 * @param model
	 * @return
	 */
	
	@RequestMapping(value = "/{usrId}/cpoint")
	public String changePointDetail(@ModelAttribute("paging") Paging paging, HttpServletRequest request, HttpServletResponse response
			, @PathVariable String usrId, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		UserListVO info = userService.selectUserDetail(usrId);
		paging.setUsrId(usrId);
		
		List<UserPointVO> resultList = userService.getChangePointList(paging);
		int totalCnt = userService.getChangePointListCnt(usrId);
		paging.setTotalCount(totalCnt);

		UserVO userVO = (UserVO) request.getSession().getAttribute("login");
		model.addAttribute("adminTp", userVO.getAdminTp());
		model.addAttribute("info", info);
		model.addAttribute("paging", paging);
		model.addAttribute("resultList", resultList);
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "user/changePoint";
	}

	@RequestMapping(value = "/{usrId}/upoint")
	public String usePointDetail(@ModelAttribute("paging") Paging paging, HttpServletRequest request, HttpServletResponse response
			, @PathVariable String usrId, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		UserListVO info = userService.selectUserDetail(usrId);
		paging.setUsrId(usrId);
		
		List<UserPointVO> resultList = userService.getUsePointList(paging);
		int totalCnt = userService.getUsePointListCnt(usrId);
		paging.setTotalCount(totalCnt);

		UserVO userVO = (UserVO) request.getSession().getAttribute("login");
		model.addAttribute("adminTp", userVO.getAdminTp());
		model.addAttribute("info", info);
		model.addAttribute("paging", paging);
		model.addAttribute("resultList", resultList);
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "user/usePoint";
	}
	
	@RequestMapping(value = "/excel")
	public String excel(@ModelAttribute("paging") Paging paging, HttpServletRequest request
			, HttpServletResponse response, ModelMap model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		List<UserListVO> excelList = userService.selectUserExcel(paging);
		
		model.put("excelList", excelList);
		model.put("type", ExcelConstants.USER);

		logger.info("===================================== END =====================================");
		// WEB_INF/mvc-config.xml에 BeanNameViewResolver를 등록했기 때문에 kr.co.medisolution.common.ExelView가 열림.
		return "excelView";
	}

	@RequestMapping(value = "/{usrId}/excel")
	public String usrDetailExcel(@ModelAttribute("paging") Paging paging, @PathVariable String usrId, HttpServletRequest request
			, HttpServletResponse response, ModelMap model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		UserListVO info = userService.selectUserDetail(usrId);
		paging.setUsrId(usrId);
		paging.setIsExcel("Y");
		
		List<MemberActiveVO> membership = userService.getMembershipList(paging);
		List<UserPointVO> changePoint = userService.getChangePointList(paging);
		List<UserPointVO> usePoint = userService.getUsePointList(paging);

		model.put("info", info);
		model.put("membership", membership);
		model.put("changePoint", changePoint);
		model.put("usePoint", usePoint);
		model.put("type", ExcelConstants.USER_DETAIL);

		logger.info("===================================== END =====================================");
		// WEB_INF/mvc-config.xml에 BeanNameViewResolver를 등록했기 때문에 kr.co.medisolution.common.ExelView가 열림.
		return "excelView";
	}
}
