package classes;

public class Suite extends Room {
	// class attributes
	public static final int NO_OF_OCCUPANTS = 3;
	private double rate = 150;
	private String type = "suite";

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
