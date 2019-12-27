package com.i4unetworks.weys.manage;

import java.util.ArrayList;
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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.i4unetworks.weys.common.ExcelConstants;
import com.i4unetworks.weys.common.Paging;
import com.i4unetworks.weys.common.UserVO;
import com.i4unetworks.weys.common.Utils;
import com.i4unetworks.weys.unit.UnitVO;

@Controller
@RequestMapping(value = "/api/manage")
public class ManageController {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	private static final String MOTHER_PAGE = "/api/manage";
	@Autowired
	private ManageService manageService;
	@Value("#{props['WS.PATH']}")
	private String WS_PATH;

	@RequestMapping(value = "/main")
	public String main(@ModelAttribute("paging") Paging paging, HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		UserVO userVO = (UserVO) request.getSession().getAttribute("login");
		paging.setStoreId(userVO.getStoreId());

		List<MemberListVO> memList = manageService.getManageInfoMembership(paging);
		List<MemberListVO> pointlist = manageService.getManageInfoPoint(paging);

		model.addAttribute("memList", memList);
		model.addAttribute("pointlist", pointlist);
		model.addAttribute("memCnt", memList.size());
		model.addAttribute("pointCnt", pointlist.size());
		model.addAttribute("storeId", userVO.getStoreId());
		model.addAttribute("motherPage", MOTHER_PAGE);
		logger.info("===================================== END =====================================");
		return "manage/main";
	}

	@RequestMapping(value = "/log")
	public String log(@ModelAttribute("paging") Paging paging, HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		UserVO userVO = (UserVO) request.getSession().getAttribute("login");
		paging.setStoreId(userVO.getStoreId());

		List<LogVO> logList = manageService.selectLogList(paging);
		int totalCnt = manageService.selectLogListCnt(paging);
		paging.setTotalCount(totalCnt);

		model.addAttribute("paging", paging);
		model.addAttribute("logList", logList);
		model.addAttribute("motherPage", MOTHER_PAGE + "/log");
		logger.info("===================================== END =====================================");
		return "manage/log";
	}

