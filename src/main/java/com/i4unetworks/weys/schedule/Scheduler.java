package com.i4unetworks.weys.schedule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.collections.MapUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.i4unetworks.weys.common.Constant;
import com.i4unetworks.weys.common.Utils;
import com.itextpdf.html2pdf.jsoup.nodes.Element;

@Component
public class Scheduler {

	protected static Logger logger = LoggerFactory.getLogger(Scheduler.class);

	@Autowired
	private ScheduleService scheduleService;

	@Value("${SCH}")
	private String SCH;
	
	/*
	//crontab 설정 
	*	*	*	*	*	*  수행할 명령어
	┬	┬	┬	┬	┬	┬
	│	│	│	│	│	│
	│	│	│	│	│	└───────── 요일 (0 - 6) (0 =일요일)
	│	│	│	│	└───────── 월 (1 - 12)
	│	│	│	└────────── 일 (1 - 31)
	│	│	└─────────── 시 (0 - 23)
	│	└──────────── 분 (0 - 59)
	└───────────── 초 (0 - 59)
	*/

	@Scheduled(cron = "0 0/10 8-23 * * 1-5")
	public void getExchangeRate(){
		try {
			if(SCH.equals("N")){
				return ;
			}
			logger.info("10분 마다 화폐 정보 불러오기, 현재시간은 : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss"));
			
			Map<String, Object> data = getExchangeInfo();
			if(data != null){
				String info = MapUtils.getString(data, "data").trim();
				info = info.replace("var exView = ", "");
				
				// 정보 맨 마지막에 , 가 있어서 변환이 안됨. 리스트 맵 마지막 , 제거 
				int index = info.lastIndexOf(",");
				info = info.substring(0, index) + info.substring(index + 1);
				
				ObjectMapper mapper = new ObjectMapper();
				Map<String, Object> newInfo = new HashMap<String, Object>();
				
				newInfo = mapper.readValue(info, new TypeReference<Map<String, Object>>() {});

				int resCnt = scheduleService.insertExchange(newInfo);
				
//				if(resCnt > 0){
//					scheduleService.insertNotiExc();
//				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Scheduled(cron = "0 1 21 * * *")
	public void sendRsvInfo(){
		try {
			if(SCH.equals("N")){
				return ;
			}
			logger.info("매일 21시 내일 예약자에게 여행자보험 푸시 : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss"));
			
			scheduleService.sendRsvInfo();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * 오후 11시 30분 시재 마감
	 */
	@Scheduled(cron = "0 30 23 * * *")
	public void sendMsgPeople(){
		try {

			if(SCH.equals("N")){
				return ;
			}
			scheduleService.updateCash();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * 오전 1시 센터 아이디 로그아웃
	 */
	@Scheduled(cron = "30 0 1 * * *")
	public void updateCenterLogout(){
		try {

			if(SCH.equals("N")){
				return ;
			}
			scheduleService.updateCenterLogout();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

//	@Scheduled(cron = "0 0/10 * * * *")
//	public void chagRoomDelete(){
//		try {
//			logger.info("가입한 회원 50만원어치 멤버십 주기 : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss"));
//			
//			// 가입한 회원 50만원어치 멤버십 주기
//			scheduleService.insertUserPoint();
//			
//		} catch (Exception e) {
//			logger.error(e.getMessage());
//		}
//	}

	@Scheduled(cron = "10 0 0 * * *")
	public void deleteYesterdayExchange(){
		try {
			if(SCH.equals("N")){
				return ;
			}
			logger.info("매일 0시 환율 정리 : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss"));
			
			// 어제 마지막 환율빼고 지우기
			scheduleService.deleteYesterdayExchange(Utils.getDiffDate(-179));
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

//	@Scheduled(cron = "15 0 0 * * *")
//	public void memberCheck(){
//		try {
//			logger.info("매일 0시 멤버십 정리 : " + Utils.getTodayDate("yyyy.MM.dd HH:mm:ss"));
//			
//			// 날짜 지난 멤버십 포인트 0으로 초기화
//			scheduleService.updateMemberCostZero(Utils.getDiffDate(1));
//			
//		} catch (Exception e) {
//			logger.error(e.getMessage());
//		}
//	}
	
	private Map<String, Object> getExchangeInfo() {

		Map<String, Object> result = new HashMap<String, Object>();
		try {

			URL url = new URL(Constant.EXCHANGE_REQ_URL);

			HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();

			urlConn.setDoInput(true);
			urlConn.setDoOutput(true);

			urlConn.setRequestMethod("GET");

			OutputStreamWriter output = new OutputStreamWriter(urlConn.getOutputStream());
			output.flush();

			/* Get response data. */
			StringBuilder sb = new StringBuilder();
			int HttpResult = urlConn.getResponseCode();
			if (HttpResult == HttpURLConnection.HTTP_OK) {
				BufferedReader br = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "euc-kr"));
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
				}
				br.close();

				result.put("result", "success");
				result.put("data", sb.toString());

//				logger.info("ResponseData sResult = " + sb.toString());
			} else {
				BufferedReader br = new BufferedReader(new InputStreamReader(urlConn.getErrorStream(), "utf-8"));
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
				}
				br.close();

//				logger.info("ResponseData = " + sb.toString());
				return null;
			}
		} catch (Exception e) {
//			logger.info(e.getMessage());
			return null;
		}

		return result;
	}
	
	public void getCurrency(){
		String ccs = "USD,JPY,AUD,BRL,CAD,CHF,CLP,CNY,CZK,DKK,GBP,HKD,HUF,IDR,ILS,MXN,MYR,NOK,NZD,PHP,PKR,PLN,RUB,SEK,SGD,THB,TRY,TWD,ZAR,EUR,INR,ISK";
		// 얻고자하는 통화 코드를 문자열로 저장합니다.
		String[] ccList = ccs.split(",");
		// 저장한 문자열에서 ,(쉼표)단위로 이를 잘라 문자열 배열에 담습니다.
		try {
			// 문자열 배열을 순회하면서 환율 정보 수집 함수인 getCurrencyRate를 호출합니다.
			for (int i = 0; i < ccList.length; i++){
				System.out.println(getCurrencyRate(ccList[i] + "KRW=X", ccList[i]));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Map<String , Object> getCurrencyRate(String cc, String unit) throws IOException {
		String URL = "https://finance.yahoo.com/quote/" + cc + "?p=" + cc;
		System.out.println(URL);
		Document doc = Jsoup.connect(URL).get();

		Elements elemDt = doc.select("span[data-reactid=\"18\"]");
		String dtStr = elemDt.text();
		

		Map<String , Object> returnMap = new HashMap<>();
		
		if(dtStr.contains("Market open")){
			dtStr = dtStr.replaceAll("Market open.", "");
			dtStr = dtStr.replaceAll("As of", "");
			dtStr = dtStr.replaceAll("BST.", "");
			dtStr = dtStr.trim();
			
			String dttm = Utils.getDiffDate(0) + " " + Utils.btsTime(dtStr);

			Elements elem = doc.select("span[data-reactid=\"24\"]");
			String str = elem.text();

			returnMap.put("unit", unit);
			returnMap.put("rate", str);
			returnMap.put("dttm", dttm);
		}
		
		return returnMap;

//		Elements elem = doc.select("span[data-reactid]");
//		for(Element el : elem){
//			System.out.println(el.toString());
//		}
		
	}
	
	public void kbCurrencyRate() throws Exception{
		String URL = "https://omoney.kbstar.com/quics?page=C021159";
		Document doc = Jsoup.connect(URL).get();
		
		Elements elemDt = doc.select(".hitday");
		String date = elemDt.text();
		
		StringTokenizer stDt = new StringTokenizer(date, " ");
		
		String d = "";
		String t = "";
		while(stDt.hasMoreTokens()){
			String tmp = stDt.nextToken();
			if(tmp.contains("."))
				d = tmp.replaceAll(":", "");
			else if(tmp.contains(":"))
				t = tmp;
		}
		System.out.println("time ::: " + d + " " + t);
		
		
		String [] nmArr = {"basicRate", "1", "2", "buy", "sell", "buyRate", "sellRate", "7", "usdRate", "9"};

		Elements elemRate = doc.select("#AllDsp1");
		String dtStr = elemRate.text().replaceAll(",", "").replaceAll("\\(per Y 100\\)", "");
		
		while(true){
			int startPt = dtStr.indexOf("(");
			int endPt = dtStr.indexOf(")");
			
			if(startPt == -1)
				break;
			
			String repTxt = dtStr.substring(startPt + 1, endPt);
			dtStr = dtStr.replaceAll("\\(" + repTxt + "\\)", "");
		}

		StringTokenizer st = new StringTokenizer(dtStr, " ");
		
		List<Map<String, Object>> resultList = new ArrayList<>();
		Map<String, Object> map = null;
		int index = 0; 
		while(st.hasMoreTokens()){
			
			String txt = st.nextToken();
			
			try{
				double num = Double.parseDouble(txt);
				map.put(nmArr[index], num);
				index = index + 1;
			} catch (Exception e) {
				if(map != null){
					resultList.add(map);
				}
				map = new HashMap<>();
				map.put("unit", txt);
				index = 0;
			}
		}
		System.out.println(resultList.toString());
	}
	
	public static void main(String[] args) throws IOException {
		String URL = "https://www.gugus.co.kr/shopping/new_update.asp?sm_date=0&sm_sort=C&sm_state=A&PS=48&GP=1";
		
		Document doc = Jsoup.connect(URL).get();

		Elements elemImg = doc.select(".clr_03");
		String imgSrc = elemImg.text();
		System.out.println(imgSrc);
		
		Elements elemDt = doc.select(".prodSec");
		String date = elemDt.text();
		System.out.println(date);

	}

//	public static void main(String[] args) throws IOException {
//		
//		String URL = "https://oldm.shinhan.com/pages/financialInfo/exchange_rate_gold/exchange_rate.jsp";
//		String URL = "https://spib.wooribank.com/pib/Dream?withyou=ENENG0358";
//		String URL = "https://spot.wooribank.com/pot/jcc?withyou=CMCOM0184&__ID=c012238";
//		Document doc = Jsoup.connect(URL).get();
//		
//		Elements elemDt = doc.select("#fxprint");
//		String date = elemDt.text();
//		
//		String [] unitArr = {"USD", "JPY", "EUR", "GBP", "CAD", "CHF", "HKD", "CNY", "THB", "IDR", "SEK", "AUD", "DKK", "NOK", "SAR", "KWD"
//				, "BHD", "AED", "SGD", "MYR", "NZD", "TWD", "PHP", "VND", "PLN", "RUB", "ZAR", "INR", "PKR", "BDT", "EGP", "MXN", "BRL", "BND"
//				, "ILS", "JOD", "CZK", "MNT", "FJD", "KHR", "TRY", "HUF", "QAR", "KZT", "NPR"};
//		
//		String [] nmArr = {"basicRate", "1", "2", "buy", "sell", "buyRate", "sellRate", "7", "usdRate", "9"};
//		
//		
//		System.out.println(date);
//		
//		int dateIndex = date.indexOf(" 조회기준일");
//		String dateString = date.substring(0, dateIndex).replaceAll("고시기준일시 : ", "");
//		System.out.println(dateString);
//		
//		int usdIndex = date.indexOf("USD");
//		String leftData = date.substring(usdIndex);
//		
//		System.out.println(leftData);
//		
//		StringTokenizer stDt = new StringTokenizer(date, " ");
//		
//		String d = "";
//		String t = "";
//		while(stDt.hasMoreTokens()){
//			String tmp = stDt.nextToken();
//			if(tmp.contains("."))
//				d = tmp.replaceAll(":", "");
//			else if(tmp.contains(":"))
//				t = tmp;
//		}
//		System.out.println("time ::: " + d + " " + t);
//		
//		
//		String [] nmArr = {"basicRate", "1", "2", "buy", "sell", "buyRate", "sellRate", "7", "usdRate", "9"};
//
//		Elements elemRate = doc.select("#AllDsp1");
//		String dtStr = elemRate.text().replaceAll(",", "").replaceAll("\\(per Y 100\\)", "");
//		
//		while(true){
//			int startPt = dtStr.indexOf("(");
//			int endPt = dtStr.indexOf(")");
//			
//			if(startPt == -1)
//				break;
//			
//			String repTxt = dtStr.substring(startPt + 1, endPt);
//			dtStr = dtStr.replaceAll("\\(" + repTxt + "\\)", "");
//		}
//
//		StringTokenizer st = new StringTokenizer(dtStr, " ");
//		
//		List<Map<String, Object>> resultList = new ArrayList<>();
//		Map<String, Object> map = null;
//		int index = 0; 
//		while(st.hasMoreTokens()){
//			
//			String txt = st.nextToken();
//			
//			try{
//				double num = Double.parseDouble(txt);
//				map.put(nmArr[index], num);
//				index = index + 1;
//			} catch (Exception e) {
//				if(map != null){
//					resultList.add(map);
//				}
//				map = new HashMap<>();
//				map.put("unit", txt);
//				index = 0;
//			}
//		}
//		System.out.println(resultList.toString());
//		
//		
//	}

}
