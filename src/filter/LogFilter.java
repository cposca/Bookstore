package filter;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CommerceEventDAO;

/**
 * Servlet Filter implementation class LogFilter
 */
@WebFilter("*")
public class LogFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LogFilter() {
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
		if(req.getRequestURI().contains("book")) {
			CommerceEventDAO cDAO = new CommerceEventDAO();
			try {
				cDAO.create(req.getParameter("bid"),new Long(System.currentTimeMillis()).toString(), "view");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if(req.getRequestURI().contains("ShoppingCart")) {
			CommerceEventDAO cDAO = new CommerceEventDAO();
			try {
				cDAO.create("0",new Long(System.currentTimeMillis()).toString(), "cart");
			} catch (SQLException e) {
				e.printStackTrace();
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
