package com.i4unetworks.weys.cop;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.i4unetworks.weys.common.Paging;

@Repository
public interface CopDao {

	List<CopVO> selectCopList(Paging paging);

	int selectCopListCnt();

	CopVO selectCopInfo(String copId);

	int insertCop(CopVO copVO);

	int deleteCop(String copId);

	int updateCop(CopVO copVO);

	List<CopBnrVO> selectCopBnrList(Paging paging);

	int selectCopBnrListCnt(Paging paging);

	CopBnrVO selectCopBnrInfo(String cbId);

	void insertCopBnr(CopBnrVO copBnrVO);

	void updateCopBnr(CopBnrVO copBnrVO);

	void deleteCopBnr(String cbId);

	int checkCopCd(String copCd);

	int updateCopPw(CopVO copVO);

}
