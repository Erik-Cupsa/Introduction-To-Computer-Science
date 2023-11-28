package assignment1;

public class Basket {
	//declaring private fields 
	private Reservation[] reservations;
	
	/*
	 * Basket
	 * 
	 * constructor that initializes a basket
	 */
	public Basket() { 
		this.reservations = new Reservation[10];
	}
	
	/*
	 * getProducts
	 * 
	 * creates a shallow copy of the array of Reservations
	 */
	public Reservation[] getProducts() {
//		if(this.reservations == null){
//			return new Reservation[0];
//		}
		Reservation[] copy = new Reservation[reservations.length];
		for (int i=0; i < reservations.length; i++){
			if (this.reservations[i] != null)copy[i] = this.reservations[i];
		}
		return copy; 
	}
	
	/*
	 * add
	 *
	 * Adds reservation to end of array
	 * @param Reservation reservation Reservation to be added
	 * @return int size Returns number of reservations
	 */
	public int add(Reservation addedReservation){
		while (true){
			boolean foundNull = false;
			for(int i=0; i<this.reservations.length; i++){
				if(this.reservations[i] ==null) {
					foundNull=true;
					break;
				}
			}
			if(foundNull || this.reservations.length == 0) break;
			this.reservations = resize();
		}
		//creating count
		int count = 0;
		for(int i=0; i<this.reservations.length; i++){
			if(this.reservations[i] ==null) {
				break;
			}
			count++;
		}
		Reservation[] copy = new Reservation[count+1];
		for(int j=0; j<count; j++){
			copy[j] = this.reservations[j];
		}
		copy[count] = addedReservation;
		this.reservations = copy;
		return copy.length;
	}

	/*
	remove

	Removes first occurence of a specified element from the array
	@param Reservation The reservation to check for
	@return boolean Whether the reservation was removed or not
	 */
	public boolean remove(Reservation reservation){
		if(this.reservations.length == 0){
			return false;
		}
		int skip = -1;
		Reservation[] newReservations = new Reservation[reservations.length-1];
		for(int i=0; i<this.reservations.length;i++){
			if(this.reservations[i] != null && this.reservations[i].equals(reservation)){
				skip = i;
				break;
			}
		}
		if(skip == -1) {
			return false;
		}
		else if(skip == this.reservations.length -1) {
			for (int i = 0; i < this.reservations.length - 1; i++) {
				newReservations[i] = this.reservations[i];
			}

			this.reservations = newReservations;
			return true;
		}
		else{
			for (int i = skip; i< this.reservations.length-1; i++){
				this.reservations[i] = this.reservations[i+1];
			}
			return true;
		}
	}

	/*
	clear

	empties the array of reservations of the basket
	 */
	public void clear(){
		Reservation[] cleared = new Reservation[0];
		this.reservations = cleared;
	}

	/*
	getNumOfReservations

	@return The number of reservations in the basket
	 */
	public int getNumOfReservations(){
		if(this.reservations == null){
			return 0;
		}
		return this.reservations.length;
	}

	/*
	getTotalCost

	@return The total cost in cents of all reservations in the basket
	 */
	public int getTotalCost(){
		int total = 0;
		for(int i=0; i<reservations.length; i++){
			total += reservations[i].getCost();
		}
		return total;
	}
	/*
	resize

	resizes the array
	@return Reservation[] The new array
	 */
	private Reservation[] resize(){
		Reservation[] newReservations = new Reservation[this.reservations.length * 2]; // creating new array with added size
		for(int i=0; i<this.reservations.length; i++) {//iterating through all old reservations
			newReservations[i] = this.reservations[i];
		}
		return newReservations;
	}
	
	
}
