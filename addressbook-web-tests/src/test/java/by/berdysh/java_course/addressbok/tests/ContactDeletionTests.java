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
		if (app.db().contacts().size() == 0) {
			app.goTo().contactPage();
			app.contact().create(new ContactData()
							.withFirstName("TestName").withLastName("TestLast").withEmail("test@email.com").withMobile("123456789").withGroup("test1"), true);
		}
	}

	@Test
	public void testContactDeletion() {
		Contacts before = app.db().contacts();
		ContactData deletedContact = before.iterator().next();
		app.goTo().contactPage();
		app.contact().delete(deletedContact);
		app.goTo().contactPage();
		assertThat(app.contact().count(), equalTo(before.size() - 1));
		Contacts after = app.db().contacts();
		assertThat(after, equalTo(before.without(deletedContact)));
	}


}
