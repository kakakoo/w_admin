package com.i4unetworks.weys.banner;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.i4unetworks.weys.common.Paging;

@Repository
public interface BannerDao {

	List<BannerVO> selectBannerList(Paging paging);

	int selectBanneristCnt();

	BannerVO selectBannerInfo(String bannerId);

	int insertBanner(BannerVO bannerVO);

	int updateBanner(BannerVO bannerVO);

	int deleteBanner(String bannerId);

	List<BannerVO> selectRsvBannerList(Paging paging);

	int selectRsvBanneristCnt();

	BannerVO selectRsvBannerInfo(String bnrId);

	int insertRsvBanner(BannerVO bannerVO);

	int updateRsvBanner(BannerVO bannerVO);

	int deleteRsvBanner(int bnrId);

	void updateBannerTarget(BannerVO bannerVO);

	void updateRsvBnrTarget(BannerVO bannerVO);

	List<Map<String, Object>> selectEventList();

	List<Map<String, Object>> selectNoticeList();

	List<Map<String, Object>> selectContList();

	List<Map<String, Object>> selectUnitList();

}
