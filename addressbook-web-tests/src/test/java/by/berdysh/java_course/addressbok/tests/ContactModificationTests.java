package by.berdysh.java_course.addressbok.tests;

import by.berdysh.java_course.addressbok.model.ContactData;
import by.berdysh.java_course.addressbok.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {


	@BeforeMethod
	public void ensurePreconditions() {
		app.goTo().contactPage();
		if (app.contact().all().size() == 0) {
			app.contact().create(new ContactData().withFirstName("TestName").withLastName("TestLast").withEmail("test@email.com").withMobile("123456789").withGroup("test1"), true);
		}
	}

	@Test
	public void testContactModification() {
		Contacts before = app.contact().all();
		ContactData modifiedContact = before.iterator().next();
		ContactData contact = new ContactData()
						.withId(modifiedContact.getId()).withFirstName("TestName").withLastName("TestLast").withEmail("test@email.com").withMobile("123456789").withGroup("null");
		app.goTo().contactPage();
		app.contact().modify(contact);
		app.goTo().contactPage();
		Contacts after = app.contact().all();
		assertEquals(after.size(), before.size());
		assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));

	}


}
