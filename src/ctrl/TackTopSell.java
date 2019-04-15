package ctrl;

import java.awt.print.Book;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import bean.BookBean;
import model.StoreModel;

/**
 * Application Lifecycle Listener implementation class TackTopSell
 *
 */
@WebListener
public class TackTopSell implements HttpSessionAttributeListener {

	/**
	 * Default constructor.
	 */
	public TackTopSell() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
	 */
	public void attributeAdded(HttpSessionBindingEvent arg0) {
		setTopSell(arg0);
	}

	private void setTopSell(HttpSessionBindingEvent arg0) {
		if (arg0.getName().equals("orderSubmitted")) {
			StoreModel storeModel = new StoreModel();
			Map<String, Integer> salesCount = storeModel.retrieveOrderCount();
			Set<String> ids = salesCount.keySet();
			try {
				List<BookBean> bookList = storeModel.getBookDetails(ids);
				arg0.getSession().getServletContext().setAttribute("topSellingBooks", bookList);
			} catch (Exception e) {
				// TODO handle error
			}
		}
	}

	/**
	 * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
	 */
	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
	 */
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		setTopSell(arg0);
	}

}
