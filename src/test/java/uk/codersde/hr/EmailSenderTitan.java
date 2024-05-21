package uk.codersde.hr;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSenderTitan {

    public static void main(String[] args) {
        // Sender's email address
        String senderEmail = "info@codersden.uk";
        // Sender's password
        String senderPassword = "M1ley@2023";

        // Recipient's email address
        String recipientEmail = "mariano.juri@gmail.com";

        // SMTP server settings (for Gmail)
        String host = "smtp.titan.email";
        int port = 465; // Gmail SMTP port

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
            System.out.println("Starting....");
            // Send message
            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}

