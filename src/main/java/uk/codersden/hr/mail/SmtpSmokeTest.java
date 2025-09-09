package uk.codersden.hr.mail;

//File: SmtpSmokeTest.java
//
//Usage (JavaMail javax.mail):
//javac -cp javax.mail-1.6.2.jar SmtpSmokeTest.java
//java  -cp .:javax.mail-1.6.2.jar SmtpSmokeTest send
//
//You can configure via ENV or args:
//ENV: SMTP_HOST (default smtp.titan.email), SMTP_USER, SMTP_PASS, SMTP_TO, SMTP_FROM
//ARG0 mode: "probe" (default, no email) or "send" (sends a message)
//Example:
//  SMTP_USER='notifications@yourdomain.com' \
//  SMTP_PASS='***' \
//  SMTP_TO='you@yourdomain.com' \
//  SMTP_FROM='notifications@yourdomain.com' \
//  java -cp .:javax.mail-1.6.2.jar SmtpSmokeTest send
//
//If using Jakarta Mail, replace imports with jakarta.mail.* and jakarta.mail.internet.*.

import javax.mail.*;
import javax.mail.internet.*;
import com.sun.mail.smtp.SMTPTransport;
import java.util.Date;
import java.util.Properties;

public class SmtpSmokeTest {

 public static void main(String[] args) {
     String mode = (args.length > 0) ? args[0].trim().toLowerCase() : "send";

     String host = getenvOr("SMTP_HOST", "smtp.titan.email");
     String user = getenvOr("SMTP_USER", "info@codersden.uk");
     String pass = getenvOr("SMTP_PASS", "J@ck2012");
     String to   = getenvOr("SMTP_TO",   user); // default to self if unset
     String from = getenvOr("SMTP_FROM", user); // best practice: from == authenticated user

     System.out.println("=== SMTP Smoke Test ===");
     System.out.println("Host: " + host);
     System.out.println("Mode: " + mode);
     System.out.println("User set: " + (!user.isEmpty()));
     System.out.println("To: " + to);
     System.out.println("From: " + from);
     System.out.println();

     boolean ok465  = testImplicitSSL465(host, user, pass, mode, from, to);
     boolean ok587  = testStartTLS587(host, user, pass, mode, from, to);

     System.out.println();
     System.out.println("Summary:");
     System.out.println("  465 (SSL):   " + (ok465 ? "OK" : "FAILED"));
     System.out.println("  587 (TLS):   " + (ok587 ? "OK" : "FAILED"));

     if (!ok465 && !ok587) {
         System.out.println("\nHints:");
         System.out.println(" - If both fail with 'response: -1', it’s likely a network/egress or TLS handshake issue.");
         System.out.println(" - Ensure the runtime supports TLS 1.2+ and ports 465/587 are allowed outbound.");
         System.out.println(" - For 465 use ssl.enable=true; for 587 use starttls.enable=true (not both).");
     }
 }

 private static boolean testImplicitSSL465(String host, String user, String pass, String mode,
                                           String from, String to) {
     System.out.println(">>> Testing 465 (implicit SSL)...");
     Properties props = new Properties();
     props.put("mail.smtp.host", host);
     props.put("mail.smtp.port", "465");
     props.put("mail.smtp.auth", "true");
     props.put("mail.smtp.ssl.enable", "true");         // implicit SSL is required on 465
     props.put("mail.smtp.ssl.protocols", "TLSv1.2");   // ensure TLS 1.2+
     props.put("mail.smtp.connectiontimeout", "10000");
     props.put("mail.smtp.timeout", "10000");
     props.put("mail.smtp.writetimeout", "10000");
     // props.put("mail.debug", "true"); // uncomment for verbose wire logs

     return runTest(props, user, pass, mode, from, to, "465/SSL");
 }

 private static boolean testStartTLS587(String host, String user, String pass, String mode,
                                        String from, String to) {
     System.out.println(">>> Testing 587 (STARTTLS)...");
     Properties props = new Properties();
     props.put("mail.smtp.host", host);
     props.put("mail.smtp.port", "587");
     props.put("mail.smtp.auth", "true");
     props.put("mail.smtp.starttls.enable", "true");    // STARTTLS on 587
     props.put("mail.smtp.starttls.required", "true");
     props.put("mail.smtp.ssl.protocols", "TLSv1.2");
     props.put("mail.smtp.connectiontimeout", "10000");
     props.put("mail.smtp.timeout", "10000");
     props.put("mail.smtp.writetimeout", "10000");
     // props.put("mail.debug", "true"); // uncomment for verbose wire logs

     return runTest(props, user, pass, mode, from, to, "587/STARTTLS");
 }

 private static boolean runTest(Properties props, String user, String pass, String mode,
                                String from, String to, String label) {
     try {
         Session session = Session.getInstance(props, new Authenticator() {
             @Override protected PasswordAuthentication getPasswordAuthentication() {
                 return new PasswordAuthentication(user, pass);
             }
         });

         // 1) Probe: open socket, EHLO, AUTH, close.
         try (SMTPTransport transport = (SMTPTransport) session.getTransport("smtp")) {
             System.out.println("[" + label + "] Connecting...");
             transport.connect();
             System.out.println("[" + label + "] Connected. Server: " + transport.getLastServerResponse());

             if (!"send".equals(mode)) {
                 transport.close();
                 System.out.println("[" + label + "] PROBE OK (no email sent).");
                 return true;
             }
         }

         // 2) Send a minimal message (send mode only)
         MimeMessage msg = new MimeMessage(session);
         msg.setFrom(new InternetAddress(from));
         msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
         msg.setSubject("SMTP Smoke Test (" + label + ")", "UTF-8");
         msg.setSentDate(new Date());
         msg.setText("Hello! This is a test message sent via " + label + ".", "UTF-8");

         System.out.println("[" + label + "] Sending test email...");
         Transport.send(msg);
         System.out.println("[" + label + "] SEND OK.");
         return true;

     } catch (Exception ex) {
         System.out.println("[" + label + "] FAILED: " + ex.getClass().getName() + " - " + ex.getMessage());
         ex.printStackTrace(System.out);
         return false;
     }
 }

 private static String getenvOr(String key, String def) {
     String v = System.getenv(key);
     return (v != null && !v.trim().isEmpty()) ? v.trim() : def;
 }
}
