package ctrl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.BookBean;
import bean.POItemBean;
import model.StoreModel;

/**
 * Servlet implementation class ShoppingCart
 */
@WebServlet(urlPatterns = { "/ShoppingCart" })
public class ShoppingCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Map<String, POItemBean> shoppingList = new HashMap<String, POItemBean>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShoppingCart() {
		super();
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/ShoppingCart.jspx").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("update") != null) {
			shoppingList = (HashMap<String, POItemBean>) request.getSession().getAttribute("shoppingList");
			Map<String, String[]> parametersMap = request.getParameterMap();
			for (String s : parametersMap.keySet()) {
				POItemBean POI = shoppingList.get(s);
				POI.setQuantity(Integer.parseInt(parametersMap.get(s)[0]));
				shoppingList.put(s, POI);
			}
			request.getSession().setAttribute("shoppingList", shoppingList.values());
			request.getSession().setAttribute("subTotal", getSubTotal());

			request.getRequestDispatcher("/ShoppingCart.jspx").forward(request, response);
		} else if (request.getParameter("addToCart") != null) {
			/**
			 * when add to cart button is clicked, make a post call to this servlet with
			 * addToCart as the button name isbn as the parameter with the book isbn value
			 */
			shoppingList = (HashMap<String, POItemBean>) request.getSession().getAttribute("shoppingList");
			String isbn = request.getParameter("isbn");
			BookBean addedBook = ((StoreModel) request.getServletContext().getAttribute("storeModel"))
					.getBookDetails(isbn);
			if (!shoppingList.containsKey(isbn)) {
				POItemBean tempBean = new POItemBean(222, isbn, addedBook.getPrice(), 1);
				shoppingList.put(isbn, tempBean);
			} else {
				POItemBean tempBean = shoppingList.get(isbn);
				tempBean.setQuantity(tempBean.getQuantity() + 1);
				shoppingList.put(isbn, tempBean);
			}
//			request.getRequestDispatcher("/ShoppingCart.jspx").forward(request, response);
		} else {
			request.getRequestDispatcher("/ShoppingCart.jspx").forward(request, response);
		}
	}

	private int getSubTotal() {
		int total = 0;
		for (POItemBean POI : shoppingList.values()) {
			total += POI.getPrice() * POI.getQuantity();
		}
		return total;
	}

}
