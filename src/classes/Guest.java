package classes;

import java.io.Serializable;

//Abstract guest class
public abstract class Guest implements Serializable {
	// private fields
	private String name;
	private String phone;
	private String email;
	private String address;

	// constructor
	public Guest(String name, String phone, String address, String email) {
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.email = email;
	}

	// setters and getters
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	// abstract method that returns guest type
	public abstract String getType();

	@Override
	public String toString() {
		String s = "Guest Details\n*******************\n";
		s += "Name - " + this.getName();
		s += "\nAddress - " + this.getAddress();
		s += "\nPhone no - " + this.getPhone();
		s += "\nType -" + this.getType();
		return s;
	}

}
