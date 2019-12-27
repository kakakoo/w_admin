package com.i4unetworks.weys.manage;

public class LogVO {

	private String unit;
	private String type;
	private String st;
	private int getAmnt;
	private int payAmnt;
	private int befAmnt;
	private int aftAmnt;
	private String regDttm;
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSt() {
		return st;
	}
	public void setSt(String st) {
		this.st = st;
	}
	public int getGetAmnt() {
		return getAmnt;
	}
	public void setGetAmnt(int getAmnt) {
		this.getAmnt = getAmnt;
	}
	public int getPayAmnt() {
		return payAmnt;
	}
	public void setPayAmnt(int payAmnt) {
		this.payAmnt = payAmnt;
	}
	public int getBefAmnt() {
		return befAmnt;
	}
	public void setBefAmnt(int befAmnt) {
		this.befAmnt = befAmnt;
	}
	public int getAftAmnt() {
		return aftAmnt;
	}
	public void setAftAmnt(int aftAmnt) {
		this.aftAmnt = aftAmnt;
	}
	public String getRegDttm() {
		return regDttm;
	}
	public void setRegDttm(String regDttm) {
		this.regDttm = regDttm;
	}
	@Override
	public String toString() {
		return "LogVO [unit=" + unit + ", type=" + type + ", st=" + st + ", getAmnt=" + getAmnt + ", payAmnt=" + payAmnt
				+ ", befAmnt=" + befAmnt + ", aftAmnt=" + aftAmnt + ", regDttm=" + regDttm + "]";
	}
}
