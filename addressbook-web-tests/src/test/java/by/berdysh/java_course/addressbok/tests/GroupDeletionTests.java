package by.berdysh.java_course.addressbok.tests;

import by.berdysh.java_course.addressbok.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {

	@Test
	public void testGroupDeletion() throws Exception {
				app.getNavigationHelper().goToGroupPage();
		if (! app.getGroupHelper().isThereAGroup()){
			app.getGroupHelper().createGroup(new GroupData("test1", null, null));
		}
		int before = app.getGroupHelper().getGroupCount();
		app.getGroupHelper().selectGroup();
		app.getGroupHelper().deleteSelectedGroup();
		app.getGroupHelper().returnToGroupPage();
		int after = app.getGroupHelper().getGroupCount();
		Assert.assertEquals(after, before - 1);
	}


}
