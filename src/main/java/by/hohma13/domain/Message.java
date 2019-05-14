package by.hohma13.domain;

public class Message {

	private String from;
	private String message;
	private String[] roomAndBulb = new String[3];


	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String[] getMessage() {
		roomAndBulb = message.split(" ");
		return roomAndBulb;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	@Override
	public String toString() {
		return message;
	}
	
	
}
