package com.i4unetworks.weys.notice;

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
@RequestMapping("/api/notice")
public class NoticeController {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	private static final String MOTHER_PAGE = "/api/notice";
	@Autowired
	private NoticeService noticeService;

	@RequestMapping(value = "")
	public String main(@ModelAttribute("paging") Paging paging, HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		List<NoticeDetailVO> resultList = noticeService.selectNoticeList(paging);
		int totalCnt = noticeService.selectNoticeListCnt(paging);
		paging.setTotalCount(totalCnt);

		model.addAttribute("resultList", resultList);
		model.addAttribute("paging", paging);
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "notice/notice";
	}
	
	@RequestMapping(value = "/admin")
	public String adminNotice(@ModelAttribute("paging") Paging paging, HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		List<NoticeDetailVO> resultList = noticeService.selectAdminList(paging);
		int totalCnt = noticeService.selectAdminListCnt(paging);
		paging.setTotalCount(totalCnt);

		model.addAttribute("resultList", resultList);
		model.addAttribute("paging", paging);
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "notice/admin";
	}
	
	@ResponseBody
	@RequestMapping(value = "/changeStat", method=RequestMethod.POST)
	public Map<String, Object> changeStatus(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String, Object> reqMap) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		
		int resCnt = noticeService.updateStatus(reqMap);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(resCnt > 0){
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}

		logger.info("===================================== END =====================================");
		return resultMap;
	}

	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String writePage(HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());

		String tp = request.getParameter("tp");

		model.addAttribute("tp", tp);
		model.addAttribute("result", "0");
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "notice/write";
	}
	

	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(HttpServletRequest request, HttpServletResponse response, @ModelAttribute NoticeDetailVO noticeDetailVO, Model model) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		int res = noticeService.insertNotice(noticeDetailVO, request);
		
		if(res > 0){
			logger.info("===================================== END =====================================");
			if(noticeDetailVO.getTp().equals("admin")){
				return "redirect:/api/notice/admin";
			} else {
				return "redirect:/api/notice";
			}
		}
		model.addAttribute("tp", noticeDetailVO.getTp());
		model.addAttribute("result", "1");
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "notice/write";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String modifyNotice(HttpServletRequest request, HttpServletResponse response, @ModelAttribute NoticeDetailVO noticeDetailVO, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		int res = noticeService.updateNotice(noticeDetailVO);
		
		if(res > 0){
			logger.info("===================================== END =====================================");
			if(noticeDetailVO.getTp().equals("admin")){
				return "redirect:/api/notice/admin";
			} else {
				return "redirect:/api/notice";
			}
		}
		model.addAttribute("tp", noticeDetailVO.getTp());
		model.addAttribute("result", "1");
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "notice/write";
	}

	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify(HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());

		String noticeId = request.getParameter("id");
		String tp = request.getParameter("tp");
		NoticeDetailVO info = noticeService.selectNotice(noticeId, tp);

		model.addAttribute("tp", tp);
		model.addAttribute("info", info);
		model.addAttribute("result", "2");
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "notice/write";
	}
	

}
