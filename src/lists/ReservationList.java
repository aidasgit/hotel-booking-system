
package lists;

import java.io.Serializable;
import java.util.ArrayList;

import classes.Payment;
import classes.Reservation;

public class ReservationList implements Serializable {
	// ArrayList of Reservations
	private ArrayList<Reservation> reservations;

	// Constructor for ReservationList
	public ReservationList() {
		reservations = new ArrayList<Reservation>();
	}

	// Gets all reservations by guest type
	// if type = all all reservations are returned
	// if type = student all students
	// if type = lecturer all lecturers
	public ArrayList<Reservation> getReservations(String mod) {
		ArrayList<Reservation> out = new ArrayList<Reservation>();
		if (mod == "all")
			return this.reservations;
		else {
			for (Reservation r : this.reservations)
				if (r.getGuest().getType() == mod)
					out.add(r);
		}
		return out;
	}

	// overloaded getReservations() without parameters
	public ArrayList<Reservation> getReservations() {
		return this.reservations;
	}

	// finds reservation by the ID
	public Reservation findReservationById(int id) {
		Reservation res = null;
		for (Reservation reserve : reservations) {
			if (reserve.getReservationID() == id) {
				res = reserve;
			}
		}
		return res;
	}

	// finds all reservations that has same name
	// and return ArrayList
	// ignore case
	public ArrayList<Reservation> getReservationsByGuest(String name) {
		ArrayList<Reservation> res = new ArrayList<Reservation>();
		for (Reservation reserve : reservations) {
			if (reserve.getGuest().getName().toLowerCase().equals(name.toLowerCase())) {
				res.add(reserve);
			}
		}
		return res;
	}

	// adds new reservation
	public void addReservation(Reservation r) {
		int id;
		//this bit is to ensure no reservation has same id
		//because if i enter two reservations and then cancel 1st one
		//i save system to file, then reload the next res id would be 2
		//this way we would have duplicate id
		do {
			id = r.getReservationID();
			for ( Reservation res : this.reservations) {
				if ( id == res.getReservationID())
					r.setReservationID(++id);
			}
		}while (r.getReservationID() != id);
		reservations.add(r);
	}

	// removes reservation by id
	public void removeReservationById(int id) {
		// finds reservation
		Reservation r = this.findReservationById(id);
		// gets reservation paid, because when reservation removed only
		// when paid
		r.getPayment().getPaid();
		// and removes this reservation from reservations
		reservations.remove(r);
	}

}
