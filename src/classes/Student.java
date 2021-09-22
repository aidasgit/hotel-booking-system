package classes;

//Abstract guest child
public class Student extends Guest {
	// class attributes
	private int studentNo;
	static final String TYPE = "student";

	// constructor
	public Student(String name, String phone, String address, String email, int no) {
		super(name, phone, address, email);
		this.studentNo = no;
	}

	// getter
	@Override
	public String getType() {
		return TYPE;
	}

}
