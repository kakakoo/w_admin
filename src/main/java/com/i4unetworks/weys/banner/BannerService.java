package com.i4unetworks.weys.banner;

import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i4unetworks.weys.common.Paging;

@Service
public class BannerService {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private BannerDao bannerDao;
	
	public List<BannerVO> selectBannerList(Paging paging) {
		
		if (paging.getIsSearch() == 1)
			paging.setSearchPage(0);
		else
			paging.setSearchPage((paging.getPageNo() - 1) * 10);
		
		return bannerDao.selectBannerList(paging);
	}
	
	public int selectBanneristCnt() {
		return bannerDao.selectBanneristCnt();
	}

	public BannerVO selectBannerInfo(String bannerId) {
		return bannerDao.selectBannerInfo(bannerId);
	}

	public int insertBanner(BannerVO bannerVO) {
		
		StringTokenizer st = new StringTokenizer(bannerVO.getReservation(), "-");
		bannerVO.setStartDt(st.nextToken());
		bannerVO.setEndDt(st.nextToken());
		
		if(bannerVO.getRedirectApp() != null){
			String app = bannerVO.getRedirectApp();
			
			if(app.equals("event")){
				String target = "/api/event/gVerSion/" + bannerVO.getEventId();
				bannerVO.setTarget(target);
			} else if(app.equals("notice")){
				String target = "/api/notice/gVerSion/" + bannerVO.getNoticeId();
				bannerVO.setTarget(target);
			} else if(app.equals("cont")){
				bannerVO.setTarget(bannerVO.getContId());
			}  else if(app.equals("rsv")){
				bannerVO.setTarget(bannerVO.getUnitCd());
			} 
		}
		
		return bannerDao.insertBanner(bannerVO);
	}

	public int updateBanner(BannerVO bannerVO) {

		StringTokenizer st = new StringTokenizer(bannerVO.getReservation(), "-");
		bannerVO.setStartDt(st.nextToken());
		bannerVO.setEndDt(st.nextToken());
		return bannerDao.updateBanner(bannerVO);
	}

	public int deleteBanner(String bannerId) {
		return bannerDao.deleteBanner(bannerId);
	}

	public List<BannerVO> selectRsvBannerList(Paging paging) {
		if (paging.getIsSearch() == 1)
			paging.setSearchPage(0);
		else
			paging.setSearchPage((paging.getPageNo() - 1) * 10);
		return bannerDao.selectRsvBannerList(paging);
	}

	public int selectRsvBanneristCnt() {
		return bannerDao.selectRsvBanneristCnt();
	}

	public BannerVO selectRsvBannerInfo(String bnrId) {
		return bannerDao.selectRsvBannerInfo(bnrId);
	}

	public int insertRsvBanner(BannerVO bannerVO) {

		StringTokenizer st = new StringTokenizer(bannerVO.getReservation(), "-");
		bannerVO.setStartDt(st.nextToken());
		bannerVO.setEndDt(st.nextToken());
		
		if(bannerVO.getRedirectApp() != null){
			String app = bannerVO.getRedirectApp();
			
			if(app.equals("event")){
				String target = "/api/event/gVerSion/" + bannerVO.getEventId();
				bannerVO.setTarget(target);
			} else if(app.equals("notice")){
				String target = "/api/notice/gVerSion/" + bannerVO.getNoticeId();
				bannerVO.setTarget(target);
			}
		}
		return bannerDao.insertRsvBanner(bannerVO);
	}

	public int updateRsvBanner(BannerVO bannerVO) {

		StringTokenizer st = new StringTokenizer(bannerVO.getReservation(), "-");
		bannerVO.setStartDt(st.nextToken());
		bannerVO.setEndDt(st.nextToken());
		return bannerDao.updateRsvBanner(bannerVO);
	}

	public int deleteRsvBanner(int bnrId) {
		return bannerDao.deleteRsvBanner(bnrId);
	}

	public List<Map<String, Object>> selectEventList() {
		return bannerDao.selectEventList();
	}

	public List<Map<String, Object>> selectNoticeList() {
		return bannerDao.selectNoticeList();
	}

	public List<Map<String, Object>> selectContList() {
		return bannerDao.selectContList();
	}

	public List<Map<String, Object>> selectUnitList() {
		return bannerDao.selectUnitList();
	}
}
