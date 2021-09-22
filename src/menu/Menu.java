package menu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import classes.Guest;
import classes.Lecturer;
import classes.Reservation;
import classes.Room;
import classes.Student;
import controller.Controller;

public class Menu implements Serializable {
	// private fields
	private int selection = 0;
	private transient Scanner myScanner;
	private Controller control;
	// guest that methods use to perform actions on him
	private Guest activeGuest;
	// Outer menu string
	public static final String OUTER_MENU = "Menu" + "\n1  -- Display Available Rooms" + "\n2  -- Display Guests"
			+ "\n3  -- Process Reservation" + "\n4  -- Process Payment" + "\n5. -- History" + "\n6  -- Exit";
	// Reservation menu string
	public static final String RESERVATION_MENU = "Menu" + "\n1  -- New Reservation" + "\n2  -- View Reservation"
			+ "\n3  -- Cancel Reservation" + "\n4  -- Return";

	// Constructor ties control object with itself
	public Menu(Controller control) {
		this.control = control;
	}

	// Sets scanner
	public void setScanner(Scanner in) {
		this.myScanner = in;
	}

	// displays outer menu
	public void showMenu() {
		// gets choice 1 - 6
		int choice = this.getValidInput(1, 6, this.OUTER_MENU);
		// passes it to handleInput
		this.handleInput(choice);

	}

	// returns int value( that gets from user) in range min - max
	// displaying menu as a prompt
	public int getValidInput(int min, int max, String menu) {
		// sets flag to false
		boolean validEntry = false;
		int choice = 0;
		// loops until valid input is not received
		while (!validEntry) {
			try {
				// display prompt
				System.out.println(menu);
				// tries to parse users input to int if fails throws exception that will
				// be caught by catch block
				choice = Integer.parseInt(this.myScanner.nextLine());
				// if input was int, check that its in range 1 to amount
				// if its not throws exception to restart the loop
				// otherwise validEntry is set to true and input
				// will be passed to handleInput()
				if (this.isInRange(choice, min, max)) {
					validEntry = true;
				} else
					throw new Exception();
			} catch (Exception ex) {
				// Outputs string that explains why input was incorrect
				System.out.printf("Please enter valid number %d to %d...\n\n", min, max);
			}
		}
		// returns valid choice
		return choice;
	}

	// handles input for outer menu
	private void handleInput(int choice) {
		switch (choice) {
		case 1:
			// displays all available rooms
			this.control.getRoomList().displayRooms(this.control.getRoomList().getAvailableRooms("available"));
			break;
		case 2:
			// displays all reservations
			this.displayReservations();
			break;
		case 3:
			// displays reservation menu
			this.processReservation();
			break;
		case 4:
			// process payments
			this.getPaid();
			break;
		case 5:
			// displays history menu
			this.historyMenu();
			break;
		case 6:
			// saves control to file
			this.control.saveSystem();
			// stops program
			System.exit(-1);
			break;
		}
		// pauses after
		System.out.println("Press Enter to return");
		this.myScanner.nextLine();
		// Shows outer menu
		this.showMenu();
	}

	// Process Reservation menu
	public void processReservation() {
		// String menu = "Reservation Menu\n" + "*********************\n" + "1. New
		// Reservation\n"
		// + "2. View Reservation\n" + "3. Cancel Reservation\n" + "4. Return\n";
		// gets valid input 1-4
		int choice = this.getValidInput(1, 4, this.RESERVATION_MENU);
		// takes action accordingly
		switch (choice) {
		case 1:
			// adds new reservation
			this.addReservation();
			break;
		case 2:
			// finds and shows detailed view of reservation
			this.viewReservation();
			break;
		case 3:
			// cancels reservation
			this.cancelReservation();
			break;
		case 4:
			// returns to outer menu
			return;
		}

	}

