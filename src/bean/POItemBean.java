package bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder={"productName", "price", "partNum"})
@XmlAccessorType(XmlAccessType.FIELD)
public class POItemBean {

	@XmlElement(name="partNum")
	private int id;
	@XmlElement(name="productName")
	private String bid;
	@XmlElement(name="price")
	private int price;
	private int quantity;

	public POItemBean() {
		this.bid = null;
	}
	
	public POItemBean(int id, String bid, int price, int quantity) {
		this.id = id;
		this.bid = bid;
		this.price = price;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
