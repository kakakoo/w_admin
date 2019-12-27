package com.i4unetworks.weys.notice;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.i4unetworks.weys.common.Paging;

@Repository
public interface NoticeDao {

	List<NoticeDetailVO> selectNoticeList(Paging paging);

	int selectNoticeListCnt(Paging paging);

	int updateStatus(Map<String, Object> reqMap);

	int insertNotice(NoticeDetailVO noticeDetailVO);

	NoticeDetailVO selectNotice(String noticeId);

	int updateNotice(NoticeDetailVO noticeDetailVO);

	int deleteNotice(String noticeId);

	List<String> selectAllUsrInfoForPush(Map<String, Object> reqMap);

	void insertAlarm(Map<String, Object> alarm);

	List<String> selectCustomPush(Map<String, Object> reqMap);

	List<String> selectNoticePush(Map<String, Object> reqMap);

	List<String> selectUsrUidCont(Map<String, Object> reqMap);

	List<String> selectUsrUidContAll(Map<String, Object> reqMap);

	List<NoticeDetailVO> selectAdminList(Paging paging);

	int selectAdminListCnt(Paging paging);

	int updateStatusAdmin(Map<String, Object> reqMap);

	int insertAdminNotice(NoticeDetailVO noticeDetailVO);

	int updateAdminNotice(NoticeDetailVO noticeDetailVO);

	NoticeDetailVO selectAdminNotice(String noticeId);

}
