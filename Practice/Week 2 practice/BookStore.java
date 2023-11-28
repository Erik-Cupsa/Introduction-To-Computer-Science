import java.util.Arrays;

public class BookStore {

	private String name;
	private Book[] bookList;
	
	public BookStore(String name){
		this.name=name;
		this.bookList=new Book[0];
	}
	
	public BookStore(String name, Book[] bookList){
		this.name=name;
		this.bookList=bookList;
	}
	
	public void sale() {
		for(int i=0; i< bookList.length; i++) {
			bookList[i].onSale();
		}
	}
	
	public String toString() {
		return "The bookstore, "+this.name+", containing the books "+Arrays.toString(bookList);
	}
	
	public String getRecommendation() {
		
		if (bookList.length == 0) {
			return "No books in this bookstore.";
		}
		
		double maxPrice = bookList[0].getPrice();
		Book mostExpensiveBook = bookList[0];
		
		for(int i=1; i<bookList.length; i++) {
			if(bookList[i].getPrice()>maxPrice) {
				maxPrice=bookList[i].getPrice();
				mostExpensiveBook=bookList[i];
			}
		}
		return mostExpensiveBook.getTitle();
	}
	
	public static BookStore betterEquipped(BookStore bookstore_one,BookStore bookstore_two) {
		if(bookstore_one.bookList.length>=bookstore_two.bookList.length) {
			return bookstore_one;
			//Returns bookstore_one if both bookstores have the same number of books
		}else {
			return bookstore_two;
		}
	}
	
	public static void main(String[] args) {
		Book myBook1 = new Book("Unknown Book", 10.59);
		Book myBook2= new Book("The Great Gatsby", "F. Scott Fitzgerald" ,7.35 );
		
		System.out.println(myBook2.toString()+" costs "+myBook2.getPrice()+"$");
		System.out.println(myBook1.toString()+" costs "+myBook1.getPrice()+"$\n");
		
		System.out.println("Let's put "+myBook1.toString()+" on sale");
		myBook1.onSale();
		System.out.println(myBook1.toString()+" costs "+myBook1.getPrice()+"$\n");
		
		System.out.println("Question: Is "+myBook2.toString()+" more expensive than "+myBook1.toString()+"?");
		System.out.println("Answer: "+myBook2.isMoreExpensive(myBook1)+"\n");
		
		System.out.println("Question: Is "+myBook1.toString()+" more expensive than "+myBook2.toString()+"?");
		System.out.println("Answer: "+myBook1.isMoreExpensive(myBook2)+"\n");
		
		System.out.println("Let's create three bookstores.");
		Book[] bookstoreList1 = new Book[2];
		bookstoreList1[0]= new Book("Harry Potter and the Chamber of Secrets","J.K. Rowling", 19.55);
		bookstoreList1[1]= new Book("Harry Potter and the Deathly Hallows","J.K. Rowling", 12.55);
		
		Book[] bookstoreList2 = new Book[1];
		bookstoreList2[0]= new Book("Harry Potter and the Prisoner of Azkaban","J.K. Rowling", 17.55);
		
		Book[] bookstoreList3 = new Book[3];
		bookstoreList3[0]= new Book("Harry Potter and the Chamber of Secrets","J.K. Rowling", 19.55);
		bookstoreList3[1]= new Book("Harry Potter and the Deathly Hallows","J.K. Rowling", 12.55);
		bookstoreList3[2]= new Book("Harry Potter and the Prisoner of Azkaban","J.K. Rowling", 17.55);
		
		
		BookStore myBookStore1 = new BookStore("Chapters",bookstoreList1);
		BookStore myBookStore2 = new BookStore("Indigo", bookstoreList2);
		BookStore myBookStore3 = new BookStore("Amazon", bookstoreList3);
		
		System.out.println(myBookStore1.toString());
		System.out.println(myBookStore2.toString());
		System.out.println(myBookStore3.toString()+"\n");
		
		System.out.println("Let's put the books in the bookstores on sale.");
		myBookStore1.sale();
		myBookStore2.sale();
		myBookStore3.sale();
		System.out.println(myBookStore1.bookList[0].getTitle()+" now costs "+myBookStore1.bookList[0].getPrice()+"$");
		System.out.println(myBookStore1.bookList[1].getTitle()+" now costs "+myBookStore1.bookList[1].getPrice()+"$");
		System.out.println(myBookStore2.bookList[0].getTitle()+" now costs "+myBookStore2.bookList[0].getPrice()+"$\n");
		
		System.out.println("The most expensive book in "+myBookStore1.name+" is "+myBookStore1.getRecommendation());
		System.out.println("The most expensive book in "+myBookStore2.name+" is "+myBookStore2.getRecommendation());
		System.out.println("The most expensive book in "+myBookStore3.name+" is "+myBookStore3.getRecommendation()+"\n");
		
		
		
		System.out.println("The bookstore named "+betterEquipped(myBookStore1,myBookStore2).name+" contains the most books between bookstores "+myBookStore1.name+" and "+myBookStore2.name+".");
		System.out.println("The bookstore named "+betterEquipped(myBookStore3,myBookStore1).name+" contains the most books between bookstores "+myBookStore3.name+" and "+myBookStore1.name+".\n");
		
		System.out.println("Testing most expensive book in an empty bookstore.");
		BookStore myBookStore4 = new BookStore("Renaud-Bray");
		System.out.println(myBookStore4.getRecommendation());
		
	}
	
}
