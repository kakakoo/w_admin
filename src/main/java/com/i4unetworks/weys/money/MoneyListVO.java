package com.i4unetworks.weys.money;

public class MoneyListVO {
	private int mmId;
	private String unit;
	private int buyAmnt;
	private double buyRate;
	private int buyKor;
	private String buyDttm;
	private int sellAmnt;
	private int sellKor;
	private double avgRate = 0.0;
	private double diffRate = 0.0;
	private double leftRate = 0.0;
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public int getBuyAmnt() {
		return buyAmnt;
	}
	public void setBuyAmnt(int buyAmnt) {
		this.buyAmnt = buyAmnt;
	}
	public double getBuyRate() {
		return buyRate;
	}
	public void setBuyRate(double buyRate) {
		this.buyRate = buyRate;
	}
	public int getBuyKor() {
		return buyKor;
	}
	public void setBuyKor(int buyKor) {
		this.buyKor = buyKor;
	}
	public String getBuyDttm() {
		return buyDttm;
	}
	public void setBuyDttm(String buyDttm) {
		this.buyDttm = buyDttm;
	}
	public int getSellAmnt() {
		return sellAmnt;
	}
	public void setSellAmnt(int sellAmnt) {
		this.sellAmnt = sellAmnt;
	}
	public int getSellKor() {
		return sellKor;
	}
	public void setSellKor(int sellKor) {
		this.sellKor = sellKor;
	}
	public double getAvgRate() {
		return avgRate;
	}
	public void setAvgRate(double avgRate) {
		this.avgRate = avgRate;
	}
	public double getDiffRate() {
		return diffRate;
	}
	public void setDiffRate(double diffRate) {
		this.diffRate = diffRate;
	}
	public int getMmId() {
		return mmId;
	}
	public void setMmId(int mmId) {
		this.mmId = mmId;
	}
	public double getLeftRate() {
		return leftRate;
	}
	public void setLeftRate(double leftRate) {
		this.leftRate = leftRate;
	}
	@Override
	public String toString() {
		return "MoneyListVO [mmId=" + mmId + ", unit=" + unit + ", buyAmnt=" + buyAmnt + ", buyRate=" + buyRate
				+ ", buyKor=" + buyKor + ", buyDttm=" + buyDttm + ", sellAmnt=" + sellAmnt + ", sellKor=" + sellKor
				+ ", avgRate=" + avgRate + ", diffRate=" + diffRate + ", leftRate=" + leftRate + "]";
	}
}
