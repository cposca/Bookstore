package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.CommerceEventBean;

public class CommerceEventDAO {
	
	private DataSource ds;
	
	public CommerceEventDAO() throws ClassNotFoundException{
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
	public List<CommerceEventBean> retrieve(int isbn) throws SQLException{
		String query = "select * from CommerceEvent where isbn = " + isbn;
		List<CommerceEventBean> rv = new ArrayList<CommerceEventBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while(r.next()) {
			String i = r.getString("ISBN");
			String timestamp = r.getString("TIMESTAMP");
			String eventType = r.getString("EVENTTYPE");
			rv.add(new CommerceEventBean(i,timestamp,eventType));
		}
		r.close();
		p.close();
		con.close();
		return rv;
	}
	
	public void create(String isbn, String timestamp, String eventType) throws SQLException {
		String update = "INSERT INTO CommerceEvent (isbn, timestamp, eventType) VALUES ('" + isbn + "', " + timestamp + "', " + eventType + "');";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(update);
		p.executeUpdate();
		p.close();
		con.close();
	}
}