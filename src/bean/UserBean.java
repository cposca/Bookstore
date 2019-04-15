package bean;

public class UserBean {
	
	private int id;
	private String fname;
	private String lname;
	
	public UserBean(int id, String fname, String lname) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
	}
	public int getId() {
		return id;
	}
	public String getFname() {
		return fname;
	}
	public String getLname() {
		return lname;
	}
	
}
