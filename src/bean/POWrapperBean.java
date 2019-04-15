package bean;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="purchaseOrder")
public class POWrapperBean {
	@XmlElement(name = "shipTo")
	private AddressBean addressToShip;
	@XmlElement(name = "Items")
	private List<POItemBean> items;

	public POWrapperBean() {
		addressToShip = null;
		items = null;
	}
	
	public POWrapperBean(AddressBean addressToShip, List<POItemBean> items) {
		this.addressToShip = addressToShip;
		this.items = items;
	}
	
}
