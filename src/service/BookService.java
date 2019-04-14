package service;

import java.sql.SQLException;

import bean.BookBean;
import dao.BookDAO;

public class BookService {

	BookDAO bookData;
	boolean daoAvailable = true;
	
	public BookService() {
		try {
			bookData = new BookDAO();
		} catch (ClassNotFoundException e) {
			daoAvailable = false;
			e.printStackTrace();
		}
	}
	
	public BookBean getProductInfo(String bid) throws SQLException {
		BookBean bookInfo = null;
		if (daoAvailable) {
			bookInfo = bookData.getBook(bid);
		}
		
		return bookInfo;
	}
	
}
