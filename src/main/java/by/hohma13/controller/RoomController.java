package by.hohma13.controller;

import by.hohma13.domain.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.io.IOException;


@Controller
public class RoomController {

	private static final String ROOM_REQUEST_GET = "GET";
	private static final String ROOM_REQUEST_SET = "SET";

	private String roomNumber;
	private String roomStatus;
	private String roomRequest;

	@MessageMapping("/message")
	@SendTo("/chat/messages")
	public Message getMessages(Message message) throws IOException {

		String [] roomMsg = message.getMessage();
		roomNumber = roomMsg[0];
		roomStatus = roomMsg[1];
		roomRequest = roomMsg[2];

		if (roomRequest.equals(ROOM_REQUEST_GET)){
			BulbTurnOnOff bulbOnOff = new BulbTurnOnOff(roomNumber);
			message.setMessage(bulbOnOff.getBulbStatus());
		}
		else if (roomRequest.equals(ROOM_REQUEST_SET)){
			BulbTurnOnOff bulbOnOff = new BulbTurnOnOff(roomNumber,roomStatus);
			message.setMessage(bulbOnOff.setBulbStatus());
		}

		return message;
	}
}
