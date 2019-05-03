package by.berdysh.java_course.addressbok.tests;

import by.berdysh.java_course.addressbok.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {


	@BeforeMethod
	public void ensurePreconditions(){
		app.getNavigationHelper().goToContactPage();
		if (!app.getContactHelper().isThereAContact()) {
			app.getContactHelper().createContact(new ContactData("TestName", "TestLast", "test@email.com", "123456789", "test1"), true);
		}
	}

	@Test
	public void testContactModification() {
		List<ContactData> before = app.getContactHelper().getContactList();
		ContactData contact = new ContactData("TestName", "TestLast", "test@email.com", "123456789", "null");
		app.getNavigationHelper().goToContactPage();
		app.getContactHelper().modifyContact(contact);
		app.getNavigationHelper().goToContactPage();
		List<ContactData> after = app.getContactHelper().getContactList();
		Assert.assertEquals(after.size(), before.size());

		Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
		before.sort(byId);
		after.sort(byId);
		Assert.assertEquals(before, after);

	}


}
