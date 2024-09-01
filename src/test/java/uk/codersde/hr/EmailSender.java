package uk.codersde.hr;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

import uk.codersden.hr.settings.Settings;

public class EmailSender {

	public static Settings getDefaultSettings() {
		Settings settings = new Settings();
		settings.setMailPassword("J@ck2012");
		settings.setMailUsername("info@codersden.uk");
		settings.setMailSmtpHost("smtp.titan.email");
		settings.setMailSmtpPort("465");
		settings.setMailSmtpAuth(true);
		settings.setMailSmtpSslEnable(true);
		
		
		return settings;
	}
	public static void send(Settings settings, String recipientEmail, String subject, String body) {
        // Sender's email address
        String senderEmail = settings.getMailUsername();
        // Sender's password
        String senderPassword = settings.getMailPassword();



        // SMTP server settings
        String host = settings.getMailSmtpHost();
        int port = Integer.valueOf(settings.getMailSmtpHost());

        // Set up properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", settings.isMailSmtpAuth());
        properties.put("mail.smtp.ssl.enable", settings.isMailSmtpSslEnable());
        
        // Create a session with authentication
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });
        
        try {
            // Create a default MimeMessage object
            MimeMessage message = new MimeMessage(session);

            // Set From: header field
            message.setFrom(new InternetAddress(senderEmail));

            // Set To: header field
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));

            // Set Subject: header field
            message.setSubject(subject);

            // Set the actual message
            message.setText(body);

            // Send message
            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
	}
    public static void main(String[] args) {
        // Sender's email address
        String senderEmail = "info@codersden.uk";
        // Sender's password
        String senderPassword = "J@ck2012";

        // Recipient's email address
        String recipientEmail = "mariano.juri@gmail.com";

        // SMTP server settings (for Gmail)
        String host = "smtp.titan.email";
        int port = 465; // Gmail SMTP port

        // Set up properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");

        // Create a session with authentication
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Create a default MimeMessage object
            MimeMessage message = new MimeMessage(session);

            // Set From: header field
            message.setFrom(new InternetAddress(senderEmail));

            // Set To: header field
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));

            // Set Subject: header field
            message.setSubject("Test Email");

            // Set the actual message
            message.setText("This is a test email sent from Java.");

            // Send message
            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}

