package service;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import bean.LoginBean;
import bean.POItemBean;
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
	@Produces(MediaType.APPLICATION_XML)
	public List<POItemBean> getOrdersByPartNumber(@DefaultValue("0") @QueryParam("partNumber") int partNumber) throws SQLException {
		List<POItemBean> orders = null;
		if (!daoAvailable) {
			if (!InstantiateDAO()) {
				return orders;
			}
		} else {
			orders = orderItemInformation.retrieve(partNumber);
		}
		
		return orders;
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
		orderInformation.create(count + 1, user.getLname(), user.getFname(), "ORDERED", user.getId());
		for (int i = 0; i < shoppingCart.size(); i++) {
			POItemBean book = shoppingCart.get(i);
			orderItemInformation.create(count + 1, book.getBid(), book.getPrice());
		}
		
		return "success";
	}
	
}
