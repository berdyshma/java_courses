package by.berdysh.java_course.addressbok;

public class ContactData {
	private final String firstName;
	private final String lastName;
	private final String email;
	private final String mobile;

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
