package com.i4unetworks.weys.notice;

public class NoticeDetailVO {

	private String noticeId;
	private String noticeTitle;
	private String noticeContent;
	private String noticeRegDttm;
	private String noticeModDttm;
	private String noticeSt;
	private String imgPath;
	private String tp;
	public String getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}
	public String getNoticeTitle() {
		return noticeTitle;
	}
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	public String getNoticeContent() {
		return noticeContent;
	}
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	public String getNoticeRegDttm() {
		return noticeRegDttm;
	}
	public void setNoticeRegDttm(String noticeRegDttm) {
		this.noticeRegDttm = noticeRegDttm;
	}
	public String getNoticeModDttm() {
		return noticeModDttm;
	}
	public void setNoticeModDttm(String noticeModDttm) {
		this.noticeModDttm = noticeModDttm;
	}
	public String getNoticeSt() {
		return noticeSt;
	}
	public void setNoticeSt(String noticeSt) {
		this.noticeSt = noticeSt;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public String getTp() {
		return tp;
	}
	public void setTp(String tp) {
		this.tp = tp;
	}
	@Override
	public String toString() {
		return "NoticeDetailVO [noticeId=" + noticeId + ", noticeTitle=" + noticeTitle + ", noticeContent="
				+ noticeContent + ", noticeRegDttm=" + noticeRegDttm + ", noticeModDttm=" + noticeModDttm
				+ ", noticeSt=" + noticeSt + ", imgPath=" + imgPath + ", tp=" + tp + "]";
	}
}
