package com.i4unetworks.weys.event;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.i4unetworks.weys.common.Paging;
import com.i4unetworks.weys.coupon.CouponVO;

@Repository
public interface EventDao {

	List<EventVO> getEventList(Paging paging);

	int getEventListCnt(Paging paging);

	int insertEvent(EventVO eventVO);

	EventVO selectEventInfo(String eventId);

	void updateEvent(EventVO eventVO);

	List<CouponVO> selectEventCoupList();

	String selectEventTitle(String eventId);

	List<String> selectNotJoinEvent(Map<String, Object> reqMap);

	void insertNotJoinEvent(Map<String, Object> alarm);

	List<Map<String, Object>> getEventJoinList(Paging paging);

	int getEventJoinListCnt(Paging paging);

	int insertEventJoinCoupon(Map<String, Object> reqMap);

	Map<String, Object> selectUsrPushInfo(Map<String, Object> reqMap);

	int updateEventJoined(Map<String, Object> reqMap);

}
