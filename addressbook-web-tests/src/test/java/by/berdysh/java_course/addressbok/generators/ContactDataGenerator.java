package by.berdysh.java_course.addressbok.generators;

import by.berdysh.java_course.addressbok.model.ContactData;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

	@Parameter(names = "-c", description = "Contact count")
	public int count;

	@Parameter(names = "-f", description = "Target file")
	public String file;

	public static void main(String[] args) throws IOException {
		ContactDataGenerator generator = new ContactDataGenerator();
		JCommander jCommander = new JCommander(generator);
		try {
			jCommander.parse(args);
		} catch (ParameterException ex) {
			jCommander.usage();
			return;
		}
		generator.run();

	}

	private void run() throws IOException {
		List<ContactData> contacts = generateContacts(count);
		save(contacts, new File(file));
	}


	private void save(List<ContactData> contacts, File file) throws IOException {
		Writer writer = new FileWriter(file);
		for (ContactData contact : contacts) {
			writer.write(String.format("%s;%s;%s;%s \n", contact.getFirstName(), contact.getLastName(), contact.getMobile(),contact.getEmail()));
		}
		writer.close();
	}

	private List<ContactData> generateContacts(int count) {
		List<ContactData> contacts = new ArrayList<ContactData>();
		for (int i = 0; i < count; i++) {
			contacts.add(new ContactData().withFirstName(String.format("FirstName %s", i))
							.withLastName(String.format("LastName %s", i)).withMobile(String.format("12345 %s", i))
			        .withEmail(String.format("test@test.com %s", i)));
		}
		return contacts;
	}
}

