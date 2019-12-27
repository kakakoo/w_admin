package com.i4unetworks.weys.unit;

public class UnitListVO {
	private int unitId;
	private String unitNm;
	private String unitCd;
	private String unitSt;
	private String rsvSt;
	private int unitSize;
	private int unitMin;
	private double weysCommis;
	private double unitCommis;
	private double airCommis;
	public int getUnitId() {
		return unitId;
	}
	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}
	public String getUnitNm() {
		return unitNm;
	}
	public void setUnitNm(String unitNm) {
		this.unitNm = unitNm;
	}
	public String getUnitCd() {
		return unitCd;
	}
	public void setUnitCd(String unitCd) {
		this.unitCd = unitCd;
	}
	public String getUnitSt() {
		return unitSt;
	}
	public void setUnitSt(String unitSt) {
		this.unitSt = unitSt;
	}
	public String getRsvSt() {
		return rsvSt;
	}
	public void setRsvSt(String rsvSt) {
		this.rsvSt = rsvSt;
	}
	public int getUnitSize() {
		return unitSize;
	}
	public void setUnitSize(int unitSize) {
		this.unitSize = unitSize;
	}
	public int getUnitMin() {
		return unitMin;
	}
	public void setUnitMin(int unitMin) {
		this.unitMin = unitMin;
	}
	public double getWeysCommis() {
		return weysCommis;
	}
	public void setWeysCommis(double weysCommis) {
		this.weysCommis = weysCommis;
	}
	public double getUnitCommis() {
		return unitCommis;
	}
	public void setUnitCommis(double unitCommis) {
		this.unitCommis = unitCommis;
	}
	public double getAirCommis() {
		return airCommis;
	}
	public void setAirCommis(double airCommis) {
		this.airCommis = airCommis;
	}
	@Override
	public String toString() {
		return "UnitListVO [unitId=" + unitId + ", unitNm=" + unitNm + ", unitCd=" + unitCd + ", unitSt=" + unitSt
				+ ", rsvSt=" + rsvSt + ", unitSize=" + unitSize + ", unitMin=" + unitMin + ", weysCommis=" + weysCommis
				+ ", unitCommis=" + unitCommis + ", airCommis=" + airCommis + "]";
	}
	
}
