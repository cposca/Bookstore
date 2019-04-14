package model;

import java.util.List;
import java.util.Map;

import bean.BookBean;
import bean.ReviewBean;
import dao.BookDAO;

public class StoreModel {

	private BookDAO bookDao;
	private Map<String, BookBean> storeBooks;

	public StoreModel() {
//		bookDao = new BookDAO();
//		try {
//			List<BookBean> tempList;
//			tempList = retrieveBooks(null, null);
//			for (BookBean b : tempList)
//				storeBooks.put(b.getIsbn(), b);
//		} catch (Exception e) {
//			e.printStackTrace();
//			// TODO: make an exception for error handling
//		}
	}

//	public List<BookBean> retrieveBooks() throws Exception {
//		return bookDao.retrieveBooks();
//	}
//	
	public BookBean getBookDetails(String bid) throws Exception {
		BookDAO dao = new BookDAO();
		return dao.getBook(bid);
	}

	public List<BookBean> retrieveBooks() throws Exception {
		return bookDao.retrieveBooks();
	}
	
	public BookBean getBookDetails(String isbn) {
		return storeBooks.get(isbn);
  }
  
	public List<BookBean> retrieveBooks(String category, String search) throws Exception {
		BookDAO dao = new BookDAO();
		return dao.retrieveBooks(category, search);
	}

	public List<String> getCategoryList() throws Exception {
		BookDAO dao = new BookDAO();
		return dao.getCategoryList();
	}

	public List<ReviewBean> getReviewList(String bid) throws Exception {
		BookDAO dao = new BookDAO();
		return dao.retrieveReviews(bid);
	}

	public void addReview(String name, String review, String bid) throws Exception {
		BookDAO dao = new BookDAO();
		dao.addReview(name, review, bid);
	}

}
