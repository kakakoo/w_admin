package com.i4unetworks.weys.store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.i4unetworks.weys.common.AriaUtils;
import com.i4unetworks.weys.common.Paging;
import com.i4unetworks.weys.common.UserVO;
import com.i4unetworks.weys.common.Utils;

@Service
public class StoreService {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private StoreDao storeDao;

	@Value("#{props['ENC.KEY']}")
	private String ENC_KEY;
	
	public List<StoreListVO> getStoreList(Paging paging) {
		
		if (paging.getIsSearch() == 1)
			paging.setSearchPage(0);
		else
			paging.setSearchPage((paging.getPageNo() - 1) * 10);
		paging.setStartDt(Utils.getTodayDate());
		paging.setEndDt(Utils.getDiffDate(1));
		
		return storeDao.getStoreList(paging);
	}
	
	public int getStoreListCnt() {
		return storeDao.getStoreListCnt();
	}

	public int insertStore(StoreDetailVO storeDetailVO) {
		int res = storeDao.insertStore(storeDetailVO);
		
		if(res > 0){
			String opentime = storeDetailVO.getStoreOpentime();
			StringTokenizer st = new StringTokenizer(opentime, "~");
			String open = st.nextToken().trim();
			String close = st.nextToken().trim();
			open = get24Time(open);
			close = get24Time(close);

			Map<String, Object> reqMap = new HashMap<>();
			reqMap.put("storeId", storeDetailVO.getStoreId());
			reqMap.put("startTm", open);
			reqMap.put("endTm", close);
			for(int i=0 ; i<7 ; i++){
				reqMap.put("day", i);
				storeDao.insertRsvStore(reqMap);
			}
		}
		
		return res;
	}

	private String get24Time(String time) {
		
		if(time.contains("AM")){
			time = time.replace("AM", "");
		} else if (time.contains("PM")){
			time = time.replace("PM", "");
			StringTokenizer st = new StringTokenizer(time, ":");
			String hour = st.nextToken().trim();
			String min = st.nextToken().trim();
			int iHour = Integer.parseInt(hour) + 12;
			time = iHour + ":" + min;
		}
		
		return time;
	}

	public StoreDetailVO selectDetail(String storeId) {
		
		StoreDetailVO result = storeDao.selectDetail(storeId);

		List<String> imgList = new ArrayList<String>();
		if(result.getStoreImg() == null || result.getStoreImg().equals("")){
			result.setImgList(null);
		} else if(result.getStoreImg().contains(",")){
			StringTokenizer st = new StringTokenizer(result.getStoreImg(), ",");
			
			while(st.hasMoreTokens()){
				imgList.add(st.nextToken());
			}
			result.setImgList(imgList);
		} else {
			imgList.add(result.getStoreImg());
			result.setImgList(imgList);
		} 
		
		List<String> closeList = new ArrayList<>();
		if(result.getCloseDt() == null || result.getCloseDt().equals("")){
			result.setCloseList(null);
		} else if (result.getCloseDt().contains(",")){
			StringTokenizer st = new StringTokenizer(result.getCloseDt(), ",");
			
			while(st.hasMoreTokens()){
				closeList.add(st.nextToken());
			}
			result.setCloseList(closeList);
		} else {
			closeList.add(result.getCloseDt());
			result.setCloseList(closeList);
		} 
		
		return result;
	}

	public int updateStore(StoreDetailVO storeDetailVO) {
		
		int res = storeDao.updateStore(storeDetailVO);
		
		if(res > 0){
			Map<String, Object> updateMap = new HashMap<>();
			updateMap.put("storeId", storeDetailVO.getStoreId());
			for(int i=0 ; i<7 ; i++){
				updateMap.put("day", i);
				updateMap.put("startTm", storeDetailVO.getStartTm().get(i));
				updateMap.put("endTm", storeDetailVO.getEndTm().get(i));
				
				String st = storeDetailVO.getOpenSt().get(i);
				updateMap.put("openSt", st.equals("true") ? "Y" : "N");
				
				storeDao.updateRsvStore(updateMap);
			}
			if(storeDetailVO.getCloseDt() != null && !storeDetailVO.getCloseDt().equals("")){
				/**
				 * 기존 공휴일 데이터 정리
				 * 정리후 새로 등록
				 */
				storeDao.deleteRsvClose(storeDetailVO.getStoreId());
				StringTokenizer st = new StringTokenizer(storeDetailVO.getCloseDt(), ",");
				while(st.hasMoreTokens()){
					String closeDt = st.nextToken();
					if(closeDt.contains("~")){
						StringTokenizer dtSt = new StringTokenizer(closeDt, "~");
						String startDt = dtSt.nextToken().trim();
						String endDt = dtSt.nextToken().trim();
						
						int index = 0;
						boolean equalDt = false;
						while(!equalDt){
							String dt = Utils.getNextDt(startDt, index);
							updateMap.put("closeDt", dt);
							storeDao.insertRsvClose(updateMap);
							
							if(dt.equals(endDt))
								equalDt = true;
							index = index + 1;
						}
					} else {
						updateMap.put("closeDt", closeDt);
						storeDao.insertRsvClose(updateMap);
					}
				}
			}
		}
		
		return res;
	}

