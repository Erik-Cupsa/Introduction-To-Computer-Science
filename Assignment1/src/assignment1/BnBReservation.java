package assignment1;

public class BnBReservation extends HotelReservation{
	
	// for testing
	public static void main(String[] args) {
		
	}
	
	/*
	 * BnBReservation
	 * 
	 * constructor that initializes a reservation
	 * @param String input Name for reservation
	 * @param Hotel hotel The hotel
	 * @param String type Room type 
	 * @param int numNights Number of nights
	 */
	public BnBReservation(String input, Hotel hotel, String type, int numNights) {
		super(input,hotel, type, numNights);
	}
	
	/*
	 * getCost
	 * 
	 * Retrieves the cost of staying iin the room
	 * @return int The integer price in cents of the room
	 */
	public int getCost() {
		int cost = super.getCost(); 
		cost += 1000 * super.getNumOfNights(); 
		return cost; 
	}
}
