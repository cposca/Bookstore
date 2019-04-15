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
		bookData = new BookDAO();
		return daoAvailable;
	}
	
	public BookBean getProductInfo(String bid) throws SQLException {
		BookBean bookInfo = null;
		if (!daoAvailable) {
			if (!InstantiateDAO()) {
				return bookInfo;
			}
		} else {
			bookInfo = bookData.getBook(bid).get(0);
		}
		
		return bookInfo;
	}
	
}
