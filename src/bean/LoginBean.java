package bean;

public class LoginBean {
	
	private int id;
	private String username;
	private String password;
	private String salt;
	
	public LoginBean(int id, String username, String password, String salt) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.salt = salt;
	}

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getSalt() {
		return salt;
	}
	
}
