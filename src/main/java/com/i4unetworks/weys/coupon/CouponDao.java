package com.i4unetworks.weys.coupon;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.i4unetworks.weys.common.Paging;

@Repository
public interface CouponDao {

	List<CouponVO> selectCouponList(Paging paging);

	int selectCouponListCnt();

	int selectTargetAllCnt();

	int selectTargetMemberCnt();

	int insertCouponByExcel(CouponVO couponVO);

	int insertCouponByMember(CouponVO couponVO);

	int insertCouponInfo(CouponVO couponVO);

	CouponVO selectCouponInfo(String couponId);

	int updateCouponStatusDone(String couponId);

	int deleteCouponInfo(String couponId);

	int deleteUserCoupon(String couponId);

	int updateCouponInfo(CouponVO couponVO);

	List<String> selectPushUsrList(Map<String, Object> pushMap);

	void deleteUUID(String uuid);

	String selectCouponPush(String couponId);

	void insertMACouponByExcel(CouponVO couponVO);

	void updateMemCostByExcel(CouponVO couponVO);

	int selectCouponQr(String qr);

	void insertCouponBarbageExcel(CouponVO couponVO);

	int insertCouponByAll(CouponVO couponVO);

	void insertMACouponByAll(CouponVO couponVO);

	void updateMemCostByAll(CouponVO couponVO);

	List<String> selectAllUser();

}
