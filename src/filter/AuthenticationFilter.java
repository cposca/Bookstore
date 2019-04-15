package filter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.VisitEventBean;
import dao.VisitEventDAO;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
@WebFilter("*")
public class AuthenticationFilter implements Filter {

	private String mainPageServlet = "Store";
	private String loginServlet = "Login";
    /**
     * Default constructor. 
     */
    public AuthenticationFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String authToken = (String) req.getSession().getAttribute("authToken");
		String username = (String) req.getSession().getAttribute("username");
		if(username == null && (req.getRequestURI().contains("admin") || req.getRequestURI().contains("services"))) {
			res.sendRedirect(loginServlet);
		}else if(username != null) {
			if(authToken != null) {
				VisitEventDAO visitDAO = new VisitEventDAO();
				List<VisitEventBean> list;
				try {
					list = visitDAO.retrieveByToken(authToken);
					if(!list.isEmpty()) {
						VisitEventBean bean = list.get(0);
						long currentTime = System.currentTimeMillis();
						if(currentTime- (Long.parseLong(bean.getTimestamp())) >1800000) {
							visitDAO.updateTimestamp(authToken, new Long(currentTime).toString());
						}else {
							visitDAO.updateStatus(authToken, "inactive");
							req.getSession().removeAttribute("authToken");
							req.getSession().removeAttribute("username");
						}
						if(req.getRequestURI().contains("admin") && !bean.getUsername().equals("admin")){
							res.sendRedirect(mainPageServlet);
						} else if(req.getRequestURI().contains("services") && (!bean.getUsername().equals("admin") || !bean.getUsername().equals("partner"))) {
							res.sendRedirect(mainPageServlet);
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
