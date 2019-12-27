package com.i4unetworks.weys.gold;

public class GoldVO {
	private int storeId;
	private String unit;
	private int paper;
	private int amnt;
	private int sumAmnt;
	private int rowSpan;
	private String rsvDt;
	private String unitDp;
	private int sAmnt;
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public int getPaper() {
		return paper;
	}
	public void setPaper(int paper) {
		this.paper = paper;
	}
	public int getAmnt() {
		return amnt;
	}
	public void setAmnt(int amnt) {
		this.amnt = amnt;
	}
	public int getSumAmnt() {
		return sumAmnt;
	}
	public void setSumAmnt(int sumAmnt) {
		this.sumAmnt = sumAmnt;
	}
	public String getRsvDt() {
		return rsvDt;
	}
	public void setRsvDt(String rsvDt) {
		this.rsvDt = rsvDt;
	}
	public int getRowSpan() {
		return rowSpan;
	}
	public void setRowSpan(int rowSpan) {
		this.rowSpan = rowSpan;
	}
	public String getUnitDp() {
		return unitDp;
	}
	public void setUnitDp(String unitDp) {
		this.unitDp = unitDp;
	}
	public int getsAmnt() {
		return sAmnt;
	}
	public void setsAmnt(int sAmnt) {
		this.sAmnt = sAmnt;
	}
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	@Override
	public String toString() {
		return "GoldVO [unit=" + unit + ", paper=" + paper + ", amnt=" + amnt + ", sumAmnt=" + sumAmnt + ", rsvDt="
				+ rsvDt + "]";
	}
}
