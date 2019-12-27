package com.i4unetworks.weys.login;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.i4unetworks.weys.common.UserVO;
import com.i4unetworks.weys.common.Utils;

@Controller
public class LoginController {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest req) {
		
		UserVO userVO = (UserVO) req.getSession().getAttribute("login");
		
		if(userVO != null){
			return "redirect:/api/main";
		}

		return "home";
	}

	/**
	 * 로그인
	 * @param req
	 * @param res
	 * @return 사용자 로그인 후 결과 리턴 
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> login(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String, Object> reqMap) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		
		logger.info("reqMap ::: " + reqMap.toString());
		
		HashMap<String, Object> returnMap = loginService.insertlogin(reqMap, req, res);

		logger.info("===================================== END =====================================");
		return returnMap;
	}

	/**
	 * 로그인
	 * @param req
	 * @param res
	 * @return 사용자 로그인 후 결과 리턴 
	 */
	@RequestMapping(value = "/getCookie", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getCookie(HttpServletRequest req, HttpServletResponse res, @RequestParam Map<String, Object> reqMap) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		
		HashMap<String, Object> returnMap = new HashMap<String, Object>();

		String id = "";
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("WEYSADMIN")) {
					id = cookies[i].getValue();
				}
			}
		}

		if (id.equals("")) {
			returnMap.put("id", "");
		} else {
			returnMap.put("id", id);
		}

		logger.info("===================================== END =====================================");
		return returnMap;
	}

	/**
	 * 로그아웃 
	 * @param req
	 * @param res
	 * @return 사용자 로그아웃 세션정리  
	 */
	@RequestMapping(value = "/logout")
	@ResponseBody
	public boolean logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		HttpSession session = request.getSession();

		session.invalidate();
		String context = request.getContextPath();
		response.sendRedirect(context + "/");
		logger.info("===================================== END =====================================");
		return true;
	}

	/**
	 * 광고판 뿌리기 
	 */
	@RequestMapping(value = "/dp")
	public String dp() {

		return "display";
	}

	/**
	 * 광고판 뿌리기 
	 */
	@RequestMapping(value = "/ex")
	public String ex(HttpServletRequest req, HttpServletResponse res, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		List<Map<String, Object>> exMap = loginService.selectExchangeRate();

		model.addAttribute("info", exMap);
		logger.info("===================================== END =====================================");
		return "ex";
	}

	/**
	 * 광고판 뿌리기 
	 */
	@ResponseBody
	@RequestMapping(value = "/display")
	public Map<String, Object> display(HttpServletRequest req, HttpServletResponse res) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		
		
		Map<String, Object> returnMap = loginService.getDisplayInfo();

		logger.info("===================================== END =====================================");
		return returnMap;
	}

	/**
	 * 비밀번호 변경
	 */
	@RequestMapping(value = "/api/cp")
	public String cp(HttpServletRequest req, HttpServletResponse res, Model model) {

		String timeOver = req.getParameter("tover");
		if(timeOver != null && timeOver.equals("true")){
			model.addAttribute("tover", "true");
		} 

		UserVO userVO = (UserVO) req.getSession().getAttribute("login");
		model.addAttribute("adminTp", userVO.getAdminTp());
		return "common/cp";
	}

	/**
	 * 비밀번호 변경
	 */
	@ResponseBody
	@RequestMapping(value = "/api/cpchk", method=RequestMethod.POST)
	public Map<String, Object> changePwd(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String, Object> reqMap) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		logger.info("req ::: " + reqMap.toString());
		
		UserVO userVO = (UserVO) req.getSession().getAttribute("login");
		Map<String, Object> resultMap = loginService.updateAdmPwd(req, reqMap, userVO);

		logger.info("===================================== END =====================================");
		return resultMap;
	}
	

	/**
	 * 광고판 뿌리기 
	 */
	@RequestMapping(value = "/testEmp")
	public String testemp(HttpServletRequest req, HttpServletResponse res, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		
		loginService.insertMoneyLog();
		logger.info("===================================== END =====================================");
		return "ex";
	}

}
