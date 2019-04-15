package ctrl;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bean.LoginBean;
import dao.AddressDAO;
import dao.LoginDAO;
import dao.UserDAO;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet(urlPatterns = { "/Signup", "/Signup/*" })
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String signupPage = "Signup.jspx";
	private String successPage = "Success.jspx";
	private String mainServlet = "Store";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignupServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().removeAttribute("error");
		if (!(request.getParameter("success") == null)) {
			response.sendRedirect(mainServlet);
		} else if (!(request.getParameter("signup") == null)) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String repeatPassword = request.getParameter("repeatPassword");
			String fName = request.getParameter("fName");
			String lName = request.getParameter("lName");
			String street = request.getParameter("street");
			String country = request.getParameter("country");
			String province = request.getParameter("province");
			String zip = request.getParameter("zip");
			String phone = request.getParameter("phone");
			LoginDAO loginDAO = new LoginDAO();
			List<LoginBean> list;
			try {
				list = loginDAO.retrieve(username);
				if (list.isEmpty()) {
					if (username.isEmpty() || password.isEmpty() || fName.isEmpty() || lName.isEmpty()
							|| street.isEmpty() || country.isEmpty() || province.isEmpty() || zip.isEmpty()
							|| phone.isEmpty()) {
						request.getSession().setAttribute("error", "All fields need to be full");
					} else {
						if (password == repeatPassword) {
							if (password.length() >= 6) {
								SecureRandom random = new SecureRandom();
								byte[] salt = new byte[64];
								random.nextBytes(salt);
								String encryptedPass = hashToString(sha256(password, salt));
								loginDAO.create(username, encryptedPass, hashToString(salt));
								list = loginDAO.retrieve(username);
								LoginBean bean = list.get(0);
								AddressDAO addDAO = new AddressDAO();
								addDAO.create(bean.getId(), street, province, country, zip, phone);
								UserDAO userDAO = new UserDAO();
								userDAO.create(bean.getId(), fName, lName);
								request.getRequestDispatcher(successPage).forward(request, response);
							} else {
								request.getSession().setAttribute("error", "The password is too short");
							}
						} else {
							request.getSession().setAttribute("error", "The passwords do not match");
						}
					}
				} else {
					request.getSession().setAttribute("error", "The username already exists!");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		request.getRequestDispatcher(signupPage).forward(request, response);
	}

	private static byte[] sha256(String base, byte[] salt) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.update(base.getBytes("UTF-8"));
			digest.update(salt);
			return digest.digest();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	private String hashToString(byte[] hash) {
		StringBuffer hexString = new StringBuffer();

		for (int i = 0; i < hash.length; i++) {
			String hex = Integer.toHexString(0xff & hash[i]);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}

		return hexString.toString();
	}

}
