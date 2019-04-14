package ctrl;

import java.io.IOException;
import java.security.MessageDigest;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.LoginBean;
import dao.LoginDAO;
import dao.VisitEventDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(urlPatterns = { "/Login", "/Login/*" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String signupServlet = "Signup";
	private String loginPage = "/Login.jspx";	
	private String mainPage = "/MainPage.jspx";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		request.getSession().removeAttribute("error");
		if(!(request.getParameter("signup") == null)) {
			response.sendRedirect(signupServlet);
		}else if(!(request.getParameter("login") == null)) {
			/*try {
				LoginDAO l = new LoginDAO();
				String username = request.getParameter("username");
				List<LoginBean> query = l.retrieve(username);
				if(query.size() != 1) {
					request.getSession().setAttribute("error", "Incorrect number of usernames detected! //TODO");
				}else {
					LoginBean bean = query.get(0);
					String salt = bean.getSalt();
					if(sha256(username+salt) == bean.getPassword()) {
						VisitEventDAO visitDAO = new VisitEventDAO();
						String timestamp = (new Long(System.currentTimeMillis())).toString();
						String status = "active";
						String token = sha256(username+timestamp+status);
						visitDAO.create(username, timestamp, status, token);
						request.getSession().setAttribute("sessionToken", token);
						request.getSession().setAttribute("username",username);
						request.getRequestDispatcher(mainPage).forward(request,response);
					}else {
						request.getSession().setAttribute("error", "Incorrect Password!");
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		} else {
			request.getRequestDispatcher(loginPage).forward(request,response);
		}
	}

	private static String sha256(String base) {
	    try{
	        MessageDigest digest = MessageDigest.getInstance("SHA-256");
	        byte[] hash = digest.digest(base.getBytes("UTF-8"));
	        StringBuffer hexString = new StringBuffer();

	        for (int i = 0; i < hash.length; i++) {
	            String hex = Integer.toHexString(0xff & hash[i]);
	            if(hex.length() == 1) hexString.append('0');
	            hexString.append(hex);
	        }

	        return hexString.toString();
	    } catch(Exception ex){
	       throw new RuntimeException(ex);
	    }
	}
}
