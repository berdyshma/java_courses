package by.berdysh.java_course.addressbok.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

	@Test
	public void testContactDeletion() {
		app.getNavigationHelper().goToContactPage();
		app.getContactHelper().selectGroup();
		app.getContactHelper().deleteSelectedContact();
		app.getContactHelper().closeAlert();
		app.getNavigationHelper().goToHomePage();
	}
}
