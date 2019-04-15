package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnector {
	private static MySQLConnector instance = new MySQLConnector();
	public static final String SERVERURL = "jdbc:mysql://localhost:3306/bookstoredb"; // database name bookstoredb
	public static final String USERNAME = "root"; // Usually root
	public static final String PASSWORD = "root";
	public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";

	private MySQLConnector() {

		try {
			Class.forName(DRIVER_CLASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	private Connection createConnection() {
		Connection connection = null;

		try {
			connection = DriverManager.getConnection(SERVERURL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			System.out.print("ERROR: Unable to Connect to Database.");
		}
		return connection;
	}

	public static Connection getConnection() {
		return instance.createConnection();
	}

}
