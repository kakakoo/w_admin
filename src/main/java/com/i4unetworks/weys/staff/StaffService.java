package com.i4unetworks.weys.staff;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i4unetworks.weys.common.Paging;

@Service
public class StaffService {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private StaffDao staffDao;
	
	public List<StaffVO> selectLogHist(Paging paging) {

		if (paging.getIsSearch() == 1)
			paging.setSearchPage(0);
		else
			paging.setSearchPage((paging.getPageNo() - 1) * 10);
		
		return staffDao.selectLogHist(paging);
	}

	public int selectLogHistCnt() {
		return staffDao.selectLogHistCnt();
	}

	public List<StaffVO> selectStaffNotice(Paging paging) {

		if (paging.getIsSearch() == 1)
			paging.setSearchPage(0);
		else
			paging.setSearchPage((paging.getPageNo() - 1) * 10);
		
		return staffDao.selectStaffNotice(paging);
	}

	public int selectStaffNoticeCnt() {
		return staffDao.selectStaffNoticeCnt();
	}

	public StaffVO selectNoticeInfo(String anId) {
		return staffDao.selectNoticeInfo(anId);
	}

	public int insertNotice(StaffVO staffVO) {
		return staffDao.insertNotice(staffVO);
	}

	public int updateNotice(StaffVO staffVO) {
		return staffDao.updateNotice(staffVO);
	}

	public int deleteNotice(int anId) {
		
		int res = staffDao.deleteNoticeLog(anId);
		
		if(res > 0){
			res = staffDao.deleteNotice(anId);
		}
		
		return res;
	}
}
