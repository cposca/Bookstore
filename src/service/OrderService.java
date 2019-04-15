package service;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import bean.AddressBean;
import bean.LoginBean;
import bean.POItemBean;
import bean.POWrapperBean;
import bean.UserBean;
import dao.AddressDAO;
import dao.PODAO;
import dao.POItemDAO;
import dao.UserDAO;

@Path("POService")
public class OrderService extends Service{

	POItemDAO orderItemInformation;
	PODAO orderInformation;
	UserDAO userInformation;
	AddressDAO addressInformation;
	
	protected boolean daoAvailable = true;

	public OrderService() {
		super();
	}
	
	@Override
	protected boolean InstantiateDAO() {
		orderItemInformation = new POItemDAO();
		orderInformation = new PODAO();
		userInformation = new UserDAO();
		addressInformation = new AddressDAO();
		return daoAvailable;
	}
	
	@GET
	@Path("/get/")
	@Produces(MediaType.TEXT_XML)
	public String getOrdersByPartNumber(@DefaultValue("0") @QueryParam("partNumber") int partNumber) throws SQLException, JAXBException, SAXException, IOException {
		String output = "";
		List<POItemBean> orders = null;
		if (!daoAvailable) {
			if (!InstantiateDAO()) {
				return output;
			}
		} else {
			orders = orderItemInformation.retrieve(partNumber);
			AddressBean bean = addressInformation.retrieve(partNumber).get(0);
			POWrapperBean wr = new POWrapperBean(bean, orders);
			JAXBContext jc = JAXBContext.newInstance(wr.getClass());
			Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
			URL r = this.getClass().getResource("/");
			String currentDirFile = URLDecoder.decode(r.getFile(), "UTF-8");
			if (currentDirFile.startsWith("/")) {
				currentDirFile = currentDirFile.replaceFirst("/", "");
			}
			currentDirFile = currentDirFile.substring(0, currentDirFile.length() - 16);
			
			SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema sc = sf.newSchema(new File(currentDirFile + "export/po.xsd"));
			marshaller.setSchema(sc);
			StringWriter sw = new StringWriter();
			marshaller.marshal(wr, new StreamResult(sw));
			output = sw.toString();
			sw.close();
		}
		return output;
	}
	
	@GET
	@Path("/create/")
	@Produces(MediaType.TEXT_PLAIN)
	public String createOrder(LoginBean login, List<POItemBean> shoppingCart, boolean orderStatus) throws SQLException {
		UserBean user = userInformation.retrieveById(login.getId()).get(0);
		int count = orderInformation.countOrders();
		if (user == null) {
			return "fail";
		}
		if (orderStatus) {
			orderInformation.create(count + 1, user.getLname(), user.getFname(), "ORDERED", user.getId());
		} else {
			orderInformation.create(count + 1, user.getLname(), user.getFname(), "DENIED", user.getId());
		}
		for (int i = 0; i < shoppingCart.size(); i++) {
			POItemBean book = shoppingCart.get(i);
			orderItemInformation.create(count + 1, book.getBid(), book.getPrice());
		}
		
		return "success";
	}
	
}
