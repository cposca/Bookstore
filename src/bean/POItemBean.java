package bean;

public class POItemBean {

	private int id;
	private String isbn;
	private int price;
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
	public String getIsbn() {
		return isbn;
	}
	public int getPrice() {
		return price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}
