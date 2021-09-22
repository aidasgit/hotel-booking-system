package main;

import java.util.Scanner;

import classes.Reservation;
import classes.Room;
import controller.Controller;
import storage.FileReadWrite;

public class Main {

	public static void main(String[] args) {
		// creating control reference
		Controller control = null;
		// reading controller object from file
		control = (Controller) FileReadWrite.readFromFile("control.ser");
		// if nothing was in the file
		if (control == null) {
			System.out.println("Starting new system");
			// starting new controller object
			control = new Controller();
		} else {
			// if there was an object saved
			// Sets Resevation.RESERVATION_NUM to the amount of reservations
			// in file +1. To keep reservations number unic.
			System.out.println("Loading system from file");
			Reservation.RESERVATION_NUM = control.getReservationList().getReservations().size() + 1;
		}
		// call control.getmenu
		control.getMenu().setScanner(new Scanner(System.in));
		// starts control.start()
		control.start();
	}

}
