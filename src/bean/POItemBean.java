package bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "POItem")
@XmlAccessorType(XmlAccessType.FIELD)
public class POItemBean {

	@XmlElement 
	private int id;
	@XmlElement 
	private String isbn;
	@XmlElement 
	private int price;
	@XmlElement 
	private int quantity;

	public POItemBean(int id, String isbn, int price, int quantity) {
		this.id = id;
		this.isbn = isbn;
		this.price = price;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
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
