package uk.codersden.hr.mail;

import java.util.Optional;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import uk.codersden.hr.settings.Settings;
import uk.codersden.hr.settings.SettingsDao;

@Service
public class EmailService {
	
	@Autowired
	private SettingsDao settingsDao;
	
	public void sendMail(String accountIdentifier, String mailFrom, String mailTo, String subject, String text) {

    	String username ="mariano.juri@gmail.com";
    	String password = "H4ngth3DJ";
    	
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.connectiontimeout", "10000");
        props.put("mail.smtp.timeout", "10000");
        props.put("mail.smtp.writetimeout", "10000");
        
        Optional<Settings> op = settingsDao.findById(accountIdentifier);
		Settings settings;
		if(!op.isEmpty()) {
			settings = op.get();
	        props.put("mail.smtp.auth", settings.isMailSmtpAuth());
	        props.put("mail.smtp.starttls.enable", settings.isMailSmtpStarttlsEnable());
	        props.put("mail.smtp.host", settings.getMailSmtpHost());
	        props.put("mail.smtp.port", settings.getMailSmtpPort());
	        props.put("mail.smtp.starttls.required", "true");
	        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
	        props.put("mail.smtp.connectiontimeout", "10000");
	        props.put("mail.smtp.timeout", "10000");
	        props.put("mail.smtp.writetimeout", "10000");
	        
	        // username = settings.getMailUsername();
	        username = "info@codersden.uk";
	        password = "jack2012";
	        // password = settings.getMailPassword();
		}
		
		final String mailUsername = username;
		final String mailPassword = password;
        // Create a session with authentication
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailUsername, mailPassword);
            }
        });
        
        
        try {
            // Create a default MimeMessage object
            Message message = new MimeMessage(session);
            // Set From: header field of the header with the sender email address
            message.setFrom(new InternetAddress(mailTo));
            // Set To: header field of the header
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailTo));
            // Set Subject: header field
            message.setSubject(subject);
            // Set the actual message
            message.setText(text);

            // Send message
            Transport.send(message);
            System.out.println("Email sent successfully!");


        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
	}
}
