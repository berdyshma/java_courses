package by.berdysh.java_course.addressbok.tests;

import by.berdysh.java_course.addressbok.model.ContactData;
import by.berdysh.java_course.addressbok.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDataCheck extends TestBase {


	@BeforeMethod
	public void ensurePreconditions() {
		app.goTo().contactPage();
		Groups groups = app.db().groups();
		if (app.contact().all().size() == 0) {
			app.contact().create(new ContactData()
							.withFirstName("TestName").withLastName("TestLast").withAddress("Address 5/55")
							.withEmail("test@email.com").withEmail2("test2@email.com").withEmail3("test3@email.com")
							.withMobile("+375(29)").withHomePhone("12 34 56").withWorkPhone("98-76-54").inGroup(groups.iterator().next()), true);
		}
	}

	@Test

	public void testContactPhones() {
		app.goTo().contactPage();
		ContactData contact = app.contact().all().iterator().next();
		ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
		assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
		assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
		assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
	}

	private String mergeEmails(ContactData contact) {
		return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
						.stream().filter((s) -> !s.equals(""))
						.collect(Collectors.joining("\n"));
	}

	private String mergePhones(ContactData contact) {
		return Arrays.asList(contact.getHomePhone(), contact.getMobile(), contact.getWorkPhone())
						.stream().filter((s) -> !s.equals(""))
						.map(ContactDataCheck::cleaned)
						.collect(Collectors.joining("\n"));

	}

	public static String cleaned(String phone) {
		return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
	}

}
