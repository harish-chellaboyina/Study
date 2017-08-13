package Utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/*
 * This class is used to send a mail through Gmail
 */
public class SendMailTLS {

	public void mail(String receiverEmail, String receiverName,String subject, String mailMessage) throws Exception {

		final String username = "harish.chellaboyina@gmail.com";
		final String password = "@591ilovemydad";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("harish.chellaboyina@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(receiverEmail));
			message.setSubject(subject);
			message.setText(mailMessage);

			Transport.send(message);

			System.out.println("Done");

		} catch (Exception e) {
			throw e;
		}
	}
}