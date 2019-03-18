package bean;

public class POBean {

	private int id;
	private String status;
	private int address;

	public POBean(int id, String status, int address) {
		super();
		this.id = id;
		this.status = status;
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public String getStatus() {
		return status;
	}

	public int getAddress() {
		return address;
	}
}
