package by.berdysh.java_course.addressbok.model;

import java.util.Objects;

public class ContactData {
	public final String firstName;
	public final String lastName;
	public final String email;
	public final String mobile;
	private String group;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ContactData that = (ContactData) o;
		return Objects.equals(firstName, that.firstName) &&
						Objects.equals(lastName, that.lastName) &&
						Objects.equals(email, that.email) &&
						Objects.equals(mobile, that.mobile) &&
						Objects.equals(group, that.group);
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, lastName, email, mobile, group);
	}

	@Override
	public String toString() {
		return "ContactData{" +
						"firstName='" + firstName + '\'' +
						", lastName='" + lastName + '\'' +
						", email='" + email + '\'' +
						", mobile='" + mobile + '\'' +
						", group='" + group + '\'' +
						'}';
	}

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
