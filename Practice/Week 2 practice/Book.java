public class Book {
	
	private String title;
	private String author;
	private double price;
	private static final String anonymousAuthor = "Anonymous";
	
	public Book(String title, double price) {
		this.title=title;
		this.price=price;	
		this.author=anonymousAuthor;
	}
	
	public Book(String title, String author, double price) {
		this.title=title;
		this.author=author;
		this.price=price;	
	}
	
	public void onSale() {
		this.price=this.price/2;
	}
	
	public String toString() {
		return title+ " by "+author;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public boolean isMoreExpensive(Book aBook) {
		return (this.price>aBook.price);
	}

}
