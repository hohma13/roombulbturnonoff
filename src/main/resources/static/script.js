function connect(room) {
	var socket = new SockJS('/chat-messaging');
	stompClient = Stomp.over(socket);

	stompClient.connect({}, function(frame) {
		console.log("connected: " + frame);
		stompClient.subscribe('/chat/messages', function(response) {
			var data = JSON.parse(response.body);
			draw("left", data.message);

		});
		if (room == 1){
			stompClient.send("/app/message", {}, JSON.stringify({'message': "1"}));
		}
		else if (room == 2){
			stompClient.send("/app/message", {}, JSON.stringify({'message': "2"}));
		}


	});

}

function draw(side, text) {
switch (text){
    case ("true1"):

        break;
}

	if (text == "true1") {

		var bulb = "Bulb is turn ON"
		var $message;
		$message =$(room1).text(bulb);

		return setTimeout(function () {
			return $message.addClass('appeared');
		}, 0);

	}
	else if (text == "false1") {
		var bulb = "Bulb is turn OFF"
		var $message;
        $message =$(room1).text(bulb);
		return setTimeout(function () {
			return $message.addClass('appeared');
		}, 0);

	}
	else if (text == "true2") {
		var bulb = "Bulb is turn ON"
		var $message;
		$message =$(room2).text(bulb);

		return setTimeout(function () {
			return $message.addClass('appeared');
		}, 0);

	}
	else if (text == "false2") {
		var bulb = "Bulb is turn OFF"
		var $message;
        $message =$(room2).text(bulb);

        return setTimeout(function () {
			return $message.addClass('appeared');
		}, 0);

	}
}

function sendMessageTrue(num){
	if (num == 1){
		stompClient.send("/app/message", {}, JSON.stringify({'message': "true1"}));
	}
	else if (num ==2){
		stompClient.send("/app/message", {}, JSON.stringify({'message': "true2"}));
	}

}

function sendMessageFalse(num){
	if (num == 1){
		stompClient.send("/app/message", {}, JSON.stringify({'message': "false1"}));
	}
	else if (num ==2){
		stompClient.send("/app/message", {}, JSON.stringify({'message': "false2"}));
	}


}
