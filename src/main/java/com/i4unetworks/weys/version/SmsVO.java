package com.i4unetworks.weys.version;

public class SmsVO {

	private int smsId;
	private String smsTitle;
	private String smsText;
	public int getSmsId() {
		return smsId;
	}
	public void setSmsId(int smsId) {
		this.smsId = smsId;
	}
	public String getSmsTitle() {
		return smsTitle;
	}
	public void setSmsTitle(String smsTitle) {
		this.smsTitle = smsTitle;
	}
	public String getSmsText() {
		return smsText;
	}
	public void setSmsText(String smsText) {
		this.smsText = smsText;
	}
	@Override
	public String toString() {
		return "SmsVO [smsId=" + smsId + ", smsTitle=" + smsTitle + ", smsText=" + smsText + "]";
	}
}
