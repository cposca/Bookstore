package bean;

public class POBean {

	private int id;
	private String status;
	private int address;
	private String fname;
	private String lname;

	public POBean(int id, String lname, String fname, String status, int address) {
		super();
		this.id = id;
		this.status = status;
		this.address = address;
		this.lname = lname;
		this.fname = fname;
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
	
	public String getFname() {
		return fname;
	}
	
	public String getLname() {
		return lname;
	}
}
