package by.hohma13.controller;

import by.hohma13.domain.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.io.*;
import java.util.Properties;

@Controller
public class RoomController {

	@MessageMapping("/message")
	@SendTo("/chat/messages")
	public Message getMessages(Message message) {
		roomchoose(message, message.getMessage());
		return message;
	}

	private void roomchoose(Message message, String roommun){
		FileInputStream fis;
		Properties property = new Properties();
		String abc = null;
		String roomprop =  "bulb.status"+roommun;
		try {
			fis = new FileInputStream("src/main/resources/config.properties");
			property.load(fis);
			abc = property.getProperty(roomprop);

		} catch (IOException e) {
			System.err.println("ОШИБКА: Файл свойств отсуствует!");
		}
		switch (message.getMessage()){
			case ("1"):
				message.setMessage(abc+"1");
				break;
			case ("2"):
				message.setMessage(abc+"2");
				break;
			case("true1"):
				confpropWrite(message,property,1,"true");
				break;
			case("true2"):
				confpropWrite(message,property,2,"true");
				break;
			case("false1"):
				confpropWrite(message,property,1,"false");
				break;
			case("false2"):
				confpropWrite(message,property,2,"false");
				break;

		}
	}

	private void confpropWrite(Message message, Properties property, int num, String bulbstatus){
		try {
			FileOutputStream file = new FileOutputStream("src/main/resources/config.properties");
			property.setProperty("bulb.status"+num, bulbstatus);
			property.store(file, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		message.setMessage(bulbstatus+num);
	}

}
