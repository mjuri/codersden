package uk.codersden.hr.mail;

import java.util.Optional;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uk.codersden.hr.profiles.RolePosition;

@RestController
@RequestMapping("/mail")
public class EmailSender {

	@CrossOrigin
	@GetMapping("/")
	public ResponseEntity<?> sendTest(){

        final String username = "info@codersden.com";
        final String password = "7YmBpuu7MH";
    	//final String username ="mariano.juri@gmail.com";
    	//final String password = "H4ngth3DJ";
        System.out.println("Starting.............");
        // Set mail properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.titan.email");
        props.put("mail.smtp.port", "465");
        /*props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");*/
        // Create a session with authentication
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a default MimeMessage object
            Message message = new MimeMessage(session);
            // Set From: header field of the header with the sender email address
            message.setFrom(new InternetAddress("info@codersden.com"));
            // Set To: header field of the header
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("mariano.juri@gmail.com"));
            // Set Subject: header field
            message.setSubject("Testing Subject");
            // Set the actual message
            message.setText("Hello, this is a test email!");

            // Send message
            Transport.send(message);
            System.out.println("Email sent successfully!");
            return ResponseEntity.ok("Email sent successfully!");


        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

