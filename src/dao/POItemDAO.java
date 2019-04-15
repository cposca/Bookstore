package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import bean.POItemBean;

public class POItemDAO {
	
	public POItemDAO() {
	}
	
	public List<POItemBean> retrieve(int id) throws SQLException{
		String query = "select * from POItem where id = " + id;
		List<POItemBean> rv = new ArrayList<POItemBean>();
		Connection con = MySQLConnector.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while(r.next()) {
			int i = r.getInt("ID");
			String isbn = r.getString("BID");
			int price = r.getInt("PRICE");
			rv.add(new POItemBean(i,isbn,price, 1));
		}
		r.close();
		p.close();
		con.close();
		return rv;
	}
	
	public void create(int id, String bid, int price) throws SQLException {
		String update = "INSERT INTO POItem (id, bid, price) VALUES (?,?,?);";
		Connection con = MySQLConnector.getConnection();
		PreparedStatement p = con.prepareStatement(update);
		p.setInt(1, id);
		p.setString(2, bid);
		p.setInt(3, price);
		p.executeUpdate();
		p.close();
		con.close();
	}
	
	public void delete(int id, String bid) throws SQLException {
		String update = "DELETE FROM POItem WHERE 'id' = ? AND bid = ?;";
		Connection con = MySQLConnector.getConnection();
		PreparedStatement p = con.prepareStatement(update);
		p.setInt(1, id);
		p.setString(2, bid);
		p.executeUpdate();
		p.close();
		con.close();
	}
	
	/*
	public void updateQuantity(int id, String isbn, int quantity) throws SQLException {
		String update = "UPDATE POItem SET 'quantity'='quantity'" + (quantity >= 0? "+" : "-") + quantity + " WHERE 'id' = '" + id + "' AND 'bid' = '" + isbn + "';";
		Connection con = MySQLConnector.getConnection();
		PreparedStatement p = con.prepareStatement(update);
		p.executeUpdate();
		p.close();
		con.close();
	}
	*/
}