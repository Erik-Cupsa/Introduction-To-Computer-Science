public class Book {
    private String title;
    private String author;
    public double price;
    public static void main(String[] args){
        //testing
    }

    public Book(String title, double price){
        this.title = title;
        this.price = price;
        this.author = "Anonymous";
    }

    public Book(String title, String author, double price){
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public void onSale(){
        this.price = this.price /2;
    }

    public String toString(){
        return "Title: " + this.title + ", Author: " + this.author + ", Price: " + this.price;
    }

    public boolean isMoreExpensive(Book one) {
        return(one.price < this.price);
    }
}
