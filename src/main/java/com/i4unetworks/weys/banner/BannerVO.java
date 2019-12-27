package com.i4unetworks.weys.banner;

public class BannerVO {

	private String bannerId;
	private String bannerNm;
	private String bannerUrl;
	private String redirectUrl;
	private String redirectApp;
	private String startDt;
	private String endDt;
	private String reservation;
	private String regDttm;
	private String redirectType;

	private int eventId;
	private int noticeId;
	private int bnrId;
	private String unitCd;
	private String contId;
	private String bnrTp;
	private String bnrCont;
	private String bnrSt;
	private String target;
	public String getBannerId() {
		return bannerId;
	}
	public void setBannerId(String bannerId) {
		this.bannerId = bannerId;
	}
	public String getBannerNm() {
		return bannerNm;
	}
	public void setBannerNm(String bannerNm) {
		this.bannerNm = bannerNm;
	}
	public String getBannerUrl() {
		return bannerUrl;
	}
	public void setBannerUrl(String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}
	public String getRedirectUrl() {
		return redirectUrl;
	}
	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
	public String getRedirectApp() {
		return redirectApp;
	}
	public void setRedirectApp(String redirectApp) {
		this.redirectApp = redirectApp;
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
	public String getReservation() {
		return reservation;
	}
	public void setReservation(String reservation) {
		this.reservation = reservation;
	}
	public String getRegDttm() {
		return regDttm;
	}
	public void setRegDttm(String regDttm) {
		this.regDttm = regDttm;
	}
	public String getRedirectType() {
		return redirectType;
	}
	public void setRedirectType(String redirectType) {
		this.redirectType = redirectType;
	}
	public int getBnrId() {
		return bnrId;
	}
	public void setBnrId(int bnrId) {
		this.bnrId = bnrId;
	}
	public String getBnrSt() {
		return bnrSt;
	}
	public void setBnrSt(String bnrSt) {
		this.bnrSt = bnrSt;
	}
	public String getBnrTp() {
		return bnrTp;
	}
	public void setBnrTp(String bnrTp) {
		this.bnrTp = bnrTp;
	}
	public String getBnrCont() {
		return bnrCont;
	}
	public void setBnrCont(String bnrCont) {
		this.bnrCont = bnrCont;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public int getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}
	public String getContId() {
		return contId;
	}
	public void setContId(String contId) {
		this.contId = contId;
	}
	public String getUnitCd() {
		return unitCd;
	}
	public void setUnitCd(String unitCd) {
		this.unitCd = unitCd;
	}
	@Override
	public String toString() {
		return "BannerVO [bannerId=" + bannerId + ", bannerNm=" + bannerNm + ", bannerUrl=" + bannerUrl
				+ ", redirectUrl=" + redirectUrl + ", redirectApp=" + redirectApp + ", startDt=" + startDt + ", endDt="
				+ endDt + ", reservation=" + reservation + ", regDttm=" + regDttm + ", redirectType=" + redirectType
				+ ", eventId=" + eventId + ", noticeId=" + noticeId + ", bnrId=" + bnrId + ", unitCd=" + unitCd
				+ ", contId=" + contId + ", bnrTp=" + bnrTp + ", bnrCont=" + bnrCont + ", bnrSt=" + bnrSt + ", target="
				+ target + "]";
	}
	
}
