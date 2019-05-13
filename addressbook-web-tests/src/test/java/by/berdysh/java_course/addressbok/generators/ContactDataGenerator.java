package by.berdysh.java_course.addressbok.generators;

import by.berdysh.java_course.addressbok.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
	public static void main (String[] args) throws IOException {
		int count = Integer.parseInt(args[0]);
		File file = new File(args[1]);

		List<ContactData> contacts = generateContacts (count);
		save(contacts, file);
	}


	private static void save(List<ContactData> contacts, File file) throws IOException {
		Writer writer = new FileWriter(file);
		for (ContactData contact : contacts) {
			writer.write(String.format("%s;%s;%s\n", contact.getFirstName(), contact.getLastName(), contact.getMobile()));
		}
		writer.close();
	}

	private static List<ContactData> generateContacts(int count) {
		List<ContactData> contacts = new ArrayList<ContactData>();
		for (int i = 0; i < count; i++){
			contacts.add(new ContactData().withFirstName(String.format("FirstName %s", i))
							.withLastName(String.format("LastName %s", i)).withMobile(String.format("12345 %s", i)));
		}
		return contacts;
	}
}

