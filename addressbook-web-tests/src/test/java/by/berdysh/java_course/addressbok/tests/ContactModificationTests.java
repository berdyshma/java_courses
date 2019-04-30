package by.berdysh.java_course.addressbok.tests;

import by.berdysh.java_course.addressbok.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {
	@Test
	public void testContactModification() {
		app.getNavigationHelper().goToContactPage();
		if (!app.getContactHelper().isThereAContact()) {
			app.getContactHelper().createContact(new ContactData("TestName", "TestLast", "test@email.com", "123456789", "test1"), true);
		}
		List<ContactData> before = app.getContactHelper().getContactList();
		app.getNavigationHelper().goToContactPage();
		app.getContactHelper().initContactModification();
		ContactData contact = new ContactData("TestName", "TestLast", "test@email.com", "123456789", "null");
		app.getContactHelper().fillContactForm(contact, false);
		app.getContactHelper().submitContactModification();
		app.getNavigationHelper().goToContactPage();
		List<ContactData> after = app.getContactHelper().getContactList();
		Assert.assertEquals(after.size(), before.size());


		Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));

	}
}
