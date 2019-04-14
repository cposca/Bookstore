package bean;

import java.io.Serializable;

public class BookBean implements Serializable{

	private static final long serialVersionUID = -5939236241100947050L;
	private String isbn;
	private String title;
	private String category;
	private int price;

	public BookBean() {
		this.isbn = null;
		this.title = null;
		this.category = null;
		this.price = -1;
	}
	
	public BookBean(String isbn, String title, String category, int price) {
		this.isbn = isbn;
		this.title = title;
		this.category = category;
		this.price = price;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getTitle() {
		return title;
	}

	public String getCategory() {
		return category;
	}

	public int getPrice() {
		return price;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}
