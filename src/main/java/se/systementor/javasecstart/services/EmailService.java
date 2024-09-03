package se.systementor.javasecstart.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendVerificationEmail(String name, String email, String token) throws MessagingException {
        String subject = "Please verify your email address";
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setSubject(subject);
        helper.setTo(email);
        helper.setText(verificationEmailTemplate(name, token), true);
        javaMailSender.send(message);
    }

    public String verificationEmailTemplate(String name, String token){
        return String.format("""
            <h1>Welcome %s!</h1>
            <p>Thank you for registering with our service. Please click the link below to verify your email address:</p>
            <p><a href="http://localhost:8080/verify/%s">Verify Email</a></p>
        """, name, token).trim();
    }


}
