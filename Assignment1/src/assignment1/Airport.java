package assignment1;

public class Airport {
	//declaring private fields
	private int x_coordinate; //x-coordinate integer
	private int y_coordinate; //y-coordinate integer
	private int fees; //fees integer
	
	//main method used for testing
	public static void main(String[] args) {
//		Airport one = new Airport(1,2,3); 
//		Airport two = new Airport(7,3,4);
//		System.out.println(getDistance(one,two));
	}
	
	/*
	 * Airport
	 * 
	 * constructor that initializes an Airport
	 * @param int x The x-coordinate
	 * @param int y The y-coordinate
	 * @param int fee The fee
	 */
	public Airport(int x, int y, int fee) {
		//storing values to new Airport
		this.x_coordinate = x; 
		this.y_coordinate = y; 
		this.fees = fee; 
	}
	
	/*
	 * getFees
	 * 
	 * Retrieves the fees of the airports
	 * @return int this.fees The integer fee of the airport
	 */
	public int getFees() {
		return this.fees; 
	}
	
	/*
	 * getDistance
	 * 
	 * Calculates the distance between the two airports
	 * @param Airport one The first airport
	 * @param Airport two The second airport
	 * @ return int distance The calculated distance
	 */
	public static int getDistance(Airport one, Airport two) {
		//initializing variables
		int distance; 
		double temp; 
		
		//computing the distance calculation in 3 steps
		//1. (x1-x2)^2
		temp = Math.pow(one.x_coordinate - two.x_coordinate, 2);
		//2. Add (y1-y2)^2
		temp += Math.pow(one.y_coordinate - two.y_coordinate, 2);
		//3. Square rooting
		temp = Math.sqrt(temp); 
		//rounding up calculation and typecasting to int
		distance = (int) Math.ceil(temp); 
		return distance; 
	}
}
