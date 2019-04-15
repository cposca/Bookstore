package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

import bean.POItemBean;

public class POItemDAO {

	public POItemDAO() {
	}

	public List<POItemBean> retrieve(int id) throws SQLException {
		String query = "select * from POItem where id = " + id;
		List<POItemBean> rv = new ArrayList<POItemBean>();
		Connection con = MySQLConnector.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next()) {
			int i = r.getInt("ID");
			String isbn = r.getString("ISBN");
			int price = r.getInt("PRICE");
			int quantity = r.getInt("QUANTITY");
			rv.add(new POItemBean(i, isbn, price, quantity));
		}
		r.close();
		p.close();
		con.close();
		return rv;
	}

	public void create(int id, String isbn, int price, int quantity) throws SQLException {
		String update = "INSERT INTO POItem (id, isbn, price, quantity) VALUES ('" + id + "', " + isbn + "', " + price
				+ "', " + quantity + "');";
		Connection con = MySQLConnector.getConnection();
		PreparedStatement p = con.prepareStatement(update);
		p.executeUpdate();
		p.close();
		con.close();
	}

	public void delete(int id, String isbn) throws SQLException {
		String update = "DELETE FROM POItem WHERE 'id' = '" + id + "' AND isbn = '" + isbn + "';";
		Connection con = MySQLConnector.getConnection();
		PreparedStatement p = con.prepareStatement(update);
		p.executeUpdate();
		p.close();
		con.close();
	}

	public void updateQuantity(int id, String isbn, int quantity) throws SQLException {
		String update = "UPDATE POItem SET 'quantity'='quantity'" + (quantity >= 0 ? "+" : "-") + quantity
				+ " WHERE 'id' = '" + id + "' AND 'isbn' = '" + isbn + "';";
		Connection con = MySQLConnector.getConnection();
		PreparedStatement p = con.prepareStatement(update);
		p.executeUpdate();
		p.close();
		con.close();
	}

	public Map<String, Integer> retrieveOrderCount() {
		Map<String, Integer> rv = new HashMap<String, Integer>();
		try {
			String query = "select bid, count(*) from POItem group by bid";
			Connection con = MySQLConnector.getConnection();
			PreparedStatement p = con.prepareStatement(query);
			ResultSet r = p.executeQuery();
			int i = 0;
			while (r.next() && i < 10) {
				rv.put(r.getString(1), r.getInt(2));
				i++;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rv;
	}
}