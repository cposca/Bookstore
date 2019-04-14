package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import bean.POBean;

public class PODAO {
	
	public PODAO() {
	}
	
	public POBean retrieve(int id) throws SQLException{
		String query = "select * from PO where id = " + id;
		Connection con = MySQLConnector.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		r.next();
		int i = r.getInt("ID");
		String status = r.getString("STATUS");
		int address = r.getInt("ADDRESS");		
		r.close();
		p.close();
		con.close();
		return new POBean(i,status,address);
	}
	
	public void create(int id, String status, int address) throws SQLException {
		String update = "INSERT INTO PO (id, status, address) VALUES ('" + id + "', " + status + "', " + address + "');";
		Connection con = MySQLConnector.getConnection();
		PreparedStatement p = con.prepareStatement(update);
		p.executeUpdate();
		p.close();
		con.close();
	}
	
	public void update(int id, String status) throws SQLException {
		String update = "UPDATE PO SET 'status'='" + status + "' WHERE 'id' = '" + id + "';";
		Connection con = MySQLConnector.getConnection();
		PreparedStatement p = con.prepareStatement(update);
		p.executeUpdate();
		p.close();
		con.close();
	}
}