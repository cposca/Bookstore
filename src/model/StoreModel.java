package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DAO.BookDAO;
import bean.BookBean;

public class StoreModel {

	public StoreModel() {

	}

	public List<BookBean> retrieveBooks() throws Exception {

		BookDAO dao = new BookDAO();
		return dao.retrieveBooks();
	}

}
