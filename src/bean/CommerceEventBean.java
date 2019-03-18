package bean;

public class CommerceEventBean {

	private String timestamp;
	private String isbn;
	private String eventType;
	
	public CommerceEventBean(String isbn, String timestamp, String eventType) {
		super();
		this.isbn = isbn;
		this.timestamp = timestamp;
		this.eventType = eventType;
	}

	public String getIsbn() {
		return isbn;
	}
	
	public String getTimestamp() {
		return timestamp;
	}

	public String getEventType() {
		return eventType;
	}
	
}
