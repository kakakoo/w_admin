package com.i4unetworks.weys.analysis;

public class AnalysisVO {

	private int totalUsr;
	private int andUsr;
	private int iosUsr;
	private int webUsr;
	private long logCnt;
	private String dt;
	public int getTotalUsr() {
		return totalUsr;
	}
	public void setTotalUsr(int totalUsr) {
		this.totalUsr = totalUsr;
	}
	public int getAndUsr() {
		return andUsr;
	}
	public void setAndUsr(int andUsr) {
		this.andUsr = andUsr;
	}
	public int getIosUsr() {
		return iosUsr;
	}
	public void setIosUsr(int iosUsr) {
		this.iosUsr = iosUsr;
	}
	public int getWebUsr() {
		return webUsr;
	}
	public void setWebUsr(int webUsr) {
		this.webUsr = webUsr;
	}
	public long getLogCnt() {
		return logCnt;
	}
	public void setLogCnt(long logCnt) {
		this.logCnt = logCnt;
	}
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	@Override
	public String toString() {
		return "AnalysisVO [totalUsr=" + totalUsr + ", andUsr=" + andUsr + ", iosUsr=" + iosUsr + ", webUsr=" + webUsr
				+ ", logCnt=" + logCnt + ", dt=" + dt + "]";
	}
	
}
