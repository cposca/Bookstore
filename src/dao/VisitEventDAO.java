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
	
	public List<VisitEventBean> retrieve(int id, String status) throws SQLException{
		String query = "select * from VisitEvent where id = '" + id + "' AND status = '" + status + "';";
		List<VisitEventBean> rv = new ArrayList<VisitEventBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while(r.next()) {
			int i = r.getInt("ID");
			String timestamp = r.getString("TIMESTAMP");
			String eventType = r.getString("EVENTTYPE");
			String s = r.getString("STATUS");
			rv.add(new VisitEventBean(i,timestamp,eventType,s));
		}
		r.close();
		p.close();
		con.close();
		return rv;
	}
	
	public void create(int id, String timestamp, int eventType, int status) throws SQLException {
		String update = "INSERT INTO VisitEvent (id, timestamp, eventType, status) VALUES ('" + id + "', " + timestamp + "', " + eventType + "', " + status + "');";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(update);
		p.executeUpdate();
		p.close();
		con.close();
	}
}