package by.hohma13.domain;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Message {

	private String from;
	private String message;
	private String abc;
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}


	@Override
	public String toString() {
		return message;
	}
	
	
}
