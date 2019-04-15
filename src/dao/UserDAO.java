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
	
	private List<UserBean> executeQuery(PreparedStatement preped) throws SQLException {
		ResultSet r = preped.executeQuery();
		List<UserBean> rv = new ArrayList<UserBean>();
		while(r.next()) {
			int id = r.getInt("ID");
			String fname = r.getString("FNAME");
			String lname = r.getString("LNAME");
			rv.add(new UserBean(id,fname,lname));
		}
		r.close();
		return rv;
	}
	
	public List<UserBean> retrieveById(int id) throws SQLException{
		String query = "select * from user where id = ?";
		Connection con = MySQLConnector.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		p.setInt(1, id);
		return executeQuery(p);
	}
	
	public List<UserBean> retrieveByName(String fname, String lname) throws SQLException{
		String query = "select * from user where fname = '?' AND lname = '?'";
		Connection con = MySQLConnector.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		p.setString(1, fname);
		p.setString(2, lname);
		return executeQuery(p);
	}
	
	public void create(int id, String fname, String lname) throws SQLException {
		String update = "INSERT INTO user (id, fname, lname) VALUES (?,?,?)";
		Connection con = MySQLConnector.getConnection();
		PreparedStatement p = con.prepareStatement(update);
		p.setInt(1, id);
		p.setString(2, fname);
		p.setString(3, lname);
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
		String update = "UPDATE user SET email = '?' WHERE id = ?";
		Connection con = MySQLConnector.getConnection();
		PreparedStatement p = con.prepareStatement(update);
		p.setString(1, email);
		p.setInt(2, id);
		p.executeUpdate();
		p.close();
		con.close();
	}
}