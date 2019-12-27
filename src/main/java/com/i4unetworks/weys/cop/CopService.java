package com.i4unetworks.weys.cop;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i4unetworks.weys.common.AriaUtils;
import com.i4unetworks.weys.common.Paging;
import com.i4unetworks.weys.common.Utils;

@Service
public class CopService {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private CopDao copDao;
	
	public List<CopVO> selectCopList(Paging paging) {

		if (paging.getIsSearch() == 1)
			paging.setSearchPage(0);
		else
			paging.setSearchPage((paging.getPageNo() - 1) * 10);
		
		return copDao.selectCopList(paging);
	}
	
	public int selectCopListCnt() {
		return copDao.selectCopListCnt();
	}

	public CopVO selectCopInfo(String copId) {
		return copDao.selectCopInfo(copId);
	}

	public int insertCop(CopVO copVO) throws Exception {
		
		String copCd = Utils.getRandomKey(20);
		
		while(true){
			int cnt = copDao.checkCopCd(copCd);
			if(cnt == 0){
				copVO.setCopCd(copCd);
				break;
			}
		}
		
		int res = copDao.insertCop(copVO); 
		if(res > 0){
			String pw = AriaUtils.encryptPassword(copCd, String.valueOf(copVO.getCopId()));
			copVO.setCopPw(pw);
			res = copDao.updateCopPw(copVO); 
		}
		return res;
	}

	public int deleteCop(String copId) {
		return copDao.deleteCop(copId);
	}

	public int updateCop(CopVO copVO) {
		return copDao.updateCop(copVO);
	}

	public List<CopBnrVO> selectCopBnrList(Paging paging) {

		if (paging.getIsSearch() == 1)
			paging.setSearchPage(0);
		else
			paging.setSearchPage((paging.getPageNo() - 1) * 10);
		
		return copDao.selectCopBnrList(paging);
	}

	public int selectCopBnrListCnt(Paging paging) {
		return copDao.selectCopBnrListCnt(paging);
	}

	public CopBnrVO selectCopBnrInfo(String cbId) {
		return copDao.selectCopBnrInfo(cbId);
	}

	public void insertCopBnr(CopBnrVO copBnrVO) {
		copDao.insertCopBnr(copBnrVO);
	}

	public void updateCopBnr(CopBnrVO copBnrVO) {
		copDao.updateCopBnr(copBnrVO);
	}

	public void deleteCopBnr(String cbId) {
		copDao.deleteCopBnr(cbId);
	}
}
