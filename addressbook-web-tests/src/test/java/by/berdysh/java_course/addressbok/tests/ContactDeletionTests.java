package by.berdysh.java_course.addressbok.tests;

import by.berdysh.java_course.addressbok.model.ContactData;
import by.berdysh.java_course.addressbok.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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
		Contacts after = app.contact().all();
		assertEquals(after.size(), before.size() - 1);
		assertThat(after, equalTo(before.without(deletedContact)));
	}


}
