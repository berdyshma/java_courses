package by.berdysh.java_course.addressbok.model;

import java.util.Objects;

public class ContactData {


	public int id;
	public String firstName;
	public String lastName;
	public String email;
	public String mobile;
	public String group;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ContactData that = (ContactData) o;
		return id == that.id &&
						Objects.equals(firstName, that.firstName) &&
						Objects.equals(lastName, that.lastName);
	}

		public ContactData withId(int id) {
		this.id = id;
		return this;
	}

	public ContactData withFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public ContactData withLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public ContactData withEmail(String email) {
		this.email = email;
		return this;
	}

	public ContactData withMobile(String mobile) {
		this.mobile = mobile;
		return this;
	}

	public ContactData withGroup(String group) {
		this.group = group;
		return this;
	}


	public int getId() {
		return id;
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
	public int hashCode() {
		return Objects.hash(id, firstName, lastName);
	}

}
