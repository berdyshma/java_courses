package by.berdysh.java_course;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class Stickers {

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
	public void Stickers() {
		List<String> failedItems = new ArrayList<>();
		driver.get("http://localhost/litecart/en/");
		List<WebElement> ducks = driver.findElements(By.cssSelector("li.product"));
		for (WebElement duck : ducks) {
			List<WebElement> stickers = duck.findElements(By.cssSelector("div.sticker"));

			if (stickers.size() != 1) {
				failedItems.add(duck.findElement(By.cssSelector("div.name")).getText());
			}

		}

	}

	@After
	public void stop() {
		driver.quit();
		driver = null;
	}
}
