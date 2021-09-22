package lists;

import java.io.Serializable;
import java.util.ArrayList;

import classes.Room;

public class RoomList implements Serializable {
	// ArrayList to store all rooms
	private ArrayList<Room> rooms;
	// Amount of available rooms
	private int singleAvailable, doubleAvailable, ensuiteAvailable;

	// constructor
	public RoomList() {
		rooms = new ArrayList<Room>();
	}

	// Adds a room
	public void addRoom(Room room) {
		rooms.add(room);
	}

	// gets rooms
	public ArrayList<Room> getRooms() {
		return this.rooms;
	}

	// returns rooms available or unavailable
	// or available of type double, single or suite
	public ArrayList<Room> getAvailableRooms(String type) {
		ArrayList<Room> aRooms = new ArrayList<Room>();
		for (Room r : this.rooms) {
			if (r.isAvailable() && type == "available")
				aRooms.add(r);
			else if (!r.isAvailable() && type == "unavailable")
				aRooms.add(r);
			else if (r.isAvailable() && r.getNumberOccupants() == 2 && type == "double")
				aRooms.add(r);
			else if (r.isAvailable() && r.getNumberOccupants() == 1 && type == "single")
				aRooms.add(r);
			else if (r.isAvailable() && r.getNumberOccupants() == 3 && type == "suite")
				aRooms.add(r);

		}
		return aRooms;
	}

	// displays how many available rooms are there
	public void displayRooms(ArrayList<Room> rooms) {
		this.update();
		System.out.println("Number of single rooms - " + this.singleAvailable);
		System.out.println("Number of double rooms - " + this.doubleAvailable);
		System.out.println("Number of suite rooms - " + this.ensuiteAvailable);

	}

	// reserves room of certain type [single|double|suite]
	public Room reserveRoom(String type) {
		// calls update that checks how many rooms are available
		this.update();
		// creates reference to room
		Room r;
		// depending of what type of room is needed
		// checks if there is one available
		// and if there is attaches 'r' handle to it
		// otherwise null is set to r
		if (type == "single" && this.singleAvailable > 0) {
			r = this.getAvailableRooms(type).get(0);
		} else if (type == "double" && this.doubleAvailable > 0) {
			r = this.getAvailableRooms(type).get(0);
		} else if (type == "suite" && this.ensuiteAvailable > 0) {
			r = this.getAvailableRooms(type).get(0);
		} else
			return null;
		// reserves a room if one was available
		r.reserve();
		// and returns handle to it
		return r;
	}

	// updates instance variables of how many rooms is available
	public void update() {
		// clears values
		this.singleAvailable = 0;
		this.doubleAvailable = 0;
		this.ensuiteAvailable = 0;
		// cycles through all the rooms and increases values
		// of room type accordingly
		for (Room r : rooms) {
			if (r.isAvailable() && r.getNumberOccupants() == 1)
				this.singleAvailable++;
			else if (r.isAvailable() && r.getNumberOccupants() == 2)
				this.doubleAvailable++;
			else if (r.isAvailable() && r.getNumberOccupants() == 3)
				this.ensuiteAvailable++;

		}
	}
}
