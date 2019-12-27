package com.i4unetworks.weys.cash;

import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.i4unetworks.weys.common.Paging;
import com.i4unetworks.weys.common.Utils;
import com.popbill.api.CashbillService;
import com.popbill.api.PopbillException;
import com.popbill.api.Response;
import com.popbill.api.cashbill.Cashbill;

@Service
public class CashService {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private CashDao cashDao;
	@Autowired
	private CashbillService cashbillService;

	@Value("#{props['ENC.KEY']}")
	private String ENC_KEY;

	private final String OFFICE_NUM = "1358800906";
	private final String OFFICE_NM = "그레잇";
	private final String OFFICE_ADDR = "서울특별시 강남구 언주로 147길 36 설봉빌딩";
	private final String OFFICE_TEL = "1670-2160";
	private final String OFFICE_CEO = "조은용";

	public List<CashVO> selectCashList(Paging paging) {

		if (paging.getIsSearch() == 1)
			paging.setSearchPage(0);
		else
			paging.setSearchPage((paging.getPageNo() - 1) * 10);

		if (paging.getReservation() == null) {
			paging.setStartDt(Utils.getDiffDate(-14));
			paging.setEndDt(Utils.getTodayDate());
		} else {
			StringTokenizer st = new StringTokenizer(paging.getReservation(), "-");
			paging.setStartDt(st.nextToken().trim());
			paging.setEndDt(st.nextToken().trim());
		}

		paging.setEncKey(ENC_KEY);
		return cashDao.selectCashList(paging);
	}

	public int selectCashListCnt(Paging paging) {
		return cashDao.selectCashListCnt(paging);
	}

	public String updateCashRegisterIssue(Map<String, Object> reqMap) {

		reqMap.put("encKey", ENC_KEY);
		CashVO info = cashDao.selectCashInfo(reqMap);
		info.setMgtKey(MapUtils.getString(reqMap, "mgtKey"));
		info.setOrderNumber(MapUtils.getString(reqMap, "orderNumber"));

		String msg = registIssue(info);
		if(msg.equals("success")){
			cashDao.updateCashInfo(reqMap);
		}
		return msg;
	}
	
	private boolean register(CashVO info){

		/**
		 * 1건의 현금영수증을 수정합니다. - [임시저장] 상태의 현금영수증만 수정할 수 있습니다. - 국세청에 신고된 현금영수증은
		 * 수정할 수 없으며, 취소 현금영수증을 발행하여 취소처리 할 수 있습니다. - 취소현금영수증 작성방법 안내 -
		 * http://blog.linkhub.co.kr/702
		 */

		// 수정할 현금영수증 문서관리번호
		String mgtKey = info.getMgtKey();

		// 현금영수증 정보 객체
		Cashbill cashbill = new Cashbill();

		// 문서관리번호, 최대 24자리, 영문, 숫자 '-', '_'로 구성
		cashbill.setMgtKey(mgtKey);

		// 현금영수증 형태, {승인거래, 취소거래} 중 기재
		cashbill.setTradeType("승인거래");

		// 취소거래시 기재, 원본현금영수증 국세청 승인번호 - getInfo API를 통해 confirmNum 값 기재
		// cashbill.setOrgConfirmNum("");

		// 과세형태, {과세, 비과세} 중 기재
		cashbill.setTaxationType("과세");

		// 거래처 식별번호, 거래유형에 따라 작성
		// 소득공제용 - 주민등록/휴대폰/카드번호 기재가능
		// 지출증빙용 - 사업자번호/주민등록/휴대폰/카드번호 기재가능
		cashbill.setIdentityNum(info.getUsrContact());

		// 거래유형, {소득공제용, 지출증빙용} 중 기재
		cashbill.setTradeUsage(info.getCashTp().equals("U") ? "소득공제용" : "지출증빙용");

		int val = info.getAmnt();
		
		int tax = 0;
		int amnt = 0;
		
		tax = (int) (val / 11);
		amnt = val - tax;
		
		// 공급가액, 숫자만 가능
		cashbill.setSupplyCost(amnt + "");

		// 세액, 숫자만 가능
		cashbill.setTax(tax + "");

		// 봉사료, 숫자만 가능
		cashbill.setServiceFee("0");

		// 합계금액, 숫자만 가능, 봉사료 + 공급가액 + 세액
		cashbill.setTotalAmount(info.getAmnt() + "");

		// 발행자 사업자번호, '-'제외 10자리
		cashbill.setFranchiseCorpNum(OFFICE_NUM);

		// 발행자 상호
		cashbill.setFranchiseCorpName(OFFICE_NM);

		// 발행자 대표자명
		cashbill.setFranchiseCEOName(OFFICE_CEO);

		// 발행자 주소
		cashbill.setFranchiseAddr(OFFICE_ADDR);

		// 발행자 연락처
		cashbill.setFranchiseTEL(OFFICE_TEL);

		// 발행안내 문자 전송여부
		cashbill.setSmssendYN(false);

		// 고객명
		cashbill.setCustomerName(info.getUsrNm());

		// 상품명
		cashbill.setItemName(info.getItemName());

		// 주문번호
		cashbill.setOrderNumber(info.getOrderNumber());

		// 고객 메일주소
		cashbill.setEmail(info.getUsrEmail());

		// 고객 휴대폰 번호
		cashbill.setHp(info.getUsrContact());
		
		try {
			Response response = cashbillService.update(OFFICE_NUM, mgtKey, cashbill);

			logger.info("response ::: " + response.toString());
		} catch (PopbillException e) {
			logger.info("PopbillException ::: " + e.getMessage());
			return false;
		}

		return true;
	}
	
