package by.berdysh.java_course.addressbok;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ContactCreationTest extends TestBase {


	@Test
	public void testContactCreation() throws Exception {

		initContactCreation();
		fillContactForm(new ContactData("TestName", "TestLast", "test@email.com", "123456789"));
		submitContactForm();
	}


}
