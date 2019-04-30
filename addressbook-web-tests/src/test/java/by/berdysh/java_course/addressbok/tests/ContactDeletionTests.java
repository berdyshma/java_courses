package by.berdysh.java_course.addressbok.tests;

import by.berdysh.java_course.addressbok.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

	@Test
	public void testContactDeletion() {
		app.getNavigationHelper().goToContactPage();
		if (!app.getContactHelper().isThereAContact()) {
			app.getContactHelper().createContact(new ContactData("TestName", "TestLast", "test@email.com", "123456789", "test1"),true);
		}
		app.getNavigationHelper().goToContactPage();
		int before = app.getContactHelper().getContactCount();
		app.getContactHelper().selectContact(before - 1);
		app.getContactHelper().deleteSelectedContact();
		app.getContactHelper().closeAlert();
		app.getNavigationHelper().goToContactPage();
		int after = app.getContactHelper().getContactCount();
		Assert.assertEquals(after, before - 1);
		}
}
