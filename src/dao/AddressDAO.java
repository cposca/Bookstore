package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import bean.AddressBean;

public class AddressDAO {
	
	private DataSource ds;
	
	public AddressDAO() throws ClassNotFoundException{
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
	public AddressBean retrieve(int id) throws SQLException{
		String query = "select * from book where id = " + id;
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		r.next();
		int i = r.getInt("ID");
		String street = r.getString("STREET");
		String province = r.getString("PROVINCE");
		String country = r.getString("COUNTRY");
		String zip = r.getString("ZIP");
		String phone = r.getString("PHONE");
		r.close();
		p.close();
		con.close();
		return new AddressBean(i,street,province,country,zip,phone);
	}
	
	public void create(int id, String street, String province, String country, String zip, String phone) throws SQLException {
		String update = "INSERT INTO address (id, street, province, country, zip, phone) VALUES ('" + id + "', " + street + "', " + province + "', " + country + "', " + zip + "', " + phone + "');";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(update);
		p.executeUpdate();
		p.close();
		con.close();
	}
	
	public void update(int id, String street, String province, String country, String zip, String phone) throws SQLException {
		String update = "UPDATE address SET street = '" + street + "', province = '" + province + "', country = '" + country + "', zip = '" + zip + "', phone = " + phone + "' WHERE id = " + id;
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(update);
		p.executeUpdate();
		p.close();
		con.close();
	}
}
