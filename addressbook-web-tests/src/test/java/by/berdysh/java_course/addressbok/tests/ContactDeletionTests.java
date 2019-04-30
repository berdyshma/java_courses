package by.berdysh.java_course.addressbok.tests;

import by.berdysh.java_course.addressbok.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ContactDeletionTests extends TestBase {

	@Test
	public void testContactDeletion() {
		app.getNavigationHelper().goToContactPage();
		if (!app.getContactHelper().isThereAContact()) {
			app.getContactHelper().createContact(new ContactData("TestName", "TestLast", "test@email.com", "123456789", "test1"),true);
		}
		app.getNavigationHelper().goToContactPage();
		List<ContactData> before = app.getContactHelper().getContactList();
		app.getContactHelper().selectContact(before.size() - 1);
		app.getContactHelper().deleteSelectedContact();
		app.getContactHelper().closeAlert();
		app.getNavigationHelper().goToContactPage();
		List<ContactData> after = app.getContactHelper().getContactList();
		Assert.assertEquals(after.size(), before.size()- 1);
		}
}
