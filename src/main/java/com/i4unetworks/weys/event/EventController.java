package com.i4unetworks.weys.event;

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
import com.i4unetworks.weys.common.Utils;
import com.i4unetworks.weys.coupon.CouponVO;

@Controller
@RequestMapping("/api/event")
public class EventController {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	private static final String MOTHER_PAGE = "/api/event";
	@Autowired
	private EventService eventService;

	@RequestMapping(value = "")
	public String main(@ModelAttribute("paging") Paging paging, HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		List<EventVO> resultList = eventService.getEventList(paging);
		int totalCnt = eventService.getEventListCnt(paging);
		paging.setTotalCount(totalCnt);

		model.addAttribute("resultList", resultList);
		model.addAttribute("paging", paging);
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "event/event";
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write(HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		String eventId = request.getParameter("id");
		List<CouponVO> coupList = eventService.selectEventCoupList();

		int res = 0;
		EventVO info = null;
		if(eventId != null){
			info = eventService.selectEventInfo(eventId);
			res = 2;
		} else {
			info = new EventVO();
			info.setStartDt(Utils.getTodayDate());
			info.setEndDt(Utils.getDiffDate(30));
		}

		model.addAttribute("info", info);
		model.addAttribute("result", res);
		model.addAttribute("coupList", coupList);
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "event/write";
	}

	/*
	 * 쿠폰 등록, 수정, 삭제 페이지 
	 */
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(@ModelAttribute EventVO eventVO, HttpServletRequest request, HttpServletResponse response, Model model)  throws Exception{

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		logger.info("req ::: " + eventVO.toString());
		
		try{
			eventService.insertEvent(eventVO);
		} catch (Exception e) {
			logger.info("error ::: " + e.getMessage());
		}
		logger.info("===================================== END =====================================");
		return "redirect:/api/event";
	}

	/*
	 * 배너 수정
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateEvent(HttpServletRequest request, HttpServletResponse response, Model model
			, @ModelAttribute EventVO eventVO) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());

		eventService.updateEvent(eventVO);

		logger.info("===================================== END =====================================");
		return "redirect:/api/event";
	}

	/*
	 * 푸시 보내기
	 */
	@ResponseBody
	@RequestMapping(value = "/sendPush", method=RequestMethod.POST)
	public Map<String, Object> sendPush(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String, Object> reqMap) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		
		String eventId = MapUtils.getString(reqMap, "eventId");
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int resCnt = eventService.updateSendPush(eventId);
		if(resCnt > 0){
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		logger.info("===================================== END =====================================");
		return resultMap;
	}
	
	/**
	 * 참여 이벤트 페이지
	 * @param paging
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/join")
	public String joinPage(@ModelAttribute("paging") Paging paging, HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		List<Map<String, Object>> resultList = eventService.getEventJoinList(paging);
		int totalCnt = eventService.getEventJoinListCnt(paging);
		paging.setTotalCount(totalCnt);

		model.addAttribute("resultList", resultList);
		model.addAttribute("paging", paging);
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "event/join";
	}

	/**
	 * 이벤트 참여자 쿠폰 주기
	 * @param req
	 * @param res
	 * @param reqMap
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/join/sendCoupon", method=RequestMethod.POST)
	public Map<String, Object> sendCoupon(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String, Object> reqMap) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		logger.info("req ::: " + reqMap.toString());
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int resCnt = eventService.updateSendCoupon(reqMap);
		if(resCnt > 0){
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		logger.info("===================================== END =====================================");
		return resultMap;
	}
	
}
