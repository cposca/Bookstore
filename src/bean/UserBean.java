package bean;

public class UserBean {
	
	private String username;
	private String password;
	private String email;
	private String fname;
	private String lname;
	
	public UserBean(String username, String password, String email, String fname, String lname) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.fname = fname;
		this.lname = lname;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getEmail() {
		return email;
	}
	public String getFname() {
		return fname;
	}
	public String getLname() {
		return lname;
	}
	
}
