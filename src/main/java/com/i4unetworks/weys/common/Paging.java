package com.i4unetworks.weys.common;

import java.util.List;
import java.util.Map;

public class Paging {

	// 페이징 처리 
	private int pageSize = 10;	// 게시글 수 
	private int firstPageNo;	// 첫 번째 페이지 번호 
	private int prevPageNo;		// 이전 페이지 번호 
	private int startPageNo; 	// 시작 페이지 
	private int pageNo = 1;		// 페이지 번호 
	private int endPageNo;		// 끝 페이지 
	private int nextPageNo;		// 다음 페이지 번호 
	private int finalPageNo;	// 마지막 페이지 번호 
	private int totalCount;		// 게시 글 전체 수 
	
	private int orderVal;
	private String orderTp;
	private String preOrder;
	private String descTp;
	private String excelType;
	
	// 검색 조건
	private String searchType;	// 검색 조건 
	private String searchText;	// 검색 텍스트 
	private String grade;	// 계정 등급 
	private List<String> listData;
	private List<String> listData1;
	private String startDt;		// 시작 일자 
	private String endDt;		// 종료 일자 
	private String dateType;	// 검색 조건 
	private String reservation;
	private String trdTp;
	private String trdUnit;
	private int searchPage;
	private int isSearch;		// 검색인지 아닌지 
	
	private int minCost = 0;
	private int maxCost = 1000000;

	private String usrId;
	private String storeId;
	private String isExcel;
	
