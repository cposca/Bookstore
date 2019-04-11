package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import javax.sql.DataSource;
import bean.BookBean;

public class BookDAO {

	public BookDAO() throws ClassNotFoundException {

	}

	private List<BookBean> executeQuery(String query) throws SQLException {

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<BookBean> bookBean = null;

		try {

			connection = MySQLConnector.getConnection();
			statement = connection.prepareStatement(query);
			rs = statement.executeQuery();
			bookBean = new ArrayList<BookBean>();

			while (rs.next()) {
				String isbn = rs.getString("BID");
				String title = rs.getString("TITLE");
				String category = rs.getString("CATEGORY");
				int price = rs.getInt("PRICE");
				bookBean.add(new BookBean(isbn, title, category, price));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					rs.close();
					statement.close();
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();

				}

			}

		}
		return bookBean;
	}

	private void executeUpdate(String update) throws SQLException {

		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = MySQLConnector.getConnection();
			statement = connection.prepareStatement(update);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			if (connection != null) {
				try {
					statement.close();
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();

				}
			}
		}

	}

	public void createBook(String isbn, String title, String author, String publisher, String publishYear,
			String category, String genre, String format, int price, int stock) throws SQLException {
		String update = "INSERT INTO book (isbn, title, author, publisher, publishYear, category, genre, format, price, stock) VALUES ('"
				+ isbn + "', " + title + "', " + author + "', " + publisher + "', " + publishYear + "', " + category
				+ "', " + genre + "', " + format + "', " + price + "', " + stock + "');";
		executeUpdate(update);
	}

	public void updateStock(String isbn, int i) throws SQLException {
		String update = "UPDATE book SET 'stock'='stock'" + (i >= 0 ? "+" : "-") + i + " WHERE 'isbn' = '" + isbn
				+ "';";
		executeUpdate(update);
	}

	public List<BookBean> retrieveBooks() throws SQLException {
		String query = "select * from book";
		return executeQuery(query);
	}

	public List<BookBean> retrieveByISBN(String isbn) throws SQLException {
		String query = "select * from book where isbn like '%" + isbn + "%'";
		return executeQuery(query);
	}

	public List<BookBean> retrieveByTitle(String title) throws SQLException {
		String query = "select * from book where title like '%" + title + "%'";
		return executeQuery(query);
	}

	public List<BookBean> retrieveByAuthor(String author) throws SQLException {
		String query = "select * from book where author like '%" + author + "%'";
		return executeQuery(query);
	}

	public List<BookBean> retrieveByPublisher(String publisher) throws SQLException {
		String query = "select * from book where publisher like '%" + publisher + "%'";
		return executeQuery(query);
	}

	public List<BookBean> retrieveByPublisherYear(String publisherYear) throws SQLException {
		String query = "select * from book where publishYear like '%" + publisherYear + "%'";
		return executeQuery(query);
	}

	public List<BookBean> retrieveByCategory(String category) throws SQLException {
		String query = "select * from book where category like '%" + category + "%'";
		return executeQuery(query);
	}

	public List<BookBean> retrieveByGenre(String genre) throws SQLException {
		String query = "select * from book where genre like '%" + genre + "%'";
		return executeQuery(query);
	}

	public List<BookBean> retrieveByFormat(String format) throws SQLException {
		String query = "select * from book where format like '%" + format + "%'";
		return executeQuery(query);
	}

	public List<BookBean> retrieveByPriceGreaterEqual(int price) throws SQLException {
		String query = "select * from book where price >= " + price;
		return executeQuery(query);
	}

	public List<BookBean> retrieveByPriceGreater(int price) throws SQLException {
		String query = "select * from book where price > " + price;
		return executeQuery(query);
	}

	public List<BookBean> retrieveByPriceLessEqual(int price) throws SQLException {
		String query = "select * from book where price <= " + price;
		return executeQuery(query);
	}

	public List<BookBean> retrieveByPriceLess(int price) throws SQLException {
		String query = "select * from book where price < " + price;
		return executeQuery(query);
	}
}
