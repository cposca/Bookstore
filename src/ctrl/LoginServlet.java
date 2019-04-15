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
import javax.xml.bind.DatatypeConverter;

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
	private String mainServlet = "Store";
	private String successPage = "/Success.jspx";
       
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
		if(!(request.getParameter("success") == null)) {
			response.sendRedirect(mainServlet);
		}
		else if(!(request.getParameter("signup") == null)) {
			response.sendRedirect(signupServlet);
		}else if(!(request.getParameter("login") == null)) {
			try {
				LoginDAO l = new LoginDAO();
				String username = request.getParameter("username");
				List<LoginBean> query = l.retrieve(username);
				if(query.size() != 1) {
					request.getSession().setAttribute("error", "Incorrect number of usernames detected! //TODO");
				}else {
					LoginBean bean = query.get(0);
					String salt = bean.getSalt();
					if(bytesToString(sha256(username,salt)).equals(bean.getPassword())) {
						VisitEventDAO visitDAO = new VisitEventDAO();
						String timestamp = (new Long(System.currentTimeMillis())).toString();
						String status = "active";
						SecureRandom random = new SecureRandom();
						byte[] salt2 = new byte[64];
						random.nextBytes(salt2);
						String token = bytesToString(sha256(username,DatatypeConverter.printBase64Binary(salt2)));
						visitDAO.create(username, timestamp, status, token);
						request.getSession().setAttribute("sessionToken", token);
						request.getSession().setAttribute("username",username);
						request.getRequestDispatcher(successPage).forward(request,response);
					}else {
						request.getSession().setAttribute("error", "Incorrect Password!");
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			request.getRequestDispatcher(loginPage).forward(request,response);
		}
	}

	private static byte[] sha256(String base, String salt) {
	    try{
	        MessageDigest digest = MessageDigest.getInstance("SHA-256");
	        digest.update(base.getBytes("UTF-8"));
	        digest.update(salt.getBytes("UTF-8"));
	        return digest.digest();
	    } catch(Exception ex){
	       throw new RuntimeException(ex);
	    }
	}
	
	private String bytesToString(byte[] b) {
		StringBuffer hexString = new StringBuffer();

        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(0xff & b[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }

        return hexString.toString();
	}
	
}
