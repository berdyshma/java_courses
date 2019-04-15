package by.berdysh.java_course.addressbok.tests;

import by.berdysh.java_course.addressbok.model.ContactData;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {
	@Test
	public void testContactModification(){
		app.getNavigationHelper().goToContactPage();
		app.getContactHelper().initContactModification();
		app.getContactHelper().fillContactForm(new ContactData("TestName", "TestLast", "test@email.com", "123456789"));
		app.getContactHelper().submitContactModification();


	}
}
