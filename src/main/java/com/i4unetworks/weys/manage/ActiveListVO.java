package com.i4unetworks.weys.manage;

public class ActiveListVO {

	private String regDttm;
	private String type;
	private String tp;
	private String unit;
	private String getAmnt;
	private String basicRate;
	private String payAmnt;
	private String usrNick;
	private String usrNm;
	private String paper;
	
	public String getRegDttm() {
		return regDttm;
	}
	public void setRegDttm(String regDttm) {
		this.regDttm = regDttm;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTp() {
		return tp;
	}
	public void setTp(String tp) {
		this.tp = tp;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getGetAmnt() {
		return getAmnt;
	}
	public void setGetAmnt(String getAmnt) {
		this.getAmnt = getAmnt;
	}
	public String getBasicRate() {
		return basicRate;
	}
	public void setBasicRate(String basicRate) {
		this.basicRate = basicRate;
	}
	public String getPayAmnt() {
		return payAmnt;
	}
	public void setPayAmnt(String payAmnt) {
		this.payAmnt = payAmnt;
	}
	public String getUsrNick() {
		return usrNick;
	}
	public void setUsrNick(String usrNick) {
		this.usrNick = usrNick;
	}
	public String getPaper() {
		return paper;
	}
	public void setPaper(String paper) {
		this.paper = paper;
	}
	public String getUsrNm() {
		return usrNm;
	}
	public void setUsrNm(String usrNm) {
		this.usrNm = usrNm;
	}
	@Override
	public String toString() {
		return "ActiveListVO [regDttm=" + regDttm + ", type=" + type + ", tp=" + tp + ", unit=" + unit + ", getAmnt="
				+ getAmnt + ", basicRate=" + basicRate + ", payAmnt=" + payAmnt + ", usrNick=" + usrNick + ", usrNm="
				+ usrNm + ", paper=" + paper + "]";
	}
	
}
