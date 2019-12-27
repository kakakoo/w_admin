package com.i4unetworks.weys.unit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnitService {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private UnitDao unitDao;
	
	public List<UnitListVO> getStoreUnitList() {
		return unitDao.getStoreUnitList();
	}

	public int updateStoreUnit(UnitListVO reqVO) throws Exception {
		
		return unitDao.updateStoreUnit(reqVO);
	}

	public List<Map<String, Object>> selectRsvStore() {
		
		List<Map<String, Object>> resultList = unitDao.selectRsvStore();
		
		for(Map<String, Object> store : resultList){
			int storeId = MapUtils.getIntValue(store, "STORE_ID");
			
			List<Map<String, Object>> rsvUnitList = unitDao.selectRsvStoreUnit(storeId);
			
			store.put("unit", rsvUnitList);
		}
		
		return resultList;
	}

	public int updateStoreRsvUnit(Map<String, Object> reqMap) {
		
		int storeId = MapUtils.getIntValue(reqMap, "storeId"); 
		String unitIds = MapUtils.getString(reqMap, "unitId", "");
		
		int res = unitDao.updateRsvUnitInit(storeId);
		
		Map<String, Object> unitMap = new HashMap<>();
		unitMap.put("storeId", storeId);
		if(!unitIds.equals("")){
			if(unitIds.contains(",")){
				StringTokenizer st = new StringTokenizer(unitIds, ",");
				while(st.hasMoreTokens()){
					unitMap.put("unitId", st.nextToken());
					res = unitDao.insertUpdateRsvStore(unitMap);
				}
			} else {
				unitMap.put("unitId", unitIds);
				res = unitDao.insertUpdateRsvStore(unitMap);
			}
		}
		
		return res;
	}
}
