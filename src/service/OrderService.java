package service;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import bean.POItemBean;
import dao.POItemDAO;

@Path("POService")
public class OrderService extends Service{

	POItemDAO orderInformation;
	protected boolean daoAvailable = true;

	public OrderService() {
		super();
	}
	
	@Override
	protected boolean InstantiateDAO() {
		orderInformation = new POItemDAO();
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
			orders = orderInformation.retrieve(partNumber);
		}
		
		return orders;
	}
	
	@GET
	@Path("/create/")
	@Produces(MediaType.TEXT_PLAIN)
	public String createOrder() {
		
		return "fail";
	}
	
}