	private String encKey;
	private String rsvStVal;
	private String rsvFormVal;
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getFirstPageNo() {
		return firstPageNo;
	}
	public void setFirstPageNo(int firstPageNo) {
		this.firstPageNo = firstPageNo;
	}
	public int getPrevPageNo() {
		return prevPageNo;
	}
	public void setPrevPageNo(int prevPageNo) {
		this.prevPageNo = prevPageNo;
	}
	public int getStartPageNo() {
		return startPageNo;
	}
	public void setStartPageNo(int startPageNo) {
		this.startPageNo = startPageNo;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getEndPageNo() {
		return endPageNo;
	}
	public void setEndPageNo(int endPageNo) {
		this.endPageNo = endPageNo;
	}
	public int getNextPageNo() {
		return nextPageNo;
	}
	public void setNextPageNo(int nextPageNo) {
		this.nextPageNo = nextPageNo;
	}
	public int getFinalPageNo() {
		return finalPageNo;
	}
	public void setFinalPageNo(int finalPageNo) {
		this.finalPageNo = finalPageNo;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		this.makepaging();
	}

	/*
	 * 페이징 생성 
	 */
	private void makepaging() {
		if(this.totalCount == 0)
			return ;
		if(this.pageNo == 0)
			this.setPageNo(1);
		if(this.pageSize == 0)
			this.setPageSize(10);
		
		int finalPage = (totalCount + (pageSize - 1)) / pageSize;
		if(this.pageNo > finalPage)
			this.setPageNo(finalPage);
		
		if(this.pageNo < 0 || this.pageNo > finalPage)
			this.pageNo = 1;
		
		boolean isNowFirst = pageNo == 1 ? true : false;
		boolean isNowFianl = pageNo == finalPage ? true : false;
		
		int startPage = 1;
		int endPage = 10;

		startPage = ((pageNo - 1) / 10) * 10 + 1;
		endPage = startPage + 10 - 1;
		
		if(endPage > finalPage)
			endPage = finalPage;
		
		this.setFirstPageNo(1);
		
		if(isNowFirst)
			this.setPrevPageNo(1);
		else
			this.setPrevPageNo(((pageNo - 1) < 1 ? 1 : (pageNo - 1)));
		
		this.setStartPageNo(startPage);
		this.setEndPageNo(endPage);
		
		if(isNowFianl)
			this.setNextPageNo(finalPage);
		else
			this.setNextPageNo(((pageNo + 1) > finalPage ? finalPage : (pageNo + 1)));
		
		this.setFinalPageNo(finalPage);
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	public String getStartDt() {
		return startDt;
	}
	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}
	public String getEndDt() {
		return endDt;
	}
	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}
	public String getDateType() {
		return dateType;
	}
	public void setDateType(String dateType) {
		this.dateType = dateType;
	}
	public int getSearchPage() {
		return searchPage;
	}
	public void setSearchPage(int searchPage) {
		this.searchPage = searchPage;
	}
	public int getIsSearch() {
		return isSearch;
	}
	public void setIsSearch(int isSearch) {
		this.isSearch = isSearch;
	}
	public String getReservation() {
		return reservation;
	}
	public void setReservation(String reservation) {
		this.reservation = reservation;
	}
	public List<String> getListData() {
		return listData;
	}
	public void setListData(List<String> listData) {
		this.listData = listData;
	}
	public List<String> getListData1() {
		return listData1;
	}
	public void setListData1(List<String> listData1) {
		this.listData1 = listData1;
	}
	public String getTrdTp() {
		return trdTp;
	}
	public void setTrdTp(String trdTp) {
		this.trdTp = trdTp;
	}
	public String getTrdUnit() {
		return trdUnit;
	}
	public void setTrdUnit(String trdUnit) {
		this.trdUnit = trdUnit;
	}
	public String getUsrId() {
		return usrId;
	}
	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}
	public String getIsExcel() {
		return isExcel;
	}
	public void setIsExcel(String isExcel) {
		this.isExcel = isExcel;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getEncKey() {
		return encKey;
	}
	public void setEncKey(String encKey) {
		this.encKey = encKey;
	}
	public String getRsvStVal() {
		return rsvStVal;
	}
	public void setRsvStVal(String rsvStVal) {
		this.rsvStVal = rsvStVal;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getMinCost() {
		return minCost;
	}
	public void setMinCost(int minCost) {
		this.minCost = minCost;
	}
	public int getMaxCost() {
		return maxCost;
	}
	public void setMaxCost(int maxCost) {
		this.maxCost = maxCost;
	}
	public String getRsvFormVal() {
		return rsvFormVal;
	}
	public void setRsvFormVal(String rsvFormVal) {
		this.rsvFormVal = rsvFormVal;
	}
	public String getPreOrder() {
		return preOrder;
	}
	public void setPreOrder(String preOrder) {
		this.preOrder = preOrder;
	}
	public int getOrderVal() {
		return orderVal;
	}
	public void setOrderVal(int orderVal) {
		this.orderVal = orderVal;
	}
	public String getOrderTp() {
		return orderTp;
	}
	public void setOrderTp(String orderTp) {
		this.orderTp = orderTp;
	}
	public String getDescTp() {
		return descTp;
	}
	public void setDescTp(String descTp) {
		this.descTp = descTp;
	}
	public String getExcelType() {
		return excelType;
	}
	public void setExcelType(String excelType) {
		this.excelType = excelType;
	}
	@Override
	public String toString() {
		return "Paging [pageSize=" + pageSize + ", firstPageNo=" + firstPageNo + ", prevPageNo=" + prevPageNo
				+ ", startPageNo=" + startPageNo + ", pageNo=" + pageNo + ", endPageNo=" + endPageNo + ", nextPageNo="
				+ nextPageNo + ", finalPageNo=" + finalPageNo + ", totalCount=" + totalCount + ", orderVal=" + orderVal
				+ ", orderTp=" + orderTp + ", preOrder=" + preOrder + ", descTp=" + descTp + ", excelType=" + excelType
				+ ", searchType=" + searchType + ", searchText=" + searchText + ", grade=" + grade + ", listData="
				+ listData + ", listData1=" + listData1 + ", startDt=" + startDt + ", endDt=" + endDt + ", dateType="
				+ dateType + ", reservation=" + reservation + ", trdTp=" + trdTp + ", trdUnit=" + trdUnit
				+ ", searchPage=" + searchPage + ", isSearch=" + isSearch + ", minCost=" + minCost + ", maxCost="
				+ maxCost + ", usrId=" + usrId + ", storeId=" + storeId + ", isExcel=" + isExcel + ", encKey=" + encKey
				+ ", rsvStVal=" + rsvStVal + ", rsvFormVal=" + rsvFormVal + "]";
	}
}
