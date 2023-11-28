package assignment1;

public class Customer {
    //declaring private fields
    private String name;
    private int balance;
    private Basket basket;

    /*
    Customer

    Constructor for customer
    @param String name Name
    @param int balance The balance in cents
     */
    public Customer(String name, int balance) {
        this.name = name;
        this.balance = balance;
        this.basket = new Basket();
        this.basket.clear();
    }

    /*
    getName

    getter method to get Name
    @return String name
     */
    public String getName() {
        return this.name;
    }

    /*
    getBalance

    getter method to get Balance
    @return int balance Balance in cents
     */
    public int getBalance() {
        return this.balance;
    }

    /*
    getBasket

    getter method to return basket
    @return Basket basket The address of the basket
     */
    public Basket getBasket() {
        return this.basket;
    }

    /*
    addFunds

    adding to the balance
    @param int add Money to be added
    @return int balance The new balance in cents
     */
    public int addFunds(int add) {
        if (add < 0) {
            throw new IllegalArgumentException("An illegal amount of money was entered");
        } else {
            this.balance += add;
            return this.balance;
        }
    }

    /*
    addToBasket

    adds reservation to the basket
    @param Reservation reservation
    @return int Number of reservations
     */
    public int addToBasket(Reservation reservation) {
        if (reservation.reservationName().equalsIgnoreCase(this.name)) {
            return this.basket.add(reservation);
        } else {
            throw new IllegalArgumentException("The name does not match one on reservation");
        }
    }

    /*
    addToBasket

    adds reservation to the basket
    @param Hotel hotel
    @param String type Room type
    @param int numNights Number of nights
    @param boolean breakfast Whether wants breakfast included or not
    @return int numReservations Number of reservations
     */
    public int addToBasket(Hotel hotel, String type, int numNights, boolean breakfast) {
        if (breakfast == true) {
            BnBReservation bnbAdd = new BnBReservation(this.name, hotel, type, numNights);
            return this.basket.add(bnbAdd);
        } else {
            HotelReservation hotelAdd = new HotelReservation(this.name, hotel, type, numNights);
            return this.basket.add(hotelAdd);
        }
    }

    /*
    addToBasket

    adds reservation to the basket
    @param Airport one
    @param Airport two
    @return int numReservations
     */
    public int addToBasket(Airport one, Airport two) {
        try {
            FlightReservation flightAdd = new FlightReservation(this.name, one, two);
            return this.basket.add(flightAdd);
        } catch (IllegalArgumentException A) {
            return this.basket.getNumOfReservations();
        }
    }

    /*
    removeFromBasket

    removes a reservation from the basket
    @param Reservation reservation to remove
    @return boolean Whether or not operation was succesful
     */
    public boolean removeFromBasket(Reservation reservation){
        return basket.remove(reservation);
    }

    /*
    checkOut

    charging total cost of basket
    @return int balance Balance left
     */
    public int checkOut(){
        int totalCost = this.basket.getTotalCost();
        if(this.balance < totalCost){
            throw new IllegalStateException("The customer does not have enough balance to cover the cost");
        }
        else{
            this.balance -= totalCost;
            this.basket.clear();
            return this.balance;
        }
    }
}
