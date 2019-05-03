package by.berdysh.java_course.addressbok.tests;

import by.berdysh.java_course.addressbok.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase {


	@Test
	public void testContactCreation() throws Exception {
		app.getNavigationHelper().goToContactPage();
		List<ContactData> before = app.getContactHelper().getContactList();
		ContactData contact = new ContactData("TestName", "TestLast", "test@email.com", "123456789", "test1");
		app.getContactHelper().createContact(contact,true);
		app.getNavigationHelper().goToContactPage();
		List<ContactData> after = app.getContactHelper().getContactList();
		Assert.assertEquals(after.size(), before.size() + 1);

    before.add (contact);
		Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
		before.sort(byId);
		after.sort(byId);
		Assert.assertEquals(before,after);

	}


}
