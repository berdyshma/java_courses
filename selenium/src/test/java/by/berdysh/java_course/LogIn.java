package by.berdysh.java_course;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class LogIn {
	private WebDriver driver;
	private WebDriverWait wait;



	@Before
	public void start(){
		//driver = new ChromeDriver();
		//driver = new FirefoxDriver();
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
		caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
	  driver = new InternetExplorerDriver(caps);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait = new WebDriverWait (driver, 10);

	}

	@Test
	public void MyFirstTest(){
		driver.get("http://localhost/litecart/admin/login.php");
		driver.findElement(By.name("username")).sendKeys("admin");
		driver.findElement(By.name("password")).sendKeys("admin");
		driver.findElement(By.name("login")).click();
	}

	@After
	public void stop(){
		driver.quit();
		driver = null;
	}

}

