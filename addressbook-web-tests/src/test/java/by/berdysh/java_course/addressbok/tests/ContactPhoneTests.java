package by.berdysh.java_course.addressbok.tests;

import by.berdysh.java_course.addressbok.model.ContactData;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase{

	@Test

	public void testContactPhones(){
		app.goTo().contactPage();
		ContactData contact = app.contact().all().iterator().next();
		ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
		assertThat(contact.getHomePhone(), equalTo(contactInfoFromEditForm.getHomePhone()));
		assertThat(contact.getMobile(), equalTo(contactInfoFromEditForm.getMobile()));
		assertThat(contact.getWorkPhone(), equalTo(contactInfoFromEditForm.getWorkPhone()));
	}

}
