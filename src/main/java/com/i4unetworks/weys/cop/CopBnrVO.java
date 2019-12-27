package com.i4unetworks.weys.cop;

public class CopBnrVO {

	private int cbId;
	private String cbNm;
	private String cbSImg;
	private String cbBImg;
	private String cbUrl;
	private String cbSt;
	private String modalNm;
	private String btnNm;
	private String regDttm;
	public int getCbId() {
		return cbId;
	}
	public void setCbId(int cbId) {
		this.cbId = cbId;
	}
	public String getCbNm() {
		return cbNm;
	}
	public void setCbNm(String cbNm) {
		this.cbNm = cbNm;
	}
	public String getCbSImg() {
		return cbSImg;
	}
	public void setCbSImg(String cbSImg) {
		this.cbSImg = cbSImg;
	}
	public String getCbBImg() {
		return cbBImg;
	}
	public void setCbBImg(String cbBImg) {
		this.cbBImg = cbBImg;
	}
	public String getCbUrl() {
		return cbUrl;
	}
	public void setCbUrl(String cbUrl) {
		this.cbUrl = cbUrl;
	}
	public String getRegDttm() {
		return regDttm;
	}
	public void setRegDttm(String regDttm) {
		this.regDttm = regDttm;
	}
	public String getCbSt() {
		return cbSt;
	}
	public void setCbSt(String cbSt) {
		this.cbSt = cbSt;
	}
	public String getModalNm() {
		return modalNm;
	}
	public void setModalNm(String modalNm) {
		this.modalNm = modalNm;
	}
	public String getBtnNm() {
		return btnNm;
	}
	public void setBtnNm(String btnNm) {
		this.btnNm = btnNm;
	}
	@Override
	public String toString() {
		return "CopBnrVO [cbId=" + cbId + ", cbNm=" + cbNm + ", cbSImg=" + cbSImg + ", cbBImg=" + cbBImg + ", cbUrl="
				+ cbUrl + ", cbSt=" + cbSt + ", modalNm=" + modalNm + ", btnNm=" + btnNm + ", regDttm=" + regDttm + "]";
	}
	
}
