package ctrl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

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

//		storeObj = new StoreModel();
//		context.setAttribute("sis", storeObj);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		storeObj = new StoreModel();
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
