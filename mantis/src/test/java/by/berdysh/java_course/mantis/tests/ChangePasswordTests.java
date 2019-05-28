package by.berdysh.java_course.mantis.tests;

import by.berdysh.java_course.mantis.model.MailMessage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase {

	@BeforeMethod
	public void startMailServerAndEnsureUser() throws IOException, MessagingException {
		app.mail().start();

	}

	@Test
	public void testChangePassword() throws MessagingException, IOException {
		app.changePass().loginAsAdminAndManageUsers();
		app.changePass().clickOnTestUserAndResetPass();
		List<MailMessage> newMessages = app.mail().waitForMail(1, 10000);
		String resetLink = app.changePass().findResetPasswordLink(newMessages, "berdysh@localhost.localdomain");
		app.changePass().changePassword(resetLink, "pass123");
		assertTrue(app.newSession().login("berdysh", "pass123"));
	}

	@AfterMethod(alwaysRun = true)
	public void stopMailServer() {
		app.mail().stop();
	}


}