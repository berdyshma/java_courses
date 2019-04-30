package by.berdysh.java_course.addressbok.tests;

import by.berdysh.java_course.addressbok.model.ContactData;
import by.berdysh.java_course.addressbok.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class ContactCreationTest extends TestBase {


	@Test
	public void testContactCreation() throws Exception {
		app.getNavigationHelper().goToContactPage();
		List<ContactData> before = app.getContactHelper().getContactList();
		app.getContactHelper().createContact(new ContactData("TestName", "TestLast", "test@email.com", "123456789", "test1"),true);
		app.getNavigationHelper().goToContactPage();
		List<ContactData> after = app.getContactHelper().getContactList();
		Assert.assertEquals(after.size(), before.size() + 1);

	}


}
