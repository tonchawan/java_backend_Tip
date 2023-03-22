package com.example.tda.service;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.tda.Controller.OrdersController;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender emailSender;
	private final PdfGeneratorService pdfGeneratorService;

    @Value("${spring.mail.username}") private String sender;


    public void sendEmail(String recipientEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject(subject);
        message.setText(body);
        emailSender.send(message);
    }
    
    public String
	sendMailWithAttachment(String recipientEmail, String subject, String body, String attachment)
	{
		// Creating a mime message
		MimeMessage mimeMessage
			= emailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper;

		try {

			// Setting multipart as true for attachments to
			// be send
			mimeMessageHelper
				= new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setFrom(sender);
			mimeMessageHelper.setTo(recipientEmail);
			mimeMessageHelper.setText(body);
			mimeMessageHelper.setSubject(subject);

			// Adding the attachment
			FileSystemResource file
				= new FileSystemResource(new File(PdfGeneratorService.class.export()));


			mimeMessageHelper.addAttachment(
				"policy.pdf", file);

			// Sending the mail
			emailSender.send(mimeMessage);
			return "Mail sent Successfully";
		}

		// Catch block to handle MessagingException
		catch (MessagingException e) {

			// Display message when exception occurred
			return "Error while sending mail!!!";
		}
	}

}
