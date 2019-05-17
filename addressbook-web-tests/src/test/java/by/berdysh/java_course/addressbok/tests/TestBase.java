package by.berdysh.java_course.addressbok.tests;

import by.berdysh.java_course.addressbok.appmanager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

public class TestBase {

	Logger logger = LoggerFactory.getLogger (TestBase.class);

	public static ApplicationManager app;

	static {
		try {
			app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@BeforeSuite(alwaysRun = true)
	public void setUp() throws Exception {
		app.init();
	}

	@AfterSuite(alwaysRun = true)
	public void tearDown() throws Exception {
		app.stop();
	}
	@BeforeMethod
	public void logTestStart(Method m, Object[] p){
		logger.info("Start test " + m.getName()+ "with parameters " + Arrays.asList(p));

	}

	@AfterMethod (alwaysRun = true)
	public void logTestStop(Method m){
		logger.info("Stop test " + m.getName());

	}

}
