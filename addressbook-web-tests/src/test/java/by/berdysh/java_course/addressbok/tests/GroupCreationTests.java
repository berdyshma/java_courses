package by.berdysh.java_course.addressbok.tests;

import by.berdysh.java_course.addressbok.model.GroupData;
import org.testng.annotations.*;

public class GroupCreationTests extends TestBase {


	@Test
	public void testGroupCreation() throws Exception {
		app.goToGroupPage();
		app.initGroupCreation();
		app.fillGroupForm(new GroupData("test1", "test2", "test3"));
		app.submitGroupCreation();
		app.returnToGroupPage();
		app.logOut();
	}

}
