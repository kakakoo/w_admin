package com.i4unetworks.weys.version;

public class MngVO {
	private int mgId;
	private String mgNm;
	private String mgTel;
	private String mgSt;
	private String regDttm;
	private String encKey;
	public int getMgId() {
		return mgId;
	}
	public void setMgId(int mgId) {
		this.mgId = mgId;
	}
	public String getMgNm() {
		return mgNm;
	}
	public void setMgNm(String mgNm) {
		this.mgNm = mgNm;
	}
	public String getMgTel() {
		return mgTel;
	}
	public void setMgTel(String mgTel) {
		this.mgTel = mgTel;
	}
	public String getMgSt() {
		return mgSt;
	}
	public void setMgSt(String mgSt) {
		this.mgSt = mgSt;
	}
	public String getRegDttm() {
		return regDttm;
	}
	public void setRegDttm(String regDttm) {
		this.regDttm = regDttm;
	}
	public String getEncKey() {
		return encKey;
	}
	public void setEncKey(String encKey) {
		this.encKey = encKey;
	}
	@Override
	public String toString() {
		return "MngVO [mgId=" + mgId + ", mgNm=" + mgNm + ", mgTel=" + mgTel + ", mgSt=" + mgSt + ", regDttm=" + regDttm
				+ "]";
	}
	
}
