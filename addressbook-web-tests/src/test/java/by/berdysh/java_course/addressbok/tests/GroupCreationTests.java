package by.berdysh.java_course.addressbok.tests;

import by.berdysh.java_course.addressbok.model.GroupData;
import org.testng.annotations.*;

public class GroupCreationTests extends TestBase {


	@Test
	public void testGroupCreation() throws Exception {
		app.getNavigationHelper().goToGroupPage();
		app.getGroupHelper().initGroupCreation();
		app.getGroupHelper().fillGroupForm(new GroupData("test1", "test2", "test3"));
		app.getGroupHelper().submitGroupCreation();
		app.getGroupHelper().returnToGroupPage();
		app.logOut();
	}

}
