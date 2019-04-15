package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.AddressBean;

/**
 * 
 * @author Strawhat Unicorns
 * This class facilitates between the AddressBean object and address table in mysql table. 
 *
 */
public class AddressDAO {

	public AddressDAO() {

	}

	/**
	 * This function will retrieve all the addresses stored in address database table.
	 * @param id
	 * @return list of all address objects.
	 * @throws SQLException
	 */
	public List<AddressBean> retrieve(int id) throws SQLException {
		String query = "select * from address where id = ?";
		List<AddressBean> rv = new ArrayList<AddressBean>();
		Connection con = MySQLConnector.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		p.setInt(1, id);
		ResultSet r = p.executeQuery();
		while (r.next()) {
			int i = r.getInt("ID");
			String street = r.getString("STREET");
			String province = r.getString("PROVINCE");
			String country = r.getString("COUNTRY");
			String zip = r.getString("ZIP");
			String phone = r.getString("PHONE");
			rv.add(new AddressBean(i, street, province, country, zip, phone));
		}
		r.close();
		p.close();
		con.close();
		return rv;
	}

	public void create(int id, String street, String province, String country, String zip, String phone)
			throws SQLException {
		String update = "INSERT INTO address (id, street, province, country, zip, phone) VALUES (?,?,?,?,?,?)";
		Connection con = MySQLConnector.getConnection();
		PreparedStatement p = con.prepareStatement(update);
		p.setInt(1, id);
		p.setString(2, street);
		p.setString(3, province);
		p.setString(4, country);
		p.setString(5, zip);
		p.setString(6, phone);
		p.executeUpdate();
		p.close();
		con.close();
	}

	public void update(int id, String street, String province, String country, String zip, String phone)
			throws SQLException {
		String update = "UPDATE address SET street = '?', province = '?', country = '?', zip = '?', phone = '?' WHERE id = ";
		Connection con = MySQLConnector.getConnection();
		PreparedStatement p = con.prepareStatement(update);
		p.setInt(1, id);
		p.setString(2, street);
		p.setString(3, province);
		p.setString(4, country);
		p.setString(5, zip);
		p.setString(6, phone);
		p.executeUpdate();
		p.close();
		con.close();
	}
}
