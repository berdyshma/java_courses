package by.berdysh.java_course.addressbok.tests;

import by.berdysh.java_course.addressbok.model.ContactData;
import org.testng.annotations.*;

public class ContactCreationTest extends TestBase {


	@Test
	public void testContactCreation() throws Exception {
		app.getContactHelper().createContact(new ContactData("TestName", "TestLast", "test@email.com", "123456789", "test1"),true);

	}


}
