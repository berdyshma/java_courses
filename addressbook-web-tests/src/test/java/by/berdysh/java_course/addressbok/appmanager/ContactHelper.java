package by.berdysh.java_course.addressbok.appmanager;

import by.berdysh.java_course.addressbok.model.ContactData;
import by.berdysh.java_course.addressbok.model.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

public class ContactHelper extends HelperBase {

	public ContactHelper(WebDriver wd) {

		super(wd);
	}


	public void initContactCreation() {
		click(By.linkText("add new"));
	}

	public void fillContactForm(ContactData contactData, boolean creation) {
		type(By.name("firstname"), contactData.getFirstName());
		type(By.name("lastname"), contactData.getLastName());
		type(By.name("email"), contactData.getEmail());
		type(By.name("email2"), contactData.getEmail2());
		type(By.name("email3"), contactData.getEmail3());
		type(By.name("mobile"), contactData.getMobile());
		type(By.name("work"), contactData.getWorkPhone());
		type(By.name("home"), contactData.getHomePhone());
		type(By.name("address"), contactData.getAddress());
		attach(By.name("photo"), contactData.getPhoto());

		if (creation)  {
			if (contactData.getGroups().size() > 0) {
				Assert.assertTrue(contactData.getGroups().size() == 1);
				new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().name);
			}
		} else {
			Assert.assertFalse(isElementPresent(By.name("new_group")));
		}

	}

	public void submitContactForm() {
		click(By.xpath("(//input[@name='submit'])[2]"));
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
		initContactModificationById(contact.getId());
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
			return new Contacts(contactCache);
		}

		contactCache = new Contacts();
		List<WebElement> rows = wd.findElements(By.name("entry"));
		for (WebElement row : rows) {
			List<WebElement> cells = row.findElements(By.tagName("td"));
			int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
			String lastName = cells.get(1).getText();
			String firstName = cells.get(2).getText();
			String address = cells.get(3).getText();
			String allEmails = cells.get(4).getText();
			String allPhones = cells.get(5).getText();


			contactCache.add(new ContactData()
							.withId(id).withLastName(lastName).withFirstName(firstName).withAddress(address)
							.withAllEmails(allEmails).withAllPhones(allPhones));
		}
		return new Contacts(contactCache);
	}


	public ContactData infoFromEditForm(ContactData contact) {
		initContactModificationById(contact.getId());
		String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
		String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
		String home = wd.findElement(By.name("home")).getAttribute("value");
		String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
		String work = wd.findElement(By.name("work")).getAttribute("value");
		String email = wd.findElement(By.name("email")).getAttribute("value");
		String email2 = wd.findElement(By.name("email2")).getAttribute("value");
		String email3 = wd.findElement(By.name("email3")).getAttribute("value");
		String address = wd.findElement(By.name("address")).getAttribute("value");

		wd.navigate().back();
		return new ContactData()
						.withId(contact.getId()).withFirstName(firstname).withLastName(lastname)
						.withHomePhone(home).withMobile(mobile).withWorkPhone(work)
						.withEmail(email).withEmail2(email2).withEmail3(email3).withAddress(address);

	}

	private void initContactModificationById(int id) {
		WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
		WebElement row = checkbox.findElement(By.xpath("./../.."));
		List<WebElement> cells = row.findElements(By.tagName("td"));
		cells.get(7).findElement(By.tagName("a")).click();
	}

}

