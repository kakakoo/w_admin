package com.i4unetworks.weys.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.i4unetworks.weys.common.Paging;
import com.i4unetworks.weys.common.Utils;

@Service
public class MemberService {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private MemberDao memberDao;
	
	@Value("${SERVER.TYPE}")
	private String SERVER_TYPE;
	
	public List<MemberVO> selectPayList(Paging paging) {

		if (paging.getIsSearch() == 1)
			paging.setSearchPage(0);
		else
			paging.setSearchPage((paging.getPageNo() - 1) * 10);
		
		return memberDao.selectPayList(paging);
	}
	
	public int selectPayListCnt() {
		return memberDao.selectPayListCnt();
	}
	
	public MemberVO selectPayInfo(String payId) {
		return memberDao.selectPayInfo(payId);
	}

	public int insertPayInfo(Map<String, Object> reqMap) {
		
		String action = MapUtils.getString(reqMap, "action");
		int res = 0;
		if(action.equals("write")){
			// payinfo 입력
			res = memberDao.insertPayInfo(reqMap);
		} else if(action.equals("update")){
			// payinfo 업데이트
			res = memberDao.updatePayInfo(reqMap);
		}
		
		return res;
	}

	public int deletePayInfo(Map<String, Object> reqMap) {
		
		String payId = MapUtils.getString(reqMap, "payId");
		return memberDao.deletePayInfo(payId);
	}

	public void insertRsvTest() {
		
		if(SERVER_TYPE.equals("USER")){
			return;
		}

		String [] unitArr = new String []{"USD", "JPY", "CNY", "EUR", "TWD", "THB", "HKD", "SGD", "PHP", "AUD"};
		double [] rateArr = new double []{1139, 1025.43, 169.53, 1284.91, 36.92, 35.85, 145.23, 841.86, 21.98, 816.15};
		String [] nmArr = new String []{"웨이즈", "그레잇", "조일본", "권태국", "정홍콩", "이달러", "박대만", "최호주", "김필립"};

		String [] horArr = new String []{"05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};
		String [] timeArr = new String []{"00", "15", "30", "45"};

		String [] paperArr = new String []{"B", "S", "R"};
		
		for(int s=13 ; s<14 ; s++){
			for(int i=0 ; i<20 ; i++){
				
				Map<String, Object> testMap = new HashMap<>();
				
				/**
				 * RSV_NO 생성
				 */
				String rsvNo = "";
				while(true){
					rsvNo = Utils.makeCode(6);
					
					int cnt = memberDao.selectCheckRsvNo(rsvNo);
					if(cnt == 0)
						break;
				}
				
				int unitIndex = (int) (Math.random() * 10);
				testMap.put("key", "GREiT");
				testMap.put("rsvNo", rsvNo);
				testMap.put("storeId", s);
				testMap.put("usrNm", nmArr[(int) (Math.random() * 9)]);
				testMap.put("usrNmId", (int) (Math.random() * 1000000) + "-" + (int) (Math.random() * 10000000));
				testMap.put("usrTel", "01000011000");
				testMap.put("usrEmail", "test@test.com");
				testMap.put("rsvDt", "2019.09.30");
				
				if(s == 13)
					testMap.put("rsvTm", (int) (Math.random() * 24) + ":" + timeArr[(int) (Math.random() * 4)]);
				else
					testMap.put("rsvTm", horArr[(int) (Math.random() * 16)] + ":" + timeArr[(int) (Math.random() * 4)]);
				testMap.put("unitCd", unitArr[unitIndex]);
				testMap.put("basicRate", rateArr[unitIndex]);
				testMap.put("rateDttm", "2019.09.29 14:37:00");

				testMap.put("rsvQr", (int) (Math.random() * 10000) + " " + (int) (Math.random() * 10000));
				
				testMap.put("rsvAmnt", (int) (Math.random() * 1000000) / 1000 * 1000);
				testMap.put("getAmnt", (int) (Math.random() * 10000000) / 10 * 10);
				testMap.put("cms", "0");

				testMap.put("rsvTp", "N");
				testMap.put("rsvSt", "R");
				testMap.put("rsvForm", "R");
				testMap.put("adminSt", "Y");
				testMap.put("rsvPaper", paperArr[(int) (Math.random() * 3)]);

				memberDao.insertRsv(testMap);
			}
		}
		
		
		
	}
}
