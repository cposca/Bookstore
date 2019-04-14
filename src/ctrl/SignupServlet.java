package ctrl;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bean.LoginBean;
import dao.LoginDAO;
import dao.UserDAO;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet(urlPatterns = { "/Signup", "/Signup/*" })
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String signupPage = "Signup.jspx";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!(request.getParameter("signup") == null)) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String repeatPassword = request.getParameter("repeatPassword");
			String street = request.getParameter("street");
			String country = request.getParameter("country");
			String province = request.getParameter("province");
			String zip = request.getParameter("zip");
			String phone = request.getParameter("phone");
			LoginDAO loginDAO = new LoginDAO();
		}
		request.getRequestDispatcher(signupPage).forward(request,response);
	}

}
