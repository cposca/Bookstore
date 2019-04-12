package model;

import java.util.List;
import java.util.Map;

import DAO.BookDAO;
import bean.BookBean;

public class StoreModel {
	
	private BookDAO bookDao;
	private Map<String, BookBean> storeBooks;

	public StoreModel() throws ClassNotFoundException {
		bookDao = new BookDAO();
		try {
			List<BookBean> tempList;
			tempList = retrieveBooks();
			for(BookBean b: tempList)
				storeBooks.put(b.getIsbn(), b);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: make an exception for error handling
		}
	}

	public List<BookBean> retrieveBooks() throws Exception {
		return bookDao.retrieveBooks();
	}
	
	public BookBean getBookDetails(String isbn) {
		return storeBooks.get(isbn);
	}

}
