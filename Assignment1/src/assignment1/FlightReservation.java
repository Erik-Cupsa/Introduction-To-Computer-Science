package assignment1;

public class FlightReservation extends Reservation{
	//declaring private fields 
	private Airport departure; 
	private Airport arrival; 
	
	//testing code
	public static void main(String[] args) {
		
	}
	
	/*
	 * FlightReservation
	 * 
	 * constructor that initializes a reservation
	 * @param String input Name for reservation
	 * @param Airport one Departure
	 * @param Airport two Arrival
	 */
	public FlightReservation(String input, Airport one, Airport two) {
		super(input);
		if(one.equals(two) == true) {
			throw new IllegalArgumentException("Both airports can not be the same");
		}
		else {
			this.departure = one;
			this.arrival = two;
		}
	}
	
	/*
	 * getCost
	 * 
	 * returns cost of the reservation
	 * @return int cost The cost in cents
	 */
	public int getCost() {
		double cost, distance, fees; 
		distance = (double) Airport.getDistance(this.departure, this.arrival);
		cost = distance/167.52 * 124;
		fees = this.departure.getFees() + this.arrival.getFees(); 
		cost += fees; 
		cost += 5375; 
		cost = Math.ceil(cost); 
		return (int) cost; 
	}

	@Override
	public boolean equals(Object input) {
		if(this == input) {
			return true; 
		}
		else if(!(input instanceof FlightReservation)) {
			return false;
		}
		else {
			FlightReservation flight = (FlightReservation) input;
			String name = reservationName();
			String flightName = flight.reservationName();
			if (name == null || flightName == null) {
				return false;
			}
			else if (this.departure == null || this.arrival == null || flight.departure == null || flight.arrival == null) {
				return false;
			}
			else if(name.equalsIgnoreCase(flightName) && this.departure.equals(flight.departure) && this.arrival.equals(flight.arrival)) {
				return true;
			}
		}
		return false;
	}

}
