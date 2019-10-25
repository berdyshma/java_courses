package by.berdysh.java_course;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class AdminPanel {
	private WebDriver driver;
	private WebDriverWait wait;


	@Before
	public void start() {
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 10);

		driver.get("http://localhost/litecart/admin/login.php");
		driver.findElement(By.name("username")).sendKeys("admin");
		driver.findElement(By.name("password")).sendKeys("admin");
		driver.findElement(By.name("login")).click();
	}

	@Test
	public void AdminMenu() {

		List<WebElement> menuItems = driver.findElements(By.cssSelector("ul#box-apps-menu > li"));
		for (int i = 1; i <= menuItems.size(); i++) {
			WebElement menuItem = driver.findElement(By.cssSelector("ul#box-apps-menu > li:nth-child(" + i + ")"));
			String menuItemName = menuItem.getText();
			menuItem.click();


			List<WebElement> menuSubItems = driver.findElements(By.cssSelector("ul#box-apps-menu > li:nth-child(" + i + ") li"));
			for (int j = 2; j <= menuSubItems.size(); j++) {
				WebElement menuSubItem = driver.findElement(By.cssSelector("ul#box-apps-menu > li:nth-child(" + i + ") li:nth-child(" + j + ")"));
				String menuSubItemName = menuSubItem.getText();
				menuSubItem.click();


			}
		}

	}


	@After
	public void stop() {
		driver.quit();
		driver = null;
	}

}

