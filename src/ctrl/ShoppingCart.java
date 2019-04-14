package ctrl;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ShoppingCartModel;
import model.StoreModel;

/**
 * Servlet implementation class ShoppingCart
 */
@WebServlet(urlPatterns = { "/ShoppingCart", "/shoppingCart" })
public class ShoppingCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ShoppingCartModel shoppingCart;

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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("update") != null) {
			shoppingCart = (ShoppingCartModel) request.getSession().getAttribute("shoppingCartModel");

			Map<String, String[]> parametersMap = request.getParameterMap();
			shoppingCart.updateCart(parametersMap);
			setShoppingAttributes(request);
			request.getRequestDispatcher("/ShoppingCart.jspx").forward(request, response);
		} else if (request.getParameter("addToCart") != null) {
			/**
			 * when add to cart button is clicked, make a post call to this servlet with
			 * addToCart as the button name isbn as the parameter with the book isbn value
			 */
			shoppingCart = (ShoppingCartModel) request.getSession().getAttribute("shoppingCartModel");
			if (shoppingCart == null) {
				shoppingCart = new ShoppingCartModel();
			}
			String isbn = request.getParameter("isbn");
			// StoreModel storeModel = (StoreModel)
			// request.getServletContext().getAttribute("storeModel");
			StoreModel storeModel = new StoreModel();
			if (!(isbn.isEmpty()) && isbn != null && storeModel != null) {
				try {
					shoppingCart.addToCart(isbn, storeModel);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				// TODO: Set error message
			}
			setShoppingAttributes(request);
			request.getRequestDispatcher("/ShoppingCart.jspx").forward(request, response);
		} else {
			request.getRequestDispatcher("/ShoppingCart.jspx").forward(request, response);
		}
	}

	private void setShoppingAttributes(HttpServletRequest request) {
		request.getSession().setAttribute("cartItems", 22);
		request.getSession().setAttribute("subTotal", shoppingCart.getSubTotal());
		request.getSession().setAttribute("shoppingList", shoppingCart.getShoppingList());
		request.getSession().setAttribute("shoppingCartModel", shoppingCart);
	}

}
