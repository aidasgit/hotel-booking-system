package controller;

import java.io.Serializable;
import java.util.Scanner;

import classes.*;
import lists.*;
import menu.Menu;
import storage.FileReadWrite;

public class Controller implements Serializable {
	// reservation list, only not paid reservations are kept
	private ReservationList reservations;
	// payment list, contains history payments also
	private PaymentList payments;
	// all rooms that are owned by hotel
	private RoomList rooms;
	// menu object
	private Menu menu;
	// scanner objec for user input, transient so it wont be saved
	private transient Scanner myScanner = new Scanner(System.in);
	// number of rooms that owned by CIT Hotel
	public static final int NUM_OF_SINGLE = 12;
	public static final int NUM_OF_DOUBLE = 8;
	public static final int NUM_OF_SUITE = 6;

	// Controller constructor
	public Controller() {
		// Creates new object for all reservations
		reservations = new ReservationList();
		// Creates object for Payments
		payments = new PaymentList();
		// Creates room list object
		rooms = new RoomList();
		// Calls inner object to set up rooms
		this.setupSystemRooms();
		// creates menu object and passes itself as to give
		// menu object access to its public methods
		menu = new Menu(this);
	}

	// saves itself to file
	public void saveSystem() {
		FileReadWrite.writeToFile("control.ser", this);
	}

	// inner method to set up certain type of room and amount,
	// and add them rooms attribute
	private void setMultipleRooms(String type, int amount) {
		for (int i = 0; i < amount; i++) {
			this.rooms.addRoom(this.createRoom(type));
		}
	}

	// private method to create one room of certain type
	private Room createRoom(String type) {
		if (type == "single")
			return new SingleRoom();
		else if (type == "double")
			return new DoubleRoom();
		else
			return new Suite();

	}

	// Sets up all rooms that are owned by CIT hotel
	private void setupSystemRooms() {
		this.setMultipleRooms("single", this.NUM_OF_SINGLE);
		this.setMultipleRooms("double", this.NUM_OF_DOUBLE);
		this.setMultipleRooms("suite", this.NUM_OF_SUITE);
		System.out.println("System Rooms have been created and added");
	}

	// Getter for menu
	public Menu getMenu() {
		return this.menu;
	}

	// Getter for rooms
	public RoomList getRoomList() {
		return this.rooms;
	}

	// Getter for reservations
	public ReservationList getReservationList() {
		return this.reservations;
	}

	// Getter for payments
	public PaymentList getPaymentList() {
		return this.payments;
	}

	// Starts menu.showMenu() method
	public void start() {
		menu.showMenu();
	}

	// makes reservation
	public void makeReservation(Room r, Guest g) {
		// creates new reservation object
		Reservation res = new Reservation(r, g);
		// adds it to controller reservations
		this.reservations.addReservation(res);
		// also ads its payment object to payments
		this.payments.add(res.getPayment());
	}

	// cancels reservation
	public void cancelReservation(Reservation r) {
		// removes payments since it won't be paid
		this.payments.remove(r.getPayment());
		// frees occupied room
		r.getRoom().free();
		// removes reservation
		this.reservations.getReservations().remove(r);
	}

	// removes reservation from the list
	public void getPaid(Reservation r) {
		this.reservations.removeReservationById(r.getReservationID());
	}

}
