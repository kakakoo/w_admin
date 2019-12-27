package com.i4unetworks.weys.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PushService implements Runnable {
	
	private JSONObject json;
	private String FCM_SERVER_KEY;
	private String FCM_API_SEND_URL;
	private String result;
	
	public static void main(String[] args) throws JSONException {
		JSONObject pushObj = new JSONObject();
		pushObj.put("to", "fBEgztz9yoE:APA91bGH_dmdTvTlryRYo98JkGair8k0UknBg0xU1T5QYgMUkG45TsNlafGhKuwWqrcukLIOO6jfQUBOr0xQ0M8H_dE9obNdjSmzM8g4hQosGZE7NSG8NzndCnUctMszTdYGLmy-mnhm");
		JSONObject dataJson = new JSONObject();
		dataJson.put("title", "test");
		dataJson.put("contents","cont");
		dataJson.put("img", "");
		dataJson.put("val", "");
		
		JSONObject notiJson = new JSONObject();
		notiJson.put("title", "title");
		notiJson.put("body", "msg");
		notiJson.put("icon", "");
		notiJson.put("sound", "activated");
		notiJson.put("badge", "1");
		
		pushObj.put("content_available", true);
		pushObj.put("data", dataJson);
		pushObj.put("notification", notiJson);
		pushObj.put("priority", "high");
		
		PushService push = new PushService(pushObj, "AIzaSyCLJ77U0BLEzyR1HFjSJOtEodKAV4uoabc", "https://fcm.googleapis.com/fcm/send");
		Thread t = new Thread(push);
		t.start();
	}

	protected static Logger logger = LoggerFactory.getLogger(PushService.class);
	
	public PushService(JSONObject json, String FCM_SERVER_KEY, String FCM_API_SEND_URL) {
		this.json = json;
		this.FCM_SERVER_KEY = FCM_SERVER_KEY;
		this.FCM_API_SEND_URL = FCM_API_SEND_URL;
		this.result = "";
	}
	
	public String getResult(){
		return this.result;
	}

	@Override
	public void run() {
		
		try{
			String authKey = FCM_SERVER_KEY; // You FCM AUTH key
			String FMCurl = FCM_API_SEND_URL;
			
			URL url = new URL(FMCurl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setUseCaches(false);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "key=" + authKey);
			conn.setRequestProperty("Content-Type", "application/json");

			try (OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream())) {
				// 혹시나 한글 깨짐이 발생하면
				// try(OutputStreamWriter wr = new
				// OutputStreamWriter(conn.getOutputStream(), "UTF-8")){ 인코딩을 변경해준다.

				wr.write(json.toString());
				wr.flush();
			} catch (Exception e) {
			}

			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			while ((output = br.readLine()) != null) {
				this.result = output;
				logger.info("push send ::: " + result);
			}

			conn.disconnect();
		} catch (Exception e) {
			logger.info("push error ::: " + e.getMessage());
		}
	}
	
	/**
	 * fcm 단건 발송
	 * 
	 * @param url
	 * @param json
	 * @return
	 * @throws Exception
	 */

	// fcm 포맷
	// fomatJson.put("to", fcm_token);
	//
	// dataJson.put("title", reqSendPushUser.getTitle());
	// dataJson.put("contents", reqSendPushUser.getContents());
	// dataJson.put("etc", reqSendPushUser.getEtc());
	// //fcm포맷 에 data 셋팅
	// fomatJson.put("data", dataJson);

	public static String fcmPush(JSONObject json, String FCM_SERVER_KEY, String FCM_API_SEND_URL) throws Exception {

		// HttpHeaders headers = new HttpHeaders();
		// //headers.setContentType(MediaType.APPLICATION_JSON);
		// headers.set("Authorization", "key=" + FCM_SERVER_KEY);
		// //Charset utf8 = Charset.forName("UTF-8");
		// Charset utf8 = Charset.forName("EUC-KR");
		// MediaType mediaType = new MediaType("application", "json", utf8);
		// headers.setContentType(mediaType);
		//
		// HttpEntity<?> httpEntity = new HttpEntity<Object>(json.toString(),
		// headers);
		//
		//
		// RestTemplate rest = new RestTemplate();
		// ResponseEntity<String> model = rest.exchange(FCM_API_SEND_URL,
		// HttpMethod.POST, httpEntity, String.class);
		//
		// model.getBody();

		String authKey = FCM_SERVER_KEY; // You FCM AUTH key
		String FMCurl = FCM_API_SEND_URL;

		URL url = new URL(FMCurl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setUseCaches(false);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Authorization", "key=" + authKey);
		conn.setRequestProperty("Content-Type", "application/json");

		try (OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream())) {
			// 혹시나 한글 깨짐이 발생하면
			// try(OutputStreamWriter wr = new
			// OutputStreamWriter(conn.getOutputStream(), "UTF-8")){ 인코딩을 변경해준다.

			wr.write(json.toString());
			wr.flush();
		} catch (Exception e) {
		}

		if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
			throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

		String output;
		String result = "";
		while ((output = br.readLine()) != null) {
			result = output;
		}

		conn.disconnect();

		return result;
	}

	/**
	 * apns 단건 발송
	 * 
	 * @param url
	 * @param json
	 * @return
	 * @throws Exception
	 */

	// fomatJson.put("apns_token", apns_token);
	// fomatJson.put("title", map.get("title"));
	// fomatJson.put("contents", map.get("contents"));
	// fomatJson.put("etc", map.get("etc"));

	public static void apnsPush(JSONObject json) throws Exception {

		// try {
		// // Setup up a simple message
		// PushNotificationPayload payload = new PushNotificationPayload();
		// payload.addAlert(String.valueOf(json.get("title")));//메시지
		// payload.addBadge(1);
		// payload.addSound("default");//필수 : default
		// payload.addCustomDictionary("title",
		// String.valueOf(json.get("title")));//사용자 정의
		// payload.addCustomDictionary("contents",
		// String.valueOf(json.get("contents")));//사용자 정의
		// payload.addCustomDictionary("etc",
		// String.valueOf(json.get("etc")));//사용자 정의
		//
		// // Push.payload(Payload payload, Object keystore, String password,
		// // boolean production, Object devices)
		// // production (true/false)
		// // true : Distribute (gateway.push.apple.com)
		// // false : Developer (gateway.sandbox.push.apple.com)
		//
		// boolean real_yn = "Y".equals(APNS_REAL)?true:false;
		// String keystore =
		// "Y".equals(APNS_REAL)?APNS_KEYSTORE:APNS_KEYSTORE_DEV;
		// String password =
		// "Y".equals(APNS_REAL)?APNS_PASSWORD:APNS_PASSWORD_DEV;
		//
		//
		// System.out.println("real_yn:::" + real_yn);
		// System.out.println("keystore:::" + keystore);
		// System.out.println("password:::" + password);
		//
		// Push.payload(payload, keystore, password, real_yn,
		// json.get("apns_token"));
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}

}
