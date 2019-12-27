package com.i4unetworks.weys.member;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.i4unetworks.weys.common.Paging;

@Repository
public interface MemberDao {

	List<MemberVO> selectPayList(Paging paging);

	int selectPayListCnt();

	MemberVO selectPayInfo(String payId);

	int insertPayInfo(Map<String, Object> reqMap);

	int updatePayInfo(Map<String, Object> reqMap);

	int deletePayInfo(String payId);

	/**
	 * TEST
	 * @param rsvNo
	 * @return
	 */
	int selectCheckRsvNo(String rsvNo);

	void insertRsv(Map<String, Object> testMap);

}
