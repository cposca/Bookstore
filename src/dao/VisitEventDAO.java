package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import bean.VisitEventBean;

public class VisitEventDAO {
	
	private DataSource ds;
	
	public VisitEventDAO() throws ClassNotFoundException{
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
	public List<VisitEventBean> retrieveByStatus(String status) throws SQLException{
		String query = "select * from VisitEvent where status = '" + status + "';";
		List<VisitEventBean> rv = new ArrayList<VisitEventBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
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
		String query = "select * from VisitEvent where username = '" + username + "';";
		List<VisitEventBean> rv = new ArrayList<VisitEventBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
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
		String query = "select * from VisitEvent where token = '" + token + "';";
		List<VisitEventBean> rv = new ArrayList<VisitEventBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
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
		String update = "INSERT INTO VisitEvent (username, timestamp, status, token) VALUES ('" + username + "', " + timestamp + "', " + status + "', " + token + "');";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(update);
		p.executeUpdate();
		p.close();
		con.close();
	}
}