	// Cancels reservation
	private void cancelReservation() {
		// finds reservation
		Reservation r = this.findReservation();
		// if reservation was found
		if (r != null) {
			// Displays action and info of reservation
			System.out.println("Reservation Canceled\n" + r);
			// cancels reservation
			this.control.cancelReservation(r);
		}
	}

	// Detail view of reservation
	private void viewReservation() {
		// finds reservation
		Reservation r = this.findReservation();
		// if one found
		if (r != null) {
			// displays guest details and room details
			System.out.println("Reservation Details\n");
			System.out.println(r.getGuest());
			System.out.println(r.getRoom());
		}
	}

	// History menu
	public void historyMenu() {
		// gets user input 1 - 3
		int choice = this.getValidInput(1, 3,
				"History menu\n1. Display all payments\n2. Display top 3 rooms\n3. Return");
		switch (choice) {

		case 1:
			// Show all payments paid and unpaid
			System.out.println(this.control.getPaymentList());
			break;
		case 2:
			// Shows most booked rooms
			System.out.println("Rooms that was booked the most");
			int i = 1;
			for (Room room : this.control.getPaymentList().topThreeRooms())
				System.out.println("Place no " + i++ + " ---Room number - " + room.getRoomNum()
						+ ". Number of times booked - " + room.getTimesBooked());
			break;
		case 3:
			return;
		}
	}

	// display active reservations that are not paid
	public void displayReservations() {
		// if no reservations returns to main menu
		if (this.control.getReservationList().getReservations().isEmpty()) {
			System.out.println("No guests found...");
			return;
		}
		// prompts user for input of what action he/she would like to do.
		int choice = this.getValidInput(1, 4,
				"1. All reservations.\n2. Lecturer's only.\n3. Student's only" + "\n4. Return");

		// mod type of guest
		String mod;
		// if user choose 1 - all; 2 - lecturer or 3 - student
		mod = choice == 1 ? "all" : choice == 2 ? "lecturer" : "student";
		// if user input was 4 returns to main menu
		if (choice == 4)
			return;
		// Prints table headers
		System.out.printf("%6s %12s %30s %15s %15s %10s\n", "Res ID", "Guest Type", "Name", "Room Number", "Room Type",
				"Amount Due");
		// and prints some values of reservation of each reservation in reservation list
		// retrieved
		for (Reservation r : this.control.getReservationList().getReservations(mod)) {
			System.out.printf("%6d %12s %30s %15s %15s %8.2f\n", r.getReservationID(), r.getGuest().getType(),
					r.getGuest().getName(), r.getRoom().getRoomNum(), r.getRoom().getType(),
					r.getPayment().getAmount());
		}
	}

	// finds reservation by name or by id
	public Reservation findReservation() {
		// prompts user to find out which way to find reservation, using id or name of
		// guest
		int choice = this.getValidInput(1, 2, "Find reservation by:\n1.Guest's name\n2.Reservation ID\n");
		// creates ArrayList<Reservation> r
		ArrayList<Reservation> r = new ArrayList<Reservation>();
		// if by name was chosen
		if (choice == 1) {
			// returns all reservations that have same name
			// and assigns it to r
			r = this.control.getReservationList()
					.getReservationsByGuest(this.getValidString("Please enter guests name >>> "));
		} else {
			// if by id gets valid int from user and finds reservation by id
			Reservation res = this.control.getReservationList()
					.findReservationById(this.getValidInt("Please enter reservation ID >>> "));
			// if found adds it to r ArrayList
			if (res != null)
				r.add(res);
		}
		// if r is empty show info that guest not found and returns null
		if (r.isEmpty()) {
			System.out.println("Guest not found...");
			return null;
		}
		// re-using choice variable setting it to 1
		choice = 1;
		// if r has more then one reservation
		if (r.size() > 1) {
			// index number for selecting which reservation is required by the user
			int ind = 1;
			// Cycles through all reservations
			for (Reservation res : r)
				// prints index and reservation details after it
				System.out.println(ind++ + ". " + res);
			// adds last return option in case user changed its mind
			System.out.println(ind + ". Return");
			// gets user to choose which reservation that he/she was looking for
			choice = this.getValidInput(1, r.size() + 1, "Multiple guests under same name, choose one : ");
		}
		// returns users choice or if only one was in r returns the one that was at 0
		// Position
		return r.get(choice - 1);
	}

