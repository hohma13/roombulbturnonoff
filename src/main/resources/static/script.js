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
var roomnum = window.location.pathname;
roomnum = roomnum.substring(1,6);

	if (text == "true") {
		turnOn(roomnum);
	}
	else if (text == "false") {
		turnOff(roomnum);
	}
}

function turnOn(roomnum){

	var bulb = "Bulb is turn ON"
	var $message;
	$message =$(roomnum).text(bulb);

	return setTimeout(function () {
		return $message.addClass('appeared');
	}, 0);
}

function turnOff(roomnum){
	var bulb = "Bulb is turn OFF"
	var $message;
	$message =$(roomnum).text(bulb);
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
