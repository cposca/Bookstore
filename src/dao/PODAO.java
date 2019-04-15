package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.POBean;

public class PODAO {
	
	public PODAO() {
	}
	
	public List<POBean> retrieve(int id) throws SQLException{
		String query = "select * from PO where id = ?";
		Connection con = MySQLConnector.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		p.setInt(1, id);
		ResultSet r = p.executeQuery();
		List<POBean> rv = new ArrayList<POBean>();
		while(r.next()) {
			int i = r.getInt("ID");
			String status = r.getString("STATUS");
			int address = r.getInt("ADDRESS");
			String fname = r.getString("FNAME");
			String lname = r.getString("LNAME");
			rv.add(new POBean(i,lname, fname,status,address));
		}		
		r.close();
		p.close();
		con.close();
		return rv;
	}
	
	public void create(int id, String fname, String lname, String status, int address) throws SQLException {
		String update = "INSERT INTO PO (id, lname, fname, status, address) VALUES ('?,?,?,?,?');";
		Connection con = MySQLConnector.getConnection();
		PreparedStatement p = con.prepareStatement(update);
		p.setInt(1, id);
		p.setString(2, lname);
		p.setString(3, fname);
		p.setString(4, status);
		p.setInt(5, address);
		p.executeUpdate();
		p.close();
		con.close();
	}
	
	public void update(int id, String status) throws SQLException {
		String update = "UPDATE PO SET 'status'= ? WHERE 'id' = ?;";
		Connection con = MySQLConnector.getConnection();
		PreparedStatement p = con.prepareStatement(update);
		p.setString(1, status);
		p.setInt(2, id);
		p.executeUpdate();
		p.close();
		con.close();
	}
	
	public int countOrders() throws SQLException {
		String query = "SELECT COUNT(*) FROM PO;";
		Connection con = MySQLConnector.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		return r.getInt(1);
	}
}