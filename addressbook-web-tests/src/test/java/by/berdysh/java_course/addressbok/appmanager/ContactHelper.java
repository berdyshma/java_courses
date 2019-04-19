package by.berdysh.java_course.addressbok.appmanager;

import by.berdysh.java_course.addressbok.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactHelper extends HelperBase {

	public ContactHelper(WebDriver wd) {

		super(wd);
	}

	public void submitContactForm() {
		click(By.xpath("(//input[@name='submit'])[2]"));
	}

	public void fillContactForm(ContactData contactData) {
		type(By.name("firstname"), contactData.getFirstName());
		type(By.name("lastname"), contactData.getLastName());
		type(By.name("email"), contactData.getEmail());
		type(By.name("mobile"), contactData.getMobile());
	}

	public void initContactCreation() {
		click(By.linkText("add new"));
	}

	public void selectContact() {click(By.name("selected[]"));}

	public void deleteSelectedContact() {click(By.xpath("(//input[@value='Delete'])"));	}

	public void closeAlert() {wd.switchTo().alert().accept();	}

	public void initContactModification() {click(By.xpath("(//img[@alt='Edit'])[1]"));}

	public void submitContactModification() {	click(By.name("update"));		}
	}
