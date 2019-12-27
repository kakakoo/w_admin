package com.i4unetworks.weys.cont;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.i4unetworks.weys.common.Paging;

@Repository
public interface ContDao {

	List<ContVO> getContList(Paging paging);

	int getContListCnt(Paging paging);

	ContVO selectContInfo(String contId);

	List<ContListVO> selectContInfoList(String contId);

	int insertContInfo(ContVO contVO);

	int updateDeleteSeq(int clId);

	int deleteContList(int clId);

	int updateClSeqUpF(int clId);

	int updateClSeqUpS(int clId);

	int updateClSeqDownF(int clId);

	int updateClSeqDownS(int clId);

	int updateContInfo(ContVO contVO);

	void updateContInfoSeq(int contId);

	String selectContInfoSt(int contId);

	void updateContInfoSeqMax(int contId);

	ContListVO selectContListInfo(String clId);

	int insertContList(ContListVO contVO);

	int updateContListVO(ContListVO contVO);

	int updateContSeqUpF(int contId);

	int updateContSeqUpS(int contId);

	int updateContSeqDownF(int contId);

	int updateContSeqDownS(int contId);

}
