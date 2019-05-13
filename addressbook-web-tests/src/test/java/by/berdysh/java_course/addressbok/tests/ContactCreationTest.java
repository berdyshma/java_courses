package by.berdysh.java_course.addressbok.tests;

import by.berdysh.java_course.addressbok.model.ContactData;
import by.berdysh.java_course.addressbok.model.Contacts;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {


	@DataProvider
	public Iterator<Object[]> validContacts() {
		List<Object[]> list = new ArrayList<Object[]>();
		list.add(new Object[]{new ContactData().withFirstName("FirstName1").withLastName("LastName1").withMobile("12345").withEmail("test1@test.com").withGroup("test1")});
		list.add(new Object[]{new ContactData().withFirstName("FirstName2").withLastName("LastName2").withMobile("12345").withEmail("test2@test.com").withGroup("test2")});
		list.add(new Object[]{new ContactData().withFirstName("FirstName3").withLastName("LastName3").withMobile("12345").withEmail("test3@test.com").withGroup("test3")});
		return list.iterator();
	}

	@Test(dataProvider = "validContacts")
	public void testContactCreation(ContactData contact) throws Exception {
		app.goTo().contactPage();
		Contacts before = app.contact().all();
		File photo = new File("src/test/resources/photo.jpg");
		app.contact().create(contact, true);
		app.goTo().contactPage();
		assertThat(app.contact().count(), equalTo(before.size() + 1));
		Contacts after = app.contact().all();
		assertThat(after, equalTo(
						before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

	}


}
