public class BookStore {
    private String name;
    private Book[] books;

    public BookStore(String name){
        this.name = name;
        this.books = new Book[0];
    }

    public BookStore(String name, Book[] books){
        this.name = name;
        this.books = books;
    }

    public void sale(){
        for(int i=0; i<books.length; i++) {
            books[i].onSale();
        }
    }

    public String toString(){
        return "Name: " + this.name + ", Number of books: " + this.books.length;
    }

    public String getRecommandation(){
        double price = 0;
        for(int i=0; i < books.length; i++){
            if(books[i].price > price){
                price = books[i].price;
            }
        }
        for(int i=0; i < books.length; i++){
            if(books[i].price == price) {
                return books[i].toString();
            }
        }
        return "";
    }

    public BookStore betterEquipped(BookStore one, BookStore two){
        if(one.books.length > two.books.length ) return one;
        return two;
    }
}