	public int deleteStore(String storeId) {
		return storeDao.deleteStore(storeId);
	}

	public int updateStatus(Map<String, Object> reqMap) {
		
		String st = MapUtils.getString(reqMap, "storeSt");
		if(st.equals("ON"))
			st = "N";
		else
			st = "Y";
		
		reqMap.put("storeSt", st);
		
		return storeDao.updateStatus(reqMap);
	}

	public List<StoreStaffVO> selectStoreStaff(Paging paging) {

		if (paging.getIsSearch() == 1)
			paging.setSearchPage(0);
		else
			paging.setSearchPage((paging.getPageNo() - 1) * 10);
		paging.setEncKey(ENC_KEY);
		return storeDao.selectStoreStaff(paging);
	}

	public int selectStoreStaffCnt() {
		return storeDao.selectStoreStaffCnt();
	}

	public List<Map<String, Object>> selectStoreList() {
		return storeDao.selectStoreList();
	}

	public int insertStaff(StoreStaffVO staffVO, UserVO userVO) throws Exception {
		staffVO.setEncKey(ENC_KEY);
		int res = storeDao.insertStaff(staffVO);
		if(res > 0){
			String inputpPw = AriaUtils.encryptPassword(staffVO.getAdminPw(), staffVO.getAdminId());
			staffVO.setAdminPw(inputpPw);
			res = storeDao.updateStaffPw(staffVO);
			
			Map<String, Object> reqMap = new HashMap<>();
			reqMap.put("adminKey", userVO.getAdminKey());
			reqMap.put("targetKey", staffVO.getAdminKey());
			storeDao.insertPwdLog(reqMap);
			
			List<Integer> storeList = new ArrayList<>();
			String storeId = staffVO.getStoreId();
			if(storeId == null){
				return res;
			}
			
			if(storeId.contains(",")){
				StringTokenizer st = new StringTokenizer(storeId, ",");
				while(st.hasMoreTokens()){
					storeList.add(Integer.parseInt(st.nextToken()));
				}
			} else {
				storeList.add(Integer.parseInt(storeId));
			}
			
			if(storeList.size() > 0){
				staffVO.setStoreList(storeList);
				storeDao.insertAdminStore(staffVO);
			}
		}
		
		return res;
		
	}

	public int deleteStaff(String adminKey) {
		storeDao.deleteStaffToken(adminKey);
		storeDao.deleteStaffStore(adminKey);
		return storeDao.deleteStaff(adminKey);
	}

	public int updateStaff(StoreStaffVO staffVO, UserVO userVO) throws Exception {
		staffVO.setEncKey(ENC_KEY);
		int res = 0;
		if(staffVO.getAdminPw() == null || staffVO.getAdminPw().equals("")){
			staffVO.setAdminPw(null);
			res = storeDao.updateStaffInfo(staffVO);
		} else {
			String inputpPw = AriaUtils.encryptPassword(staffVO.getAdminPw(), staffVO.getAdminId());
			staffVO.setAdminPw(inputpPw);
			res = storeDao.updateStaffInfo(staffVO);

			Map<String, Object> reqMap = new HashMap<>();
			reqMap.put("adminKey", userVO.getAdminKey());
			reqMap.put("targetKey", staffVO.getAdminKey());
			storeDao.insertPwdLog(reqMap);
		}
		
		if(res > 0){
			storeDao.deleteStaffStore(staffVO.getAdminKey());
			List<Integer> storeList = new ArrayList<>();
			String storeId = staffVO.getStoreId();
			if(storeId == null || storeId.equals("")){
				return res;
			}
			
			if(storeId.contains(",")){
				StringTokenizer st = new StringTokenizer(storeId, ",");
				while(st.hasMoreTokens()){
					storeList.add(Integer.parseInt(st.nextToken()));
				}
			} else {
				storeList.add(Integer.parseInt(storeId));
			}
			staffVO.setStoreList(storeList);
			storeDao.insertAdminStore(staffVO);
		}
		return res;
	}

