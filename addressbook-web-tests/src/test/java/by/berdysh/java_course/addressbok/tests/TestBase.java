package by.berdysh.java_course.addressbok.tests;

import by.berdysh.java_course.addressbok.appmanager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {

	public final ApplicationManager app = new ApplicationManager(BrowserType.IE);

	@BeforeMethod(alwaysRun = true)
	public void setUp() throws Exception {
		app.init();
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() throws Exception {
		app.stop();
	}

}
