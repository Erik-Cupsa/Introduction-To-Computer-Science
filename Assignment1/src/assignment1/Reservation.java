package assignment1;

public abstract class Reservation {
	// declaring private fields
	private String name; 
	
	//for testing purposes
	public static void main(String[] args) {
		
	}
	
	/*
	 * Reservation
	 * 
	 * constructor that initializes a reservation
	 * @param String input Name for reservation
	 */
	public Reservation(String input) {
		this.name = input;
	}
	
	/*
	 * reservationName
	 * 
	 * Retrieves the name of the reservation
	 * @return String this.name The name of the reservation
	 */
	public final String reservationName() {
		return this.name; 
	}
	
	/*
	 * getCost
	 * 
	 * Abstract method to determine cost
	 * @return int cost The cost
	 */
	public abstract int getCost(); 
	
	/*
	 * equals
	 * 
	 * Abstract method to determine equality
	 * @param Object input
	 * @return boolean Whether equal or not
	 */
	public abstract boolean equals(Object input); 
}
