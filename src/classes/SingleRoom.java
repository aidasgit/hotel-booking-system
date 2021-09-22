package classes;

//Abstract room child
public class SingleRoom extends Room {
	// static final max number of occupants
	public static final int NO_OF_OCCUPANTS = 1;
	// Nightly rate pp per night
	private double rate = 75;
	// type of room
	private String type = "single";

	// overridden getter
	@Override
	public int getNumberOccupants() {
		return this.NO_OF_OCCUPANTS;
	}

	// getters
	public double getRate() {
		return this.rate;
	}

	public String getType() {
		return this.type;
	}

}
