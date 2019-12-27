<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<jsp:include page="../common/commonjs.jsp"/>
<script type="text/javascript">
	var wsUri = "ws://localhost:8080/weys/websocket";
	function init() {
		websocket = new WebSocket(wsUri);
		websocket.onopen = function(evt) {
			onOpen(evt)
		};
		websocket.onmessage = function(evt) {
			onMessage(evt)
		};
		websocket.onerror = function(evt) {
			onError(evt)
		};
// 		output = document.getElementById("output");
	}
// 	function send_message() {
// 		websocket = new WebSocket(wsUri);
// 		websocket.onopen = function(evt) {
// 			onOpen(evt)
// 		};
// 		websocket.onmessage = function(evt) {
// 			onMessage(evt)
// 		};
// 		websocket.onerror = function(evt) {
// 			onError(evt)
// 		};
// 	}

	function onOpen(evt) {
		writeToScreen("Connected to Endpoint!");
		doSend('NONE', storeId.value);
	}
	function onMessage(evt) {
		console.log(evt);
		var msg = evt.data;
		writeToScreen("Message Received: " + msg);
	}
	function onError(evt) {
		writeToScreen('ERROR: ' + evt.data);
	}
	function doSend(barcode, storeId) {
		writeToScreen("Message Sent: " + barcode + ", " + storeId);
		websocket.send(JSON.stringify({
			barcode : barcode
			, storeId : storeId
		}));
	}
	function writeToScreen(message) {
		var pre = document.createElement("p");
		pre.style.wordWrap = "break-word";
		pre.innerHTML = message;

		output.appendChild(pre);
	}
	window.addEventListener("load", init, false);
</script>

</head>
<body>
	<input type="text" id="storeId" value="33">
	<div id="output"></div>
</body>
</html>