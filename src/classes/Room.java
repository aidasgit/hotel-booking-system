package classes;

import java.io.Serializable;

//Abstract class for hotel rooms
public abstract class Room implements Serializable, Comparable {
	// sets room number counter as static
	public static int ROOM_NUM = 1;
	// room number
	private int roomNum;
	// available or not
	private boolean available;
	// times it was booked
	private int timesBooked;

	// constructor initiates it's attributes
	public Room() {
		this.timesBooked = 0;
		this.available = true;
		roomNum = ROOM_NUM;
		// increases static variable so next room to be created would have its room
		// number increased
		ROOM_NUM++;
	}

	// Interface comparable requires this method implemented
	// Its for sorting by the amount of times booked
	// used in history menu
	@Override
	public int compareTo(Object r) {
		return ((Room) r).getTimesBooked() - this.getTimesBooked();
	}

	// returns true if room is available
	public boolean isAvailable() {
		return available;
	}

	// reserves room
	public void reserve() {
		// sets available to false
		this.available = false;
		// increases timesBooked
		this.timesBooked++;
	}

	// makes room available
	public void free() {
		this.available = true;
	}

	// calculates nightly rate
	public double calculateNightlyRate() {
		return this.getRate() * this.getNumberOccupants();
	}

	// abstract method get max number of occupants
	public abstract int getNumberOccupants();

	// abstract method that gets pp rate
	public abstract double getRate();

	// getter for roomNum
	public int getRoomNum() {
		return this.roomNum;
	}

	// getter for timesBooked
	public int getTimesBooked() {
		return this.timesBooked;
	}

	// abstract method that gets room Type used in toString()
	public abstract String getType();

	// detailed information toString
	@Override
	public String toString() {
		String s = "Room Details\n*******************";
		s += "\nRoom number - " + this.getRoomNum();
		s += "\nRoom type - " + this.getType();
		s += "\nRoom rate pp - €" + this.getRate();
		s += "\nMax no of Occupants - " + this.getNumberOccupants();
		return s;
	}
}
