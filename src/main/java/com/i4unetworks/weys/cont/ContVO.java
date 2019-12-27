package com.i4unetworks.weys.cont;

public class ContVO {

	private int contId;
	private String contTitle;
	private String contCtgr;
	private String listType;
	private int seq;
	private String contSt;
	private String startDt;
	private String endDt;
	private String reservation;
	public int getContId() {
		return contId;
	}
	public void setContId(int contId) {
		this.contId = contId;
	}
	public String getContTitle() {
		return contTitle;
	}
	public void setContTitle(String contTitle) {
		this.contTitle = contTitle;
	}
	public String getContCtgr() {
		return contCtgr;
	}
	public void setContCtgr(String contCtgr) {
		this.contCtgr = contCtgr;
	}
	public String getListType() {
		return listType;
	}
	public void setListType(String listType) {
		this.listType = listType;
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
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getReservation() {
		return reservation;
	}
	public void setReservation(String reservation) {
		this.reservation = reservation;
	}
	public String getContSt() {
		return contSt;
	}
	public void setContSt(String contSt) {
		this.contSt = contSt;
	}
	@Override
	public String toString() {
		return "ContVO [contId=" + contId + ", contTitle=" + contTitle + ", contCtgr=" + contCtgr + ", listType="
				+ listType + ", seq=" + seq + ", contSt=" + contSt + ", startDt=" + startDt + ", endDt=" + endDt
				+ ", reservation=" + reservation + "]";
	}
	
}
