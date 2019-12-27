package com.i4unetworks.weys.rsv;

public class RsvUnitVO {

	private int ruId;
	private int storeId;
	private int unitId;
	private int storeCnt;
	private String storeNm;
	private String unitCd;
	private String unitSt;
	private int unitSize;
	private int unitMin;
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public int getUnitId() {
		return unitId;
	}
	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}
	public String getStoreNm() {
		return storeNm;
	}
	public void setStoreNm(String storeNm) {
		this.storeNm = storeNm;
	}
	public String getUnitCd() {
		return unitCd;
	}
	public void setUnitCd(String unitCd) {
		this.unitCd = unitCd;
	}
	public String getUnitSt() {
		return unitSt;
	}
	public void setUnitSt(String unitSt) {
		this.unitSt = unitSt;
	}
	public int getUnitSize() {
		return unitSize;
	}
	public void setUnitSize(int unitSize) {
		this.unitSize = unitSize;
	}
	public int getUnitMin() {
		return unitMin;
	}
	public void setUnitMin(int unitMin) {
		this.unitMin = unitMin;
	}
	public int getStoreCnt() {
		return storeCnt;
	}
	public void setStoreCnt(int storeCnt) {
		this.storeCnt = storeCnt;
	}
	public int getRuId() {
		return ruId;
	}
	public void setRuId(int ruId) {
		this.ruId = ruId;
	}
	@Override
	public String toString() {
		return "RsvUnitVO [storeId=" + storeId + ", unitId=" + unitId + ", storeNm=" + storeNm + ", unitCd=" + unitCd
				+ ", unitSt=" + unitSt + ", unitSize=" + unitSize + ", unitMin=" + unitMin + "]";
	}
	
}
