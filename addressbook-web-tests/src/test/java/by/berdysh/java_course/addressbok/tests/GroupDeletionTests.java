package by.berdysh.java_course.addressbok.tests;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {

	@Test
	public void testGroupDeletion() throws Exception {

		app.goToGroupPage();
		app.selectGroup();
		app.deleteSelectedGroup();
		app.returnToGroupPage();
	}


}
