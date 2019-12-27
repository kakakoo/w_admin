package com.i4unetworks.weys.common;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExcelRead {

	protected static Logger logger = LoggerFactory.getLogger(ExcelRead.class);

	/**
	 * 유저 리스트 엑셀 읽어서 유저 리스트 리턴하기 
	 * @param filePath
	 * @return
	 */
	public static List<String> userIdList(String filePath){
		
		if(!(filePath.contains(".xls") || filePath.contains(".xlsx"))){
			return null;
		}
		
		List<String> resultList = new ArrayList<String>();
		try{
			FileInputStream fis = new FileInputStream(new File(filePath));
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			// 해당 엑셀파일의 시트 읽기 
			XSSFSheet sheet = workbook.getSheetAt(0);
			// 해당 시트의 행 갯수 
			int rows = sheet.getPhysicalNumberOfRows();
			
			for(int rowIndex = 1; rowIndex < rows; rowIndex++){
				// 1열부터 하나씩 읽는다. 
				XSSFRow row = sheet.getRow(rowIndex);
				if(row != null){
					int cells = row.getPhysicalNumberOfCells();
					for(int columnIndex = 0 ; columnIndex <= cells ; columnIndex++){
						// 첫번째 사용자 리스트 값만 가져온다.
						if(columnIndex != 0)
							break;
						// 열의 각 행의 값을 가져온다. 
						XSSFCell cell = row.getCell(columnIndex);
						String value = getCellValue(cell);
						resultList.add(value);
					}
				}
			}
			
		} catch (Exception e) {
			logger.info("Excel read Error ::: " + e.getMessage());
			return null;
		}
		
		return resultList;
	}

	private static String getCellValue(XSSFCell cell) {

		String value = "";
		
		switch (cell.getCellType()) {
		case XSSFCell.CELL_TYPE_NUMERIC :
			value = cell.getNumericCellValue() + "";
			break;
		case XSSFCell.CELL_TYPE_STRING :
			value = cell.getStringCellValue();
			break;
		case XSSFCell.CELL_TYPE_BLANK :
			value = cell.getBooleanCellValue() + "";
			break;
		case XSSFCell.CELL_TYPE_ERROR :
			value = cell.getErrorCellValue() + "";
			break;
		}
		
		return value;
	}
	
	
}
