package by.berdysh.java_course.addressbok.model;

import java.util.Objects;

public class ContactData {


	public void setId(int id) {
		this.id = id;
	}

	public int id;
	public final String firstName;
	public final String lastName;
	public String email;
	public String mobile;
	private String group;

	public ContactData(int id,String firstName, String lastName, String email, String mobile, String group) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobile = mobile;
		this.group = group;
	}

	public ContactData(int id,String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;

	}

	public int getId() {
		return id;
	}



	public ContactData(String firstName, String lastName, String email, String mobile, String group) {
		this.id = Integer.MAX_VALUE;
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

	@Override
	public String toString() {
		return "ContactData{" +
						"id='" + id + '\'' +
						", firstName='" + firstName + '\'' +
						", lastName='" + lastName + '\'' +
						", email='" + email + '\'' +
						", mobile='" + mobile + '\'' +
						", group='" + group + '\'' +
						'}';
	}
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ContactData that = (ContactData) o;
		return Objects.equals(firstName, that.firstName) &&
						Objects.equals(lastName, that.lastName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, lastName);
	}

}
