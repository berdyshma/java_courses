package by.berdysh.java_course.addressbok.appmanager;

import by.berdysh.java_course.addressbok.model.ContactData;
import by.berdysh.java_course.addressbok.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

	public ContactHelper(WebDriver wd) {

		super(wd);
	}

	public void submitContactForm() {
		click(By.xpath("(//input[@name='submit'])[2]"));
	}

	public void fillContactForm(ContactData contactData, boolean creation) {
		type(By.name("firstname"), contactData.getFirstName());
		type(By.name("lastname"), contactData.getLastName());
		type(By.name("email"), contactData.getEmail());
		type(By.name("mobile"), contactData.getMobile());

		if (creation) {
			new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
		} else {
			Assert.assertFalse(isElementPresent(By.name("new_group")));
		}

	}

	public void initContactCreation() {
		click(By.linkText("add new"));
	}

	public void selectContact(int index) {
		wd.findElements(By.name("selected[]")).get(index).click();
	}

	public void deleteSelectedContact() {
		click(By.xpath("(//input[@value='Delete'])"));
	}

	public void closeAlert() {
		wd.switchTo().alert().accept();
	}

	public void initContactModification() {
		click(By.xpath("(//img[@alt='Edit'])[1]"));
	}

	public void submitContactModification() {
		click(By.name("update"));
	}

	public void createContact(ContactData contact, boolean b) {
		initContactCreation();
		fillContactForm(contact, true);
		submitContactForm();
	}
	public void modifyContact(ContactData contact) {
		initContactModification();
		fillContactForm(contact, false);
		submitContactModification();
	}

	public boolean isThereAContact() {
		return isElementPresent(By.name("selected[]"));
	}

	public int getContactCount() {
		return wd.findElements(By.name("selected[]")).size();
	}

	public List<ContactData> getContactList() {
		List<ContactData> contacts = new ArrayList<>();
		List<WebElement> rows = wd.findElements(By.xpath("//tr[@name='entry']"));
		for (WebElement cell : rows) {
			List<WebElement> cells = cell.findElements(By.tagName("td"));
			String firstName = cells.get(2).getText();
			String lastName = cells.get(1).getText();
			int id = Integer.parseInt(cell.findElement(By.tagName("input")).getAttribute("value"));
			ContactData contact = new ContactData(id, firstName, lastName);
			contacts.add(contact);
		}
		return contacts;
	}
}
