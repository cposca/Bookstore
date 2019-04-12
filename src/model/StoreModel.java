package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.BookBean;
import dao.BookDAO;

public class StoreModel {

	public StoreModel() {

	}

	public List<BookBean> retrieveBooks(String category, String search) throws Exception {

		BookDAO dao = new BookDAO();
		return dao.retrieveBooks(category, search);
	}

	public List<String> getCategoryList() throws Exception {
		BookDAO dao = new BookDAO();
		return dao.getCategoryList();
	}

}
