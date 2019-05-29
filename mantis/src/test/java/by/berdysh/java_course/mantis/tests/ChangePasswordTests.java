package by.berdysh.java_course.mantis.tests;

import by.berdysh.java_course.mantis.model.MailMessage;
import by.berdysh.java_course.mantis.model.Users;
import by.berdysh.java_course.mantis.model.UsersData;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase {

	@BeforeMethod
	public void startMailServerAndEnsureUser() throws IOException, MessagingException {
		app.mail().start();

	}

	@Test
	public void testChangePassword() throws MessagingException, IOException {
		app.changePass().loginAsAdminAndManageUsers();
		Users users = app.db().users();
		Optional<UsersData> selectedUserO = users.stream().filter((u) -> !u.getUsername().contains("administrator")).findAny();
		UsersData selectedUser = selectedUserO.orElseThrow(() -> new IllegalStateException());
		String email = selectedUser.getEmail();
		String username = selectedUser.getUsername();
		app.changePass().clickOnTestUserAndResetPass(selectedUser);
		List<MailMessage> newMessages = app.mail().waitForMail(1, 10000);
		String resetLink = app.changePass().findResetPasswordLink(newMessages, email);
		app.changePass().changePassword(resetLink, "password");
		assertTrue(app.newSession().login(username, "password"));
	}


	@AfterMethod(alwaysRun = true)
	public void stopMailServer() {
		app.mail().stop();
	}


}