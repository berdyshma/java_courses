package by.berdysh.java_course;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class MyFirstTest {
	private WebDriver driver;
	private WebDriverWait wait;

	@Before
	public void start(){
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait = new WebDriverWait (driver, 10);

	}

	@Test
	public void MyFirstTest(){
		driver.get("http://www.google.com");
		WebElement q = driver.findElement(By.name("q"));
		q.sendKeys("webdriver");
	}

	@After
	public void stop(){
		driver.quit();
		driver = null;
	}

}