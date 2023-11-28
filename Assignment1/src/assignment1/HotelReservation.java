package assignment1;

public class HotelReservation extends Reservation{
	//declaring private fields
	private Hotel hotelReservation; 
	private String type; 
	private int numNights; 
	private int price; 
	
	//testing purposes
	public static void main(String[] args) {
		
	}
	
	/*
	 * HotelReservation
	 * 
	 * constructor that initializes a reservation
	 * @param String input Name for reservation
	 * @param Hotel hotel The hotel
	 * @param String type Room type 
	 * @param int numNights Number of nights
	 */
	public HotelReservation(String input, Hotel hotel, String type, int numNights) {
		super(input);
		this.hotelReservation = hotel;
		this.type = type;
		if(numNights < 0){
			throw new IllegalArgumentException("Illegal number of nights");
		}
		else{
			this.numNights = numNights;
		}
		try {
			this.price = hotelReservation.reserveRoom(type); 
		} catch (IllegalArgumentException I) {
			throw new IllegalArgumentException ("No such reservation is possible");
		}
	}
	
	/*
	 * getNumOfNights
	 * 
	 * Retrieves the number of nights
	 * @return int this.numNights The number of nights
	 */
	public int getNumOfNights() {
		return this.numNights; 
	}
	
	/*
	 * getCost
	 * 
	 * Retrieves the cost of staying iin the room
	 * @return int The integer price in cents of the room
	 */
	public int getCost() {
		return this.price * this.numNights; 
	}
	
	@Override
	public boolean equals(Object input) {
		if(this == input) {
			return true; 
		}
		else if(input instanceof HotelReservation == false) {
			return false;
		}
		else {
			HotelReservation hotel = (HotelReservation) input; 
			String name = reservationName();
			String resName = hotel.reservationName(); 
			
			if(name.equalsIgnoreCase(resName) && type.equalsIgnoreCase(hotel.type)) {
				//splitting up for readability
				if(hotelReservation.equals(hotel.hotelReservation) && numNights == hotel.getNumOfNights()) {
					if (hotel.getCost() == (this.price*this.numNights)){
						if(this.getClass().equals(hotel.getClass())) {
							return true;
						}
					}
				}
				
			}
		}
		return false;
	}

}
