package by.berdysh.java_course.addressbok.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {

	@XStreamOmitField
	@Id
	@Column (name = "id")
	public int id;


	@Expose
	@Column (name = "firstname")
	public String firstName;

	@Expose
	@Column (name = "lastname")
	public String lastName;

	@Expose
	@Column (name = "email")
	@Type(type = "text")
	public String email;

	@Column (name = "email2")
	@Type(type = "text")
	public String email2;

	@Column (name = "email3")
	@Type(type = "text")
	public String email3;

	@Transient
	public String allEmails;

	@Column (name = "address")
	@Type(type = "text")
	public String address;

	@Expose
	@Column (name = "mobile")
	@Type(type = "text")
	public String mobile;

	@Transient
	public String group;

	@Column (name = "work")
	@Type(type = "text")
	public String workPhone;

	@Column (name = "home")
	@Type(type = "text")
	public String homePhone;

	@Transient
	public String allPhones;

	@Column (name = "deprecated", columnDefinition = "DATETIME")
	public String deprecated;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ContactData that = (ContactData) o;
		return id == that.id &&
						Objects.equals(firstName, that.firstName) &&
						Objects.equals(lastName, that.lastName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, firstName, lastName);
	}

	@Override
	public String toString() {
		return "ContactData{" +
						"id=" + id +
						", firstName='" + firstName + '\'' +
						", lastName='" + lastName + '\'' +
						'}';
	}

	@Column (name = "photo")
	@Type(type = "text")
	public String photo;



	public ContactData withPhoto(File photo) {
		this.photo = photo.getPath();
		return this;
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

	public ContactData withEmail2(String email2) {
		this.email2 = email2;
		return this;
	}

	public ContactData withEmail3(String email3) {
		this.email3 = email3;
		return this;
	}
	public ContactData withAllEmails(String allEmails) {
		this.allEmails = allEmails;
		return this;
	}

	public ContactData withAddress(String address) {
		this.address = address;
		return this;
	}

	public ContactData withMobile(String mobile) {
		this.mobile = mobile;
		return this;
	}

	public ContactData withWorkPhone(String workPhone) {
		this.workPhone = workPhone;
		return this;
	}

	public ContactData withHomePhone(String homePhone) {
		this.homePhone = homePhone;
		return this;
	}

	public ContactData withAllPhones(String allPhones) {
		this.allPhones = allPhones;
		return this;
	}

	public ContactData withGroup(String group) {
		this.group = group;
		return this;
	}

	public File getPhoto() {
		if (photo != null) {
			return new File(photo);
		} else {
			return null;
		}
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

	public String getEmail2() {
		return email2;
	}

	public String getEmail3() {
		return email3;
	}

	public String getAllEmails() {
		return allEmails;
	}

	public String getAddress() {
		return address;
	}

	public String getMobile() {
		return mobile;
	}

	public String getWorkPhone() {
		return workPhone;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public String getAllPhones() {
		return allPhones;
	}

	public String getGroup() {
		return group;
	}

}
