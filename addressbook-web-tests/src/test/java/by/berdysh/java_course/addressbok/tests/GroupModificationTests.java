package by.berdysh.java_course.addressbok.tests;


import by.berdysh.java_course.addressbok.model.GroupData;
import org.testng.annotations.Test;

public class GroupModificationTests extends TestBase {

	@Test

	public void testGroupModification() {
		app.getNavigationHelper().goToGroupPage();
		app.getGroupHelper().selectGroup();
		app.getGroupHelper().initGroupModification();
		app.getGroupHelper().fillGroupForm(new GroupData("test1", "test2", "test3"));
		app.getGroupHelper().submitGroupModification();
		app.getGroupHelper().returnToGroupPage();
	}

}