package bean;

public class VisitEventBean {

	private int id;
	private String username;
	private String timestamp;
	private String status;
	private String token;
	
	public VisitEventBean(int id, String username, String timestamp, String status, String token) {
		super();
		this.id = id;
		this.token = token;
		this.username = username;
		this.timestamp = timestamp;
		this.status = status;
	}
	
	public int getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public String getStatus() {
		return status;
	}
	public String getToken() {
		return token;
	}

}