	private boolean issue(CashVO info){
		/**
		 * 발행된 현금영수증을 국세청에 전송한다.
		 */
		
		// 현금영수증 문서관리번호
		String mgtKey = info.getMgtKey();
		
		// 메모
		String memo = info.getMemo();
		
		try {
			Response response = cashbillService.issue(OFFICE_NUM, mgtKey, memo);
			
			logger.info("response ::: " + response.toString());
		} catch (PopbillException e) {
			logger.info("PopbillException ::: " + e.getMessage());
			return false;
		}

		return true;
	}
	
	private String registIssue(CashVO info){

		/**
		 * 1건의 현금영수증을 즉시발행합니다.
		 * - 발행일 기준 오후 5시 이전에 발행된 현금영수증은 다음날 오후 2시에 국세청
		 *   전송결과를 확인할 수 있습니다.
		 * - 현금영수증 국세청 전송 정책에 대한 정보는 "[현금영수증 API 연동매뉴얼]
		 *   > 1.4. 국세청 전송정책"을 참조하시기 바랍니다.
		 * - 취소현금영수증 작성방법 안내 - http://blog.linkhub.co.kr/702
		 */
		
		
		// 메모
		String Memo = info.getMemo();

		// 현금영수증 정보 객체
		Cashbill cashbill = new Cashbill();
	
		// 문서관리번호, 최대 24자리, 영문, 숫자 '-', '_'로 구성
		cashbill.setMgtKey(info.getMgtKey());			
		
		// 현금영수증 형태, {승인거래, 취소거래} 중 기재
		cashbill.setTradeType("승인거래");				
		
		// 취소거래시 기재, 원본 현금영수증 국세청 승인번호 - getInfo API를 통해 confirmNum 값 기재
		cashbill.setOrgConfirmNum("");  			

		// 취소거래시 기재, 원본 현금영수증 거래일자 - getInfo API를 통해 tradeDate 값 기재
		cashbill.setOrgTradeDate("");
		
		
		// 과세형태, {과세, 비과세} 중 기재
		cashbill.setTaxationType("과세");				
		
		// 거래처 식별번호, 거래유형에 따라 작성
	    // 소득공제용 - 주민등록/휴대폰/카드번호 기재가능
	    // 지출증빙용 - 사업자번호/주민등록/휴대폰/카드번호 기재가능
		cashbill.setIdentityNum(info.getUsrContact());
		
		// 거래유형, {소득공제용, 지출증빙용} 중 기재
		cashbill.setTradeUsage(info.getCashTp().equals("U") ? "소득공제용" : "지출증빙용");

		int val = info.getAmnt();
		
		int tax = 0;
		int amnt = 0;
		
		tax = (int) (val / 11);
		amnt = val - tax;
		
		// 공급가액, 숫자만 가능
		cashbill.setSupplyCost(amnt + "");
		
		// 세액, 숫자만 가능
		cashbill.setTax(tax + "");						
		
		// 봉사료, 숫자만 가능
		cashbill.setServiceFee("0");					
		
		// 합계금액, 숫자만 가능, 봉사료 + 공급가액 + 세액
		cashbill.setTotalAmount(info.getAmnt() + "");				

		
		// 발행자 사업자번호, '-'제외 10자리
		cashbill.setFranchiseCorpNum(OFFICE_NUM);
		
		// 발행자 상호
		cashbill.setFranchiseCorpName(OFFICE_NM);	
		
		// 발행자 대표자명
		cashbill.setFranchiseCEOName(OFFICE_CEO);
		
		// 발행자 주소
		cashbill.setFranchiseAddr(OFFICE_ADDR);
		
		// 발행자 연락처
		cashbill.setFranchiseTEL(OFFICE_TEL);	
		
		// 발행안내 문자 전송여부
		cashbill.setSmssendYN(false);					
	
		
		// 고객명
		cashbill.setCustomerName(info.getUsrNm());
		
		// 상품명
		cashbill.setItemName(info.getItemName());
		
		// 주문번호
		cashbill.setOrderNumber(info.getOrderNumber());
		
		// 고객 메일주소
		cashbill.setEmail(info.getUsrEmail());
		
		// 고객 휴대폰 번호
		cashbill.setHp(info.getUsrContact());
			
		try {
			Response response = cashbillService.registIssue(OFFICE_NUM, cashbill, Memo);
			if(response.getCode() == 1){
				return "success";
			} else {
				logger.info("Response ::: " + response.getCode());
				logger.info("Response ::: " + response.getMessage());
				return response.getMessage();
			}
		} catch (PopbillException e) {
			logger.info("PopbillException ::: " + e.getMessage());
			return e.getMessage();
		}
	}
}
