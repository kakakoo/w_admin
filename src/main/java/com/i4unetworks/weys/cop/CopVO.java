package com.i4unetworks.weys.cop;

public class CopVO {
	private int copId;
	private String copAdm;
	private String copPw;
	private String copPwDue;
	private String copNm;
	private String copCd;
	private String copSt;
	private String copLogo;
	private String copLogoB;
	private String copBg;
	private String regDttm;
	public int getCopId() {
		return copId;
	}
	public void setCopId(int copId) {
		this.copId = copId;
	}
	public String getCopNm() {
		return copNm;
	}
	public void setCopNm(String copNm) {
		this.copNm = copNm;
	}
	public String getCopCd() {
		return copCd;
	}
	public void setCopCd(String copCd) {
		this.copCd = copCd;
	}
	public String getCopSt() {
		return copSt;
	}
	public void setCopSt(String copSt) {
		this.copSt = copSt;
	}
	public String getRegDttm() {
		return regDttm;
	}
	public void setRegDttm(String regDttm) {
		this.regDttm = regDttm;
	}
	public String getCopLogo() {
		return copLogo;
	}
	public void setCopLogo(String copLogo) {
		this.copLogo = copLogo;
	}
	public String getCopBg() {
		return copBg;
	}
	public void setCopBg(String copBg) {
		this.copBg = copBg;
	}
	public String getCopLogoB() {
		return copLogoB;
	}
	public void setCopLogoB(String copLogoB) {
		this.copLogoB = copLogoB;
	}
	public String getCopAdm() {
		return copAdm;
	}
	public void setCopAdm(String copAdm) {
		this.copAdm = copAdm;
	}
	public String getCopPw() {
		return copPw;
	}
	public void setCopPw(String copPw) {
		this.copPw = copPw;
	}
	public String getCopPwDue() {
		return copPwDue;
	}
	public void setCopPwDue(String copPwDue) {
		this.copPwDue = copPwDue;
	}
	@Override
	public String toString() {
		return "CopVO [copId=" + copId + ", copAdm=" + copAdm + ", copPw=" + copPw + ", copPwDue=" + copPwDue
				+ ", copNm=" + copNm + ", copCd=" + copCd + ", copSt=" + copSt + ", copLogo=" + copLogo + ", copLogoB="
				+ copLogoB + ", copBg=" + copBg + ", regDttm=" + regDttm + "]";
	}
}
