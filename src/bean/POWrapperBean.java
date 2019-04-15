package bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="purchaseOrder")
@XmlAccessorType(XmlAccessType.FIELD)
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
	
	public AddressBean getAddressToShip() {
		return addressToShip;
	}

	public void setAddressToShip(AddressBean addressToShip) {
		this.addressToShip = addressToShip;
	}

	public List<POItemBean> getItems() {
		return items;
	}

	public void setItems(List<POItemBean> items) {
		this.items = items;
	}
	
}
