package com.i4unetworks.weys.main;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.i4unetworks.weys.common.Paging;
import com.i4unetworks.weys.common.UserVO;
import com.i4unetworks.weys.common.Utils;

@Controller
@RequestMapping("/api")
public class MainController {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private MainService mainService;

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		String date = Utils.getTodayDate();
		int totalUser = mainService.selectTotalUser(null);
		int todayUser = mainService.selectTotalUser(date);
		int yesUser = mainService.selectTotalUser(Utils.getDiffDate(-1));
		int totalRsv = mainService.selectTotalRsv();
		int todayRsvDone = mainService.selectTodayRsvDone(date);
		int todayRsv = mainService.selectTodayRsv(date);
		int todayRsvAll = mainService.selectTodayRsvAll(date);
		int yesRsv = mainService.selectYesRsv(Utils.getDiffDate(-1));

		List<Map<String, Object>> readyUnit = mainService.selectReadyUnit(date);
		List<Map<String, Object>> readyStore = mainService.selectReadyStore();
		List<String> rsvDts = mainService.selectRsvDt(date);
		
		UserVO userVO = (UserVO) request.getSession().getAttribute("login");
		
		/**
		 * 저녁 10시 이후 남은 예약건 확인
		 */
		int remainCnt = mainService.selectRemainRsv();

		model.addAttribute("adminTp", userVO.getAdminTp());
		model.addAttribute("totalUser", Utils.setStringFormatInteger(String.valueOf(totalUser)));
		model.addAttribute("todayUser", Utils.setStringFormatInteger(String.valueOf(todayUser)));
		model.addAttribute("yesUser", Utils.setStringFormatInteger(String.valueOf(yesUser)));
		model.addAttribute("totalRsv", Utils.setStringFormatInteger(String.valueOf(totalRsv)));
		model.addAttribute("todayRsvDone", Utils.setStringFormatInteger(String.valueOf(todayRsvDone)));
		model.addAttribute("todayRsv", Utils.setStringFormatInteger(String.valueOf(todayRsv)));
		model.addAttribute("todayRsvAll", Utils.setStringFormatInteger(String.valueOf(todayRsvAll)));
		model.addAttribute("yesRsv", Utils.setStringFormatInteger(String.valueOf(yesRsv)));
		model.addAttribute("readyUnit", readyUnit);
		model.addAttribute("readyStore", readyStore);
		model.addAttribute("rsvDts", rsvDts);
		model.addAttribute("remainCnt", remainCnt);
		logger.info("===================================== END =====================================");
		return "main/main";
	}
	

	@RequestMapping(value = "/survey")
	public String survey(@ModelAttribute("paging") Paging paging, HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		UserVO userVO = (UserVO) request.getSession().getAttribute("login");
		
		if(!userVO.getAdminTp().equals("S")){
			return "main/main";
		}
		
		List<Map<String, Object>> surList = mainService.selectSurveyHist(paging);
		int totalCnt = mainService.selectSurveyHistCnt(paging);
		paging.setTotalCount(totalCnt);

		model.addAttribute("paging", paging);
		model.addAttribute("surList", surList);
		model.addAttribute("adminTp", userVO.getAdminTp());
		logger.info("===================================== END =====================================");
		return "main/survey";
	}

	@RequestMapping(value = "/rate")
	public String rate(HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		UserVO userVO = (UserVO) request.getSession().getAttribute("login");
		
		if(!userVO.getAdminTp().equals("S")){
			return "main/main";
		}
		
		List<Map<String, Object>> rateList = mainService.selectRateList();

		model.addAttribute("surList", rateList);
		model.addAttribute("adminTp", userVO.getAdminTp());
		logger.info("===================================== END =====================================");
		return "main/rate";
	}

}
