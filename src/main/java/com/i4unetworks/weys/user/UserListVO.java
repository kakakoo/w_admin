package com.i4unetworks.weys.user;

public class UserListVO {

	private String usrId;
	private String usrEmail;
	private String usrTel;
	private String usrNm;
	private String usrFrom;
	private String regDttm;
	private String usrUnregDttm;
	private String startDt;
	private String endDt;
	private int rCnt;
	private int rdCnt;
	private int cCnt;
	private int cost;
	private String usrGrade;
	public String getUsrId() {
		return usrId;
	}
	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}
	public String getUsrEmail() {
		return usrEmail;
	}
	public void setUsrEmail(String usrEmail) {
		this.usrEmail = usrEmail;
	}
	public String getRegDttm() {
		return regDttm;
	}
	public void setRegDttm(String regDttm) {
		this.regDttm = regDttm;
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
	public String getUsrNm() {
		return usrNm;
	}
	public void setUsrNm(String usrNm) {
		this.usrNm = usrNm;
	}
	public String getUsrTel() {
		return usrTel;
	}
	public void setUsrTel(String usrTel) {
		this.usrTel = usrTel;
	}
	public int getrCnt() {
		return rCnt;
	}
	public void setrCnt(int rCnt) {
		this.rCnt = rCnt;
	}
	public int getRdCnt() {
		return rdCnt;
	}
	public void setRdCnt(int rdCnt) {
		this.rdCnt = rdCnt;
	}
	public int getcCnt() {
		return cCnt;
	}
	public void setcCnt(int cCnt) {
		this.cCnt = cCnt;
	}
	public String getUsrGrade() {
		return usrGrade;
	}
	public void setUsrGrade(String usrGrade) {
		this.usrGrade = usrGrade;
	}
	public String getUsrUnregDttm() {
		return usrUnregDttm;
	}
	public void setUsrUnregDttm(String usrUnregDttm) {
		this.usrUnregDttm = usrUnregDttm;
	}
	public String getUsrFrom() {
		return usrFrom;
	}
	public void setUsrFrom(String usrFrom) {
		this.usrFrom = usrFrom;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	@Override
	public String toString() {
		return "UserListVO [usrId=" + usrId + ", usrEmail=" + usrEmail + ", usrTel=" + usrTel + ", usrNm=" + usrNm
				+ ", usrFrom=" + usrFrom + ", regDttm=" + regDttm + ", usrUnregDttm=" + usrUnregDttm + ", startDt="
				+ startDt + ", endDt=" + endDt + ", rCnt=" + rCnt + ", rdCnt=" + rdCnt + ", cCnt=" + cCnt
				+ ", usrGrade=" + usrGrade + "]";
	}
}
