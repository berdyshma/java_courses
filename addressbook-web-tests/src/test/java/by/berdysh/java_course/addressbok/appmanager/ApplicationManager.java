package by.berdysh.java_course.addressbok.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
	WebDriver wd;

	private SessionHelper sessionHelper;
	private NavigationHelper navigationHelper;
	private ContactHelper contactHelper;
	private GroupHelper groupHelper;
	private String browser;

	public ApplicationManager(String browser) {
		this.browser = browser;
	}

	public void init() {
		if (browser.equals(BrowserType.FIREFOX)) {
			wd = new FirefoxDriver();
		} else if (browser.equals(BrowserType.CHROME)) {
			wd = new ChromeDriver();
		} else if (browser.equals(BrowserType.IE)) {
			wd = new InternetExplorerDriver();
		}

		wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		wd.get("http://localhost/addressbook/");
		groupHelper = new GroupHelper(wd);
		contactHelper = new ContactHelper(wd);
		navigationHelper = new NavigationHelper(wd);
		sessionHelper = new SessionHelper(wd);

		sessionHelper.login("admin", "secret");
	}


	public void logOut() {
		wd.findElement(By.linkText("Logout")).click();
	}

	public void stop() {
		wd.quit();
	}

	public boolean isElementPresent(By by) {
		try {
			wd.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}


	public GroupHelper group() {
		return groupHelper;
	}

	public ContactHelper contact() {
		return contactHelper;
	}

	public NavigationHelper goTo() {
		return navigationHelper;
	}
}
