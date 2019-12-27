package com.i4unetworks.weys.cont;

import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i4unetworks.weys.common.Paging;

@Service
public class ContService {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private ContDao contDao;
	
	public List<ContVO> getContList(Paging paging) {

		if (paging.getIsSearch() == 1)
			paging.setSearchPage(0);
		else
			paging.setSearchPage((paging.getPageNo() - 1) * 10);
		
		return contDao.getContList(paging);
	}
	
	public int getContListCnt(Paging paging) {
		return contDao.getContListCnt(paging);
	}

	public ContVO selectContInfo(String contId) {
		return contDao.selectContInfo(contId);
	}

	public List<ContListVO> selectContInfoList(String contId) {
		return contDao.selectContInfoList(contId);
	}

	public int insertContInfo(ContVO contVO) {
		
		StringTokenizer st = new StringTokenizer(contVO.getReservation(), "-");
		contVO.setStartDt(st.nextToken().trim());
		contVO.setEndDt(st.nextToken().trim());
		
		int res = contDao.insertContInfo(contVO);
		
		if(res == 0)
			return 0;
		else 
			return contVO.getContId();
	}

	public int updateContList(Map<String, Object> reqMap) {
		
		String tp = MapUtils.getString(reqMap, "tp");
		int clId = MapUtils.getIntValue(reqMap, "clId");
		int res = 0;
		if(tp.equals("D")){
			/**
			 * 해당 컨텐츠 삭제
			 * 1. 해당 컨텐츠 후순위 아이들 seq 1씩 감소
			 * 2. 해당 컨텐츠 삭제
			 */
			res = contDao.updateDeleteSeq(clId);
			res = contDao.deleteContList(clId);
		} else if(tp.equals("P")){
			/**
			 * 해당 컨텐츠 순위 상승
			 * 해당 컨텐츠보다 순위 높은컨텐츠 순위 +1
			 * 해당 컨텐츠 순위 -1 
			 */
			res = contDao.updateClSeqUpF(clId);
			if(res > 0){
				res = contDao.updateClSeqUpS(clId);
			}
		} else if(tp.equals("W")){
			/**
			 * 해당 컨텐츠 순위 하강
			 * 해당 컨텐츠보다 순위 높은컨텐츠 순위 -1
			 * 해당 컨텐츠 순위 +1
			 */
			res = contDao.updateClSeqDownF(clId);
			if(res > 0){
				res = contDao.updateClSeqDownS(clId);
			}
		}
		
		return res;
	}

	public int updateContInfo(ContVO contVO) {
		
		StringTokenizer st = new StringTokenizer(contVO.getReservation(), "-");
		contVO.setStartDt(st.nextToken().trim());
		contVO.setEndDt(st.nextToken().trim());
		
		String oldSt = contDao.selectContInfoSt(contVO.getContId());
		
		if(oldSt.equals("Y") && contVO.getContSt().equals("N")){
			contDao.updateContInfoSeq(contVO.getContId());
		}
		
		if(oldSt.equals("N") && contVO.getContSt().equals("Y")){
			contDao.updateContInfoSeqMax(contVO.getContId());
		}
		
		return contDao.updateContInfo(contVO);
	}

	public ContListVO selectContListInfo(String clId) {
		return contDao.selectContListInfo(clId);
	}

	public int insertContList(ContListVO contVO) {
		StringTokenizer st = new StringTokenizer(contVO.getReservation(), "-");
		contVO.setStartDt(st.nextToken().trim());
		contVO.setEndDt(st.nextToken().trim());
		return contDao.insertContList(contVO);
	}

	public int updateContListVO(ContListVO contVO) {
		StringTokenizer st = new StringTokenizer(contVO.getReservation(), "-");
		contVO.setStartDt(st.nextToken().trim());
		contVO.setEndDt(st.nextToken().trim());
		return contDao.updateContListVO(contVO);
	}

	public int updateContSeq(Map<String, Object> reqMap) {
		String tp = MapUtils.getString(reqMap, "tp");
		int contId = MapUtils.getIntValue(reqMap, "contId");
		int res = 0;
		if(tp.equals("P")){
			/**
			 * 해당 컨텐츠 순위 상승
			 * 해당 컨텐츠보다 순위 높은컨텐츠 순위 +1
			 * 해당 컨텐츠 순위 -1 
			 */
			res = contDao.updateContSeqUpF(contId);
			if(res > 0){
				res = contDao.updateContSeqUpS(contId);
			}
		} else if(tp.equals("W")){
			/**
			 * 해당 컨텐츠 순위 하강
			 * 해당 컨텐츠보다 순위 높은컨텐츠 순위 -1
			 * 해당 컨텐츠 순위 +1
			 */
			res = contDao.updateContSeqDownF(contId);
			if(res > 0){
				res = contDao.updateContSeqDownS(contId);
			}
		}
		
		return res;
	}
}
