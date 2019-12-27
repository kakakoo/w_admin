package com.i4unetworks.weys.unit;

public class UnitVO {
	private String unitCd;
	private String unitNm;
	public String getUnitCd() {
		return unitCd;
	}
	public void setUnitCd(String unitCd) {
		this.unitCd = unitCd;
	}
	public String getUnitNm() {
		return unitNm;
	}
	public void setUnitNm(String unitNm) {
		this.unitNm = unitNm;
	}
	@Override
	public String toString() {
		return "UnitVO [unitCd=" + unitCd + ", unitNm=" + unitNm + "]";
	}
}
