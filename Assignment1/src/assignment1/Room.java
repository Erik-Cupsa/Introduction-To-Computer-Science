package assignment1;

public class Room {
	//declaring private fields 
	private String type; 
	private int price; 
	private boolean available; 
	
	//main method used for testing
	public static void main(String[] args) {
		Room one = new Room("double");
		Room two = new Room(one);
//		two.changeAvailability();
//		one.changeAvailability();
		Room[] rooms = {one,two};
		System.out.println(makeRoomAvailable(rooms, "double"));
	}
	
	/*
	 * Room
	 * 
	 * constructor that initializes a Room
	 * @param String input Input of type of room
	 */
	public Room(String input) { 
		//converting input to lower case for comparison
		input = toLowerCase(input); 
		if (input.equals("double")) { //if room is double
			//creating Room of with double parameters
			this.type = input; 
			this.price = 9000; 
			this.available = true; 
		}
		else if(input.equals("queen")) { // if room is queen
			//creating Room with queen parameters
			this.type = input; 
			this.price = 11000; 
			this.available = true; 
		}
		else if(input.equals("king")) { // if room is king
			//creating Room with king parameters
			this.type = input; 
			this.price = 15000; 
			this.available = true; 
		}
		else { //if none of the options, throw exception
			throw new IllegalArgumentException("No room of such type can be created"); 
		}
	}
	
	/*
	 * Room
	 * 
	 * constructor that copies a Room
	 * @param Room room Room to be copied
	 */
	public Room(Room room) {
		//storing values 
		this.type = room.type; 
		this.price = room.price; 
		this.available = room.available; 
	}
	
	/*
	 * getType
	 * 
	 * Retrieves the type of the room
	 * @return String this.type The type of the room
	 */
	public String getType() {
		return this.type; 
	}
	
	/*
	 * getPrice
	 * 
	 * Retrieves the price of the room
	 * @return int this.price The integer price in cents of the room
	 */
	public int getPrice() {
		return this.price; 
	}
	
	/*
	 * changeAvailability
	 * 
	 * changes the value stored in Room to opposite
	 */
	public void changeAvailability() {
		this.available = ! this.available;
	}
	
	/*
	 * findAvailableRoom
	 * 
	 * finding an available room 
	 * @param Room[] rooms Array of Rooms
	 * @param String type Type of the room
	 * @return Room firstAvailable First available room in the array of indicated type
	 */
	public static Room findAvailableRoom(Room[] rooms, String type) {
		//converting input to lower case for comparison
		type = toLowerCase(type); 
		for(int i=0; i<rooms.length; i++) { //iterating through all rooms
			if(rooms[i] != null && rooms[i].type.equals(type) && rooms[i].available == true) { //if room is of same type and available
				return rooms[i]; //return the room
			}
		}
		return null; //else return null
	}
	
	/*
	 * makeRoomAvailable
	 * 
	 * Finding first unavailable room of a certain type and making it available
	 * @param Room[] rooms Array of Rooms
	 * @param String type Type of the room
	 * @return boolean Whether the method was successful or not
	 */
	public static boolean makeRoomAvailable(Room[] rooms, String type) {
		//converting input to lower case for comparison
		type = toLowerCase(type); 
		for(int i=0; i<rooms.length; i++) { //iterating through all rooms
			if(rooms[i] != null && rooms[i].type.equals(type) && rooms[i].available == false) { //if room is of same type and unavailable
				rooms[i].changeAvailability(); 
				return true; //if one found
			}
		}
		return false; //if none found 
	}
	
	/*
	 * toLowerCase
	 * 
	 * converts a string to all lower case characters
	 * @param String a The string to convert
	 * @return String out The lower case version of the String
	 */
	private static String toLowerCase(String a) {
		//removing all spaces from the input
		String out = "";
		if(a != null){
			a = a.replaceAll(" ", "");
			out = a; // initializing output with input string
			for(int i=0; i<a.length(); i++) { //iterating through all characters in String
				if(a.charAt(i) >= 65 && a.charAt(i) <= 90) { //if character is an upper case letter
					out = out.replace(a.charAt(i),(char) (a.charAt(i) + 32)) ; // convert to lower case
				}
			}
		}

		return out; //return new string in lower case
	}
}
