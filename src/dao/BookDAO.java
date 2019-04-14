package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import bean.BookBean;
import bean.ReviewBean;

public class BookDAO {

	public BookDAO() {

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
}
