package by.berdysh.java_course.addressbok.tests;

import by.berdysh.java_course.addressbok.model.ContactData;
import by.berdysh.java_course.addressbok.model.Contacts;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {


	@Test
	public void testContactCreation() throws Exception {
		app.goTo().contactPage();
		Contacts before = app.contact().all();
		ContactData contact = new ContactData()
						.withFirstName("TestName").withLastName("TestLast").withEmail("test@email.com").withMobile("123456789").withGroup("test1");
		app.contact().create(contact, true);
		app.goTo().contactPage();
		assertThat(app.contact().count(), equalTo(before.size() + 1));
		Contacts after = app.contact().all();
		assertThat(after, equalTo(
						before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

	}


}
