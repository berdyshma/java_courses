package by.berdysh.java_course.mantis.tests;

import by.berdysh.java_course.mantis.appmanager.ApplicationManager;
import by.berdysh.java_course.mantis.model.Issue;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class TestBase {


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
		app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
	}

	@AfterSuite(alwaysRun = true)
	public void tearDown() throws Exception {
		app.ftp().restore("config_inc.php.bak", "config_inc.php");
		app.stop();

	}

	public boolean isIssueOpen(int issueID) throws RemoteException, ServiceException, MalformedURLException {
		Issue myIssue = app.soap().issueStatus(issueID);
		String status = myIssue.getStatus();
		if (status.equals("resolved") || status.equals("closed")) {
			return false;
		} else {
			return true;
		}
	}

	public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
		if (isIssueOpen(issueId)== true) {
			throw new SkipException("Ignored because of issue " + issueId);
		}
	}

}
