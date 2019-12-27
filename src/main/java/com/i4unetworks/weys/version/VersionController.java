package com.i4unetworks.weys.version;

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
@RequestMapping("/api")
public class VersionController {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	private static final String MOTHER_PAGE = "/api/version";
	@Autowired
	private VersionService versionService;

	@RequestMapping(value = "/version")
	public String version(@ModelAttribute("paging") Paging paging, HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		VersionVO ios = versionService.selectVersionInfo("IOS");
		VersionVO aos = versionService.selectVersionInfo("AOS");
		List<AdmLogVO> logList = versionService.selectAdmLog(paging);
		List<MngVO> mngList = versionService.selectMngList();
		List<SmsVO> smsList = versionService.selectSmsList();
		int logCnt = versionService.selectAdmLogCnt();
		paging.setTotalCount(logCnt);

		model.addAttribute("ios", ios);
		model.addAttribute("aos", aos);
		model.addAttribute("logList", logList);
		model.addAttribute("mngList", mngList);
		model.addAttribute("smsList", smsList);
		model.addAttribute("motherPage", MOTHER_PAGE);
		model.addAttribute("paging", paging);
		logger.info("===================================== END =====================================");
		return "version/version";
	}

	@RequestMapping(value = "/version/write")
	public String updateVersion(HttpServletRequest request, HttpServletResponse response, @ModelAttribute VersionVO versionVO) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		versionService.updateVersion(versionVO);
		logger.info("===================================== END =====================================");
		return "redirect:/api/version";
	}

	@RequestMapping(value = "/tut")
	public String tutorial(@ModelAttribute("paging") Paging paging, HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		

		model.addAttribute("motherPage", "/api/tut");
		logger.info("===================================== END =====================================");
		return "version/tut";
	}

	@RequestMapping(value = "/version/mng/write", method = RequestMethod.GET)
	public String write(HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		String mgId = request.getParameter("id");

		int res = 0;
		MngVO info = null;
		if(mgId != null){
			info = versionService.selectMngInfo(mgId);
			res = 2;
		} else {
			info = new MngVO();
		}

		model.addAttribute("info", info);
		model.addAttribute("result", res);
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "version/write";
	}

	/*
	 * 쿠폰 등록, 수정, 삭제 페이지 
	 */
	@RequestMapping(value = "/version/mng/write", method = RequestMethod.POST)
	public String write(@ModelAttribute MngVO mngVO, HttpServletRequest request, HttpServletResponse response, Model model)  throws Exception{

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		logger.info("req ::: " + mngVO.toString());
		
		try{
			versionService.insertManager(mngVO);
		} catch (Exception e) {
			logger.info("error ::: " + e.getMessage());
		}
		logger.info("===================================== END =====================================");
		return "redirect:/api/version";
	}

	/*
	 * 배너 수정
	 */
	@RequestMapping(value = "/version/mng/update", method = RequestMethod.POST)
	public String updateEvent(HttpServletRequest request, HttpServletResponse response, Model model
			, @ModelAttribute MngVO mngVO) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());

		versionService.updateManager(mngVO);

		logger.info("===================================== END =====================================");
		return "redirect:/api/version";
	}

	/*
	 * 쿠폰 등록, 수정, 삭제 페이지 
	 */
	@RequestMapping(value = "/version/mng/delete", method = RequestMethod.GET)
	public String delete(HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		String mgId = request.getParameter("id");
		versionService.deleteManager(mgId);
		logger.info("===================================== END =====================================");
		return "redirect:/api/version";
	}

	/**
	 * sms 상세 페이지
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/version/sms/write", method = RequestMethod.GET)
	public String smsWrite(HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		String smsId = request.getParameter("id");

		int res = 0;
		SmsVO info = null;
		if(smsId != null){
			info = versionService.selectSmsInfo(smsId);
			res = 2;
		} else {
			info = new SmsVO();
		}

		model.addAttribute("info", info);
		model.addAttribute("result", res);
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "version/s_write";
	}

	/*
	 * sms 등록
	 */
	@RequestMapping(value = "/version/sms/write", method = RequestMethod.POST)
	public String smsWrite(@ModelAttribute SmsVO smsVO, HttpServletRequest request, HttpServletResponse response, Model model)  throws Exception{

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		logger.info("req ::: " + smsVO.toString());
		
		try{
			versionService.insertSms(smsVO);
		} catch (Exception e) {
			logger.info("error ::: " + e.getMessage());
		}
		logger.info("===================================== END =====================================");
		return "redirect:/api/version";
	}

	/*
	 * sms 수정
	 */
	@RequestMapping(value = "/version/sms/update", method = RequestMethod.POST)
	public String updateSMS(HttpServletRequest request, HttpServletResponse response, Model model
			, @ModelAttribute SmsVO smsVO) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());

		versionService.updateSms(smsVO);

		logger.info("===================================== END =====================================");
		return "redirect:/api/version";
	}

	/*
	 * sms 삭제 
	 */
	@RequestMapping(value = "/version/sms/delete", method = RequestMethod.GET)
	public String deleteSms(HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		String smsId = request.getParameter("id");
		versionService.deleteSms(smsId);
		logger.info("===================================== END =====================================");
		return "redirect:/api/version";
	}

}
