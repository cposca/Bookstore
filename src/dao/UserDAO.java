package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.UserBean;

public class UserDAO {

	public UserDAO() {
	}
	
	private List<UserBean> executeQuery(String query) throws SQLException {
		Connection con = MySQLConnector.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		List<UserBean> rv = new ArrayList<UserBean>();
		while(r.next()) {
			int id = r.getInt("ID");
			String email = r.getString("EMAIL");
			String fname = r.getString("FNAME");
			String lname = r.getString("LNAME");
			rv.add(new UserBean(id,email,fname,lname));
		}
		r.close();
		p.close();
		con.close();
		return rv;
	}
	
	public List<UserBean> retrieveById(int id) throws SQLException{
		String query = "select * from user where id = " + id;
		return executeQuery(query);
	}
	
	public List<UserBean> retrieveByName(String fname, String lname) throws SQLException{
		String query = "select * from user where fname = '" + lname + "' AND lname = '" + lname + "';";
		return executeQuery(query);
	}
	
	public List<UserBean> retrieveByEmail(String email) throws SQLException{
		String query = "select * from user where email = '" + email;
		return executeQuery(query);
	}
	
	public void create(int id, String email, String fname, String lname) throws SQLException {
		String update = "INSERT INTO user (id, email, fname, lname) VALUES ('" + id + "', " + email + "', " + fname + "', " + lname + "');";
		Connection con = MySQLConnector.getConnection();
		PreparedStatement p = con.prepareStatement(update);
		p.executeUpdate();
		p.close();
		con.close();
	}
	
	public void delete(int id) throws SQLException {
		String update = "DELETE FROM user WHERE 'id' = '" + id;
		Connection con = MySQLConnector.getConnection();
		PreparedStatement p = con.prepareStatement(update);
		p.executeUpdate();
		p.close();
		con.close();
	}
	
	public void updateEmail(int id, String email) throws SQLException {
		String update = "UPDATE user SET 'email'='" + email + "' WHERE 'id' = '" + id + "';";
		Connection con = MySQLConnector.getConnection();
		PreparedStatement p = con.prepareStatement(update);
		p.executeUpdate();
		p.close();
		con.close();
	}
}