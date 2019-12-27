package com.i4unetworks.weys.cash;

public class CashVO {
	private int cashId;
	private String usrNm;
	private String usrEmail;
	private String usrContact;
	private String memo;
	private int amnt;
	private String cashTp;
	private String mgtKey;
	private String itemName;
	private String orderNumber;
	private String cashSt;
	private String regDttm;
	public int getCashId() {
		return cashId;
	}
	public void setCashId(int cashId) {
		this.cashId = cashId;
	}
	public String getUsrNm() {
		return usrNm;
	}
	public void setUsrNm(String usrNm) {
		this.usrNm = usrNm;
	}
	public String getUsrEmail() {
		return usrEmail;
	}
	public void setUsrEmail(String usrEmail) {
		this.usrEmail = usrEmail;
	}
	public String getUsrContact() {
		return usrContact;
	}
	public void setUsrContact(String usrContact) {
		this.usrContact = usrContact;
	}
	public int getAmnt() {
		return amnt;
	}
	public void setAmnt(int amnt) {
		this.amnt = amnt;
	}
	public String getCashTp() {
		return cashTp;
	}
	public void setCashTp(String cashTp) {
		this.cashTp = cashTp;
	}
	public String getMgtKey() {
		return mgtKey;
	}
	public void setMgtKey(String mgtKey) {
		this.mgtKey = mgtKey;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getCashSt() {
		return cashSt;
	}
	public void setCashSt(String cashSt) {
		this.cashSt = cashSt;
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
	@Override
	public String toString() {
		return "CashVO [cashId=" + cashId + ", usrNm=" + usrNm + ", usrEmail=" + usrEmail + ", usrContact=" + usrContact
				+ ", amnt=" + amnt + ", cashTp=" + cashTp + ", mgtKey=" + mgtKey + ", itemName=" + itemName
				+ ", orderNumber=" + orderNumber + ", cashSt=" + cashSt + ", regDttm=" + regDttm + "]";
	}
	
}
