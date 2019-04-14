package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import javax.sql.DataSource;
import bean.BookBean;
import bean.ReviewBean;

public class BookDAO {

	public BookDAO() throws ClassNotFoundException {

	}

	public List<BookBean> retrieveBooks(String category, String search) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<BookBean> bookBean = null;
		String query = "select * from book";
		if (category != null) {
			query += " WHERE category = ?";
		} else if (search != null) {
			query += " WHERE title LIKE ?";
		}

		try {

			connection = MySQLConnector.getConnection();
			statement = connection.prepareStatement(query);
			if (category != null) {
				statement.setString(1, category);
			} else if (search != null) {
				statement.setString(1, "%" + search + "%");
			}
			rs = statement.executeQuery();
			bookBean = new ArrayList<BookBean>();

			while (rs.next()) {
				String isbn = rs.getString("BID");
				String title = rs.getString("TITLE");
				String cat = rs.getString("CATEGORY");
				int price = rs.getInt("PRICE");
				bookBean.add(new BookBean(isbn, title, cat, price));
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

	public List<String> getCategoryList() throws SQLException {

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<String> categories = null;
		String query = "select distinct category from book";

		try {

			connection = MySQLConnector.getConnection();
			statement = connection.prepareStatement(query);
			rs = statement.executeQuery();
			categories = new ArrayList<String>();

			while (rs.next()) {

				String category = rs.getString("CATEGORY");
				categories.add(category);
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
		return categories;

	}

	public BookBean getBook(String bid) throws SQLException {

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		BookBean book = null;
		String query = "select * from book WHERE bid = ?";

		try {

			connection = MySQLConnector.getConnection();
			statement = connection.prepareStatement(query);
			statement.setString(1, bid);
			rs = statement.executeQuery();

			while (rs.next()) {

				String isbn = rs.getString("BID");
				String title = rs.getString("TITLE");
				String cat = rs.getString("CATEGORY");
				int price = rs.getInt("PRICE");
				book = new BookBean(isbn, title, cat, price);

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
		return book;
	}

	public void addReview(String name, String review, String bid) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = MySQLConnector.getConnection();
			String query = "INSERT reviews (name,review,bid) VALUES (?,?,?)";
			statement = connection.prepareStatement(query);
			statement.setString(1, name);
			statement.setString(2, review);
			statement.setString(3, bid);
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

	public List<ReviewBean> retrieveReviews(String bid) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<ReviewBean> reviews = null;
		String query = "select * from reviews WHERE bid = ? ORDER by DATE DESC";

		try {

			connection = MySQLConnector.getConnection();
			statement = connection.prepareStatement(query);

			statement.setString(1, bid);

			rs = statement.executeQuery();
			reviews = new ArrayList<ReviewBean>();

			while (rs.next()) {
				String name = rs.getString("NAME");
				Date date = rs.getDate("DATE");
				String review = rs.getString("REVIEW");
				String isbn = rs.getString("BID");

				reviews.add(new ReviewBean(name, date, review, isbn));
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
		return reviews;

	}
//	private List<BookBean> executeQuery(String query) throws SQLException {
//
//		Connection connection = null;
//		PreparedStatement statement = null;
//		ResultSet rs = null;
//		List<BookBean> bookBean = null;
//
//		try {
//
//			connection = MySQLConnector.getConnection();
//			statement = connection.prepareStatement(query);
//			rs = statement.executeQuery();
//			bookBean = new ArrayList<BookBean>();
//
//			while (rs.next()) {
//				String isbn = rs.getString("BID");
//				String title = rs.getString("TITLE");
//				String category = rs.getString("CATEGORY");
//				int price = rs.getInt("PRICE");
//				bookBean.add(new BookBean(isbn, title, category, price));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			if (connection != null) {
//				try {
//					rs.close();
//					statement.close();
//					connection.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//
//				}
//
//			}
//
//		}
//		return bookBean;
//	}

//	private void executeUpdate(String update) throws SQLException {
//
//		Connection connection = null;
//		PreparedStatement statement = null;
//
//		try {
//			connection = MySQLConnector.getConnection();
//			statement = connection.prepareStatement(update);
//			statement.executeUpdate();
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//
//		} finally {
//			if (connection != null) {
//				try {
//					statement.close();
//					connection.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//
//				}
//			}
//		}
//
//	}

//	public void createBook(String isbn, String title, String author, String publisher, String publishYear,
//			String category, String genre, String format, int price, int stock) throws SQLException {
//		String update = "INSERT INTO book (isbn, title, author, publisher, publishYear, category, genre, format, price, stock) VALUES ('"
//				+ isbn + "', " + title + "', " + author + "', " + publisher + "', " + publishYear + "', " + category
//				+ "', " + genre + "', " + format + "', " + price + "', " + stock + "');";
//		executeUpdate(update);
//	}

//	public void updateStock(String isbn, int i) throws SQLException {
//		String update = "UPDATE book SET 'stock'='stock'" + (i >= 0 ? "+" : "-") + i + " WHERE 'isbn' = '" + isbn
//				+ "';";
//		executeUpdate(update);
//	}

//	public List<BookBean> retrieveByISBN(String isbn) throws SQLException {
//		String query = "select * from book where isbn like '%" + isbn + "%'";
//		return executeQuery(query);
//	}
//
//	public List<BookBean> retrieveByTitle(String title) throws SQLException {
//		String query = "select * from book where title like '%" + title + "%'";
//		return executeQuery(query);
//	}
//
//	public List<BookBean> retrieveByAuthor(String author) throws SQLException {
//		String query = "select * from book where author like '%" + author + "%'";
//		return executeQuery(query);
//	}
//
//	public List<BookBean> retrieveByPublisher(String publisher) throws SQLException {
//		String query = "select * from book where publisher like '%" + publisher + "%'";
//		return executeQuery(query);
//	}
//
//	public List<BookBean> retrieveByPublisherYear(String publisherYear) throws SQLException {
//		String query = "select * from book where publishYear like '%" + publisherYear + "%'";
//		return executeQuery(query);
//	}
//
//	public List<BookBean> retrieveByCategory(String category) throws SQLException {
//		String query = "select * from book where category like '%" + category + "%'";
//		return executeQuery(query);
//	}
//
//	public List<BookBean> retrieveByGenre(String genre) throws SQLException {
//		String query = "select * from book where genre like '%" + genre + "%'";
//		return executeQuery(query);
//	}
//
//	public List<BookBean> retrieveByFormat(String format) throws SQLException {
//		String query = "select * from book where format like '%" + format + "%'";
//		return executeQuery(query);
//	}
//
//	public List<BookBean> retrieveByPriceGreaterEqual(int price) throws SQLException {
//		String query = "select * from book where price >= " + price;
//		return executeQuery(query);
//	}
//
//	public List<BookBean> retrieveByPriceGreater(int price) throws SQLException {
//		String query = "select * from book where price > " + price;
//		return executeQuery(query);
//	}
//
//	public List<BookBean> retrieveByPriceLessEqual(int price) throws SQLException {
//		String query = "select * from book where price <= " + price;
//		return executeQuery(query);
//	}
//
//	public List<BookBean> retrieveByPriceLess(int price) throws SQLException {
//		String query = "select * from book where price < " + price;
//		return executeQuery(query);
//	}
}
