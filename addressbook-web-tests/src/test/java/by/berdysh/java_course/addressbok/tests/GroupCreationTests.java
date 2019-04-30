package by.berdysh.java_course.addressbok.tests;

import by.berdysh.java_course.addressbok.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class GroupCreationTests extends TestBase {


	@Test
	public void testGroupCreation() throws Exception {
		app.getNavigationHelper().goToGroupPage();
		List <GroupData> before = app.getGroupHelper().getGroupList();
		app.getGroupHelper().createGroup(new GroupData("test1", null, null));
		List <GroupData> after = app.getGroupHelper().getGroupList();
		Assert.assertEquals(after.size(), before.size() + 1);
		app.logOut();
	}

}
