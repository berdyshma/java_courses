package by.berdysh.java_course.mantis.appmanager;

import by.berdysh.java_course.mantis.model.MailMessage;
import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class MailHelper {
	private ApplicationManager app;
	private Wiser wiser;

	public MailHelper(ApplicationManager app) {
		this.app = app;
		wiser = new Wiser();
	}

	public List<MailMessage> waitForMail(int count, long timeout) {
		long start = System.currentTimeMillis();
		while (System.currentTimeMillis() < start + timeout) {
			if (wiser.getMessages().size() >= count) {
				return wiser.getMessages().stream().map((m) -> toModelMail(m)).collect(Collectors.toList());
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();

			}
		}
		throw new Error("No mail :(");
	}

	public static MailMessage toModelMail(WiserMessage m) {
		try {
			MimeMessage mm = m.getMimeMessage();
			return new MailMessage(mm.getAllRecipients()[0].toString(), (String) mm.getContent());
		} catch (MessagingException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

	public void start() {
		wiser.start();
	}

	public void stop() {
		wiser.stop();
	}

}