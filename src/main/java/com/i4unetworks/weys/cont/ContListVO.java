package com.i4unetworks.weys.cont;

public class ContListVO {
	private int clId;
	private int contId;
	private String clTitle;
	private String clSubTitle;
	private String clColor;
	private String clImg;
	private String clUrl;
	private String startDt;
	private String endDt;
	private String reservation;
	private int clSeq;
	public int getClId() {
		return clId;
	}
	public void setClId(int clId) {
		this.clId = clId;
	}
	public String getClTitle() {
		return clTitle;
	}
	public void setClTitle(String clTitle) {
		this.clTitle = clTitle;
	}
	public String getClSubTitle() {
		return clSubTitle;
	}
	public void setClSubTitle(String clSubTitle) {
		this.clSubTitle = clSubTitle;
	}
	public String getClColor() {
		return clColor;
	}
	public void setClColor(String clColor) {
		this.clColor = clColor;
	}
	public String getClImg() {
		return clImg;
	}
	public void setClImg(String clImg) {
		this.clImg = clImg;
	}
	public String getClUrl() {
		return clUrl;
	}
	public void setClUrl(String clUrl) {
		this.clUrl = clUrl;
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
	public int getClSeq() {
		return clSeq;
	}
	public void setClSeq(int clSeq) {
		this.clSeq = clSeq;
	}
	public int getContId() {
		return contId;
	}
	public void setContId(int contId) {
		this.contId = contId;
	}
	public String getReservation() {
		return reservation;
	}
	public void setReservation(String reservation) {
		this.reservation = reservation;
	}
	@Override
	public String toString() {
		return "ContListVO [clId=" + clId + ", contId=" + contId + ", clTitle=" + clTitle + ", clSubTitle=" + clSubTitle
				+ ", clColor=" + clColor + ", clImg=" + clImg + ", clUrl=" + clUrl + ", startDt=" + startDt + ", endDt="
				+ endDt + ", clSeq=" + clSeq + "]";
	}
}
