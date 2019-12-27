package com.i4unetworks.weys.store;

public class StoreListVO {

	private int storeId;
	private String storeNm;
	private String storeNmEng;
	private String storeSt;
	private String storeRsv;
	private String todayRsv;
	private String deliverToday;
	private int rsvDone;
	private int rsvToday;
	private int rsvTomorrow;
	private int getToday;
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public String getStoreNm() {
		return storeNm;
	}
	public void setStoreNm(String storeNm) {
		this.storeNm = storeNm;
	}
	public String getStoreSt() {
		return storeSt;
	}
	public void setStoreSt(String storeSt) {
		this.storeSt = storeSt;
	}
	public String getStoreRsv() {
		return storeRsv;
	}
	public void setStoreRsv(String storeRsv) {
		this.storeRsv = storeRsv;
	}
	public String getStoreNmEng() {
		return storeNmEng;
	}
	public void setStoreNmEng(String storeNmEng) {
		this.storeNmEng = storeNmEng;
	}
	public int getRsvDone() {
		return rsvDone;
	}
	public void setRsvDone(int rsvDone) {
		this.rsvDone = rsvDone;
	}
	public int getRsvToday() {
		return rsvToday;
	}
	public void setRsvToday(int rsvToday) {
		this.rsvToday = rsvToday;
	}
	public int getGetToday() {
		return getToday;
	}
	public void setGetToday(int getToday) {
		this.getToday = getToday;
	}
	public int getRsvTomorrow() {
		return rsvTomorrow;
	}
	public void setRsvTomorrow(int rsvTomorrow) {
		this.rsvTomorrow = rsvTomorrow;
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
		return "StoreListVO [storeId=" + storeId + ", storeNm=" + storeNm + ", storeNmEng=" + storeNmEng + ", storeSt="
				+ storeSt + ", storeRsv=" + storeRsv + ", todayRsv=" + todayRsv + ", deliverToday=" + deliverToday
				+ ", rsvDone=" + rsvDone + ", rsvToday=" + rsvToday + ", rsvTomorrow=" + rsvTomorrow + ", getToday="
				+ getToday + "]";
	}
}
