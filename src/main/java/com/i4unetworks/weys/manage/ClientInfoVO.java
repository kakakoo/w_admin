package com.i4unetworks.weys.manage;

public class ClientInfoVO {

	private String usrNick;
	private String usrNm;
	private String usrNmId;
	private String usrEmail;
	private String startDt;
	private String endDt;
	private String limitAmnt;
	private String type;
	private String cost;

	public String getUsrNick() {
		return usrNick;
	}
	public void setUsrNick(String usrNick) {
		this.usrNick = usrNick;
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
	public String getStartDt() {
		return startDt;
	}
	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}
	public String getEndDt() {
		return endDt;
	}
	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}
	public String getLimitAmnt() {
		return limitAmnt;
	}
	public void setLimitAmnt(String limitAmnt) {
		this.limitAmnt = limitAmnt;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getUsrNmId() {
		return usrNmId;
	}
	public void setUsrNmId(String usrNmId) {
		this.usrNmId = usrNmId;
	}
	@Override
	public String toString() {
		return "ClientInfoVO [usrNick=" + usrNick + ", usrNm=" + usrNm + ", usrNmId=" + usrNmId + ", usrEmail="
				+ usrEmail + ", startDt=" + startDt + ", endDt=" + endDt + ", limitAmnt=" + limitAmnt + ", type=" + type
				+ ", cost=" + cost + "]";
	}
	
}
