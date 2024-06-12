package com.entidades.buenSabor.business.service;

import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

@Service
public class EmailService {

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private int port;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${spring.mail.from}")
    private String from;

    public void sendEmailWithAttachment(String to, String subject, String text, ByteArrayOutputStream attachmentStream, String attachmentName) throws EmailException, IOException {
        Properties mProperties = new Properties();
        mProperties.put("mail.smtp.host", host);
        mProperties.put("mail.smtp.ssl.trust", host);
        mProperties.put("mail.smtp.starttls.enable", "true");
        mProperties.put("mail.smtp.port", String.valueOf(port));
        mProperties.put("mail.smtp.user", username);
        mProperties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        mProperties.put("mail.smtp.auth", "true");

        Session mSession = Session.getInstance(mProperties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            MimeMessage mCorreo = new MimeMessage(mSession);
            mCorreo.setFrom(new InternetAddress(from));
            mCorreo.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            mCorreo.setSubject(subject);

            // Crear el cuerpo del mensaje
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(text, "ISO-8859-1", "html");

            // Crear el adjunto
            MimeBodyPart attachmentPart = new MimeBodyPart();
            ByteArrayDataSource dataSource = new ByteArrayDataSource(new ByteArrayInputStream(attachmentStream.toByteArray()), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            attachmentPart.setDataHandler(new DataHandler(dataSource));
            attachmentPart.setFileName(attachmentName);

            // Juntar todo
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentPart);

            mCorreo.setContent(multipart);

            Transport.send(mCorreo);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
