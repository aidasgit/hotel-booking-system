package lists;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import classes.Payment;
import classes.Room;

public class PaymentList implements Serializable {
	// ArrayList that stores all payments
	private ArrayList<Payment> payments;

	// constructor
	public PaymentList() {
		payments = new ArrayList<Payment>();
	}

	// getter of all payments
	public ArrayList<Payment> getAllPayments() {
		return payments;
	}

	// gets all unpaid payments
	public ArrayList<Payment> getUnpaid() {
		ArrayList<Payment> p = new ArrayList<Payment>();
		for (Payment pay : payments) {
			if (!pay.isPaid())
				p.add(pay);
		}
		return p;
	}

	// gets all paid payments
	public ArrayList<Payment> getPaid() {
		ArrayList<Payment> p = new ArrayList<Payment>();
		for (Payment pay : payments) {
			if (pay.isPaid())
				p.add(pay);
		}
		return p;
	}

	// adds new payment
	public void add(Payment p) {
		this.payments.add(p);
	}

	// Overridden toString()
	@Override
	public String toString() {
		String s = "";
		for (Payment p : this.payments)
			s += p.toString();
		return s;
	}

	// Outputs up to 3 rooms that was booked the most
	// in payments
	public List<Room> topThreeRooms() {
		// Creates rooms List
		List<Room> rooms = new ArrayList<Room>();
		// Puts all rooms into it
		for (Payment p : this.payments)
			rooms.add(p.getRoom());
		// Gets rid of all duplicate rooms
		rooms = rooms.stream().distinct().collect(Collectors.toList());
		// Sorts in descending order
		Collections.sort(rooms);
		// if rooms have more then 3 rooms in that list,
		// keeps only 1st 3
		rooms = rooms.size() < 3 ? rooms : rooms.subList(0, 3);
		return rooms;
	}

	// removes payment
	public void remove(Payment p) {
		this.payments.remove(p);
	}
}
