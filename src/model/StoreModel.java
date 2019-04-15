package model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import bean.BookBean;
import bean.ReviewBean;
import dao.BookDAO;
import dao.POItemDAO;

public class StoreModel {

	BookDAO bookDao;
	POItemDAO purchaseOrderDao;
	
	public StoreModel() {
		this.bookDao = new BookDAO();
		this.purchaseOrderDao = new POItemDAO();
	}

	public BookBean getBookDetails(String bid) throws Exception {
		return bookDao.getBook(bid);
	}
	public List<BookBean> getBookDetails(Set<String> bids) throws Exception {
		return bookDao.getBook(bids);
	}

	public List<BookBean> retrieveBooks(String category, String search) throws Exception {
		return bookDao.retrieveBooks(category, search);
	}

	public List<String> getCategoryList() throws Exception {
		return bookDao.getCategoryList();
	}

	public List<ReviewBean> getReviewList(String bid) throws Exception {
		return bookDao.retrieveReviews(bid);
	}

	public void addReview(String name, String review, String bid) throws Exception {
		bookDao.addReview(name, review, bid);
	}
	
	public Map<String, Integer> retrieveOrderCount() {
		return purchaseOrderDao.retrieveOrderCount();
	}

}
