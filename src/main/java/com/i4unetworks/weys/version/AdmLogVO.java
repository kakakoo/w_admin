package com.i4unetworks.weys.version;

public class AdmLogVO {

	private String adminId;
	private String ipAddr;
	private String regDttm;
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getIpAddr() {
		return ipAddr;
	}
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	public String getRegDttm() {
		return regDttm;
	}
	public void setRegDttm(String regDttm) {
		this.regDttm = regDttm;
	}
	@Override
	public String toString() {
		return "AdmLogVO [adminId=" + adminId + ", ipAddr=" + ipAddr + ", regDttm=" + regDttm + "]";
	}
}
