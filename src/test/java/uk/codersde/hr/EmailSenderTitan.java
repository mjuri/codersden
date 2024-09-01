package uk.codersde.hr;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSenderTitan {

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
        
        // Authenticate
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Create a default MimeMessage object
            Message message = new MimeMessage(session);

            // Set From field
            message.setFrom(new InternetAddress(senderEmail));

            // Set To field
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));

            // Set Subject
            message.setSubject("Hi mariano from codersden.uk");

            // Set Content
            message.setText("Here it's just a test");

            // Send message
            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

