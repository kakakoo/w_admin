package com.i4unetworks.weys.store;

import java.util.List;

public class StoreDetailVO {

	private String storeId;
	private String storeNm;
	private String storeNmEng;
	private String displayNm;
	private String storeAddr;
	private String storeUrl;
	private String storeTel;
	private String storeOpentime;
	private String storeImg;
	private String rsvImg;
	private String storeSt;
	private String storeRsv;
	private String storeAir;
	private String deliverSt;
	private String todayRsv;
	private String deliverToday;
	private int deliverCms;
	private int deliverTime;
	private int rsvCommis;
	private String closeDt;
	private List<String> closeList;
	private List<String> imgList;
	private List<String> openSt;
	private List<String> startTm;
	private List<String> endTm;
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getStoreNm() {
		return storeNm;
	}
	public void setStoreNm(String storeNm) {
		this.storeNm = storeNm;
	}
	public String getStoreAddr() {
		return storeAddr;
	}
	public void setStoreAddr(String storeAddr) {
		this.storeAddr = storeAddr;
	}
	public String getStoreUrl() {
		return storeUrl;
	}
	public void setStoreUrl(String storeUrl) {
		this.storeUrl = storeUrl;
	}
	public String getStoreTel() {
		return storeTel;
	}
	public void setStoreTel(String storeTel) {
		this.storeTel = storeTel;
	}
	public String getStoreOpentime() {
		return storeOpentime;
	}
	public void setStoreOpentime(String storeOpentime) {
		this.storeOpentime = storeOpentime;
	}
	public String getStoreImg() {
		return storeImg;
	}
	public void setStoreImg(String storeImg) {
		this.storeImg = storeImg;
	}
	public String getStoreSt() {
		return storeSt;
	}
	public void setStoreSt(String storeSt) {
		this.storeSt = storeSt;
	}
	public List<String> getImgList() {
		return imgList;
	}
	public void setImgList(List<String> imgList) {
		this.imgList = imgList;
	}
	public String getStoreRsv() {
		return storeRsv;
	}
	public void setStoreRsv(String storeRsv) {
		this.storeRsv = storeRsv;
	}
	public List<String> getOpenSt() {
		return openSt;
	}
	public void setOpenSt(List<String> openSt) {
		this.openSt = openSt;
	}
	public List<String> getStartTm() {
		return startTm;
	}
	public void setStartTm(List<String> startTm) {
		this.startTm = startTm;
	}
	public List<String> getEndTm() {
		return endTm;
	}
	public void setEndTm(List<String> endTm) {
		this.endTm = endTm;
	}
	public String getCloseDt() {
		return closeDt;
	}
	public void setCloseDt(String closeDt) {
		this.closeDt = closeDt;
	}
	public List<String> getCloseList() {
		return closeList;
	}
	public void setCloseList(List<String> closeList) {
		this.closeList = closeList;
	}
	public String getStoreAir() {
		return storeAir;
	}
	public void setStoreAir(String storeAir) {
		this.storeAir = storeAir;
	}
	public int getRsvCommis() {
		return rsvCommis;
	}
	public void setRsvCommis(int rsvCommis) {
		this.rsvCommis = rsvCommis;
	}
	public String getStoreNmEng() {
		return storeNmEng;
	}
	public void setStoreNmEng(String storeNmEng) {
		this.storeNmEng = storeNmEng;
	}
	public String getDisplayNm() {
		return displayNm;
	}
	public void setDisplayNm(String displayNm) {
		this.displayNm = displayNm;
	}
	public String getRsvImg() {
		return rsvImg;
	}
	public void setRsvImg(String rsvImg) {
		this.rsvImg = rsvImg;
	}
	public String getDeliverSt() {
		return deliverSt;
	}
	public void setDeliverSt(String deliverSt) {
		this.deliverSt = deliverSt;
	}
	public int getDeliverCms() {
		return deliverCms;
	}
	public void setDeliverCms(int deliverCms) {
		this.deliverCms = deliverCms;
	}
	public int getDeliverTime() {
		return deliverTime;
	}
	public void setDeliverTime(int deliverTime) {
		this.deliverTime = deliverTime;
	}
	public String getTodayRsv() {
		return todayRsv;
	}
	public void setTodayRsv(String todayRsv) {
		this.todayRsv = todayRsv;
	}
	public String getDeliverToday() {
		return deliverToday;
	}
	public void setDeliverToday(String deliverToday) {
		this.deliverToday = deliverToday;
	}
	@Override
	public String toString() {
		return "StoreDetailVO [storeId=" + storeId + ", storeNm=" + storeNm + ", storeNmEng=" + storeNmEng
				+ ", displayNm=" + displayNm + ", storeAddr=" + storeAddr + ", storeUrl=" + storeUrl + ", storeTel="
				+ storeTel + ", storeOpentime=" + storeOpentime + ", storeImg=" + storeImg + ", rsvImg=" + rsvImg
				+ ", storeSt=" + storeSt + ", storeRsv=" + storeRsv + ", storeAir=" + storeAir + ", deliverSt="
				+ deliverSt + ", deliverCms=" + deliverCms + ", deliverTime=" + deliverTime + ", rsvCommis=" + rsvCommis
				+ ", closeDt=" + closeDt + ", closeList=" + closeList + ", imgList=" + imgList + ", openSt=" + openSt
				+ ", startTm=" + startTm + ", endTm=" + endTm + "]";
	}
	
}
