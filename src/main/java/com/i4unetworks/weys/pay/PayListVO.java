package com.i4unetworks.weys.pay;

public class PayListVO {
	private int id;
	private String usrNick;
	private String usrEmail;
	private int payAmnt;
	private String payText;
	private String paySt;
	private String payMethod;
	private String regDttm;
	private String rbankNm;
	private String rbankCd;
	private String rbankHolder;
	private String cancelDttm;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsrNick() {
		return usrNick;
	}
	public void setUsrNick(String usrNick) {
		this.usrNick = usrNick;
	}
	public String getUsrEmail() {
		return usrEmail;
	}
	public void setUsrEmail(String usrEmail) {
		this.usrEmail = usrEmail;
	}
	public int getPayAmnt() {
		return payAmnt;
	}
	public void setPayAmnt(int payAmnt) {
		this.payAmnt = payAmnt;
	}
	public String getPayText() {
		return payText;
	}
	public void setPayText(String payText) {
		this.payText = payText;
	}
	public String getPaySt() {
		return paySt;
	}
	public void setPaySt(String paySt) {
		this.paySt = paySt;
	}
	public String getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	public String getRegDttm() {
		return regDttm;
	}
	public void setRegDttm(String regDttm) {
		this.regDttm = regDttm;
	}
	public String getRbankNm() {
		return rbankNm;
	}
	public void setRbankNm(String rbankNm) {
		this.rbankNm = rbankNm;
	}
	public String getRbankCd() {
		return rbankCd;
	}
	public void setRbankCd(String rbankCd) {
		this.rbankCd = rbankCd;
	}
	public String getRbankHolder() {
		return rbankHolder;
	}
	public void setRbankHolder(String rbankHolder) {
		this.rbankHolder = rbankHolder;
	}
	public String getCancelDttm() {
		return cancelDttm;
	}
	public void setCancelDttm(String cancelDttm) {
		this.cancelDttm = cancelDttm;
	}
	@Override
	public String toString() {
		return "PayListVO [id=" + id + ", usrNick=" + usrNick + ", usrEmail=" + usrEmail + ", payAmnt=" + payAmnt
				+ ", payText=" + payText + ", paySt=" + paySt + ", payMethod=" + payMethod + ", regDttm=" + regDttm
				+ "]";
	}
}
