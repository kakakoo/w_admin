package com.i4unetworks.weys.paygate;

import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface PaygateDao {

	void insertPaygateLog(Map<String, Object> logMap);

	int selectRsvCheck(Map<String, Object> reqMap);

	int selectRsvId(Map<String, Object> reqMap);

}
