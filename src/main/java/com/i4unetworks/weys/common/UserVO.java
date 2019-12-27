package com.i4unetworks.weys.common;

public class UserVO {

	private String adminKey;
	private String adminPw;
	private String adminId;
	private String adminName;
	private String adminTp;
	private String pwdDue;
	private String storeId;
	private String storeNm;
	public String getAdminKey() {
		return adminKey;
	}
	public void setAdminKey(String adminKey) {
		this.adminKey = adminKey;
	}
	public String getAdminPw() {
		return adminPw;
	}
	public void setAdminPw(String adminPw) {
		this.adminPw = adminPw;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getAdminTp() {
		return adminTp;
	}
	public void setAdminTp(String adminTp) {
		this.adminTp = adminTp;
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
	public String getPwdDue() {
		return pwdDue;
	}
	public void setPwdDue(String pwdDue) {
		this.pwdDue = pwdDue;
	}
	@Override
	public String toString() {
		return "UserVO [adminKey=" + adminKey + ", adminPw=" + adminPw + ", adminId=" + adminId + ", adminName="
				+ adminName + ", adminTp=" + adminTp + ", storeId=" + storeId + ", storeNm=" + storeNm + "]";
	}
	
}
