package com.i4unetworks.weys.version;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.i4unetworks.weys.common.Paging;

@Repository
public interface VersionDao {

	VersionVO selectVersionInfo(String type);

	int updateVersion(VersionVO versionVO);

	List<AdmLogVO> selectAdmLog(Paging paging);

	int selectAdmLogCnt();

	List<MngVO> selectMngList(Map<String, Object> reqMap);

	MngVO selectMngInfo(Map<String, Object> reqMap);

	void insertManager(MngVO mngVO);

	void updateManager(MngVO mngVO);

	void deleteManager(String mgId);

	List<SmsVO> selectSmsList();

	SmsVO selectSmsInfo(String smsId);

	void insertSms(SmsVO smsVO);

	void updateSms(SmsVO smsVO);

	void deleteSms(String smsId);

}
