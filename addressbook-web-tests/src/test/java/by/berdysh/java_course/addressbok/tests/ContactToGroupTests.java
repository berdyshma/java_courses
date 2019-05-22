package by.berdysh.java_course.addressbok.tests;


import by.berdysh.java_course.addressbok.model.ContactData;
import by.berdysh.java_course.addressbok.model.GroupData;
import by.berdysh.java_course.addressbok.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactToGroupTests extends TestBase {

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
	public void testAddContactToGroup() {
		ContactData selectedContact = app.db().contacts().iterator().next();
		GroupData currentGroup = app.db().groups().iterator().next();
		int contactId = selectedContact.getId();
		Groups groupsBefore = selectedContact.getGroups();
		if (groupsBefore.size() < app.db().groups().size()) {
			app.contact().addToGroup(selectedContact, currentGroup);
		}
		ContactData updatedContact = app.db().contacts().iterator().next().inGroup(currentGroup).withId(contactId);
		Groups groupsAfter = updatedContact.getGroups();
		assertThat(groupsAfter, equalTo(groupsBefore.withAdded(currentGroup)));
	}


}
