package com.i4unetworks.weys.cont;

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
@RequestMapping("/api/cont")
public class ContController {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	private static final String MOTHER_PAGE = "/api/cont";
	@Autowired
	private ContService contService;
	
	@RequestMapping(value = "")
	public String main(@ModelAttribute("paging") Paging paging, HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		List<ContVO> resultList = contService.getContList(paging);
		int totalCnt = contService.getContListCnt(paging);
		paging.setTotalCount(totalCnt);

		model.addAttribute("resultList", resultList);
		model.addAttribute("paging", paging);
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "cont/cont";
	}

	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write(HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		String contId = request.getParameter("id");

		int res = 0;
		ContVO info = null;
		List<ContListVO> contList = new ArrayList<>();
		if(contId != null){
			info = contService.selectContInfo(contId);
			contList = contService.selectContInfoList(contId);
			res = 2;
		} else {
			info = new ContVO();
			info.setStartDt(Utils.getTodayDate());
			info.setEndDt(Utils.getDiffDate(30));
		}

		model.addAttribute("info", info);
		model.addAttribute("result", res);
		model.addAttribute("contList", contList);
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "cont/write";
	}

	/*
	 * 컨텐츠 등록
	 */
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(@ModelAttribute ContVO contVO, HttpServletRequest request, HttpServletResponse response, Model model)  throws Exception{

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		logger.info("req ::: " + contVO.toString());
		
		int contId = 0;
		try{
			contId = contService.insertContInfo(contVO);
		} catch (Exception e) {
			logger.info("error ::: " + e.getMessage());
		}
		if(contId == 0){
			return "redirect:/api/cont";
		}
		logger.info("===================================== END =====================================");
		return "redirect:/api/cont/write?id=" + contId;
	}

	/*
	 * 컨텐츠 업데이트
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateCont(@ModelAttribute ContVO contVO, HttpServletRequest request, HttpServletResponse response, Model model)  throws Exception{

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		logger.info("req ::: " + contVO.toString());
		
		int contId = 0;
		try{
			contId = contService.updateContInfo(contVO);
		} catch (Exception e) {
			logger.info("error ::: " + e.getMessage());
		}
		if(contId == 0){
			return "redirect:/api/cont";
		}
		logger.info("===================================== END =====================================");
		return "redirect:/api/cont/write?id=" + contVO.getContId();
	}
	
	/*
	 * 컨텐츠 순서변경
	 */
	@ResponseBody
	@RequestMapping(value = "/updateContSeq", method=RequestMethod.POST)
	public Map<String, Object> updateContSeq(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String, Object> reqMap) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		logger.info("req ::: " + reqMap.toString());
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int resCnt = contService.updateContSeq(reqMap);
		if(resCnt > 0){
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		logger.info("===================================== END =====================================");
		return resultMap;
	}
	
	/*
	 * 컨텐츠 리스트 순서 변경
	 */
	@ResponseBody
	@RequestMapping(value = "/updateContList", method=RequestMethod.POST)
	public Map<String, Object> updateContList(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String, Object> reqMap) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		logger.info("req ::: " + reqMap.toString());
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int resCnt = contService.updateContList(reqMap);
		if(resCnt > 0){
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		logger.info("===================================== END =====================================");
		return resultMap;
	}

	@RequestMapping(value = "/list/write", method = RequestMethod.GET)
	public String writeList(HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		String contId = request.getParameter("contId");
		String clId = request.getParameter("id");
		
		if(contId == null){
			return "redirect:/api/cont";
		}

		int res = 0;
		ContListVO info = null;
		if(clId != null){
			info = contService.selectContListInfo(clId);
			res = 2;
		} else {
			info = new ContListVO();
			info.setStartDt(Utils.getTodayDate());
			info.setEndDt(Utils.getDiffDate(30));
		}

		model.addAttribute("info", info);
		model.addAttribute("result", res);
		model.addAttribute("contId", contId);
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "cont/cont_write";
	}

	/*
	 * 컨텐츠 리스트 업데이트 
	 */
	@RequestMapping(value = "/list/write", method = RequestMethod.POST)
	public String listWrite(@ModelAttribute ContListVO contVO, HttpServletRequest request, HttpServletResponse response, Model model)  throws Exception{

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		logger.info("req ::: " + contVO.toString());
		
		try{
			int contId = contService.insertContList(contVO);
		} catch (Exception e) {
			logger.info("error ::: " + e.getMessage());
		}

		logger.info("===================================== END =====================================");
		return "redirect:/api/cont/write?id=" + contVO.getContId();
	}

	/*
	 * 컨텐츠 리스트 수정 
	 */
	@RequestMapping(value = "/list/update", method = RequestMethod.POST)
	public String updateList(@ModelAttribute ContListVO contVO, HttpServletRequest request, HttpServletResponse response, Model model)  throws Exception{

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		logger.info("req ::: " + contVO.toString());
		
		try{
			int contId = contService.updateContListVO(contVO);
		} catch (Exception e) {
			logger.info("error ::: " + e.getMessage());
		}

		logger.info("===================================== END =====================================");
		return "redirect:/api/cont/write?id=" + contVO.getContId();
	}

}
