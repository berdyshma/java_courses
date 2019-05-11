package by.berdysh.java_course.addressbok.tests;

import by.berdysh.java_course.addressbok.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

public class ContactCreationTest extends TestBase {


	@Test
	public void testContactCreation() throws Exception {
		app.goTo().contactPage();
		Set<ContactData> before = app.contact().all();
		ContactData contact = new ContactData()
						.withFirstName("TestName").withLastName("TestLast").withEmail("test@email.com").withMobile("123456789").withGroup("test1");
		app.contact().create(contact, true);
		app.goTo().contactPage();
		Set<ContactData> after = app.contact().all();
		Assert.assertEquals(after.size(), before.size() + 1);

		contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
		before.add(contact);
		Assert.assertEquals(before, after);

	}


}
