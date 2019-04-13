package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import bean.LoginBean;

public class LoginDAO {
	
private DataSource ds;
	
	public LoginDAO() throws ClassNotFoundException{
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
	public void create(int id, String username, String password, String salt) throws SQLException {
		String update = "INSERT INTO Login (id, username, password, salt) VALUES ('" + id + "', " + username + "', " + password + "', " + salt + "');";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(update);
		p.executeUpdate();
		p.close();
		con.close();
	}
	
	public List<LoginBean> retrieve(String username) throws SQLException{
		String query = "select * from Login where username = " + username;
		List<LoginBean> rv = new ArrayList<LoginBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while(r.next()) {
			int id = r.getInt("ID");
			String user = r.getString("USERNAME");
			String password = r.getString("PASSWORD");
			String salt = r.getString("SALT");
			rv.add(new LoginBean(id,user,password,salt));
		}
		r.close();
		p.close();
		con.close();
		return rv;
	}
}
