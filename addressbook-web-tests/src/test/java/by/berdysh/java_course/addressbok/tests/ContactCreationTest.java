package by.berdysh.java_course.addressbok.tests;

import by.berdysh.java_course.addressbok.model.ContactData;
import by.berdysh.java_course.addressbok.model.Contacts;
import org.testng.annotations.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {


	@Test
	public void testContactCreation() throws Exception {
		app.goTo().contactPage();
		Contacts before = app.contact().all();
		File photo = new File("src/test/resources/photo.jpg");
		ContactData contact = new ContactData()
						.withFirstName("TestName").withLastName("TestLast").withAddress("Address 5/55")
						.withEmail("test@email.com").withEmail2("test2@email.com").withEmail3("test3@email.com")
						.withMobile("+375(29)").withHomePhone("12 34 56").withWorkPhone("98-76-54")
						.withGroup("test1").withPhoto(photo);

		app.contact().create(contact, true);
		app.goTo().contactPage();
		assertThat(app.contact().count(), equalTo(before.size() + 1));
		Contacts after = app.contact().all();
		assertThat(after, equalTo(
						before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

	}


}
