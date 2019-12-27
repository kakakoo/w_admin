package com.i4unetworks.weys.coupon;

import java.util.List;

public class CouponVO {

	private String couponId;
	private String couponNm;
	private String couponDesc;
	private String couponStore;
	private String pushDesc;
	private String pushType;
	private String couponSt;
	private String couponTp;
	private String sendTp;
	private String couponImg;
	private String couponBnr;
	private String couponTarget;
	private String couponCode;
	private String startDt;
	private String endDt;
	private String dueDt;
	private String regDttm;
	private int couponCnt = 0;
	private String excelFilePath;
	private String uCnt;
	private String tCnt;
	private int memberCost;
	private int memberPeriod;
	private int couponLimit;
	private String reservation;
	private String usrId;
	private String qrCode;
	private String qrImg;
	private String periodTp;
	private List<String> userList;
	
	public String getCouponId() {
		return couponId;
	}
	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}
	public String getCouponNm() {
		return couponNm;
	}
	public void setCouponNm(String couponNm) {
		this.couponNm = couponNm;
	}
	public String getCouponSt() {
		return couponSt;
	}
	public void setCouponSt(String couponSt) {
		this.couponSt = couponSt;
	}
	public String getCouponImg() {
		return couponImg;
	}
	public void setCouponImg(String couponImg) {
		this.couponImg = couponImg;
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
	public String getRegDttm() {
		return regDttm;
	}
	public void setRegDttm(String regDttm) {
		this.regDttm = regDttm;
	}
	public String getuCnt() {
		return uCnt;
	}
	public void setuCnt(String uCnt) {
		this.uCnt = uCnt;
	}
	public String gettCnt() {
		return tCnt;
	}
	public void settCnt(String tCnt) {
		this.tCnt = tCnt;
	}
	public String getPushDesc() {
		return pushDesc;
	}
	public void setPushDesc(String pushDesc) {
		this.pushDesc = pushDesc;
	}
	public String getPushType() {
		return pushType;
	}
	public void setPushType(String pushType) {
		this.pushType = pushType;
	}
	public String getCouponTarget() {
		return couponTarget;
	}
	public void setCouponTarget(String couponTarget) {
		this.couponTarget = couponTarget;
	}
	public int getCouponCnt() {
		return couponCnt;
	}
	public void setCouponCnt(int couponCnt) {
		this.couponCnt = couponCnt;
	}
	public String getExcelFilePath() {
		return excelFilePath;
	}
	public void setExcelFilePath(String excelFilePath) {
		this.excelFilePath = excelFilePath;
	}
	public List<String> getUserList() {
		return userList;
	}
	public void setUserList(List<String> userList) {
		this.userList = userList;
	}
	public String getReservation() {
		return reservation;
	}
	public void setReservation(String reservation) {
		this.reservation = reservation;
	}
	public String getCouponDesc() {
		return couponDesc;
	}
	public void setCouponDesc(String couponDesc) {
		this.couponDesc = couponDesc;
	}
	public String getCouponStore() {
		return couponStore;
	}
	public void setCouponStore(String couponStore) {
		this.couponStore = couponStore;
	}
	public String getCouponTp() {
		return couponTp;
	}
	public void setCouponTp(String couponTp) {
		this.couponTp = couponTp;
	}
	public String getCouponBnr() {
		return couponBnr;
	}
	public void setCouponBnr(String couponBnr) {
		this.couponBnr = couponBnr;
	}
	public int getMemberCost() {
		return memberCost;
	}
	public void setMemberCost(int memberCost) {
		this.memberCost = memberCost;
	}
	public int getMemberPeriod() {
		return memberPeriod;
	}
	public void setMemberPeriod(int memberPeriod) {
		this.memberPeriod = memberPeriod;
	}
	public String getSendTp() {
		return sendTp;
	}
	public void setSendTp(String sendTp) {
		this.sendTp = sendTp;
	}
	public String getCouponCode() {
		return couponCode;
	}
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	public int getCouponLimit() {
		return couponLimit;
	}
	public void setCouponLimit(int couponLimit) {
		this.couponLimit = couponLimit;
	}
	public String getDueDt() {
		return dueDt;
	}
	public void setDueDt(String dueDt) {
		this.dueDt = dueDt;
	}
	public String getUsrId() {
		return usrId;
	}
	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}
	public String getQrCode() {
		return qrCode;
	}
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
	public String getQrImg() {
		return qrImg;
	}
	public void setQrImg(String qrImg) {
		this.qrImg = qrImg;
	}
	public String getPeriodTp() {
		return periodTp;
	}
	public void setPeriodTp(String periodTp) {
		this.periodTp = periodTp;
	}
	@Override
	public String toString() {
		return "CouponVO [couponId=" + couponId + ", couponNm=" + couponNm + ", couponDesc=" + couponDesc
				+ ", couponStore=" + couponStore + ", pushDesc=" + pushDesc + ", pushType=" + pushType + ", couponSt="
				+ couponSt + ", couponTp=" + couponTp + ", sendTp=" + sendTp + ", couponImg=" + couponImg
				+ ", couponBnr=" + couponBnr + ", couponTarget=" + couponTarget + ", couponCode=" + couponCode
				+ ", startDt=" + startDt + ", endDt=" + endDt + ", dueDt=" + dueDt + ", regDttm=" + regDttm
				+ ", couponCnt=" + couponCnt + ", excelFilePath=" + excelFilePath + ", uCnt=" + uCnt + ", tCnt=" + tCnt
				+ ", memberCost=" + memberCost + ", memberPeriod=" + memberPeriod + ", couponLimit=" + couponLimit
				+ ", reservation=" + reservation + ", usrId=" + usrId + ", qrCode=" + qrCode + ", qrImg=" + qrImg
				+ ", periodTp=" + periodTp + ", userList=" + userList + "]";
	}
	
}
