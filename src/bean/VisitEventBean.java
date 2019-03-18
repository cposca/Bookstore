package bean;

public class VisitEventBean {

	private int id;
	private String timestamp;
	private String eventType;
	private String status;
	
	public VisitEventBean(int id, String timestamp, String eventType, String status) {
		super();
		this.timestamp = timestamp;
		this.id = id;
		this.eventType = eventType;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public String getEventType() {
		return eventType;
	}
	
	public String getStatus() {
		return status;
	}
	
}
