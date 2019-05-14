package by.hohma13.controller;

import by.hohma13.domain.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.io.IOException;


@Controller
public class RoomController {

	private static final String ROOM_STATUS_GET = "GET";
	private static final String ROOM_STATUS_SET = "SET";

	private String roomNumber;
	private String roomStatus;
	private String roomRequest;

	@MessageMapping("/message")
	@SendTo("/chat/messages")
	public Message getMessages(Message message) throws IOException {
		//roomchoose(message, message.getMessage());
		String [] roomMsg = message.getMessage();
		roomNumber = roomMsg[0];
		roomStatus = roomMsg[1];
		roomRequest = roomMsg[2];


		if (roomRequest.equals(ROOM_STATUS_GET)){
			InputOutputController bulbOnOff = new InputOutputController(roomNumber);
			String abc = bulbOnOff.getBulbStatus();
			message.setMessage(abc);
		}
		else if (roomRequest.equals(ROOM_STATUS_SET)){
			InputOutputController bulbOnOff = new InputOutputController(roomNumber,roomStatus);
			message.setMessage(bulbOnOff.setBulbStatus());
		}

		return message;
	}

//	private void roomchoose(Message message, String[] roommun){
//		FileInputStream fis;
//		Properties property = new Properties();
//		String abc = null;
//		String roomprop =  "bulb.status"+roommun;
//		try {
//			fis = new FileInputStream("src/main/resources/config.properties");
//			property.load(fis);
//			abc = property.getProperty(roomprop);
//
//		} catch (IOException e) {
//			System.err.println("ОШИБКА: Файл свойств отсуствует!");
//		}
//		switch (message.getMessage()){
//			case ("1"):
//				message.setMessage(abc+"1");
//				break;
//			case ("2"):
//				message.setMessage(abc+"2");
//				break;
//			case("true1"):
//				confpropWrite(message,property,1,"true");
//				break;
//			case("true2"):
//				confpropWrite(message,property,2,"true");
//				break;
//			case("false1"):
//				confpropWrite(message,property,1,"false");
//				break;
//			case("false2"):
//				confpropWrite(message,property,2,"false");
//				break;
//
//		}
//	}
//
//	private void confpropWrite(Message message, Properties property, int num, String bulbstatus){
//		try {
//			FileOutputStream file = new FileOutputStream("src/main/resources/config.properties");
//			property.setProperty("bulb.status"+num, bulbstatus);
//			property.store(file, null);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		message.setMessage(bulbstatus+num);
//	}

}
