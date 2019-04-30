package by.berdysh.java_course.addressbok.tests;

import by.berdysh.java_course.addressbok.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {
	@Test
	public void testContactModification() {
		app.getNavigationHelper().goToContactPage();
		if (!app.getContactHelper().isThereAContact()) {
			app.getContactHelper().createContact(new ContactData("TestName", "TestLast", "test@email.com", "123456789", "test1"), true);
		}
		int before = app.getContactHelper().getContactCount();
		app.getNavigationHelper().goToContactPage();
		app.getContactHelper().initContactModification();
		app.getContactHelper().fillContactForm(new ContactData("TestName", "TestLast", "test@email.com", "123456789", "null"), false);
		app.getContactHelper().submitContactModification();
		app.getNavigationHelper().goToContactPage();
		int after = app.getContactHelper().getContactCount();
		Assert.assertEquals(after, before);


	}
}
