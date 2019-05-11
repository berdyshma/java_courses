package by.berdysh.java_course.addressbok.tests;

import by.berdysh.java_course.addressbok.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {


	@Test
	public void testGroupCreation() throws Exception {
		app.goTo().groupPage();
		List<GroupData> before = app.group().list();
		GroupData group = new GroupData().withName("test1").withHeader("test2").withFooter("test3");
		app.group().create(group);
		List<GroupData> after = app.group().list();
		Assert.assertEquals(after.size(), before.size() + 1);

		before.add(group);
		Comparator<? super GroupData> byID = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
		before.sort(byID);
		after.sort(byID);
		Assert.assertEquals(before,after);
		}

}
