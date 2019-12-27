package com.i4unetworks.weys.staff;

import java.util.List;

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
import com.i4unetworks.weys.common.Utils;

@Controller
@RequestMapping("/api/staff")
public class StaffController {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	private static final String MOTHER_PAGE = "/api/staff";
	@Autowired
	private StaffService staffService;

	/*
	 * 인수인계 상황
	 */
	@RequestMapping(value = "")
	public String main(@ModelAttribute("paging") Paging paging, HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		List<StaffVO> resultList = staffService.selectLogHist(paging);
		int totalCnt = staffService.selectLogHistCnt();
		paging.setTotalCount(totalCnt);

		model.addAttribute("resultList", resultList);
		model.addAttribute("paging", paging);
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "staff/staff";
	}

	/*
	 * 담당자 공지
	 */
	@RequestMapping(value = "/notice")
	public String notice(@ModelAttribute("paging") Paging paging, HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		List<StaffVO> resultList = staffService.selectStaffNotice(paging);
		int totalCnt = staffService.selectStaffNoticeCnt();
		paging.setTotalCount(totalCnt);

		model.addAttribute("resultList", resultList);
		model.addAttribute("paging", paging);
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "staff/notice";
	}

	/*
	 * 지점 등록 및 수정 화면   
	 */
	@RequestMapping(value = "/notice/write", method = RequestMethod.GET)
	public String writePage(HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		String anId = request.getParameter("id");
		
		if(anId != null){
			StaffVO detail = staffService.selectNoticeInfo(anId);
			model.addAttribute("info", detail);
			model.addAttribute("result", "2");
		} else {
			model.addAttribute("result", "0");
		}
		
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "staff/write";
	}

	/*
	 * 공지사항 등록  
	 */
	@RequestMapping(value = "/notice/write", method = RequestMethod.POST)
	public String write(HttpServletRequest request, HttpServletResponse response, @ModelAttribute StaffVO staffVO, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		logger.info("req ::: " + staffVO.toString());
		
		int res = staffService.insertNotice(staffVO);
		
		if(res > 0){
			return "redirect:/api/staff/notice";
		}
		model.addAttribute("result", "1");
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "staff/write";
	}

	/*
	 * 공지사항 수정 
	 */
	@RequestMapping(value = "/notice/update", method = RequestMethod.POST)
	public String update(HttpServletRequest request, HttpServletResponse response, @ModelAttribute StaffVO staffVO, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		logger.info("req ::: " + staffVO.toString());
		
		int res = staffService.updateNotice(staffVO);
		
		if(res > 0){
			return "redirect:/api/staff/notice";
		}
		model.addAttribute("result", "1");
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "staff/write";
	}

	/*
	 * 공지사항 삭제 
	 */
	@RequestMapping(value = "/notice/delete", method = RequestMethod.POST)
	public String delete(HttpServletRequest request, HttpServletResponse response, @ModelAttribute StaffVO staffVO, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		logger.info("req ::: " + staffVO.toString());
		
		int res = staffService.deleteNotice(staffVO.getAnId());
		
		if(res > 0){
			return "redirect:/api/staff/notice";
		}
		model.addAttribute("result", "1");
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "staff/write";
	}

}
