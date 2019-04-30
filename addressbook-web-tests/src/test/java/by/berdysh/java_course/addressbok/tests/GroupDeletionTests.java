package by.berdysh.java_course.addressbok.tests;

import by.berdysh.java_course.addressbok.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GroupDeletionTests extends TestBase {

	@Test
	public void testGroupDeletion() throws Exception {
				app.getNavigationHelper().goToGroupPage();
		if (! app.getGroupHelper().isThereAGroup()){
			app.getGroupHelper().createGroup(new GroupData("test1", null, null));
		}
		List<GroupData> before = app.getGroupHelper().getGroupList();
		app.getGroupHelper().selectGroup(before.size() - 1);
		app.getGroupHelper().deleteSelectedGroup();
		app.getGroupHelper().returnToGroupPage();
		List <GroupData> after = app.getGroupHelper().getGroupList();
		Assert.assertEquals(after.size(), before.size() - 1);

		before.remove(before.size() - 1);
		Assert.assertEquals(before, after);
		}
	}


