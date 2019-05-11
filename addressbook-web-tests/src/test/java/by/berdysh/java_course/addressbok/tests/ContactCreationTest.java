package by.berdysh.java_course.addressbok.tests;

import by.berdysh.java_course.addressbok.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase {


	@Test
	public void testContactCreation() throws Exception {
		app.goTo().contactPage();
		List<ContactData> before = app.contact().list();
		ContactData contact = new ContactData()
						.withFirstName("TestName").withLastName("TestLast").withEmail("test@email.com").withMobile("123456789").withGroup("test1");
		app.contact().create(contact, true);
		app.goTo().contactPage();
		List<ContactData> after = app.contact().list();
		Assert.assertEquals(after.size(), before.size() + 1);

		before.add(contact);
		Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
		before.sort(byId);
		after.sort(byId);
		Assert.assertEquals(before, after);

	}


}
