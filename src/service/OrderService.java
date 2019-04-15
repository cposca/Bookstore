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
public class OrderService extends Service {

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
	@Path("/getJson/")
	@Produces(MediaType.APPLICATION_JSON)
	public String getOrdersByPartNumberJSON(@DefaultValue("0") @QueryParam("partNumber") int partNumber)
			throws SQLException, JAXBException, SAXException, IOException {
		String output = "{\n";
		List<POItemBean> orders = null;
		if (!daoAvailable) {
			if (!InstantiateDAO()) {
				return output;
			}
		} else {
			orders = orderItemInformation.retrieve(partNumber);
			List<AddressBean> beanList = addressInformation.retrieve(partNumber);
			if (beanList.size() <= 0) {
				return output + "}";
			}
			AddressBean bean = beanList.get(0);
			output += "\"Purchase Order\": {\n";
			if (bean != null) {
				output += "\t\"Ship To\": {\n";
				output += "\t\t\"Street\": \"" + bean.getStreet() + "\",\n";
				output += "\t\t\"Province\": \"" + bean.getProvince() + "\",\n";
				output += "\t\t\"Zip\": \"" + bean.getZip() + "\",\n";
				output += "\t\t\"Country\": \"" + bean.getCountry() + "\",\n";
				output += "\t\t\"Street\": \"" + bean.getPhone() + "\"\n";
				output += "\t},\n";
			}
			if (orders.size() > 0) {
				output += "\t\"Items\": [\n";
				for (int i = 0; i < orders.size(); i++) {
					output += "\t\t{\n";
					output += "\t\t\t\"Product Name\": \"" + orders.get(i).getBid() + "\",\n";
					output += "\t\t\t\"Price\": \"" + orders.get(i).getPrice() + "\",\n";
					output += "\t\t\t\"Part Number\": \"" + orders.get(i).getId() + "\"\n";
					output += "\t\t}\n";
				}
				output += "\t]\n";
			}
		}
		output += "}";
		return output;
	}

	@GET
	@Path("/get/")
	@Produces(MediaType.APPLICATION_XML)
	public String getOrdersByPartNumber(@DefaultValue("0") @QueryParam("partNumber") int partNumber)
			throws SQLException, JAXBException, SAXException, IOException {
		String output = "";
		List<POItemBean> orders = null;
		if (!daoAvailable) {
			if (!InstantiateDAO()) {
				return output;
			}
		} else {
			orders = orderItemInformation.retrieve(partNumber);
			List<AddressBean> beanList = addressInformation.retrieve(partNumber);
			if (beanList.size() <= 0) {
				return output;
			}
			AddressBean bean = beanList.get(0);
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
		UserBean user;
		try {
			user = userInformation.retrieveById(login.getId()).get(0);
		} catch (NullPointerException e) {
			return "fail";
		}
		int count = orderInformation.countOrders();
		if (user == null) {
			return "fail";
		}
		if (orderStatus) {
			orderInformation.create(count + 1, user.getLname(), user.getFname(), "ORDERED", user.getId());
		} else {
			orderInformation.create(count + 1, user.getLname(), user.getFname(), "DENIED", user.getId());
			return "fail";
		}
		for (int i = 0; i < shoppingCart.size(); i++) {
			POItemBean book = shoppingCart.get(i);
			orderItemInformation.create(count + 1, book.getBid(), book.getPrice());
		}

		return "success";
	}

}