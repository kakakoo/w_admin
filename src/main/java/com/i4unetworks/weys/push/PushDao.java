package com.i4unetworks.weys.push;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface PushDao {

	List<Map<String, Object>> selectEventList();

	List<Map<String, Object>> selectNoticeList();

	List<Map<String, Object>> selectContList();

	List<Map<String, Object>> selectTestPush();

	List<String> selectTestPushAll(String string);

}