	// Process payment
	public void getPaid() {
		// finds reservation by name or id
		Reservation r = this.findReservation();
		// if one found
		if (r != null) {
			// prints its details
			System.out.println(r);
			// Prompts user to make sure its correct one
			String uInput = this
					.getValidString("Is this the guest that is paying? (y/n) >>> " + r.getPayment().getAmount());
			// if user input something starting with 'y' or 'Y'
			// calls control.getPaid(r) and passes reservation chosen by the user
			if (uInput.toLowerCase().charAt(0) == 'y') {
				this.control.getPaid(r);
				// displays msg that info was received
				System.out.println("Payment Received...\nThank you.");
			}
		}
	}

	// adds new reservation also gets all input from std
	public void addReservation() {
		// prompts for what type of guest it will be lecturer or student
		int choice = this.getValidInput(1, 2, "Type of Guest\n1.Lecturer\n2.Student");
		// sets type accordingly
		String type = choice == 1 ? "lecturer" : "student";
		// creates new guest using std input from user
		// and depending of what type guest it is
		this.activeGuest = getGuestData(type);
		// prompts user to choose what type of room required
		int roomChoice = this.getValidInput(1, 3, "Type of Room required\n1.Singe\n2.Double\n3.Suite");
		switch (roomChoice) {
		case 1:
			type = "single";
			break;
		case 2:
			type = "double";
			break;
		case 3:
			type = "suite";
			break;
		}
		// Attempts to reserve a room of that type. If it was succesfull that room
		// handle is passed to r
		Room r = this.control.getRoomList().reserveRoom(type);
		// if room was available
		if (r != null) {
			// displays successful msg and info about a room
			System.out.printf("%s room was succesfully reserved...\n",
					type.substring(0, 1).toUpperCase() + type.substring(1));
			this.control.makeReservation(r, this.activeGuest);
		} else
			// otherwise display info that room of that type is not available
			System.out.println("That type of room is not available\n");
	}

	// takes number and checks if its min - max range inclusive
	private boolean isInRange(int number, int min, int max) {
		if ((number >= min) && (number <= max))
			return true;
		else {
			return false;
		}
	}

	// creates student or lecturer and returns it as a guest object
	public Guest getGuestData(String type) {
		// gets guest info from std input
		String name = this.getValidString("Please enter guests name:");
		String phone = this.getValidString("Please enter guests phone number:");
		String address = this.getValidString("Please enter address:");
		String email = this.getValidString("Please enter email:");
		// creates reference of type Guest
		Guest g;
		if (type == "lecturer") {
			int number = this.getValidInt("Please enter Staff id: ");
			// if lecturer assigns new lecturer object to it
			g = new Lecturer(name, phone, address, email, number);
		} else {
			int number = this.getValidInt("Please enter Student id: ");
			// if student assigns new student object to it
			g = new Student(name, phone, address, email, number);
		}
		return g;
	}

	// this method makes sure string is not empty
	private String getValidString(String promt) {
		System.out.println(promt);
		String output = myScanner.nextLine();
		while (output.length() < 1) {
			System.out.println("String can't be an empty string...");
			output = myScanner.nextLine();
		}
		return output;
	}

	// this method set private because its for inner working only
	// takes string as parameter to display a prompt for user
	// and will loop while gets valid int
	// then returns it
	private int getValidInt(String promt) {
		int i = 0;
		System.out.println(promt);
		while (!myScanner.hasNextInt()) {
			System.out.print("Choice must be a digit...\nPlease try again >>> ");
			myScanner.next();
		}
		i = myScanner.nextInt();
		// this needs to be here to get rid of \n that nextInt() don't use
		myScanner.nextLine();
		return i;
	}

}
