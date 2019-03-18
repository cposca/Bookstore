package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.BookBean;

public class BookDAO {

	private DataSource ds;
	
	public BookDAO() throws ClassNotFoundException{
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
	private List<BookBean> executeQuery(String query) throws SQLException{
		List<BookBean> rv = new ArrayList<BookBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while(r.next()) {
			String isbn = r.getString("ISBN");
			String title = r.getString("TITLE");
			String author = r.getString("AUTHOR");
			String publisher = r.getString("PUBLISHER");
			String publishYear = r.getString("PUBLISHYEAR");
			String category = r.getString("CATEGORY");
			String genre = r.getString("GENRE");
			String format = r.getString("FORMAT");
			String description = r.getString("DESCRIPTION");
			int price = r.getInt("PRICE");
			int stock = r.getInt("STOCK");
			rv.add(new BookBean(isbn,title,author,publisher,publishYear,category,genre,format,description,price,stock));
		}
		r.close();
		p.close();
		con.close();
		return rv;
	}
	
	private void executeUpdate(String update) throws SQLException {
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(update);
		p.executeUpdate();
		p.close();
		con.close();
	}
	
	
	public void createBook(String isbn, String title, String author, String publisher, String publishYear, String category, String genre, String format, int price, int stock) throws SQLException{
		String update = "INSERT INTO book (isbn, title, author, publisher, publishYear, category, genre, format, price, stock) VALUES ('" + isbn + "', " + title + "', " + author + "', " + publisher + "', " + publishYear + "', " + category + "', " + genre + "', " + format + "', " + price + "', " + stock + "');";
		executeUpdate(update);
	}
	
	public void updateStock(String isbn, int i) throws SQLException {
		String update = "UPDATE book SET 'stock'='stock'" + (i >= 0? "+" : "-") + i + " WHERE 'isbn' = '" + isbn + "';";
		executeUpdate(update);
	}
	
	public List<BookBean> retrieveByISBN(String isbn) throws SQLException{
		String query = "select * from book where isbn like '%" + isbn + "%'";
		return executeQuery(query);
	}
	
	public List<BookBean> retrieveByTitle(String title) throws SQLException{
		String query = "select * from book where title like '%" + title + "%'";
		return executeQuery(query);
	}
	
	public List<BookBean> retrieveByAuthor(String author) throws SQLException{
		String query = "select * from book where author like '%" + author + "%'";
		return executeQuery(query);
	}
	
	public List<BookBean> retrieveByPublisher(String publisher) throws SQLException{
		String query = "select * from book where publisher like '%" + publisher + "%'";
		return executeQuery(query);
	}
	
	public List<BookBean> retrieveByPublisherYear(String publisherYear) throws SQLException{
		String query = "select * from book where publishYear like '%" + publisherYear + "%'";
		return executeQuery(query);
	}
	
	public List<BookBean> retrieveByCategory(String category) throws SQLException{
		String query = "select * from book where category like '%" + category + "%'";
		return executeQuery(query);
	}
	
	public List<BookBean> retrieveByGenre(String genre) throws SQLException{
		String query = "select * from book where genre like '%" + genre + "%'";
		return executeQuery(query);
	}
	
	public List<BookBean> retrieveByFormat(String format) throws SQLException{
		String query = "select * from book where format like '%" + format + "%'";
		return executeQuery(query);
	}
	
	public List<BookBean> retrieveByPriceGreaterEqual(int price) throws SQLException{
		String query = "select * from book where price >= " + price;
		return executeQuery(query);
	}
	
	public List<BookBean> retrieveByPriceGreater(int price) throws SQLException{
		String query = "select * from book where price > " + price;
		return executeQuery(query);
	}
	
	public List<BookBean> retrieveByPriceLessEqual(int price) throws SQLException{
		String query = "select * from book where price <= " + price;
		return executeQuery(query);
	}
	
	public List<BookBean> retrieveByPriceLess(int price) throws SQLException{
		String query = "select * from book where price < " + price;
		return executeQuery(query);
	}
}
