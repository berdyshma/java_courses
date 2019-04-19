package by.berdysh.java_course.addressbok.model;

public class ContactData {
	public final String firstName;
	public final String lastName;
	public final String email;
	public final String mobile;
	private String group;

	public ContactData(String firstName, String lastName, String email, String mobile, String group) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobile = mobile;
		this.group = group;
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

	public String getGroup() {
		return group;
	}
}
