package bean;

public class POItemBean {

	private int id;
	private String isbn;
	private int price;
	private int quantity;
	
	public POItemBean(int id, String isbn, int price, int quantity) {
		super();
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
}
