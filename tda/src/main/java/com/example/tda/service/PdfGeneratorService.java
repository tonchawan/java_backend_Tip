package com.example.tda.service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

// import java.util.Date;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import com.example.tda.entity.Order;
import com.example.tda.entity.Packages;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class PdfGeneratorService {
        public void export(HttpServletResponse response, Order order,Packages packages) throws DocumentException, IOException {
                Document document = new Document(PageSize.A4);

                 //Assume that the fonts is folder which contains all the fonts you want to use it
                FontFactory.register("D:/sei/project/Final_Tip/java_backend_Tip/tda/src/main/resources/static/THSarabunNew.ttf", "thsaraban");
                
                PdfWriter.getInstance(document, response.getOutputStream());
                document.open();
                // Font size for Header
                Font fontTitle = FontFactory.getFont("thsaraban", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                fontTitle.setSize(22);
                // Font size for normal text
                Font fontNormal = FontFactory.getFont("thsaraban", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                fontNormal.setSize(15);
                // Header
                Paragraph header = new Paragraph("Insurance", fontTitle);
                header.setAlignment(Paragraph.ALIGN_CENTER);
                // Add space below the header
                header.setSpacingAfter(50);

                Paragraph customerDetail = new Paragraph("Customer Detail", fontTitle);
                Paragraph firstName = new Paragraph("FirstName :  " + order.getFirstName() + Chunk.NEWLINE, fontNormal);

                Paragraph lastName = new Paragraph("LastName :  " + order.getLastName() + Chunk.NEWLINE, fontNormal);

                Paragraph identity = new Paragraph("Identity :  " + order.getIdentity() + Chunk.NEWLINE,
                                fontNormal);

                Paragraph email = new Paragraph("Email :  " + order.getEmail() + Chunk.NEWLINE, fontNormal);
                Paragraph phone = new Paragraph("Phone Number :  " + order.getPhone() + Chunk.NEWLINE,
                                fontNormal);

                Paragraph dob = new Paragraph("Date of birth :  " + order.getDob() + Chunk.NEWLINE,fontNormal);


                Paragraph addressDetail = new Paragraph("Address Detail", fontTitle);

                Paragraph address = new Paragraph(
                                "House Number :  " + order.getAddress() + Chunk.NEWLINE,
                                fontNormal);
                Paragraph subDistrict = new Paragraph(
                                "Sub District :  " + order.getSubDistrict() + Chunk.NEWLINE,
                                fontNormal);
                Paragraph district = new Paragraph(
                                "District :  " + order.getDistrict() + Chunk.NEWLINE,
                                fontNormal);
                Paragraph province = new Paragraph(
                                "Province :  " + order.getProvince() + Chunk.NEWLINE,
                                fontNormal);
                Paragraph zipCode = new Paragraph(
                                "Zip Code :  " + order.getZipCode() + Chunk.NEWLINE,
                                fontNormal);

                Paragraph premium = new Paragraph("Price :  " + packages.getPremium()+ Chunk.NEWLINE,
                                fontNormal);

                Paragraph benefiaial = new Paragraph("Beneficial :  " + order.getBenefiaial()+ Chunk.NEWLINE,
                                fontNormal);

                // Paragraph plan_coverage = new Paragraph("Coverage :  " + order.getPlan().getCoverage() + Chunk.NEWLINE,
                //                 fontNormal);
                Paragraph startDate = new Paragraph("Start Date :  " + order.getStartDate() + Chunk.NEWLINE,
                                fontNormal);
                Paragraph endDate = new Paragraph("End Date :  " + order.getEndDate() + Chunk.NEWLINE,
                                fontNormal);

                document.add(header);
                document.add(customerDetail);

                document.add(firstName);
                document.add(lastName);
                document.add(addressDetail);
                document.add(address);
                document.add(subDistrict);
                document.add(district);
                document.add(province);
                document.add(zipCode);
                document.add(identity);
                document.add(phone);
                document.add(email);
                document.add(phone);
                document.add(dob);
                document.add(premium);
                document.add(startDate);
                document.add(endDate);
                document.add(benefiaial);

                document.close();
               
        }
}

