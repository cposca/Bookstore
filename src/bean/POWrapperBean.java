package bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="purchaseOrder")
public class POWrapperBean {
	@XmlAttribute(name = "shipTo")
	private AddressBean addressToShip;
	@XmlAttribute(name = "Items")
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
