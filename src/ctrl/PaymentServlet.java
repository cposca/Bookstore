package ctrl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.AddressBean;
import bean.LoginBean;
import bean.POItemBean;
import bean.UserBean;
import dao.AddressDAO;
import dao.LoginDAO;
import dao.UserDAO;
import model.ShoppingCartModel;

import service.OrderService;

/**
 * Servlet implementation class ShoppingCart
 */
@WebServlet(urlPatterns = { "/Payment", "/payment" })
public class PaymentServlet extends HttpServlet {

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PaymentServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = (String) request.getSession().getAttribute("username");

		LoginDAO lDao = new LoginDAO();

		int counter = 0;

//		ShoppingCartModel shoppingCart = (ShoppingCartModel) request.getSession().getAttribute("shoppingCartModel");
//		List<POItemBean> listt = shoppingCart.getShoppingList();

		try {
			List<LoginBean> query = lDao.retrieve(username);
			AddressBean address = null;
			LoginBean user = null;
			OrderService order = null;
			if (query.size() > 0) {
				AddressDAO addressDAO = new AddressDAO();
				// UserDAO userDAO = new UserDAO();
				// UserBean user = userDAO.retrieve(query.get(0));
				user = query.get(0);
				List<AddressBean> addresses = addressDAO.retrieve(query.get(0).getId());
				if (addresses.size() > 0) {
					address = addresses.get(0);
					request.setAttribute("address", address);
				}

			}

			if (request.getAttribute("confirm") != null && address != null) {

				counter++;

				if (counter % 3 == 0) {
					request.setAttribute("order", "fail");
				}

				ShoppingCartModel shoppingCart = (ShoppingCartModel) request.getSession()
						.getAttribute("shoppingCartModel");
				List<POItemBean> list = shoppingCart.getShoppingList();

				String accept = order.createOrder();
				// String order = createOrder();
				// String order = createOrder(user, list, true);

				// address
				// request.getRequestDispatcher("/Comfirm.jspx").forward(request, response);
				// return;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("/PaymentPage.jspx").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
