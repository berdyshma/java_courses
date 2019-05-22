package by.berdysh.java_course.addressbok.tests;

import by.berdysh.java_course.addressbok.model.ContactData;
import by.berdysh.java_course.addressbok.model.GroupData;
import by.berdysh.java_course.addressbok.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactFromGroupTests extends TestBase {
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
	public void testDeleteContactFromGroup() {
		GroupData currentGroup = app.db().groups().iterator().next();
		app.contact().goIntoSelectedGroup(currentGroup);
		ContactData removedContact = app.db().contacts().iterator().next();
		int contactId = removedContact.getId();
		Groups groupsBefore = removedContact.getGroups();
		if (groupsBefore.size() > 0) {
			app.contact().removeFromGroup(removedContact);
		}
		ContactData updatedContact = app.db().contacts().iterator().next().withId(contactId);
		Groups groupsAfter = updatedContact.getGroups();
		assertThat(groupsAfter, equalTo(groupsBefore.without(currentGroup)));
	}
}
