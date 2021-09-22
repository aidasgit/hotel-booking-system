package classes;

import java.io.Serializable;

public class DoubleRoom extends Room implements Serializable {
	// max number of occupants
	public static final int NO_OF_OCCUPANTS = 2;
	// rate pp per night
	private double rate = 100;
	// type of room
	private String type = "double";

	// getters
	@Override
	public int getNumberOccupants() {
		return this.NO_OF_OCCUPANTS;
	}

	public double getRate() {
		return this.rate;
	}

	public String getType() {
		return this.type;
	}
}
