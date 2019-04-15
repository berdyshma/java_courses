package by.berdysh.java_course.addressbok.model;

public class ContactData {
	public final String firstName;
	public final String lastName;
	public final String email;
	public final String mobile;

	public ContactData(String firstName, String lastName, String email, String mobile) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobile = mobile;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getMobile() {
		return mobile;
	}
}
