package com.i4unetworks.weys.cop;

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
@RequestMapping("/api/cop")
public class CopController {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	private static final String MOTHER_PAGE = "/api/cop";
	@Autowired
	private CopService copService;
	
	@RequestMapping(value = "")
	public String copMain(@ModelAttribute("paging") Paging paging, HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		List<CopVO> resultList = copService.selectCopList(paging);
		int totalCnt = copService.selectCopListCnt();
		paging.setTotalCount(totalCnt);

		model.addAttribute("resultList", resultList);
		model.addAttribute("paging", paging);
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "cop/cop";
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write(HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		String copId = request.getParameter("id");

		int res = 0;
		CopVO info = null;
		if(copId != null){
			info = copService.selectCopInfo(copId);
			res = 2;
		} else {
			info = new CopVO();
		}

		model.addAttribute("info", info);
		model.addAttribute("result", res);
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "cop/write";
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(@ModelAttribute CopVO copVO, HttpServletRequest request, HttpServletResponse response, Model model)  throws Exception{

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		copService.insertCop(copVO);
		logger.info("===================================== END =====================================");
		return "redirect:/api/cop";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		String copId = request.getParameter("id");
		copService.deleteCop(copId);
		logger.info("===================================== END =====================================");
		return "redirect:/api/cop";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@ModelAttribute CopVO copVO, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		copService.updateCop(copVO);
		logger.info("===================================== END =====================================");
		return "redirect:/api/cop";
	}

	@RequestMapping(value = "/banner")
	public String banner(@ModelAttribute("paging") Paging paging, HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		List<CopBnrVO> resultList = copService.selectCopBnrList(paging);
		int totalCnt = copService.selectCopBnrListCnt(paging);
		paging.setTotalCount(totalCnt);

		model.addAttribute("resultList", resultList);
		model.addAttribute("paging", paging);
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "cop/cop_banner";
	}

	@RequestMapping(value = "/banner/write", method = RequestMethod.GET)
	public String bWrite(HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		String cbId = request.getParameter("id");

		int res = 0;
		CopBnrVO info = null;
		if(cbId != null){
			info = copService.selectCopBnrInfo(cbId);
			res = 2;
		} else {
			info = new CopBnrVO();
		}

		model.addAttribute("info", info);
		model.addAttribute("result", res);
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "cop/bwrite";
	}
	
	@RequestMapping(value = "/banner/write", method = RequestMethod.POST)
	public String bWrite(@ModelAttribute CopBnrVO copBnrVO, HttpServletRequest request, HttpServletResponse response, Model model)  throws Exception{

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		logger.info("req ::: " + copBnrVO.toString());
		
		copService.insertCopBnr(copBnrVO);
		logger.info("===================================== END =====================================");
		return "redirect:/api/cop/banner";
	}

	@RequestMapping(value = "/banner/delete", method = RequestMethod.GET)
	public String deleteBnr(HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		String cbId = request.getParameter("id");
		copService.deleteCopBnr(cbId);
		logger.info("===================================== END =====================================");
		return "redirect:/api/cop/banner";
	}

	@RequestMapping(value = "/banner/update", method = RequestMethod.POST)
	public String updateBnr(@ModelAttribute CopBnrVO copBnrVO, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		copService.updateCopBnr(copBnrVO);
		logger.info("===================================== END =====================================");
		return "redirect:/api/cop/banner";
	}

}
