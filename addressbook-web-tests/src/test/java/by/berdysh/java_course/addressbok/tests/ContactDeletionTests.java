package by.berdysh.java_course.addressbok.tests;

import by.berdysh.java_course.addressbok.model.ContactData;
import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

	@Test
	public void testContactDeletion() {
		app.getNavigationHelper().goToContactPage();
		if (!app.getContactHelper().isThereAContact()) {
			app.getContactHelper().createContact(new ContactData("TestName", "TestLast", "test@email.com", "123456789", "test1"),true);
		}
		app.getNavigationHelper().goToContactPage();
		app.getContactHelper().selectContact();
		app.getContactHelper().deleteSelectedContact();
		app.getContactHelper().closeAlert();
		}
}
