package com.i4unetworks.weys.common;

import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Utils {

	private static List<String> dayList = null;

	/* 숫자 포맷 1000 자리 넘는거 변경 */
	public static String setStringFormatInteger(String number) {

		String underNum = "";
		if (number.contains(".")) {
			underNum = number.substring(number.indexOf("."));
			number = number.substring(0, number.indexOf("."));
		}
		int length = number.length();
		if (length < 4)
			return number + underNum;

		String prefix = number.substring(0, length - 3);
		String suffix = number.substring(length - 3);

		return setStringFormatInteger(prefix) + "," + suffix + underNum;
	}

	/* 오늘 날짜 format 에 맞춰 가져오기 */
	public static String getTodayDate() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);
		return sdf.format(date);
	}

	public static String getTodayDate(String format) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.KOREA);
		return sdf.format(date);
	}

	/**
	 * Comment : 특정 날짜로 부터 날짜 계산해서 구하기
	 * 
	 * @param when
	 *            날짜 차이 ( 2일전, 4일전 )
	 * @param year
	 *            해당 년도
	 * @param month
	 *            해당 월
	 * @param day
	 *            해당 일
	 */
	public static String getDateFormat(int when, int year, int month, int day) {

		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day);
		cal.add(Calendar.DATE, when);
		Date d = cal.getTime();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);
		String date = sdf.format(d);

		return date;
	}

	/**
	 * Comment : 오늘부터 몇일 날짜 차이 구하기
	 * 
	 * @param when
	 *            날짜 차이 ( 2일전, 4일전 )
	 * @param year
	 *            해당 년도
	 * @param month
	 *            해당 월
	 * @param day
	 *            해당 일
	 */
	public static String getDiffDate(int when) {

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, when);
		Date d = cal.getTime();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);
		String date = sdf.format(d);

		return date;
	}
	
	/**
	 * 오늘 요일 구하기
	 * 
	 * @return
	 */
	public static int getTdayDay(String dt) throws ParseException {
		Calendar cal = Calendar.getInstance();
		if (dt != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
			Date date = sdf.parse(dt);
			cal.setTime(date);
		}
		return cal.get(Calendar.DAY_OF_WEEK) - 1;
	}

	/**
	 * 해당 월의 전달 마지막 날짜 구하기
	 */
	public static Map<String, Object> getLastMonthMaxDay() {
		Calendar cal = Calendar.getInstance();

		int year = cal.get(Calendar.YEAR);
		int lastMonth = cal.get(Calendar.MONTH) - 1;
		if (lastMonth < 0) {
			year = year - 1;
			lastMonth = 11;
		}
		cal.set(year, lastMonth, 1);

		Date d = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);
		String date = sdf.format(d);

		Map<String, Object> resMap = new HashMap<>();
		resMap.put("startDt", date);

		cal.set(year, lastMonth, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		d = cal.getTime();
		date = sdf.format(d);
		resMap.put("endDt", date + " 23:59:59");
		return resMap;
	}

	/**
	 * 1년전 1일 날짜 구하기
	 * 
	 * @return
	 */
	public static String getLastYear() {
		Calendar cal = Calendar.getInstance();

		int year = cal.get(Calendar.YEAR);
		int lastMonth = cal.get(Calendar.MONTH) - 11;

		if (lastMonth < 0) {
			year = year - 1;
			lastMonth = lastMonth + 12;
		}
		cal.set(year, lastMonth, 1);

		Date d = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);
		return sdf.format(d);
	}

	public static void createGiftiShow() throws Exception {

		URL url = new URL("https://giftishowgw.giftishow.co.kr/media/salelist.asp?MDCODE=M000101425");
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0");

		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = builder.parse(urlConnection.getInputStream());

		NodeList items = doc.getElementsByTagName("goodslist");

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Sample sheet");

		int rownum = 0;

		for (int ii = 0; ii < items.getLength(); ii++) {
			Element item = (Element) items.item(ii);

			String goods_id = getValue(item, "goods_id");
			String category1 = getValue(item, "category1");
			String category2 = getValue(item, "category2");
			String affiliate = getValue(item, "affiliate");
			String desc = getValue(item, "desc");
			String goods_nm = getValue(item, "goods_nm");
			String goods_img = getValue(item, "goods_img");
			String sale_price = getValue(item, "sale_price");
			String sale_vat = getValue(item, "sale_vat");
			String total_price = getValue(item, "total_price");
			String period_end = getValue(item, "period_end");
			String limit_date = getValue(item, "limit_date");
			String end_date = getValue(item, "end_date");

			Row row = sheet.createRow(rownum++);
			row.createCell(0).setCellValue(goods_id);
			row.createCell(1).setCellValue(category1);
			row.createCell(2).setCellValue(category2);
			row.createCell(3).setCellValue(affiliate);
			row.createCell(4).setCellValue(desc);
			row.createCell(5).setCellValue(goods_nm);
			row.createCell(6).setCellValue(goods_img);
			row.createCell(7).setCellValue(sale_price);
			row.createCell(8).setCellValue(sale_vat);
			row.createCell(9).setCellValue(total_price);
			row.createCell(10).setCellValue(period_end);
			row.createCell(11).setCellValue(limit_date);
			row.createCell(12).setCellValue(end_date);
		}

		FileOutputStream out = new FileOutputStream(new File("/Users/dslee/Desktop/kthow.xls"));
		workbook.write(out);
		out.close();
		System.out.println("Excel written successfully..");
	}

	public static String getValue(Element parent, String nodeName) {
		return parent.getElementsByTagName(nodeName).item(0).getFirstChild().getNodeValue();
	}

	public static String getDayString(int day) {

		if (dayList == null) {
			dayList = new ArrayList<>();
			dayList.add("일");
			dayList.add("월");
			dayList.add("화");
			dayList.add("수");
			dayList.add("목");
			dayList.add("금");
			dayList.add("토");
		}
		return dayList.get(day);
	}
	
	public static String getNextDt(String startDt, int index) {

		StringTokenizer st = new StringTokenizer(startDt, ".");
		int year = Integer.parseInt(st.nextToken());
		int month = Integer.parseInt(st.nextToken()) - 1;
		int day = Integer.parseInt(st.nextToken());

		return getDateFormat(index, year, month, day);
	}

	public static String getDiffMonth(int when) {

		Calendar cal = Calendar.getInstance();
		if (when == 1) {
			cal.add(Calendar.DATE, 30);
		} else {
			cal.add(Calendar.MONTH, when);
			cal.add(Calendar.DATE, -1);
		}
		Date d = cal.getTime();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);
		return sdf.format(d);
	}

	public static long diffTwoDate(String date1, String date2) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");

		long days = 0;
		try {
			Date d1 = sdf.parse(date1);
			Date d2 = sdf.parse(date2);

			long diff = d1.getTime() - d2.getTime();
			days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			return 0;
		}
		return days;
	}
	
	public static String getRandomKey(int limit) {

		String code = "";
		for (int i = 0; i < limit; i++) {
			int num = (int) (Math.random() * 36);
			String str = "";
			if (num < 10) {
				str = num + "";
			} else {
				num = num + 55;
				str = (char) num + "";
			}

			if (i == 0) {
				code = str;
			} else {
				code = code + str;
			}
		}
		return code;
	}

	public static String makeCode(int length) {

		String code = "";
		for (int i = 0; i < length; i++) {
			int num = (int) (Math.random() * 36);
			String str = "";
			if (num < 10) {
				str = num + "";
			} else {
				num = num + 55;
				str = (char) num + "";
			}

			if (i == 0) {
				code = str;
			} else {
				code = code + str;
			}
		}
		return code;
	}
	
	public static String btsTime(String tm){
		
		int ampm = 0;
		boolean bAm = false;
		if(tm.contains("PM")){
			ampm = 12;
			tm = tm.replaceAll("PM", "");
		} else {
			tm = tm.replaceAll("AM", "");
			bAm = true;
		}
		
		String [] time = tm.split(":");
		String hour = time[0];
		String min = time[1];
		
		if(bAm && hour.equals("12")){
			hour = "0";
		}
		
		int iHour = Integer.parseInt(hour) + 8 + ampm;
		if(iHour > 24)
			iHour = iHour - 24;
		
		String res = iHour + ":" + min;
		
		return res;
	}

	public static String checkTimeRsv() {

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR, -2);
		Date d = cal.getTime();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		String date = sdf.format(d);

		return date;
	}
	

}
