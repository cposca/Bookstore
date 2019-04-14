package service;

import java.sql.SQLException;

import bean.BookBean;
import dao.BookDAO;

public class BookService extends Service{

	BookDAO bookData;
	protected boolean daoAvailable = true;
	
	public BookService() {
		super();
	}

	@Override
	protected boolean InstantiateDAO() {
		try {
			bookData = new BookDAO();
		} catch (ClassNotFoundException e) {
			daoAvailable = false;
			e.printStackTrace();
		}
		return daoAvailable;
	}
	
	public BookBean getProductInfo(String bid) throws SQLException {
		BookBean bookInfo = null;
		if (!daoAvailable) {
			if (!InstantiateDAO()) {
				return bookInfo;
			}
		} else {
			bookInfo = bookData.getBook(bid);
		}
		
		return bookInfo;
	}
	
}
