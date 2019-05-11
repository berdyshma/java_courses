package by.berdysh.java_course.addressbok.tests;

import by.berdysh.java_course.addressbok.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class GroupDeletionTests extends TestBase {

	@BeforeMethod
	public void ensurePreconditions() {
		app.goTo().groupPage();
		if (app.group().list().size() == 0) {
			app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
		}
	}

	@Test
	public void testGroupDeletion() throws Exception {
		List<GroupData> before = app.group().list();
		int index = before.size() - 1;
		app.group().delete(index);
		List <GroupData> after = app.group().list();
		Assert.assertEquals(after.size(), before.size() - 1);

		before.remove(index);
		Comparator<? super GroupData> byID = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
		before.sort(byID);
		after.sort (byID);
		Assert.assertEquals(before, after);
		}


}


