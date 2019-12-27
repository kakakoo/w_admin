package com.i4unetworks.weys.member;

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
@RequestMapping("/api/member")
public class MemberController {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	private static final String MOTHER_PAGE = "/api/member";
	@Autowired
	private MemberService memberService;

	/*
	 * 멤버십 결제 리스트 
	 */
	@RequestMapping(value = "")
	public String main(@ModelAttribute("paging") Paging paging, HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		List<MemberVO> resultList = memberService.selectPayList(paging);
		int totalCnt = memberService.selectPayListCnt();
		paging.setTotalCount(totalCnt);

		model.addAttribute("resultList", resultList);
		model.addAttribute("paging", paging);
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "member/member";
	}

	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write(HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		String payId = request.getParameter("id");
		
		if(payId != null){
			MemberVO info = memberService.selectPayInfo(payId);
			model.addAttribute("info", info);
			model.addAttribute("result", "2");
		} else {
			MemberVO info = new MemberVO();
			model.addAttribute("info", info);
			model.addAttribute("result", "0");
		}
		
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "member/write";
	}

	/*
	 * 지점 상태 변경
	 */
	@ResponseBody
	@RequestMapping(value = "/write", method=RequestMethod.POST)
	public Map<String, Object> insertPayInfo(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String, Object> reqMap) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		
		int resCnt = memberService.insertPayInfo(reqMap);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(resCnt > 0){
			resultMap.put("result", "success");
		} else if(resCnt == -1) {
			resultMap.put("result", "fail");
			resultMap.put("msg", "중복되는 기간이 있습니다.");
		} else {
			resultMap.put("result", "fail");
			resultMap.put("msg", "다시 시도해 주세요.");
		}
		logger.info("===================================== END =====================================");
		return resultMap;
	}

	/*
	 * 지점 삭제
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", method=RequestMethod.POST)
	public Map<String, Object> deletePayInfo(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String, Object> reqMap) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		
		int resCnt = memberService.deletePayInfo(reqMap);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(resCnt > 0){
			resultMap.put("result", "success");
		} else if(resCnt == -1) {
			resultMap.put("result", "fail");
			resultMap.put("msg", "이벤트 진행중입니다.");
		} else {
			resultMap.put("result", "fail");
			resultMap.put("msg", "다시 시도해 주세요.");
		}
		logger.info("===================================== END =====================================");
		return resultMap;
	}

	/*
	 * 지점 삭제
	 */
	@ResponseBody
	@RequestMapping(value = "test", method=RequestMethod.GET)
	public Map<String, Object> test(HttpServletRequest req, HttpServletResponse res) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		
		memberService.insertRsvTest();
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		logger.info("===================================== END =====================================");
		return resultMap;
	}

}
