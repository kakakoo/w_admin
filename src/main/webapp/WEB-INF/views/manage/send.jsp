<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<jsp:include page="../common/commonjs.jsp"/>
<script type="text/javascript">
	var wsUri = "ws://dev.weysapp.com/admin/websocket";
	function init() {
		output = document.getElementById("output");
	}
	function send_message() {

		websocket = new WebSocket(wsUri);
		websocket.onopen = function(evt) {
			onOpen(evt)
		};
// 		websocket.onmessage = function(evt) {
// 			onMessage(evt)
// 		};
		websocket.onerror = function(evt) {
			onError(evt)
		};
	}

	function onOpen(evt) {
		writeToScreen("Connected to Endpoint!");
		doSend(barcode.value, storeId.value);
	}
	function onMessage(evt) {
		writeToScreen("Message Received: " + evt.data);
	}
	function onError(evt) {
		writeToScreen('ERROR: ' + evt.data);
	}
	function doSend(barcode, storeId) {
		writeToScreen("Message Sent: " + barcode + ", " + storeId);
// 		websocket.send(message);
		websocket.send(JSON.stringify({
			barcode : barcode
			, storeId : storeId
		}));
		websocket.close();
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
	<h1 style="text-align: center;">Hello World WebSocket Client</h1>
	<br>
	<div style="text-align: center;">
		<form action="">
			<input onclick="send_message()" value="Send" type="button"> 
			<input id="barcode" name="barcode" value="2222 1122 3232 2122" type="text"><br>
			<input id="storeId" name="storeId" value="2" type="text"><br>
		</form>
	</div>
	<div id="output"></div>
</body>
</html>