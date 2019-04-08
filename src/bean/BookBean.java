package bean;

public class BookBean {

	private String isbn;
	private String title;

	private String category;

	private int price;

	public BookBean(String isbn, String title, String category, int price) {
		super();
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

}
