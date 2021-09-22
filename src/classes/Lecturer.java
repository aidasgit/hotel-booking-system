
package classes;

//guest child
public class Lecturer extends Guest {
	// private fields
	private int staffNo;
	static final String TYPE = "lecturer";

	// constructor calls parent method passes its values
	// sets staffNo
	public Lecturer(String name, String phone, String address, String email, int no) {
		super(name, phone, address, email);
		this.staffNo = no;
	}

	// getter for type
	@Override
	public String getType() {
		return TYPE;
	}

}
