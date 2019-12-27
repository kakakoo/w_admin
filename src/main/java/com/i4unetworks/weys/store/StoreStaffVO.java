package com.i4unetworks.weys.store;

import java.util.List;

public class StoreStaffVO {

	private String adminKey;
	private String adminId;
	private String adminPw;
	private String adminName;
	private String adminTp;
	private String adminTpText;
	private String storeId;
	private String storeNm;
	private String adminTel;
	private String encKey;
	private String adminSt;
	private List<Integer> storeList;
	public String getAdminKey() {
		return adminKey;
	}
	public void setAdminKey(String adminKey) {
		this.adminKey = adminKey;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getAdminPw() {
		return adminPw;
	}
	public void setAdminPw(String adminPw) {
		this.adminPw = adminPw;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
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
	public String getAdminTel() {
		return adminTel;
	}
	public void setAdminTel(String adminTel) {
		this.adminTel = adminTel;
	}
	public String getEncKey() {
		return encKey;
	}
	public void setEncKey(String encKey) {
		this.encKey = encKey;
	}
	public String getAdminTp() {
		return adminTp;
	}
	public List<Integer> getStoreList() {
		return storeList;
	}
	public void setStoreList(List<Integer> storeList) {
		this.storeList = storeList;
	}
	public void setAdminTp(String adminTp) {
		
		if(adminTp.equals("S")){
			this.adminTpText = "슈퍼관리자";
		} else if(adminTp.equals("A")){
			this.adminTpText = "환전관리자";
		} else {
			this.adminTpText = "지점매니저";
		}
		this.adminTp = adminTp;
	}
	public String getAdminTpText() {
		return adminTpText;
	}
	public void setAdminTpText(String adminTpText) {
		this.adminTpText = adminTpText;
	}
	public String getAdminSt() {
		return adminSt;
	}
	public void setAdminSt(String adminSt) {
		this.adminSt = adminSt;
	}
	@Override
	public String toString() {
		return "StoreStaffVO [adminKey=" + adminKey + ", adminId=" + adminId + ", adminPw=" + adminPw + ", adminName="
				+ adminName + ", adminTp=" + adminTp + ", adminTpText=" + adminTpText + ", storeId=" + storeId
				+ ", storeNm=" + storeNm + ", adminTel=" + adminTel + ", encKey=" + encKey + ", adminSt=" + adminSt
				+ ", storeList=" + storeList + "]";
	}
	
}
