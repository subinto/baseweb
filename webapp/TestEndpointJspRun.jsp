<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Websocket Test</title>
	</head>
	<body>
	<form>
		<input id="message" type="text">
		<input onclick="wsSendMessage();" value="Echo" type="button">
		<input onclick="wsCloseConnection();" value="Disconnect" type="button">
	</form>
	<br>
	<textarea id="echoText" rows="30" cols="100"></textarea>
	<script type="text/javascript">
		var webSocket = new WebSocket("ws://127.0.0.1:7200/websocket");
		var echoText = document.getElementById("echoText");
		echoText.value = "";
		var message = document.getElementById("message");
		webSocket.onopen = function(message){ wsOpen(message);};
		webSocket.onmessage = function(message){ wsGetMessage(message);};
		webSocket.onclose = function(message){ wsClose(message);};
		webSocket.onerror = function(message){ wsError(message);};
		
		function wsOpen(message){
			echoText.value += "Connected ... \n";
		}
		
		function wsSendMessage(){
			webSocket.send(message.value);
			echoText.value += "Message sent to the server : " + message.value + "\n";
			message.value = "";
		}
		
		function wsCloseConnection(){
			webSocket.close();
		}
		
		function wsGetMessage(message){
			echoText.value += "Message received from to the server : " + message.data + "\n";
		}
		
		function wsClose(message){
			echoText.value += "Disconnect ... \n";
			console.log("disconnect", message);
		}

		function wsError(message){
			echoText.value += "Error \n";
			console.log("error", message);
		}
	</script>
</body>
</html>