package com.email.services;

import com.email.model.EnvironmentConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javax.mail.Transport;

@Service
public class EmailService {

    @Autowired
    public EnvironmentConfig env;

    public boolean sendEmail(String subject, String message, String to) {

        boolean f = false;

        String from = env.getUserName();
        String password = env.getPassword();

        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();
        System.out.println("PROPERTIES "+properties);

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port","465");
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.auth","true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        session.setDebug(true);

        System.out.println(to + subject + message);
        System.out.println(from + password);

        MimeMessage m = new MimeMessage(session);

        try {

            m.setFrom(from);

            m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            m.setSubject(subject);

            m.setText(message);

            Transport.send(m);
            f=true;


        } catch (Exception e) {
            e.printStackTrace();
        }

        return f;
    }

}
