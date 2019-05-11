package by.berdysh.java_course.addressbok.tests;

import by.berdysh.java_course.addressbok.model.ContactData;
import org.testng.annotations.Test;

public class ContactPhoneTests extends TestBase{

	@Test

	public void testContactPhones(){
		app.goTo().contactPage();
		ContactData contact = app.contact().all().iterator().next();
		ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
	}

}
