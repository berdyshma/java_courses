package by.berdysh.java_course.addressbok.tests;

import by.berdysh.java_course.addressbok.model.ContactData;
import by.berdysh.java_course.addressbok.model.Contacts;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {


		@DataProvider
	public Iterator<Object[]> validContacts() throws IOException {
		List<Object[]> list = new ArrayList<Object[]>();
		BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")));
		String line = reader.readLine();
		while (line != null){
			String[] split = line.split(";");
			list.add(new Object[]{new ContactData().withFirstName(split[0]).withLastName(split[1]).withMobile(split[2])
							.withEmail(split[3]).withGroup(split[4])});
			line = reader.readLine();
		}
		return list.iterator();
	}


	@Test(dataProvider = "validContacts")
	public void testContactCreation(ContactData contact) throws Exception {
		app.goTo().contactPage();
		Contacts before = app.contact().all();
		File photo = new File("src/test/resources/photo.jpg");
		app.contact().create(contact, true);
		app.goTo().contactPage();
		assertThat(app.contact().count(), equalTo(before.size() + 1));
		Contacts after = app.contact().all();
		assertThat(after, equalTo(
						before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

	}


}
