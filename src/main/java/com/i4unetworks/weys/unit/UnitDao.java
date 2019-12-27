package com.i4unetworks.weys.unit;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface UnitDao {

	List<UnitListVO> getStoreUnitList();

	int updateStoreUnit(UnitListVO reqVO);

	List<Map<String, Object>> selectRsvStore();

	List<Map<String, Object>> selectRsvStoreUnit(int storeId);

	int insertUpdateRsvStore(Map<String, Object> unitMap);

	int updateRsvUnitInit(int storeId);

}
