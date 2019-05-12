package by.berdysh.java_course.addressbok.tests;

import by.berdysh.java_course.addressbok.model.ContactData;
import by.berdysh.java_course.addressbok.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {


	@BeforeMethod
	public void ensurePreconditions() {
		app.goTo().contactPage();
		if (app.contact().all().size() == 0) {
			app.contact().create(new ContactData()
							.withFirstName("TestName").withLastName("TestLast").withEmail("test@email.com").withMobile("123456789").withGroup("test1"), true);
		}
	}

	@Test
	public void testContactDeletion() {

		app.goTo().contactPage();
		Contacts before = app.contact().all();
		ContactData deletedContact = before.iterator().next();
		app.contact().delete(deletedContact);
		app.goTo().contactPage();
		assertThat(app.contact().count(), equalTo(before.size() - 1));
		Contacts after = app.contact().all();
		assertThat(after, equalTo(before.without(deletedContact)));
	}


}
