package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import bean.LoginBean;

public class LoginDAO {
	
	
	public LoginDAO() {
	}
	
	public void create(String username, String password, String salt) throws SQLException {
		String update = "INSERT INTO Login (username, password, salt) VALUES ('" + username + "', " + password + "', " + salt + "');";
		Connection con = MySQLConnector.getConnection();
		PreparedStatement p = con.prepareStatement(update);
		p.executeUpdate();
		p.close();
		con.close();
	}
	
	public List<LoginBean> retrieve(String username) throws SQLException{
		String query = "select * from Login where username = " + username;
		Connection con = MySQLConnector.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		List<LoginBean> rv = new ArrayList<LoginBean>();
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
