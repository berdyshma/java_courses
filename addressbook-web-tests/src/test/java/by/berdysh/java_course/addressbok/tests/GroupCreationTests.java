package by.berdysh.java_course.addressbok.tests;

import by.berdysh.java_course.addressbok.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.*;

public class GroupCreationTests extends TestBase {


	@Test
	public void testGroupCreation() throws Exception {
		app.getNavigationHelper().goToGroupPage();
		int before = app.getGroupHelper().getGroupCount();
		app.getGroupHelper().createGroup(new GroupData("test1", null, null));
		int after = app.getGroupHelper().getGroupCount();
		Assert.assertEquals(after, before + 1);
		app.logOut();
	}

}
