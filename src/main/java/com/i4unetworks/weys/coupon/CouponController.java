package com.i4unetworks.weys.coupon;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.i4unetworks.weys.common.ExcelRead;
import com.i4unetworks.weys.common.Paging;
import com.i4unetworks.weys.common.Utils;

@Controller
@RequestMapping("/api/coupon")
public class CouponController {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	private static final String MOTHER_PAGE = "/api/coupon";
	@Autowired
	private CouponService couponService;

	@Value("${UPLOAD.PATH}")
	private String UPLOAD_ROOT_PATH;
	/*
	 * 쿠폰 리스트 
	 */
	@RequestMapping(value = "")
	public String main(@ModelAttribute("paging") Paging paging, HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		List<CouponVO> resultList = couponService.selectCouponList(paging);
		int totalCnt = couponService.selectCouponListCnt();
		paging.setTotalCount(totalCnt);

		model.addAttribute("resultList", resultList);
		model.addAttribute("paging", paging);
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "coupon/coupon";
	}

	/*
	 * 쿠폰 등록, 수정, 삭제 페이지 
	 */
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write(HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		String couponId = request.getParameter("id");

		int res = 0;
		CouponVO info = null;
		if(couponId != null){
			info = couponService.selectCouponInfo(couponId);
			res = 2;
		} else {
			info = new CouponVO();
			info.setCouponCnt(couponService.selectTargetAllCnt());
			info.setStartDt(Utils.getTodayDate());
			info.setEndDt(Utils.getDiffDate(30));
		}

		model.addAttribute("info", info);
		model.addAttribute("result", res);
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "coupon/write";
	}

	/*
	 * 쿠폰 등록, 수정, 삭제 페이지 
	 */
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(@ModelAttribute CouponVO couponVO, HttpServletRequest request, HttpServletResponse response, Model model)  throws Exception{

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		couponService.insertCoupon(couponVO);
		logger.info("===================================== END =====================================");
		return "redirect:/api/coupon";
	}
	

	/*
	 * 발급대상 인원수 
	 */
	@ResponseBody
	@RequestMapping(value = "/getTargetCnt", method=RequestMethod.POST)
	public Map<String, Object> getTargetCnt(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String, Object> reqMap) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		
		String type = MapUtils.getString(reqMap, "type");
		int resCnt = 0;
		
		if(type.equals("S")){
			// 발급대상이 지정일 경우 
			String excel = MapUtils.getString(reqMap, "excel");
			List<String> list = ExcelRead.userIdList(UPLOAD_ROOT_PATH + excel);
			if(list == null)
				resCnt = 0;
			else
				resCnt = list.size();
		} else if(type.equals("T")){
			// 발급대상이 전체인원일 경우 
			resCnt = couponService.selectTargetAllCnt();
		} else if(type.equals("M")){
			// 발급대상이 멤버십 경우 
			resCnt = couponService.selectTargetMemberCnt();
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", "success");
		resultMap.put("resCnt", resCnt);
		logger.info("===================================== END =====================================");
		return resultMap;
	}


	/*
	 * 쿠폰 등록, 수정, 삭제 페이지 
	 */
	@RequestMapping(value = "/done", method = RequestMethod.GET)
	public String done(HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		String couponId = request.getParameter("id");
		try{
			couponService.updateCouponDone(couponId);
		} catch (Exception e) {
			logger.info("error ::: " + e.getMessage());
		}
		logger.info("===================================== END =====================================");
		return "redirect:/api/coupon";
	}

	/*
	 * 쿠폰 등록, 수정, 삭제 페이지 
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		String couponId = request.getParameter("id");
		couponService.deleteCoupon(couponId);
		logger.info("===================================== END =====================================");
		return "redirect:/api/coupon";
	}

	/*
	 * 쿠폰 등록, 수정, 삭제 페이지 
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@ModelAttribute CouponVO couponVO, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		couponService.updateCoupon(couponVO);
		logger.info("===================================== END =====================================");
		return "redirect:/api/coupon";
	}

	
}
