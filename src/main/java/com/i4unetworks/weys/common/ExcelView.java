package com.i4unetworks.weys.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import com.i4unetworks.weys.manage.ActiveListVO;
import com.i4unetworks.weys.rsv.RsvExcelVO;
import com.i4unetworks.weys.rsv.RsvListVO;
import com.i4unetworks.weys.store.StoreChangeVO;
import com.i4unetworks.weys.trade.TradeDetailVO;
import com.i4unetworks.weys.user.MemberActiveVO;
import com.i4unetworks.weys.user.UserListVO;
import com.i4unetworks.weys.user.UserPointVO;

@Component
public class ExcelView extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request
			, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		// 파일이름 설정 {ExcelType}_{DATE}.xls
		String type = (String) model.get("type");
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String fileName = type + "_" + sdf.format(calendar.getTime()) + ".xlsx";

		Workbook workBook = new XSSFWorkbook();
		Sheet workSheet = null;
		Row row = null;
		

		/* 사용자 정보 */
		if(type.equals(ExcelConstants.USER)) {
			@SuppressWarnings("unchecked")
			List<UserListVO> excelList = (List<UserListVO>) model.get("excelList");
			// Sheet 생성
			workSheet = workBook.createSheet(type);
			row = workSheet.createRow(0);
			
			// 헤더 생성
			int rowNum = 0;
			for(String str : ExcelConstants.TABLE_HEADER_USER) {
				row.createCell(rowNum).setCellValue(ExcelConstants.TABLE_HEADER_USER[rowNum++]);
			}
			
			// 내용 생성
			UserListVO item;
			// "아이디", "가입일", "이름", "이메일", "전화번호", "예약중", "예약완료", "보유한도", "쿠폰수"
			for(int i=0; i<excelList.size(); i++) {
				row = workSheet.createRow(i+1); // 헤더가 0번이라서.
				item = excelList.get(i);
				row.createCell(0).setCellValue(item.getUsrId());
				row.createCell(1).setCellValue(item.getRegDttm());
				row.createCell(2).setCellValue(item.getUsrNm());
				row.createCell(3).setCellValue(item.getUsrEmail());
				row.createCell(4).setCellValue(item.getUsrTel());
				row.createCell(5).setCellValue(item.getrCnt());
				row.createCell(6).setCellValue(item.getRdCnt());
				row.createCell(7).setCellValue(item.getcCnt());
			}
			
			// 컬럼 Width 자동조절
			for(int i=0; i<ExcelConstants.TABLE_HEADER_USER.length; i++) {
				workSheet.autoSizeColumn(i);
			}
		}

		/* 사용자 상세 정보 */
