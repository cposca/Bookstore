package ctrl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.BookBean;
import model.StoreModel;

@WebServlet(urlPatterns = { "/Start", "/Start/*" })
public class Start extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StoreModel storeObj;

	public Start() {
		super();
	}

	public void init() throws ServletException {
		ServletContext context = getServletContext();
		try {
			storeObj = new StoreModel();
			context.setAttribute("storeModel", storeObj);
		} catch (ClassNotFoundException e) {
			// TODO make an exception
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			List<BookBean> books = storeObj.retrieveBooks();
			request.setAttribute("books", books);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/MainPage.jspx").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
