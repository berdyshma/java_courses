package by.berdysh.java_course.mantis.appmanager;

import by.berdysh.java_course.mantis.model.MailMessage;
import by.berdysh.java_course.mantis.model.UsersData;
import org.openqa.selenium.By;
import ru.lanwen.verbalregex.VerbalExpression;

import java.util.List;

public class ChangePasswordHelper extends HelperBase {

	public ChangePasswordHelper(ApplicationManager app) {
		super(app);
	}

	public void loginAsAdminAndManageUsers() {
		wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
		type(By.name("username"), "administrator");
		type(By.name("password"), "root");
		click(By.cssSelector("input[value='Login']"));
		click(By.linkText("Manage Users"));
	}


	public void clickOnTestUserAndResetPass(UsersData user) {
		click(By.linkText(String.format("%s", user.getUsername())));
		click(By.cssSelector("input[value='Reset Password']"));
	}


	public String findResetPasswordLink(List<MailMessage> mailMessages, String email) {
		MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
		VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
		return regex.getText(mailMessage.text);
	}

	public void changePassword(String confirmationLink, String password) {
		wd.get(confirmationLink);
		type(By.name("password"), password);
		type(By.name("password_confirm"), password);
		click(By.cssSelector("input[value='Update User']"));
	}
}