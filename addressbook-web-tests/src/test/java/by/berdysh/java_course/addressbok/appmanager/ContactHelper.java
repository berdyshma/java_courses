package by.berdysh.java_course.addressbok.appmanager;

import by.berdysh.java_course.addressbok.model.ContactData;
import by.berdysh.java_course.addressbok.model.Contacts;
import by.berdysh.java_course.addressbok.model.Groups;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

	public void selectContactById(int id) {
		wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
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

	public void create(ContactData contact, boolean b) {
		initContactCreation();
		fillContactForm(contact, true);
		submitContactForm();
		contactCache = null;
	}

	public void modify(ContactData contact) {
		initContactModification();
		fillContactForm(contact, false);
		submitContactModification();
		contactCache = null;
	}

	public void delete(ContactData contact) {
		selectContactById(contact.getId());
		deleteSelectedContact();
		closeAlert();
		contactCache = null;
	}

	public boolean isThereAContact() {
		return isElementPresent(By.name("selected[]"));
	}

	public int count() {
		return wd.findElements(By.name("selected[]")).size();
	}

	private Contacts contactCache = null;


	public Contacts all() {
		if (contactCache != null) {
			return new Contacts (contactCache);
		}

		contactCache = new Contacts();
		List<WebElement> rows = wd.findElements(By.xpath("//tr[@name='entry']"));
		for (WebElement cell : rows) {
			List<WebElement> cells = cell.findElements(By.tagName("td"));
			String firstName = cells.get(2).getText();
			String lastName = cells.get(1).getText();
			int id = Integer.parseInt(cell.findElement(By.tagName("input")).getAttribute("value"));
			contactCache.add(new ContactData()
							.withId(id).withFirstName(firstName).withLastName(lastName));
		}
		return new Contacts (contactCache);
	}


}
