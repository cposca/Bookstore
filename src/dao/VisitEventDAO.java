package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import bean.VisitEventBean;

public class VisitEventDAO {
	
	
	public VisitEventDAO() {
	}
	
	public List<VisitEventBean> retrieveByStatus(String status) throws SQLException{
		String query = "select * from VisitEvent where status = '?'";
		List<VisitEventBean> rv = new ArrayList<VisitEventBean>();
		Connection con = MySQLConnector.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		p.setString(1, status);
		ResultSet r = p.executeQuery();
		while(r.next()) {
			int id = r.getInt("ID");
			String username = r.getString("USERNAME");
			String timestamp = r.getString("TIMESTAMP");
			String s = r.getString("STATUS");
			String token = r.getString("TOKEN");
			rv.add(new VisitEventBean(id,username,timestamp,s,token));
		}
		r.close();
		p.close();
		con.close();
		return rv;
	}
	
	public List<VisitEventBean> retrieveByUsername(String username) throws SQLException{
		String query = "select * from VisitEvent where username = '?'";
		List<VisitEventBean> rv = new ArrayList<VisitEventBean>();
		Connection con = MySQLConnector.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		p.setString(1, username);
		ResultSet r = p.executeQuery();
		while(r.next()) {
			int id = r.getInt("ID");
			String u = r.getString("USERNAME");
			String timestamp = r.getString("TIMESTAMP");
			String status = r.getString("STATUS");
			String token = r.getString("TOKEN");
			rv.add(new VisitEventBean(id,u,timestamp,status,token));
		}
		r.close();
		p.close();
		con.close();
		return rv;
	}
	
	public List<VisitEventBean> retrieveByToken(String token) throws SQLException{
		String query = "select * from VisitEvent where token = '?'";
		List<VisitEventBean> rv = new ArrayList<VisitEventBean>();
		Connection con = MySQLConnector.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		p.setString(1, token);
		ResultSet r = p.executeQuery();
		while(r.next()) {
			int id = r.getInt("ID");
			String username = r.getString("USERNAME");
			String timestamp = r.getString("TIMESTAMP");
			String status = r.getString("STATUS");
			String t = r.getString("TOKEN");
			rv.add(new VisitEventBean(id,username,timestamp,status,t));
		}
		r.close();
		p.close();
		con.close();
		return rv;
	}
	
	public void create(String username, String timestamp, String status, String token) throws SQLException {
		String update = "INSERT INTO VisitEvent (username, timestamp, status, token, bid) VALUES (?,?,?,?,?)";
		Connection con = MySQLConnector.getConnection();
		PreparedStatement p = con.prepareStatement(update);
		p.setString(1, username);
		p.setString(2, timestamp);
		p.setString(3, status);
		p.setString(4, token);
		p.setString(5, "b003");
		p.executeUpdate();
		p.close();
		con.close();
	}
	
	public void updateTimestamp(String token, String timestamp) throws SQLException {
		String update = "UPDATE VisitEvent SET 'timestamp'= ? WHERE 'token' = ?";
		Connection con = MySQLConnector.getConnection();
		PreparedStatement p = con.prepareStatement(update);
		p.setString(1, timestamp);
		p.setString(2, token);
		p.executeUpdate();
		p.close();
		con.close();
	}
	
	public void updateStatus(String token, String status) throws SQLException {
		String update = "UPDATE VisitEvent SET 'status'= ? WHERE 'token' = ?";
		Connection con = MySQLConnector.getConnection();
		PreparedStatement p = con.prepareStatement(update);
		p.setString(1, status);
		p.setString(2, token);
		p.executeUpdate();
		p.close();
		con.close();
	}
}