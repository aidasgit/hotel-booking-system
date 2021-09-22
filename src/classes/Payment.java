package classes;

import java.io.Serializable;

public class Payment implements Serializable {
	// hotel room reference
	private Room room;
	// guest reference
	private Guest guest;
	// is paid
	private boolean isPaid;
	// amount depending of guest. 10% discount for lecturer
	private double amount;

	public Payment(Room room, Guest guest) {
		// sets default values
		this.room = room;
		this.guest = guest;
		this.isPaid = false;
		// if guest is lecturer sets discount to 0.1
		double discount = guest.getType() == "lecturer" ? 0.1 : 0.0;
		// sets amount considering of a guest
		this.amount = room.getRate() * room.getNumberOccupants()
				- room.getRate() * room.getNumberOccupants() * discount;
	}

	// gets paid sets paid to true and free's the room
	public void getPaid() {
		this.isPaid = true;
		this.room.free();
	}

	// getter for isPaid
	public boolean isPaid() {
		return this.isPaid;
	}

	// getter for amount
	public double getAmount() {
		return this.amount;
	}

	// Details output for payment
	@Override
	public String toString() {
		return " Guest name : " + guest.getName() + ", " + guest.getType() + ". Room number :" + room.getRoomNum()
				+ " ,Amount - " + this.amount + " ,Status - " + (this.isPaid() ? "paid" : "not-paid") + "\n";
	}

	// getter for room
	public Room getRoom() {
		return this.room;
	}

	// getter for guest
	public Guest getGuest() {
		return this.guest;
	}

}
