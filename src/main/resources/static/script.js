function connect(roomNum) {
	var socket = new SockJS('/chat-messaging');
	stompClient = Stomp.over(socket);

	stompClient.connect({}, function(frame) {
		console.log("connected: " + frame);
		stompClient.subscribe('/chat/messages', function(response) {
			var data = JSON.parse(response.body);
			draw("left", data.message);

		});
		stompClient.send("/app/message", {}, JSON.stringify({'message': roomNum + " bulb GET"}));

	});

}

function draw(side, text) {
var roomGet = window.location.pathname;
roomGet = roomGet.substring(1,6);

	if (text == "true") {
		turnOn(roomGet);
	}
	else if (text == "false") {
		turnOff(roomGet);
	}
}

function turnOn(room){

	var bulb = "Bulb is turn ON"
	var $message;
	var roomText = "'div."+room+"'";
	$message =$(roomText).text(bulb);

	return setTimeout(function () {
		return $message.addClass('appeared');
	}, 0);
}

function turnOff(room){
	var bulb = "Bulb is turn OFF"
	var $message;
	var roomText = "'div."+room+"'";
	$message =$(roomText).text(bulb);
	return setTimeout(function () {
		return $message.addClass('appeared');
	}, 0);
}

function sendMessageTrue(num){
		stompClient.send("/app/message", {}, JSON.stringify({'message': num + " true SET"}));
}

function sendMessageFalse(num){

		stompClient.send("/app/message", {}, JSON.stringify({'message': num + " false SET"}));


}
