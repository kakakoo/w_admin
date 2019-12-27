package com.i4unetworks.weys.staff;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.i4unetworks.weys.common.Paging;

@Repository
public interface StaffDao {

	List<StaffVO> selectLogHist(Paging paging);

	int selectLogHistCnt();

	List<StaffVO> selectStaffNotice(Paging paging);

	int selectStaffNoticeCnt();

	StaffVO selectNoticeInfo(String anId);

	int insertNotice(StaffVO staffVO);

	int updateNotice(StaffVO staffVO);

	int deleteNoticeLog(int anId);

	int deleteNotice(int anId);

}
