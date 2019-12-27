package com.i4unetworks.weys.version;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.i4unetworks.weys.common.Paging;

@Service
public class VersionService {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private VersionDao versionDao;
	
	@Value("#{props['ENC.KEY']}")
	private String ENC_KEY;
	
	public VersionVO selectVersionInfo(String type) {
		return versionDao.selectVersionInfo(type);
	}

	public int updateVersion(VersionVO versionVO) {
		return versionDao.updateVersion(versionVO);
	}

	public List<AdmLogVO> selectAdmLog(Paging paging) {

		if (paging.getIsSearch() == 1)
			paging.setSearchPage(0);
		else
			paging.setSearchPage((paging.getPageNo() - 1) * 10);
		
		return versionDao.selectAdmLog(paging);
	}

	public int selectAdmLogCnt() {
		return versionDao.selectAdmLogCnt();
	}

	public List<MngVO> selectMngList() {

		Map<String, Object> reqMap = new HashMap<>();
		reqMap.put("encKey", ENC_KEY);
		return versionDao.selectMngList(reqMap);
	}

	public MngVO selectMngInfo(String mgId) {
		
		Map<String, Object> reqMap = new HashMap<>();
		reqMap.put("encKey", ENC_KEY);
		reqMap.put("mgId", mgId);
		
		return versionDao.selectMngInfo(reqMap);
	}

	public void insertManager(MngVO mngVO) {
		mngVO.setEncKey(ENC_KEY);
		versionDao.insertManager(mngVO);
	}

	public void updateManager(MngVO mngVO) {
		mngVO.setEncKey(ENC_KEY);
		versionDao.updateManager(mngVO);
	}

	public void deleteManager(String mgId) {
		versionDao.deleteManager(mgId);
	}

	public List<SmsVO> selectSmsList() {
		return versionDao.selectSmsList();
	}

	public SmsVO selectSmsInfo(String smsId) {
		return versionDao.selectSmsInfo(smsId);
	}

	public void insertSms(SmsVO smsVO) {
		versionDao.insertSms(smsVO);
	}

	public void updateSms(SmsVO smsVO) {
		versionDao.updateSms(smsVO);
	}

	public void deleteSms(String smsId) {
		versionDao.deleteSms(smsId);
	}
}
