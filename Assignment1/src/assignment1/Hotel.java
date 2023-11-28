package assignment1;

public class Hotel {
	//declaring private fields 
	private String name;
	private Room[] rooms; 
	
	//main method used for testing
	public static void main(String[] args) {
		
	}
	
	/*
	 * Hotel
	 * 
	 * constructor that initializes a Hotel
	 * @param String name Name of hotel
	 * @param Room[] rooms Rooms for the hotel
	 */
	public Hotel(String name, Room[] rooms) {
		this.name = name; 
		Room[] copy = new Room[rooms.length]; // creating new array of rooms to make deep copy
		for(int i=0; i<rooms.length; i++) {//iterating through all rooms
			copy[i] = new Room(rooms[i]);
		}
		this.rooms = copy; 
	}
	
	/*
	 * reserveRoom
	 * 
	 * Reserves a room
	 * @param String type Type of room
	 * @return int price Price of the room
	 */
	public int reserveRoom(String type) {
		//converting input to lower case for comparison
		type = toLowerCase(type); 
		Room room = Room.findAvailableRoom(this.rooms, type);
		if(room == null) { // if no room found
			throw new IllegalArgumentException("There is no room of the specified type available");
		}
		else { // if room found
			room.changeAvailability(); // changing the availability
			return room.getPrice(); // returning price
		}
	}
	
	/*
	 * cancelRoom
	 * 
	 * Cancelling a room
	 * @param String type Type of room
	 * @return boolean Whether operation was successful
	 */
	public boolean cancelRoom(String type) {
		//converting input to lower case for comparison
		if (type != null) type = toLowerCase(type);
		return Room.makeRoomAvailable(this.rooms, type); 
	}
	
	/*
	 * toLowerCase
	 * 
	 * converts a string to all lower case characters
	 * @param String a The string to convert
	 * @return String out The lower case version of the String
	 */
	private static String toLowerCase(String a) {
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
