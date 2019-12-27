package com.i4unetworks.weys.rsv;

public class GroupVO {

	private int storeId;
	private int adminKey;
	private int groupId;
	private String rsvDt;
	private String retDt;
	private String encKey;
	private String barcode;
	private String barcodeUrl;
	private String storeNm;
	private int grpCnt;
	private int doneCnt;
	private int retCnt;
	private String groupSt;
	private String groupTp;
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public String getRsvDt() {
		return rsvDt;
	}
	public void setRsvDt(String rsvDt) {
		this.rsvDt = rsvDt;
	}
	public String getEncKey() {
		return encKey;
	}
	public void setEncKey(String encKey) {
		this.encKey = encKey;
	}
	public int getAdminKey() {
		return adminKey;
	}
	public void setAdminKey(int adminKey) {
		this.adminKey = adminKey;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getBarcodeUrl() {
		return barcodeUrl;
	}
	public void setBarcodeUrl(String barcodeUrl) {
		this.barcodeUrl = barcodeUrl;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getStoreNm() {
		return storeNm;
	}
	public void setStoreNm(String storeNm) {
		this.storeNm = storeNm;
	}
	public int getGrpCnt() {
		return grpCnt;
	}
	public void setGrpCnt(int grpCnt) {
		this.grpCnt = grpCnt;
	}
	public String getGroupSt() {
		return groupSt;
	}
	public void setGroupSt(String groupSt) {
		this.groupSt = groupSt;
	}
	public int getDoneCnt() {
		return doneCnt;
	}
	public void setDoneCnt(int doneCnt) {
		this.doneCnt = doneCnt;
	}
	public int getRetCnt() {
		return retCnt;
	}
	public void setRetCnt(int retCnt) {
		this.retCnt = retCnt;
	}
	public String getGroupTp() {
		return groupTp;
	}
	public void setGroupTp(String groupTp) {
		this.groupTp = groupTp;
	}
	public String getRetDt() {
		return retDt;
	}
	public void setRetDt(String retDt) {
		this.retDt = retDt;
	}
	@Override
	public String toString() {
		return "GroupVO [storeId=" + storeId + ", adminKey=" + adminKey + ", groupId=" + groupId + ", rsvDt=" + rsvDt
				+ ", retDt=" + retDt + ", encKey=" + encKey + ", barcode=" + barcode + ", barcodeUrl=" + barcodeUrl
				+ ", storeNm=" + storeNm + ", grpCnt=" + grpCnt + ", doneCnt=" + doneCnt + ", retCnt=" + retCnt
				+ ", groupSt=" + groupSt + ", groupTp=" + groupTp + "]";
	}
	
}