	/**
	 * 고객이 판매
	 * @param paging
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/sell", method=RequestMethod.GET)
	public String sell(@ModelAttribute("paging") Paging paging, HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());

		UserVO userVO = (UserVO) request.getSession().getAttribute("login");
		int totalAmnt = manageService.selectStoreKor(userVO.getStoreId());
		List<UnitVO> unitList = manageService.selectRsvUnit();

		model.addAttribute("totalAmnt", totalAmnt);
		model.addAttribute("info", new ClientInfoVO());
		model.addAttribute("unitList", unitList);
		model.addAttribute("storeId", userVO.getStoreId());
		model.addAttribute("motherPage", MOTHER_PAGE + "/sell");
		logger.info("===================================== END =====================================");
		return "manage/sell";
	}
	
	@ResponseBody
	@RequestMapping(value = "/sell", method=RequestMethod.POST)
	public Map<String, Object> updateSell(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String, Object> reqMap) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		logger.info("req ::: " + reqMap.toString());
		UserVO userVO = (UserVO) req.getSession().getAttribute("login");
		
		reqMap.put("adminKey", userVO.getAdminKey());
		int resCnt = manageService.insertSellUnit(reqMap);
		
		Map<String, Object> resultMap = new HashMap<>();
		if(resCnt > 0){
			resultMap.put("result", "success");
		} else if(resCnt == -11) {
			resultMap.put("result", "uncertify");
		} else {
			resultMap.put("result", "fail");
		}
		
		logger.info("===================================== END =====================================");
		return resultMap;
	}

	@RequestMapping(value = "/buy", method=RequestMethod.GET)
	public String buy(@ModelAttribute("paging") Paging paging, HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());

		UserVO userVO = (UserVO) request.getSession().getAttribute("login");
		List<UnitVO> unitList = manageService.selectRsvUnit();

		model.addAttribute("info", new ClientInfoVO());
		model.addAttribute("unitList", unitList);
		model.addAttribute("storeId", userVO.getStoreId());
		model.addAttribute("motherPage", MOTHER_PAGE + "/buy");
		logger.info("===================================== END =====================================");
		return "manage/buy";
	}
	
	@ResponseBody
	@RequestMapping(value = "/buy", method=RequestMethod.POST)
	public Map<String, Object> updateBuy(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String, Object> reqMap) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		logger.info("req ::: " + reqMap.toString());
		UserVO userVO = (UserVO) req.getSession().getAttribute("login");
		
		reqMap.put("adminKey", userVO.getAdminKey());
		Map<String, Object> resultMap = new HashMap<>();
		int resCnt = manageService.insertBuyUnit(reqMap);
		if(resCnt > 0){
			resultMap.put("result", "success");
		} else if(resCnt == -11) {
			resultMap.put("result", "uncertify");
		} else {
			resultMap.put("result", "fail");
		}
		logger.info("===================================== END =====================================");
		return resultMap;
	}

	@RequestMapping(value = "/active")
	public String active(@ModelAttribute("paging") Paging paging, HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		String fail = (String) request.getParameter("fail");
		String upload = (String) request.getParameter("upload");
		UserVO userVO = (UserVO) request.getSession().getAttribute("login");
		paging.setStoreId(userVO.getStoreId());

		List<ActiveListVO> resultList = manageService.selectActiveList(paging);
		int totalCnt = manageService.selectActiveListCnt(paging);
		paging.setTotalCount(totalCnt);

		if(fail != null)
			model.addAttribute("fail", "Y");
		else
			model.addAttribute("fail", "N");
		
		if(upload != null)
			model.addAttribute("upload", upload);
		else
			model.addAttribute("upload", "N");
		
		model.addAttribute("paging", paging);
		model.addAttribute("resultList", resultList);
		model.addAttribute("motherPage", MOTHER_PAGE + "/active");
		model.addAttribute("storeId", userVO.getStoreId());
		model.addAttribute("WS_PATH", WS_PATH);
		logger.info("===================================== END =====================================");
		return "manage/active";
	}

	@RequestMapping(value = "/{barcode}/write", method=RequestMethod.GET)
	public String active(@PathVariable String barcode, HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		UserVO userVO = (UserVO) request.getSession().getAttribute("login");
		ClientInfoVO info = new ClientInfoVO();
		try{
			info = manageService.selectClientInfo(barcode);
			List<UnitVO> unitList = manageService.selectStoreUnit(userVO.getStoreId());
			Map<String, Object> rateMap = manageService.selectUnitBasicRate(unitList.get(0).getUnitCd());
			String totalAmnt = "";
			String totalUsrAmnt = "";
			if(info.getType().equals("member")){
				totalAmnt = manageService.selectStoreUnitAmnt(unitList.get(0).getUnitCd(), userVO.getStoreId(), "B");
				double basicRate = MapUtils.getDoubleValue(rateMap, "basicRate");
				int cost = Integer.parseInt(info.getCost());
				
				if(unitList.get(0).getUnitCd().equals("JPY"))
					cost = (int) (Math.floor(cost * 100 / basicRate));
				else
					cost = (int) (Math.floor(cost / basicRate));
				totalUsrAmnt = Utils.setStringFormatInteger(String.valueOf(cost)) + " " + unitList.get(0).getUnitCd();

				List<Map<String, Object>> payList = manageService.selectPayCoinList(userVO.getStoreId(), unitList.get(0).getUnitCd(), "B", info.getType());
				model.addAttribute("payList", payList);
			}
				
			List<Map<String, Object>> getList = manageService.selectGetCoinList(userVO.getStoreId(), unitList.get(0).getUnitCd(), "B", info.getType());

			int maxValue = manageService.selectMaxValue(unitList.get(0).getUnitCd(), MapUtils.getDoubleValue(rateMap, "basicRate"));
			
			model.addAttribute("info", info);
			model.addAttribute("getList", getList);
			model.addAttribute("unitList", unitList);
			model.addAttribute("barcode", barcode);
			model.addAttribute("maxValue", maxValue);
			model.addAttribute("basicRate", rateMap.get("basicRate"));
			model.addAttribute("basicRateText", rateMap.get("basicRateText") + " , 최대 지급 외화 : " + Utils.setStringFormatInteger(maxValue + ""));
			model.addAttribute("totalAmnt", totalAmnt);
			model.addAttribute("totalUsrAmnt", totalUsrAmnt);
		} catch (Exception e){
			String referer = request.getHeader("Referer");
			logger.info("===================================== END =====================================");
			return "redirect:" + referer + "?fail=f";
		}
		model.addAttribute("storeId", userVO.getStoreId());
		model.addAttribute("motherPage", MOTHER_PAGE + "/active");
		logger.info("===================================== END =====================================");
		return "manage/write_" + info.getType();
	}
	

	@RequestMapping(value = "/nomem", method=RequestMethod.GET)
	public String nonMem(HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		UserVO userVO = (UserVO) request.getSession().getAttribute("login");
		try{
			List<UnitVO> unitList = manageService.selectStoreUnit(userVO.getStoreId());
			if(userVO.getStoreId().equals("1")){
				UnitVO eur = new UnitVO();
				eur.setUnitCd("EUR");
				eur.setUnitNm("유럽 연합");
				unitList.add(eur);
				UnitVO cny = new UnitVO();
				cny.setUnitCd("CNY");
				cny.setUnitNm("중국 위안");
				unitList.add(cny);
			}
			Map<String, Object> rateMap = manageService.selectUnitBasicRate(unitList.get(0).getUnitCd());
			String totalAmnt = manageService.selectStoreUnitAmnt(unitList.get(0).getUnitCd(), userVO.getStoreId(), "B");
				
			List<Map<String, Object>> payList = manageService.selectPayCoinList(userVO.getStoreId(), unitList.get(0).getUnitCd(), "B", "");
			List<Map<String, Object>> getList = manageService.selectGetCoinList(userVO.getStoreId(), unitList.get(0).getUnitCd(), "B", "");
			
			double commis_rate = manageService.selectUnitCommis(unitList.get(0).getUnitCd()) / 2.0;
			double basicRate = MapUtils.getDoubleValue(rateMap, "basicRate");
			double commission = Double.parseDouble(String.format("%.2f", basicRate * commis_rate / 100.0));
			double buy_price = basicRate + commission;
			
			int maxValue = manageService.selectMaxValue(unitList.get(0).getUnitCd(), buy_price);

			model.addAttribute("payList", payList);
			model.addAttribute("getList", getList);
			model.addAttribute("unitList", unitList);
			model.addAttribute("maxValue", maxValue);
			model.addAttribute("basicRate", basicRate + commission);
			model.addAttribute("basicRateText", "매매기준율 : " + basicRate + " , 살때 수수료 : " + commission + " , 살때 가격 : " + String.format("%.2f", buy_price) + " , 최대 지급 외화 : " + Utils.setStringFormatInteger(maxValue + ""));
			model.addAttribute("totalAmnt", totalAmnt);
		} catch (Exception e){
			String referer = request.getHeader("Referer");
			logger.info("===================================== END =====================================");
			return "redirect:" + referer + "?fail=f";
		}
		model.addAttribute("storeId", userVO.getStoreId());
		model.addAttribute("motherPage", MOTHER_PAGE + "/active");
		logger.info("===================================== END =====================================");
		return "manage/write_non_member";
	}

	@RequestMapping(value = "/{barcode}/write", method=RequestMethod.POST)
	public String coinTrade(@PathVariable String barcode, HttpServletRequest request, HttpServletResponse response, Model model
			 , @RequestParam Map<String, Object> reqMap) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		logger.info("req ::: " + reqMap.toString());
		
		
		UserVO userVO = (UserVO) request.getSession().getAttribute("login");
		try{
			int res = manageService.insertTradeInfo(reqMap, userVO.getAdminId(), barcode);
			if(res == 0){
				return "redirect:/api/manage/active?upload=f";
			} else if(res == -1){
				return "redirect:/api/manage/active?upload=n";
			}
		} catch (Exception e){
			String referer = request.getHeader("Referer");
			return "redirect:" + referer;
		}
		logger.info("===================================== END =====================================");
		return "redirect:/api/manage/active?upload=t";
	}

	/*
	 * 화폐단위 변경
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", method=RequestMethod.POST)
	public Map<String, Object> deleteTrade(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String, Object> reqMap) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		
		UserVO userVO = (UserVO) req.getSession().getAttribute("login");
		String paper = MapUtils.getString(reqMap, "paper");
		String tradeType = MapUtils.getString(reqMap, "tradeType");
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int resCnt = manageService.deleteTrade(paper, tradeType, userVO.getStoreId());
		if(resCnt > 0){
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		logger.info("===================================== END =====================================");
		return resultMap;
	}

	/*
	 * 화폐단위 변경
	 */
	@ResponseBody
	@RequestMapping(value = "/changeStatus", method=RequestMethod.POST)
	public Map<String, Object> changeStatus(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String, Object> reqMap) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		
		String unitCd = MapUtils.getString(reqMap, "unitCd");
		String storeId = MapUtils.getString(reqMap, "storeId");
		String tradeType = MapUtils.getString(reqMap, "tradeType", "");
		String writeType = MapUtils.getString(reqMap, "writeType");
		
