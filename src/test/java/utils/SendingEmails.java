package utils;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendingEmails {

	Properties properties = null;
	Path currentRelativePath = Paths.get("");
	String s = currentRelativePath.toAbsolutePath().toString();
	String basePath = s + File.separator;

	public void sendEmail(String senderEmail, String senderPassword, String toEmails, String subject, String body)
			throws MessagingException {
		final String user = senderEmail;
		final String password = senderPassword;

		InternetAddress[] to = InternetAddress.parse(toEmails);

		// 1) get the session object
		Properties props = System.getProperties();
		String host = "smtp.gmail.com";
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		// 2) compose message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipients(Message.RecipientType.TO, to);

			message.setSubject(subject);

			// 3) create MimeBodyPart object and set your message text
			BodyPart messageBodyPart1 = new MimeBodyPart();
			messageBodyPart1.setText(body);

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart1);

			message.setContent(multipart);

			Transport.send(message);

			System.out.println("message sent....");
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
	}

}
