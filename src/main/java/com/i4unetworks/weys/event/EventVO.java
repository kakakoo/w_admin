package com.i4unetworks.weys.event;

import java.util.List;

public class EventVO {

	private int eventId;
	private String eventTitle;
	private String eventDesc;
	private String eventImg;
	private List<String> imgList;
	private String eventBnr;
	private int couponId;
	private String eventTxt;
	private int btnPst;
	private int eventLimit;
	private int memberPeriod;
	private int memberPoint;
	private String eventSt;
	private String eventTp;
	private String startDt;
	private String endDt;
	private String regDttm;
	private String reservation;
	private int ecnt;
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public String getEventTitle() {
		return eventTitle;
	}
	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}
	public String getEventSt() {
		return eventSt;
	}
	public void setEventSt(String eventSt) {
		this.eventSt = eventSt;
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
	public String getEventImg() {
		return eventImg;
	}
	public void setEventImg(String eventImg) {
		this.eventImg = eventImg;
	}
	public int getCouponId() {
		return couponId;
	}
	public void setCouponId(int couponId) {
		this.couponId = couponId;
	}
	public int getEventLimit() {
		return eventLimit;
	}
	public void setEventLimit(int eventLimit) {
		this.eventLimit = eventLimit;
	}
	public String getReservation() {
		return reservation;
	}
	public void setReservation(String reservation) {
		this.reservation = reservation;
	}
	public int getMemberPeriod() {
		return memberPeriod;
	}
	public void setMemberPeriod(int memberPeriod) {
		this.memberPeriod = memberPeriod;
	}
	public int getMemberPoint() {
		return memberPoint;
	}
	public void setMemberPoint(int memberPoint) {
		this.memberPoint = memberPoint;
	}
	public String getEventBnr() {
		return eventBnr;
	}
	public void setEventBnr(String eventBnr) {
		this.eventBnr = eventBnr;
	}
	public String getEventTp() {
		return eventTp;
	}
	public void setEventTp(String eventTp) {
		this.eventTp = eventTp;
	}
	public List<String> getImgList() {
		return imgList;
	}
	public void setImgList(List<String> imgList) {
		this.imgList = imgList;
	}
	public String getEventTxt() {
		return eventTxt;
	}
	public void setEventTxt(String eventTxt) {
		this.eventTxt = eventTxt;
	}
	public int getBtnPst() {
		return btnPst;
	}
	public void setBtnPst(int btnPst) {
		this.btnPst = btnPst;
	}
	public String getEventDesc() {
		return eventDesc;
	}
	public void setEventDesc(String eventDesc) {
		this.eventDesc = eventDesc;
	}
	public int getEcnt() {
		return ecnt;
	}
	public void setEcnt(int ecnt) {
		this.ecnt = ecnt;
	}
	@Override
	public String toString() {
		return "EventVO [eventId=" + eventId + ", eventTitle=" + eventTitle + ", eventDesc=" + eventDesc + ", eventImg="
				+ eventImg + ", imgList=" + imgList + ", eventBnr=" + eventBnr + ", couponId=" + couponId
				+ ", eventTxt=" + eventTxt + ", btnPst=" + btnPst + ", eventLimit=" + eventLimit + ", memberPeriod="
				+ memberPeriod + ", memberPoint=" + memberPoint + ", eventSt=" + eventSt + ", eventTp=" + eventTp
				+ ", startDt=" + startDt + ", endDt=" + endDt + ", regDttm=" + regDttm + ", reservation=" + reservation
				+ "]";
	}
}
