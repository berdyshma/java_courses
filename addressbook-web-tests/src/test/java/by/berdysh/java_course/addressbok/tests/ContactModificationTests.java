package by.berdysh.java_course.addressbok.tests;

import by.berdysh.java_course.addressbok.model.ContactData;
import by.berdysh.java_course.addressbok.model.Contacts;
import by.berdysh.java_course.addressbok.model.GroupData;
import by.berdysh.java_course.addressbok.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {


	@BeforeMethod
	public void ensurePreconditions() {
		app.goTo().groupPage();
		if (app.db().groups().size() == 0) {
			app.group().create(new GroupData().withName("test1"));
		}
		app.goTo().contactPage();
		if (app.db().contacts().size() == 0) {
			Groups groups = app.db().groups();
			app.goTo().contactPage();
			app.contact().create(new ContactData()
							.withFirstName("TestName").withLastName("TestLast").withEmail("test@email.com").withMobile("123456789")
							.inGroup(groups.iterator().next()), true);
			app.goTo().contactPage();
		}

	}

	@Test
	public void testContactModification() {
		Groups groups = app.db().groups();
		Contacts before = app.db().contacts();
		ContactData modifiedContact = before.iterator().next();
		ContactData contact = new ContactData()
						.withId(modifiedContact.getId()).withFirstName("TestName").withLastName("TestLast").withEmail("test@email.com").withMobile("123456789").inGroup(groups.iterator().next());;
		app.goTo().contactPage();
		app.contact().modify(contact);
		app.goTo().contactPage();
		assertThat(app.contact().count(), equalTo(before.size()));
		Contacts after = app.db().contacts();
		assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
		verifyContactListInUI();

	}


}
