package com.i4unetworks.weys.common;

public class ExcelConstants {

	/** List Type */
	public static final String USER = "USER";
	public static final String USER_DETAIL = "USER_DETAIL";
	public static final String TRADE = "TRADE";
	public static final String STORE_DETAIL = "STORE_DETAIL";

	public static final String MANAGE_TRADE = "MANAGE_TRADE";
	public static final String MANAGE_SETTING = "MANAGE_SETTING";

	public static final String RSV_LIST = "RSV_LIST";
	public static final String RSV_DATA_LIST = "RSV_DATA_LIST";
	public static final String RSV_CANCEL_LIST = "RSV_CANCEL_LIST";
	
	/*
	 * 엑셀 헤더 이름 
	 */
	/** Table Header */
	public static final String[] TABLE_HEADER_USER = {"아이디", "가입일", "이름", "이메일", "전화번호", "예약중", "예약완료", "쿠폰수"};
	public static final String[] TABLE_HEADER_TRADE = {"등록일", "거래구분", "통화", "금액", "등록내용", "지역", "등록자 이메일", "닉네임", "거래상태"};

	public static final String[] TABLE_HEADER_USER_DETAIL_INFO = {"닉네임", "메일", "이름", "멤버십가입일", "잔여한도", "만료기간", "포인트"};
	public static final String[] TABLE_HEADER_USER_DETAIL_MEMBER = {"거래일", "구분", "통화", "수령금액", "매매기준율", "지급금액", "신청서", "지점", "담당자"};
	public static final String[] TABLE_HEADER_USER_DETAIL_CPOINT = {"거래일", "통화", "수령금액", "매매기준율", "지급포인트", "신청서", "지점", "담당자"};
	public static final String[] TABLE_HEADER_USER_DETAIL_UPOINT = {"거래일", "상품명", "사용포인트", "거래코드"};

	public static final String[] TABLE_HEADER_STORE_DETAIL_MEMBER = {"거래일", "거래상세", "통화", "수령금액", "매매기준율", "지급금액", "닉네임", "담당자", "신청서"};
	public static final String[] TABLE_HEADER_STORE_DETAIL_POINT = {"거래일", "거래상세", "통화", "수령금액", "매매기준율", "지급포인트", "닉네임", "담당자", "신청서"};

	public static final String[] TABLE_HEADER_MANAGE_TRADE = {"날짜", "No", "거래구분", "통화", "받은 금액", "환율", "건낸 금액", "거래자", "신청서"};
	public static final String[] TABLE_HEADER_MANAGE_SETTING = {"화폐", "통화명", "금고 수량", "금고 금액", "환전소 금액", "예약관리 금액", "인천지점 금액", "매매기준율", "한화"};

	public static final String[] TABLE_HEADER_RSV_LIST = {"구분", "거래코드", "예약번호", "거래일시", "구매통화", "외화금액", "매매기준율", "원화금액", "수수료"
			, "외화배송료", "지불금액", "권종선택", "고객성명", "휴대전화", "수령장KR", "수령장소ENG", "수령예정일시"};

	public static final String[] TABLE_HEADER_RSV_DATA_LIST = {"코드", "지점", "등록일", "예약일", "통화", "예약외화", "한도외화", "한도환율", "일반외화", "일반환율",
			"수수료", "배송비", "입금금액", "수령인", "입금은행", "담당자", "예약상태"};
	public static final String[] TABLE_HEADER_RSV_CANCEL_LIST = {"취소일", "예금주", "은행", "계좌", "환불금액"};
}