//		if(type.equals(ExcelConstants.USER_DETAIL)) {
//			@SuppressWarnings("unchecked")
//			
//			UserListVO info = (UserListVO) model.get("info");
//			List<MemberActiveVO> membership = (List<MemberActiveVO>) model.get("membership");
//			List<UserPointVO> changePoint = (List<UserPointVO>) model.get("changePoint");
//			List<UserPointVO> usePoint = (List<UserPointVO>) model.get("usePoint");
//			
//			
//			/**
//			 * 멤버십 SHEET 
//			 */
//			Sheet workSheet2 = workBook.createSheet("멤버십");
//			row = workSheet2.createRow(0);
//			
//			// 헤더 생성
//			int rowNum = 0;
//			for(String str : ExcelConstants.TABLE_HEADER_USER_DETAIL_INFO) {
//				row.createCell(rowNum).setCellValue(ExcelConstants.TABLE_HEADER_USER_DETAIL_INFO[rowNum++]);
//			}
//			// "닉네임", "메일", "이름", "멤버십가입일", "잔여한도", "만료기간", "포인트"
//			row = workSheet2.createRow(1); // 헤더가 0번이라서.
//			row.createCell(0).setCellValue(info.getUsrNick());
//			row.createCell(1).setCellValue(info.getUsrEmail());
//			row.createCell(2).setCellValue(info.getUsrNm());
//			row.createCell(3).setCellValue(info.getStartDt());
//			row.createCell(4).setCellValue(info.getCost());
//			row.createCell(5).setCellValue(info.getEndDt());
//			row.createCell(6).setCellValue(info.getUsrPoint());
//			
//			// 헤더 생성
//			rowNum = 0;
//			row = workSheet2.createRow(4);
//			for(String str : ExcelConstants.TABLE_HEADER_USER_DETAIL_MEMBER) {
//				row.createCell(rowNum).setCellValue(ExcelConstants.TABLE_HEADER_USER_DETAIL_MEMBER[rowNum++]);
//			}
//			
//			// 내용 생성
//			MemberActiveVO item;
//			// "거래일", "구분", "통화", "수령금액", "매매기준율", "지급금액", "신청서", "지점", "담당자"
//			for(int i=0; i<membership.size(); i++) {
//				row = workSheet2.createRow(i+5); // 헤더가 4번이라서.
//				item = membership.get(i);
//				row.createCell(0).setCellValue(item.getRegDttm());
//				row.createCell(1).setCellValue(item.getTp());
//				row.createCell(2).setCellValue(item.getUnit());
//				row.createCell(3).setCellValue(item.getGetAmnt());
//				row.createCell(4).setCellValue(item.getBasicRate());
//				row.createCell(5).setCellValue(item.getPayAmnt());
//				row.createCell(6).setCellValue(item.getPaper());
//				row.createCell(7).setCellValue(item.getStoreNm());
//				row.createCell(8).setCellValue(item.getAdminName());
//			}
//			
//			// 컬럼 Width 자동조절
//			for(int i=0; i<ExcelConstants.TABLE_HEADER_USER_DETAIL_MEMBER.length; i++) {
//				workSheet2.autoSizeColumn(i);
//			}
//			/**
//			 * 멤버십 SHEET 끝
//			 */
//
//			/**
//			 * 포인트 전환 SHEET 
//			 */
//			Sheet workSheet3 = workBook.createSheet("포인트 전환");
//			row = workSheet3.createRow(0);
//			
//			// 헤더 생성
//			rowNum = 0;
//			for(String str : ExcelConstants.TABLE_HEADER_USER_DETAIL_INFO) {
//				row.createCell(rowNum).setCellValue(ExcelConstants.TABLE_HEADER_USER_DETAIL_INFO[rowNum++]);
//			}
//			// "닉네임", "메일", "이름", "멤버십가입일", "잔여한도", "만료기간", "포인트"
//			row = workSheet3.createRow(1); // 헤더가 0번이라서.
//			row.createCell(0).setCellValue(info.getUsrNick());
//			row.createCell(1).setCellValue(info.getUsrEmail());
//			row.createCell(2).setCellValue(info.getUsrNm());
//			row.createCell(3).setCellValue(info.getStartDt());
//			row.createCell(4).setCellValue(info.getCost());
//			row.createCell(5).setCellValue(info.getEndDt());
//			row.createCell(6).setCellValue(info.getUsrPoint());
//			
//			// 헤더 생성
//			rowNum = 0;
//			row = workSheet3.createRow(4);
//			for(String str : ExcelConstants.TABLE_HEADER_USER_DETAIL_CPOINT) {
//				row.createCell(rowNum).setCellValue(ExcelConstants.TABLE_HEADER_USER_DETAIL_CPOINT[rowNum++]);
//			}
//			
//			// 내용 생성
//			UserPointVO item2;
//			// "거래일", "통화", "수령금액", "매매기준율", "지급포인트", "신청서", "지점", "담당자"
//			for(int i=0; i<membership.size(); i++) {
//				row = workSheet3.createRow(i+5); // 헤더가 4번이라서.
//				item2 = changePoint.get(i);
//				row.createCell(0).setCellValue(item2.getRegDttm());
//				row.createCell(1).setCellValue(item2.getUnit());
//				row.createCell(2).setCellValue(item2.getGetAmnt());
//				row.createCell(3).setCellValue(item2.getBasicRate());
//				row.createCell(4).setCellValue(item2.getPoint());
//				row.createCell(5).setCellValue(item2.getPaper());
//				row.createCell(6).setCellValue(item2.getStoreNm());
//				row.createCell(7).setCellValue(item2.getAdminName());
//			}
//			
//			// 컬럼 Width 자동조절
//			for(int i=0; i<ExcelConstants.TABLE_HEADER_USER_DETAIL_CPOINT.length; i++) {
//				workSheet3.autoSizeColumn(i);
//			}
//			/**
//			 * 포인트 전환 SHEET 끝
//			 */
//
//			/**
//			 * 포인트 사용 SHEET 
//			 */
//			Sheet workSheet4 = workBook.createSheet("포인트 사용");
//			row = workSheet4.createRow(0);
//			
//			// 헤더 생성
//			rowNum = 0;
//			for(String str : ExcelConstants.TABLE_HEADER_USER_DETAIL_INFO) {
//				row.createCell(rowNum).setCellValue(ExcelConstants.TABLE_HEADER_USER_DETAIL_INFO[rowNum++]);
//			}
//			// "닉네임", "메일", "이름", "멤버십가입일", "잔여한도", "만료기간", "포인트"
//			row = workSheet4.createRow(1); // 헤더가 0번이라서.
//			row.createCell(0).setCellValue(info.getUsrNick());
//			row.createCell(1).setCellValue(info.getUsrEmail());
//			row.createCell(2).setCellValue(info.getUsrNm());
//			row.createCell(3).setCellValue(info.getStartDt());
//			row.createCell(4).setCellValue(info.getCost());
//			row.createCell(5).setCellValue(info.getEndDt());
//			row.createCell(6).setCellValue(info.getUsrPoint());
//			
//			// 헤더 생성
//			rowNum = 0;
//			row = workSheet4.createRow(4);
//			for(String str : ExcelConstants.TABLE_HEADER_USER_DETAIL_UPOINT) {
//				row.createCell(rowNum).setCellValue(ExcelConstants.TABLE_HEADER_USER_DETAIL_UPOINT[rowNum++]);
//			}
//			
//			// 내용 생성
//			// "거래일", "상품명", "사용포인트", "거래코드"
//			for(int i=0; i<membership.size(); i++) {
//				row = workSheet4.createRow(i+5); // 헤더가 4번이라서.
//				item2 = usePoint.get(i);
//				row.createCell(0).setCellValue(item2.getRegDttm());
//				row.createCell(1).setCellValue(item2.getMemo());
//				row.createCell(2).setCellValue(item2.getPoint());
//				row.createCell(3).setCellValue(item2.getPaper());
//			}
//			
//			// 컬럼 Width 자동조절
//			for(int i=0; i<ExcelConstants.TABLE_HEADER_USER_DETAIL_INFO.length; i++) {
//				workSheet4.autoSizeColumn(i);
//			}
//			/**
//			 * 포인트 사용 SHEET 끝
//			 */
//		}

		/* 거래 정보 */
		if(type.equals(ExcelConstants.TRADE)) {
			@SuppressWarnings("unchecked")
			List<TradeDetailVO> excelList = (List<TradeDetailVO>) model.get("excelList");
			// Sheet 생성
			workSheet = workBook.createSheet(type);
			row = workSheet.createRow(0);
			
			// 헤더 생성
			int rowNum = 0;
			for(String str : ExcelConstants.TABLE_HEADER_TRADE) {
				row.createCell(rowNum).setCellValue(ExcelConstants.TABLE_HEADER_TRADE[rowNum++]);
			}
			
			// 내용 생성
			TradeDetailVO item;
			// "등록일", "거래구분", "통화", "금액", "등록내용", "지역", "등록자 이메일", "닉네임", "거래상태"
			for(int i=0; i<excelList.size(); i++) {
				row = workSheet.createRow(i+1); // 헤더가 0번이라서.
				item = excelList.get(i);
				row.createCell(0).setCellValue(item.getTradeDt());
				row.createCell(1).setCellValue(item.getTradeTp());
				row.createCell(2).setCellValue(item.getTradeUnit());
				row.createCell(3).setCellValue(item.getTradeAmnt());
				row.createCell(4).setCellValue(item.getTradeText());
				row.createCell(5).setCellValue(item.getTradeSido());
				row.createCell(6).setCellValue(item.getUsrEmail());
				row.createCell(7).setCellValue(item.getUsrNick());
				row.createCell(8).setCellValue(item.getTradeSt());
			}
			
			// 컬럼 Width 자동조절
			for(int i=0; i<ExcelConstants.TABLE_HEADER_TRADE.length; i++) {
				workSheet.autoSizeColumn(i);
			}
		}


		/* 지점 거래 상세 내역 */
		if(type.equals(ExcelConstants.STORE_DETAIL)) {
			@SuppressWarnings("unchecked")
			List<StoreChangeVO> memberList = (List<StoreChangeVO>) model.get("memberList");
			List<StoreChangeVO> pointList = (List<StoreChangeVO>) model.get("pointList");
			
			// 멤버십 Sheet 생성
			Sheet workSheet1 = workBook.createSheet("멤버십");
			row = workSheet1.createRow(0);
			
			// 헤더 생성
			int rowNum = 0;
			for(String str : ExcelConstants.TABLE_HEADER_STORE_DETAIL_MEMBER) {
				row.createCell(rowNum).setCellValue(ExcelConstants.TABLE_HEADER_STORE_DETAIL_MEMBER[rowNum++]);
			}
			
			// 내용 생성
			StoreChangeVO item;
			// "거래일", "거래상세", "통화", "수령금액", "매매기준율", "지급금액", "닉네임", "담당자", "신청서"
			for(int i=0; i<memberList.size(); i++) {
				row = workSheet1.createRow(i+1); // 헤더가 0번이라서.
				item = memberList.get(i);
				row.createCell(0).setCellValue(item.getRegDttm());
				row.createCell(1).setCellValue(item.getTp());
				row.createCell(2).setCellValue(item.getUnit());
				row.createCell(3).setCellValue(item.getGetAmnt());
				row.createCell(4).setCellValue(item.getBasicRate());
				row.createCell(5).setCellValue(item.getPayAmnt());
				row.createCell(6).setCellValue(item.getUsrNick());
				row.createCell(7).setCellValue(item.getAdminName());
				row.createCell(8).setCellValue(item.getPaper());
			}
			
			// 컬럼 Width 자동조절
			for(int i=0; i<ExcelConstants.TABLE_HEADER_STORE_DETAIL_MEMBER.length; i++) {
				workSheet1.autoSizeColumn(i);
			}
			
			// 포인트 Sheet 생성
			Sheet workSheet2 = workBook.createSheet("포인트");
			row = workSheet2.createRow(0);
			
			// 헤더 생성
			rowNum = 0;
			for(String str : ExcelConstants.TABLE_HEADER_STORE_DETAIL_POINT) {
				row.createCell(rowNum).setCellValue(ExcelConstants.TABLE_HEADER_STORE_DETAIL_POINT[rowNum++]);
			}
			
			// 내용 생성
			// "거래일", "거래상세", "통화", "수령금액", "매매기준율", "지급포인트", "닉네임", "담당자", "신청서"
			for(int i=0; i<pointList.size(); i++) {
				row = workSheet2.createRow(i+1); // 헤더가 0번이라서.
				item = pointList.get(i);
				row.createCell(0).setCellValue(item.getRegDttm());
				row.createCell(1).setCellValue(item.getTp());
				row.createCell(2).setCellValue(item.getUnit());
				row.createCell(3).setCellValue(item.getGetAmnt());
				row.createCell(4).setCellValue(item.getBasicRate());
				row.createCell(5).setCellValue(item.getPoint());
				row.createCell(6).setCellValue(item.getUsrNick());
				row.createCell(7).setCellValue(item.getAdminName());
				row.createCell(8).setCellValue(item.getPaper());
			}
			
			// 컬럼 Width 자동조절
			for(int i=0; i<ExcelConstants.TABLE_HEADER_STORE_DETAIL_POINT.length; i++) {
				workSheet2.autoSizeColumn(i);
			}
		}

		/* 거래내역 정보 */
		if(type.equals(ExcelConstants.MANAGE_TRADE)) {
			@SuppressWarnings("unchecked")
			List<ActiveListVO> excelList = (List<ActiveListVO>) model.get("excelList");
			// Sheet 생성
			workSheet = workBook.createSheet(type);
			row = workSheet.createRow(0);
			
			// 헤더 생성
			int rowNum = 0;
			for(String str : ExcelConstants.TABLE_HEADER_MANAGE_TRADE) {
				row.createCell(rowNum).setCellValue(ExcelConstants.TABLE_HEADER_MANAGE_TRADE[rowNum++]);
			}
			
			// 내용 생성
			ActiveListVO item;
			// "날짜", "No", "거래구분", "통화", "금액", "환율", "받은 금액", "비고", "거래자"
			for(int i=0; i<excelList.size(); i++) {
				row = workSheet.createRow(i+1); // 헤더가 0번이라서.
				item = excelList.get(i);
				row.createCell(0).setCellValue(item.getRegDttm());
				row.createCell(1).setCellValue(i + 1);
				row.createCell(2).setCellValue(item.getType());
				row.createCell(3).setCellValue(item.getUnit());
				row.createCell(4).setCellValue(Utils.setStringFormatInteger(item.getGetAmnt()));
				row.createCell(5).setCellValue(Utils.setStringFormatInteger(item.getBasicRate()));
				row.createCell(6).setCellValue(Utils.setStringFormatInteger(item.getPayAmnt()));
				row.createCell(7).setCellValue(item.getUsrNick());
				row.createCell(8).setCellValue(item.getPaper());
			}
			
			// 컬럼 Width 자동조절
			for(int i=0; i<ExcelConstants.TABLE_HEADER_MANAGE_TRADE.length; i++) {
				workSheet.autoSizeColumn(i);
			}
		}
		/* 시제 정보 */
		if(type.equals(ExcelConstants.MANAGE_SETTING)) {
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> excelList = (List<Map<String, Object>>) model.get("excelList");
			// Sheet 생성
			workSheet = workBook.createSheet(type);
			row = workSheet.createRow(0);
			
			// 헤더 생성
			int rowNum = 0;
			for(String str : ExcelConstants.TABLE_HEADER_MANAGE_SETTING) {
				Cell cell = row.createCell(rowNum);
				cell.setCellValue(ExcelConstants.TABLE_HEADER_MANAGE_SETTING[rowNum]);
				
				CellStyle style = workBook.createCellStyle();
				style.setBorderTop(CellStyle.BORDER_MEDIUM);
				style.setTopBorderColor(HSSFColor.BLACK.index);
				style.setBorderBottom(CellStyle.BORDER_MEDIUM);
				style.setBottomBorderColor(HSSFColor.BLACK.index);
				style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
				style.setFillPattern(CellStyle.SOLID_FOREGROUND);
				if(rowNum == 0){
					style.setBorderLeft(CellStyle.BORDER_MEDIUM);
					style.setLeftBorderColor(HSSFColor.BLACK.index);
				} else if(rowNum == ExcelConstants.TABLE_HEADER_MANAGE_SETTING.length - 1){
					style.setBorderRight(CellStyle.BORDER_MEDIUM);
					style.setRightBorderColor(HSSFColor.BLACK.index);
				}
				cell.setCellStyle(style);
				rowNum++;
			}
			
			// 내용 생성
			String unitNm = "";
			// "화폐", "통화명", "금고 수량", "금고 금액", "환전소 금액", "예약관리 금액", "인천 금액", " 금액", "매매기준율", "한화"
			int index = 1;
			int startIndex = 0;
			for(Map<String, Object> item : excelList){
				row = workSheet.createRow(index);
				
				if(unitNm.equals(MapUtils.getString(item, "UNIT_NM"))){
					int coinId = MapUtils.getIntValue(item, "COIN_ID");
					if(coinId == 99999){
						CellStyle styleLeft = workBook.createCellStyle();
						styleLeft.setBorderLeft(CellStyle.BORDER_MEDIUM);
						styleLeft.setLeftBorderColor(HSSFColor.BLACK.index);
						styleLeft.setBorderBottom(CellStyle.BORDER_MEDIUM);
						styleLeft.setBottomBorderColor(HSSFColor.BLACK.index);
						CellStyle styleRight = workBook.createCellStyle();
						styleRight.setBorderRight(CellStyle.BORDER_MEDIUM);
						styleRight.setRightBorderColor(HSSFColor.BLACK.index);
						styleRight.setBorderBottom(CellStyle.BORDER_MEDIUM);
						styleRight.setBottomBorderColor(HSSFColor.BLACK.index);
						styleRight.setFillForegroundColor(HSSFColor.SEA_GREEN.index);
						styleRight.setFillPattern(CellStyle.SOLID_FOREGROUND);

						CellStyle commonStyle = workBook.createCellStyle();
						commonStyle.setBorderBottom(CellStyle.BORDER_MEDIUM);
						commonStyle.setBottomBorderColor(HSSFColor.BLACK.index);
						commonStyle.setFillForegroundColor(HSSFColor.SEA_GREEN.index);
						commonStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
						
						Cell cell = row.createCell(0);
						cell.setCellValue(unitNm);
						cell.setCellStyle(styleLeft);

						cell = row.createCell(1);
						cell.setCellValue("합계");
						cell.setCellStyle(commonStyle);
						
						cell = row.createCell(2);
						cell.setCellValue(Utils.setStringFormatInteger(MapUtils.getString(item, "SAFE_CNT")));
						cell.setCellStyle(commonStyle);

						cell = row.createCell(3);
						cell.setCellValue(Utils.setStringFormatInteger(MapUtils.getString(item, "SAFE_VAL")));
						cell.setCellStyle(commonStyle);

						cell = row.createCell(4);
						cell.setCellValue(Utils.setStringFormatInteger(MapUtils.getString(item, "S_VAL", "0")));
						cell.setCellStyle(commonStyle);

						cell = row.createCell(5);
						cell.setCellValue(Utils.setStringFormatInteger(MapUtils.getString(item, "R_VAL", "0")));
						cell.setCellStyle(commonStyle);

						cell = row.createCell(6);
						cell.setCellValue(Utils.setStringFormatInteger(MapUtils.getString(item, "I_VAL", "0")));
						cell.setCellStyle(commonStyle);

						cell = row.createCell(7);
						cell.setCellValue(Utils.setStringFormatInteger(MapUtils.getString(item, "BASIC_RATE", "0")));
						cell.setCellStyle(commonStyle);
						
						int sum = MapUtils.getIntValue(item, "SAFE_VAL") + MapUtils.getIntValue(item, "S_VAL") + MapUtils.getIntValue(item, "R_VAL") + MapUtils.getIntValue(item, "I_VAL");
						double basicRate = MapUtils.getDoubleValue(item, "BASIC_RATE");
						
						int total = sum;
						if(!"KOR".equals(MapUtils.getString(item, "UNIT_CD"))){
							total = (int) (total * basicRate);
							if("JPY".equals(MapUtils.getString(item, "UNIT_CD"))){
								total = total / 100;
							}
						}

						cell = row.createCell(8);
						cell.setCellValue(Utils.setStringFormatInteger(total + ""));
						cell.setCellStyle(commonStyle);
						
						workSheet.addMergedRegion(new CellRangeAddress(startIndex, index, 0, 0));
						
						startIndex = index;
						unitNm = MapUtils.getString(item, "UNIT_NM");	
					} else {
						CellStyle styleLeft = workBook.createCellStyle();
						styleLeft.setBorderLeft(CellStyle.BORDER_MEDIUM);
						styleLeft.setLeftBorderColor(HSSFColor.BLACK.index);
						CellStyle styleRight = workBook.createCellStyle();
						styleRight.setBorderRight(CellStyle.BORDER_MEDIUM);
						styleRight.setRightBorderColor(HSSFColor.BLACK.index);
						styleRight.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
						styleRight.setFillPattern(CellStyle.SOLID_FOREGROUND);
						
						Cell cell = row.createCell(0);
						cell.setCellValue(MapUtils.getString(item, "UNIT_NM"));
						cell.setCellStyle(styleLeft);
						row.createCell(1).setCellValue(MapUtils.getString(item, "COIN_NM"));
						row.createCell(2).setCellValue(Utils.setStringFormatInteger(MapUtils.getString(item, "SAFE_CNT")));
						row.createCell(3).setCellValue(Utils.setStringFormatInteger(MapUtils.getString(item, "SAFE_VAL")));
						row.createCell(4).setCellValue("");
						row.createCell(5).setCellValue("");
						row.createCell(6).setCellValue("");
						row.createCell(7).setCellValue("");
						row.createCell(8).setCellValue("");
					}
				} else {
					unitNm = MapUtils.getString(item, "UNIT_NM");
					
					CellStyle styleLeft = workBook.createCellStyle();
					styleLeft.setBorderLeft(CellStyle.BORDER_MEDIUM);
					styleLeft.setLeftBorderColor(HSSFColor.BLACK.index);
					CellStyle styleRight = workBook.createCellStyle();
					styleRight.setBorderRight(CellStyle.BORDER_MEDIUM);
					styleRight.setRightBorderColor(HSSFColor.BLACK.index);
					styleRight.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
					styleRight.setFillPattern(CellStyle.SOLID_FOREGROUND);
					
					Cell cell = row.createCell(0);
					cell.setCellValue(unitNm);
					cell.setCellStyle(styleLeft);
					row.createCell(1).setCellValue(MapUtils.getString(item, "COIN_NM"));
					row.createCell(2).setCellValue(Utils.setStringFormatInteger(MapUtils.getString(item, "SAFE_CNT")));
					row.createCell(3).setCellValue(Utils.setStringFormatInteger(MapUtils.getString(item, "SAFE_VAL")));
					row.createCell(4).setCellValue("");
					row.createCell(5).setCellValue("");
					row.createCell(6).setCellValue("");
					row.createCell(7).setCellValue("");
					row.createCell(8).setCellValue("");
					startIndex = index;
				}
				index = index + 1;
			}
			
			// 컬럼 Width 자동조절
			for(int i=0; i<ExcelConstants.TABLE_HEADER_MANAGE_SETTING.length; i++) {
				workSheet.autoSizeColumn(i);
			}
		}
		

		/* 거래 정보 */
		if(type.equals(ExcelConstants.RSV_LIST)) {
			@SuppressWarnings("unchecked")
			List<RsvExcelVO> excelList = (List<RsvExcelVO>) model.get("info");
			// Sheet 생성
			workSheet = workBook.createSheet(type);
			row = workSheet.createRow(0);
			
			// 헤더 생성
			int rowNum = 0;
			for(String str : ExcelConstants.TABLE_HEADER_RSV_LIST) {
				row.createCell(rowNum).setCellValue(ExcelConstants.TABLE_HEADER_RSV_LIST[rowNum++]);
			}
			
			// 내용 생성
			RsvExcelVO item;
			// "구분", "거래코드", "예약번호", "거래일시", "구매통화", "외화금액", "매매기준율", "원화금액", "수수료"
			// , "외화배송료", "지불금액", "권종선택", "고객성명", "휴대전화", "수령장KR", "수령장소ENG", "수령예정일시"
			for(int i=0; i<excelList.size(); i++) {
				row = workSheet.createRow(i+1); // 헤더가 0번이라서.
				item = excelList.get(i);
				
				String rsvFrom = item.getRsvForm();
				String rsvDt = "";
				if(rsvFrom.equals("R")){
					rsvDt = item.getRsvDt() + " " + item.getRsvTm();
				} else {
					String tm = item.getRsvTm();
					int deliverTm = item.getDeliverTime();
					
					StringTokenizer st = new StringTokenizer(tm, ":");
					String sHour = st.nextToken();
					String sMin = st.nextToken();
					
					int lastHour = Integer.parseInt(sHour) + deliverTm;
					
					rsvDt = item.getRsvDt() + " " + item.getRsvTm() + "~" + lastHour + ":" + sMin;
				}
				
				String rsvPaper = item.getRsvPaper();
				if(rsvPaper.equals("S")){
					rsvPaper = "소액";
				} else if(rsvPaper.equals("B")){
					rsvPaper = "고액";
				} else {
					rsvPaper = "랜덤";
				}
				
				int memoCnt = item.getMemoCnt();
				String rsvNm = item.getRsvNm();
				if(memoCnt > 0){
					rsvNm = "★" + rsvNm;
				}
				
				row.createCell(0).setCellValue(i + 1);
				row.createCell(1).setCellValue(item.getRsvQr());
				row.createCell(2).setCellValue(item.getRsvNo());
				row.createCell(3).setCellValue(item.getRegDttm());
				row.createCell(4).setCellValue(item.getUnit());
				row.createCell(5).setCellValue(Utils.setStringFormatInteger(item.getRsvAmnt()));
				row.createCell(6).setCellValue(Utils.setStringFormatInteger((item.getBasicRate() + "")));
				row.createCell(7).setCellValue(Utils.setStringFormatInteger((item.getWonBill() + "")));
				row.createCell(8).setCellValue(Utils.setStringFormatInteger(item.getWeysCommis() + ""));
				row.createCell(9).setCellValue(Utils.setStringFormatInteger(item.getCms() + ""));
				row.createCell(10).setCellValue(Utils.setStringFormatInteger(item.getGetAmnt() + ""));
				row.createCell(11).setCellValue(rsvPaper);
				row.createCell(12).setCellValue(rsvNm);
				row.createCell(13).setCellValue(item.getRsvTel());
				row.createCell(14).setCellValue(item.getStoreNm());
				row.createCell(15).setCellValue(item.getStoreNmEng());
				row.createCell(16).setCellValue(rsvDt);
			}
			
			// 컬럼 Width 자동조절
			for(int i=0; i<ExcelConstants.TABLE_HEADER_RSV_LIST.length; i++) {
				workSheet.autoSizeColumn(i);
			}
			fileName = type + "_" + model.get("dt") + ".xlsx";
		}
		

		/* 거래 정보 */
		if(type.equals(ExcelConstants.RSV_DATA_LIST)) {
			@SuppressWarnings("unchecked")
			List<RsvListVO> excelList = (List<RsvListVO>) model.get("info");
			// Sheet 생성
			workSheet = workBook.createSheet(type);
			row = workSheet.createRow(0);
			
			// 헤더 생성
			int rowNum = 0;
			for(String str : ExcelConstants.TABLE_HEADER_RSV_DATA_LIST) {
				row.createCell(rowNum).setCellValue(ExcelConstants.TABLE_HEADER_RSV_DATA_LIST[rowNum++]);
			}
			
			// 내용 생성
			RsvListVO item;
			// "코드", "지점", "등록일", "예약일", "통화", "예약외화", "한도외화", "한도환율", "일반외화", "일반환율",
			// "수수료", "배송비", "입금금액", "수령인", "입금은행", "담당자", "예약상태"
			for(int i=0; i<excelList.size(); i++) {
				row = workSheet.createRow(i+1); // 헤더가 0번이라서.
				item = excelList.get(i);
				row.createCell(0).setCellValue(item.getRsvNo());
				row.createCell(1).setCellValue(item.getStoreNm());
				row.createCell(2).setCellValue(item.getRegDttm());
				row.createCell(3).setCellValue(item.getRsvDt());
				row.createCell(4).setCellValue(item.getUnit());
				row.createCell(5).setCellValue(Utils.setStringFormatInteger(item.getRsvAmnt() + ""));
				row.createCell(6).setCellValue(Utils.setStringFormatInteger(item.getRsvAmntWeys() + ""));
				row.createCell(7).setCellValue(Utils.setStringFormatInteger(item.getBasicRateWeys() + ""));
				row.createCell(8).setCellValue(Utils.setStringFormatInteger(item.getRsvAmntUser() + ""));
				row.createCell(9).setCellValue(Utils.setStringFormatInteger(item.getBasicRateUser() + ""));
				row.createCell(10).setCellValue(Utils.setStringFormatInteger(item.getCommis() + ""));
				row.createCell(11).setCellValue(Utils.setStringFormatInteger(item.getCms() + ""));
				row.createCell(12).setCellValue(Utils.setStringFormatInteger(item.getGetAmnt() + ""));
				row.createCell(13).setCellValue(item.getRsvNm() + "(" + item.getUsrTel() + ")");
				row.createCell(14).setCellValue(item.getVbankNm());
				row.createCell(15).setCellValue(item.getAdminName());
				row.createCell(16).setCellValue(item.getRsvSt());
			}
			
			// 컬럼 Width 자동조절
			for(int i=0; i<ExcelConstants.TABLE_HEADER_RSV_DATA_LIST.length; i++) {
				workSheet.autoSizeColumn(i);
			}
		}
		
		/* 환불 정보 */
		if(type.equals(ExcelConstants.RSV_CANCEL_LIST)) {
			@SuppressWarnings("unchecked")
			List<RsvListVO> excelList = (List<RsvListVO>) model.get("info");
			// Sheet 생성
			workSheet = workBook.createSheet(type);
			row = workSheet.createRow(0);
			
			// 헤더 생성
			int rowNum = 0;
			for(String str : ExcelConstants.TABLE_HEADER_RSV_CANCEL_LIST) {
				row.createCell(rowNum).setCellValue(ExcelConstants.TABLE_HEADER_RSV_CANCEL_LIST[rowNum++]);
			}
			
			// 내용 생성
			RsvListVO item;
			// "취소일", "예금주", "은행", "계좌", "환불금액"
			for(int i=0; i<excelList.size(); i++) {
				row = workSheet.createRow(i+1); // 헤더가 0번이라서.
				item = excelList.get(i);
				row.createCell(0).setCellValue(item.getCancelDttm());
				row.createCell(1).setCellValue(item.getRsvNm());
				row.createCell(2).setCellValue(item.getRetBankNm());
				row.createCell(3).setCellValue(item.getRetBankCd());
				row.createCell(4).setCellValue(Utils.setStringFormatInteger(item.getCancelAmnt() + ""));
			}
			
			// 컬럼 Width 자동조절
			for(int i=0; i<ExcelConstants.TABLE_HEADER_RSV_CANCEL_LIST.length; i++) {
				workSheet.autoSizeColumn(i);
			}
		}
		
		response.setHeader("Content-Disposition", "ATTachment; Filename=" + fileName);
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		ServletOutputStream out = response.getOutputStream();
		out.flush();
		workBook.write(out);
		out.flush();
		if (workBook instanceof SXSSFWorkbook) {
            ((SXSSFWorkbook) workBook).dispose();
        }
	}
}
