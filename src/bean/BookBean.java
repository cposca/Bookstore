package bean;

public class BookBean {

	private String isbn;
	private String title;
	private String author;
	private String publisher;
	private String publishYear;
	private String category;
	private String genre;
	private String format;
	private String description;
	private int price;
	private int stock;

	public BookBean(String isbn, String title, String author, String publisher, String publishYear, String category,
			String genre, String format, String description, int price, int stock) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.publishYear = publishYear;
		this.category = category;
		this.genre = genre;
		this.format = format;
		this.price = price;
		this.stock = stock;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getPublisher() {
		return publisher;
	}

	public String getPublishYear() {
		return publishYear;
	}

	public String getCategory() {
		return category;
	}

	public String getGenre() {
		return genre;
	}

	public String getFormat() {
		return format;
	}
	
	public String getDescription() {
		return description;
	}

	public int getPrice() {
		return price;
	}
	
	public int getStock() {
		return stock;
	}
	
}
