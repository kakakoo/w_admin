package com.i4unetworks.weys.staff;

public class StaffVO {
	
	private String act;
	private String regDttm;
	private String memo;
	private String adminName;
	
	private int anId;
	private String anTitle;
	private String anDesc;
	private String anSt;
	public String getAct() {
		return act;
	}
	public void setAct(String act) {
		this.act = act;
	}
	public String getRegDttm() {
		return regDttm;
	}
	public void setRegDttm(String regDttm) {
		this.regDttm = regDttm;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public int getAnId() {
		return anId;
	}
	public void setAnId(int anId) {
		this.anId = anId;
	}
	public String getAnTitle() {
		return anTitle;
	}
	public void setAnTitle(String anTitle) {
		this.anTitle = anTitle;
	}
	public String getAnDesc() {
		return anDesc;
	}
	public void setAnDesc(String anDesc) {
		this.anDesc = anDesc;
	}
	public String getAnSt() {
		return anSt;
	}
	public void setAnSt(String anSt) {
		this.anSt = anSt;
	}
	@Override
	public String toString() {
		return "StaffVO [act=" + act + ", regDttm=" + regDttm + ", memo=" + memo + ", adminName=" + adminName
				+ ", anId=" + anId + ", anTitle=" + anTitle + ", anDesc=" + anDesc + ", anSt=" + anSt + "]";
	}
	
}
