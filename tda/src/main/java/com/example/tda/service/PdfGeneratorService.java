package com.example.tda.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
// import java.util.Date;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import com.example.tda.entity.Order;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class PdfGeneratorService {
        public void export(HttpServletResponse response, Order order) throws DocumentException, IOException {
                Document document = new Document(PageSize.A4);

                PdfWriter.getInstance(document, outputStream);
                document.open();
                // Font size for Header
                Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
                fontTitle.setSize(22);
                // Font size for normal text
                Font fontNormal = FontFactory.getFont(FontFactory.HELVETICA);
                fontNormal.setSize(15);
                // Header
                Paragraph header = new Paragraph("Insurance", fontTitle);
                header.setAlignment(Paragraph.ALIGN_CENTER);
                // Add space below the header
                header.setSpacingAfter(50);

                Paragraph customerDetail = new Paragraph("Customer Detail", fontTitle);
                Paragraph firstname = new Paragraph("FirstName :  " + order.getFirstName() + Chunk.NEWLINE, fontNormal);

                // Paragraph lastname = new Paragraph("LastName :  " + order.getLastname() + Chunk.NEWLINE, fontNormal);

                // Paragraph citizen_id = new Paragraph("Citizen Id :  " + order.getCitizen_id() + Chunk.NEWLINE,
                //                 fontNormal);

                // Paragraph email = new Paragraph("Email :  " + order.getEmail() + Chunk.NEWLINE, fontNormal);
                // Paragraph phone_number = new Paragraph("Phone Number :  " + order.getPhone_number() + Chunk.NEWLINE,
                //                 fontNormal);

                // Paragraph addressDetail = new Paragraph("Address Detail", fontTitle);

                // Paragraph address_house = new Paragraph(
                //                 "House Number :  " + order.getaddress_house_number() + Chunk.NEWLINE,
                //                 fontNormal);
                // Paragraph address_moo = new Paragraph(
                //                 "Moo :  " + order.getAddress_moo() + Chunk.NEWLINE,
                //                 fontNormal);
                // Paragraph address_village = new Paragraph(
                //                 "Building/Village :  " + order.getAddress_village() + Chunk.NEWLINE,
                //                 fontNormal);
                // Paragraph address_soi = new Paragraph(
                //                 "Soi :  " + order.getAddress_soi() + Chunk.NEWLINE,
                //                 fontNormal);
                // Paragraph address_road = new Paragraph(
                //                 "Road :  " + order.getAddress_road() + Chunk.NEWLINE,
                //                 fontNormal);
                // Paragraph address_amphur = new Paragraph(
                //                 "Amphur :  " + order.getAddress_amphur() + Chunk.NEWLINE,
                //                 fontNormal);
                // Paragraph address_tumbon = new Paragraph(
                //                 "Tombon :  " + order.getAddress_tumbon() + Chunk.NEWLINE,
                //                 fontNormal);
                // Paragraph address_province = new Paragraph(
                //                 "Province :  " + order.getAddress_province() + Chunk.NEWLINE,
                //                 fontNormal);
                // Paragraph address_zipcode = new Paragraph(
                //                 "Zipcode :  " + order.getAddress_zipcode() + Chunk.NEWLINE,
                //                 fontNormal);

                // Paragraph planDetail = new Paragraph("Plan Detail", fontTitle);

                // Paragraph plan_name = new Paragraph("Plan :  " + order.getPlan().getName() + Chunk.NEWLINE, fontNormal);

                // Paragraph plan_desc = new Paragraph("Description :  " + order.getPlan().getDesc() + Chunk.NEWLINE,
                //                 fontNormal);

                // Paragraph plan_price = new Paragraph("Price :  " + order.getPlan().getPrice() + Chunk.NEWLINE,
                //                 fontNormal);

                // Paragraph plan_coverage = new Paragraph("Coverage :  " + order.getPlan().getCoverage() + Chunk.NEWLINE,
                //                 fontNormal);
                // Paragraph start = new Paragraph("Start Date :  " + order.getCover_start_date() + Chunk.NEWLINE,
                //                 fontNormal);
                // Paragraph end = new Paragraph("End Date :  " + order.getCover_end_date() + Chunk.NEWLINE,
                //                 fontNormal);

                document.add(header);
                document.add(customerDetail);
                document.add(firstname);
                // document.add(lastname);
                // document.add(citizen_id);
                // document.add(email);
                // document.add(phone_number);

                // document.add(addressDetail);
                // document.add(address_house);
                // document.add(address_moo);
                // document.add(address_village);
                // document.add(address_soi);
                // document.add(address_road);
                // document.add(address_amphur);
                // document.add(address_tumbon);
                // document.add(address_province);
                // document.add(address_zipcode);

                // document.add(planDetail);
                // document.add(plan_name);
                // document.add(plan_desc);
                // document.add(plan_price);
                // document.add(plan_coverage);
                // document.add(start);
                // document.add(end);

                document.close();
               
        }
}
