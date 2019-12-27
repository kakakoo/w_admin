package com.i4unetworks.weys.banner;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.i4unetworks.weys.common.Paging;
import com.i4unetworks.weys.common.Utils;

@Controller
@RequestMapping("/api/banner")
public class BannerController {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	private static final String MOTHER_PAGE = "/api/banner";
	@Autowired
	private BannerService bannerService;

	/*
	 * 배너 
	 */
	@RequestMapping(value = "")
	public String banner(@ModelAttribute("paging") Paging paging, HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		List<BannerVO> resultList = bannerService.selectBannerList(paging);
		int totalCnt = bannerService.selectBanneristCnt();
		paging.setTotalCount(totalCnt);

		model.addAttribute("resultList", resultList);
		model.addAttribute("paging", paging);
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "banner/banner";
	}

	/*
	 * 예약 배너 
	 */
	@RequestMapping(value = "/rsv")
	public String rBanner(@ModelAttribute("paging") Paging paging, HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		List<BannerVO> resultList = bannerService.selectRsvBannerList(paging);
		int totalCnt = bannerService.selectRsvBanneristCnt();
		paging.setTotalCount(totalCnt);

		model.addAttribute("resultList", resultList);
		model.addAttribute("paging", paging);
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "banner/rbanner";
	}

	/*
	 * 배너 등록, 수정, 삭제 페이지 
	 */
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write(HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		String bannerId = request.getParameter("id");
		
		int res = 0;
		BannerVO info = null;
		if(bannerId != null){
			info = bannerService.selectBannerInfo(bannerId);
			res = 2;
		} else {
			info = new BannerVO();
			info.setStartDt(Utils.getTodayDate());
			info.setEndDt(Utils.getDiffDate(30));
		}

		List<Map<String, Object>> eventList = bannerService.selectEventList();
		List<Map<String, Object>> noticeList = bannerService.selectNoticeList();
		List<Map<String, Object>> contList = bannerService.selectContList();
		List<Map<String, Object>> unitList = bannerService.selectUnitList();

		model.addAttribute("info", info);
		model.addAttribute("result", res);
		model.addAttribute("appList", redirectAppList());
		model.addAttribute("contList", contList);
		model.addAttribute("eventList", eventList);
		model.addAttribute("noticeList", noticeList);
		model.addAttribute("unitList", unitList);
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "banner/write";
	}

	/*
	 * 배너 등록, 수정, 삭제 페이지 
	 */
	@RequestMapping(value = "/rsv/write", method = RequestMethod.GET)
	public String rwrite(HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		String bnrId = request.getParameter("id");
		
		int res = 0;
		BannerVO info = null;
		if(bnrId != null){
			info = bannerService.selectRsvBannerInfo(bnrId);
			res = 2;
		} else {
			info = new BannerVO();
			info.setStartDt(Utils.getTodayDate());
			info.setEndDt(Utils.getDiffDate(30));
		}
		List<Map<String, Object>> eventList = bannerService.selectEventList();
		List<Map<String, Object>> noticeList = bannerService.selectNoticeList();

		model.addAttribute("info", info);
		model.addAttribute("result", res);
		model.addAttribute("appList", redirectAppList());
		model.addAttribute("eventList", eventList);
		model.addAttribute("noticeList", noticeList);
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "banner/rwrite";
	}

	/*
	 * 배너 등록
	 */
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String writeBanner(HttpServletRequest request, HttpServletResponse response, Model model
			, @ModelAttribute BannerVO bannerVO) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		bannerService.insertBanner(bannerVO);

		logger.info("===================================== END =====================================");
		return "redirect:/api/banner";
	}

	/*
	 * 배너 등록
	 */
	@RequestMapping(value = "/rsv/write", method = RequestMethod.POST)
	public String writerBanner(HttpServletRequest request, HttpServletResponse response, Model model
			, @ModelAttribute BannerVO bannerVO) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		bannerService.insertRsvBanner(bannerVO);

		logger.info("===================================== END =====================================");
		return "redirect:/api/banner/rsv";
	}

	/*
	 * 배너 수정
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateBanner(HttpServletRequest request, HttpServletResponse response, Model model
			, @ModelAttribute BannerVO bannerVO) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		bannerService.updateBanner(bannerVO);

		logger.info("===================================== END =====================================");
		return "redirect:/api/banner";
	}

	/*
	 * 배너 수정
	 */
	@RequestMapping(value = "/rsv/update", method = RequestMethod.POST)
	public String updaterBanner(HttpServletRequest request, HttpServletResponse response, Model model
			, @ModelAttribute BannerVO bannerVO) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		bannerService.updateRsvBanner(bannerVO);

		logger.info("===================================== END =====================================");
		return "redirect:/api/banner/rsv";
	}

	/*
	 * 배너 삭제
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deleteBanner(HttpServletRequest request, HttpServletResponse response, Model model
			, @ModelAttribute BannerVO bannerVO) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		bannerService.deleteBanner(bannerVO.getBannerId());

		logger.info("===================================== END =====================================");
		return "redirect:/api/banner";
	}

	/*
	 * 배너 삭제
	 */
	@RequestMapping(value = "/rsv/delete", method = RequestMethod.POST)
	public String deleterBanner(HttpServletRequest request, HttpServletResponse response, Model model
			, @ModelAttribute BannerVO bannerVO) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		bannerService.deleteRsvBanner(bannerVO.getBnrId());

		logger.info("===================================== END =====================================");
		return "redirect:/api/banner/rsv";
	}

	/*
	 * 앱네 화면 이동 리스트 
	 */
	private Map<String, Object> redirectAppList(){
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("event", "이벤트");
		result.put("coupon", "쿠폰");
		result.put("rsv", "예약");
		result.put("mypage", "마이페이지");
		result.put("notice", "공지사항");
		result.put("cont", "컨텐츠");
		
		return result;
	}
}
