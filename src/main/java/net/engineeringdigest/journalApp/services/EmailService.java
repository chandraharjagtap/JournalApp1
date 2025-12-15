package net.engineeringdigest.journalApp.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(String to, String subject, String message) {
       try {
           SimpleMailMessage mailMessage = new SimpleMailMessage();
           mailMessage.setTo(to);
           mailMessage.setSubject(subject);
           mailMessage.setText(message);

           mailSender.send(mailMessage);
       }catch (Exception e){
           log.error("exception while send emial"+e);
       }
    }


}
