package com.example.tda.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.tda.Controller.OrdersController;
import com.example.tda.entity.Order;
import com.example.tda.repository.OrderRepository;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import com.example.tda.service.PdfGeneratorService;

@Service
public class EmailService {


    @Autowired
    private JavaMailSender emailSender;
	private PdfGeneratorService pdfGeneratorService;
	private OrderRepository orderRepository;

    @Value("${spring.mail.username}") private String sender;


    public void sendEmail(String recipientEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject(subject);
        message.setText(body);
        emailSender.send(message);
    }
    
    public void
	sendMailWithAttachment(
		Integer id,
		String recipientEmail, 
		String subject, 
		String body, 
		String attachment)
	throws IOException{
		// Creating a mime message
		MimeMessage mimeMessage = emailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper;

		try {

			// Setting multipart as true for attachments to
			// be send
			mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setFrom(sender);
			mimeMessageHelper.setTo(recipientEmail);
			mimeMessageHelper.setText(body);
			mimeMessageHelper.setSubject(subject);

			// Create a PDF in memory
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			Document document = new Document();
			PdfWriter.getInstance(document, outputStream);
			document.open();
			document.add(new Paragraph("Hello, World! 123"));
			document.close();
			byte[] pdfBytes = outputStream.toByteArray();


			 // Create a DataSource with the PDF content
			 DataSource dataSource = new ByteArrayDataSource(pdfBytes, "application/pdf");

			
		 // Add an attachment to the email
		 mimeMessageHelper.addAttachment("sample2.pdf", dataSource);

		 // Send the email with attachment
		 emailSender.send(mimeMessage);
		} catch (MessagingException | DocumentException e) {
		 e.printStackTrace();
	 }
	}

}
