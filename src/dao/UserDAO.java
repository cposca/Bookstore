package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import bean.UserBean;

public class UserDAO {
	
private DataSource ds;
	
	public UserDAO() throws ClassNotFoundException{
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
	private UserBean executeQuery(String query) throws SQLException {
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		r.next();
		String username = r.getString("USERNAME");
		String password = r.getString("PASSWORD");
		String email = r.getString("EMAIL");
		String fname = r.getString("FNAME");
		String lname = r.getString("LNAME");
		r.close();
		p.close();
		con.close();
		return new UserBean(username,password,email,fname,lname);
	}
	
	public UserBean retrieveById(int id) throws SQLException{
		String query = "select * from user where id = " + id;
		return executeQuery(query);
	}
	
	public UserBean retrieveByName(String fname, String lname) throws SQLException{
		String query = "select * from user where fname = '" + lname + "' AND lname = '" + lname + "';";
		return executeQuery(query);
	}
	
	public UserBean retrieveByEmail(String email) throws SQLException{
		String query = "select * from user where email = '" + email;
		return executeQuery(query);
	}
	
	public void create(int id, String email, String fname, String lname) throws SQLException {
		String update = "INSERT INTO user (id, email, fname, lname) VALUES ('" + id + "', " + email + "', " + fname + "', " + lname + "');";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(update);
		p.executeUpdate();
		p.close();
		con.close();
	}
	
	public void delete(int id) throws SQLException {
		String update = "DELETE FROM user WHERE 'id' = '" + id;
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(update);
		p.executeUpdate();
		p.close();
		con.close();
	}
	
	public void updateEmail(int id, String email) throws SQLException {
		String update = "UPDATE user SET 'email'='" + email + "' WHERE 'id' = '" + id + "';";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(update);
		p.executeUpdate();
		p.close();
		con.close();
	}
}