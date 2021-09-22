package classes;

import java.io.Serializable;

import lists.PaymentList;

public class Reservation implements Serializable {
	// Private fields
	private Room room;
	private Guest guest;
	private Payment payment;
	public static int RESERVATION_NUM = 1;
	public int resNum;

	// Constructor
	public Reservation(Room room, Guest guest) {
		payment = new Payment(room, guest);
		resNum = RESERVATION_NUM;
		RESERVATION_NUM++;
		this.room = room;
		this.guest = guest;
	}

	// getters
	public int getReservationID() {
		return this.resNum;
	}

	public Guest getGuest() {
		return this.guest;
	}

	public Payment getPayment() {
		return this.payment;
	}

	public Room getRoom() {
		return this.room;
	}

	//setter
	public void setReservationID(int id) {
		this.resNum = id;
	}

	// Overridden toString method
	@Override
	public String toString() {
		return this.resNum + " Guest name : " + guest.getName() + ", " + guest.getType() + ". Room number :"
				+ room.getRoomNum();
	}
}
