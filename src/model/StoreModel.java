package model;

import java.util.List;
import java.util.Map;

import bean.BookBean;
import bean.ReviewBean;
import dao.BookDAO;

public class StoreModel {

	BookDAO dao;

	public StoreModel() {
		dao = new BookDAO();
	}

	public BookBean getBookDetails(String bid) throws Exception {
		return dao.getBook(bid).get(0);
	}

	public List<BookBean> retrieveBooks(String category, String search) throws Exception {
		return dao.retrieveBooks(category, search);
	}

	public List<String> getCategoryList() throws Exception {
		return dao.getCategoryList();
	}

	public List<ReviewBean> getReviewList(String bid) throws Exception {
		return dao.retrieveReviews(bid);
	}

	public void addReview(String name, String review, String bid) throws Exception {
		dao.addReview(name, review, bid);
	}

}