		Map<String, Object> rateMap = manageService.selectUnitBasicRate(unitCd);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String totalAmnt = "";
		if(writeType.equals("member")){
			totalAmnt = manageService.selectStoreUnitAmnt(unitCd, storeId, tradeType);
			List<Map<String, Object>> payList = manageService.selectPayCoinList(storeId, unitCd, tradeType, writeType);
			resultMap.put("payList", payList);
		}
		List<Map<String, Object>> getList = manageService.selectGetCoinList(storeId, unitCd, tradeType, writeType);

		int maxValue = manageService.selectMaxValue(unitCd, MapUtils.getDoubleValue(rateMap, "basicRate"));
		
		resultMap.put("getList", getList);
		resultMap.put("result", "success");
		resultMap.put("totalAmnt", totalAmnt);
		resultMap.put("basicRate", rateMap.get("basicRate"));
		resultMap.put("basicRateText", rateMap.get("basicRateText") + " , 최대 지급 외화 : " + Utils.setStringFormatInteger(maxValue + ""));
		resultMap.put("maxValue", maxValue);

		logger.info("===================================== END =====================================");
		return resultMap;
	}

	/*
	 * 화폐단위 변경
	 */
	@ResponseBody
	@RequestMapping(value = "/changeStatus_nomem", method=RequestMethod.POST)
	public Map<String, Object> changeStatus_nomem(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String, Object> reqMap) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		
		String unitCd = MapUtils.getString(reqMap, "unitCd");
		String storeId = MapUtils.getString(reqMap, "storeId");
		String tradeType = MapUtils.getString(reqMap, "tradeType", "");
		
		Map<String, Object> rateMap = manageService.selectUnitBasicRate(unitCd);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String totalAmnt = manageService.selectStoreUnitAmnt(unitCd, storeId, tradeType);
		List<Map<String, Object>> payList = manageService.selectPayCoinList(storeId, unitCd, tradeType, "");
		resultMap.put("payList", payList);
		List<Map<String, Object>> getList = manageService.selectGetCoinList(storeId, unitCd, tradeType, "");

		double commis_rate = manageService.selectUnitCommis(unitCd);
		
		if(unitCd.equals("CNY")){
			commis_rate = commis_rate * 0.7;
		} else {
			commis_rate = commis_rate * 0.5;
		}
		
		double basicRate = MapUtils.getDoubleValue(rateMap, "basicRate");
		double commission = Double.parseDouble(String.format("%.2f", basicRate * commis_rate / 100.0));
		double buy_price = basicRate + commission;
		double sell_price = basicRate - commission;
		
		int maxValue = manageService.selectMaxValue(unitCd, buy_price);
		
		resultMap.put("getList", getList);
		resultMap.put("result", "success");
		resultMap.put("totalAmnt", totalAmnt);
		resultMap.put("maxValue", maxValue);
		resultMap.put("basicRate", tradeType.equals("B") ? basicRate + commission : basicRate - commission);
		if(tradeType.equals("B"))
			resultMap.put("basicRateText", "매매기준율 : " + basicRate + " , 살때 수수료 : " + commission + " , 살때 가격 : " + String.format("%.2f", buy_price) + " , 최대 지급 외화 : " + Utils.setStringFormatInteger(maxValue + ""));
		else
			resultMap.put("basicRateText", "매매기준율 : " + basicRate + " , 팔때 수수료 : " + commission + " , 팔때 가격 : " + String.format("%.2f", sell_price));

		logger.info("===================================== END =====================================");
		return resultMap;
	}

	/*
	 * 고객이 외화를 팔때
	 */
	@ResponseBody
	@RequestMapping(value = "/sellUnit", method=RequestMethod.POST)
	public Map<String, Object> sellUnit(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String, Object> reqMap) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());

		String unitCd = MapUtils.getString(reqMap, "unitCd");
		String isApp = MapUtils.getString(reqMap, "isApp");
		
		Map<String, Object> resultMap = manageService.selectUnitBasicRateSell(unitCd, isApp);
		logger.info("===================================== END =====================================");
		return resultMap;
	}

	@ResponseBody
	@RequestMapping(value = "/buyUnit", method=RequestMethod.POST)
	public Map<String, Object> buyUnit(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String, Object> reqMap) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());

		UserVO userVO = (UserVO) req.getSession().getAttribute("login");
		String unitCd = MapUtils.getString(reqMap, "unitCd");
		String isApp = MapUtils.getString(reqMap, "isApp");
		
		Map<String, Object> resultMap = manageService.selectUnitBasicRateBuy(unitCd, isApp, userVO.getStoreId());
		logger.info("===================================== END =====================================");
		return resultMap;
	}

	/**
	 * 담당 지점 화폐 관리
	 * @param paging
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/money")
	public String money(HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		UserVO userVO = (UserVO) request.getSession().getAttribute("login");

		List<Map<String, Object>> coinList = manageService.selectStoreMoney(userVO.getStoreId());

		model.addAttribute("coinList", coinList);
		model.addAttribute("motherPage", MOTHER_PAGE + "/money");
		logger.info("===================================== END =====================================");
		return "manage/money";
	}
	
	/**
	 * 담당 지점 화폐 관리
	 * @param paging
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/setting")
	public String setting(HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		UserVO userVO = (UserVO) request.getSession().getAttribute("login");

		List<Map<String, Object>> coinList = manageService.selectStoreCoin(userVO.getStoreId());

		model.addAttribute("coinList", coinList);
		model.addAttribute("storeId", userVO.getStoreId());
		model.addAttribute("motherPage", MOTHER_PAGE + "/setting");
		logger.info("===================================== END =====================================");
		return "manage/setting";
	}

	/*
	 * 보유 화폐 업데이트
	 */
	@RequestMapping(value = "/coinUpdate", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> coinUpdate(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String, Object> reqMap) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		
		int cnt = manageService.updateCoin(reqMap);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap.put("result", cnt);
		logger.info("===================================== END =====================================");
		return resultMap;
	}

	/*
	 * 보유 화폐 업데이트
	 */
	@ResponseBody
	@RequestMapping(value = "/moneyUpdate", method=RequestMethod.POST)
	public Map<String, Object> moneyUpdate(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String, Object> reqMap) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		logger.info("req ::: " + reqMap.toString());
		
		UserVO userVO = (UserVO) req.getSession().getAttribute("login");
		reqMap.put("storeId", userVO.getStoreId());
		int cnt = manageService.updateMoney(reqMap);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap.put("result", cnt);
		logger.info("===================================== END =====================================");
		return resultMap;
	}

	/*
	 * 신청서 중복 체크 
	 */
	@RequestMapping(value = "/checkPaper", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> checkPaper(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String, Object> reqMap) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		
		String paper = MapUtils.getString(reqMap, "paper");
		
		int cnt = manageService.checkPaper(paper);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap.put("result", cnt);
		logger.info("===================================== END =====================================");
		return resultMap;
	}

	/*
	 * 신청서 중복 체크 
	 */
	@RequestMapping(value = "/checkCost", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> checkCost(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String, Object> reqMap) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		
		int cnt = manageService.checkCost(reqMap);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap.put("result", cnt);
		logger.info("===================================== END =====================================");
		return resultMap;
	}

	@RequestMapping(value = "/trade/excel")
	public String tradeExcel(@ModelAttribute("paging") Paging paging, HttpServletRequest request
			, HttpServletResponse response, ModelMap model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		List<ActiveListVO> resultList = manageService.selectActiveListExcel(paging);
		
		model.put("excelList", resultList);
		model.put("type", ExcelConstants.MANAGE_TRADE);

		logger.info("===================================== END =====================================");
		// WEB_INF/mvc-config.xml에 BeanNameViewResolver를 등록했기 때문에 kr.co.medisolution.common.ExelView가 열림.
		return "excelView";
	}

	@RequestMapping(value = "/setting/excel")
	public String settingExcel(HttpServletRequest request, HttpServletResponse response, ModelMap model) {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + request.getRequestURL());
		
		UserVO userVO = (UserVO) request.getSession().getAttribute("login");

		List<Map<String, Object>> coinList = manageService.selectStoreCoin(userVO.getStoreId());
		List<Map<String, Object>> exMap = manageService.selectExchangeRate();

		model.put("excelList", coinList);
		model.put("type", ExcelConstants.MANAGE_SETTING);

		logger.info("===================================== END =====================================");
		// WEB_INF/mvc-config.xml에 BeanNameViewResolver를 등록했기 때문에 kr.co.medisolution.common.ExelView가 열림.
		return "excelView";
	}
	
	@RequestMapping(value = "/send")
	public String send(HttpServletRequest request, HttpServletResponse response, Model model) {

		model.addAttribute("motherPage", MOTHER_PAGE);
		return "manage/send";
	}

	@RequestMapping(value = "/get")
	public String get(HttpServletRequest request, HttpServletResponse response, Model model) {

		model.addAttribute("motherPage", MOTHER_PAGE);
		return "manage/get";
	}

	/*
	 * 바코드 확인
	 */
	@ResponseBody
	@RequestMapping(value = "/chkBarcode", method=RequestMethod.POST)
	public Map<String, Object> chkBarcode(HttpServletRequest req, HttpServletResponse res, @RequestBody Map<String, Object> reqMap) throws Exception {

		logger.info("===================================== START : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss") + " ===================================");
		logger.info("url ::: " + req.getRequestURL());
		
		String barcode = MapUtils.getString(reqMap, "barcode");
		
		Map<String, Object> resultMap = manageService.chkBarcode(barcode);
		
		if(resultMap == null){
			resultMap = new HashMap<>();
			resultMap.put("flag", "fail");
		}

		logger.info("===================================== END =====================================");
		return resultMap;
	}

}
