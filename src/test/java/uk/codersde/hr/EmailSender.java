package uk.codersde.hr;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {

    public static void main(String[] args) {
        // Sender's email address
        String senderEmail = "mariano.juri@gmail.com";
        // Sender's password
        String senderPassword = "vqkf nwjx lalo fqxt";

        // Recipient's email address
        String recipientEmail = "juri.mariano@gmail.com";

        // SMTP server settings (for Gmail)
        String host = "i";
        int port = 587; // Gmail SMTP port

        // Set up properties
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);

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

