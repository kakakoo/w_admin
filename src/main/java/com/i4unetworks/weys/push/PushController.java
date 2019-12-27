package com.i4unetworks.weys.push;

import java.util.ArrayList;
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
import com.i4unetworks.weys.common.Utils;

@Controller
@RequestMapping("/api/push")
public class PushController {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	private static final String MOTHER_PAGE = "/api/push";
	@Autowired
	private AdmPushService pushService;

	@RequestMapping(value = "")
	public String main(@ModelAttribute("paging") Paging paging, HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());

		List<Map<String, Object>> eventList = pushService.selectEventList();
		List<Map<String, Object>> noticeList = pushService.selectNoticeList();
		List<Map<String, Object>> contList = pushService.selectContList();

		model.addAttribute("eventList", eventList);
		model.addAttribute("noticeList", noticeList);
		model.addAttribute("contList", contList);
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "push/push";
	}

	@RequestMapping(value = "/contents")
	public String pushContents(@ModelAttribute("paging") Paging paging, HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());

		List<Map<String, Object>> contList = pushService.selectContList();
		
		List<String> doneList = new ArrayList<>();
		for(int i=0 ; i>-3 ; i--){
			String dt = Utils.getDiffDate(i);
			doneList.add(dt);
		}

		List<String> readyList = new ArrayList<>();
		for(int i=0 ; i<3 ; i++){
			String dt = Utils.getDiffDate(i);
			readyList.add(dt);
		}

		model.addAttribute("doneList", doneList);
		model.addAttribute("readyList", readyList);
		model.addAttribute("contList", contList);
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "push/cont";
	}

	/*
	 * push 보내기
	 */
	@ResponseBody
	@RequestMapping(value = "", method=RequestMethod.POST)
	public Map<String, Object> sendPush(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String, Object> reqMap) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		logger.info("req ::: " + reqMap.toString());
		
		int resCnt = pushService.updateSendPush(reqMap);
		
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
	 * push 보내기
	 */
	@ResponseBody
	@RequestMapping(value = "/contents", method=RequestMethod.POST)
	public Map<String, Object> sendContents(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String, Object> reqMap) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		logger.info("req ::: " + reqMap.toString());
		
		int resCnt = pushService.updateSendPushCont(reqMap);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(resCnt > 0){
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}

		logger.info("===================================== END =====================================");
		return resultMap;
	}

	@RequestMapping(value = "/test")
	public String te(@ModelAttribute("paging") Paging paging, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());

//		pushService.sendPushTestAll(null);
		
		logger.info("===================================== END =====================================");
		return "push/push";
	}
}
