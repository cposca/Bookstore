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
import dao.CommerceEventDAO;
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

	static int counter = 0;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = (String) request.getSession().getAttribute("username");
		LoginDAO lDao = new LoginDAO();

		boolean pass;

		try {
			List<LoginBean> query = lDao.retrieve(username);
			AddressBean address = null;
			LoginBean user = null;
			OrderService order = new OrderService();
			if (query.size() > 0) {
				AddressDAO addressDAO = new AddressDAO();
				user = query.get(0);
				List<AddressBean> addresses = addressDAO.retrieve(query.get(0).getId());
				if (addresses.size() > 0) {
					address = addresses.get(0);
					request.setAttribute("address", address);
				}

			}
			
			request.setAttribute("error3", "You must be signed in to confirm your order!");

			if (request.getParameter("confirm") != null && address != null) {

				counter++;
				pass = true;

				String credit = request.getParameter("credit");

				if (credit == null || credit.length() == 0) {
					request.setAttribute("error2", "Your credit card information cannot be empty!");
				} else {

					if (counter % 3 == 0) {
						pass = false;
					}

					ShoppingCartModel shoppingCart = (ShoppingCartModel) request.getSession()
							.getAttribute("shoppingCartModel");
					List<POItemBean> list = shoppingCart.getShoppingList();

					String accept = order.createOrder(user, list, pass);
					// CALL WHEN SUCCEED
					CommerceEventDAO cDAO = new CommerceEventDAO();
					try {
						cDAO.create("0", new Long(System.currentTimeMillis()).toString(), "purchase");
					} catch (SQLException e) {
						e.printStackTrace();
					}
					request.setAttribute("approved", accept);
				}

				request.getRequestDispatcher("/PaymentPage.jspx").forward(request, response);

			} else {
				request.getRequestDispatcher("/PaymentPage.jspx").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// request.getRequestDispatcher("/PaymentPage.jspx").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