	public List<Map<String, Object>> selectStoreCoin(String storeId) {

		List<Map<String, Object>> list = storeDao.selectStoreCoin(storeId);
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();;

		String unitNm = "";
		String unitCd = "";
		double unitTot = 0.0;
		Map<String, Object> coinMap = null;
		List<Map<String, Object>> coinList = new ArrayList<Map<String, Object>>();
		
		/*
		 * unitNm : 홍콩 달러
		 * unitCd : HKD
		 * unitTot : 0.00
		 * coinList : 
		 * 		COIN_NM : 500 홍콩달러
		 * 		COIN_CNT : 0
		 */
		for(Map<String, Object> temp : list){
			String nm = MapUtils.getString(temp, "UNIT_NM");
			if(unitNm.equals(nm)){
				unitTot = unitTot + MapUtils.getDoubleValue(temp, "COIN_VAL");
				coinList.add(temp);
			} else {
				if(coinMap != null){
					coinMap.put("unitTot", unitTot);
					coinMap.put("coinList", coinList);
					resultList.add(coinMap);
				}
				coinMap = new HashMap<String, Object>();
				coinList = new ArrayList<Map<String, Object>>();
				
				unitNm = MapUtils.getString(temp, "UNIT_NM");
				unitCd = MapUtils.getString(temp, "UNIT_CD");
				unitTot = MapUtils.getDoubleValue(temp, "COIN_VAL");
				coinMap.put("unitNm", unitNm);
				coinMap.put("unitCd", unitCd);
				coinList.add(temp);
			}
		}
		coinMap.put("unitTot", unitTot);
		coinMap.put("coinList", coinList);
		resultList.add(coinMap);
		
		return resultList;
	}

	public String selectStoreNm(String storeId) {
		return storeDao.selectStoreNm(storeId);
	}

	public List<StoreChangeVO> selectStoreMember(Paging paging) {

		if (paging.getIsSearch() == 1)
			paging.setSearchPage(0);
		else
			paging.setSearchPage((paging.getPageNo() - 1) * 10);
		
		if(paging.getReservation() == null){
			paging.setStartDt(Utils.getDiffDate(-14));
			paging.setEndDt(Utils.getTodayDate());
		} else {
			StringTokenizer st = new StringTokenizer(paging.getReservation(), "-");
			paging.setStartDt(st.nextToken().trim());
			paging.setEndDt(st.nextToken().trim());
		}
		
		return storeDao.selectStoreMember(paging);
	}

	public int selectStoreMemberCnt(Paging paging) {
		return storeDao.selectStoreMemberCnt(paging);
	}

	public List<StoreChangeVO> selectStorePoint(Paging paging) {
		
		if (paging.getIsSearch() == 1)
			paging.setSearchPage(0);
		else
			paging.setSearchPage((paging.getPageNo() - 1) * 10);
		
		if(paging.getReservation() == null){
			paging.setStartDt(Utils.getDiffDate(-14));
			paging.setEndDt(Utils.getTodayDate());
		} else {
			StringTokenizer st = new StringTokenizer(paging.getReservation(), "-");
			paging.setStartDt(st.nextToken().trim());
			paging.setEndDt(st.nextToken().trim());
		}
		
		return storeDao.selectStorePoint(paging);
	}

	public int selectStorePointCnt(Paging paging) {
		return storeDao.selectStorePointCnt(paging);
	}

	public List<Map<String, Object>> selectOpenDay(String storeId) {
		
		List<Map<String, Object>> list = storeDao.selectOpenDay(storeId);
		
		for(Map<String, Object> temp : list){
			int day = MapUtils.getIntValue(temp, "DAY");
			temp.put("dayString", Utils.getDayString(day));
		}
		
		return list;
	}

	public List<StoreListVO> selectStoreAllList() {
		return storeDao.selectStoreAllList();
	}

	public StoreStaffVO selectAdminInfo(String adminKey) {
		
		Map<String, Object> reqMap = new HashMap<>();
		reqMap.put("adminKey", adminKey);
		reqMap.put("encKey", ENC_KEY);
		
		StoreStaffVO info = storeDao.selectAdminInfo(reqMap);
		List<Integer> storeIds = storeDao.selectAdminStore(adminKey);
		info.setStoreList(storeIds);
		return info;
	}
}
